package me.gastherr.cookiemoney.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import me.gastherr.cookiemoney.CookieBase;
import me.gastherr.cookiemoney.CookieMoneyAPI;
import me.gastherr.cookiemoney.util.CookieContainer;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryEvents implements Listener{
	
	private CookieBase base;
	private ArrayList<Material> wl;
	
	public InventoryEvents(CookieBase base){
		
		this.base = base;
		wl = new ArrayList<>();
		wl.add(Material.COOKIE);
		wl.add(Material.CHEST);
		wl.add(Material.ENDER_CHEST);
		wl.add(Material.EMERALD_BLOCK);
		
		base.getPlugin().getServer().getPluginManager().registerEvents(this, base.getPlugin());
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e){
		
		UUID uid = e.getPlayer().getUniqueId();
		if (!base.getInBank().containsKey(uid)) return;

		base.getInBank().remove(uid);
		
		if(e.getInventory().contains(Material.COOKIE) && e.getInventory().getName().equals("Cookie deponate")){
	
			int cookies = 0;
			Player p = (Player)	e.getPlayer();
			@SuppressWarnings("unchecked")
			Collection<ItemStack> its = (Collection<ItemStack>) p.getOpenInventory().getTopInventory().all(Material.COOKIE).values();
			for (ItemStack i : its){
				cookies += i.getAmount();
			}
			
			CookieMoneyAPI.addCookies(p, cookies);
			
			p.sendMessage("Oops! You closed your deponate inventory! Luckly we deponate your "+cookies+" cookies!" );
			p.updateInventory();
			
		}
		
	}
	
	@EventHandler
	public void onInteract(InventoryClickEvent e){
		
		Player p = (Player) e.getWhoClicked();
		UUID uid = p.getUniqueId();
		
		ItemStack exit = new ItemStack(Material.EMERALD_BLOCK);
		ItemMeta mExit = exit.getItemMeta();
		mExit.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"CONFIRM");
		exit.setItemMeta(mExit);
		
		Material click = e.getCurrentItem().getType();
		
		if(!base.getInBank().containsKey(p.getUniqueId())) return;
		
		if (!wl.contains(click)) e.setCancelled(true);
		
		if (e.getClickedInventory() != null && e.getClickedInventory().getName().equals("Cookie withdraw")){
			if (click.equals(Material.EMERALD_BLOCK)){
				e.setCancelled(true);
				int cookies = 0;
				@SuppressWarnings("unchecked")
				Collection<ItemStack> its = (Collection<ItemStack>) e.getClickedInventory().all(Material.COOKIE).values();
				for (ItemStack i : its){
					cookies += i.getAmount();
				}
				int allCks = CookieMoneyAPI.getCookies(e.getWhoClicked().getName()) - cookies;
				CookieMoneyAPI.setCookies(p, CookieMoneyAPI.getCookies(p)-allCks);
				
				if (cookies != 0){
					p.sendMessage("You withdrawed "+allCks+" cookies and you have now "+CookieMoneyAPI.getCookies(p)+" cookies!");
				}
				
				p.openInventory(new CookieContainer().createInventory(p));
				base.getInBank().put(p.getUniqueId(), true);
			}
		}
		
		if (e.getClickedInventory() != null && e.getClickedInventory().getName().equals("Cookie deposite")){
			if (e.getCurrentItem().getType().equals(Material.EMERALD_BLOCK)){
				e.setCancelled(true);
				int cookies = 0;
				@SuppressWarnings("unchecked")
				Collection<ItemStack> its = (Collection<ItemStack>) e.getClickedInventory().all(Material.COOKIE).values();
				for (ItemStack i : its){
					cookies += i.getAmount();
				}
				
				base.getPlayers().replace(uid, base.getPlayers().get(uid)+cookies);
				
				
				if (cookies != 0){
					p.sendMessage("You deposited "+cookies+" cookies and you have now "+CookieMoneyAPI.getCookies(p)+" cookies!");
				}
				
				p.openInventory(new CookieContainer().createInventory(p));
				base.getInBank().put(p.getUniqueId(), true);
			}
		}
		
		depositeAction(e, p);
		deponateAction(e, p);
		cookieAction(e, p);
		
	}
	
	private void depositeAction(InventoryClickEvent e, Player p){
		
		ItemStack depo = new ItemStack(Material.ENDER_CHEST);
		ItemMeta mDepo = depo.getItemMeta();
		mDepo.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"DEPOSITING");
		depo.setItemMeta(mDepo);
		
		if(e.getCurrentItem() != null && e.getCurrentItem().equals(depo)){
			e.setCancelled(true);
			p.openInventory(new CookieContainer().createDeponateInventory(p));
			base.getInBank().put(p.getUniqueId(), true);
		}
	}
	
	private void deponateAction(InventoryClickEvent e, Player p){
		
		ItemStack withd = new ItemStack(Material.CHEST);
		ItemMeta mWith = withd.getItemMeta();
		mWith.setDisplayName(ChatColor.DARK_RED+""+ChatColor.BOLD+"WITHDRAWING");
		withd.setItemMeta(mWith);
		
		if(e.getCurrentItem() != null && e.getCurrentItem().equals(withd)){
			e.setCancelled(true);
			p.openInventory(new CookieContainer().createDepositeInventory(p));
			base.getInBank().put(p.getUniqueId(), true);
		}
	}
	
	private void cookieAction(InventoryClickEvent e, Player p){
		ItemStack ck = new ItemStack(Material.COOKIE);
		ItemMeta mCk = ck.getItemMeta();
		mCk.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+CookieMoneyAPI.getCookies(p)+" COOKIES");
		ck.setItemMeta(mCk);
		
		if(e.getCurrentItem().equals(ck)){
			e.setCancelled(true);
		}
	}

}
