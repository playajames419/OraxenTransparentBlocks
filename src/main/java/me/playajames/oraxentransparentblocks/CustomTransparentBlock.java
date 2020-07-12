package me.playajames.oraxentransparentblocks;

import me.playajames.oraxentransparentblocks.Utils.ArmorStandUtils;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CustomTransparentBlock {
    @NotNull
    private ArmorStand armorStand;

    public CustomTransparentBlock(ItemStack item, Location location, boolean isVisible, boolean isSmall, boolean hasGravity) {
        this.armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setVisible(isVisible);
        armorStand.setSmall(isSmall);
        armorStand.setGravity(hasGravity);
        armorStand.getEquipment().setHelmet(item);
        CustomTransparentBlockManager.addBlock(this);
    }

    public CustomTransparentBlock(ArmorStand armorStand) {
        this.armorStand = armorStand;
    }

    public CustomTransparentBlock(String serializedCustomBlock, Chunk chunk) {
        this.armorStand = ArmorStandUtils.findArmorStand(serializedCustomBlock, chunk);
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }

//    public void breakNaturally() {
//        World world = armorStand.getWorld();
//        if (getDrops() != null)
//            for (Loot drop : getDrops())
//                drop.dropNaturally(armorStand.getLocation(), 0);
//        destroy();
//    }

    public void setBlockType(ItemStack item) {
        armorStand.getEquipment().setHelmet(item);
    }

    public void destroy() {
        CustomTransparentBlockManager.removeBlock(this);
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
