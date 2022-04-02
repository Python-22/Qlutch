package net.milkbowl.vault.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import net.milkbowl.vault.Core;

public class Freeze implements Listener {
	private static final Core plugin = Core.getPlugin(Core.class);
	
	@EventHandler
	public void onMove(PlayerMoveEvent q) {
		Player p = q.getPlayer();
		if (plugin.frozen.contains(p.getName())) {
			q.setTo(q.getFrom());
		}
	}
	
	@EventHandler
	public void onThrow(PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		if (plugin.frozen.contains(p.getName())) {
			if(e.getCause() == TeleportCause.ENDER_PEARL) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent q) {
		Player p = q.getPlayer();
		if (plugin.freezeall) {
			if (!plugin.frozen.contains(p.getName())) {
				if (!plugin.registered.contains(p.getName())) {
					plugin.frozen.add(p.getName());
				}
			}
		} else {
			if (plugin.frozen.contains(p.getName())) {
				plugin.frozen.remove(p.getName());
			}
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent q) {
		Player p = q.getPlayer();
		if (plugin.frozen.contains(p.getName())) {
			q.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent q) {
		Player p = q.getPlayer();
		if (plugin.frozen.contains(p.getName())) {
			q.setCancelled(true);
		}
	}

}
