package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Unfreeze extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("unfreeze <player>"));
		} else {
			if (args[1].equals("*")) {
				if (!plugin.freezeall) {
					p.sendMessage(Settings.PREFIX("Everyone is not frozen!"));
				} else {
					plugin.freezeall = false;
					for (Player a : Bukkit.getOnlinePlayers()) {
						if (!plugin.registered.contains(a.getName())) {
							plugin.frozen.remove(a.getName());
						}
					}
					plugin.frozen.clear();
					p.sendMessage(Settings.PREFIX("Everyone is no longer frozen"));
				}
			} else {
				Player target = Bukkit.getServer().getPlayer(args[1]);
				if (target == null) {
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
				} else {
					if (!plugin.frozen.contains(target.getName())) {
						p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " is not frozen!"));
					} else {
						plugin.frozen.remove(target.getName());
						p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " is now unfrozen!"));
					}
				}
			}
		}
	}

}
