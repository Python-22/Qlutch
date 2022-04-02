package net.milkbowl.vault.commands.cmds;

import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.methods.Tips;
import net.milkbowl.vault.util.Settings;

public class Tip extends CommandManager {
	
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		p.sendMessage("§8("+Settings.PRIMARY_COLOUR+"Tip§8) l §7" + Tips.getTip());
	}
    
}
