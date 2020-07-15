package me.playajames.oraxentransparentblocks.Listeners.OraxenBlockListeners;

import me.playajames.oraxentransparentblocks.OraxenTransparentBlock;
import me.playajames.oraxentransparentblocks.OraxenTransparentBlockManager;
import me.playajames.oraxentransparentblocks.Events.OraxenTransparentBlockInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class OraxenTransparentBlockInteractListener implements Listener {

    @EventHandler
    public void onCustomBlockInteract(PlayerInteractAtEntityEvent event) {
        if (!(event.getRightClicked() instanceof ArmorStand)) return;

        ArmorStand armorStand = (ArmorStand) event.getRightClicked();

        if (!OraxenTransparentBlockManager.isBlock(armorStand)) return;

        OraxenTransparentBlock block = OraxenTransparentBlockManager.getBlock(armorStand);

        Bukkit.getPluginManager().callEvent(new OraxenTransparentBlockInteractEvent(event.getPlayer(), block, event.getHand()));

    }

}
