package me.playajames.oraxentransparentblocks;

import me.playajames.oraxentransparentblocks.Utils.ArmorStandUtils;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomBlock {
    @NotNull
    private ArmorStand armorStand;

    public CustomBlock(ArmorStand armorStand) {
        this.armorStand = armorStand;
    }

    public CustomBlock(String serializedCustomBlock, Chunk chunk) {
        this.armorStand = ArmorStandUtils.findArmorStand(serializedCustomBlock, chunk);
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }

    public void breakNaturally() {
        World world = armorStand.getWorld();
        for (ItemStack item : getDrops())
            world.dropItemNaturally(armorStand.getLocation(), item);
        destroy();
    }

    public void setBlockType(ItemStack item) {
        armorStand.getEquipment().setHelmet(item);
    }

    public void destroy() {
        CustomBlockManager.removeBlock(this);
        armorStand.remove();
    }

    private List<ItemStack> getDrops() {
        return null;
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
