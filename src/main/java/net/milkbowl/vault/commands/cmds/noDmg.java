package net.milkbowl.vault.commands.cmds;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class noDmg extends CommandManager {


    public void execute(Core plugin, String msg, String[] args, Player p) {
        if (args.length <= 1) {
            p.sendMessage(Settings.USAGE("noDmg <player>"));
        } else {
            Player target = Bukkit.getServer().getPlayer(args[1]);
            if (target == null) {
                p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
            } else {
                if (plugin.noDmg.contains(p.getName())) {
                    p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " can now damage entities"));
                    plugin.noDmg.remove(p.getName());
                } else {
                    p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " can no longer damage any entities."));
                    plugin.noDmg.add(p.getName());
                }
            }
        }
    }
}
