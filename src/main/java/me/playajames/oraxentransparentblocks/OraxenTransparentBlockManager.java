package me.playajames.oraxentransparentblocks;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static me.playajames.oraxentransparentblocks.OraxenTransparentBlocks.CONNECTION;

public class OraxenTransparentBlockManager {

    public static boolean isBlock(ArmorStand armorStand) {
        if (getBlock(armorStand) != null) return true;
        return false;
    }

    public static boolean addBlock(OraxenTransparentBlock block) {
        UUID world = block.getArmorStand().getLocation().getWorld().getUID();
        long chunk = block.getArmorStand().getLocation().getChunk().getChunkKey();
        UUID uuid = block.getArmorStand().getUniqueId();
        try {
            Statement statement = CONNECTION.createStatement();
            statement.executeUpdate("INSERT INTO 'blocks' (world,chunk,uuid) VALUES('" + world + "','" + chunk + "','" + uuid + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean removeBlock(OraxenTransparentBlock block) {
        UUID world = block.getArmorStand().getLocation().getWorld().getUID();
        long chunk = block.getArmorStand().getLocation().getChunk().getChunkKey();
        UUID uuid = block.getArmorStand().getUniqueId();
        try {
            Statement statement = CONNECTION.createStatement();
            statement.executeUpdate("DELETE FROM 'blocks' WHERE world = '" + world + "' AND chunk = '" + chunk + "' AND uuid = '" + uuid + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public static OraxenTransparentBlock getBlock(ArmorStand armorStand) {
        String world = armorStand.getLocation().getWorld().getUID().toString();
        String chunk = String.valueOf(armorStand.getLocation().getChunk().getChunkKey());
        String uuid = armorStand.getUniqueId().toString();
        OraxenTransparentBlock block = null;
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM blocks WHERE world = '" + world + "' AND chunk = '" + chunk + "' AND uuid = '" + uuid + "'");
            if (result.next()) {
                block = new OraxenTransparentBlock(
                        result.getString("world"),
                        result.getString("chunk"),
                        result.getString("uuid"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return block;
    }

    public static List<OraxenTransparentBlock> getBlocks() {
        List<OraxenTransparentBlock> blocks = new ArrayList<OraxenTransparentBlock>();
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM 'blocks'");
            while (result.next())
                blocks.add(new OraxenTransparentBlock(result.getString("world"), result.getString("chunk"), result.getString("uuid")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return blocks;
    }

    public static List<OraxenTransparentBlock> getBlocks(World world) {
        List<OraxenTransparentBlock> blocks = new ArrayList<OraxenTransparentBlock>();
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM 'blocks' WHERE world = '" + world.getUID() + "'");
            while (result.next())
                blocks.add(new OraxenTransparentBlock(result.getString("world"), result.getString("chunk"), result.getString("uuid")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return blocks;
    }

    public static List<OraxenTransparentBlock> getBlocks(Chunk chunk) {
        List<OraxenTransparentBlock> blocks = new ArrayList<OraxenTransparentBlock>();
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM 'blocks' WHERE world = '" + chunk.getWorld().getUID() + "' AND chunk = '" + chunk.getChunkKey() + "'");
            while (result.next())
                blocks.add(new OraxenTransparentBlock(result.getString("world"), result.getString("chunk"), result.getString("uuid")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return blocks;
    }

}
