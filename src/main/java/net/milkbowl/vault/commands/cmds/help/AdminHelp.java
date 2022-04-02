package net.milkbowl.vault.commands.cmds.help;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.methods.Tips;
import net.milkbowl.vault.util.Settings;

public class AdminHelp extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		int maxPages = 1;
		if (plugin.registeredAdmin.contains(p.getName())) {
			if (args.length <= 1) {
				p.sendMessage(Settings.USAGE("adminhelp <1-" + maxPages + ">"));
			} else {
				if (args[1].equalsIgnoreCase("1")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
					p.sendMessage("         "+Settings.PRIMARY_COLOUR + Settings.NAME + " by " + Settings.AUTHOR);
					p.sendMessage("");
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7spy &8l &fSpys on everyones " + Settings.NAME + " commands"));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7login &8l &fLogins another user"));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7forcelogout &8l &fLogouts another user"));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7logoutall &8l &fLogs out all other users logged in"));
					p.sendMessage("");
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8("+Settings.PRIMARY_COLOUR+"Tip&8) l &7" + Tips.getTip()));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
				}
			}
		} else {
			p.sendMessage(Settings.PREFIX("You must be an " + Settings.HIGHLIGHT_COLOUR + "admin " + Settings.SECONDARY_COLOUR + " to execute this command"));
		}
	}

}
