package net.milkbowl.vault.protocol;

import net.milkbowl.vault.protocol.Reflection.FieldAccessor;
import net.milkbowl.vault.protocol.Reflection.MethodInvoker;
import com.google.common.collect.MapMaker;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

/**
 * Represents a very tiny alternative to ProtocolLib.
 * <p>
 * It now supports intercepting packets during login and status ping (such as OUT_SERVER_PING)!
 * <p>
 * Modified by fren_gor to support 1.17+ servers
 *
 * @author Kristian
 */
public class TinyProtocol {
    private static final AtomicInteger ID = new AtomicInteger(0);

    public static TinyProtocol tinyprotocol;

    // Used in order to lookup a channel
    private static final MethodInvoker getPlayerHandle = Reflection.getMethod("{obc}.entity.CraftPlayer", "getHandle");
    private static final Class<?> playerConnectionClass = Reflection.getUntypedClass("{nms.server.network}.PlayerConnection");
    private static final Class<?> networkManagerClass = Reflection.getUntypedClass("{nms.network}.NetworkManager");
    private static final FieldAccessor<?> getConnection = Reflection.getField("{nms.server.level}.EntityPlayer", null, playerConnectionClass);
    private static final FieldAccessor<?> getManager = Reflection.getField(playerConnectionClass, null, networkManagerClass);
    private static final FieldAccessor<Channel> getChannel = Reflection.getField(networkManagerClass, Channel.class, 0);

    // Looking up ServerConnection
    private static final Class<?> minecraftServerClass = Reflection.getUntypedClass("{nms.server}.MinecraftServer");
    private static final Class<?> serverConnectionClass = Reflection.getUntypedClass("{nms.server.network}.ServerConnection");
    private static final FieldAccessor<?> getMinecraftServer = Reflection.getField("{obc}.CraftServer", minecraftServerClass, 0);
    private static final FieldAccessor<?> getServerConnection = Reflection.getField(minecraftServerClass, serverConnectionClass, 0);
    private static final FieldAccessor<List> getChannelFutures = Reflection.getField(serverConnectionClass, List.class, 0);
    private static final FieldAccessor<List> getNetworkMarkers = Reflection.getField(serverConnectionClass, List.class, 1);


    // Speedup channel lookup
    private Listener listener;

    // Channels that have already been removed
    private final Set<Channel> uninjectedChannels = Collections.newSetFromMap(new MapMaker().weakKeys().makeMap());

    // List of network markers
    private List<Object> networkManagers;

    // Injected channel handlers
    private final List<Channel> serverChannels = new ArrayList<>();
    private ChannelInboundHandlerAdapter serverChannelHandler;
    private ChannelInitializer<Channel> beginInitProtocol;
    private ChannelInitializer<Channel> endInitProtocol;

    // Current handler name
    private final String handlerName;

    protected volatile boolean closed = false, injected = false;
    protected final Plugin plugin;

    private List<PacketListener> protocolsListeners = new ArrayList<PacketListener>();

    /**
     * Construct a new instance of TinyProtocol, and start intercepting packets for all connected clients and future clients.
     * <p>
     * You can construct multiple instances per plugin.
     *
     * @param plugin - the plugin.
     */
    public TinyProtocol(final Plugin plugin) {
        this.plugin = plugin;

        this.tinyprotocol = this;


        // Compute handler name
        this.handlerName = getHandlerName();

        // Prepare existing players
        registerBukkitEvents();

        try {
            registerChannelHandler();
            registerPlayers(plugin);
            injected = true;
        } catch (IllegalArgumentException ex) {
            // Damn you, late bind
            plugin.getLogger().info("[TinyProtocol] Delaying server channel injection due to late bind.");

            new BukkitRunnable() {
                @Override
                public void run() {
                    registerChannelHandler();
                    registerPlayers(plugin);
                    injected = true;
                    plugin.getLogger().info("[TinyProtocol] Late bind injection successful.");
                }
            }.runTask(plugin);
        }
    }

    private void createServerChannelHandler() {
        // Handle connected channels
        endInitProtocol = new ChannelInitializer<Channel>() {

            @Override
            protected void initChannel(Channel channel) {
                try {
                    // This can take a while, so we need to stop the main thread from interfering
                    synchronized (networkManagers) {
                        // Stop injecting channels
                        if (!closed) {
                            channel.eventLoop().submit(() -> injectChannelInternal(channel));
                        }
                    }
                } catch (Exception e) {
                    plugin.getLogger().log(Level.SEVERE, "Cannot inject incoming channel " + channel, e);
                }
            }

        };

        // This is executed before Minecraft's channel handler
        beginInitProtocol = new ChannelInitializer<Channel>() {

            @Override
            protected void initChannel(Channel channel) {
                channel.pipeline().addLast(endInitProtocol);
            }

        };

        serverChannelHandler = new ChannelInboundHandlerAdapter() {

            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                Channel channel = (Channel) msg;

                // Prepare to initialize ths channel
                channel.pipeline().addFirst(beginInitProtocol);
                ctx.fireChannelRead(msg);
            }

        };
    }

    /**
     * Register bukkit events.
     */
    private void registerBukkitEvents() {
        listener = new Listener() {

            @EventHandler(priority = EventPriority.MONITOR)
            public void onPlayerLogin(PlayerLoginEvent e) {
                if (closed)
                    return;

                injectPlayer(e.getPlayer());

            }

            @EventHandler
            public void onPluginDisable(PluginDisableEvent e) {
                if (e.getPlugin().equals(plugin)) {
                    close();
                }
            }

        };

        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

    @SuppressWarnings("unchecked")
    private void registerChannelHandler() {
        Object mcServer = getMinecraftServer.get(Bukkit.getServer());
        Object serverConnection = getServerConnection.get(mcServer);

        // We need to synchronize against this list
        networkManagers = getNetworkMarkers.get(serverConnection);
        List<ChannelFuture> futures = getChannelFutures.get(serverConnection);

        createServerChannelHandler();

        synchronized (futures) {
            for (ChannelFuture item : futures) {
                // Channel future that contains the server connection
                Channel serverChannel = item.channel();

                serverChannels.add(serverChannel);
                serverChannel.pipeline().addFirst(serverChannelHandler);
            }
        }
    }

    private void unregisterChannelHandler() {
        if (serverChannelHandler == null)
            return;

        for (Channel serverChannel : serverChannels) {
            final ChannelPipeline pipeline = serverChannel.pipeline();

            // Remove channel handler
            serverChannel.eventLoop().execute(() -> {
                try {
                    pipeline.remove(serverChannelHandler);
                } catch (NoSuchElementException e) {
                    // That's fine
                }
            });
        }
    }

    private void registerPlayers(Plugin plugin) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            injectPlayer(player);
        }
    }



    /**
     * Invoked when the server has received a packet from a given player.
     * <p>
     * Use {@link Channel#remoteAddress()} to get the remote address of the client.
     *
     * @param sender - the player that sent the packet, {@code null} for early login/status packets.
     * @param channel - channel that received the packet. Never {@code null}.
     * @param packet - the packet being received.
     * @return The packet to receive instead, or {@code null} to cancel.
     */
    public Object onPacketInAsync(Player sender, Channel channel, Object packet) {
        return packet;
    }

    /**
     * Send a packet to a particular player.
     * <p>
     * Note that {onPacketOutAsync(Player, Channel, Object)} will be invoked with this packet.
     *
     * @param player - the destination player.
     * @param packet - the packet to send.
     */
    public void sendPacket(Player player, Object packet) {
        sendPacket(getChannel(player), packet);
    }

    /**
     * Send a packet to a particular client.
     * <p>
     * Note that {onPacketOutAsync(Player, Channel, Object)} will be invoked with this packet.
     *
     * @param channel - client identified by a channel.
     * @param packet - the packet to send.
     */
    public void sendPacket(Channel channel, Object packet) {
        channel.pipeline().writeAndFlush(packet);
    }

    /**
     * Pretend that a given packet has been received from a player.
     * <p>
     * Note that {@link #onPacketInAsync(Player, Channel, Object)} will be invoked with this packet.
     *
     * @param player - the player that sent the packet.
     * @param packet - the packet that will be received by the server.
     */
    public void receivePacket(Player player, Object packet) {
        receivePacket(getChannel(player), packet);
    }

    /**
     * Pretend that a given packet has been received from a given client.
     * <p>
     * Note that {@link #onPacketInAsync(Player, Channel, Object)} will be invoked with this packet.
     *
     * @param channel - client identified by a channel.
     * @param packet - the packet that will be received by the server.
     */
    public void receivePacket(Channel channel, Object packet) {
        channel.pipeline().context("encoder").fireChannelRead(packet);
    }

    /**
     * Retrieve the name of the channel injector, default implementation is "tiny-" + plugin name + "-" + a unique ID.
     * <p>
     * Note that this method will only be invoked once. It is no longer necessary to override this to support multiple instances.
     *
     * @return A unique channel handler name.
     */
    protected String getHandlerName() {
        return "tiny-" + plugin.getName() + "-" + ID.incrementAndGet();
    }

    /**
     * Add a custom channel handler to the given player's channel pipeline, allowing us to intercept sent and received packets.
     * <p>
     * This will automatically be called when a player has logged in.
     *
     * @param player - the player to inject.
     */
    public void injectPlayer(Player player) {
        if(getChannel(player) == null) 		return; // Inject to a later point in the login process

        injectChannelInternal(getChannel(player)).player = player;
    }

    /**
     * Add a custom channel handler to the given channel.
     *
     * @param channel - the channel to inject.
     */
    public void injectChannel(Channel channel) {
        injectChannelInternal(channel);
    }

    /**
     * Add a custom channel handler to the given channel.
     *
     * @param channel - the channel to inject.
     * @return The packet interceptor.
     */
    private PacketInterceptor injectChannelInternal(Channel channel) {

        try {
            PacketInterceptor interceptor = (PacketInterceptor) channel.pipeline().get(handlerName);

            // Inject our packet interceptor
            if (interceptor == null) {
                interceptor = new PacketInterceptor();
                channel.pipeline().addBefore("packet_handler", handlerName, interceptor);
                uninjectedChannels.remove(channel);
            }

            return interceptor;
        } catch (IllegalArgumentException e) {
            // Try again
            return (PacketInterceptor) channel.pipeline().get(handlerName);
        }
    }

    /**
     * Retrieve the Netty channel associated with a player. This is cached.
     *
     * @param player - the player.
     * @return The Netty channel.
     */
    public Channel getChannel(Player player) {
        try{
            Object connection = getConnection.get(getPlayerHandle.invoke(player));
            Object manager = getManager.get(connection);
            return getChannel.get(manager);

        }catch (Throwable t) {
            return null;
        }




    }

    /**
     * Uninject a specific player.
     *
     * @param player - the injected player.
     */
    public void uninjectPlayer(Player player) {
        uninjectChannel(getChannel(player));
    }

    public List<PacketListener> getProtocolsListeners() {
        return protocolsListeners;
    }

    public void addProtocolsListener(PacketListener	listener) {
        if(this.protocolsListeners.contains(listener)) return;

        this.protocolsListeners.add(listener);
    }

    public void removeProtocolsListener(PacketListener	listener) {
        this.protocolsListeners.remove(listener);
    }



    /**
     * Uninject a specific channel.
     * <p>
     * This will also disable the automatic channel injection that occurs when a player has properly logged in.
     *
     * @param channel - the injected channel.
     */
    public void uninjectChannel(final Channel channel) {
        // No need to guard against this if we're closing
        if (!closed) {
            uninjectedChannels.add(channel);
        }

        // See ChannelInjector in ProtocolLib, line 590
        channel.eventLoop().execute(() -> channel.pipeline().remove(handlerName));
    }

    /**
     * Determine if the given player has been injected by TinyProtocol.
     *
     * @param player - the player.
     * @return TRUE if it is, FALSE otherwise.
     */
    public boolean hasInjected(Player player) {
        return hasInjected(getChannel(player));
    }

    /**
     * Determine if the given channel has been injected by TinyProtocol.
     *
     * @param channel - the channel.
     * @return TRUE if it is, FALSE otherwise.
     */
    public boolean hasInjected(Channel channel) {
        return channel.pipeline().get(handlerName) != null;
    }

    /**
     * Cease listening for packets. This is called automatically when your plugin is disabled.
     */
    public final void close() {
        if (!closed) {
            closed = true;

            // Remove our handlers
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                uninjectPlayer(player);
            }

            // Clean up Bukkit
            HandlerList.unregisterAll(listener);
            unregisterChannelHandler();
        }
    }

    /**
     * Channel handler that is inserted into the player's channel pipeline, allowing us to intercept sent and received packets.
     *
     * @author Kristian
     */
    private final class PacketInterceptor extends ChannelDuplexHandler {
        // Updated by the login event
        public volatile Player player;

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            // Intercept channel
            final Channel channel = ctx.channel();



            try {


                if(player == null) {
                    registerPlayers(null);
                }
                for(PacketListener pl : protocolsListeners)
                    try {
                        msg = pl.onPacketInAsync(player, channel, msg);
                    }catch (Throwable ignored) {}
            } catch (Exception e) {
                plugin.getLogger().log(Level.SEVERE, "Error in onPacketInAsync().", e);
            }

            if (msg != null) {
                super.channelRead(ctx, msg);
            }
        }

        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            try {
                for(PacketListener pl : protocolsListeners)
                    msg = pl.onPacketOutAsync(player, ctx.channel(), msg);
            } catch (Exception e) {
                plugin.getLogger().log(Level.SEVERE, "Error in onPacketOutAsync().", e);
            }

            if (msg != null) {
                super.write(ctx, msg, promise);
            }
        }


    }
    public static TinyProtocol getTinyProtocol() {
        return tinyprotocol;
    }
    public void registerListener(PacketListener listener) {
        this.protocolsListeners.add(listener);
    }
    public static abstract class PacketListener {

        public PacketListener(){
            TinyProtocol.getTinyProtocol().registerListener(this);
        }

        public Object onPacketOutAsync(Player reciever, Channel channel, Object packet){
            return packet;
        }

        public Object onPacketInAsync(Player sender, Channel channel, Object packet){
            return packet;
        }
    }
}