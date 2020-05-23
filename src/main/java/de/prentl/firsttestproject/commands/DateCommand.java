package de.prentl.firsttestproject.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Date;

public class DateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Date date = new Date();
        sender.sendMessage("Heute ist der " + date.toString());
        return false;
    }
}
