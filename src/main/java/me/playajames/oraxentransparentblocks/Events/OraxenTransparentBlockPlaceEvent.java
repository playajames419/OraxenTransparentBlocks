package me.playajames.oraxentransparentblocks.Events;

import me.playajames.oraxentransparentblocks.OraxenTransparentBlock;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class OraxenTransparentBlockPlaceEvent extends Event  {

    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private Player player;
    private ItemStack item;
    private Block clickedBlock;
    private OraxenTransparentBlock block;

    public OraxenTransparentBlockPlaceEvent(Player player, ItemStack item, Block clickedBlock, OraxenTransparentBlock block) {
        this.player = player;
        this.block = block;
        this.item = item;
        this.clickedBlock = clickedBlock;
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