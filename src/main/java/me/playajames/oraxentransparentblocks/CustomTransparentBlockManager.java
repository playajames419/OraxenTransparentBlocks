package me.playajames.oraxentransparentblocks;

import de.leonhard.storage.Yaml;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.*;

import static me.playajames.oraxentransparentblocks.OraxenTransparentBlocks.CONFIG;

public class CustomTransparentBlockManager {
    private static HashMap<World, HashMap<Chunk, HashMap<UUID, CustomTransparentBlock>>> loadedBlocks = new HashMap<>();
    private static Plugin plugin = OraxenTransparentBlocks.getPlugin(OraxenTransparentBlocks.class);
    private static String dataFolderPath = OraxenTransparentBlocks.getPlugin(OraxenTransparentBlocks.class).getDataFolder().getPath() + "/data/";

    public static boolean isBlock(ArmorStand armorStand) {
        List<CustomTransparentBlock> blocks = getBlocks(armorStand.getChunk());
        for (CustomTransparentBlock block : blocks)
            if (block.getArmorStand().getUniqueId().equals(armorStand.getUniqueId()))
                return true;
        return false;
    }

    public static boolean addBlock(CustomTransparentBlock block) {
        if (CONFIG.getBoolean("debug")) plugin.getLogger().info("Adding block...");
        Chunk chunk = block.getArmorStand().getLocation().getChunk();
        if (!isChunkLoaded(chunk)) return false;
        if (CONFIG.getBoolean("debug")) plugin.getLogger().info("Chunk is loaded.");
        if (loadedBlocks.get(chunk.getWorld()).get(chunk).containsKey(block.getArmorStand().getUniqueId())) return false;
        loadedBlocks.get(chunk.getWorld()).get(chunk).put(block.getArmorStand().getUniqueId(), block);
        if (CONFIG.getBoolean("debug")) plugin.getLogger().info("Block added successfully. >> " + block.serializeVerbose());
        return true;
    }

    public static boolean removeBlock(CustomTransparentBlock block) {
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
        HashMap<UUID, CustomTransparentBlock> blocks = parseChunkStorageFile(chunk, storage);
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

    public static CustomTransparentBlock getBlock(ArmorStand armorStand) {
        return new CustomTransparentBlock(armorStand);
    }

    public static List<CustomTransparentBlock> getBlocks(World world) {
        List<CustomTransparentBlock> blocksList = new ArrayList<CustomTransparentBlock>();
        if (loadedBlocks.isEmpty()) return null;
        for (HashMap<UUID, CustomTransparentBlock> chunkBlockMap : loadedBlocks.get(world).values())
            for (CustomTransparentBlock block : chunkBlockMap.values())
                blocksList.add(block);
        return blocksList;
    }

    public static List<CustomTransparentBlock> getBlocks(Chunk chunk) {
        if (!isChunkLoaded(chunk)) return null;
        List<CustomTransparentBlock> blocksList = new ArrayList<>();
        for (CustomTransparentBlock block : loadedBlocks.get(chunk.getWorld()).get(chunk).values())
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
        Collection<CustomTransparentBlock> blocks = loadedBlocks.get(chunk.getWorld()).get(chunk).values();
        Set<String> activeStoredBlocks = storage.keySet();
        for (CustomTransparentBlock block : blocks) {
            if (activeStoredBlocks.contains(block.getArmorStand().getUniqueId().toString())) {
                activeStoredBlocks.remove(block.getArmorStand().getUniqueId().toString());
                continue;
            }
            storage.set(block.serialize(), null);
        }
        if (activeStoredBlocks.isEmpty()) return;
        for (String key : activeStoredBlocks)
            storage.remove(key);
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

    private static HashMap<UUID, CustomTransparentBlock> parseChunkStorageFile(Chunk chunk, Yaml storage) {

        HashMap<UUID, CustomTransparentBlock> blocks = new HashMap<>();

        Set<String> sections = storage.keySet();

        for (String section : sections) {
            CustomTransparentBlock block = new CustomTransparentBlock(section, chunk);
            if (block.getArmorStand() == null) continue;
            blocks.put(block.getArmorStand().getUniqueId(), block);
        }

        return blocks;
    }
}
