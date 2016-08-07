package net.liverfler.oldcore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class Utils {
	


	public static double calc(int tp, int szint) {
		double next = szint * 0.5 * szint * 600;
		return next;	
	}
	
	public static void info(String info) {
	
		Bukkit.getServer().getConsoleSender()
				.sendMessage("§b[Oldcore] §e" + info);
	}
	
	public static void message(Player p, String message) {
		
		p.sendMessage("§2§lOld-core: §r§2" + message);
	}
	

	
	public static String date(){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();

		String date = dateFormat.format(cal.getTime());
		
		return date;
		
	}
	public static String dateshort(){
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd");
		Calendar cal = Calendar.getInstance();

		String date = dateFormat.format(cal.getTime());
		
		return date;
		
	}
	
	public static String yesterdate(){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String date = dateFormat.format(cal.getTime());
		
		return date;
		
	}
	
	public static String dayago(int nappalezelott){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, nappalezelott);
		String date = dateFormat.format(cal.getTime());
		
		return date;
		
	}
	
	public static String hhmm(){
		
		DateFormat dateFormat = new SimpleDateFormat("HH/mm");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String date = dateFormat.format(cal.getTime());
		
		return date;
		
	}
	
public static String mm(){
		
		DateFormat dateFormat = new SimpleDateFormat("mm");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String date = dateFormat.format(cal.getTime());
		
		return date;
		
	}
	
	
	



	
}
