package me.playajames.oraxentransparentblocks.Listeners.OraxenBlockListeners;

import io.th0rgal.oraxen.items.OraxenItems;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import me.playajames.oraxentransparentblocks.OraxenTransparentBlock;
import me.playajames.oraxentransparentblocks.OraxenTransparentBlockManager;
import me.playajames.oraxentransparentblocks.Events.OraxenTransparentBlockPlaceEvent;
import me.playajames.oraxentransparentblocks.Events.OraxenTransparentBlockPrePlaceEvent;
import me.playajames.oraxentransparentblocks.OraxenMechanics.TransparentBlockMechanic;
import me.playajames.oraxentransparentblocks.Utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class OraxenTransparentBlockPlaceListener implements Listener {

    private final MechanicFactory factory;

    public OraxenTransparentBlockPlaceListener(MechanicFactory factory) {
        this.factory = factory;
    }

    @EventHandler
    public void onCustomBlockPlace(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getHand() != EquipmentSlot.HAND) return;

        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

        String oraxenItemId = OraxenItems.getIdByItem(item);
        if (oraxenItemId == null) return;

        if (factory.isNotImplementedIn(oraxenItemId)) return;

        Location location = event.getClickedBlock().getLocation().getBlock().getRelative(event.getBlockFace()).getLocation();
        location.add(0.5, 0, 0.5);

        // Check is armorstand already exists at location
        for (Entity entity : location.getWorld().getNearbyEntities(location, 0.5, 1.0, 0.5)) {
            if (!(entity instanceof ArmorStand)) break;
            if (OraxenTransparentBlockManager.isBlock((ArmorStand) entity)) return;
        }

        if (!PlayerUtils.canBuild(event.getPlayer(), location)) return;

        TransparentBlockMechanic mechanic = (TransparentBlockMechanic) factory.getMechanic(oraxenItemId);

        OraxenTransparentBlockPrePlaceEvent prePlaceEvent = new OraxenTransparentBlockPrePlaceEvent(event.getPlayer(), item, location);

        Bukkit.getPluginManager().callEvent(prePlaceEvent);

        if (prePlaceEvent.isCancelled()) return;

        event.getPlayer().getInventory().setItemInMainHand(item.clone().subtract(1));

        OraxenTransparentBlock block = new OraxenTransparentBlock(item, location, mechanic.isVisible(), mechanic.isSmall(), mechanic.hasGravity());

        Bukkit.getPluginManager().callEvent(new OraxenTransparentBlockPlaceEvent(event.getPlayer(), item, block));

    }
}
