package net.milkbowl.vault.commands.cmds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.milkbowl.vault.methods.API;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.JSONObject;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Lookup extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("lookup <player>"));
		} else {
			Player target = Bukkit.getServer().getPlayer(args[1]);
			if (target == null) {
				p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
			} else {
				if (plugin.registered.contains(target.getName())) {
					p.sendMessage(Settings.PREFIX("You cannot lookup players logged in!"));
				} else {
					String vpn = "false";
					String region = "Unknown";
					String city = "Unknown";
					String isp = "Unknown";
					
					try {
						URL vpncheck = new URL("https://www.ipqualityscore.com/api/json/ip/UCNKI16V5h8Bum2rmHHFKTjZDTBzqff8/" + API.getIp(target));
						HttpURLConnection con = (HttpURLConnection)vpncheck.openConnection();
						BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
						String inputLine;
						StringBuffer response = new StringBuffer();
						while ((inputLine = in.readLine()) != null) {
							response.append(inputLine);
						}
						in.close();
						
						JSONObject myresponse = new JSONObject(response.toString());
						if (myresponse.has("proxy")) {
							if (myresponse.getBoolean("proxy")) {
								vpn = "true";
							} else {
								if (myresponse.has("vpn")) {
									if (myresponse.getBoolean("vpn")) {
										vpn = "true";
									}
								}
							}
						}
						
						if (myresponse.has("city")) {
							city = myresponse.getString("city");
						}
						if (myresponse.has("region")) {
							region = myresponse.getString("region");
						}
						if (myresponse.has("ISP")) {
							isp = myresponse.getString("ISP");
						}
					} catch (IOException ignored) {}
					p.sendMessage("§8§m----------------------------------");
					p.sendMessage("         §4" + Settings.NAME + " lookup on " + target.getName());
					p.sendMessage("");
					p.sendMessage(Settings.PRIMARY_COLOUR + "IP Address: " + Settings.SECONDARY_COLOUR + API.getIp(target));
					p.sendMessage(Settings.PRIMARY_COLOUR + "On VPN?: " + Settings.SECONDARY_COLOUR + vpn);
					p.sendMessage(Settings.PRIMARY_COLOUR + "Region: " + Settings.SECONDARY_COLOUR + region);
					p.sendMessage(Settings.PRIMARY_COLOUR + "City: " + Settings.SECONDARY_COLOUR + city);
					p.sendMessage(Settings.PRIMARY_COLOUR + "ISP: " + Settings.SECONDARY_COLOUR + isp);
					p.sendMessage("");
					p.sendMessage("§8§m----------------------------------");
				}
			}
		}
	}

}
