package me.playajames.oraxentransparentblocks.Events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class OraxenTransparentBlockBreakEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private Player player;
    private ItemStack item;
    private Location location;

    public OraxenTransparentBlockBreakEvent(Player player, ItemStack item, Location location) {
        this.player = player;
        this.item = item;
        this.location = location;
        this.canceled = false;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getItem() {
        return item;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
