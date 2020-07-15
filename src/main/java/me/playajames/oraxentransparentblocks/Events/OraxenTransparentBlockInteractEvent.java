package me.playajames.oraxentransparentblocks.Events;

import me.playajames.oraxentransparentblocks.OraxenTransparentBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.inventory.EquipmentSlot;

public class OraxenTransparentBlockInteractEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private Player player;
    private OraxenTransparentBlock block;
    private EquipmentSlot hand;

    public OraxenTransparentBlockInteractEvent(Player player, OraxenTransparentBlock block, EquipmentSlot hand) {
        this.player = player;
        this.block = block;
        this.hand = hand;
        this.canceled = false;
    }

    public Player getPlayer() {
        return player;
    }

    public OraxenTransparentBlock getBlock() {
        return block;
    }

    public EquipmentSlot getHand() {
        return hand;
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
