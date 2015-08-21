package me.gastherr.cookiemoney.event;

import java.util.Collection;
import java.util.UUID;

import me.gastherr.cookiemoney.CookieBase;
import me.gastherr.cookiemoney.CookieMoneyAPI;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryEvents implements Listener{
	
	private CookieBase base;
	
	public InventoryEvents(CookieBase base){
		this.base = base;
		base.getPlugin().getServer().getPluginManager().registerEvents(this, base.getPlugin());
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e){
		
		if(e.getInventory().contains(Material.COOKIE) && e.getInventory().getName().equals("Cookie deponate")){
			UUID uid = e.getPlayer().getUniqueId();
			if (base.getSafeTransfer().containsKey(uid) && base.getSafeTransfer().get(uid)){
				base.getSafeTransfer().remove(uid);
				return;
			}
			int cookies = 0;
			Player p = (Player)	e.getPlayer();
			@SuppressWarnings("unchecked")
			Collection<ItemStack> its = (Collection<ItemStack>) p.getOpenInventory().getTopInventory().all(Material.COOKIE).values();
			for (ItemStack i : its){
				p.getInventory().addItem(i);
			}
			
			
			p.sendMessage("Oops! You closed your deponate inventory! Luckly we caught your "+cookies+" cookies!" );
			p.updateInventory();
			
		}
		
	}
	
	@EventHandler
	public void onInteract(InventoryClickEvent e){
		
		if (e.getClickedInventory() != null){
			if (e.getCurrentItem().getType().equals(Material.EMERALD_BLOCK)){
				e.setCancelled(true);
				int cookies = 0;
				@SuppressWarnings("unchecked")
				Collection<ItemStack> its = (Collection<ItemStack>) e.getClickedInventory().all(Material.COOKIE).values();
				for (ItemStack i : its){
					cookies += i.getAmount();
				}
				
				int allCks = CookieMoneyAPI.getCookies(e.getWhoClicked().getName()) - cookies;
				if (allCks < 0){
					e.getWhoClicked().getInventory().addItem(new ItemStack(Material.COOKIE, -allCks));
					e.getWhoClicked().closeInventory();
					return;
				}
				
				Player p = (Player) e.getWhoClicked();
				
				CookieMoneyAPI.setCookies(p, CookieMoneyAPI.getCookies(p)-allCks);
				base.getSafeTransfer().put(e.getWhoClicked().getUniqueId(), true);
				if (cookies != 0){
					p.sendMessage("You deposited "+allCks+" cookies. You have now "+CookieMoneyAPI.getCookies(p)+" cookies!");
				}
				p.closeInventory();
			}
		}
		if (e.getClickedInventory() != null && e.getClickedInventory().getName().equals("Cookie deponate")){
			if (e.getCurrentItem().getType().equals(Material.EMERALD_BLOCK)){
				e.setCancelled(true);
				int cookies = 0;
				@SuppressWarnings("unchecked")
				Collection<ItemStack> its = (Collection<ItemStack>) e.getClickedInventory().all(Material.COOKIE).values();
				for (ItemStack i : its){
					cookies += i.getAmount();
				}
				CookieMoneyAPI.addCookies(e.getWhoClicked().getName(), cookies);
				base.getSafeTransfer().put(e.getWhoClicked().getUniqueId(), true);
				Player p = (Player)	e.getWhoClicked();
				base.getSafeTransfer().put(e.getWhoClicked().getUniqueId(), true);
				
				if (cookies != 0){
					p.sendMessage("You deponated "+cookies+" cookies. You have now "+CookieMoneyAPI.getCookies(p)+" cookies!");
				}
				
				p.closeInventory();
			}
		}
		
	}

}
