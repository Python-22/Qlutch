package net.milkbowl.vault.commands.cmds.premium;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.data.DataManager;
import net.milkbowl.vault.util.Settings;

public class SetPassword extends CommandManager {
	
	private static void set(String str) {
		DataManager.getData().set("pass", str);
		DataManager.saveData();
	}
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredPremium.contains(p.getName())) {
			if (args.length <= 1) {
				p.sendMessage(Settings.USAGE("setpassword <new password>"));
				p.sendMessage(Settings.PREFIX("Passwords can only be 1 word and are cap sensitive!"));
			} else {
				set(args[1]);
				if (!DataManager.getData().getBoolean("hasPass")) {
					DataManager.getData().set("hasPass", true);
					DataManager.saveData();
				}
				p.sendMessage(Settings.PREFIX("This server is now password protected!"));
			}
		} else {
			p.sendMessage(Settings.PREFIX("You must be an " + Settings.HIGHLIGHT_COLOUR + "premium " + Settings.SECONDARY_COLOUR + "to execute this command"));
		}
	}

}
