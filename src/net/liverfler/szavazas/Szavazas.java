package net.liverfler.szavazas;

import java.util.ArrayList;
import java.util.HashMap;

import net.liverfler.oldcore.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Szavazas {public static HashMap<String, Integer> playercount = new HashMap<String, Integer>();
	static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Oldcore");
	public static ArrayList<Player> Szavazok = new ArrayList<Player>();
	public static Boolean vaneszavazas;
	public static int Igen;
	public static int Nem;
	
	public static void szavazas (Player p, Boolean vote){
		if(vaneszavazas){
			
		
		
		if(Szavazok.contains(p)){
			Utils.message(p, "Te m�r szavazt�l!");
		}else{
			if(vote){
				Igen++;
				Utils.message(p, "�2Igennel szavazt�l!");
			}else{
				Nem++;
				Utils.message(p, "�2Nemmel szavazt�l!");
			}
		}
		
		
		Szavazok.add(p);
		}else{
			Utils.message(p, "Jelenleg nem folyik szavaz�s!");
		}
	}
	
	public static void szavazinditas (String kerdes){
		
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		vaneszavazas = true;

    	scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
		@Override
		public void run() {
			Bukkit.broadcastMessage("�3---------------------------------");
			Bukkit.broadcastMessage("�2�lOld-core szavaz�s: �3�l " + kerdes);
			Bukkit.broadcastMessage("    Szavaz�s: �2/oldcore igen   �4/oldcore nem");
			Bukkit.broadcastMessage("    60 m�sodpercen bel�l z�rul");
			Bukkit.broadcastMessage("    �2Igen:[" + Igen + "]   �4Nem:[" + Nem + "]");
			Bukkit.broadcastMessage("�3---------------------------------");
			return;
		}},1L);
    	
    	scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
    		@Override
    		public void run() {
    			Bukkit.broadcastMessage("�3---------------------------------");
    			Bukkit.broadcastMessage("�2�lOld-core szavaz�s: �3�l " + kerdes);
    			Bukkit.broadcastMessage("    Szavaz�s: �2/oldcore igen   �4/oldcore nem");
    			Bukkit.broadcastMessage("    45 m�sodpercen bel�l z�rul");
    			Bukkit.broadcastMessage("    �2Igen:[" + Igen + "]   �4Nem:[" + Nem + "]");
    			Bukkit.broadcastMessage("�3---------------------------------");
    		return;
    			
    		}},300L);
    	
    	scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
    		@Override
    		public void run() {
    			Bukkit.broadcastMessage("�3---------------------------------");
    			Bukkit.broadcastMessage("�2�lOld-core szavaz�s: �3�l " + kerdes);
    			Bukkit.broadcastMessage("    Szavaz�s: �2/oldcore igen   �4/oldcore nem");
    			Bukkit.broadcastMessage("    30 m�sodpercen bel�l z�rul");
    			Bukkit.broadcastMessage("    �2Igen:[" + Igen + "]   �4Nem:[" + Nem + "]");
    			Bukkit.broadcastMessage("�3---------------------------------");
    		return;
    			
    		}},600L);

    	
    	scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
    		@Override
    		public void run() {
    			Bukkit.broadcastMessage("�3---------------------------------");
    			Bukkit.broadcastMessage("�2�lOld-core szavaz�s: �3�l " + kerdes);
    			Bukkit.broadcastMessage("    Szavaz�s: �2/oldcore igen   �4/oldcore nem");
    			Bukkit.broadcastMessage("    20 m�sodpercen bel�l z�rul");
    			Bukkit.broadcastMessage("    �2Igen:[" + Igen + "]   �4Nem:[" + Nem + "]");
    			Bukkit.broadcastMessage("�3---------------------------------");
    		return;
    			
    		}},800L);

    	
    	scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
    		@Override
    		public void run() {
    			Bukkit.broadcastMessage("�3---------------------------------");
    			Bukkit.broadcastMessage("�2�lOld-core szavaz�s: �3�l " + kerdes);
    			Bukkit.broadcastMessage("    Szavaz�s: �2/oldcore igen   �4/oldcore nem");
    			Bukkit.broadcastMessage("    10 m�sodpercen bel�l z�rul");
    			Bukkit.broadcastMessage("    �2Igen:[" + Igen + "]   �4Nem:[" + Nem + "]");
    			Bukkit.broadcastMessage("�3---------------------------------");
    		return;
    			
    		}},1000L);
    	
    	scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
    		@Override
    		public void run() {
    			Bukkit.broadcastMessage("�3---------------------------------");
    			Bukkit.broadcastMessage("�2�lOld-core szavaz�s: �3�l " + kerdes);
    			Bukkit.broadcastMessage("    Szavaz�s: �2/oldcore igen   �4/oldcore nem");
    			Bukkit.broadcastMessage("    5 m�sodpercen bel�l z�rul");
    			Bukkit.broadcastMessage("    �2Igen:[" + Igen + "]   �4Nem:[" + Nem + "]");
    			Bukkit.broadcastMessage("�3---------------------------------");
    		return;
    			
    		}},1100L);
    	
    	scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
    		@Override
    		public void run() {
    			Bukkit.broadcastMessage("�3---------------------------------");
    			Bukkit.broadcastMessage("�2�lOld-core szavaz�s lez�rult");
    			Bukkit.broadcastMessage("    �3�l"+ kerdes);
    			Bukkit.broadcastMessage("    �2Igennel szavazott: �3�o["+ Igen +"]");
    			Bukkit.broadcastMessage("    �4Nemmel szavazott: �3�o["+ Nem +"]");
    			Bukkit.broadcastMessage("�3---------------------------------");
    			vaneszavazas = false;
    			Szavazas.Szavazok.clear();
    			Nem = 0;
    			Igen = 0;
    		return;
    			
    		}},1200L);
		
		
	}
	
}
