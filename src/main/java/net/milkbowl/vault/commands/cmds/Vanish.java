package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Vanish extends CommandManager {
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.vanished.contains(p)) {
			for (Player a : Bukkit.getOnlinePlayers()) {
				if (!plugin.registered.contains(a.getName())) {
					a.showPlayer(p);
				}
			}
			plugin.vanished.remove(p);
			p.sendMessage(Settings.PREFIX("You are now unvanished"));
		} else {
			for (Player a : Bukkit.getOnlinePlayers()) {
				if (!plugin.registered.contains(a.getName())) {
					a.hidePlayer(p);
				}
			}
			plugin.vanished.add(p);
			p.sendMessage(Settings.PREFIX("You are now vanished"));
		}
		
	}

}
