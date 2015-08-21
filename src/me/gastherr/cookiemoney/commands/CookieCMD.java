package me.gastherr.cookiemoney.commands;

import me.gastherr.cookiemoney.CookieBase;
import me.gastherr.cookiemoney.CookieMoneyAPI;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CookieCMD implements CommandExecutor{
	
	public CookieCMD(CookieBase base){
		base.getPlugin().getCommand("cookies").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("cookies")){

			if (!(s instanceof Player)){
				s.sendMessage("This command can use only player in game!");
				return false;
			}
			s.sendMessage("You have:"+CookieMoneyAPI.getCookies(s.getName())+" cookies!");
		}
		
		return false;
	}

}
