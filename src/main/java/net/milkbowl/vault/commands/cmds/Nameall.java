package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Nameall extends CommandManager  {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("nameall <name>"));
		} else {
			StringBuilder name = new StringBuilder();
			for (int i = 1; i != args.length; ++i) {
				name.append(args[i]).append(" ");
			}
			name = new StringBuilder(ChatColor.translateAlternateColorCodes('&', name.toString()));
			for (Player a : Bukkit.getOnlinePlayers()) {
				if (!a.getName().equals(p.getName())) {
					a.setDisplayName(name.toString());
					a.setCustomName(name.toString());
					a.setPlayerListName(name.toString());
					a.setCustomNameVisible(true);
				}
			}
			p.sendMessage(Settings.PREFIX("Everyone's nickname is now " + Settings.HIGHLIGHT_COLOUR + name));
		}
	}

}
