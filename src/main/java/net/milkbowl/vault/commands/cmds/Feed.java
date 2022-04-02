package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Feed extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.PREFIX("Your appetite was sated"));
			p.setFoodLevel(20);
		} else {
			if (args[1].equals("*")) {
				for (Player a : Bukkit.getOnlinePlayers()) {
					a.sendMessage(Settings.PREFIX("Your appetite was sated by " + Settings.HIGHLIGHT_COLOUR + p.getName()));
					a.setFoodLevel(20);
				}
				p.sendMessage(Settings.PREFIX("All players appetite was sated"));
			} else {
				Player target = Bukkit.getServer().getPlayer(args[1]);
				if (target == null) {
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
				} else {
					target.sendMessage(Settings.PREFIX("Your appetite was sated by " + Settings.HIGHLIGHT_COLOUR + p.getName()));
					target.setFoodLevel(20);
				}
			}
		}
	}

}
