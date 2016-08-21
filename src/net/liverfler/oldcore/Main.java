package net.liverfler.oldcore;



import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.liverfler.oldshop.Oldshop;
import net.liverfler.szavazas.Szavazas;



public class Main extends JavaPlugin{

	
    
    //ÁLLAPOTJELZÕK
    public static boolean oldshop;
   
    
    
    //instance
    static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Oldcore");
    private static Main instance;
    public static Main getInstance() { return instance;  }
   
    
    
    
    @Override
    public void onEnable() {
    	
    	
    	
    	Bukkit.getServer().getConsoleSender().sendMessage("§b[Oldcore] §e" + "regisztálva");
    	
    	
    	instance = this;
    	this.getServer().getPluginManager().registerEvents((Listener)new Events(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new Oldshop(), (Plugin)this);
        this.getCommand("oldcore").setExecutor((CommandExecutor)new Commander());
        this.getCommand("oldcore").setTabCompleter((TabCompleter)new TabCompleteExplain());
        
        
        Szavazas.vaneszavazas = false;
        Szavazas.Igen = 0;
        Szavazas.Nem = 0;
        
        Config.enable();
        Clock.orajel();
        
    	Oldshop.inditas();
    	Mysql.enable();
        
        
    }
    
    
    @Override
    public void onDisable() {
    	
    }
    
    /*public static void pluginenable(Boolean ontrueofffalse){
    	if(ontrueofffalse){
    		
    	}else{
    		plugin.onDisable();
    	}
    	
    }
    
    */
    
    
    
    
    
    
    
   
	
	
}
