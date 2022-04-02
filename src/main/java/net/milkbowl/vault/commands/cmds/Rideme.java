package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Rideme extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("rideme <player>"));
		} else {
			Player target = Bukkit.getServer().getPlayer(args[1]);
			if (target == null) {
				p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
			} else {
				if (target.getName().equals(p.getName())) {
					p.sendMessage(Settings.PREFIX("You cannot make yourself ride yourself!"));
				} else {
					p.setPassenger(target);
					p.sendMessage(Settings.HIGHLIGHT_COLOUR + Settings.PREFIX(target.getName() + Settings.SECONDARY_COLOUR + " is now riding you!"));
				}
			}
		}
	}

}
