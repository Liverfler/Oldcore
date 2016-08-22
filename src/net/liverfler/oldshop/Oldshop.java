/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Chunk
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockState
 *  org.bukkit.block.Chest
 *  org.bukkit.block.Sign
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryHolder
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.inventory.meta.ItemMeta
 */
package net.liverfler.oldshop;

import java.util.HashMap;
import net.liverfler.oldcore.Main;
import net.liverfler.oldcore.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
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
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Oldshop
implements Listener {
    public static HashMap<Integer, Location> chests = new HashMap<Integer, Location>();
    public static Boolean shopenable = false;

    public static void inditas() {
        if (Main.oldshop) {
        
            chests.put(0, new Location(Bukkit.getWorld((String)"event"), -4.0, 5.0, 11.0));
            chests.put(1, new Location(Bukkit.getWorld((String)"event"), -3.0, 5.0, 10.0));
            chests.put(2, new Location(Bukkit.getWorld((String)"event"), -1.0, 5.0, 10.0));
            chests.put(3, new Location(Bukkit.getWorld((String)"event"), 1.0, 5.0, 10.0));
            chests.put(4, new Location(Bukkit.getWorld((String)"event"), 3.0, 5.0, 10.0));
            chests.put(5, new Location(Bukkit.getWorld((String)"event"), 5.0, 5.0, 10.0));
            chests.put(6, new Location(Bukkit.getWorld((String)"event"), 7.0, 5.0, 10.0));
            chests.put(7, new Location(Bukkit.getWorld((String)"event"), 8.0, 5.0, 11.0));
            chests.put(8, new Location(Bukkit.getWorld((String)"event"), 8.0, 5.0, 13.0));
            chests.put(9, new Location(Bukkit.getWorld((String)"event"), 8.0, 5.0, 15.0));
            chests.put(10, new Location(Bukkit.getWorld((String)"event"), 8.0, 5.0, 17.0));
            chests.put(11, new Location(Bukkit.getWorld((String)"event"), 7.0, 5.0, 18.0));
            chests.put(12, new Location(Bukkit.getWorld((String)"event"), 5.0, 5.0, 18.0));
            chests.put(13, new Location(Bukkit.getWorld((String)"event"), 3.0, 5.0, 18.0));
            chests.put(14, new Location(Bukkit.getWorld((String)"event"), 1.0, 5.0, 18.0));
            chests.put(15, new Location(Bukkit.getWorld((String)"event"), -1.0, 5.0, 18.0));
            chests.put(16, new Location(Bukkit.getWorld((String)"event"), -3.0, 5.0, 18.0));
            chests.put(17, new Location(Bukkit.getWorld((String)"event"), -4.0, 5.0, 17.0));
        } else {
        	Utils.consoleinfo("bolt kikapcsolva");
        }
    }

    @EventHandler(priority=EventPriority.MONITOR)
    void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equals("§l§4Old-shop")) {
            event.setCancelled(true);
            int slot = event.getRawSlot();
            Player p = Bukkit.getPlayer((String)event.getWhoClicked().getName());
            if (slot < 18 && !event.getInventory().getItem(slot).getItemMeta().getDisplayName().equals("off")) {
                p.closeInventory();
                Oldshop.openshop(slot, p);
            }
            if (slot > 44) {
                int termekint = slot - 18;
                int aruint = slot - 9;
                ItemStack termek = new ItemStack(event.getInventory().getItem(termekint));
                ItemStack ar = new ItemStack(event.getInventory().getItem(aruint));
                ItemStack slotn = new ItemStack(event.getInventory().getItem(22));
                String slotnum = String.valueOf(slotn.getItemMeta().getDisplayName()) + " " + slot;
                Oldshop.correct(termek, ar, p, slotnum);
            }
        }
        if (event.getInventory().getTitle().equals("§l§4Megerõsítés")) {
            event.setCancelled(true);
            ItemStack fullslotbook = new ItemStack(event.getInventory().getItem(4));
            String fullslot = fullslotbook.getItemMeta().getDisplayName();
            String[] list = fullslot.split(" ");
            int lada = Integer.parseInt(list[0]);
            int slotn = Integer.parseInt(list[1]);
            ItemStack termek = new ItemStack(event.getInventory().getItem(12));
            ItemStack ar = new ItemStack(event.getInventory().getItem(14));
            int slot = event.getRawSlot();
            Player p = Bukkit.getPlayer((String)event.getWhoClicked().getName());
            if (slot == 10) {
                p.closeInventory();
                Oldshop.openshop(lada, p);
            }
            if (slot == 16) {
                p.closeInventory();
                if (Oldshop.invcheck(p, ar.getType(), ar.getAmount()).booleanValue()) {
                    if (p.getInventory().firstEmpty() != -1) {
                        p.getInventory().addItem(new ItemStack[]{termek});
                        Oldshop.removemat(p, ar.getType(), ar.getAmount());
                        Utils.message(p, "Sikeres vásárlás!");
                        Oldshop.vasarlog(lada, slotn, p);
                        Oldshop.openshop(lada, p);
                    } else {
                        Utils.message(p, "Tele van az inventory-d!");
                    }
                } else {
                    Utils.message(p, "Nincs meg nálad a termék ára!");
                }
            }
        }
    }

    public static void openshop(int shopnum, Player p) {
        Oldshop.inditas();
        Chunk c = Bukkit.getWorld((String)"event").getChunkAt(chests.get(1));
        Chunk c2 = Bukkit.getWorld((String)"event").getChunkAt(chests.get(14));
        if (!c.isLoaded()) {
            c.load();
            c2.load();
        }
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, (int)54, (String)"§l§4Old-shop");
        int i = 0;
        while (i < 27) {
            ItemStack isadd;
            ItemMeta isaddMeta;
            if (i < 18) {
                isadd = new ItemStack(Material.CHEST);
                isaddMeta = isadd.getItemMeta();
                
                Location signloc = chests.get(i);
                signloc.setY(signloc.getY() + 1);
                Block s = signloc.getBlock();
                Sign sign = (Sign)s.getState();
                if (sign.getLine(0).equals("off")) {
                    isadd.setType(Material.OBSIDIAN);
                    isaddMeta.setDisplayName(sign.getLine(0));
                } else {
                    isaddMeta.setDisplayName(sign.getLine(0));
                    isadd.setItemMeta(isaddMeta);
                    inv.setItem(i, isadd);
                }
                signloc.setY(signloc.getY() - 1);
            } else {
                isadd = new ItemStack(Material.STAINED_GLASS_PANE);
                isaddMeta = isadd.getItemMeta();
                isadd.setDurability((short) 14);
                isaddMeta.setDisplayName(String.valueOf(shopnum));
                isadd.setItemMeta(isaddMeta);
                inv.setItem(i, isadd);
            }
            int origchestpos = 0;
            int ii = 27;
            while (ii < 45) {
                Location cheloc = chests.get(shopnum).getBlock().getLocation();
                Block sc = cheloc.getBlock();
                Chest che = (Chest)sc.getState();
                if (che.getInventory().getItem(origchestpos) != null) {
                    ItemStack it = new ItemStack(che.getInventory().getItem(origchestpos));
                    inv.setItem(ii, it);
                }
                ++origchestpos;
                ++ii;
            }
            ii = 45;
            while (ii < 54) {
                ItemStack isadd2 = new ItemStack(Material.STAINED_GLASS_PANE);
                ItemMeta isaddMeta2 = isadd2.getItemMeta();
                isadd2.setDurability((short) 5);
                isaddMeta2.setDisplayName("Megvétel");
                isadd2.setItemMeta(isaddMeta2);
                inv.setItem(ii, isadd2);
                ++ii;
            }
            ++i;
        }
        p.openInventory(inv);
    }

    public static void correct(ItemStack termek, ItemStack ar, Player p, String slotnumber) {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, (int)27, (String)"§l§4Megerõsítés");
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

    public static void vasarlog(int lada, int slot, Player p) {
        int posslot = slot - 27;
        Location cheloc = chests.get(lada).getBlock().getLocation();
        Block s = cheloc.getBlock();
        Chest che = (Chest)s.getState();
        che.getBlockInventory();
        if (che.getInventory().getItem(posslot) == null) {
            ItemStack stick = new ItemStack(Material.STICK);
            ItemMeta bookmeta = stick.getItemMeta();
            bookmeta.setDisplayName("0");
            stick.setItemMeta(bookmeta);
            che.getInventory().setItem(posslot, stick);
            che.update();
        }
        ItemStack st = che.getInventory().getItem(posslot);
        ItemMeta stmeta = st.getItemMeta();
        int calc = Integer.parseInt(stmeta.getDisplayName());
        stmeta.setDisplayName(String.valueOf(++calc));
        st.setItemMeta(stmeta);
        che.getInventory().setItem(posslot, st);
    }

    public static Boolean invcheck(Player p, Material mat, int amount) {
        Boolean ret = p.getInventory().contains(mat, amount);
        return ret;
    }

    public static void removemat(Player p, Material mat, int amount) {
        int i = 0;
        while (i < p.getInventory().getSize()) {
            ItemStack itm = p.getInventory().getItem(i);
            if (itm != null && itm.getType().equals((Object)mat)) {
                int amt = itm.getAmount() - amount;
                itm.setAmount(amt);
                p.getInventory().setItem(i, amt > 0 ? itm : null);
                p.updateInventory();
                break;
            }
            ++i;
        }
    }
}

