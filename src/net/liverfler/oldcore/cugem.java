package net.liverfler.oldcore;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.block.Skull;

public class cugem {
	@SuppressWarnings("deprecation")
	public static boolean cugemmethod(String name, int gem) {
		Location loc1 = new Location(Bukkit.getWorld("world"), 91, 203, 109);
		Location loc2 = new Location(Bukkit.getWorld("world"), 84, 202, 110);
		Location loc3 = new Location(Bukkit.getWorld("world"), 98, 202, 110);
		Location loc4 = new Location(Bukkit.getWorld("world"), 91, 203, 103);
		Location loc5 = new Location(Bukkit.getWorld("world"), 84, 202, 104);
		Location loc6 = new Location(Bukkit.getWorld("world"), 98, 202, 104);
		Location loc7 = new Location(Bukkit.getWorld("world"), 91, 203, 97);
		Location loc8 = new Location(Bukkit.getWorld("world"), 84, 202, 98);
		Location loc9 = new Location(Bukkit.getWorld("world"), 98, 202, 98);
		Location loc10 = new Location(Bukkit.getWorld("world"), 91, 203, 91);
		Location loc11 = new Location(Bukkit.getWorld("world"), 84, 202, 92);
		Location loc12 = new Location(Bukkit.getWorld("world"), 98, 202, 92);
		Location loc13 = new Location(Bukkit.getWorld("world"), 91, 203, 85);
		Location loc14 = new Location(Bukkit.getWorld("world"), 84, 202, 86);
		Location loc15 = new Location(Bukkit.getWorld("world"), 98, 202, 86);
		Location loc16 = new Location(Bukkit.getWorld("world"), 91, 203, 79);
		Location loc17 = new Location(Bukkit.getWorld("world"), 84, 202, 80);
		Location loc18 = new Location(Bukkit.getWorld("world"), 98, 202, 80);
		
		Location take = null;
	
		if (loc18.getBlock().getType().equals(Material.AIR)){take = loc18;}
		if (loc17.getBlock().getType().equals(Material.AIR)){take = loc17;}
		if (loc16.getBlock().getType().equals(Material.AIR)){take = loc16;}
		if (loc15.getBlock().getType().equals(Material.AIR)){take = loc15;}
		if (loc14.getBlock().getType().equals(Material.AIR)){take = loc14;}
		if (loc13.getBlock().getType().equals(Material.AIR)){take = loc13;}
		if (loc12.getBlock().getType().equals(Material.AIR)){take = loc12;}
		if (loc11.getBlock().getType().equals(Material.AIR)){take = loc11;}
		if (loc10.getBlock().getType().equals(Material.AIR)){take = loc10;}
		if (loc9.getBlock().getType().equals(Material.AIR)){take = loc9;}
		if (loc8.getBlock().getType().equals(Material.AIR)){take = loc8;}
		if (loc7.getBlock().getType().equals(Material.AIR)){take = loc7;}
		if (loc6.getBlock().getType().equals(Material.AIR)){take = loc6;}
		if (loc5.getBlock().getType().equals(Material.AIR)){take = loc5;}
		if (loc4.getBlock().getType().equals(Material.AIR)){take = loc4;}
		if (loc3.getBlock().getType().equals(Material.AIR)){take = loc3;}
		if (loc2.getBlock().getType().equals(Material.AIR)){take = loc2;}
		if (loc1.getBlock().getType().equals(Material.AIR)){take = loc1;}

		take.setY(take.getY()-1);
		take.getBlock().setType(org.bukkit.Material.EMERALD_BLOCK);
		take.setY(take.getY()+1);
		take.getBlock().setType(org.bukkit.Material.SIGN_POST);
		
		BlockState state = take.getBlock().getState();
		Sign sign = (Sign)state;
		sign.setLine(0, "§l[CUGEM]");
		sign.setLine(1, "" + gem);
		sign.setLine(2, "§l" + name);
		sign.setLine(3, "§o Jobb klikk!");
		sign.update(true);
		
		take.setY(take.getY()+1);
		take.getBlock().setType(org.bukkit.Material.SKULL);
		take.getBlock().setData((byte) 3);
		Skull s = (Skull)take.getBlock().getState();
		s.setOwner(name);	
		s.update();
		return false;
	}




}
