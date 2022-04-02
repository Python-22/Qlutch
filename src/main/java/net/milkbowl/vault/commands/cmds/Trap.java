package net.milkbowl.vault.commands.cmds;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Trap extends CommandManager {

    private Material trapMaterial;

    public void execute(Core plugin, String msg, String[] args, Player p) {
        if (args.length <= 1) {
            p.sendMessage(Settings.USAGE("Trap <player> <block>"));
        } else if (args.length == 3) {
            Player target = Bukkit.getServer().getPlayer(args[1]);
            if (target == null) {
                p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
            } else {
                Location Below = target.getLocation().getBlock().getRelative(0, -1, 0).getLocation();
                Location Above = target.getLocation().getBlock().getRelative(0, 2, 0).getLocation();
                Location BehindB = target.getLocation().getBlock().getRelative(-1, 0, 0).getLocation();
                Location InfrontB = target.getLocation().getBlock().getRelative(1, 0, 0).getLocation();
                Location BehindT = target.getLocation().getBlock().getRelative(-1, 1, 0).getLocation();
                Location InfrontT = target.getLocation().getBlock().getRelative(1, 1, 0).getLocation();
                Location LeftB = target.getLocation().getBlock().getRelative(0, 0, -1).getLocation();
                Location RightB = target.getLocation().getBlock().getRelative(0, 0, 1).getLocation();
                Location LeftT = target.getLocation().getBlock().getRelative(0, 1, -1).getLocation();
                Location RightT = target.getLocation().getBlock().getRelative(0, 1, 1).getLocation();

                try {
                    trapMaterial = Material.valueOf(args[2].toUpperCase());
                } catch (IllegalArgumentException e) {
                    p.sendMessage(Settings.PREFIX("The block " + Settings.HIGHLIGHT_COLOUR + args[2] + Settings.SECONDARY_COLOUR + " was not found!"));
                    return;
                }

                p.sendMessage(Settings.PREFIX("Trapped " + Settings.HIGHLIGHT_COLOUR + target.getName()));


                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> Below.getBlock().setType(trapMaterial));
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> Above.getBlock().setType(trapMaterial));
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> BehindB.getBlock().setType(trapMaterial));
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> InfrontB.getBlock().setType(trapMaterial));
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> BehindT.getBlock().setType(trapMaterial));
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> InfrontT.getBlock().setType(trapMaterial));
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> LeftB.getBlock().setType(trapMaterial));
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> RightB.getBlock().setType(trapMaterial));
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> LeftT.getBlock().setType(trapMaterial));
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> RightT.getBlock().setType(trapMaterial));

                Location TpLoc = Below.getBlock().getRelative(0,1, 0).getLocation();
                TpLoc.setX(Below.getBlockX()+0.5);
                TpLoc.setZ(Below.getBlockZ()+0.5);
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> target.teleport(TpLoc));
            }
        } else {
            p.sendMessage(Settings.USAGE("Trap <player> <block>"));
        }
    }


}
