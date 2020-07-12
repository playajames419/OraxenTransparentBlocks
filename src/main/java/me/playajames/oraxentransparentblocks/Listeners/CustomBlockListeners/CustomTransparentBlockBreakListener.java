package me.playajames.oraxentransparentblocks.Listeners.CustomBlockListeners;

import io.th0rgal.oraxen.mechanics.MechanicFactory;
import me.playajames.oraxentransparentblocks.CustomTransparentBlock;
import me.playajames.oraxentransparentblocks.CustomTransparentBlockManager;
import me.playajames.oraxentransparentblocks.Events.OraxenTransparentBlockBreakEvent;
import me.playajames.oraxentransparentblocks.Events.OraxenTransparentBlockPreBreakEvent;
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

public class CustomTransparentBlockBreakListener implements Listener {

    private final MechanicFactory factory;

    public CustomTransparentBlockBreakListener(MechanicFactory factory) {
        this.factory = factory;
    }

    @EventHandler
    public void onCustomBlockBreak(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        if (!(event.getEntity() instanceof ArmorStand)) return;

        Player player = (Player) event.getDamager();
        ArmorStand armorStand = (ArmorStand) event.getEntity();

        if (!CustomTransparentBlockManager.isBlock(armorStand)) return;

        CustomTransparentBlock block = CustomTransparentBlockManager.getBlock(armorStand);

        if (!PlayerUtils.canBuild(player, block.getArmorStand().getLocation())) return;

        Bukkit.getPluginManager().callEvent(new OraxenTransparentBlockPreBreakEvent(player, block));

        ItemStack item = block.getArmorStand().getItem(EquipmentSlot.HEAD);
        Location location = block.getArmorStand().getLocation();

        block.breakNaturally();

        Bukkit.getPluginManager().callEvent(new OraxenTransparentBlockBreakEvent(player, item, location));

    }
}
