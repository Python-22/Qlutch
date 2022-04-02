package net.milkbowl.vault.commands.cmds;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

import java.util.ArrayList;

public class Bombs extends CommandManager {

	private int Radius;
	
	public void execute(Core plugin, String msg, String[] args, Player p) {

		if (args.length <= 1) {
			p.sendMessage(Settings.USAGE("bomb <radius>"));
		} else {
			try {
				Radius = Integer.parseInt(args[1]);
			} catch (NumberFormatException ex) {
				p.sendMessage(Settings.PREFIX("The radius has to be a number"));
				return;
			}

			p.sendMessage(Settings.PREFIX("Right click to throw the bomb!"));
			ItemStack bomb = new ItemStack(Material.TNT, 1);
			ItemMeta meta = bomb.getItemMeta();
			meta.setDisplayName("§8§kI§c" + Settings.NAME + " Bomb§8§kI");

			ArrayList<String> lore = new ArrayList<>();
			lore.add(Settings.SECONDARY_COLOUR + "Radius: " + Settings.HIGHLIGHT_COLOUR + Radius);
			meta.setLore(lore);

			bomb.setItemMeta(meta);
			p.getInventory().addItem(bomb);
		}
	}

}
