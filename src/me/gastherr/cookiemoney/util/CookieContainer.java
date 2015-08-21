package me.gastherr.cookiemoney.util;

import me.gastherr.cookiemoney.CookieMoneyAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CookieContainer{
	
	public CookieContainer(){}
	
	public Inventory createDepositeInventory(Player p){
		int cookies = CookieMoneyAPI.getCookies(p);
		Inventory in = Bukkit.createInventory(p, 36, "Cookie deposite");
		
		if (cookies != 0){
			in.addItem(new ItemStack(Material.COOKIE, cookies));
		}
		
		ItemStack exit = new ItemStack(Material.EMERALD_BLOCK);
		ItemMeta mExit = exit.getItemMeta();
		mExit.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"EXIT");
		exit.setItemMeta(mExit);
		
		in.setItem(in.getSize()-1, exit);
		return in;
	}
	
	public Inventory createDeponateInventory(Player p){
		Inventory in = Bukkit.createInventory(p, 36, "Cookie deponate");
		
		ItemStack exit = new ItemStack(Material.EMERALD_BLOCK);
		ItemMeta mExit = exit.getItemMeta();
		mExit.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"EXIT");
		exit.setItemMeta(mExit);
		
		in.setItem(in.getSize()-1, exit);
		return in;
	}
	
	public Inventory createInventory(Player p){
		int cookies = CookieMoneyAPI.getCookies(p);
		Inventory in = Bukkit.createInventory(p, 9*invSize(cookies), "Your account");
		
		ItemStack depo = new ItemStack(Material.ENDER_CHEST);
		ItemMeta mDepo = depo.getItemMeta();
		mDepo.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"DEPOSITING");
		depo.setItemMeta(mDepo);
		
		ItemStack withd = new ItemStack(Material.CHEST);
		ItemMeta mWith = withd.getItemMeta();
		mWith.setDisplayName(ChatColor.DARK_RED+""+ChatColor.BOLD+"WITHDRAWING");
		withd.setItemMeta(mWith);
		
		ItemStack am = new ItemStack(Material.COOKIE);
		ItemMeta mAm = am.getItemMeta();
		mAm.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+cookies+" COOKIES");
		am.setItemMeta(mAm);
		
		in.setItem(in.getSize()-1, am);
		in.addItem(depo);
		in.addItem(withd);
		return in;
	}
	
	private int invSize(int cookies){
		int size = 1;
		if(cookies > 512) size = 2;
		else if (cookies > 512+576) size = 3;
		else if (cookies > 51+76*2) size = 4;
		else if (cookies > 512+576*3) size = 4;
		else if (cookies > 512+576*4) size = 5;
		else if (cookies > 512+576*5) size = 6;
		return size;
	}


}
