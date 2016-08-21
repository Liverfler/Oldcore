package net.liverfler.oldcore;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commander implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		if (cmd.getName().equalsIgnoreCase("oldcore")) {
			Player commander = (Player) sender;

			if (args.length < 1) {
				sender.sendMessage("------------------------------------------------");
				sender.sendMessage("§lOldcore segítség:");
				sender.sendMessage("------------------------------------------------");
				sender.sendMessage("§2§l  Infó egy játékosról:");
				sender.sendMessage("§a         -/Oldcore info [játékosnév]");
				sender.sendMessage("§2§l  Oldcore toplista:");
				sender.sendMessage("§a         -/Oldcore toplista");
				sender.sendMessage("§2§l  Oldcore bolt:");
				sender.sendMessage("§a         -/Oldcore bolt");
				sender.sendMessage("------------------------------------------------");
				sender.sendMessage("§7§o Oldcore | Liverfler | V.1.1");
				sender.sendMessage("§7§o------------------------------------------------");
				

			} else {
				if(args[0].equalsIgnoreCase("igen")){
					net.liverfler.szavazas.Szavazas.szavazas(commander, true);
				}
				if(args[0].equalsIgnoreCase("nem")){
					net.liverfler.szavazas.Szavazas.szavazas(commander, false);
				}
				
				if(args[0].equalsIgnoreCase("szavazas")){
					if(net.liverfler.szavazas.Szavazas.vaneszavazas){
						Utils.message(commander, "Jelenleg folyik egy szavazás, így nem indíthatsz másikat!");
					}
					if(args.length<2){
						Utils.message(commander, "Írd le hogy mirõl akarsz szavaztatni!");
					}else{
					String indok = "";
					for (int i = 1; i < args.length; i++) {
						indok = indok + args[i] + " ";
					}
					net.liverfler.szavazas.Szavazas.szavazinditas(indok);
					}
				}
				
				if (args[0].equalsIgnoreCase("info")) {
					
					if(args.length<2){
						sender.sendMessage("------------------------------------------------");
						sender.sendMessage("§2§l  Infó egy játékosról:");
						sender.sendMessage("§a         -/Oldcore info [játékosnév]");
						sender.sendMessage("------------------------------------------------");
					}else{
						
					
					String player = args[1];
					Player p = Bukkit.getServer().getPlayer(player);
					final Player targetplayer = Bukkit.getPlayerExact(args[1]);

					if (targetplayer != null) {
						String pname = p.getPlayer().getName();
						if (!Main.tp.containsKey(pname)) {
							Mysql.mysqlLoader(pname);
						}
						int szint = Main.szint.get(pname);
						int tp = Main.tp.get(pname);
						
						int warn = Main.warn.get(pname);
						int ora = Main.ora.get(pname);
						int perc = Main.perc.get(pname);
						

						double next = Utils.calc(tp, szint);

						sender.sendMessage("");
						sender.sendMessage("§l------------OLD-CORE---------------");
						sender.sendMessage("§2----    §2§l" + pname
								+ "§2    ----");
						sender.sendMessage("§eOld-core szint: §4§l "
								+ szint);
						sender.sendMessage("----  ----  ----  ----  ----  ----");
						sender.sendMessage("§rTapasztalat: §3 " + tp
								+ "      §rSzintlépéshez: §3" + next);
						
						
						;
						sender.sendMessage("§rEddig játszott: " + ora
								+ " óra  " + perc + " perc");
						sender.sendMessage("§rWarnolva: " + warn);

						
						sender.sendMessage("§l----------------------------------");

					} else {
						String pname = args[1];
						Integer szint = Mysql.mysqlLoadInt(pname, "szint");
						Integer tp = Mysql.mysqlLoadInt(pname, "tp");
						Integer warn = Mysql.mysqlLoadInt(pname, "warn");
						Integer ora = Mysql.mysqlLoadInt(pname, "ora");
						Integer perc = Mysql.mysqlLoadInt(pname, "perc");
			

						if (szint == null) {
							sender.sendMessage("A játékos még nem járt a szerveren.");
							return false;
						}
						double next = Utils.calc(tp, szint);

						sender.sendMessage("");
						sender.sendMessage("§l------------OLD-CORE---------------");
						sender.sendMessage("§2----    §2§l" + pname
								+ "§2    ----");
						sender.sendMessage("§eOld-core szint: §4§l "
								+ szint);
						sender.sendMessage("----  ----  ----  ----  ----  ----");
						sender.sendMessage("§rTapasztalat: §3 " + tp
								+ "      §rSzintlépéshez:: §3" + next);
					
						sender.sendMessage("§rEddig játszott: " + ora	+ " óra  " + perc + " perc");
						sender.sendMessage("§rWarnolva: " + warn);
						
						sender.sendMessage("§l----------------------------------");
						}
						
						
					}
						
						
					}

				if (args[0].equalsIgnoreCase("bolt")) {
					if(!Main.config("Oldshop")){
						Utils.message(commander, "Ezen a szerveren nincs Old-bolt");
					}else{
						Oldshop.openshop(1, commander);
					}
						
					
					
				}
				
				if (args[0].equalsIgnoreCase("toplista")) {
					Main.topten(commander);
				
				
			}
				
				if (args[0].equalsIgnoreCase("staffszoba")) {
					if (sender.hasPermission("oldcore.staff")) {
						commander.teleport(new Location(Bukkit.getWorld("event"), 0.5, 5, 0));
					}
					
				
				
			}
				
				
				if (args[0].equalsIgnoreCase("stafflogger")) {
					if(args.length<2){
						sender.sendMessage("------------------------------------------------");
						sender.sendMessage("§2§l  Stafflogger parancsok:");
						sender.sendMessage("§a         -/Oldcore stafflogger add [staffnév]");
						sender.sendMessage("§a         -/Oldcore stafflogger jelentes");
						sender.sendMessage("§a         -/Oldcore stafflogger convert §4§lNE HASZNÁLD!");
						sender.sendMessage("------------------------------------------------");
					}else{
					
					if (sender.hasPermission("oldcore.staff")) {
						
					
					if (args[1].equalsIgnoreCase("add")) {
						if (!args[2].isEmpty()) {
							Main.addstaff(args[2], commander);
						}else{
							Utils.message(commander, "Helyes használat: /oldcore stafflogger add játékosnév");
						}
						
						
					}
					
					if (args[1].equalsIgnoreCase("convert")) {
						Main.staffymlconverter();
						
						
					}
					
					if (args[1].equalsIgnoreCase("jelentes")) {
						Main.staffjelentes(commander);
						
						
					}
					
					}}
				}

				if (args[0].equalsIgnoreCase("warn")) {// XXXXXXXXXXXXXX
					if (sender.hasPermission("oldcore.staff")) {

						final Player targetplayer = Bukkit
								.getPlayerExact(args[1]);

						if (targetplayer != null) {
							String player = args[1];
							Player p = Bukkit.getServer().getPlayer(player);
							String pla = p.getPlayer().getName();
							Utils.info("online játékos warnolva");
							int warn = Main.warn.get(pla);
							warn = warn + 1;
							Main.warn.put(pla, warn);
							sender.sendMessage("§l[OLD-CORE]:§r Az online játékos warn-olva! Eddig összesen §3"
									+ warn + "§r alkalommal!");
						} else {

							String pname = args[1];
							int warn = Mysql.mysqlLoadInt(pname, "warn");
							int plus = 1;
							int fin = plus + warn;
							Mysql.mysqlSaveInt(pname, "warn", fin);
							Utils.info("nem online játékos warnolva");
							sender.sendMessage("§l[OLD-CORE]:§r A játékos nincs online! De warn-olva! Eddig összesen §3"
									+ fin + "§r alkalommal!");
						}

					} else {
						sender.sendMessage("Ehhez nincs jogod!");
					}
				}
			}


				
			}		
		
		
		return false;
	}
}
