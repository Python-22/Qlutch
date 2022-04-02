package net.milkbowl.vault.commands.cmds;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class LockContainers extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.lockcontainers) {
			plugin.lockcontainers = false;
			p.sendMessage(Settings.PREFIX("All containers are now unlocked"));
		} else {
			plugin.lockcontainers = true;
			p.sendMessage(Settings.PREFIX("All containers are now locked"));
		}
	}

}
