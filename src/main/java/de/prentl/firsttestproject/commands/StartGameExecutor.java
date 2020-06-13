package de.prentl.firsttestproject.commands;

import de.prentl.firsttestproject.McdGame;
import de.prentl.firsttestproject.entities.EntityUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartGameExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings[0].equals("on")) {
            McdGame.isStarted = true;
            Bukkit.getLogger().info("game started");
        }
        if (strings[0].equals("off")) {
            McdGame.isStarted = false;
            Bukkit.getLogger().info("game stopped");
        }
        return false;
    }
}
