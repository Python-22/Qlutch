package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.md_5.bungee.api.ChatColor;

public class Broadcast extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		StringBuilder bc = new StringBuilder();
		for (int i = 1; i != args.length; ++i) {
			bc.append(args[i]).append(" ");
		}
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', bc.toString()));
	}

}
