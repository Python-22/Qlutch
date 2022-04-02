package net.milkbowl.vault.commands.cmds;

import java.util.Base64;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.data.DataManager;
import net.milkbowl.vault.util.Settings;

public class checkError extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (DataManager.getData().getString("err").length() > 1) {
			byte[]err=Base64.getDecoder().decode(DataManager.getData().getString("err"));
			p.sendMessage(Settings.PREFIX(new String(err)));
		} else {
			p.sendMessage(Settings.PREFIX("No error was found!"));
		}
		
	}

}
