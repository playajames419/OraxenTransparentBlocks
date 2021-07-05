package me.playajames.oraxentransparentblocks.Events;

import me.playajames.oraxentransparentblocks.OraxenTransparentBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class OraxenTransparentBlockPlaceEvent extends Event  {

    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private Player player;
    private ItemStack item;
    private OraxenTransparentBlock block;

    public OraxenTransparentBlockPlaceEvent(Player player, ItemStack item, OraxenTransparentBlock block) {
        this.player = player;
        this.block = block;
        this.item = item;
        this.canceled = false;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getItem() {
        return item;
    }

    public OraxenTransparentBlock getBlock() {
        return block;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}