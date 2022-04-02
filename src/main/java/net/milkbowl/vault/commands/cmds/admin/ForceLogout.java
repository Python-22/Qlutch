package net.milkbowl.vault.commands.cmds.admin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class ForceLogout extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredAdmin.contains(p.getName())) {
			if (args.length <= 1) {
				p.sendMessage(Settings.USAGE("forcelogout <player>"));
			} else {
				Player target = Bukkit.getServer().getPlayer(args[1]);
				if (target == null) {
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
				} else {
					if (!plugin.registered.contains(target.getName())) {
						p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " is not logged in."));
					} else {
						plugin.registered.remove(target.getName());
						p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " has been logged out."));
						target.sendMessage(Settings.PREFIX("You have been logged out of " + Settings.NAME + " by an admin"));
						plugin.registeredPremium.remove(target.getName());
						plugin.registeredAdmin.remove(target.getName());
					}
				}
			}
		} else {
			p.sendMessage(Settings.PREFIX("You must be an " + Settings.HIGHLIGHT_COLOUR + "admin " + Settings.SECONDARY_COLOUR + "to execute this command"));
		}
	}

}
