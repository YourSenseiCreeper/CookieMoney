package me.gastherr.cookiemoney.commands;

import me.gastherr.cookiemoney.CookieBase;
import me.gastherr.cookiemoney.util.CookieContainer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CookieCMD implements CommandExecutor{
	
	private CookieBase base;
	
	public CookieCMD(CookieBase base){
		this.base = base;
		base.getPlugin().getCommand("cookies").setExecutor(this);
		base.getPlugin().getCommand("cookie").setExecutor(this);
	}
	
	private String ingame = ChatColor.RED+"This command can use only player in game!";
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("cookies") || cmd.getName().equalsIgnoreCase("cookie")){
			
			if (args.length == 0){
				
				if (!(s instanceof Player)){
					s.sendMessage(ingame);
					return false;
				}
				bankCMD(s, args);
				// s.sendMessage("You have: "+CookieMoneyAPI.getCookies(s.getName())+" cookies!");
			}
			if (args.length > 0){
				// if(args[0].equalsIgnoreCase("bank")) ;
				// if(args[0].equalsIgnoreCase("deponate")) deponateCMD(s, args);
			}
		}
		
		return false;
	}
	
	public void bankCMD(CommandSender s, String[] args){
			
			if (!(s instanceof Player)){
				s.sendMessage(ingame);
			}
			
			Player p = (Player) s;
			p.openInventory(new CookieContainer().createInventory(p));
			base.getInBank().put(p.getUniqueId(), true);
		
	}
	
	public void deponateCMD(CommandSender s, String[] args){
		
		if (args.length == 1){
			
			if (!(s instanceof Player)){
				s.sendMessage(ingame);
			}
			
			Player p = (Player) s;
			p.openInventory(new CookieContainer().createDeponateInventory(p));
			
		}else{
			s.sendMessage(ChatColor.RED+"Correct usage: /cookies deponate");
		}
		
	}

}
