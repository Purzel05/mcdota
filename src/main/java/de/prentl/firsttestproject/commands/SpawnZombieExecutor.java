package de.prentl.firsttestproject.commands;

import de.prentl.firsttestproject.McDotaMain;
import de.prentl.firsttestproject.customentities.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpawnZombieExecutor implements CommandExecutor {

    private static final int numberOfZombies = 5;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        World world = Bukkit.getWorld(McDotaMain.MAP_WORLD);

        CustomZombie zombie;

        for (int i = 0; i < numberOfZombies; i++) {
            zombie = CustomEntityType.blueLeftZombieType.spawn(new Location(world, BlueLeftZombie.spawnLoc.x + i,
                    BlueLeftZombie.spawnLoc.y, BlueLeftZombie.spawnLoc.z));
            McDotaMain.zombies.add(zombie);
        }

        for (int i = 0; i < numberOfZombies; i++) {
            zombie = CustomEntityType.blueCenterZombieType.spawn(new Location(world, BlueCenterZombie.spawnLoc.x + i,
                    BlueCenterZombie.spawnLoc.y, BlueCenterZombie.spawnLoc.z + i));
            McDotaMain.zombies.add(zombie);
        }

        for (int i = 0; i < numberOfZombies; i++) {
            zombie = CustomEntityType.blueRightZombieType.spawn(new Location(world, BlueRightZombie.spawnLoc.x,
                    BlueRightZombie.spawnLoc.y, BlueRightZombie.spawnLoc.z + i));
            McDotaMain.zombies.add(zombie);
        }

        return false;
    }
}
