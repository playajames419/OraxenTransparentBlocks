package me.playajames.oraxentransparentblocks.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class PlayerUtils {

    public static boolean canBuild(Player player, Location location) {

        return true;

//        BlockBreakEvent fakeEvent = new BlockBreakEvent(location.getBlock(), player);
//        Bukkit.getPluginManager().callEvent(fakeEvent);
//        if (!fakeEvent.isCancelled()) return true;
//        return false;
    }

}
