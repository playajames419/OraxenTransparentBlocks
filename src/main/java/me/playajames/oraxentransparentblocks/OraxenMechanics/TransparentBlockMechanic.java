package me.playajames.oraxentransparentblocks.OraxenMechanics;

import io.th0rgal.oraxen.mechanics.Mechanic;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import org.bukkit.configuration.ConfigurationSection;

public class TransparentBlockMechanic extends Mechanic {

    private boolean visible;
    private boolean small;

    @SuppressWarnings("unchecked")
    public TransparentBlockMechanic(MechanicFactory mechanicFactory, ConfigurationSection section) {
        super(mechanicFactory, section);

        if (section.isBoolean("armostand_visible"))
            this.visible = section.getBoolean("armorstand_visible");

        if (section.isBoolean("armostand_small"))
            this.small = section.getBoolean("armorstand_small");

    }

    public boolean isVisible() {
        return this.visible;
    }

    public boolean isSmall() {
        return this.small;
    }

}
