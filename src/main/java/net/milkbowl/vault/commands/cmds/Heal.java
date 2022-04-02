package net.milkbowl.vault.commands.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.util.Settings;

public class Heal extends CommandManager {
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (args.length <= 1) {
			p.sendMessage(Settings.PREFIX("You have been healed"));
			p.setHealth(20);
			for(PotionEffect effects: p.getActivePotionEffects()) {
				for(NegativeEffects bad: NegativeEffects.values()) {
					if(effects.getType().getName().equalsIgnoreCase(bad.name())) {
						p.removePotionEffect(effects.getType()); 
					}
				}
			}
		} else {
			if (args[1].equals("*")) {
				for (Player a : Bukkit.getOnlinePlayers()) {
					a.sendMessage(Settings.PREFIX("You have been healed by " + Settings.HIGHLIGHT_COLOUR + p.getName()));
					a.setHealth(20);
					for(PotionEffect effects: a.getActivePotionEffects()) {
						for(NegativeEffects bad: NegativeEffects.values()) {
							if(effects.getType().getName().equalsIgnoreCase(bad.name())) {
								a.removePotionEffect(effects.getType()); 
							}
						}
					}
				}
				p.sendMessage(Settings.PREFIX("Everyone was healed!"));
			} else {
				Player target = Bukkit.getServer().getPlayer(args[1]);
				if (target == null) {
					p.sendMessage(Settings.PREFIX("The player " + Settings.HIGHLIGHT_COLOUR + args[1] + Settings.SECONDARY_COLOUR + " is not online."));
				} else {
					target.sendMessage(Settings.PREFIX("You have been healed by " + Settings.HIGHLIGHT_COLOUR + p.getName()));
					target.setHealth(20);
					for(PotionEffect effects: target.getActivePotionEffects()) {
						for(NegativeEffects bad: NegativeEffects.values()) {
							if(effects.getType().getName().equalsIgnoreCase(bad.name())) {
								target.removePotionEffect(effects.getType()); 
							}
						}
					}
				}
			}
		}
	}
	
    private enum NegativeEffects{
        CONFUSION, HARM, HUNGER,POISON, SLOW_DIGGING, SLOW, WEAKNESS, WITHER
    }
}
