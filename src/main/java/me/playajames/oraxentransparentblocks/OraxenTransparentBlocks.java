package me.playajames.oraxentransparentblocks;

import de.leonhard.storage.Config;
import io.th0rgal.oraxen.mechanics.MechanicsManager;
import me.playajames.oraxentransparentblocks.Commands.OTBCommand;
import me.playajames.oraxentransparentblocks.Listeners.OraxenBlockListeners.OraxenTransparentBlockInteractListener;
import me.playajames.oraxentransparentblocks.OraxenMechanics.TransparentBlockMechanicFactory;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class OraxenTransparentBlocks extends JavaPlugin {

    public static Config CONFIG;
    public static boolean DEBUG = false;
    public static Connection CONNECTION;

    @Override
    public void onEnable() {
        loadConfig();
        if (!initDatabase())
            Bukkit.getPluginManager().disablePlugin(this);
        registerCommands();
        MechanicsManager.registerMechanicFactory("transparent_block", TransparentBlockMechanicFactory.class);
        if (!verifyMechanic()) return;
        registerListeners();
        getLogger().info("Mechanic 'transparent_block' registered with Oraxen.");
        getLogger().info("Enabled successfully.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled successfully.");
    }

    private void loadConfig() {
        this.saveDefaultConfig();
        CONFIG = new Config("config", "plugins/OraxenTransparentBlocks");
        String debug = CONFIG.getBoolean("debug") ? "enabled" : "disabled";
        getLogger().info("Debugging is " + debug + ".");
        if (debug == "enabled") DEBUG = true;
    }

    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new OraxenTransparentBlockInteractListener(), OraxenTransparentBlocks.getPlugin(OraxenTransparentBlocks.class));
        // Other events registered in TransparentBlockMechanicFactory.class
    }

    private void registerCommands() {
        this.getCommand("otb").setExecutor(new OTBCommand());
    }

    private boolean verifyMechanic() {
        if (MechanicsManager.getMechanicFactory("transparent_block") == null) {
            getLogger().info("`transparent_block` mechanic has not been added to the Oraxen mechanics.yml file.");
            Bukkit.getPluginManager().disablePlugin(this);
            return false;
        }
        return true;
    }

    private boolean initDatabase() {
        try {
            CONNECTION = DriverManager.getConnection("jdbc:sqlite:plugins/OraxenTransparentBlocks/storage.db");
            CONNECTION.createStatement().executeUpdate(
                "CREATE TABLE IF NOT EXISTS 'blocks' (" +
                        "'id' INTEGER NOT NULL UNIQUE, " +
                        "'world' TEXT NOT NULL, " +
                        "'chunk' TEXT NOT NULL, " +
                        "'uuid' TEXT NOT NULL, " +
                        "PRIMARY KEY('id' AUTOINCREMENT)" +
                    ");"
            );
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    // TODO implement way to limit amount of entites to render, make command to allow players to lower this value
}
