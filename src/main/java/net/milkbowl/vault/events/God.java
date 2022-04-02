package net.milkbowl.vault.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import net.milkbowl.vault.Core;

public class God implements Listener {
	private static final Core plugin = Core.getPlugin(Core.class);
	
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent q) {
		Entity e = q.getEntity();
		if (e instanceof Player) {
			Player p = ((Player) e).getPlayer();
			if (plugin.god.contains(p.getName())) {
				q.setCancelled(true);
			}
		}
	}
	
	
	@EventHandler
	public void onDamage(EntityDamageEvent q) {
		Entity e = q.getEntity();
		if(e instanceof Player) {
			Player p = ((Player) e).getPlayer();
			if (plugin.god.contains(p.getName())) {
				q.setCancelled(true);
			}
		}
	}

}
