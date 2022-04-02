package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Msg extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length < 3) {
			p.sendMessage(Settings.USAGE("msg <player> <message>"));
		} else {
			Player target = Bukkit.getPlayerExact(args[1]);
			if(target == null) {
				p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online"));
			} else {
				StringBuilder message = new StringBuilder();
				for (int i = 2; i != args.length; ++i) {
					message.append(args[i]).append(" ");
				}
				plugin.reply.put(target, p);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8["+Settings.SECONDARY_COLOUR+"You to "+Settings.PRIMARY_COLOUR + Bukkit.getPlayer(args[1]).getName() + "&8] &7" + message));
				target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8["+Settings.PRIMARY_COLOUR + p.getName() + Settings.SECONDARY_COLOUR+" to you&8] &7" + message));
			}
			
		}
	}
}
