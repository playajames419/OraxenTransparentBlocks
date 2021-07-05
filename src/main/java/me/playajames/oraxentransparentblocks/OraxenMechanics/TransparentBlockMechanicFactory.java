package me.playajames.oraxentransparentblocks.OraxenMechanics;

import io.th0rgal.oraxen.mechanics.Mechanic;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import me.playajames.oraxentransparentblocks.Listeners.OraxenBlockListeners.OraxenTransparentBlockBreakListener;
import me.playajames.oraxentransparentblocks.Listeners.OraxenBlockListeners.OraxenTransparentBlockInteractListener;
import me.playajames.oraxentransparentblocks.Listeners.OraxenBlockListeners.OraxenTransparentBlockPlaceListener;
import me.playajames.oraxentransparentblocks.OraxenTransparentBlocks;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

public class TransparentBlockMechanicFactory extends MechanicFactory {

    public TransparentBlockMechanicFactory(ConfigurationSection section) {
        super(section);
        Bukkit.getPluginManager().registerEvents(new OraxenTransparentBlockPlaceListener(this), OraxenTransparentBlocks.getPlugin(OraxenTransparentBlocks.class));
        Bukkit.getPluginManager().registerEvents(new OraxenTransparentBlockBreakListener(this), OraxenTransparentBlocks.getPlugin(OraxenTransparentBlocks.class));
    }

    @Override
    public Mechanic parse(ConfigurationSection itemMechanicConfiguration) {
        Mechanic mechanic = new TransparentBlockMechanic(this, itemMechanicConfiguration);
        addToImplemented(mechanic);
        return mechanic;
    }

}