package me.playajames.oraxentransparentblocks;

import de.leonhard.storage.Config;
import io.th0rgal.oraxen.mechanics.MechanicsManager;
import me.playajames.oraxentransparentblocks.Commands.OTBCommand;
import me.playajames.oraxentransparentblocks.Listeners.ChunkLoadListener;
import me.playajames.oraxentransparentblocks.Listeners.ChunkUnloadListener;
import me.playajames.oraxentransparentblocks.OraxenMechanics.TransparentBlockMechanicFactory;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class OraxenTransparentBlocks extends JavaPlugin {

    public static Config CONFIG;

    @Override
    public void onEnable() {
        loadConfig();
        registerListeners();
        registerCommands();
        startScheduler();
        MechanicsManager.registerMechanicFactory("transparent_block", TransparentBlockMechanicFactory.class);
        getLogger().info("Mechanic 'transparentBlock' registered with Oraxen.");
        getLogger().info("Enabled successfully.");
    }

    @Override
    public void onDisable() {
        OraxenTransparentBlockManager.saveAll();
        getLogger().info("Disabled successfully.");
    }

    private void loadConfig() {
        this.saveDefaultConfig();
        CONFIG = new Config("config", "plugins/OraxenTransparentBlocks");
        String debug = CONFIG.getBoolean("debug") ? "enabled" : "disabled";
        getLogger().info("Debugging is " + debug + ".");
    }

    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new ChunkLoadListener(), this);
        this.getServer().getPluginManager().registerEvents(new ChunkUnloadListener(), this);
    }

    private void registerCommands() {
        this.getCommand("otb").setExecutor(new OTBCommand());
    }

    private void startScheduler() {
        try {
            Bukkit.getScheduler().runTaskTimer(this, () -> OraxenTransparentBlockManager.saveAll(), 20L * CONFIG.getInt("save_interval"), 20L * CONFIG.getInt("save_interval"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO implement drops for blocks
    // TODO implement way to limit about of entites to render, make command to allow players to lower this value


}
