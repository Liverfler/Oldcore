package net.liverfler.oldcore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;



public class Clock {
	static String t = Utils.mm();
	static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Oldcore");
	public static void orajel () {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				if(!Utils.mm().equals(t)){
					t = Utils.mm();
				
				
				
				if(Utils.hhmm().equals("00/00")){
					Main.staffymlconverter();
				}
				
				
				for (Player p : Bukkit.getOnlinePlayers()) {
					String playername = p.getName();
					boolean check = false;
					if(Main.afk.containsKey(playername)){
						if(Main.afk.get(playername).equals(p.getLocation())){check = false;	
						}else{	check = true;	}
					}else{	check = true;}
					
					Main.afk.put(playername, p.getLocation());
					if(Main.stafftimer.containsKey(playername)){
						int staffperc = Main.stafftimer.get(playername);
						staffperc += 1;
						Main.stafftimer.put(playername, staffperc);
					}
					if(check){
						
						if(Main.stafftimer.containsKey(playername)){
							int staffperc = Main.stafftimer.get(playername);
							staffperc += 1;
							Main.stafftimer.put(playername, staffperc);
						}
						
						if(!Main.tp.containsKey(playername)){
							Mysql.mysqlLoader(playername);
						}
						int tp = Main.tp.get(playername);
						
						int ora = Main.ora.get(playername);
						
						
						int perc = Main.perc.get(playername);
						tp += 60;
						perc += 1;
						Main.tp.put(playername, tp);
						
						int weektp = Main.weektp.get(playername);
						weektp = (weektp + 60);
						Main.weektp.put(playername, weektp);
						
						
						
						
						 int szint = Main.szint.get(playername);
						 
						 
						 double next = Utils.calc(tp, szint);
						 
						 if (perc >= 61){
							 ora += 1;
							 perc = 0;
							 Main.ora.put(playername, ora);
					
						 }
					
						 Main.perc.put(playername, perc);
						 
						
						 if(tp >= next){
							 szint = (szint + 1);
							Main.szint.put(playername, szint);
							Main.tp.put(playername, 0);
							//Levelcommand.levelcommand(playername, szint);
							p.sendMessage("§l[OLD-CORE]:§r Oldcore szintet léptél: " + szint + "§r §o(Több infó: /oldcore!");
							//Levelcommand.levelcommand(playername, szint);						 
							}
					}
				}
			}
			}
		}, 0L, 200L);
	}
}
