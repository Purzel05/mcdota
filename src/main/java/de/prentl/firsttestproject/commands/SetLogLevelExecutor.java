package de.prentl.firsttestproject.commands;

import de.prentl.firsttestproject.McdGame;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.logging.Level;

public class SetLogLevelExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings[0].equals("severe")) {
            Bukkit.getLogger().setLevel(Level.SEVERE);
            System.out.println("log level set to severe");
        }

        if (strings[0].equals("warning")) {
            Bukkit.getLogger().setLevel(Level.WARNING);
            System.out.println("log level set to warning");
        }

        if (strings[0].equals("info")) {
            Bukkit.getLogger().setLevel(Level.INFO);
            System.out.println("log level set to info");
        }

        if (strings[0].equals("fine")) {
            Bukkit.getLogger().setLevel(Level.FINE);
            System.out.println("log level set to fine");
        }
        return false;
    }
}
