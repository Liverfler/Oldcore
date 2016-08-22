
package net.liverfler.oldcore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.liverfler.szavazas.Szavazas;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class TabCompleteExplain
implements TabCompleter {
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("oldcore")) {
            ArrayList<String> alparancsok;
            
            if (strings.length == 2 && strings[0].equalsIgnoreCase("stafflogger")) {
                alparancsok = new ArrayList<String>();
                alparancsok.add("add");
                alparancsok.add("remove");
                alparancsok.add("jelentes");
                return alparancsok;
            }
            ArrayList<String> foparancsok = new ArrayList<String>();
            if (Szavazas.vaneszavazas.booleanValue()) {
                foparancsok.add("igen");
                foparancsok.add("nem");
            }
            foparancsok.add("info");
            foparancsok.add("bolt");
            if (commandSender.hasPermission("oldcore.staff")) {
                foparancsok.add("szavazas");
                foparancsok.add("staffszoba");
                foparancsok.add("stafflogger");
                foparancsok.add("warn");
                foparancsok.add("unwarn");
            }
            return foparancsok;
        }
        return null;
    }
}

