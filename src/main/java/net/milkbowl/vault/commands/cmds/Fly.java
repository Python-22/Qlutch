package net.milkbowl.vault.commands.cmds;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Fly extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (!p.isFlying()) {
			p.sendMessage(Settings.PREFIX("Set fly to enabled"));
			p.setAllowFlight(true);
			p.setFlying(true);
		} else {
			p.sendMessage(Settings.PREFIX("Set fly to disabled"));
			p.setAllowFlight(false);
			p.setFlying(false);
		}
	}

}
