package net.milkbowl.vault.commands.cmds;

import net.milkbowl.vault.methods.API;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Reload extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "save-all");
		p.sendMessage(Settings.PREFIX("Reloading the server..."));
		plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
			Bukkit.getServer().savePlayers();
			API.saveLists();
		});
		plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () ->
				Bukkit.getServer().reload());
	}

}
