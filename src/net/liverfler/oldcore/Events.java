package net.liverfler.oldcore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

public class Events implements Listener {
	static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Oldcore");

	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent evt) {
		final String pname = evt.getPlayer().getName();
		ItemStack item = evt.getItemInHand();
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasDisplayName()){
				if(item.getItemMeta().getDisplayName().equals("§7§lOld§6§lGold§8§lBolck")){
					Utils.message(evt.getPlayer(), "Az §7§lOld§6§lGold§8§lBolck §r§2nem építkezésre való!");
					evt.setCancelled(true);
				}
			}
			
		}
		
		
		
    }
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent evt) {
		final String pname = evt.getPlayer().getName();
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();	
        	scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				Mysql.mysqlLoader(pname);
	    		return;
			}
		},20L);  
    Main.staffimport(evt.getPlayer());
    }
		

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerQuit(PlayerQuitEvent e) {
		Main.staffexport(e.getPlayer());
		Player p = e.getPlayer();
		String playername = p.getName();
		if(Main.tp.containsKey(playername)){
		try {
			Statement statement = Main.c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			ResultSet res = statement
					.executeQuery("SELECT * FROM playerdata WHERE PlayerName = '"
							+ playername + "';");
			res.next();
			res.updateInt("tp", Main.tp.get(playername));
			res.updateInt("szint", Main.szint.get(playername));
			res.updateInt("weektp", Main.weektp.get(playername));
			res.updateInt("warn", Main.warn.get(playername));
			res.updateInt("ora", Main.ora.get(playername));
			res.updateInt("perc", Main.perc.get(playername));

			Main.tp.remove(playername);
			Main.szint.remove(playername);
			Main.weektp.remove(playername);
			Main.warn.remove(playername);
			Main.ora.remove(playername);
			Main.perc.remove(playername);

			res.updateRow();
			statement.close();
		} catch (SQLException e1) {
			Main.log.log(Level.SEVERE, "[CUCORE]: Hiba: Mysql kurvaanyja kapcsolódásbiszbasz");
			e1.printStackTrace();
			}catch (NullPointerException ex){
				Main.log.log(Level.SEVERE, "[CUCORE]: Hiba: Mysql feltöltéskor és hash törléskor");
				
			}
		}
	}
}
