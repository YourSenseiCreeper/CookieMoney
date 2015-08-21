package me.gastherr.cookiemoney.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import me.gastherr.cookiemoney.CookieBase;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager{
	
	private CookieBase base;
	private FileConfiguration config;
	
	public ConfigManager(CookieBase plugin){
		this.base = plugin;
		try {
			registerPluginConfig("config");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void registerPluginConfig(String name) throws IOException{
		File file = new File(base.getPlugin().getDataFolder(), name+".yml");
		FileConfiguration getConfig = YamlConfiguration.loadConfiguration(file);
        ConsoleCommandSender con = Bukkit.getConsoleSender();
        if (!file.exists()) {
                file.getParentFile().mkdirs();
                getConfig = YamlConfiguration.loadConfiguration(file);
                getConfig.save(file);
                config = getConfig;
                con.sendMessage(("&f[&2CookieMoney&f] Creating:&2 "+name+".yml"));
        }else{
        	config = getConfig;
        }
	}
	
	public void savePluginConfig(String name){
		File f = new File(base.getPlugin().getDataFolder(), name+".yml");
		try {
			config.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
	public HashMap<UUID, Integer> playerLoader(){
		HashMap<UUID, Integer> players = new HashMap<>();
		ConsoleCommandSender con = Bukkit.getConsoleSender();
		int all = 0;
		for (String uid : config.getConfigurationSection("").getKeys(false)) {
			UUID ud = UUID.fromString(uid);
		    int points = config.getInt(uid);
		    players.put(ud, points);
		    all++;
		}
		con.sendMessage(base.color("&f[&2CookieMoney&f] All saved players ("+all+") loaded!"));
		return players;
	}
	
	public void playerSaver(){
		for(UUID uid : base.getPlayers().keySet()){
			savePlayer(uid);
		}
		savePluginConfig("config");
	}
	
	//Zapis gracza z funkcj¹ auto-oczyszczania
	public void savePlayer(UUID playerUUID){
		UUID uid = playerUUID;
		int pktConfig = config.getInt(uid.toString());
		int pkt = base.getPlayers().get(uid);
		
		if (pkt != pktConfig){
			if (config.contains(uid.toString())){
				config.set(uid.toString(), pkt);
				return;
			}else if(pkt == 0 && config.contains(uid.toString())){
				config.set(uid.toString(), null);
				return;
			}else{
				config.createSection(uid.toString());
				config.set(uid.toString(), pkt);
				return;
			}
		}
		
	}
	
	public void save(){
		playerSaver();
		savePluginConfig("config");
	}

}
