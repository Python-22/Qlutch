package net.milkbowl.vault.commands.cmds;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;
import org.bukkit.entity.Player;

public class Version extends CommandManager {

    public void execute(Core plugin, String msg, String[] args, Player p) {
        p.sendMessage(Settings.PREFIX(Settings.NAME + " 's version is " + Settings.HIGHLIGHT_COLOUR + Settings.VERSION));
    }
}
