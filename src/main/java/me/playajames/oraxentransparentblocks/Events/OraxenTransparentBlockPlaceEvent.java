package me.playajames.oraxentransparentblocks.Events;

import me.playajames.oraxentransparentblocks.CustomTransparentBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OraxenTransparentBlockPlaceEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private Player player;
    private CustomTransparentBlock block;

    public OraxenTransparentBlockPlaceEvent(Player player, CustomTransparentBlock block) {
        this.player = player;
        this.block = block;
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
}