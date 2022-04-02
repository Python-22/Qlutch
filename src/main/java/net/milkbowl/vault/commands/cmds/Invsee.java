package net.milkbowl.vault.commands.cmds;

import java.util.concurrent.ExecutionException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Invsee extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("invsee <player>"));
		} else {
			Player target = Bukkit.getServer().getPlayer(args[1]);
			if (target == null) {
				p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
			} else {
				plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
					try {
						Bukkit.getScheduler().callSyncMethod(plugin, () ->
						p.openInventory(target.getInventory())
						).get();
					} catch (InterruptedException | ExecutionException ignored) {}
				});
				p.sendMessage(Settings.PREFIX("You are now viewing " + Settings.HIGHLIGHT_COLOUR + target.getName() + "'s" + Settings.SECONDARY_COLOUR + " inventory."));
			}
		}
	}

}
