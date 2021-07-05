package me.playajames.oraxentransparentblocks;

import me.playajames.oraxentransparentblocks.Utils.ArmorStandUtils;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class OraxenTransparentBlock {
    @NotNull
    private ArmorStand armorStand;

    public OraxenTransparentBlock(ItemStack item, Location location, boolean isVisible, boolean isSmall, boolean hasGravity) {
        this.armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setVisible(isVisible);
        armorStand.setSmall(isSmall);
        armorStand.setGravity(hasGravity);
        armorStand.getEquipment().setHelmet(item);
        armorStand.addDisabledSlots(EquipmentSlot.HEAD);
        OraxenTransparentBlockManager.addBlock(this);
    }

    public OraxenTransparentBlock(String worldId, String chunkId, String uuidString) {
        this.armorStand = ArmorStandUtils.findArmorStand(worldId, chunkId, uuidString);
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }

    public void setBlockType(ItemStack item) {
        armorStand.getEquipment().setHelmet(item);
    }

    public void destroy() {
        OraxenTransparentBlockManager.removeBlock(this);
        armorStand.remove();
    }

    public String serialize() {
        StringBuilder builder = new StringBuilder();
        String armorstandUuid = armorStand.getUniqueId().toString();

        builder.append(armorstandUuid);

        return builder.toString();

    }

    public String serializeVerbose() {
        StringBuilder builder = new StringBuilder();
        String armorstandUuid = armorStand.getUniqueId().toString();

        builder.append(armorstandUuid).append(",");
        builder.append(armorStand.getLocation().getWorld().getUID()).append(",");
        builder.append(armorStand.getLocation().getChunk().getChunkKey());

        return builder.toString();

    }
}
