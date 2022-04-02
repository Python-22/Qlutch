package net.milkbowl.vault.commands.cmds.premium;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Theme extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredPremium.contains(p.getName())) {
			if (args.length <= 1) {
				p.sendMessage(Settings.USAGE("theme <blue | green | red | gold | purple>"));
			} else {
				String colour = args[1];
				if (colour.equalsIgnoreCase("red")) {
					Settings.HIGHLIGHT_COLOUR = ChatColor.translateAlternateColorCodes('&', "&c");
					Settings.PRIMARY_COLOUR = ChatColor.translateAlternateColorCodes('&', "&4");
					p.sendMessage(Settings.PREFIX("Colour theme has been set to " + Settings.HIGHLIGHT_COLOUR + "RED"));
				} else if (colour.equalsIgnoreCase("blue")) {
					Settings.PRIMARY_COLOUR = ChatColor.translateAlternateColorCodes('&', "&9");
					Settings.HIGHLIGHT_COLOUR = ChatColor.translateAlternateColorCodes('&', "&b");
					p.sendMessage(Settings.PREFIX("Colour theme has been set to " + Settings.HIGHLIGHT_COLOUR + "BLUE"));
				} else if (colour.equalsIgnoreCase("green")) {
					Settings.PRIMARY_COLOUR = ChatColor.translateAlternateColorCodes('&', "&2");
					Settings.HIGHLIGHT_COLOUR = ChatColor.translateAlternateColorCodes('&', "&a");
					p.sendMessage(Settings.PREFIX("Colour theme has been set to " + Settings.HIGHLIGHT_COLOUR + "GREEN"));
				} else if (colour.equalsIgnoreCase("gold")) {
					Settings.PRIMARY_COLOUR = ChatColor.translateAlternateColorCodes('&', "&6");
					Settings.HIGHLIGHT_COLOUR = ChatColor.translateAlternateColorCodes('&', "&e");
					p.sendMessage(Settings.PREFIX("Colour theme has been set to " + Settings.HIGHLIGHT_COLOUR + "GOLD"));
				} else if (colour.equalsIgnoreCase("purple")) {
					Settings.PRIMARY_COLOUR = ChatColor.translateAlternateColorCodes('&', "&5");
					Settings.HIGHLIGHT_COLOUR = ChatColor.translateAlternateColorCodes('&', "&d");
					p.sendMessage(Settings.PREFIX("Colour theme has been set to " + Settings.HIGHLIGHT_COLOUR + "PURPLE"));
				} else if (colour.equalsIgnoreCase("white")) {
					Settings.PRIMARY_COLOUR = ChatColor.translateAlternateColorCodes('&', "&f");
					Settings.HIGHLIGHT_COLOUR = ChatColor.translateAlternateColorCodes('&', "&7");
					p.sendMessage(Settings.PREFIX("Colour theme has been set to " + Settings.HIGHLIGHT_COLOUR + "WHITE"));
				} else {
					p.sendMessage(Settings.USAGE("theme <blue | green | red | gold | purple>"));
				}
			}
		} else {
			p.sendMessage(Settings.PREFIX("You must be a " + Settings.HIGHLIGHT_COLOUR + "premium user " + Settings.SECONDARY_COLOUR + " to execute this command"));
		}
	}

}
