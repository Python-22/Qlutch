package net.milkbowl.vault.commands.cmds.premium;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class SetPrefix extends CommandManager {
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredPremium.contains(p.getName())) {
			if (args.length <= 1) {
				p.sendMessage(Settings.USAGE("setprefix <prefix>"));
			} else {
				plugin.prefix.put(p, args[1]);
				p.sendMessage(Settings.PREFIX("Your new server chat prefix is " + Settings.HIGHLIGHT_COLOUR + args[1]));
			}
		} else {
			p.sendMessage(Settings.PREFIX("You must be an " + Settings.HIGHLIGHT_COLOUR + "premium " + Settings.SECONDARY_COLOUR + "to execute this command"));
		}
	}


}
