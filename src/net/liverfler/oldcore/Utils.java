package net.liverfler.oldcore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Utils {
	public static void consoleinfo(String info){
		Bukkit.getServer().getConsoleSender().sendMessage("§b[Oldcore] §e" + info);
	}
	
	public static void consolealert(String alert){
		Bukkit.getServer().getConsoleSender().sendMessage("§r[Oldcore ERROR] §e" + alert);
		
	}
	
	 public static String mm() {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("mm");
	        Calendar cal = Calendar.getInstance();
	        cal.add(5, -1);
	        String date = dateFormat.format(cal.getTime());
	        return date;
	    }
	 
	 public static String date(String yyyyperMMperddperHHpermm){
		 SimpleDateFormat dateFormat = new SimpleDateFormat(yyyyperMMperddperHHpermm);
		 Calendar cal = Calendar.getInstance();
		 cal.add(5, -1);
		 String date = dateFormat.format(cal.getTime()); 		 
		 return date;
		 
	 }
	 
	 public static String yesterdate() {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	        Calendar cal = Calendar.getInstance();
	        cal.add(5, -1);
	        
	        String date = dateFormat.format(cal.getTime());
	        return date;
	    }
	 
	 public static String dayago(int nappalezelott) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	        Calendar cal = Calendar.getInstance();
	        cal.add(5, nappalezelott);
	        String date = dateFormat.format(cal.getTime());
	        return date;
	    }
	  public static void message(Player p, String message) {
	        p.sendMessage("§2§lOld-core: §r§2" + message);
	    }
	
}
