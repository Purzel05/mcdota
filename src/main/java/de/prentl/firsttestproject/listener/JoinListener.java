package de.prentl.firsttestproject.listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.GREEN + "Willkommen auf MCDota!");
        event.setJoinMessage(ChatColor.YELLOW + player.getName() + ChatColor.BLUE + " hat den Server betreten");
        World world = player.getWorld();
        Location location = new Location(world,40,4,20);
        player.teleport(location);
    }
}
