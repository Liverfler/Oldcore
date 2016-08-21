/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.event.player.PlayerJoinEvent
 *  org.bukkit.event.player.PlayerQuitEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginManager
 *  org.bukkit.scheduler.BukkitScheduler
 */
package net.liverfler.oldcore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import net.liverfler.oldcore.Mysql;
import net.liverfler.oldcore.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Events
implements Listener {
    static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Oldcore");

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent evt) {
        ItemStack item = evt.getItemInHand();
        if (item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("§7§lOld§6§lGold§8§lBolck")) {
            Utils.message(evt.getPlayer(), "Az §7§lOld§6§lGold§8§lBolck §r§2nem építkezésre való!");
            evt.setCancelled(true);
        }
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent evt) {
        final String pname = evt.getPlayer().getName();
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable(){

            @Override
            public void run() {
            	               
                Mysql.mysqlLoader(pname);
            }
        }, 20);
        Stafflogger.staffimport(evt.getPlayer());
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent e) {
        Stafflogger.staffexport(e.getPlayer());
        Player p = e.getPlayer();
        String playername = p.getName();
        if (Playerdata.tp.containsKey(playername)) {
            try {
                Statement statement = Mysql.c.createStatement(1005, 1008);
                ResultSet res = statement.executeQuery("SELECT * FROM playerdata WHERE PlayerName = '" + playername + "';");
                res.next();
                res.updateInt("tp", (int)Playerdata.tp.get(playername));
                res.updateInt("szint", (int)Playerdata.szint.get(playername));
                res.updateInt("weektp", (int)Playerdata.weektp.get(playername));
                res.updateInt("warn", (int)Playerdata.warn.get(playername));
                res.updateInt("ora", (int)Playerdata.ora.get(playername));
                res.updateInt("perc", (int)Playerdata.perc.get(playername));
                Playerdata.tp.remove(playername);
                Playerdata.szint.remove(playername);
                Playerdata.weektp.remove(playername);
                Playerdata.warn.remove(playername);
                Playerdata.ora.remove(playername);
                Playerdata.perc.remove(playername);
                res.updateRow();
                statement.close();
            }
            catch (SQLException e1) {
                
                Utils.consolealert("Hiba: Mysql kurvaanyja kapcsolódásbiszbasz");
                e1.printStackTrace();
            }
            catch (NullPointerException ex) {
            	Utils.consolealert("Hiba: Mysql feltöltéskor és hash törléskor");
           
            }
        }
    }

}

