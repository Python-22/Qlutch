package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class NPU extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("npu <player>"));
		} else {
			
			Player target = Bukkit.getServer().getPlayer(args[1]);
			if (target == null) {
				p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
			} else {
				if (plugin.npu.contains(target.getName())) {
					plugin.npu.remove(target.getName());
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " can now pick up items"));    
				} else {
					plugin.npu.add(target.getName());
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " can no longer pick up items"));    
				}
			}
		}
	}

}
