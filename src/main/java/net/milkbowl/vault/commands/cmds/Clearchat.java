package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Clearchat extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		for (int x = 0; x <= 20; x++) {
			Bukkit.broadcastMessage("");
		}
		p.sendMessage(Settings.PREFIX("Chat has been cleared for everyone"));
	}

}
