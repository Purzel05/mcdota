package de.prentl.firsttestproject.commands;

import de.prentl.firsttestproject.McDotaMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Witch;
import org.bukkit.util.Vector;

public class MoveWitchExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Vector pos = McDotaMain.witch.getLocation().toVector();
        Vector target = McDotaMain.villager1.getLocation().toVector();
        Vector velocity = target.subtract(pos);
        McDotaMain.witch.setVelocity(velocity.normalize().multiply(0.5));
        return false;
    }
}
