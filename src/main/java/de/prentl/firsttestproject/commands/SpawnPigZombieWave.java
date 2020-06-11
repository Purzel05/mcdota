package de.prentl.firsttestproject.commands;

import de.prentl.firsttestproject.entities.EntityUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpawnPigZombieWave implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        EntityUtils.spawnPigZombies();
        EntityUtils.equipPigZombiesAndSkeletons();
        return false;
    }
}
