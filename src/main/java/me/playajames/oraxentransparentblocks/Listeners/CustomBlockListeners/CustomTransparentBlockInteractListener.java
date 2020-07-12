package me.playajames.oraxentransparentblocks.Listeners.CustomBlockListeners;

import me.playajames.oraxentransparentblocks.CustomTransparentBlock;
import me.playajames.oraxentransparentblocks.CustomTransparentBlockManager;
import me.playajames.oraxentransparentblocks.Events.OraxenTransparentBlockInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class CustomTransparentBlockInteractListener implements Listener {

    @EventHandler
    public void onCustomBlockInteract(PlayerInteractAtEntityEvent event) {
        if (!(event.getRightClicked() instanceof ArmorStand)) return;

        ArmorStand armorStand = (ArmorStand) event.getRightClicked();

        if (!CustomTransparentBlockManager.isBlock(armorStand)) return;

        CustomTransparentBlock block = CustomTransparentBlockManager.getBlock(armorStand);

        Bukkit.getPluginManager().callEvent(new OraxenTransparentBlockInteractEvent(event.getPlayer(), block, event.getHand()));

    }

}
