package net.milkbowl.vault.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.util.Settings;

public class Bombs implements Listener {
	private static final Core plugin = Core.getPlugin(Core.class);
	
	@EventHandler
	public void bombLaunch(PlayerInteractEvent q) {
		ItemStack item = q.getPlayer().getItemInHand();
		Action action = q.getAction();
		Player p = q.getPlayer();
		final Location loc = p.getLocation();
		loc.setY(loc.getY() + 1.5f);
		
		if (item == null) {
			return;
		}
		
		if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
			if (item.getType().equals(Material.TNT) && (item.getItemMeta().getDisplayName().equals("§8§kI§c" + Settings.NAME + " Bomb§8§kI"))) {
				ItemStack bomb = new ItemStack(item.getType(), 1);
				final Entity drop = loc.getWorld().dropItemNaturally(loc, bomb);
				drop.setVelocity(loc.getDirection().multiply(2));
				String loreResult[] = item.getItemMeta().getLore().toString().replace("]", "").split(Settings.HIGHLIGHT_COLOUR);
				int radius = Integer.parseInt(loreResult[loreResult.length - 1]);

				new BukkitRunnable() {
					public void run() {
						loc.getWorld().createExplosion(drop.getLocation(), radius, true);
					}
				}.runTaskLater(plugin, 40);
			}
		}
	}
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent e) {
		if(e.blockList().size() > 0) {
			e.blockList().removeIf(b -> b.getType() == Material.BARRIER);
		}
	}

}
