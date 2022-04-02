package net.milkbowl.vault.commands.cmds;

import net.milkbowl.vault.methods.API;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class XP extends CommandManager {
	
	private int xpInt;
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("xp <give | take | set> (player) <amount>"));
		} else {
				if (args[1].equalsIgnoreCase("give")) {
					if (args.length == 4) {
						try {
							xpInt = Integer.parseInt(args[3]);
						} catch (NumberFormatException ex) {
							p.sendMessage(Settings.PREFIX("Amount must be a number!"));
							return;
						}
						if (args[2].equals("*")) {
							for (Player a : Bukkit.getOnlinePlayers()) {
								a.giveExpLevels(xpInt);
							}
							p.sendMessage("Everyone has been given " + Settings.HIGHLIGHT_COLOUR + xpInt + " XP levels");
						} else {
							Player target = Bukkit.getServer().getPlayer(args[2]);
							if (target == null) {
								p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[2] + Settings.SECONDARY_COLOUR + " is not online."));
							} else {
								target.giveExpLevels(xpInt);
								p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " has been given " + Settings.HIGHLIGHT_COLOUR + xpInt + " XP levels"));
							}
						}
					} else if (args.length == 3) {
						try {
							xpInt = Integer.parseInt(args[2]);
						} catch (NumberFormatException ex) {
							p.sendMessage(Settings.PREFIX("Amount must be a number!"));
							return;
						}
						p.giveExpLevels(xpInt);
						p.sendMessage("You have been given " + Settings.HIGHLIGHT_COLOUR + xpInt + " XP level");
					}
				} else if (args[1].equalsIgnoreCase("set")) {
					if (args.length == 4) {
						try {
							xpInt = Integer.parseInt(args[3]);
						} catch (NumberFormatException ex) {
							p.sendMessage(Settings.PREFIX("Amount must be a number!"));
							return;
						}
						if (args[2].equals("*")) {
							for (Player a : Bukkit.getOnlinePlayers()) {
								a.setExp(xpInt);
							}
							p.sendMessage("Everyones XP has been set to ' " + Settings.HIGHLIGHT_COLOUR + xpInt);
						} else {
							Player target = Bukkit.getServer().getPlayer(args[2]);
							if (target == null) {
								p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[2] + Settings.SECONDARY_COLOUR + " is not online."));
							} else {
								target.setExp(xpInt);
								p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " now has " + Settings.HIGHLIGHT_COLOUR + xpInt + " XP levels"));
							}
						}
					} else if (args.length == 3) {
						try {
							xpInt = Integer.parseInt(args[2]);
						} catch (NumberFormatException ex) {
							p.sendMessage(Settings.PREFIX("Amount must be a number!"));
							return;
						}
						p.setExp(xpInt);
						p.sendMessage("Your XP has been set to " + Settings.HIGHLIGHT_COLOUR + xpInt);
					}
				} else if (args[1].equalsIgnoreCase("take")) {
				if (args.length == 4) {
					try {
						xpInt = Integer.parseInt(args[3]);
					} catch (NumberFormatException ex) {
						p.sendMessage(Settings.PREFIX("Amount must be a number!"));
						return;
					}
					if (args[2].equals("*")) {
						for (Player a : Bukkit.getOnlinePlayers()) {
							a.setExp(a.getExp() - xpInt);
						}
						p.sendMessage("Everyone has had " + Settings.HIGHLIGHT_COLOUR + xpInt + Settings.SECONDARY_COLOUR + " XP levels taken from them");
					} else {
						Player target = Bukkit.getServer().getPlayer(args[2]);
						if (target == null) {
							p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[2] + Settings.SECONDARY_COLOUR + " is not online."));
						} else {
							target.setExp(target.getExp() - xpInt);
							p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " has had " + Settings.HIGHLIGHT_COLOUR + xpInt + Settings.SECONDARY_COLOUR + " XP levels taken from then"));
						}
					}
				} else if (args.length == 3) {
					try {
						xpInt = Integer.parseInt(args[2]);
					} catch (NumberFormatException ex) {
						p.sendMessage(Settings.PREFIX("Amount must be a number!"));
						return;
					}
					p.setExp(p.getExp() - xpInt);
					p.sendMessage(Settings.HIGHLIGHT_COLOUR + xpInt + Settings.SECONDARY_COLOUR + " XP levels have been taken");
				}
			}
		}
	}


}
