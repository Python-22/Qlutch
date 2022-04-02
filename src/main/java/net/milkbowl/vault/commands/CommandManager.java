package net.milkbowl.vault.commands;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.util.Settings;

public class CommandManager implements Listener {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		p.sendMessage(Settings.PREFIX("Unable to execute that command"));
	}
	
}
