package de.Ygg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.login.LoginException;

import de.Ygg.listener.CommandListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class YggBot {

	public static YggBot INSTANCE; 
	
	public ShardManager shardMan;
	private CommandManager cmdMan;
	
	public static void main(String[] args){
		try {
			new YggBot();
		} catch (LoginException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		
	}

	public YggBot()  throws LoginException, IllegalArgumentException {
		INSTANCE = this;
		
		
		
		
		DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
		builder.setToken("NjI5NjkyNzIxMTUzNTcyODc2.XZddrA._HomYboc7cKkoN7KrpMyEQlHDFM");
		
		
		builder.setActivity(Activity.playing("leben."));
		builder.setStatus(OnlineStatus.ONLINE);
		
		this.cmdMan = new CommandManager();
		
		
		
		builder.addEventListeners(new CommandListener());	
		
		shardMan = builder.build();
		System.out.println("Bot online.");
		
		shutdown();

		
		
		
	}
	public void shutdown() {
		
		new Thread(() -> {
			
			String line ="";
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				while((line = reader.readLine()) != null) {
					
					if(line.equalsIgnoreCase("exit")) {
				if(shardMan != null) {
					shardMan.setStatus(OnlineStatus.OFFLINE);
					shardMan.shutdown();		
					System.out.println("Bot offline.");
					}
				
				reader.close();
				}
					else {
						System.out.println("Use 'exit' to shutdown.");
					}
					}
			}catch (IOException e) {
				e.printStackTrace();
				
			}
			
		}).start();
 	}
	
	
	public CommandManager getCmdMan() {
		return cmdMan;
	}
 	
}
