package net.milkbowl.vault.commands.cmds;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Rename extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
        ItemStack item = p.getItemInHand();

        if (item.getType() == Material.AIR) {
        	p.sendMessage(Settings.PREFIX("You aren't holding anything!"));
        } else {
    		StringBuilder newName = new StringBuilder();
    		for (int i = 1; i != args.length; ++i) {
    			newName.append(args[i]).append(" ");
    		}
        	ItemMeta meta = item.getItemMeta();
        	if (meta != null) {
        		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', newName.toString()));
        		item.setItemMeta(meta);
        		p.sendMessage(Settings.PREFIX("The new name has been set to " + Settings.HIGHLIGHT_COLOUR + newName));
        	}
        }
	}


}
