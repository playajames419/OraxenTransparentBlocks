package me.playajames.oraxentransparentblocks.Listeners;

import me.playajames.oraxentransparentblocks.CustomTransparentBlockManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

public class ChunkUnloadListener implements Listener {
    @EventHandler
    public void onChunkUnload(ChunkUnloadEvent event) {
        CustomTransparentBlockManager.unloadChunk(event.getChunk());
    }
}
