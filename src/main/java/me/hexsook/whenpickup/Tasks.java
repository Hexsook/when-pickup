package me.hexsook.whenpickup;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.KeybindComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Tasks implements Runnable {

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            List<Item> items = new ArrayList<>();
            for (Entity ent : player.getNearbyEntities(WhenPickup.PICKUP_RADIUS, WhenPickup.PICKUP_RADIUS, WhenPickup.PICKUP_RADIUS)) {
                if (ent instanceof Item) {
                    items.add((Item) ent);
                }
            }
            if (!items.isEmpty()) {

                String[] message = Translation.actionbar(player).split("\\{button}");
                KeybindComponent shift = new KeybindComponent();
                shift.setKeybind("key.sneak");

                KeybindComponent swap = new KeybindComponent();
                swap.setKeybind("key.swapOffhand");

                BaseComponent component = new TextComponent("");
                component.addExtra(new TextComponent(message[0]));
                component.addExtra(shift);
                component.addExtra(new TextComponent(" + "));
                component.addExtra(swap);
                component.addExtra(new TextComponent(message[1]));

                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component);
            }
        }

        for (Player player : new ArrayList<>(WhenPickup.openingItemPickup.keySet())) {
            WhenPickup.openInventory(player);
        }
    }


}
