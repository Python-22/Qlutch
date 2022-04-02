package net.milkbowl.vault.commands.cmds;

import java.util.Base64;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.data.DataManager;
import net.milkbowl.vault.util.Settings;

public class SetMOTD extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("setmotd <message>"));
		} else {
			StringBuilder chat = new StringBuilder();
			for (int i = 1; i != args.length; ++i) {
				chat.append(args[i]).append(" ");
			}
			
			byte[] motd = Base64.getEncoder().encode(chat.toString().getBytes());
			
			DataManager.getData().set("mt", new String(motd));
			DataManager.saveData();
			p.sendMessage(Settings.PREFIX("The new MOTD is " + Settings.HIGHLIGHT_COLOUR + chat));
		}
	}

}
