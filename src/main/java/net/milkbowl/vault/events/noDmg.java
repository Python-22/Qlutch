package net.milkbowl.vault.events;

import net.milkbowl.vault.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class noDmg implements Listener {
    private static final Core plugin = Core.getPlugin(Core.class);


    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (plugin.noDmg.contains(e.getDamager().getName())) {
            e.setCancelled(true);
        }
    }


}
