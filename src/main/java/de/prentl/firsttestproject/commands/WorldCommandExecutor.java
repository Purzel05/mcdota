package de.prentl.firsttestproject.commands;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage("§cBitte benutze /worlds help");
            } else {
                if (args[0].equalsIgnoreCase("help")) {
                    player.sendMessage("§e/world create <Name> [Type] [Seed] §7- §eErstellt eine Welt");
                    player.sendMessage("§e/world tp <Name> §7- §eTeleportiert dich zu genannter Welt");
                } else if (args[0].equalsIgnoreCase("tp")){
                    if(args.length == 2){
                        if(Bukkit.getWorld(args[1]) != null){
                            player.teleport(Bukkit.getWorld(args[1]).getSpawnLocation());
                        }else{
                            System.out.println("World ist null");
                        }
                    }

                } else if(args[0].equalsIgnoreCase("create")){
                    if(args.length >= 2){
                        WorldCreator creator = new WorldCreator(args[1]);

                        if (args.length >= 3 && args[2].equalsIgnoreCase("flat")){
                            creator.type(WorldType.FLAT);
                            creator.generateStructures(false);
                        } else {
                            creator.type(WorldType.NORMAL);
                        }

                        if(args.length >= 4) {
                            creator.seed(Long.parseLong(args[3]));
                        }
                        player.sendMessage("§7Welt wird erstellt...");
                        Bukkit.createWorld(creator);
                        player.sendMessage("§aDie Welt wurde erstellt");
                    }
                }
            }
        }


        return true;
    }
}

