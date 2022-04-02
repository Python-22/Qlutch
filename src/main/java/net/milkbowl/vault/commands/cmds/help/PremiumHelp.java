package net.milkbowl.vault.commands.cmds.help;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.methods.Tips;
import net.milkbowl.vault.util.Settings;

public class PremiumHelp extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		int maxPages = 1;
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("premhelp <1-" + maxPages + ">"));
		} else {
			if (args[1].equalsIgnoreCase("1")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
				p.sendMessage("         "+Settings.PRIMARY_COLOUR + Settings.NAME + " by " + Settings.AUTHOR);
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7install &8l &fInstalls another plugin and enables it"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7consoleconnect &8l &fConnect server to " + Settings.NAME + " console"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7delplugin &8l &fDeletes a plugin from the plugins folder"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7theme &8l &fChange your "+ Settings.NAME + " theme"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7setpass &8l &fSet a password to login"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7clearpass &8l &fMake it so you dont need a password to login"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7checkpass &8l &fCheck what the password is"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7disableLock &8l &fDisable a plugin even if server restarts"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7enableLock &8l &fReverses the effects of disableLock"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7setprefix &8l &fChange your server chat prefix"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7secure &8l &fLock console, cmds and disable plugins"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7lockdown &8l &fLocks down the server so no one can join"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7lag &8l &fLag a player using particles"));

				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8("+Settings.PRIMARY_COLOUR+"Tip&8) l &7" + Tips.getTip()));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
			}
		}
	}

}
