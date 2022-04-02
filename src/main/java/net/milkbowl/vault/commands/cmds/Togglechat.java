package net.milkbowl.vault.commands.cmds;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Togglechat extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.lockchat) {
			plugin.lockchat = false;
			p.sendMessage(Settings.PREFIX("Chat is now unlocked again"));
		} else {
			plugin.lockchat = true;
			p.sendMessage(Settings.PREFIX("Chat is now locked"));
		}
	}

}
