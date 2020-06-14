package de.prentl.firsttestproject.commands;

import de.prentl.firsttestproject.McdGame;
import de.prentl.firsttestproject.McdPlugin;
import de.prentl.firsttestproject.entities.EntityUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class StartGameExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String message;
        if (strings.length == 1 && strings[0] != null && strings[0].equals("on")) {
            McdGame.isStarted = true;
            message = "game started!";
        } else {
            McdGame.isStarted = false;
            message = "game stopped!";

        }
        Bukkit.getLogger().info(message);
        Objects.requireNonNull(Bukkit.getWorld(McdPlugin.MAP_WORLD)).getPlayers().forEach(p -> { p.sendMessage(message);});
        return false;
    }
}
