package net.milkbowl.vault.commands.cmds;

import org.bukkit.Material;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

import java.util.Locale;

public class Enchant extends CommandManager {
	
	private int level;
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 2) {
			p.sendMessage(Settings.USAGE("enchant <enchantment> <level>"));
		} else {
			ItemStack item = p.getItemInHand();
			if (item.getType() == Material.AIR) {
				p.sendMessage(Settings.PREFIX("You aren't holding anything!"));
			} else {
				ItemMeta meta = item.getItemMeta();
				if(EnchantmentWrapper.getByName(args[1].toUpperCase()) == null) {
					p.sendMessage(Settings.PREFIX("This enchantment wasn't found!"));
				} else {
					try {
						level = Integer.parseInt(args[2]);
					} catch (Exception e) {
						p.sendMessage(Settings.PREFIX("Invalid integer (Max: 32727)"));
						return;
					}
					
					meta.addEnchant(EnchantmentWrapper.getByName(args[1].toUpperCase()), level, true);
					item.setItemMeta(meta);
					p.sendMessage(Settings.PREFIX("Your item was enchanted with " + Settings.HIGHLIGHT_COLOUR + args[1] + " " + level));
				}
			}
		}
	}

}
