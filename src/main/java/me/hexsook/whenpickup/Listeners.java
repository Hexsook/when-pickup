package me.hexsook.whenpickup;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Listeners implements Listener {

    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onShift(PlayerSwapHandItemsEvent e) {
        Player player = e.getPlayer();
        if (!player.isSneaking()) {
            return;
        }

        List<Item> items = new ArrayList<>();
        for (Entity ent : player.getNearbyEntities(WhenPickup.PICKUP_RADIUS, WhenPickup.PICKUP_RADIUS, WhenPickup.PICKUP_RADIUS)) {
            if (ent instanceof Item) {
                items.add((Item) ent);
            }
        }

        if (items.isEmpty()) {
            return;
        }

        WhenPickup.openInventory(player);
        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
        e.setCancelled(true);
    }

    private boolean lock = false;

    @EventHandler
    public void onInventory(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        Inventory clickedInv = e.getClickedInventory();

        if (clickedInv == null) {
            return;
        }

        if (!clickedInv.equals(e.getView().getTopInventory()) ||
                !(clickedInv.getHolder() instanceof WhenPickupHolder)) {
            return;
        }

        e.setCancelled(true);

        if (item == null || item.getItemMeta() == null || item.getItemMeta().hasItemFlag(ItemFlag.HIDE_UNBREAKABLE)) {
            return;
        }

        if (lock) {
            return;
        }

        lock = true;
        for (Entity ent : player.getNearbyEntities(WhenPickup.PICKUP_RADIUS, WhenPickup.PICKUP_RADIUS, WhenPickup.PICKUP_RADIUS)) {
            if (ent instanceof Item && ((Item) ent).getItemStack().equals(item)) {
                ent.remove();
                break;
            }
        }
        player.getInventory().addItem(item);
        new ArrayList<>(WhenPickup.openingItemPickup.keySet()).forEach(WhenPickup::openInventory);
        lock = false;
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        WhenPickup.openingItemPickup.remove((Player) e.getPlayer());
    }
}
