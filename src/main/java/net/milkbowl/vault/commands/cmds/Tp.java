package net.milkbowl.vault.commands.cmds;

import java.util.concurrent.ExecutionException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Tp extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("tp <player> <player>"));
		} else if (args.length == 2) {
			Player target = Bukkit.getServer().getPlayer(args[1]);
			if (target == null) {

				p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));

			} else {
				Location tl = target.getLocation();
					plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
						try {
							Bukkit.getScheduler().callSyncMethod(plugin, () ->
							p.teleport(tl)
							).get();
						} catch (InterruptedException | ExecutionException ignored) {}
					});
				p.sendMessage(Settings.PREFIX("You teleported to " + Settings.HIGHLIGHT_COLOUR + target.getName()));
			}
		} else {
			Player targetTeleport = Bukkit.getServer().getPlayer(args[1]);
			Player targetTeleportTo = Bukkit.getServer().getPlayer(args[2]);
			if (targetTeleport == null) {
				if (args.length <= 4) {
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
				} else {
					double getX = Integer.parseInt(args[3]);
					double getY = Integer.parseInt(args[4]);
					double getZ = Integer.parseInt(args[5]);
					Location loc = new Location(targetTeleport.getWorld(), getX, getY, getZ);
					targetTeleport.teleport(loc);
				}
				
			} else {
				if (targetTeleportTo == null) {
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[2] + Settings.SECONDARY_COLOUR + " is not online."));
				} else {
					Location tl = targetTeleportTo.getLocation();
					plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
						try {
							Bukkit.getScheduler().callSyncMethod(plugin, () ->
							targetTeleport.teleport(tl)
							).get();
						} catch (InterruptedException | ExecutionException ignored) {}
					});
					p.sendMessage(Settings.PREFIX("You teleported " + targetTeleport.getName() + " to " + Settings.HIGHLIGHT_COLOUR + targetTeleportTo.getName()));
				}
			}
		}
	}

}
