package net.milkbowl.vault.commands.cmds;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.concurrent.ExecutionException;

public class Swap extends CommandManager {


    public void execute(Core plugin, String msg, String[] args, Player p) {
        if (args.length <= 1) {
            p.sendMessage(Settings.USAGE("Swap <player> <player>"));
        } else {
            Player target = Bukkit.getServer().getPlayer(args[1]);
            if (target == null) {
                p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
            } else {
                Player target2 = Bukkit.getServer().getPlayer(args[2]);
                if (target2 == null) {
                    p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[2] + Settings.SECONDARY_COLOUR + " is not online."));
                } else {
                    p.sendMessage(Settings.PREFIX(Settings.HIGHLIGHT_COLOUR + target.getName() + Settings.SECONDARY_COLOUR + " and " + Settings.HIGHLIGHT_COLOUR + target2.getName() + Settings.SECONDARY_COLOUR + " have swapped locations!"));
                    plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
                        try {
                            Bukkit.getScheduler().callSyncMethod(plugin, () ->
                                    target.teleport(target2.getLocation())
                            ).get();
                        } catch (InterruptedException | ExecutionException ignored) {}
                    });
                    plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
                        try {
                            Bukkit.getScheduler().callSyncMethod(plugin, () ->
                                    target2.teleport(target.getLocation())
                            ).get();
                        } catch (InterruptedException | ExecutionException ignored) {}
                    });
                }
            }
        }
    }
}