package de.prentl.firsttestproject.commands;

import de.prentl.firsttestproject.McDotaMain;
import de.prentl.firsttestproject.entities.*;
import de.prentl.firsttestproject.entities.pigs.McdPigZombie;
import de.prentl.firsttestproject.entities.zombies.BlueLeftZombie;
import de.prentl.firsttestproject.entities.zombies.McdZombie;
import de.prentl.firsttestproject.entities.pigs.YellowRightPigZombie;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpawnZombieExecutor implements CommandExecutor {

    private static final int numberOfZombies = 1;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        World world = Bukkit.getWorld(McDotaMain.MAP_WORLD);

        McdZombie zombie;

        for (int i = 0; i < numberOfZombies; i++) {
            zombie = CustomEntityType.blueLeftZombieType.spawn(new Location(world, BlueLeftZombie.spawnLoc.x + i,
                    BlueLeftZombie.spawnLoc.y, BlueLeftZombie.spawnLoc.z));
            McDotaMain.zombies.add(zombie);
        }

        /*for (int i = 0; i < numberOfZombies; i++) {
            zombie = CustomEntityType.blueCenterZombieType.spawn(new Location(world, BlueCenterZombie.spawnLoc.x + i,
                    BlueCenterZombie.spawnLoc.y, BlueCenterZombie.spawnLoc.z + i));
            McDotaMain.zombies.add(zombie);
        }

        for (int i = 0; i < numberOfZombies; i++) {
            zombie = CustomEntityType.blueRightZombieType.spawn(new Location(world, BlueRightZombie.spawnLoc.x,
                    BlueRightZombie.spawnLoc.y, BlueRightZombie.spawnLoc.z + i));
            McDotaMain.zombies.add(zombie);
        }*/

        McdPigZombie pigZombie;

        for (int i = 0; i < numberOfZombies; i++) {
            pigZombie = CustomEntityType.yellowRightPigZombieType.spawn(new Location(world, YellowRightPigZombie.spawnLoc.x + i,
                    YellowRightPigZombie.spawnLoc.y, YellowRightPigZombie.spawnLoc.z));
            McDotaMain.zombies.add(pigZombie);
        }

        return false;
    }
}
