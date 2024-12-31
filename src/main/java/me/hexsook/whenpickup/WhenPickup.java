package me.hexsook.whenpickup;

import hexsook.originext.config.ConfigurationException;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class WhenPickup extends JavaPlugin {

    public static final Map<Player, Inventory> openingItemPickup = new HashMap<>();
    public static double PICKUP_RADIUS = 1;
    private static WhenPickup instance;

    @Override
    public void onEnable() {
        instance = this;

        try {
            Config.load();
            PICKUP_RADIUS = Config.getConfig().getDouble("pickup-radius", 1);
        } catch (ConfigurationException e) {
            getLogger().warning("Cannot load config.yml");
        }

        getServer().getPluginManager().registerEvents(new Listeners(), this);
        Bukkit.getScheduler().runTaskTimer(this, new Tasks(), 0, 5);

        getLogger().info("Finished loading, thanks for using WhenPickup by Hexsook!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Goodbye, thanks for using WhenPickup by Hexsook!");
    }

    public static WhenPickup getInstance() {
        return instance;
    }

    private static final int[] ITEM_SLOTS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
            20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};
    private static final int[] FRAME_SLOTS = {36, 37, 38, 39, 40, 41, 42, 43, 44};
    private static final int[] PLAYER_SLOTS = {45, 46, 47, 48, 49, 50, 51, 52, 53};

    public static void openInventory(Player player) {
        Inventory inv = Bukkit.createInventory(new WhenPickupHolder(), 54, Translation.inventoryTitle(player));

        {
            ItemStack frameItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta meta = frameItem.getItemMeta();
            meta.setDisplayName(Translation.inventoryFrameItem(player));
            meta.setLore(Collections.singletonList(Translation.inventoryFramePlayers(player)));
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            frameItem.setItemMeta(meta);
            for (int frameSlot : FRAME_SLOTS) {
                inv.setItem(frameSlot, frameItem);
            }
        }

        {
            List<Item> items = new ArrayList<>();
            for (Entity ent : player.getNearbyEntities(WhenPickup.PICKUP_RADIUS, WhenPickup.PICKUP_RADIUS, WhenPickup.PICKUP_RADIUS)) {
                if (ent instanceof Item) {
                    items.add((Item) ent);
                }
            }
            if (!items.isEmpty()) {
                for (int i = 0; i < ITEM_SLOTS.length; i++) {
                    if (i >= items.size()) {
                        break;
                    }

                    inv.setItem(ITEM_SLOTS[i], items.get(i).getItemStack());
                }
            }
        }

        {
            List<Player> players = new ArrayList<>();

            for (Entity ent : player.getNearbyEntities(2, 2, 2)) {
                if (ent instanceof Player && openingItemPickup.containsKey((Player) ent)) {
                    players.add((Player) ent);
                }
            }
            if (!players.isEmpty()) {
                for (int i = 0; i < PLAYER_SLOTS.length; i++) {
                    if (i >= players.size()) {
                        break;
                    }

                    ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                    SkullMeta sm = (SkullMeta) skull.getItemMeta();
                    sm.setDisplayName("§a" + players.get(i).getDisplayName());
                    sm.setOwningPlayer(players.get(i));
                    sm.setUnbreakable(true);
                    sm.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    skull.setItemMeta(sm);
                    inv.setItem(PLAYER_SLOTS[i], skull);
                }
            } else {
                ItemStack empty = new ItemStack(Material.RED_STAINED_GLASS_PANE);
                ItemMeta meta = empty.getItemMeta();
                meta.setDisplayName(Translation.inventoryNoNearbyPlayers(player));
                meta.setUnbreakable(true);
                meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                empty.setItemMeta(meta);
                for (int playerSlot : PLAYER_SLOTS) {
                    inv.setItem(playerSlot, empty);
                }
            }
        }

        player.openInventory(inv);
        openingItemPickup.put(player, inv);
    }
}
