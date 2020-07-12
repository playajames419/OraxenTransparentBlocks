package me.playajames.oraxentransparentblocks.OraxenMechanics;

import io.th0rgal.oraxen.OraxenPlugin;
import io.th0rgal.oraxen.mechanics.Mechanic;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import io.th0rgal.oraxen.mechanics.MechanicsManager;
import me.playajames.oraxentransparentblocks.Listeners.CustomBlockListeners.CustomTransparentBlockBreakListener;
import me.playajames.oraxentransparentblocks.Listeners.CustomBlockListeners.CustomTransparentBlockPlaceListener;
import org.bukkit.configuration.ConfigurationSection;

public class TransparentBlockMechanicFactory extends MechanicFactory {

    public TransparentBlockMechanicFactory(ConfigurationSection section) {
        super(section);
        MechanicsManager.registerListeners(OraxenPlugin.get(), new CustomTransparentBlockPlaceListener(this));
        MechanicsManager.registerListeners(OraxenPlugin.get(), new CustomTransparentBlockBreakListener(this));
    }

    @Override
    public Mechanic parse(ConfigurationSection itemMechanicConfiguration) {
        Mechanic mechanic = new TransparentBlockMechanic(this, itemMechanicConfiguration);
        addToImplemented(mechanic);
        return mechanic;
    }

}