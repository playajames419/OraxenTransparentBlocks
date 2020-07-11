package me.playajames.oraxentransparentblocks.OraxenMechanics;

import io.th0rgal.oraxen.OraxenPlugin;
import io.th0rgal.oraxen.mechanics.Mechanic;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import io.th0rgal.oraxen.mechanics.MechanicsManager;
import me.playajames.oraxentransparentblocks.Listeners.CustomBlockPlaceListener;
import org.bukkit.configuration.ConfigurationSection;

public class TransparentBlockMechanicFactory extends MechanicFactory {

    public TransparentBlockMechanicFactory(ConfigurationSection section) {
        super(section);
        System.out.println("here");
        MechanicsManager.registerListeners(OraxenPlugin.get(), new TransparentBlockMechanicListener(this));
        MechanicsManager.registerListeners(OraxenPlugin.get(), new CustomBlockPlaceListener(this));
    }

    @Override
    public Mechanic parse(ConfigurationSection itemMechanicConfiguration) {
        Mechanic mechanic = new TransparentBlockMechanic(this, itemMechanicConfiguration);
        addToImplemented(mechanic);
        return mechanic;
    }

}