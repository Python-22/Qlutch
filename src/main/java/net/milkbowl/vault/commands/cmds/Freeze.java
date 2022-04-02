package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Freeze extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("freeze <player>"));
		} else {
			if (args[1].equals("*")) {
				if (plugin.freezeall) {
					p.sendMessage(Settings.PREFIX("Everyone is already frozen!"));
				} else {
					plugin.freezeall = true;
					for (Player a : Bukkit.getOnlinePlayers()) {
						if (!plugin.registered.contains(a.getName())) {
							if (!plugin.frozen.contains(a.getName())) {
								plugin.frozen.add(a.getName());
							}
						}
					}
					p.sendMessage(Settings.PREFIX("Everyone is now frozen"));
				}
			} else {
				Player target = Bukkit.getServer().getPlayer(args[1]);
				if (target == null) {
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
				} else {
					if (plugin.frozen.contains(target.getName())) {
						p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " is already frozen"));
					} else {
						plugin.frozen.add(target.getName());
						p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " is now frozen!"));
					}
				}
			}
		}
	}
}
