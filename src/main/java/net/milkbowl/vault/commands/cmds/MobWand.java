package net.milkbowl.vault.commands.cmds;
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class MobWand extends CommandManager {

    private EntityType mobType;
    private int mobAmount;

    public void execute(Core plugin, String msg, String[] args, Player p) {

        if (args.length == 3) {
            try {
                mobType = EntityType.valueOf(args[1].toUpperCase());
            } catch (IllegalArgumentException e) {
                p.sendMessage(Settings.PREFIX("The mob " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " was not found!"));
                return;
            }
            try {
                mobAmount = Integer.parseInt(args[2]);
            } catch (NumberFormatException ex) {
                p.sendMessage(Settings.PREFIX("The amount has to be a number"));
                return;
            }
            ItemStack wandItem = new ItemStack(Material.DIAMOND_HOE, 1);
            ItemMeta wandMeta = wandItem.getItemMeta();
            p.sendMessage(Settings.PREFIX("You received a "  +Settings.HIGHLIGHT_COLOUR + mobType.toString() + Settings.SECONDARY_COLOUR + " mob wand"));
            wandMeta.setDisplayName(Settings.SECONDARY_COLOUR+Settings.NAME + " mob wand: " + Settings.HIGHLIGHT_COLOUR + mobType.toString());

            ArrayList<String> lore = new ArrayList<>();
            lore.add(Settings.SECONDARY_COLOUR + "Amount: " + Settings.HIGHLIGHT_COLOUR + mobAmount);
            wandMeta.setLore(lore);

            wandItem.setItemMeta(wandMeta);
            p.getInventory().addItem(wandItem);

        } else {
            p.sendMessage(Settings.USAGE("mobwand <mob> <amount>"));
        }



    }

}
