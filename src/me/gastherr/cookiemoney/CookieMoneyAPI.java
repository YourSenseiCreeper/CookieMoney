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
	 * Pobiera ilosc ciastek.
	 * @param name Nazwa gracza
	 * @return Ilosc ciastek gracza
	 */
	public static int getCookies(String name){
		Player p = Bukkit.getPlayer(name);
		return getCookies(p.getUniqueId());	
	}
	
	/**
	 * Pobiera ilosc ciastek.
	 * @param uid UUID gracza
	 * @return Ilosc ciastek gracza
	 */
	public static int getCookies(UUID uid){
		
		if(cb.getPlayers().containsKey(uid)){
			return cb.getPlayers().get(uid);
		}else{
			return 0;
		}
		
	}
	
	/**
	 * Pobiera ilosc ciastek.
	 * @param p Gracz od którego pobierana jest ilosc ciastek
	 * @return Ilosc ciastek gracza p
	 */
	public static int getCookies(Player p){
		return getCookies(p.getUniqueId());
	}
	
	/**
	 * Ustawia ilosc ciastek dla gracza p.
	 * @param p Gracz
	 * @param amount Ilosc ciastek do ustawienia
	 */
	public static void setCookies(Player p, int amount){
		setCookies(p.getUniqueId(), amount);
	}
	
	/**
	 * Ustawia ilosc ciastek dla gracza z podanym UUID.
	 * @param uid UUID gracza
	 * @param amount Ilosc ciastek do ustawienia
	 */
	public static void setCookies(UUID uid, int amount){
		if (cb.getPlayers().containsKey(uid)){
			cb.getPlayers().replace(uid, amount);
		}
	}
	
	/**
	 * Ustawia ilosc ciastek dla gracza o podanej nazwie.
	 * @param name Nazwa gracza
	 * @param amount Ilosc ciastek do ustawienia
	 */
	public static void setCookies(String name, int amount){
		Player p = Bukkit.getPlayer(name);
		setCookies(p.getUniqueId(), amount);
	}
	
	/**
	 * Dodaje ilosc ciastek amount do ogólnej liczby ciastek gracza p.
	 * @param p Gracz któremu dodawane s¹ ciastka
	 * @param amount Ilosc ciastek do dodania
	 * @return Ilosc ciastek gracza po dodaniu.
	 */
	public static int addCookies(Player p, int amount){
		int ret = getCookies(p.getUniqueId())+amount;
		setCookies(p, ret);
		return ret;
	}
	
	/**
	 * Dodaje ilosc ciastek amount do ogolnej liczby ciastek gracza z UUId uid.
	 * @param uid UUID gracza ktoremu dodawane s¹ ciastka
	 * @param amount Ilosc ciastek do dodania
	 * @return Ilosc ciastek gracza po dodaniu.
	 */
	public static int addCookies(UUID uid, int amount){
		int ret = getCookies(uid)+amount;
		setCookies(uid, ret);
		return ret;
	}
	
	/**
	 * Dodaje ilosc ciastek amount do ogolnej liczby ciastek gracza z nazw¹ name.
	 * @param name Nazwa gracza ktoremu dodawane s¹ ciastka
	 * @param amount Ilosc ciastek do dodania
	 * @return Ilosc ciastek gracza po dodaniu.
	 */
	public static int addCookies(String name, int amount){
		int ret = getCookies(name)+amount;
		setCookies(name, ret);
		return ret;
	}
	
	/**
	 * Usuwa ciastka z konta gracza.
	 * @param p Gracz z którego konta odejmujemy
	 * @param amount Liczba ciastek do odjecia
	 * @return Aktualna liczba ciastek na koncie gracza
	 */
	public static int removeCookies(Player p, int amount){
		int ret = getCookies(p)-amount;
		if (ret < 0) return 0;
		setCookies(p, ret);
		return ret;
	}
	
	/**
	 * Usuwa ciastka z konta gracza.
	 * @param uid UUID gracza z którego konta odejmujemy
	 * @param amount Liczba ciastek do odjecia
	 * @return Aktualna liczba ciastek na koncie gracza
	 */
	public static int removeCookies(UUID uid, int amount){
		int ret = getCookies(uid)-amount;
		if (ret < 0) return 0;
		setCookies(uid, ret);
		return ret;
	}
	
	/**
	 * Usuwa ciastka z konta gracza.
	 * @param name Nazwa gracza z którego konta odejmujemy
	 * @param amount Liczba ciastek do odjecia
	 * @return Aktualna liczba ciastek na koncie gracza
	 */
	public static int removeCookies(String name, int amount){
		int ret = getCookies(name)-amount;
		if (ret < 0) return 0;
		setCookies(name, ret);
		return ret;
	}
	
	/**
	 * Deponuje ciastka na konto gracza.
	 * @param p Gracz który chce zdeponowac ciastka
	 * @param item Ciastka do zdeponowania
	 */
	public static void deposite(Player p, ItemStack item){
		int amount = item.getAmount();
		p.getInventory().remove(item);
		addCookies(p, amount);
	}
	
	/**
	 * Deponuje ciastka na konto gracza.
	 * @param uid UUID gracza który chce zdeponowac ciastka.
	 * @param item Ciastka do zdeponowania
	 * @throws NullPointerException Gdy gracz o podanym UUID jest offline.
	 */
	public static void deposite(UUID uid, ItemStack item) throws NullPointerException{
		Player p = Bukkit.getPlayer(uid);
		deposite(p, item);
	}
	
	/**
	 * Deponuje ciastka na konto gracza.
	 * @param name Nazwa gracza ktory chce zdeponowac ciastka.
	 * @param item Ciastka do zdeponowania
	 * @throws NullPointerException Gdy gracz o podanej nazwie jest offline.
	 */
	public static void deposite(String name, ItemStack item) throws NullPointerException{
		Player p = Bukkit.getPlayer(name);
		deposite(p, item);
	}
	
	/**
	 * Wylacza plugin (Uzywac awaryjnie w przypadku duzych lagow)
	 */
	public static void disable(){
		Bukkit.getPluginManager().disablePlugin(cb.getPlugin());
	}

}
