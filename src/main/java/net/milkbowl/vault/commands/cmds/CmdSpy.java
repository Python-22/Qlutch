package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class CmdSpy extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			if (plugin.cmdspy.contains(p.getName())) {
				plugin.cmdspy.remove(p.getName());
				p.sendMessage(Settings.PREFIX("Command spy is now off!"));
			} else {
				plugin.cmdspy.add(p.getName());
				p.sendMessage(Settings.PREFIX("Command spy is now on!"));
			}
		} else {
			Player target = Bukkit.getServer().getPlayer(args[1]);
			if (target == null) {
				p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
			} else {
				
				if (plugin.cmdspyPlayer.containsKey(p)) {
					plugin.cmdspyPlayer.remove(p);
					p.sendMessage(Settings.PREFIX("You are no longer spying on " + Settings.HIGHLIGHT_COLOUR + target.getName() + "'s " + Settings.SECONDARY_COLOUR + "commands"));
				} else {
					plugin.cmdspyPlayer.put(p, target);
					p.sendMessage(Settings.PREFIX("You are now spying on " + Settings.HIGHLIGHT_COLOUR + target.getName() + "'s " + Settings.SECONDARY_COLOUR + "commands"));
				}    
			}
		}
	}

}
