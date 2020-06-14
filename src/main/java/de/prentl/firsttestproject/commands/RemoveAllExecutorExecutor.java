package de.prentl.firsttestproject.commands;

import de.prentl.firsttestproject.entities.EntityUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RemoveAllExecutorExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        EntityUtils.removeLivingEntities();
        return false;
    }
}