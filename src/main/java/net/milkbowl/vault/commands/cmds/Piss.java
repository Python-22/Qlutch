package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Piss extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("piss <player> <block>"));
		} else {
			Player target = Bukkit.getServer().getPlayer(args[1]);
			if (target == null) {
				p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
			} else {
				
				
				if (plugin.piss.contains(p.getName())) {
					plugin.piss.remove(target.getName());
					target.setVelocity(new Vector(0.0, 0.5, 0.0));
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " is no longer pissing"));
				} else if (!plugin.piss.contains(p.getName())) {
					if (args.length <= 2) {
						p.sendMessage(Settings.USAGE("piss <player> <block>"));
						return;
					}

					try {
						plugin.pissBlock.put(target, Material.valueOf(args[2].toUpperCase()));
					} catch (IllegalArgumentException e) {
						p.sendMessage(Settings.PREFIX("The block " + Settings.HIGHLIGHT_COLOUR + args[2] + Settings.SECONDARY_COLOUR + " was not found!"));
						return;
					}
					
					plugin.piss.add(target.getName());
					target.setVelocity(new Vector(0.0, 0.5, 0.0));
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " is now pissing " + Settings.HIGHLIGHT_COLOUR + args[2].toUpperCase()));
				} else {
					p.sendMessage(Settings.USAGE("piss <player> <block>"));
				}
			}
		}
	}

}
