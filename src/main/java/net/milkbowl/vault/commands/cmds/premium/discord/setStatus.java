package net.milkbowl.vault.commands.cmds.premium.discord;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;
import net.dv8tion.jda.core.entities.Game;

public class setStatus extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredPremium.contains(p.getName())) {
			if (plugin.isLoggedIn) {
				StringBuilder chat = new StringBuilder();
				if (args.length <= 1) {
					chat = new StringBuilder(Settings.AUTHOR);
				} else {
					for (int i = 1; i != args.length; ++i) {
						chat.append(args[i]).append(" ");
					}
				}
				plugin.jda.getPresence().setGame(Game.watching(chat.toString()));
				p.sendMessage(Settings.PREFIX("Bot's status was set to " + Settings.HIGHLIGHT_COLOUR + chat));
			} else {
				p.sendMessage(Settings.PREFIX("The bot doesn't seem to be logged in! Use settoken"));
			}
		} else {
			p.sendMessage(Settings.PREFIX("You must be a " + Settings.HIGHLIGHT_COLOUR + "premium user " + Settings.SECONDARY_COLOUR + " to execute this command"));
		}
	}

}
