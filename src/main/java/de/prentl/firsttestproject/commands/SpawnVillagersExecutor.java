package de.prentl.firsttestproject.commands;

import de.prentl.firsttestproject.McDotaMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

import java.util.Date;

public class SpawnVillagersExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        World world = Bukkit.getWorld("world");
        Location location = new Location(world, 8, 4, 95);
        McDotaMain.villager1 = (Villager) world.spawnEntity(location, EntityType.VILLAGER);
        McDotaMain.villager1.setAI(false);
        return false;
    }
}
