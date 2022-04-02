package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Whitelist extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("whitelist <on | off | reload | add | remove>"));
		} else {
			if (args[1].equalsIgnoreCase("on")) {
				Bukkit.setWhitelist(true);
				p.sendMessage(Settings.PREFIX("Whitelist has turned on!"));
			} else if (args[1].equalsIgnoreCase("off")) {
				Bukkit.setWhitelist(false);
				p.sendMessage(Settings.PREFIX("Whitelist has been turned off!"));
			} else if (args[1].equalsIgnoreCase("reload")) {
				Bukkit.reloadWhitelist();
				p.sendMessage(Settings.PREFIX("Whitelist has been reloaded!"));
			} else if (args[1].equalsIgnoreCase("add")) {
				if (args.length <= 2) {
					p.setWhitelisted(true);
					p.sendMessage(Settings.PREFIX("You have been added to the whitelist!"));
				} else {
					if (args[1].equals("*")) {
						for (Player a : Bukkit.getOnlinePlayers()) {
							a.setWhitelisted(true);
							p.sendMessage(Settings.PREFIX("All online players are now whitelisted"));
						}
					} else {
						Player target = Bukkit.getServer().getPlayer(args[2]);
						if (target == null) {
							p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
						} else {
							target.setWhitelisted(true);
							p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " is now whitelisted!"));
						}
					}
				}
			}else if (args[1].equalsIgnoreCase("remove")) {
				if (args.length <= 2) {
					p.setWhitelisted(false);
					p.sendMessage(Settings.PREFIX("You have been removed from the whitelist!"));
				} else {
					if (args[1].equals("*")) {
						for (Player a : Bukkit.getOnlinePlayers()) {
							a.setWhitelisted(false);
							p.sendMessage(Settings.PREFIX("All online players have been removed from the whitelist"));
						}
					} else {
						Player target = Bukkit.getServer().getPlayer(args[2]);
						if (target == null) {
							p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
						} else {
							target.setWhitelisted(false);
							p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " is no longer whitelisted!"));
						}
					}
				}
			}
		}
	}

}
