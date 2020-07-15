package me.playajames.oraxentransparentblocks.Events;

import me.playajames.oraxentransparentblocks.OraxenTransparentBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OraxenTransparentBlockPreBreakEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private Player player;
    private OraxenTransparentBlock block;

    public OraxenTransparentBlockPreBreakEvent(Player player, OraxenTransparentBlock block) {
        this.player = player;
        this.block = block;
        this.canceled = false;
    }

    public Player getPlayer() {
        return player;
    }

    public OraxenTransparentBlock getBlock() {
        return block;
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
