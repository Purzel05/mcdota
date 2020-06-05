package de.prentl.firsttestproject.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MirrorCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = Bukkit.getPlayer(sender.getName());
        World world = player.getWorld();

        // setting base
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                Location loc = new Location(world, i, 3, j);
                loc.getBlock().setType(Material.STONE);
            }
        }

        // setting walls
        for (int i = 0; i <= 100; i++) {
            for (int k = 4; k < 8; k++) {
                Location location = new Location(world,i,k,0);
                location.getBlock().setType(Material.STONE);
                location = new Location(world,0,k,i);
                location.getBlock().setType(Material.STONE);
            }
        }

        // mirroring
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100 - i; j++) {
                for (int k = 0; k < 20; k++) {
                    Location locationSource = new Location(world,i,k,j);
                    Location locationTarget = new Location(world,100-i,k,100-j);
                    locationTarget.getBlock().setType(locationSource.getBlock().getType());
                }
            }
        }

        return false;
    }
}
