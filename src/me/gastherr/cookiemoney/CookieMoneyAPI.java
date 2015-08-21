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
	 * Pobiera ilo�� ciastek.
	 * @param name Nazwa gracza
	 * @return Ilo�� ciastek gracza
	 */
	public static int getCookies(String name){
		Player p = Bukkit.getPlayer(name);
		return getCookies(p.getUniqueId());	
	}
	
	/**
	 * Pobiera ilo�� ciastek.
	 * @param uid UUID gracza
	 * @return Ilo�� ciastek gracza
	 */
	public static int getCookies(UUID uid){
		
		if(cb.getPlayers().containsKey(uid)){
			return cb.getPlayers().get(uid);
		}else{
			return 0;
		}
		
	}
	
	/**
	 * Pobiera ilo�� ciastek.
	 * @param p Gracz od kt�rego pobierana jest ilo�� ciastek
	 * @return Ilo�� ciastek gracza p
	 */
	public static int getCookies(Player p){
		return getCookies(p.getUniqueId());
	}
	
	/**
	 * Ustawia ilo�� ciastek dla gracza p.
	 * @param p Gracz
	 * @param amount Ilo�� ciastek do ustawienia
	 */
	public static void setCookies(Player p, int amount){
		setCookies(p.getUniqueId(), amount);
	}
	
	/**
	 * Ustawia ilo�� ciastek dla gracza z podanym UUID.
	 * @param uid UUID gracza
	 * @param amount Ilo�� ciastek do ustawienia
	 */
	public static void setCookies(UUID uid, int amount){
		if (cb.getPlayers().containsKey(uid)){
			cb.getPlayers().replace(uid, amount);
		}
	}
	
	/**
	 * Ustawia ilo�� ciastek dla gracza o podanej nazwie.
	 * @param name Nazwa gracza
	 * @param amount Ilo�� ciastek do ustawienia
	 */
	public static void setCookies(String name, int amount){
		Player p = Bukkit.getPlayer(name);
		setCookies(p.getUniqueId(), amount);
	}
	
	/**
	 * Dodaje ilo�� ciastek amount do og�lnej liczby ciastek gracza p.
	 * @param p Gracz kt�remu dodawane s� ciastka
	 * @param amount Ilo�� ciastek do dodania
	 */
	public static void addCookies(Player p, int amount){
		setCookies(p, getCookies(p.getUniqueId())+amount);
	}
	
	/**
	 * Dodaje ilo�� ciastek amount do og�lnej liczby ciastek gracza z UUId uid.
	 * @param uid UUID gracza kt�remu dodawane s� ciastka
	 * @param amount Ilo�� ciastek do dodania
	 */
	public static void addCookies(UUID uid, int amount){
		setCookies(uid, getCookies(uid)+amount);
	}
	
	/**
	 * Dodaje ilo�� ciastek amount do og�lnej liczby ciastek gracza z nazw� name.
	 * @param name Nazwa gracza kt�remu dodawane s� ciastka
	 * @param amount Ilo�� ciastek do dodania
	 */
	public static void addCookies(String name, int amount){
		setCookies(name, getCookies(name)+amount);
	}
	
	/**
	 * Deponuje ciastka na konto gracza.
	 * @param p Gracz kt�ry chce zdeponowa� ciastka
	 * @param item Ciastka do zdeponowania
	 */
	public static void deposite(Player p, ItemStack item){
		int amount = item.getAmount();
		p.getInventory().remove(item);
		addCookies(p, amount);
	}
	
	/**
	 * Deponuje ciastka na konto gracza.
	 * @param uid UUID gracza kt�ry chce zdeponowa� ciastka.
	 * @param item Ciastka do zdeponowania
	 * @throws NullPointerException Gdy gracz o podanym UUID jest offline.
	 */
	public static void deposite(UUID uid, ItemStack item) throws NullPointerException{
		Player p = Bukkit.getPlayer(uid);
		deposite(p, item);
	}
	
	/**
	 * Deponuje ciastka na konto gracza.
	 * @param name Nazwa gracza kt�ry chce zdeponowa� ciastka.
	 * @param item Ciastka do zdeponowania
	 * @throws NullPointerException Gdy gracz o podanej nazwie jest offline.
	 */
	public static void deposite(String name, ItemStack item) throws NullPointerException{
		Player p = Bukkit.getPlayer(name);
		deposite(p, item);
	}
	
	/**
	 * Wy��cza plugin (U�ywa� awaryjnie w przypadku du�ych lag�w)
	 */
	public static void disable(){
		Bukkit.getPluginManager().disablePlugin(cb.getPlugin());
	}

}
