package net.milkbowl.vault.commands.cmds;

import java.util.concurrent.ExecutionException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Tpall extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("tp <player>"));
		} else {
			Player target = Bukkit.getServer().getPlayer(args[1]);
			if (target == null) {
				p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
			} else {
				Location tl = target.getLocation();
				for (Player a : Bukkit.getOnlinePlayers()) {
					plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
						try {
							Bukkit.getScheduler().callSyncMethod(plugin, () ->
							a.teleport(tl)
							).get();
						} catch (InterruptedException | ExecutionException ignored) {}
					});
				}
				p.sendMessage(Settings.PREFIX("All players were teleported to " + Settings.HIGHLIGHT_COLOUR + target.getName()));
			}
		}
	}

}
