package me.gastherr.cookiemoney;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CookieMoneyAPI{
	
	private static CookieBase cb;
	
	public CookieMoneyAPI(CookieBase plugin) {
		CookieMoneyAPI.cb = plugin;
	}
	
	/**
	 * Pobiera iloœæ ciastek.
	 * @param name Nazwa gracza
	 * @return Iloœæ ciastek gracza
	 */
	public static int getCookies(String name){
		Player p = Bukkit.getPlayer(name);
		return getCookies(p.getUniqueId());	
	}
	
	/**
	 * Pobiera iloœæ ciastek.
	 * @param uid UUID gracza
	 * @return Iloœæ ciastek gracza
	 */
	public static int getCookies(UUID uid){
		
		if(cb.getPlayers().containsKey(uid)){
			return cb.getPlayers().get(uid);
		}else{
			return 0;
		}
		
	}
	
	/**
	 * Pobiera iloœæ ciastek.
	 * @param p Gracz od którego pobierana jest iloœæ ciastek
	 * @return Iloœæ ciastek gracza p
	 */
	public static int getCookies(Player p){
		return getCookies(p.getUniqueId());
	}
	
	/**
	 * Ustawia iloœæ ciastek dla gracza p.
	 * @param p Gracz
	 * @param amount Iloœæ ciastek do ustawienia
	 */
	public static void setCookies(Player p, int amount){
		setCookies(p.getUniqueId(), amount);
	}
	
	/**
	 * Ustawia iloœæ ciastek dla gracza z podanym UUID.
	 * @param uid UUID gracza
	 * @param amount Iloœæ ciastek do ustawienia
	 */
	public static void setCookies(UUID uid, int amount){
		if (cb.getPlayers().containsKey(uid)){
			cb.getPlayers().replace(uid, amount);
		}
	}
	
	/**
	 * Ustawia iloœæ ciastek dla gracza o podanej nazwie.
	 * @param name Nazwa gracza
	 * @param amount Iloœæ ciastek do ustawienia
	 */
	public static void setCookies(String name, int amount){
		Player p = Bukkit.getPlayer(name);
		setCookies(p.getUniqueId(), amount);
	}
	
	/**
	 * Dodaje iloœæ ciastek amount do ogólnej liczby ciastek gracza p.
	 * @param p Gracz któremu dodawane s¹ ciastka
	 * @param amount Iloœæ ciastek do dodania
	 */
	public static void addCookies(Player p, int amount){
		setCookies(p, getCookies(p.getUniqueId())+amount);
	}
	
	/**
	 * Dodaje iloœæ ciastek amount do ogólnej liczby ciastek gracza z UUId uid.
	 * @param uid UUID gracza któremu dodawane s¹ ciastka
	 * @param amount Iloœæ ciastek do dodania
	 */
	public static void addCookies(UUID uid, int amount){
		setCookies(uid, getCookies(uid)+amount);
	}
	
	/**
	 * Dodaje iloœæ ciastek amount do ogólnej liczby ciastek gracza z nazw¹ name.
	 * @param name Nazwa gracza któremu dodawane s¹ ciastka
	 * @param amount Iloœæ ciastek do dodania
	 */
	public static void addCookies(String name, int amount){
		setCookies(name, getCookies(name)+amount);
	}
	
	/**
	 * Deponuje ciastka na konto gracza.
	 * @param p Gracz który chce zdeponowaæ ciastka
	 * @param item Ciastka do zdeponowania
	 */
	public static void deposite(Player p, ItemStack item){
		int amount = item.getAmount();
		p.getInventory().remove(item);
		addCookies(p, amount);
	}
	
	/**
	 * Deponuje ciastka na konto gracza.
	 * @param uid UUID gracza który chce zdeponowaæ ciastka.
	 * @param item Ciastka do zdeponowania
	 * @throws NullPointerException Gdy gracz o podanym UUID jest offline.
	 */
	public static void deposite(UUID uid, ItemStack item) throws NullPointerException{
		Player p = Bukkit.getPlayer(uid);
		deposite(p, item);
	}
	
	/**
	 * Deponuje ciastka na konto gracza.
	 * @param name Nazwa gracza który chce zdeponowaæ ciastka.
	 * @param item Ciastka do zdeponowania
	 * @throws NullPointerException Gdy gracz o podanej nazwie jest offline.
	 */
	public static void deposite(String name, ItemStack item) throws NullPointerException{
		Player p = Bukkit.getPlayer(name);
		deposite(p, item);
	}
	
	/**
	 * Wy³¹cza plugin (U¿ywaæ awaryjnie w przypadku du¿ych lagów)
	 */
	public static void disable(){
		Bukkit.getPluginManager().disablePlugin(cb.getPlugin());
	}

}
