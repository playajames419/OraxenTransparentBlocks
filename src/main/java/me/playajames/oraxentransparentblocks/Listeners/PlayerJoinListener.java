package me.playajames.oraxentransparentblocks.Listeners;

import me.playajames.oraxentransparentblocks.CustomBlock;
import me.playajames.oraxentransparentblocks.CustomBlockManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        CustomBlockManager.createBlock(event.getPlayer().getLocation(), new ItemStack(Material.DIRT));
    }
}
