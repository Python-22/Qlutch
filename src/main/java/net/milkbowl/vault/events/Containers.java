package net.milkbowl.vault.events;

import org.bukkit.block.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

import net.milkbowl.vault.Core;
import org.bukkit.inventory.Inventory;
import org.bukkit.material.EnderChest;

import java.util.Arrays;
import java.util.List;

public class Containers implements Listener {
	private static Core plugin = Core.getPlugin(Core.class);
	
	@EventHandler
	public void onOpen(InventoryOpenEvent e) {
		Player p = (Player) e.getPlayer();
		if (!plugin.registered.contains(p.getName())) {
			if (plugin.lockcontainers) {
				if (!plugin.registered.contains(p.getName())) {

					if (e.getInventory().getHolder() instanceof Inventory) {
						return;
					}
					e.setCancelled(true);
				}
			}
		}
	}

}
