package net.liverfler.oldcore;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.liverfler.oldshop.Oldshop;
import net.liverfler.szavazas.Szavazas;

public class Commander
implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("oldcore")) {
            if (args.length < 1) {
                sender.sendMessage("------------------------------------------------");
                sender.sendMessage("�lOldcore seg�ts�g:");
                sender.sendMessage("------------------------------------------------");
                sender.sendMessage("�2�l  Inf� egy j�t�kosr�l:");
                sender.sendMessage("�a         -/Oldcore info [j�t�kosn�v]");
                sender.sendMessage("�2�l  Oldcore toplista:");
                sender.sendMessage("�a         -/Oldcore toplista");
                sender.sendMessage("�2�l  Oldcore bolt:");
                sender.sendMessage("�a         -/Oldcore bolt");
                sender.sendMessage("------------------------------------------------");
                sender.sendMessage("�7�o Oldcore | Liverfler | V.2.0");
                sender.sendMessage("�7�o------------------------------------------------");
            } else {
                if (args[0].equalsIgnoreCase("igen") || args[0].equalsIgnoreCase("nem") || args[0].equalsIgnoreCase("szavazas")) {
                    Commander.szavazas(sender, args);
                }
                if (args[0].equalsIgnoreCase("info")) {
                    Commander.info(sender, args);
                }
                if (args[0].equalsIgnoreCase("bolt")) {
                    Commander.bolt(sender, args);
                }
                if (args[0].equalsIgnoreCase("toplista")) {
                    sender.sendMessage(Mysql.topten());
                }
                if (args[0].equalsIgnoreCase("staffszoba")) {
                    Commander.staffszoba(sender, args);
                }
                if (args[0].equalsIgnoreCase("stafflogger")) {
                    Commander.stafflogger(sender, args);
                }
                if (args[0].equalsIgnoreCase("warn")) {
                    Commander.warn(sender, args);
                }
                if (args[0].equalsIgnoreCase("unwarn")) {
                    Commander.unwarn(sender, args);
                }
                if (args[0].equalsIgnoreCase("getold")) {
                    Commander.getold(sender, args);
                }
            }
        }
        return false;
    }

    public static void szavazas(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player commander = (Player)sender;
            if (args[0].equalsIgnoreCase("igen")) {
                Szavazas.szavazas(commander, true);
            }
            if (args[0].equalsIgnoreCase("nem")) {
                Szavazas.szavazas(commander, false);
            }
            if (args[0].equalsIgnoreCase("szavazas")) {
                if (Szavazas.vaneszavazas.booleanValue()) {
                    sender.sendMessage(Commander.messagecreator("Jelenleg folyik egy szavaz�s, �gy nem ind�thatsz m�sikat!"));
                }
                if (args.length < 2) {
                    sender.sendMessage(Commander.messagecreator("\u00cdrd le hogy mir�l akarsz szavaztatni!"));
                } else {
                    String indok = "";
                    int i = 1;
                    while (i < args.length) {
                        indok = String.valueOf(indok) + args[i] + " ";
                        ++i;
                    }
                    Szavazas.szavazinditas(indok);
                }
            }
        } else {
            sender.sendMessage(Commander.messagecreator("Konzolb�l nem lehet szavaz�ssal kapcsolatos m�veletet v�gezni!"));
        }
    }

    public static void info(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("------------------------------------------------");
            sender.sendMessage("�2�l  Inf� egy j�t�kosr�l:");
            sender.sendMessage("�a         -/Oldcore info [j�t�kosn�v]");
            sender.sendMessage("------------------------------------------------");
        } else {
            String player = args[1];
            Player p = Bukkit.getServer().getPlayer(player);
            Player targetplayer = Bukkit.getPlayerExact((String)args[1]);
            if (targetplayer != null) {
                String pname = p.getPlayer().getName();
                if (!Playerdata.tp.containsKey(pname)) {
                    Mysql.mysqlLoader(pname);
                }
                int szint = Playerdata.szint.get(pname);
                int tp = Playerdata.tp.get(pname);
                int warn = Playerdata.warn.get(pname);
                int ora = Playerdata.ora.get(pname);
                int perc = Playerdata.perc.get(pname);
                double next = Clock.calc(tp, szint);
                sender.sendMessage("");
                sender.sendMessage("�l------------OLD-CORE---------------");
                sender.sendMessage("�2----    �2�l" + pname + "�2    ----");
                sender.sendMessage("�eOld-core szint: �4�l " + szint);
                sender.sendMessage("----  ----  ----  ----  ----  ----");
                sender.sendMessage("�rTapasztalat: �3 " + tp + "      �rSzintl�p�shez: �3" + next);
                sender.sendMessage("�rEddig j�tszott: " + ora + " �ra  " + perc + " perc");
                sender.sendMessage("�rWarnolva: " + warn);
                sender.sendMessage("�l----------------------------------");
            } else {
                String pname = args[1];
                Integer szint = Mysql.mysqlLoadInt(pname, "szint");
                Integer tp = Mysql.mysqlLoadInt(pname, "tp");
                Integer warn = Mysql.mysqlLoadInt(pname, "warn");
                Integer ora = Mysql.mysqlLoadInt(pname, "ora");
                Integer perc = Mysql.mysqlLoadInt(pname, "perc");
                if (szint == null) {
                    sender.sendMessage(Commander.messagecreator("A j�t�kos m�g nem j�rt a szerveren."));
                    return;
                }
                double next = Clock.calc(tp, szint);
                sender.sendMessage("");
                sender.sendMessage("�l------------OLD-CORE---------------");
                sender.sendMessage("�2----    �2�l" + pname + "�2    ----");
                sender.sendMessage("�eOld-core szint: �4�l " + szint);
                sender.sendMessage("----  ----  ----  ----  ----  ----");
                sender.sendMessage("�rTapasztalat: �3 " + tp + "      �rSzintl�p�shez:: �3" + next);
                sender.sendMessage("�rEddig j�tszott: " + ora + " �ra  " + perc + " perc");
                sender.sendMessage("�rWarnolva: " + warn);
                sender.sendMessage("�l----------------------------------");
            }
        }
    }

    public static void bolt(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player commander = (Player)sender;
            if (!Main.oldshop) {
                sender.sendMessage(Commander.messagecreator("Ezen a szerveren nincs Old-bolt!"));
            } else {
                
                Oldshop.openshop(1, commander);
            }
        } else {
            sender.sendMessage(Commander.messagecreator("Konzolb�l nem lehet boltot nyitni!"));
        }
    }

    public static void staffszoba(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player commander = (Player)sender;
            if (sender.hasPermission("oldcore.staff")) {
                Location loc = new Location(Bukkit.getWorld((String)"event"), 0.5, 5.0, 0.0);
                Block b = loc.getBlock();
                if (sender instanceof Player) {
                    commander.teleport(b.getLocation());
                }
            } else {
                sender.sendMessage(Commander.messagecreator("Ehhez nincs jogod!"));
            }
        } else {
            sender.sendMessage(Commander.messagecreator("Most te a konzolt akarod teleport�lni a staffszob�ba!"));
        }
    }

    public static void stafflogger(CommandSender sender, String[] args) {
        if (sender.hasPermission("oldcore.staff")) {
            if (args.length < 2) {
                sender.sendMessage("------------------------------------------------");
                sender.sendMessage("�2�l  Stafflogger parancsok:");
                sender.sendMessage("�a         -/Oldcore stafflogger add [staffn�v]");
                sender.sendMessage("�a         -/Oldcore stafflogger remove [staffn�v]");
                sender.sendMessage("�a         -/Oldcore stafflogger jelentes");
                sender.sendMessage("�a         -/Oldcore stafflogger convert �4�lNE HASZN�LD!");
                sender.sendMessage("------------------------------------------------");
            } else {
                if (args[1].equalsIgnoreCase("add")) {
                    if (!args[2].isEmpty()) {
                        if (Stafflogger.addstaff(args[2])) {
                            sender.sendMessage(Commander.messagecreator("Staff hozz�ad�sa sikeres!"));
                        } else {
                            sender.sendMessage(Commander.messagecreator("Staff hozz�ad�s hiba!"));
                        }
                    } else {
                        sender.sendMessage(Commander.messagecreator("Helyes haszn�lat: /oldcore stafflogger add j�t�kosn�v!"));
                    }
                }
                if (args[1].equalsIgnoreCase("remove")) {
                    if (!args[2].isEmpty()) {
                        if (Stafflogger.removestaff(args[2])) {
                            sender.sendMessage(Commander.messagecreator("Staff elt�vol�t�s sikeres!"));
                        } else {
                            sender.sendMessage(Commander.messagecreator("Nincs ilyen nev� staff!"));
                        }
                    } else {
                        sender.sendMessage(Commander.messagecreator("Helyes haszn�lat: /oldcore stafflogger remove j�t�kosn�v"));
                    }
                }
                if (args[1].equalsIgnoreCase("convert")) {
                	Stafflogger.staffymlconverter();
                }
                if (args[1].equalsIgnoreCase("jelentes")) {
                    if (sender instanceof Player) {
                        Player commander = (Player)sender;
                        Stafflogger.staffjelentes(commander);
                    } else {
                        sender.sendMessage(Commander.messagecreator("A konzol nem tudja megkapni a k�nyvecsk�t!"));
                    }
                }
            }
        } else {
            sender.sendMessage(Commander.messagecreator("Ehhez nincs jogod!"));
        }
    }

    public static void warn(CommandSender sender, String[] args) {
        if (sender.hasPermission("oldcore.staff")) {
        	 if (args.length > 1) {
        		
        	
            Player targetplayer = Bukkit.getPlayerExact((String)args[1]);
           
                if (targetplayer != null) {
                    String player = args[1];
                    Player p = Bukkit.getServer().getPlayer(player);
                    String pla = p.getPlayer().getName();
                    int warn = Playerdata.warn.get(pla);
                    Playerdata.warn.put(pla, ++warn);
                    sender.sendMessage(Commander.messagecreator("Az online j�t�kos warn-olva! Eddig �sszesen �3" + warn + "�r alkalommal!"));
                } else {
                    String pname = args[1];
                    int warn = Mysql.mysqlLoadInt(pname, "warn");
                    int plus = 1;
                    int fin = plus + warn;
                    Mysql.mysqlSaveInt(pname, "warn", fin);
                    sender.sendMessage(Commander.messagecreator("A j�t�kos nincs online! De warn-olva! Eddig �sszesen �3" + fin + "�r alkalommal!"));
                }
            } else {
            	 sender.sendMessage(Commander.messagecreator("Helyes haszn�lat: /oldcore warn j�t�kosn�v"));
            }
        } else {
            sender.sendMessage(Commander.messagecreator("Ehhez nincs jogod"));
        }
    }
    
    public static void unwarn(CommandSender sender, String[] args) {
        if (sender.hasPermission("oldcore.staff")) {
        	 if (args.length > 1) {
        		
        	
            Player targetplayer = Bukkit.getPlayerExact((String)args[1]);
           
                if (targetplayer != null) {
                    String player = args[1];
                    Player p = Bukkit.getServer().getPlayer(player);
                    String pla = p.getPlayer().getName();
                    int warn = Playerdata.warn.get(pla);
                    Playerdata.warn.put(pla, --warn);
                    sender.sendMessage(Commander.messagecreator("Az online j�t�kos unwarn-olva! �sszesen �3" + warn + "�r warn-ja maradt!"));
                } else {
                    String pname = args[1];
                    int warn = Mysql.mysqlLoadInt(pname, "warn");
                    int plus = -1;
                    int fin = plus + warn;
                    Mysql.mysqlSaveInt(pname, "warn", fin);
                    sender.sendMessage(Commander.messagecreator("A j�t�kos nincs online! De unwarn-olva! �sszesen �3" + fin + "�r warn-ja maradt!"));
                }
            } else {
            	 sender.sendMessage(Commander.messagecreator("Helyes haszn�lat: /oldcore unwarn j�t�kosn�v"));
            }
        } else {
            sender.sendMessage(Commander.messagecreator("Ehhez nincs jogod"));
        }
    }

    public static void getold(CommandSender sender, String[] args) {
        if (sender.hasPermission("oldcore.staff")) {
            if (args.length < 3) {
                sender.sendMessage(Commander.messagecreator("Helyes haszn�lat: /oldcore getold oldgold [sz�m]"));
                sender.sendMessage(Commander.messagecreator("Helyes haszn�lat: /oldcore getold oldblock [sz�m]"));
            } else {
                int n;
                Player commander;
                if (args[1].equals("oldgold")) {
                    if (sender instanceof Player) {
                        commander = (Player)sender;
                        n = Integer.parseInt(args[2]);
                        ItemStack oldgold = new ItemStack(Material.GOLD_INGOT);
                        ItemMeta oldg = oldgold.getItemMeta();
                        oldg.setDisplayName("�6�lOld�8�lGold");
                        oldgold.setItemMeta(oldg);
                        oldgold.setAmount(n);
                        commander.getInventory().addItem(new ItemStack[]{oldgold});
                    } else {
                        sender.sendMessage(Commander.messagecreator("A konzol nem tudja megkapni az oldgold-ot!"));
                    }
                }
                if (args[1].equals("oldblock")) {
                    if (sender instanceof Player) {
                        commander = (Player)sender;
                        n = Integer.parseInt(args[2]);
                        ItemStack oldblock = new ItemStack(Material.GOLD_BLOCK);
                        ItemMeta oldb = oldblock.getItemMeta();
                        oldb.setDisplayName("�7�lOld�6�lGold�8�lBolck");
                        oldblock.setItemMeta(oldb);
                        oldblock.setAmount(n);
                        commander.getInventory().addItem(new ItemStack[]{oldblock});
                    } else {
                        sender.sendMessage(Commander.messagecreator("A konzol nem tudja megkapni az oldgold-ot!"));
                    }
                }
            }
        } else {
            sender.sendMessage(Commander.messagecreator("Ehhez nincs jogod"));
        }
    }

    public static void delete(CommandSender sender, String[] args){
    	if (sender.hasPermission("oldcore.staff")) {
       	 if (args.length > 1) {
       		
       	
           Player targetplayer = Bukkit.getPlayerExact((String)args[1]);
          
               if (targetplayer != null) {
                   String player = args[1];
                   Player p = Bukkit.getServer().getPlayer(player);
                   String pla = p.getPlayer().getName();
                   
                   Playerdata.ora.remove(pla);
                   Playerdata.perc.remove(pla);
                   Playerdata.szint.remove(pla);
                   Playerdata.tp.remove(pla);
                   Playerdata.warn.remove(pla);
                   Playerdata.weektp.remove(pla);
                   Mysql.mysqldelete(pla);
                   
                   
                   
                   
                   sender.sendMessage(Commander.messagecreator("Az online j�t�kos Oldcore adatai t�r�lve!"));
                   
                  
               }
           } else {
           	 sender.sendMessage(Commander.messagecreator("Helyes haszn�lat: /oldcore delete j�t�kosn�v"));
           }
       } else {
           sender.sendMessage(Commander.messagecreator("Ehhez nincs jogod"));
       }
    	
    }
    
    public static String messagecreator(String string) {
        String ret = "�2�lOld-core: �r�2" + string;
        return ret;
    }
}
