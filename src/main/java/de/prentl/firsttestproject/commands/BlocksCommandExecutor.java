package de.prentl.firsttestproject.commands;

import de.prentl.firsttestproject.McdGame;
import de.prentl.firsttestproject.McdPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class BlocksCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String message;
        if (McdPlugin.blocksMode) {
            McdPlugin.blocksMode = false;
            message = "bloxmode is now off";
        } else {
            McdPlugin.blocksMode = true;
            message = "bloxmode is now on";
        }

        Bukkit.getLogger().info(message);
        Objects.requireNonNull(Bukkit.getWorld(McdPlugin.MAP_WORLD)).getPlayers().forEach(p -> { p.sendMessage(message);});
        return false;
    }
}
