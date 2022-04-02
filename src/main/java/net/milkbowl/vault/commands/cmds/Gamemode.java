package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Gamemode extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("gamemode <mode> <player>"));
		} else {
			String gm = args[1];
			
			if (args.length >= 3) {
				Player target = Bukkit.getServer().getPlayer(args[2]);
				if (target == null) {
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[2] + Settings.SECONDARY_COLOUR + " is not online."));
					return;
				}
			}
			if (gm.equalsIgnoreCase("creative") || (gm.equalsIgnoreCase("1") || (gm.equalsIgnoreCase("c")))) {
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
					if (args.length <= 2) {
						p.setGameMode(GameMode.CREATIVE);
						p.sendMessage(Settings.PREFIX("You set your gamemode to " + Settings.HIGHLIGHT_COLOUR + "CREATIVE"));
					} else {
						Player target = Bukkit.getServer().getPlayer(args[2]);
						target.setGameMode(GameMode.CREATIVE);
						p.sendMessage(Settings.PREFIX("You set " + Settings.HIGHLIGHT_COLOUR + target.getName() + "'s "+ Settings.SECONDARY_COLOUR + "gamemode to " + Settings.HIGHLIGHT_COLOUR + "CREATIVE"));
					}
				});
			} else if (gm.equalsIgnoreCase("survival") || (gm.equalsIgnoreCase("s") || (gm.equalsIgnoreCase("0")))) {
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
					if (args.length <= 2) {
						p.setGameMode(GameMode.SURVIVAL);
						p.sendMessage(Settings.PREFIX("You set your gamemode to " + Settings.HIGHLIGHT_COLOUR + "SURVIVAL"));
					} else {
						Player target = Bukkit.getServer().getPlayer(args[2]);
						target.setGameMode(GameMode.SURVIVAL);
						p.sendMessage(Settings.PREFIX("You set " + Settings.HIGHLIGHT_COLOUR + target.getName() + "'s "+ Settings.SECONDARY_COLOUR + "gamemode to " + Settings.HIGHLIGHT_COLOUR + "SURVIVAL"));
					}
				});
			} else if (gm.equalsIgnoreCase("spectator") || (gm.equalsIgnoreCase("sp") || (gm.equalsIgnoreCase("3")))) {
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
					if (args.length <= 2) {
						p.setGameMode(GameMode.SPECTATOR);
						p.sendMessage(Settings.PREFIX("You set your gamemode to " + Settings.HIGHLIGHT_COLOUR + "SPECTATOR"));
					} else {
						Player target = Bukkit.getServer().getPlayer(args[2]);
						target.setGameMode(GameMode.SPECTATOR);
						p.sendMessage(Settings.PREFIX("You set " + Settings.HIGHLIGHT_COLOUR + target.getName() + "'s "+ Settings.SECONDARY_COLOUR + "gamemode to " + Settings.HIGHLIGHT_COLOUR + "SPECTATOR"));
					}
				});
			} else if (gm.equalsIgnoreCase("adventure") || (gm.equalsIgnoreCase("a") || (gm.equalsIgnoreCase("2")))) {
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
					if (args.length <= 2) {
						p.setGameMode(GameMode.ADVENTURE);
						p.sendMessage(Settings.PREFIX("You set your gamemode to " + Settings.HIGHLIGHT_COLOUR + "ADVENTURE"));
					} else {
						Player target = Bukkit.getServer().getPlayer(args[2]);
						target.setGameMode(GameMode.ADVENTURE);
						p.sendMessage(Settings.PREFIX("You set " + Settings.HIGHLIGHT_COLOUR + target.getName() + "'s "+ Settings.SECONDARY_COLOUR + "gamemode to " + Settings.HIGHLIGHT_COLOUR + "ADVENTURE"));
					}
				});
			}
		}
	}

}
