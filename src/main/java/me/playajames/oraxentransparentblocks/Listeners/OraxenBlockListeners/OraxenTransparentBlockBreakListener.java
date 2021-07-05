package me.playajames.oraxentransparentblocks.Listeners.OraxenBlockListeners;

import io.th0rgal.oraxen.items.OraxenItems;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import io.th0rgal.oraxen.utils.drops.Loot;
import me.playajames.oraxentransparentblocks.OraxenTransparentBlock;
import me.playajames.oraxentransparentblocks.OraxenTransparentBlockManager;
import me.playajames.oraxentransparentblocks.Events.OraxenTransparentBlockBreakEvent;
import me.playajames.oraxentransparentblocks.Events.OraxenTransparentBlockPreBreakEvent;
import me.playajames.oraxentransparentblocks.OraxenMechanics.TransparentBlockMechanic;
import me.playajames.oraxentransparentblocks.Utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class OraxenTransparentBlockBreakListener implements Listener {

    private final MechanicFactory factory;

    public OraxenTransparentBlockBreakListener(MechanicFactory factory) {
        this.factory = factory;
    }

    @EventHandler
    public void onCustomBlockBreak(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        if (!(event.getEntity() instanceof ArmorStand)) return;

        Player player = (Player) event.getDamager();
        ArmorStand armorStand = (ArmorStand) event.getEntity();

        if (!OraxenTransparentBlockManager.isBlock(armorStand)) return;

        OraxenTransparentBlock block = OraxenTransparentBlockManager.getBlock(armorStand);

        if (!PlayerUtils.canBuild(player, block.getArmorStand().getLocation())) return;

        OraxenTransparentBlockPreBreakEvent preBreakEvent = new OraxenTransparentBlockPreBreakEvent(player, block);

        Bukkit.getPluginManager().callEvent(preBreakEvent);

        if (preBreakEvent.isCancelled()) return;

        ItemStack item = block.getArmorStand().getItem(EquipmentSlot.HEAD);
        Location location = block.getArmorStand().getLocation();

        TransparentBlockMechanic mechanic = (TransparentBlockMechanic) factory.getMechanic(OraxenItems.getIdByItem(item));

        block.destroy();

        if (mechanic.getBreakSound() != null)
            block.getArmorStand().getWorld().playSound(block.getArmorStand().getLocation(), mechanic.getBreakSound(), 1, 1);

        for (Loot loot : mechanic.getDrops()) {
            if (loot == null) {
                Bukkit.getLogger().info("Drop item not found, check configuration file for " + block.getArmorStand().getItem(EquipmentSlot.HEAD).displayName() + ".");
                continue;
            }
            loot.dropNaturally(location, 1);
        }

        Bukkit.getPluginManager().callEvent(new OraxenTransparentBlockBreakEvent(player, item, location));

    }
}
