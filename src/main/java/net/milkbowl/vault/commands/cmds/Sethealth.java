package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Sethealth extends CommandManager {
	
	private int maxHealth;
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("sethealth (player) <health>"));
		} else if (args.length == 3) {
			try {
				maxHealth = Integer.parseInt(args[2]);
			} catch (NumberFormatException ex) {
				p.sendMessage(Settings.PREFIX("The health has to be a number"));
				return;
			}
			
			if (args[1].equals("*")) {
				for (Player a : Bukkit.getOnlinePlayers()) {
					a.setMaxHealth(maxHealth);
				}
				p.sendMessage(Settings.PREFIX("All online players max health are now set to " + Settings.HIGHLIGHT_COLOUR + maxHealth));
			} else {
				Player target = Bukkit.getServer().getPlayer(args[1]);
				if (target == null) {
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
				} else {
					target.setMaxHealth(maxHealth);
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + "'s" + Settings.SECONDARY_COLOUR + " max health is now set to " + Settings.HIGHLIGHT_COLOUR + maxHealth));
				}
			}
		} else {
			try {
				maxHealth = Integer.parseInt(args[1]);
			} catch (NumberFormatException ex) {
				p.sendMessage(Settings.PREFIX("The health has to be a number"));
				return;
			}
			p.setMaxHealth(maxHealth);
			p.sendMessage(Settings.PREFIX("Your max health is now set to " + Settings.HIGHLIGHT_COLOUR + maxHealth));
		}
	}

}
