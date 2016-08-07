package net.liverfler.oldcore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import code.husky.mysql.MySQL;

public class Main extends JavaPlugin {
		
		
		static Logger log = Logger.getLogger("Minecraft");		
		//MYSQL ELÉRÉS  [][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]
		static MySQL MySQL = new MySQL(Bukkit.getServer().getPluginManager().getPlugin("Oldcore"), "mc2.infinityhosting.hu", "3306", "mcdb_2464", "mcdb_2464",						//
				"5b2aced07c");	

		static Connection c = null;																		//
		//[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]
	
		//LISTÁK! HASHMAPEK! Statikus változók![][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]
		public static YamlConfiguration config;
		public static ArrayList<String> servers = new ArrayList<String>();
		public static HashMap<String, Integer> playercount = new HashMap<String, Integer>();
		public static HashMap<String, Integer> tp = new HashMap<String, Integer>();		
		public static HashMap<String, Integer> szint = new HashMap<String, Integer>();
		public static HashMap<String, Integer> weektp = new HashMap<String, Integer>();
		public static HashMap<String, Integer> warn = new HashMap<String, Integer>();
		public static HashMap<String, Integer> ora = new HashMap<String, Integer>();
		public static HashMap<String, Integer> perc = new HashMap<String, Integer>();
		public static HashMap<String, Location> afk = new HashMap<String, Location>();
		public static HashMap<String, Integer> stafftimer = new HashMap<String, Integer>();
		
		//[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]
		private int taskId = 0;	
		
		private static Main instance;
		public static Main getInstance() {
			return instance;
		}

	
		public void onEnable() {
			instance = this;



			
			//MYSQL KAPCSOLÓDÁS
			try {
				c = MySQL.openConnection();
				getLogger().info("MYSQL kapcsolódás sikeres!");
			} catch (ClassNotFoundException | SQLException e) {
				Utils.info("§4MYSQL kapcsolódási hiba, Oldcore letiltva!");
			
				this.setEnabled(false);
			}
			
			// event regisztráció
			getServer().getPluginManager().registerEvents(new Events(), this);
			
			getServer().getPluginManager().registerEvents(new Chathandler(), this);
			getServer().getPluginManager().registerEvents(new Oldshop(), this);
			
			
			this.getCommand("oldcore").setExecutor(new Commander());
			
			
			
			for (Player p : Bukkit.getOnlinePlayers()) {
				String pname = p.getName();
				try {
					Statement statement = Main.c.createStatement();
					ResultSet res = statement.executeQuery("SELECT * FROM playerdata WHERE PlayerName = '"	+ pname + "';");
					
					if (res.next()) {
						final int tp = res.getInt("tp");
						final int szint = res.getInt("szint");
						final int weektp = res.getInt("weektp");
						final int warn = res.getInt("warn");
						
						final int ora = res.getInt("ora");
						final int perc = res.getInt("perc");
						
						Main.tp.put(pname, tp);
						Main.szint.put(pname, szint);
						Main.weektp.put(pname, weektp);
						Main.warn.put(pname, warn);
						Main.ora.put(pname, ora);
						Main.perc.put(pname, perc);
						
						statement.close();
					}else{
						statement.executeUpdate("INSERT INTO playerdata (`PlayerName`) VALUES ('"+ pname + "');");
						statement.close();
					}
					
				} catch (SQLException e) {
					Main.log.log(Level.SEVERE,
						"Mysql hiba a játékosok adatainak újratöltésekor (reload)!");
					e.printStackTrace();
				}
			}
		
	        
			net.liverfler.szavazas.Szavazas.vaneszavazas = false;
			net.liverfler.szavazas.Szavazas.Igen = 0;
			net.liverfler.szavazas.Szavazas.Nem = 0;
			Oldshop.enable();
			Clock.orajel();
			
			
			BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
			scheduler.scheduleSyncRepeatingTask((Plugin) this, new Runnable() {
				@SuppressWarnings("unused")
				@Override
				public void run() {
					Statement runst = null;
					try {
						runst = Main.c.createStatement();
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
			}, 0L, 400L);
		}
		
		public void onDisable() {
			log.info("Oldcore sikeresen leállt");
	
			getServer().getScheduler().cancelTask(taskId);
			taskId = 0;
			for (Player p : Bukkit.getOnlinePlayers()) {
				String playername = p.getName();
				if(Main.tp.containsKey(playername)){
				try {
					Statement statement = c.createStatement(
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					ResultSet res = statement
							.executeQuery("SELECT * FROM playerdata WHERE PlayerName = '"	+ playername + "';");
					res.next();
					res.updateInt("tp", Main.tp.get(playername));
					res.updateInt("szint", Main.szint.get(playername));
					res.updateInt("weektp", Main.weektp.get(playername));
					res.updateInt("warn", Main.warn.get(playername));
					res.updateInt("ora", Main.ora.get(playername));
					res.updateInt("perc", Main.perc.get(playername));
				
					res.updateRow();
					statement.close();
				} catch (SQLException e) {
					getLogger()
							.severe("§cMysql hiba a játékosok adatainak leálláskori mentésekor!");
					e.printStackTrace();
					}
				}
			}	
		}
		
		public static void topten(Player p){
			try {
				Statement statementtop = Main.c.createStatement();
				ResultSet res = statementtop.executeQuery("SELECT * FROM playerdata ORDER BY ora DESC LIMIT 10");
				String Players = "------------------------------------------------ \n§l§5Top 10 játékos: §r";
				
				if (res.next()) {Players = Players + " §2" + 1 + ".§r" + res.getString(1) + "  ";}
				if (res.next()) {Players = Players + " §2" + 2 + ".§r" + res.getString(1) + "\n  ";}
				if (res.next()) {Players = Players + " §2" + 3 + ".§r" + res.getString(1) + "  ";}
				if (res.next()) {Players = Players + " §2" + 4 + ".§r" + res.getString(1) + "  ";}
				if (res.next()) {Players = Players + " §2" + 5 + ".§r" + res.getString(1) + "\n  ";}
				if (res.next()) {Players = Players + " §2" + 6 + ".§r" + res.getString(1) + "  ";}
				if (res.next()) {Players = Players + " §2" + 7 + ".§r" + res.getString(1) + "  ";}
				if (res.next()) {Players = Players + " §2" + 8 + ".§r" + res.getString(1) + "\n  ";}
				if (res.next()) {Players = Players + " §2" + 9 + ".§r" + res.getString(1) + "  ";}
				if (res.next()) {Players = Players + " §2" + 10 + ".§r" + res.getString(1) + "  ";}
				Players = Players + "\n------------------------------------------------";
				statementtop.close();
				p.sendMessage(Players);
				
			} catch (SQLException e) {
				Main.log.log(Level.SEVERE,
					"Mysql hiba a játékosok adatainak újratöltésekor (reload)!");
				e.printStackTrace();
			}
			
			
		}
		
		public static void addstaff(String added, Player sender){
			String playerName = added;
	         File f = new File("plugins/Oldcore/Stafflog/" + playerName + ".yml");
	         FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

	         //When the player file is created for the first time...
	         if (!f.exists()) {
	             try {
	                 playerData.save(f);
	                 Utils.message(sender, added + " hozzáadva a staffloggerez!");
	             } catch (IOException exception) {

	                 exception.printStackTrace();
	             }
	         }else{
	        	 Utils.message(sender, added + " már hozzávan adva a staffloggerhez!");
	        	 
	        	 
	        	 
	         }
		}
		
		
		public static Boolean config (String conf){
			 
	         File f = new File("plugins/Oldcore/config.yml");
	         FileConfiguration config = YamlConfiguration.loadConfiguration(f);
	         if (!f.exists()) {
	             try {
	            	 config.set("Oldshop", false);
	                 config.save(f);
	                 
	             } catch (IOException exception) {

	                 exception.printStackTrace();
	             }
	         }
	         Boolean ret = config.getBoolean(conf);
	         
			return ret;
	      
	          
	         
	        
	        
		}
		
		
		public static void staffimport (Player p){
			 String playerName = p.getName();
	         File f = new File("plugins/Oldcore/Stafflog/" + playerName + ".yml");
	         FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);
	         
	         if (f.exists()) {
	             
	         Utils.info("Staffimport megtalálta a yml-t. ");
	         String date = Utils.date();
	         if(!playerData.contains(date)){
	        	 playerData.set("date", 0);
	        	 Main.stafftimer.put(playerName, playerData.getInt("date"));
	         }else{
	        	 Main.stafftimer.put(playerName, 0);

	         }
	        
	         }         
	         
	         Main.perc.put(playerName, playerData.getInt("torzsadat.perc"));
	         Main.tp.put(playerName, playerData.getInt("torzsadat.tp"));
	        
		}
		
		public static void staffexport (Player p){
			Utils.info("staffexport indul");
			if(Main.stafftimer.containsKey(p.getName())){
				Utils.info("staffexport stafftimerben megvan a játékos");
			
			String pname = p.getName();
			File f = new File("plugins/Oldcore/Stafflog/" + pname + ".yml");
	        FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);
	        String date = Utils.date();
			playerData.set(date, stafftimer.get(pname));


			
			try { 
	            playerData.save(f);
	        } catch (IOException exception) {
	            exception.printStackTrace();
	        }

			}
		}
		
		public static void staffjelentes(Player p){
			YamlConfiguration config = new YamlConfiguration();
			File folder = new File("plugins/Oldcore/Stafflog");
			File[] files = folder.listFiles();
			
			ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
			BookMeta bm = (BookMeta)book.getItemMeta();
			bm.setAuthor("Oldcore");
			bm.setTitle("Staffjelentés");
			
			
			
			
			for(File file : files){
				FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);
				
			
		
				
			
				String lista = file.getName();	
				for(int i=-1; i>-11; i--){
					
		              String date = Utils.dayago(i);
		              String adding;
		              if(!playerData.contains(date)){
		            	  adding = date + "-------";
				         }else{
				        	 String adder = playerData.getString(date);
				        	 adding = date + " - " +  adder;}
		              lista = lista + "\n" + adding;  }
				
				
				bm.addPage(lista);
				
				 
			}
			book.setItemMeta(bm);
			p.getInventory().addItem(book);
	
		}
		
		
		public static void staffymlconverter (){
			YamlConfiguration config = new YamlConfiguration();
			File folder = new File("plugins/Oldcore/Stafflog");
			
			
			
			File[] files = folder.listFiles();
			for(File file : files){
				FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);
				
				
				 String date = Utils.yesterdate();
		         if(!playerData.contains(date)){
		        	 
		        	 
		        	 playerData.set(date, "00;00@");
		        	
		         }else{
		        	 int minute = playerData.getInt(date);
		        	 
		        	 int ora =((int) (minute / 60));
		        	 int perc = minute % 60;
		        	 String strora = ora+ "";
		        	 String strperc = perc + "" ;
		        	 String finora = ora+ "";
		        	 String finperc = perc + "" ;
		        	 
		        	 
		        	 if(strora.length()==1){
		        		 finora="0"+strora;}
		        	 
		        	 if (strperc.length()==1){
		        		 finperc="0"+strperc;
		        	 }
		        	 
		        	 
		        	 playerData.set(date, finora + ";" + finperc);
		        	 
		        	 

		        
		         
		         }
		         try { 
			            playerData.save(file);
			        } catch (IOException exception) {
			            exception.printStackTrace();
			        }
		
			}
			
		}
}
