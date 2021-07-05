package me.playajames.oraxentransparentblocks.Events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class OraxenTransparentBlockPrePlaceEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private Player player;
    private ItemStack item;
    private Location location;

    public OraxenTransparentBlockPrePlaceEvent(Player player, ItemStack item, Location location) {
        this.player = player;
        this.item = item;
        this.location = location;
        this.canceled = false;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public Location getLocation() {
        return location;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isCancelled() {
        return canceled;
    }

    @Override
    public void setCancelled(boolean canceled) {
        this.canceled = canceled;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
