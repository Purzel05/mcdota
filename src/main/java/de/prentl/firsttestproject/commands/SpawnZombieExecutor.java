package de.prentl.firsttestproject.commands;

import de.prentl.firsttestproject.McDotaMain;
import de.prentl.firsttestproject.customentities.CustomZombie;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpawnZombieExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        World world = Bukkit.getWorld(McDotaMain.MAP_WORLD);

        CustomZombie zombie;

        for (int i = 0; i < 5; i++) {
            zombie = McDotaMain.zombieType.spawn(new Location(world, McDotaMain.spawnLocBlueLeft[0] + i,
                    McDotaMain.spawnLocBlueLeft[1], McDotaMain.spawnLocBlueLeft[2]));
            zombie.setSide("BLUE");
            zombie.setLane("LEFT");
            McDotaMain.zombies.add(zombie);
        }

        for (int i = 0; i < 5; i++) {
            zombie = McDotaMain.zombieType.spawn(new Location(world, McDotaMain.spawnLocBlueCenter[0] + i,
                    McDotaMain.spawnLocBlueCenter[1], McDotaMain.spawnLocBlueCenter[2] + i));
            zombie.setSide("BLUE");
            zombie.setLane("CENTER");
            McDotaMain.zombies.add(zombie);
            McDotaMain.zombies.add(zombie);
        }

        for (int i = 0; i < 5; i++) {
            zombie = McDotaMain.zombieType.spawn(new Location(world, McDotaMain.spawnLocBlueRight[0],
                    McDotaMain.spawnLocBlueRight[1], McDotaMain.spawnLocBlueRight[2] + i));
            zombie.setSide("BLUE");
            zombie.setLane("RIGHT");
            McDotaMain.zombies.add(zombie);
        }

        return false;
    }
}
