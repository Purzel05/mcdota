package de.prentl.firsttestproject.commands;

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
        World world = Bukkit.getWorld("world");
        //EntityTypes.spawnEntity(new CustomZombie(world), new Location(Bukkit.getWorld("world"), 8, 4, 8));

        //CustomZombie customZombie = new CustomZombie(world);
        //Location location = new Location(world, 8, 4, 8);




        return false;
    }
}
