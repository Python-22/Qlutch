package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.methods.API;
import net.milkbowl.vault.util.Settings;

public class Save extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "save-all");
		plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
			Bukkit.getServer().savePlayers();
			API.saveLists();
		});
		
		p.sendMessage(Settings.PREFIX("Everything is now saved."));
	}

}
