package net.milkbowl.vault.util;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigOperator {

	public static FileConfiguration getConfigFromPlugin(String plugin) {
		if(PluginUtils.getPluginByName(plugin) != null) {
			return PluginUtils.getPluginByName(plugin).getConfig();
		}else {
			return null;
		}
	}

	public static FileConfiguration getConfigFromFile(File file) {
		return YamlConfiguration.loadConfiguration(file);
	}

	public static void reloadConfiguration(File file) {
		YamlConfiguration.loadConfiguration(file);
	}

	public static void saveConfiguration(File file) throws IOException {
		YamlConfiguration.loadConfiguration(file).save(file);
	}
}