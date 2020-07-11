package me.playajames.oraxentransparentblocks;

import de.leonhard.storage.Config;
import io.th0rgal.oraxen.mechanics.MechanicsManager;
import me.playajames.oraxentransparentblocks.Listeners.ChunkLoadListener;
import me.playajames.oraxentransparentblocks.Listeners.ChunkUnloadListener;
import me.playajames.oraxentransparentblocks.Listeners.PlayerJoinListener;
import me.playajames.oraxentransparentblocks.OraxenMechanics.TransparentBlockMechanicFactory;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class OraxenTransparentBlocks extends JavaPlugin {

    public static Config CONFIG;

    @Override
    public void onEnable() {
        loadConfig();
        registerListeners();
        //this.getServer().getPluginManager().registerEvents(new JoinListener(), this);
        MechanicsManager.registerMechanicFactory("transparent", TransparentBlockMechanicFactory.class);
        Bukkit.getLogger().info("Enabled successfully.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadConfig() {
        this.saveDefaultConfig();
        CONFIG = new Config("config", "plugins/OraxenTransparentBlocks");
        String debug = CONFIG.getBoolean("debug") ? "enabled" : "disabled";
        Bukkit.getLogger().info("Debugging is " + debug + ".");
    }

    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new ChunkLoadListener(), this);
        this.getServer().getPluginManager().registerEvents(new ChunkUnloadListener(), this);
    }
}
