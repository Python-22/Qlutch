package net.milkbowl.vault.commands.cmds;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Top extends CommandManager {

    public void execute(Core plugin, String msg, String[] args, Player p) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            p.teleport(p.getWorld().getHighestBlockAt(p.getLocation()).getLocation().add(0.0, 1.0, 0.0));
            p.sendMessage(Settings.PREFIX("Woosh!"));
        });
    }
}
