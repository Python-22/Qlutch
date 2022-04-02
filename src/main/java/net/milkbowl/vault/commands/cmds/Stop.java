package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.methods.API;
import net.milkbowl.vault.util.Settings;

public class Stop extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		p.sendMessage(Settings.PREFIX("Stopping the server..."));
		API.saveLists();
		Bukkit.getServer().savePlayers();
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "save-all");
		Bukkit.getServer().shutdown();
	}

}
