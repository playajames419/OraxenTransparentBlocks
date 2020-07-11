package me.playajames.oraxentransparentblocks.Listeners;

import io.th0rgal.oraxen.items.OraxenItems;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import me.playajames.oraxentransparentblocks.CustomBlockManager;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CustomBlockPlaceListener implements Listener {
    private final MechanicFactory factory;

    public CustomBlockPlaceListener(MechanicFactory factory) {
        this.factory = factory;
    }

    @EventHandler
    public void onCustomBlockPlace(PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;

        String oraxenItemId = OraxenItems.getIdByItem(event.getPlayer().getInventory().getItemInMainHand());
        if (oraxenItemId == null) return;

        if (factory.isNotImplementedIn(oraxenItemId)) return;

        System.out.println("CustomBlockPlaceListener triggered.");
    }
}
