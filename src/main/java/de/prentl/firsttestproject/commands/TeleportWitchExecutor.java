package de.prentl.firsttestproject.commands;

import de.prentl.firsttestproject.McDotaMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.util.Vector;

public class TeleportWitchExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        World world = Bukkit.getWorld("world");
        sender.sendMessage("Teleporting to " + args[0] + ":" + args[1] + ":" + args[2]);
        Location location = new Location(world, Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        McDotaMain.witch.teleport(location);
        return false;
    }
}
