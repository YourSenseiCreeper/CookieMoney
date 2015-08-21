package me.gastherr.cookiemoney.event;

import java.util.UUID;

import me.gastherr.cookiemoney.CookieBase;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class CookieAmountUpdater implements Listener{
	
	private CookieBase cb;
	
	public CookieAmountUpdater(CookieBase base){
		base.getPlugin().getServer().getPluginManager().registerEvents(this, base.getPlugin());
		this.cb = base;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		
		Player p = e.getPlayer();
		UUID uid = p.getUniqueId();
		
		if (p.getInventory().contains(Material.COOKIE)){
			int cookies = cb.getPlayers().replace(uid, p.getInventory().first(Material.COOKIE));
			p.sendMessage("Ciastka: "+cookies);
			return;
		}else{
			cb.getPlayers().replace(uid, 0);
		}
		
	}

}
