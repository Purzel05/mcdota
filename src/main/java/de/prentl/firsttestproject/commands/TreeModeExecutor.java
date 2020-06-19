package de.prentl.firsttestproject.commands;

import de.prentl.firsttestproject.McdGame;
import de.prentl.firsttestproject.McdPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class TreeModeExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String message;
        if (args.length == 1 && args[0] != null && args[0].equals("ON")) {
            McdPlugin.treeMode = true;
            message = "TreeMode ON!";
        } else {
            McdPlugin.treeMode = false;
            message = "TreeMode OFF!";

        }
        Bukkit.getLogger().info(message);
        Objects.requireNonNull(Bukkit.getWorld(McdPlugin.MAP_WORLD)).getPlayers().forEach(p -> { p.sendMessage(message);});
        return false;
    }
}
