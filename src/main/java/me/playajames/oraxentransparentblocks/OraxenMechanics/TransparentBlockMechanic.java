package me.playajames.oraxentransparentblocks.OraxenMechanics;

import io.th0rgal.oraxen.mechanics.Mechanic;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class TransparentBlockMechanic extends Mechanic {

    private boolean visible;
    private boolean small;
    private boolean gravity;
    private List<ItemStack> drops;

    @SuppressWarnings("unchecked")
    public TransparentBlockMechanic(MechanicFactory mechanicFactory, ConfigurationSection section) {
        super(mechanicFactory, section);

        if (section.isBoolean("armostand_visible"))
            this.visible = section.getBoolean("armorstand_visible");

        if (section.isBoolean("armostand_small"))
            this.small = section.getBoolean("armorstand_small");

        if (section.isBoolean("armostand_gravity"))
            this.gravity = section.getBoolean("armorstand_gravity");

        if (section.contains("drops")) {
            System.out.println("Section contains drops!");
        }

    }

    public boolean isVisible() {
        return this.visible;
    }

    public boolean isSmall() {
        return this.small;
    }

    public boolean hasGravity() {
        return this.gravity;
    }

    public List<ItemStack> getDrops() {
        return drops;
    }

    private ItemStack parseDrop(String section) {
        return null;
    }
}
