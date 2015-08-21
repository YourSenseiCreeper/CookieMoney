package me.gastherr.cookiemoney;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CookieMoney extends JavaPlugin{

	private ConsoleCommandSender con;
	
	@Override
	public void onEnable() {
		con = Bukkit.getConsoleSender();
		con.sendMessage("Welcome to CookieMoney!");
	}
	
	@Override
	public void onDisable() {
		con.sendMessage("CookieMoney will back soon!");
	}

	
	
	

}
