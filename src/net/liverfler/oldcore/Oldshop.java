package net.liverfler.oldcore;



import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class Oldshop implements Listener {
	public static HashMap<Integer, Location> chests = new HashMap<Integer, Location>();
	public static Boolean shopenable = false;
	
	public static void enable(){
		
		if(Main.config("Oldshop")){
			
		
		chests.put(0, new Location(Bukkit.getWorld("event"), -4, 5, 11));
		chests.put(1, new Location(Bukkit.getWorld("event"), -3, 5, 10));
		chests.put(2, new Location(Bukkit.getWorld("event"), -1, 5, 10));
		chests.put(3, new Location(Bukkit.getWorld("event"), 1, 5, 10));
		chests.put(4, new Location(Bukkit.getWorld("event"), 3, 5, 10));
		chests.put(5, new Location(Bukkit.getWorld("event"), 5, 5, 10));
		chests.put(6, new Location(Bukkit.getWorld("event"), 7, 5, 10));
		chests.put(7, new Location(Bukkit.getWorld("event"), 8, 5, 11));
		chests.put(8, new Location(Bukkit.getWorld("event"), 8, 5, 13));
		chests.put(9, new Location(Bukkit.getWorld("event"), 8, 5, 15));
		chests.put(10, new Location(Bukkit.getWorld("event"), 8, 5, 17));
		chests.put(11, new Location(Bukkit.getWorld("event"), 7, 5, 18));
		chests.put(12, new Location(Bukkit.getWorld("event"), 5, 5, 18));
		chests.put(13, new Location(Bukkit.getWorld("event"), 3, 5, 18));
		chests.put(14, new Location(Bukkit.getWorld("event"), 1, 5, 18));
		chests.put(15, new Location(Bukkit.getWorld("event"), -1, 5, 18));
		chests.put(16, new Location(Bukkit.getWorld("event"), -3, 5, 18));
		chests.put(17, new Location(Bukkit.getWorld("event"), -4, 5, 17));
		}
	}
	
	
	@EventHandler(priority = EventPriority.MONITOR)
	 void onInventoryClick(InventoryClickEvent event){
	        if(event.getInventory().getTitle().equals("§l§4Old-shop")){
	            event.setCancelled(true);
	            int slot = event.getRawSlot();
	            Player p = Bukkit.getPlayer(event.getWhoClicked().getName());
	            if(slot<18&&!event.getInventory().getItem(slot).getItemMeta().getDisplayName().equals("off")){
	            	p.closeInventory();
	            	openshop(slot, p);
	            }
	            
	            if(slot>44){
		        	int termekint = slot-18;
	            	int aruint = slot-9;
	            	ItemStack termek = new ItemStack(event.getInventory().getItem(termekint));
	            	ItemStack ar = new ItemStack(event.getInventory().getItem(aruint));
	            	ItemStack slotn = new ItemStack(event.getInventory().getItem(22));
	            	String slotnum = slotn.getItemMeta().getDisplayName() +" " + slot;
	            	correct(termek, ar, p, slotnum);
		        }
	        }
	        
	        if(event.getInventory().getTitle().equals("§l§4Megerõsítés")){
	            event.setCancelled(true);
	            ItemStack fullslotbook = new ItemStack(event.getInventory().getItem(4));
            	String fullslot = fullslotbook.getItemMeta().getDisplayName();
	            String[] list = fullslot.split(" ");
	            int lada = Integer.parseInt(list[0]);
	            int slotn = Integer.parseInt(list[1]);
	            
	            ItemStack termek = new ItemStack(event.getInventory().getItem(12));
            	ItemStack ar = new ItemStack(event.getInventory().getItem(14));
	           
	            
	            int slot = event.getRawSlot();
	            Player p = Bukkit.getPlayer(event.getWhoClicked().getName());
	            
	            
	            if(slot==10){
	            	p.closeInventory();
	            	openshop(lada, p);	
	            }
	            
	            if(slot==16){
	            	p.closeInventory();
	            	
	            	if(invcheck(p, ar.getType(), ar.getAmount())){
	            		if(p.getInventory().firstEmpty()!= -1){
	            			p.getInventory().addItem(termek);
	            			removemat(p, ar.getType(), ar.getAmount());
	            			Utils.message(p, "Sikeres vásárlás!");
	            			vasarlog(lada, slotn, p);
	            			openshop(lada, p);
	            		}else{
	            			Utils.message(p, "Tele van az inventory-d!");

	            		}
	            		
	            		
	            		
	            		
	            		
	            		
	            		
	            	}else{
	            		Utils.message(p, "Nincs meg nálad a termék ára!");
	            	}
	            }
	            
	            
	        }
	        
	        
	        
	        
	    }
	
	
	
	public static void openshop(int shopnum, Player p){
		Location loader = chests.get(1);
		Location loader2 = chests.get(14);
		
			loader.getChunk().load();
			loader2.getChunk().load();
		
		
		
		Inventory inv;
		inv = Bukkit.createInventory(null, 54, "§l§4Old-shop");
		for(int i=0; i<27; i++){
			if(i<18){
				ItemStack isadd = new ItemStack(Material.CHEST);
				ItemMeta isaddMeta = isadd.getItemMeta();
				Location chestsignloc = chests.get(i).getBlock().getLocation();
				Location signloc = chests.get(i).getBlock().getLocation();
				signloc.setY(chestsignloc.getY()+1);
				
				Block s = signloc.getBlock();
				Sign sign = (Sign) s.getState();
			
				
				if(sign.getLine(0).equals("off")){
					isadd.setType(Material.OBSIDIAN);
					isaddMeta.setDisplayName(sign.getLine(0));
					
				}else{
					isaddMeta.setDisplayName(sign.getLine(0));
					isadd.setItemMeta(isaddMeta);	

					inv.setItem(i, isadd);
					
				}
				
				
				
				
				
				
				
			}else{
				ItemStack isadd = new ItemStack(Material.STAINED_GLASS_PANE);
				ItemMeta isaddMeta = isadd.getItemMeta();
				isadd.setDurability((short) 14);
				isaddMeta.setDisplayName(shopnum + "");
				isadd.setItemMeta(isaddMeta);	
				inv.setItem(i, isadd);
			}
			int origchestpos = 0;
			for(int ii=27; ii<45; ii++){
				Location cheloc = chests.get(shopnum).getBlock().getLocation();
				Block s = cheloc.getBlock();
				Chest che = (Chest) s.getState();
				if(che.getInventory().getItem(origchestpos)!=null){
					ItemStack it = new ItemStack(che.getInventory().getItem(origchestpos));
					inv.setItem(ii, it);
				}
				
				
				
				
				origchestpos++;
			}
			
			for(int ii=45; ii<54; ii++){
				ItemStack isadd = new ItemStack(Material.STAINED_GLASS_PANE);
				ItemMeta isaddMeta = isadd.getItemMeta();
				isadd.setDurability((short) 5);
				isaddMeta.setDisplayName("Megvétel");
				isadd.setItemMeta(isaddMeta);	
				inv.setItem(ii, isadd);
				
			}
			
			
		}
		

	    p.openInventory(inv);
		
		
	}
	
	public static void correct(ItemStack termek, ItemStack ar, Player p, String slotnumber){
		Inventory inv;
		inv = Bukkit.createInventory(null, 27, "§l§4Megerõsítés");
		ItemStack num = new ItemStack(Material.BOOK);
		ItemMeta numeta = num.getItemMeta();
		numeta.setDisplayName(slotnumber);
		num.setItemMeta(numeta);	
		inv.setItem(4, num);
		
		
		ItemStack isadd = new ItemStack(Material.STAINED_GLASS_PANE);
		ItemMeta isaddMeta = isadd.getItemMeta();
		isadd.setDurability((short) 14);
		isaddMeta.setDisplayName("Mégse");
		isadd.setItemMeta(isaddMeta);	
		inv.setItem(10, isadd);

		isadd.setDurability((short) 5);
		isaddMeta.setDisplayName("Vásárlás");
		isadd.setItemMeta(isaddMeta);	
		inv.setItem(16, isadd);
		
		ItemStack sign = new ItemStack(Material.SIGN);
		ItemMeta signMeta = sign.getItemMeta();
		signMeta.setDisplayName("Ezt vásárlod!");
		sign.setItemMeta(signMeta);	
		inv.setItem(3, sign);

		signMeta.setDisplayName("Ezzel fizetsz!");
		sign.setItemMeta(signMeta);	
		inv.setItem(5, sign);
		
		inv.setItem(12, termek);
		inv.setItem(14, ar);
		
		
		
		 p.openInventory(inv);
	}
	public static void vasarlog(int lada, int slot, Player p){
		int posslot = slot-27;
		Location cheloc = chests.get(lada).getBlock().getLocation();
		Block s = cheloc.getBlock();
		Chest che = (Chest) s.getState();
		che.getBlockInventory();
	
		if(che.getInventory().getItem(posslot)==null){
			
			
			ItemStack stick = new ItemStack(Material.STICK);
			ItemMeta bookmeta = stick.getItemMeta();
					bookmeta.setDisplayName(0 + "");
					stick.setItemMeta(bookmeta);		
				
			
			che.getInventory().setItem(posslot, stick);
			
			che.update();
		}
		ItemStack st = che.getInventory().getItem(posslot);
		ItemMeta stmeta = st.getItemMeta();
		
		int calc = Integer.parseInt(stmeta.getDisplayName());
		calc++;
		stmeta.setDisplayName(calc + "");
		st.setItemMeta(stmeta);
		
		che.getInventory().setItem(posslot, st);
				
		
	
		
		
	}
	public static Boolean invcheck(Player p, Material mat, int amount) {
		Boolean ret = p.getInventory().contains(mat, amount);
		return ret;
	}

	public static void removemat(Player p, Material mat, int amount) {
		for (int i = 0; i < p.getInventory().getSize(); i++) {
			ItemStack itm = p.getInventory().getItem(i);
			if (itm != null && itm.getType().equals(mat)) {
				int amt = itm.getAmount() - amount;
				itm.setAmount(amt);
				p.getInventory().setItem(i, amt > 0 ? itm : null);
				p.updateInventory();
				break;
			}

		}
	}
	
	
}
