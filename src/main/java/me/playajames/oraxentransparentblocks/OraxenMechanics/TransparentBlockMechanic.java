package me.playajames.oraxentransparentblocks.OraxenMechanics;

import io.th0rgal.oraxen.mechanics.Mechanic;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import io.th0rgal.oraxen.utils.drops.Drop;
import io.th0rgal.oraxen.utils.drops.Loot;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TransparentBlockMechanic extends Mechanic {

    private final Drop drop;

    @SuppressWarnings("unchecked")
    public TransparentBlockMechanic(MechanicFactory mechanicFactory, ConfigurationSection section) {

        super(mechanicFactory, section);

        List<Loot> loots = new ArrayList<>();
        ConfigurationSection drop = section.getConfigurationSection("drop");
        for (LinkedHashMap<String, Object> lootConfig
                : (List<LinkedHashMap<String, Object>>) drop.getList("loots")) {
            loots.add(new Loot(lootConfig));
        }
        if (drop.isString("minimal_tool"))
            this.drop = new Drop(loots, drop.getBoolean("silktouch"),
                    drop.getBoolean("fortune"),
                    getItemID(),
                    Material.getMaterial(drop.getString("minimal_tool")));
        else
            this.drop = new Drop(loots, drop.getBoolean("silktouch"),
                    drop.getBoolean("fortune"),
                    getItemID());
    }

    public Drop getDrop() {
        return drop;
    }

}
