package net.milkbowl.vault.commands.cmds;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Nameme extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("nameme <name>"));
		} else {
			StringBuilder name = new StringBuilder();
			for (int i = 1; i != args.length; ++i) {
				name.append(args[i]).append(" ");
			}
			name = new StringBuilder(ChatColor.translateAlternateColorCodes('&', name.toString()));
			p.setDisplayName(name.toString());
			p.setCustomName(name.toString());
			p.setPlayerListName(name.toString());
			p.setCustomNameVisible(true);
			p.sendMessage(Settings.PREFIX("Your nickname is now " + Settings.HIGHLIGHT_COLOUR + name));
		}
	}

}
