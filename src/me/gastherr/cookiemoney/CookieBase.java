package me.gastherr.cookiemoney;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;

import me.gastherr.cookiemoney.commands.CookieCMD;
import me.gastherr.cookiemoney.event.CookieAmountUpdater;
import me.gastherr.cookiemoney.event.PlayerInAndOut;
import me.gastherr.cookiemoney.util.ConfigManager;

public class CookieBase {
	
	private CookieMoney plugin;
	protected ConfigManager cm;
	protected HashMap<UUID, Integer> players;
	
	public CookieBase(CookieMoney plugin){
		this.setPlugin(plugin);
		cm = new ConfigManager(this);
		players = cm.playerLoader();
		
		new CookieAmountUpdater(this);
		new PlayerInAndOut(this);
		new CookieCMD(this);
	}
	
	public CookieMoney getPlugin() {
		return plugin;
	}
	
	public HashMap<UUID, Integer> getPlayers(){
		return this.players;
	}

	private void setPlugin(CookieMoney plugin) {
		this.plugin = plugin;
	}

	public String color(String msg){
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	public ConfigManager getConfig(){
		return this.cm;
	}

}
