package de.prentl.firsttestproject.commands;

import de.prentl.firsttestproject.McdGame;
import de.prentl.firsttestproject.McdPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class StartGameExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String message;
        if (McdGame.isStarted) {
            McdGame.isStarted = false;
            message = "game stopped";
        } else {
            McdGame.isStarted = true;
            message = "game started";
        }

        Bukkit.getLogger().info(message);
        Objects.requireNonNull(Bukkit.getWorld(McdPlugin.MAP_WORLD)).getPlayers().forEach(p -> { p.sendMessage(message);});
        return false;
    }
}
