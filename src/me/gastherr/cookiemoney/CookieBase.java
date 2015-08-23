package me.gastherr.cookiemoney;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;

import me.gastherr.cookiemoney.commands.CookieCMD;
import me.gastherr.cookiemoney.event.InventoryEvents;
import me.gastherr.cookiemoney.event.PlayerInAndOut;
import me.gastherr.cookiemoney.util.ConfigManager;

public class CookieBase {
	
	private CookieMoney plugin;
	protected ConfigManager cm;
	protected HashMap<UUID, Integer> players;
	protected HashMap<UUID, Boolean> safeTransfer;
	
	public CookieBase(CookieMoney plugin){
		this.setPlugin(plugin);
		cm = new ConfigManager(this);
		players = cm.playerLoader();
		safeTransfer = new HashMap<>();
		
		// new CookieAmountUpdater(this); TO REMOVE
		new PlayerInAndOut(this);
		new InventoryEvents(this);
		
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
	
	public HashMap<UUID, Boolean> getSafeTransfer(){
		return this.safeTransfer;
	}

}
