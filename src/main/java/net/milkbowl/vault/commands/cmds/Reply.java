package net.milkbowl.vault.commands.cmds;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Reply extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length < 2) {
			p.sendMessage(Settings.USAGE("reply <message>"));
		} else {
			StringBuilder message = new StringBuilder();
			for (int i = 1; i != args.length; ++i) {
				message.append(args[i]).append(" ");
			}
			if (plugin.reply.get(p) == null) {
				p.sendMessage(Settings.USAGE("You have no one to reply too!"));
			} else {
				Player target = plugin.reply.get(p);
				p.sendMessage("§8["+Settings.SECONDARY_COLOUR+"You to "+Settings.PRIMARY_COLOUR + target.getName() + "§8] §7" + message);
				target.sendMessage("§8["+Settings.PRIMARY_COLOUR + p.getName() + Settings.SECONDARY_COLOUR+" to you§8] §7" + message);
			}
		}
	}
}
