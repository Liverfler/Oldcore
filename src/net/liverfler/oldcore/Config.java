package net.liverfler.oldcore;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import code.husky.mysql.MySQL;

public class Config {

	
	
	
	public static void enable(){
		
		
        File f = new File("plugins/Oldcore/config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration((File)f);
        
        
        
        try {
            if (!config.contains("Oldshop")) {
                config.set("Oldshop", (Object)false);
                Main.oldshop = false;
            } else {
            	Main.oldshop = config.getBoolean("Oldshop");
            }
            if (!config.contains("Mysql.mysql")) {
               
                config.set("Mysql.cim", (Object)"pelda.peldacim.hu");
                config.set("Mysql.port", (Object)"3306");
                config.set("Mysql.database", (Object)"database");
                config.set("Mysql.felhnev", (Object)"mcdbname");
                config.set("Mysql.jelszo", (Object)"jelszo");
                
            } else {
                
                config.getBoolean("Mysql.mysql");
                int in = config.getInt("Mysql.port");
                Mysql.mysqldata.put("cim", (String)config.get("Mysql.cim"));
                Mysql.mysqldata.put("port", Integer.toString(in));
                Mysql.mysqldata.put("database", (String)config.get("Mysql.database"));
                Mysql.mysqldata.put("felhnev", (String)config.get("Mysql.felhnev"));
                Mysql.mysqldata.put("jelszo", (String)config.get("Mysql.jelszo"));
            }
            Mysql.MySQL = new MySQL(Bukkit.getServer().getPluginManager().getPlugin("Oldcore"), Mysql.mysqldata.get("cim"), Mysql.mysqldata.get("port"), Mysql.mysqldata.get("database"), Mysql.mysqldata.get("felhnev"), Mysql.mysqldata.get("jelszo"));
            config.save(f);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        
        
        
        
        
        
	}
	
	
	public static void disable(){
		
	}
	
	
	public static void staffymlconverter() {
        
        File folder = new File("plugins/Oldcore/Stafflog");
        File[] arrfile = folder.listFiles();
        int n = arrfile.length;
        int n2 = 0;
        while (n2 < n) {
            String date;
            File file = arrfile[n2];
            YamlConfiguration playerData = YamlConfiguration.loadConfiguration((File)file);
            if (!playerData.contains(date = Utils.yesterdate())) {
                playerData.set(date, (Object)"00;00@");
            } else {
                int minute = playerData.getInt(date);
                int ora = minute / 60;
                int perc = minute % 60;
                String strora = String.valueOf(ora);
                String strperc = String.valueOf(perc);
                String finora = String.valueOf(ora);
                String finperc = String.valueOf(perc);
                if (strora.length() == 1) {
                    finora = "0" + strora;
                }
                if (strperc.length() == 1) {
                    finperc = "0" + strperc;
                }
                playerData.set(date, (Object)(String.valueOf(finora) + ";" + finperc));
            }
            try {
                playerData.save(file);
            }
            catch (IOException exception) {
                exception.printStackTrace();
            }
            ++n2;
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
