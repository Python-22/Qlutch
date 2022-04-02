package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Coords extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.PREFIX("Your coords are: " + Settings.HIGHLIGHT_COLOUR + p.getLocation().getX() + ", " + p.getLocation().getY() + ", " + p.getLocation().getZ()));
		} else {
			Player target = Bukkit.getServer().getPlayer(args[1]);
			if (target == null) {
				p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
			} else {
				p.sendMessage(Settings.PREFIX(target.getName() + "'s coords are: " + Settings.HIGHLIGHT_COLOUR + target.getLocation().getX() + ", " + target.getLocation().getY() + ", " + target.getLocation().getZ()));
			}
		}
	}

}
