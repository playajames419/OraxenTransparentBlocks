package me.playajames.oraxentransparentblocks.Commands;

import me.playajames.oraxentransparentblocks.OraxenTransparentBlocks;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class OTBCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!label.equalsIgnoreCase("otb")) return false;

        if (!sender.hasPermission("oraxentransparentblocks.reload")) {
            sender.sendMessage(ChatColor.RED + "Sorry, you don't have permission to do that.");
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage("Usage: /otb reload");
            return false;
        }

        OraxenTransparentBlocks.getPlugin(OraxenTransparentBlocks.class).reloadConfig();
        sender.sendMessage("Oraxen Transparent Blocks reloaded!");
        return true;
    }
}
