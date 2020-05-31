package de.prentl.firsttestproject.listener;

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
        player.sendMessage("Willkommen zurück!");
        event.setJoinMessage("");
        World world = player.getWorld();
        Location location = new Location(world,1.5,4,77.45);
        player.teleport(location);
    }
}
