package net.milkbowl.vault.commands.cmds;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Wand extends CommandManager {
	
	private Material wandMaterial;
	private int wandRadius;
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		
		if (args.length == 3) {
			try {
				wandMaterial = Material.valueOf(args[1].toUpperCase());
			} catch (IllegalArgumentException e) {
				p.sendMessage(Settings.PREFIX("The block " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " was not found!"));
				return;
			}
			try {
				wandRadius = Integer.parseInt(args[2]);
			} catch (NumberFormatException ex) {
				p.sendMessage(Settings.PREFIX("The radius has to be a number"));
				return;
			}
			ItemStack wandItem = new ItemStack(Material.DIAMOND_AXE, 1);
			ItemMeta wandMeta = wandItem.getItemMeta();
			p.sendMessage(Settings.PREFIX("You received a "  +Settings.HIGHLIGHT_COLOUR + wandMaterial.toString() + Settings.SECONDARY_COLOUR + " wand"));
			wandMeta.setDisplayName(Settings.SECONDARY_COLOUR+Settings.NAME + " wand: " + Settings.HIGHLIGHT_COLOUR + wandMaterial.toString());
			
			ArrayList<String> lore = new ArrayList<>();
			lore.add(Settings.SECONDARY_COLOUR + "Radius: " + Settings.HIGHLIGHT_COLOUR + wandRadius);
			wandMeta.setLore(lore);
			
			wandItem.setItemMeta(wandMeta);
			p.getInventory().addItem(wandItem);
			
		} else {
			p.sendMessage(Settings.USAGE("wand <block> <radius>"));
		}
		
			
			
	}

}
