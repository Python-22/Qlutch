package net.milkbowl.vault.commands.cmds;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Item extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("i <item> <amount>"));
		} else {
			if(!(org.bukkit.Material.getMaterial(args[1].toUpperCase()) == null)){
				ItemStack togive;
				if (args.length <= 2) {
					togive = new ItemStack(Material.valueOf(args[1].toUpperCase()), 1);
				} else {
					togive = new ItemStack(Material.valueOf(args[1].toUpperCase()), Integer.parseInt(args[2]));
				}
				p.getInventory().addItem(togive);
			} else {
				p.sendMessage(Settings.PREFIX("The item " + Settings.HIGHLIGHT_COLOUR + args[1].toUpperCase() + Settings.SECONDARY_COLOUR + " doesn't seem to exist"));
			}
		}
	}

	
}