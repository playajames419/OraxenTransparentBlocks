package me.playajames.oraxentransparentblocks.Events;

import me.playajames.oraxentransparentblocks.CustomTransparentBlock;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class OraxenTransparentBlockBreakEvent extends Event implements Cancellable {

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

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return canceled;
    }

    @Override
    public void setCancelled(boolean canceled) {
        this.canceled = canceled;
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
}
