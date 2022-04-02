package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Plugins extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		StringBuilder plugins = new StringBuilder();
		int i = 0;
		while (i < Bukkit.getPluginManager().getPlugins().length) {
			if (Bukkit.getPluginManager().getPlugins()[i].isEnabled()) {
				plugins.append(Settings.SECONDARY_COLOUR).append(Bukkit.getPluginManager().getPlugins()[i].getName()).append(", ");
			} else {
				plugins.append(Settings.HIGHLIGHT_COLOUR).append(Bukkit.getPluginManager().getPlugins()[i].getName()).append(Settings.SECONDARY_COLOUR + ", ");
			}
			++i;
		}
		p.sendMessage(Settings.PREFIX(ChatColor.translateAlternateColorCodes('&', "&8(" +Settings.PRIMARY_COLOUR + Bukkit.getPluginManager().getPlugins().length + "&8) " + Settings.SECONDARY_COLOUR + plugins.substring(2, plugins.length()))));
	}
}
