package de.prentl.firsttestproject.commands;

import de.prentl.firsttestproject.McDotaMain;
import net.minecraft.server.v1_15_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class SpawnZombieExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = Bukkit.getPlayer(sender.getName());
        Location loc = player.getLocation();
        World world = loc.getWorld();
        CraftWorld craftWorld = (CraftWorld) world;
        WorldServer worldServer = craftWorld.getHandle();
        Entity entity = McDotaMain.zombie.spawn(loc);
        return false;
    }
}
