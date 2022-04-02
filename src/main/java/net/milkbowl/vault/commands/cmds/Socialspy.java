package net.milkbowl.vault.commands.cmds;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;
import org.bukkit.entity.Player;

public class Socialspy extends CommandManager {

    public void execute(Core plugin, String msg, String[] args, Player p) {
        if (plugin.socialspy.contains(p.getName())) {
            plugin.socialspy.remove(p.getName());
            p.sendMessage(Settings.PREFIX("Social spy is now off!"));
        } else {
            plugin.socialspy.add(p.getName());
            p.sendMessage(Settings.PREFIX("Social spy is now on!"));
        }
    }
}
