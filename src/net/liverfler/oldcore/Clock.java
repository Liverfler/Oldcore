package net.liverfler.oldcore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Clock {

	static String t = Utils.mm();
	static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Oldcore");
	
	 public static void orajel() {
	        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	        scheduler.scheduleSyncRepeatingTask(plugin, new Runnable(){

	            @Override
	            public void run() {
	                if (!Utils.mm().equals(Clock.t)) {
	                    Clock.t = Utils.mm();
	                    if (Utils.date("HH/mm").equals("00/00")){
	                    	
	                    }
	                    
	                
	                    for (Player p : Bukkit.getOnlinePlayers()) {
	                    	 String playername = p.getName();
	                    	if (!Playerdata.tp.containsKey(playername)) {
	                            Mysql.mysqlLoader(playername);
	                        }
	                    	
	                    	boolean isafk = false;
	                    	if(!Playerdata.afk.containsKey(playername)){
	                    		Playerdata.afk.put(playername, p.getLocation());
	                    	}
	                    	if(!Playerdata.afk.get(playername).equals(p.getLocation())){
	                    		isafk = true;
	                    	}
	                    	
	                    	
	                    	
	                    	
	                        int staffperc;
	                       
	                        if(isafk){
	                        
	                        	
	                        	if (Playerdata.stafftimer.containsKey(playername)) {
		                            staffperc = Playerdata.stafftimer.get(playername);
		                            Playerdata.stafftimer.put(playername, ++staffperc);
		                        }
		                        
		                        int tp = Playerdata.tp.get(playername);
		                        int ora = Playerdata.ora.get(playername);
		                        int perc = Playerdata.perc.get(playername);
		                        Playerdata.tp.put(playername, tp += 60);
		                        int weektp = Playerdata.weektp.get(playername);
		                        Playerdata.weektp.put(playername, weektp += 60);
		                        int szint = Playerdata.szint.get(playername);
		                        double next = Clock.calc(tp, szint);
		                        if (++perc >= 61) {
		                            perc = 0;
		                            Playerdata.ora.put(playername, ++ora);
		                        }
		                        Playerdata.perc.put(playername, perc);
		                        if ((double)tp < next) continue;
		                        Playerdata.szint.put(playername, ++szint);
		                        Playerdata.tp.put(playername, 0);
		                        p.sendMessage("§l[OLD-CORE]:§r Oldcore szintet léptél: " + szint + "§r §o(Több infó: /oldcore!");
	                        	
	                        	
	                        	
	                        	
	                        }
	                        
	                        
	                        
	                        
	                       
	                 
	                        
	                        
	                        
	                        
	                    }
	                }
	            }
	        }, 0, 200);
	    }
	
	
	
	
	 public static double calc(int tp, int szint) {
	        double next = (double)szint * 0.5 * (double)szint * 600.0;
	        return next;
	    }
	
}
