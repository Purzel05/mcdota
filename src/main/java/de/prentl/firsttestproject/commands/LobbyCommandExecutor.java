package de.prentl.firsttestproject.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = Bukkit.getPlayer(sender.getName());
        World world = Bukkit.getWorld("lobby");
        Location location = new Location(world, 1.5, 4, 77.45);
        player.teleport(location);
        return false;
    }
}
