package de.prentl.firsttestproject.commands;

import de.prentl.firsttestproject.McdGame;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpawnPigZombieWaveExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        McdGame.spawnPigZombies();
        return false;
    }
}
