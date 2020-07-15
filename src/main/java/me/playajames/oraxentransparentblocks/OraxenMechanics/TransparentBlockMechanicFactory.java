package me.playajames.oraxentransparentblocks.OraxenMechanics;

import io.th0rgal.oraxen.OraxenPlugin;
import io.th0rgal.oraxen.mechanics.Mechanic;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import io.th0rgal.oraxen.mechanics.MechanicsManager;
import me.playajames.oraxentransparentblocks.Listeners.OraxenBlockListeners.OraxenTransparentBlockBreakListener;
import me.playajames.oraxentransparentblocks.Listeners.OraxenBlockListeners.OraxenTransparentBlockInteractListener;
import me.playajames.oraxentransparentblocks.Listeners.OraxenBlockListeners.OraxenTransparentBlockPlaceListener;
import org.bukkit.configuration.ConfigurationSection;

public class TransparentBlockMechanicFactory extends MechanicFactory {

    public TransparentBlockMechanicFactory(ConfigurationSection section) {
        super(section);
        MechanicsManager.registerListeners(OraxenPlugin.get(), new OraxenTransparentBlockPlaceListener(this));
        MechanicsManager.registerListeners(OraxenPlugin.get(), new OraxenTransparentBlockBreakListener(this));
        MechanicsManager.registerListeners(OraxenPlugin.get(), new OraxenTransparentBlockInteractListener());
    }

    @Override
    public Mechanic parse(ConfigurationSection itemMechanicConfiguration) {
        Mechanic mechanic = new TransparentBlockMechanic(this, itemMechanicConfiguration);
        addToImplemented(mechanic);
        return mechanic;
    }

}