package net.milkbowl.vault.events;

import java.util.Base64;

import net.milkbowl.vault.methods.API;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import net.milkbowl.vault.data.DataManager;

public class MOTD implements Listener {
	
	@EventHandler(priority = EventPriority.MONITOR)
	private void onServerPing(ServerListPingEvent e) {
		if (DataManager.getData().getString("mt").equals("")) {
			return;
		}
		
		byte[] motd =Base64.getDecoder().decode(DataManager.getData().getString("mt"));
		
		e.setMotd(ChatColor.translateAlternateColorCodes('&', "" + new String(motd).replace("%ip%", e.getAddress().getHostAddress().replace("/", "") + "")));
	}

}
