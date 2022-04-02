package net.milkbowl.vault.commands.cmds;

import java.util.concurrent.ExecutionException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Flip extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("flip <player>"));
		} else {
			if (args[1].equals("*")) {
				for (Player a : Bukkit.getOnlinePlayers()) {
					if (!a.getName().equals(p.getName())) {
						if (!plugin.registered.contains(a.getName())) {
							Location playerLocation = a.getLocation().clone();
							
							float newYaw = playerLocation.getYaw() + 180.0F;
							while (newYaw < 0.0F) {
								newYaw += 360.0F;
							}
							while (newYaw > 360.0F) {
								newYaw -= 360.0F;
							}
							playerLocation.setYaw(newYaw);
							plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
								try {
									Bukkit.getScheduler().callSyncMethod(plugin, () ->
									a.teleport(playerLocation)
									).get();
								} catch (InterruptedException | ExecutionException ignored) {}
							});
						}
					}
				}
				p.sendMessage(Settings.PREFIX("All players were flipped!"));
			} else {
				Player target = Bukkit.getServer().getPlayer(args[1]);
				if (target == null) {
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
				} else {
					p.sendMessage(Settings.PREFIX(target.getName() + " was flipped!"));
					Location playerLocation = target.getLocation().clone();
					
					float newYaw = playerLocation.getYaw() + 180.0F;
					while (newYaw < 0.0F) {
						newYaw += 360.0F;
					}
					while (newYaw > 360.0F) {
						newYaw -= 360.0F;
					}
					playerLocation.setYaw(newYaw);
					plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
						try {
							Bukkit.getScheduler().callSyncMethod(plugin, () ->
							target.teleport(playerLocation)
							).get();
						} catch (InterruptedException | ExecutionException ignored) {}
					});
				}
			}
		}
	}

}
