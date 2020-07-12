package me.playajames.oraxentransparentblocks.Listeners;

import me.playajames.oraxentransparentblocks.CustomTransparentBlockManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class ChunkLoadListener implements Listener {
    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        CustomTransparentBlockManager.loadChunk(event.getChunk());
    }
}
