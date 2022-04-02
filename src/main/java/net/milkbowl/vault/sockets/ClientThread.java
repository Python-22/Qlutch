package net.milkbowl.vault.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.function.Consumer;

public class ClientThread extends Thread {
	
    private Socket client;
    private DataInputStream inStream;
    private DataOutputStream outStream;
    private Consumer<String> messageReceive;
    private Consumer<ClientThread> disconnected;

    private boolean isRunning;
    
    public ClientThread(Socket inSocket, Consumer<String> messageReceive, Consumer<ClientThread> disconnected) {
    	client = inSocket;
    	this.messageReceive = messageReceive;
    	isRunning = true;
    	
    	this.disconnected = disconnected;
    }
    
    
	public void run() {
    	byte[] bytes;
    	
    	try {
            inStream = new DataInputStream(client.getInputStream());
            outStream = new DataOutputStream(client.getOutputStream());
            
            while (isRunning) {
            	bytes = new byte[2048];
            	int bytesToRead = inStream.read(bytes);
            	if (bytesToRead < 0)
            		break;
            		
            		String msgReceived = new String(bytes);
            		
            		if (messageReceive != null)
            			messageReceive.accept(msgReceived);
            		}
            		
                    inStream.close();
                    outStream.close();
                    client.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
        //System.out.println("Client Disconnected.");
        disconnect();
    }
    
    public void send(String msg) {
    	try {
    		outStream.write(msg.getBytes());
    	} catch (IOException e) {
    		e.printStackTrace();
    		disconnect();
    	}
    }
    
    public void disconnect() {
    	try {
    		if (!client.isClosed()) {
                client.shutdownInput();
                client.shutdownOutput();
                client.close();
    		}
    		isRunning = false;

    		
    		disconnected.accept(this);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

}
