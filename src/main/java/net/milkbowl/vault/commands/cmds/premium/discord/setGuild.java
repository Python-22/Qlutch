package net.milkbowl.vault.commands.cmds.premium.discord;


import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.data.DataManager;
import net.milkbowl.vault.util.Settings;

public class setGuild extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredPremium.contains(p.getName())) {
			if (args.length <= 1) {
				p.sendMessage(Settings.USAGE("setguild <discord guild ID>"));
			} else {
				DataManager.getData().set("id", args[1]);
				DataManager.saveData();
				p.sendMessage(Settings.PREFIX("The discord guild has now been set"));
			}
		} else {
			p.sendMessage(Settings.PREFIX("You must be a " + Settings.HIGHLIGHT_COLOUR + "premium user " + Settings.SECONDARY_COLOUR + " to execute this command"));
		}
	}


}
