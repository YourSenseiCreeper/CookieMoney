package me.gastherr.cookiemoney.event;

import java.util.UUID;

import me.gastherr.cookiemoney.CookieBase;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerInAndOut implements Listener{
	
	private CookieBase cb;
	
	public PlayerInAndOut(CookieBase base){
		this.cb = base;
		base.getPlugin().getServer().getPluginManager().registerEvents(this, base.getPlugin());
	}
	
	@EventHandler
	public void login(PlayerJoinEvent e){
		
		UUID uid = e.getPlayer().getUniqueId();
		if(!cb.getPlayers().containsKey(uid)){
			cb.getPlayers().put(uid, 0);
		}
		
	}
	
	@EventHandler
	public void logout(PlayerQuitEvent e){
		
		UUID uid = e.getPlayer().getUniqueId();
		cb.getConfig().savePlayer(uid);
	}

}
