package me.gastherr.cookiemoney;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CookieMoney extends JavaPlugin{

	private ConsoleCommandSender con;
	private CookieBase cb;
	private static CookieMoneyAPI cbapi; 
	
	@Override
	public void onEnable() {
		cb = new CookieBase(this);
		cbapi = new CookieMoneyAPI(cb);
		con = Bukkit.getConsoleSender();
		con.sendMessage(cb.color("&f[&2CookieMoney&f] Enabling &2v"+this.getDescription().getVersion()+"&f by&2 "+this.getDescription().getAuthors().get(0)));
	}
	
	@Override
	public void onDisable() {
		con.sendMessage("CookieMoney will back soon!");
		cb.getConfig().save();
	}

	/**
	 * Pobiera API pluginu CookieMoney
	 * @return Obiekt API pluginu CookieMoney
	 */
	public static CookieMoneyAPI getAPI(){
		return cbapi;
	}
	
	
	

}
