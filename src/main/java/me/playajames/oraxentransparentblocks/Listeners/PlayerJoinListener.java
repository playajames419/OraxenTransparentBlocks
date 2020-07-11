package me.playajames.oraxentransparentblocks.Listeners;

import me.playajames.oraxentransparentblocks.CustomBlockManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        System.out.println(CustomBlockManager.getBlocks(event.getPlayer().getWorld()).size() + " blocks loaded.");
    }
}
