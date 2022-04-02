package net.milkbowl.vault.events;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.util.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class SocialSpy implements Listener {
    private static final Core plugin = Core.getPlugin(Core.class);

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent q) {
        Player p = q.getPlayer();
        for (Player a : Bukkit.getOnlinePlayers()) {
            if (plugin.socialspy.contains(a.getName())) {
                if (q.getMessage().startsWith("/msg") || (q.getMessage().startsWith("/r"))) {
                    if (!p.getName().equals(a.getName())) {
                        a.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[" + Settings.PRIMARY_COLOUR + "SocialSpy&8] &7" + p.getName() + " &8» " + Settings.SECONDARY_COLOUR + q.getMessage()));
                    }
                }
            }
        }
    }
}
