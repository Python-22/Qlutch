package net.milkbowl.vault.commands.cmds.admin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Login extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredAdmin.contains(p.getName())) {
			if (args.length <= 2) {
				p.sendMessage(Settings.USAGE("login <player> <rank>"));
			} else {
				Player target = Bukkit.getServer().getPlayer(args[1]);
				if (target == null) {
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
				} else {
					if (plugin.registered.contains(target.getName())) {
						p.sendMessage(Settings.PREFIX("This player is already logged in"));
					} else {
						if (args[2].equalsIgnoreCase("admin")) {
							plugin.registered.add(target.getName());
							plugin.registeredPremium.add(target.getName());
							plugin.registeredAdmin.add(target.getName());
							p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " has been logged into " + Settings.HIGHLIGHT_COLOUR + Settings.NAME + " admin"));
						} else if (args[2].equalsIgnoreCase("premium")) {
							plugin.registered.add(target.getName());
							plugin.registeredPremium.add(target.getName());
							p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " has been logged into " + Settings.HIGHLIGHT_COLOUR + Settings.NAME + " premium"));
						} else if (args[2].equalsIgnoreCase("lite")) {
							plugin.registered.add(target.getName());
							p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " has been logged into " + Settings.HIGHLIGHT_COLOUR + Settings.NAME + " lite"));
						} else {
							plugin.registered.add(target.getName());
							plugin.registeredFree.add(target.getName());
							p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " has been logged into " + Settings.HIGHLIGHT_COLOUR + Settings.NAME + " free"));
						}
						target.sendMessage(Settings.PREFIX("You have been logged into " + Settings.HIGHLIGHT_COLOUR + Settings.NAME + " " + args[2].toLowerCase() + Settings.SECONDARY_COLOUR + " by an admin"));
					}
				}
			}
		} else {
			p.sendMessage(Settings.PREFIX("You must be an " + Settings.HIGHLIGHT_COLOUR + "admin " + Settings.SECONDARY_COLOUR + " to execute this command"));
		}
	}

}
