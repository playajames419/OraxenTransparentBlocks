package me.playajames.oraxentransparentblocks.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ArmorStandUtils {

    public static ArmorStand findArmorStand(String worldId, String chunkId, String uuidString) {
        Chunk chunk = Bukkit.getWorld(UUID.fromString(worldId)).getChunkAt(Long.parseLong(chunkId));
        Entity[] entities = chunk.getEntities();
        UUID uuid = UUID.fromString(uuidString);
        for (Entity entity : entities)
            if (entity instanceof ArmorStand)
                if (entity.getUniqueId().equals(uuid))
                    return (ArmorStand) entity;
        return null;
    }

    public static List<ArmorStand> getArmorStands(Chunk chunk) {
        List<Entity> allEntities = Arrays.asList(chunk.getEntities());
        if (allEntities.isEmpty()) return null;
        List<ArmorStand> armorStands = new ArrayList<>();
        for (Entity entity : allEntities) {
            if (entity instanceof ArmorStand)
                armorStands.add((ArmorStand) entity);
        }
        return armorStands.isEmpty() ? null : armorStands;
    }

}
