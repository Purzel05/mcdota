package de.prentl.firsttestproject.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatListener extends JavaPlugin implements Listener {

    @Override
    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onChat(final AsyncPlayerChatEvent event){

        final Player player = event.getPlayer();
        final String message = event.getMessage().replace("%", "%%");


        if(player.hasPermission("server.Default")){
            event.setFormat("§8[§8Default§8] §8" + player.getName() + "§8 >> §f" + message);
            return;
        }
        if(player.hasPermission("server.Admin")){
            event.setFormat("§4[§4Admin§4] §4" + player.getName() + "§6 >> §4" + message);
            return;
        }
        if(player.hasPermission("server.Developer")){
            event.setFormat("§b[§bDeveloper§b] §b" + player.getName() + "§0 >> §6" + message);
            return;
        }
        if(player.hasPermission("server.Builder")){
            event.setFormat("§e[§eBuilder§e] §e" + player.getName() + "§c >> §1" + message);
            return;
        }
        if(player.hasPermission("server.Kröte")){
            event.setFormat("§d[§dKröte§d] §d" + player.getName() + "§f >> §9" + message);
        }


    }
}



