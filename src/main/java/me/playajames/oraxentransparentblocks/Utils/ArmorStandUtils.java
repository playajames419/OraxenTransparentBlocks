package me.playajames.oraxentransparentblocks.Utils;

import org.bukkit.Chunk;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

import java.util.UUID;

public class ArmorStandUtils {

    public static ArmorStand findArmorStand(String uuidString, Chunk chunk) {

        Entity[] entities = chunk.getEntities();
        UUID uuid = UUID.fromString(uuidString);
        for (Entity entity : entities)
            if (entity instanceof ArmorStand)
                if (entity.getUniqueId().equals(uuid))
                    return (ArmorStand) entity;

        return null;
    }

}
