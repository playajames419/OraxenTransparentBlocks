package me.playajames.oraxentransparentblocks.Events;

import me.playajames.oraxentransparentblocks.CustomTransparentBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class OraxenTransparentBlockPreBreakEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private Player player;
    private CustomTransparentBlock block;

    public OraxenTransparentBlockPreBreakEvent(Player player, CustomTransparentBlock block) {
        this.player = player;
        this.block = block;
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

    public CustomTransparentBlock getBlock() {
        return block;
    }
}
