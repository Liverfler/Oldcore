/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginManager
 *  org.bukkit.scheduler.BukkitScheduler
 */
package net.liverfler.szavazas;

import java.util.ArrayList;
import java.util.HashMap;
import net.liverfler.oldcore.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Szavazas {
    public static HashMap<String, Integer> playercount = new HashMap<String, Integer>();
    static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Oldcore");
    public static ArrayList<Player> Szavazok = new ArrayList<Player>();
    public static Boolean vaneszavazas;
    public static int Igen;
    public static int Nem;

    public static void szavazas(Player p, Boolean vote) {
        if (vaneszavazas.booleanValue()) {
            if (Szavazok.contains((Object)p)) {
                Utils.message(p, "Te m�r szavazt�l!");
            } else if (vote.booleanValue()) {
                ++Igen;
                Utils.message(p, "�2Igennel szavazt�l!");
            } else {
                ++Nem;
                Utils.message(p, "�2Nemmel szavazt�l!");
                
            }
            Szavazok.add(p);
        } else {
            Utils.message(p, "Jelenleg nem folyik szavaz�s!");
        }
    }

    public static void szavazinditas(String kerdes) {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        vaneszavazas = true;
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable(){

            @Override
            public void run() {
                Bukkit.broadcastMessage((String)"�3---------------------------------");
                Bukkit.broadcastMessage((String)("�2�lOld-core szavaz�s: �3�l " + kerdes));
                Bukkit.broadcastMessage((String)"    Szavaz�s: �2/oldcore igen   �4/oldcore nem");
                Bukkit.broadcastMessage((String)"    60 m�sodpercen bel\u00fcl z�rul");
                Bukkit.broadcastMessage((String)("    �2Igen:[" + Szavazas.Igen + "]   �4Nem:[" + Szavazas.Nem + "]"));
                Bukkit.broadcastMessage((String)"�3---------------------------------");
            }
        }, 1);
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable(){

            @Override
            public void run() {
                Bukkit.broadcastMessage((String)"�3---------------------------------");
                Bukkit.broadcastMessage((String)("�2�lOld-core szavaz�s: �3�l " + kerdes));
                Bukkit.broadcastMessage((String)"    Szavaz�s: �2/oldcore igen   �4/oldcore nem");
                Bukkit.broadcastMessage((String)"    45 m�sodpercen bel\u00fcl z�rul");
                Bukkit.broadcastMessage((String)("    �2Igen:[" + Szavazas.Igen + "]   �4Nem:[" + Szavazas.Nem + "]"));
                Bukkit.broadcastMessage((String)"�3---------------------------------");
            }
        }, 300);
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable(){

            @Override
            public void run() {
                Bukkit.broadcastMessage((String)"�3---------------------------------");
                Bukkit.broadcastMessage((String)("�2�lOld-core szavaz�s: �3�l " + kerdes));
                Bukkit.broadcastMessage((String)"    Szavaz�s: �2/oldcore igen   �4/oldcore nem");
                Bukkit.broadcastMessage((String)"    30 m�sodpercen bel\u00fcl z�rul");
                Bukkit.broadcastMessage((String)("    �2Igen:[" + Szavazas.Igen + "]   �4Nem:[" + Szavazas.Nem + "]"));
                Bukkit.broadcastMessage((String)"�3---------------------------------");
            }
        }, 600);
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable(){

            @Override
            public void run() {
                Bukkit.broadcastMessage((String)"�3---------------------------------");
                Bukkit.broadcastMessage((String)("�2�lOld-core szavaz�s: �3�l " + kerdes));
                Bukkit.broadcastMessage((String)"    Szavaz�s: �2/oldcore igen   �4/oldcore nem");
                Bukkit.broadcastMessage((String)"    20 m�sodpercen bel\u00fcl z�rul");
                Bukkit.broadcastMessage((String)("    �2Igen:[" + Szavazas.Igen + "]   �4Nem:[" + Szavazas.Nem + "]"));
                Bukkit.broadcastMessage((String)"�3---------------------------------");
            }
        }, 800);
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable(){

            @Override
            public void run() {
                Bukkit.broadcastMessage((String)"�3---------------------------------");
                Bukkit.broadcastMessage((String)("�2�lOld-core szavaz�s: �3�l " + kerdes));
                Bukkit.broadcastMessage((String)"    Szavaz�s: �2/oldcore igen   �4/oldcore nem");
                Bukkit.broadcastMessage((String)"    10 m�sodpercen bel\u00fcl z�rul");
                Bukkit.broadcastMessage((String)("    �2Igen:[" + Szavazas.Igen + "]   �4Nem:[" + Szavazas.Nem + "]"));
                Bukkit.broadcastMessage((String)"�3---------------------------------");
            }
        }, 1000);
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable(){

            @Override
            public void run() {
                Bukkit.broadcastMessage((String)"�3---------------------------------");
                Bukkit.broadcastMessage((String)("�2�lOld-core szavaz�s: �3�l " + kerdes));
                Bukkit.broadcastMessage((String)"    Szavaz�s: �2/oldcore igen   �4/oldcore nem");
                Bukkit.broadcastMessage((String)"    5 m�sodpercen bel\u00fcl z�rul");
                Bukkit.broadcastMessage((String)("    �2Igen:[" + Szavazas.Igen + "]   �4Nem:[" + Szavazas.Nem + "]"));
                Bukkit.broadcastMessage((String)"�3---------------------------------");
            }
        }, 1100);
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable(){

            @Override
            public void run() {
                Bukkit.broadcastMessage((String)"�3---------------------------------");
                Bukkit.broadcastMessage((String)"�2�lOld-core szavaz�s lez�rult");
                Bukkit.broadcastMessage((String)("    �3�l" + kerdes));
                Bukkit.broadcastMessage((String)("    �2Igennel szavazott: �3�o[" + Szavazas.Igen + "]"));
                Bukkit.broadcastMessage((String)("    �4Nemmel szavazott: �3�o[" + Szavazas.Nem + "]"));
                Bukkit.broadcastMessage((String)"�3---------------------------------");
                Szavazas.vaneszavazas = false;
                Szavazas.Szavazok.clear();
                Szavazas.Nem = 0;
                Szavazas.Igen = 0;
            }
        }, 1200);
    }

}

