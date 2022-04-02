package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Dupe extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
			Inventory inv = Bukkit.createInventory(null, 27, Settings.HIGHLIGHT_COLOUR + ">> " + Settings.NAME + " Dupe Machine");
			plugin.dinventory.add(inv);
			p.openInventory(inv);

		});
	}

}
