package me.playajames.oraxentransparentblocks.Listeners.CustomBlockListeners;

import io.th0rgal.oraxen.items.OraxenItems;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import me.playajames.oraxentransparentblocks.CustomTransparentBlock;
import me.playajames.oraxentransparentblocks.Events.OraxenTransparentBlockPlaceEvent;
import me.playajames.oraxentransparentblocks.Events.OraxenTransparentBlockPrePlaceEvent;
import me.playajames.oraxentransparentblocks.OraxenMechanics.TransparentBlockMechanic;
import me.playajames.oraxentransparentblocks.Utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class CustomTransparentBlockPlaceListener implements Listener {

    private final MechanicFactory factory;

    public CustomTransparentBlockPlaceListener(MechanicFactory factory) {
        this.factory = factory;
    }

    @EventHandler
    public void onCustomBlockPlace(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getHand() != EquipmentSlot.HAND) return;

        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

        String oraxenItemId = OraxenItems.getIdByItem(item);
        if (oraxenItemId == null) return;

        if (!factory.getMechanicID().equals("transparent_block")) return;

        Location location = event.getClickedBlock().getLocation().getBlock().getRelative(event.getBlockFace()).getLocation();
        location.add(0.5, 0, 0.5);

        if (!PlayerUtils.canBuild(event.getPlayer(), location)) return;

        //TransparentBlockMechanic mechanic = (TransparentBlockMechanic) factory.getMechanic(oraxenItemId);

        Bukkit.getPluginManager().callEvent(new OraxenTransparentBlockPrePlaceEvent(event.getPlayer(), item, location));

        event.getPlayer().getInventory().setItemInMainHand(item.clone().subtract(1));

        CustomTransparentBlock block = new CustomTransparentBlock(item, location, false, true);

        Bukkit.getPluginManager().callEvent(new OraxenTransparentBlockPlaceEvent(event.getPlayer(), block));

    }
}
