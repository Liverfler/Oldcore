package net.liverfler.oldcore;

import java.io.File;
import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class Stafflogger {
	public static boolean addstaff(String added) {
        Boolean correct = false;
        String playerName = added;
        File f = new File("plugins/Oldcore/Stafflog/" + playerName + ".yml");
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration((File)f);
        if (!f.exists()) {
            try {
                playerData.save(f);
                correct = true;
            }
            catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return correct;
    }

    public static boolean removestaff(String added) {
        Boolean correct = false;
        String playerName = added;
        File f = new File("plugins/Oldcore/Stafflog/" + playerName + ".yml");
       
        if (f.exists()) {
            f.delete();
            correct = true;
        }
        return correct;
    }

    public static void staffimport(Player p) {
        String playerName = p.getName();
        File f = new File("plugins/Oldcore/Stafflog/" + playerName + ".yml");
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration((File)f);
        if (f.exists()) {
            String date = Utils.date("yyyy/MM/dd");
            if (!playerData.contains(date)) {
                playerData.set("date", (Object)0);
                Playerdata.stafftimer.put(playerName, playerData.getInt("date"));
            } else {
            	Playerdata.stafftimer.put(playerName, 0);
            }
        }
        Playerdata.perc.put(playerName, playerData.getInt("torzsadat.perc"));
        Playerdata.tp.put(playerName, playerData.getInt("torzsadat.tp"));
    }

    public static void staffexport(Player p) {
        if (Playerdata.stafftimer.containsKey(p.getName())) {
            String pname = p.getName();
            File f = new File("plugins/Oldcore/Stafflog/" + pname + ".yml");
            YamlConfiguration playerData = YamlConfiguration.loadConfiguration((File)f);
            String date = Utils.date("yyyy/MM/dd");
            playerData.set(date, (Object)Playerdata.stafftimer.get(pname));
            try {
                playerData.save(f);
            }
            catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void staffjelentes(Player p) {
        File folder = new File("plugins/Oldcore/Stafflog");
        File[] files = folder.listFiles();
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
        BookMeta bm = (BookMeta)book.getItemMeta();
        bm.setAuthor("Oldcore");
        bm.setTitle("Staffjelentés");
        File[] arrfile = files;
        int n = arrfile.length;
        int n2 = 0;
        while (n2 < n) {
            File file = arrfile[n2];
            YamlConfiguration playerData = YamlConfiguration.loadConfiguration((File)file);
            String lista = file.getName();
            int i = -1;
            while (i > -11) {
                String adding;
                String date = Utils.dayago(i);
                if (!playerData.contains(date)) {
                    adding = String.valueOf(date) + "-------";
                } else {
                    String adder = playerData.getString(date);
                    adding = String.valueOf(date) + " - " + adder;
                }
                lista = String.valueOf(lista) + "\n" + adding;
                --i;
            }
            bm.addPage(new String[]{lista});
            ++n2;
        }
        book.setItemMeta((ItemMeta)bm);
        p.getInventory().addItem(new ItemStack[]{book});
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
