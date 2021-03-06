package me.playajames.oraxentransparentblocks.OraxenMechanics;

import io.th0rgal.oraxen.mechanics.Mechanic;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import io.th0rgal.oraxen.utils.drops.Loot;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TransparentBlockMechanic extends Mechanic {

    private boolean visible;
    private boolean small;
    private boolean gravity;
    private Sound breakSound;
    private List<Loot> drops = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public TransparentBlockMechanic(MechanicFactory mechanicFactory, ConfigurationSection section) {
        super(mechanicFactory, section);

        if (section.isBoolean("armorstand_visible"))
            this.visible = section.getBoolean("armorstand_visible");

        if (section.isBoolean("armorstand_small"))
            this.small = section.getBoolean("armorstand_small");

        if (section.isBoolean("block_gravity"))
            this.gravity = section.getBoolean("block_gravity");

        if (section.contains("break_sound"))
            this.breakSound = Sound.valueOf(section.getString("break_sound"));

        if (section.contains("drop"))
            parseDrops(section.getConfigurationSection("drop"));

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

    public List<Loot> getDrops() {
        return drops;
    }

    public Sound getBreakSound() {
        return breakSound;
    }

    private void parseDrops(ConfigurationSection section) {
        if (!section.contains("loots")) return;
        for (LinkedHashMap<String, Object> lootConfig : (List<LinkedHashMap<String, Object>>) section.getList("loots"))
            drops.add(new Loot(lootConfig));
    }

    private void parseBreakSound() {

    }
}
