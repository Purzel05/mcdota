package de.prentl.firsttestproject.commands;

import de.prentl.firsttestproject.McDotaMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.util.Vector;

public class WitchAttackExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        McDotaMain.witch.attack(McDotaMain.villager1);

        
        return false;
    }
}
