package net.liverfler.oldcore;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import code.husky.mysql.MySQL;


public class Mysql {
	public static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Oldcore");
	
	//MYSQL
    static MySQL MySQL;
    static Connection c = null;
    public static HashMap<String, String> mysqldata = new HashMap<String, String>();
    
    
    
    public static void enable(){

               try {
                   c = MySQL.openConnection();
                   Utils.consoleinfo("MYSQL kapcsolódás sikeres!");
               }
               catch (ClassNotFoundException | SQLException e) {
            	   Utils.consolealert("§4MYSQL kapcsolódási hiba, Oldcore letiltva!");

               }
               for (Player p : Bukkit.getOnlinePlayers()) {
                   String pname = p.getName();
                   try {
                       Statement statement = c.createStatement();
                       ResultSet res = statement.executeQuery("SELECT * FROM playerdata WHERE PlayerName = '" + pname + "';");
                       if (res.next()) {
                           int tp = res.getInt("tp");
                           int szint = res.getInt("szint");
                           int weektp = res.getInt("weektp");
                           int warn = res.getInt("warn");
                           int ora = res.getInt("ora");
                           int perc = res.getInt("perc");
                           Playerdata.tp.put(pname, tp);
                           Playerdata.szint.put(pname, szint);
                           Playerdata.weektp.put(pname, weektp);
                           Playerdata.warn.put(pname, warn);
                           Playerdata.ora.put(pname, ora);
                           Playerdata.perc.put(pname, perc);
                           statement.close();
                           continue;
                       }
                       statement.executeUpdate("INSERT INTO playerdata (`PlayerName`) VALUES ('" + pname + "');");
                       statement.close();
                       continue;
                   }
                   catch (SQLException e) {

                       Utils.consolealert("Mysql hiba a játékosok adatainak újratöltésekor (reload)!");
                       e.printStackTrace();
                   }
               }
               BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
               scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
       			@SuppressWarnings("unused")
       			@Override
       			public void run() {
       				Statement runst = null;
       				try {
       					runst = c.createStatement();
       					ResultSet runres = runst.executeQuery("SELECT 1;");
       				} catch (SQLException e) {

       					e.printStackTrace();
       				} finally {
       					try {
       						runst.close();
       					} catch (SQLException e) {

       						e.printStackTrace();
       					}
       				}
       			}
       		}, 20L, 400L);   
               
               

   
    
    }
    
    public static void mysqldelete(String pname){
    	 try { 
         	
             Statement statement = Mysql.c.createStatement(1005, 1008);
        
             
             ResultSet res = statement.executeQuery("SELECT * FROM playerdata WHERE PlayerName = '" + pname + "';");
             res.deleteRow();
             
             
         }
         catch (SQLException e) {
             Utils.consolealert("Mysql hiba a játékos adatainak újratötésekor (mysqlLoader)!");
             e.printStackTrace();
         }
    }
    
    public static void mysqlLoader(String pname) {
       
        	 
            try { 
            	
                Statement statement = Mysql.c.createStatement(1005, 1008);
                ResultSet res = statement.executeQuery("SELECT * FROM playerdata WHERE PlayerName = '" + pname + "';");
                if (res.next()) {
                	Utils.consoleinfo("resnext true");
                    int tp = res.getInt("tp");
                    int szint = res.getInt("szint");
                    int weektp = res.getInt("weektp");
                    int warn = res.getInt("warn");
                    int ora = res.getInt("ora");
                    int perc = res.getInt("perc");
                    Playerdata.tp.put(pname, tp);
                    Playerdata.szint.put(pname, szint);
                    Playerdata.weektp.put(pname, weektp);
                    Playerdata.warn.put(pname, warn);
                    Playerdata.ora.put(pname, ora);
                    Playerdata.perc.put(pname, perc);
                    statement.close();
                } else {
                    statement.executeUpdate("INSERT INTO playerdata (`PlayerName`) VALUES ('" + pname + "');");
                    statement.close();
                    Playerdata.tp.put(pname, 0);
                    Playerdata.szint.put(pname, 1);
                    Playerdata.weektp.put(pname, 0);
                    Playerdata.warn.put(pname, 0);
                    Playerdata.ora.put(pname, 0);
                    Playerdata.perc.put(pname, 0);
                }
                Utils.consoleinfo("Mysql adatok importálása" + pname);
            }
            catch (SQLException e) {
                Utils.consolealert("Mysql hiba a játékos adatainak újratötésekor (mysqlLoader)!");
                e.printStackTrace();
            }
        
    }
    
    
    public static Integer mysqlLoadInt(String pname, String path) {
        try {
            Statement statement = Mysql.c.createStatement(1005, 1008);
            ResultSet res = statement.executeQuery("SELECT * FROM playerdata WHERE PlayerName = '" + pname + "';");
            if (res.next()) {
                Integer i = res.getInt(path);
                statement.close();
                return i;
            }
            statement.close();
            return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            Integer a = -1;
            return a;
        }
    }
    
    public static boolean mysqlSaveInt(String pname, String path, int value) {
        try {
            Statement statement = Mysql.c.createStatement(1005, 1008);
            ResultSet res = statement.executeQuery("SELECT * FROM playerdata WHERE PlayerName = '" + pname + "';");
            res.next();
            res.updateInt(path, value);
            res.updateRow();
            statement.close();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static String topten() {
        String Players = "------------------------------------------------ \n§l§5Top 10 játékos: §r";
        try {
            Statement statementtop = c.createStatement();
            ResultSet res = statementtop.executeQuery("SELECT * FROM playerdata ORDER BY ora DESC LIMIT 10");
            if (res.next()) {
                Players = String.valueOf(Players) + " §2" + 1 + ".§r" + res.getString(1) + "  ";
            }
            if (res.next()) {
                Players = String.valueOf(Players) + " §2" + 2 + ".§r" + res.getString(1) + "\n  ";
            }
            if (res.next()) {
                Players = String.valueOf(Players) + " §2" + 3 + ".§r" + res.getString(1) + "  ";
            }
            if (res.next()) {
                Players = String.valueOf(Players) + " §2" + 4 + ".§r" + res.getString(1) + "  ";
            }
            if (res.next()) {
                Players = String.valueOf(Players) + " §2" + 5 + ".§r" + res.getString(1) + "\n  ";
            }
            if (res.next()) {
                Players = String.valueOf(Players) + " §2" + 6 + ".§r" + res.getString(1) + "  ";
            }
            if (res.next()) {
                Players = String.valueOf(Players) + " §2" + 7 + ".§r" + res.getString(1) + "  ";
            }
            if (res.next()) {
                Players = String.valueOf(Players) + " §2" + 8 + ".§r" + res.getString(1) + "\n  ";
            }
            if (res.next()) {
                Players = String.valueOf(Players) + " §2" + 9 + ".§r" + res.getString(1) + "  ";
            }
            if (res.next()) {
                Players = String.valueOf(Players) + " §2" + 10 + ".§r" + res.getString(1) + "  ";
            }
            Players = String.valueOf(Players) + "\n------------------------------------------------";
            statementtop.close();
        }
        catch (SQLException e) {
        	Utils.consolealert("Mysql hiba a játékos adatainak újratötésekor (top ten)!");
            e.printStackTrace();
        }
        return Players;
    }
    
   
    
    
    
}
