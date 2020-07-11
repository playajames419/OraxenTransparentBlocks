package me.playajames.oraxentransparentblocks;

import de.leonhard.storage.Yaml;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.*;

import static me.playajames.oraxentransparentblocks.OraxenTransparentBlocks.CONFIG;

public class CustomBlockManager {
    private static HashMap<World, HashMap<Chunk, HashMap<UUID, CustomBlock>>> loadedBlocks = new HashMap<>();
    private static Plugin plugin = OraxenTransparentBlocks.getPlugin(OraxenTransparentBlocks.class);
    private static String dataFolderPath = OraxenTransparentBlocks.getPlugin(OraxenTransparentBlocks.class).getDataFolder().getPath() + "/data/";

    public static CustomBlock createBlock(Location location, ItemStack item) {
        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setVisible(CONFIG.getBoolean("armorstandsVisible"));
        armorStand.getEquipment().setHelmet(item);
        return new CustomBlock(armorStand);
    }

    public static boolean addBlock(CustomBlock block) {
        plugin.getLogger().info("Adding block...");
        Chunk chunk = block.getArmorStand().getLocation().getChunk();
        if (!isChunkLoaded(chunk)) return false;
        plugin.getLogger().info("Chunk is loaded.");
        if (loadedBlocks.get(chunk.getWorld()).get(chunk).containsKey(block.getArmorStand().getUniqueId())) return false;
        plugin.getLogger().info("Block already exists.");
        loadedBlocks.get(chunk.getWorld()).get(chunk).put(block.getArmorStand().getUniqueId(), block);
        plugin.getLogger().info("Block added successfully. >> " + block.serializeVerbose());
        return true;
    }

    public static boolean removeBlock(CustomBlock block) {
        Chunk chunk = block.getArmorStand().getLocation().getChunk();
        if (!isChunkLoaded(chunk)) return false;
        if (!loadedBlocks.get(chunk.getWorld()).get(chunk).containsKey(block.getArmorStand().getUniqueId())) return false;
        loadedBlocks.get(chunk.getWorld()).get(chunk).remove(block.getArmorStand().getUniqueId());
        return true;
    }

    private static boolean worldMapExists(World world) {
        if (loadedBlocks.isEmpty()) return false;
        if (loadedBlocks.containsKey(world)) return true;
        return false;
    }

    private static void generateWorldMap(World world) {
        loadedBlocks.put(world, new HashMap<>());
    }

    private static void removeWorldMapIfEmpty(World world) {
        if (loadedBlocks.get(world).isEmpty())
            loadedBlocks.remove(world);
    }

    private static boolean isChunkLoaded(Chunk chunk) {
        if (!worldMapExists(chunk.getWorld())) return false;
        if (loadedBlocks.get(chunk.getWorld()).containsKey(chunk)) return true;
        return false;
    }

    public static boolean loadChunk(Chunk chunk) {
        Yaml storage = new Yaml(String.valueOf(chunk.getChunkKey()), dataFolderPath + chunk.getWorld().getUID());
        HashMap<UUID, CustomBlock> blocks = parseChunkStorageFile(chunk, storage);
        if (!worldMapExists(chunk.getWorld())) generateWorldMap(chunk.getWorld());
        if (isChunkLoaded(chunk)) return true;
        loadedBlocks.get(chunk.getWorld()).put(chunk, blocks);
        return false;
    }

    public static boolean unloadChunk(Chunk chunk) {
        if (!isChunkLoaded(chunk)) return true;
        loadedBlocks.get(chunk.getWorld()).remove(chunk);
        removeWorldMapIfEmpty(chunk.getWorld());
        return false;
    }

    public static List<CustomBlock> getBlocks(World world) {
        List<CustomBlock> blocksList = new ArrayList<CustomBlock>();
        if (loadedBlocks.isEmpty()) return null;
        for (HashMap<UUID, CustomBlock> chunkBlockMap : loadedBlocks.get(world).values())
            for (CustomBlock block : chunkBlockMap.values())
                blocksList.add(block);
        return blocksList;
    }

    public static List<CustomBlock> getBlocks(Chunk chunk) {
        if (!isChunkLoaded(chunk)) return null;
        List<CustomBlock> blocksList = new ArrayList<>();
        for (CustomBlock block : loadedBlocks.get(chunk.getWorld()).get(chunk).values())
            blocksList.add(block);
        return blocksList;
    }

    public static void saveWorld(World world) {
        if (!worldMapExists(world)) return;
        for (Chunk chunk : loadedBlocks.get(world).keySet())
            saveChunk(chunk);
    }

    public static void saveChunk(Chunk chunk) {
        if (!isChunkLoaded(chunk)) return;
        Yaml storage = new Yaml(String.valueOf(chunk.getChunkKey()), dataFolderPath + chunk.getWorld().getUID());
        for (CustomBlock block : loadedBlocks.get(chunk.getWorld()).get(chunk).values())
            storage.set(block.serialize(), null);
    }

    public static void saveAll() {
        for (World world : loadedBlocks.keySet())
            saveWorld(world);
    }

    public static int clean() {
        return -1;
    }

    public static int clean(long chunkId) {
        return -1;
    }

    public static boolean destroyChunkStorageFile(long chunkId) {
        return false;
    }

    private static HashMap<UUID, CustomBlock> parseChunkStorageFile(Chunk chunk, Yaml storage) {

        HashMap<UUID, CustomBlock> blocks = new HashMap<>();

        Set<String> sections = storage.keySet();

        for (String section : sections) {
            CustomBlock block = new CustomBlock(section, chunk);
            blocks.put(block.getArmorStand().getUniqueId(), block);
        }

        return blocks;
    }

    private static List<ArmorStand> getArmorStands(Chunk chunk) {
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
