package net.milkbowl.vault.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import net.milkbowl.vault.Core;

public class NoPickup implements Listener {
	private static final Core plugin = Core.getPlugin(Core.class);
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		Player p = e.getPlayer();
		if (plugin.npu.contains(p.getName())) {
			e.setCancelled(true);
		}
	}

}
