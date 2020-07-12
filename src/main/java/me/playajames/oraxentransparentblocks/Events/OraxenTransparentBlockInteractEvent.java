package me.playajames.oraxentransparentblocks.Events;

import me.playajames.oraxentransparentblocks.CustomTransparentBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.inventory.EquipmentSlot;

public class OraxenTransparentBlockInteractEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private Player player;
    private CustomTransparentBlock block;
    private EquipmentSlot hand;

    public OraxenTransparentBlockInteractEvent(Player player, CustomTransparentBlock block, EquipmentSlot hand) {
        this.player = player;
        this.block = block;
        this.hand = hand;
        this.canceled = false;
    }

    @Override
    public boolean isCancelled() {
        return canceled;
    }

    @Override
    public void setCancelled(boolean canceled) {
        this.canceled = canceled;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public CustomTransparentBlock getBlock() {
        return block;
    }

    public EquipmentSlot getHand() {
        return hand;
    }
}
