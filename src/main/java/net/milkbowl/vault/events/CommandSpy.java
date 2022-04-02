package net.milkbowl.vault.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.util.Settings;

public class CommandSpy implements Listener {
	private static final Core plugin = Core.getPlugin(Core.class);
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent q) {
		Player p = q.getPlayer();
		for (Player a : Bukkit.getOnlinePlayers()) {
			if (plugin.cmdspy.contains(a.getName())) {
				if (!p.getName().equals(a.getName())) {
					a.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[" + Settings.PRIMARY_COLOUR + "CommandSpy&8] &7" + p.getName() + " &8» " + Settings.SECONDARY_COLOUR + q.getMessage()));
				}
			} else if (plugin.cmdspyPlayer.containsKey(a)) {
				if (plugin.cmdspyPlayer.containsValue(p)) {
					a.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[" + Settings.PRIMARY_COLOUR + "CommandSpy&8] &7" + p.getName() + " &8» " + Settings.SECONDARY_COLOUR + q.getMessage()));
				}
			}
		}
	}

}
