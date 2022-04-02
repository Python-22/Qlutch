package net.milkbowl.vault.commands.cmds;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Fix extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length >= 1 && (args[1].equals("*"))) {
			repair(p);
			p.sendMessage(Settings.PREFIX("All the items in your inventory have been repaired!"));
		} else {
			ItemStack item = p.getItemInHand();
			if (item.getType() == Material.AIR) {
				p.sendMessage(Settings.PREFIX("You aren't holding anything!"));
			} else {
				try {
					item.setDurability((short)0);
					p.sendMessage(Settings.PREFIX("Your item is now max durability!"));
				} catch(Exception e) {
					p.sendMessage(Settings.PREFIX("This item cannot be repaired!"));
				}
			}
		}
	}
	
	
	public void repair(Player p) {
		for(int i = 0; i <= 36; i++) {
			try {
				p.getInventory().getItem(i).setDurability((short) 0);
			} catch(Exception ignored) {}
		}
	}

}
