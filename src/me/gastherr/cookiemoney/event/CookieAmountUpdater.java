package me.gastherr.cookiemoney.event;

import java.util.Collection;
import java.util.UUID;

import me.gastherr.cookiemoney.CookieBase;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

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
			
			int cookies = 0;
			@SuppressWarnings("unchecked")
			Collection<ItemStack> its = (Collection<ItemStack>) p.getInventory().all(Material.COOKIE).values();
			for (ItemStack i : its){
				cookies += i.getAmount();
			}
			int amount = cb.getPlayers().get(uid);
			if (cookies != amount){
				cb.getPlayers().replace(uid, cookies);
				p.sendMessage("Ciastka: "+cookies);
			}
			return;
		}else{
			cb.getPlayers().replace(uid, 0);
		}
		
	}

}
