package me.playajames.oraxentransparentblocks.OraxenMechanics;

import io.th0rgal.oraxen.mechanics.MechanicFactory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class TransparentBlockMechanicListener implements Listener {

    private final MechanicFactory factory;

    public TransparentBlockMechanicListener(TransparentBlockMechanicFactory factory) {
        this.factory = factory;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        event.getPlayer().sendMessage("You win!");
    }

}