package de.prentl.firsttestproject.listener;

import de.prentl.firsttestproject.McdGame;
import de.prentl.firsttestproject.McdMap;
import de.prentl.firsttestproject.McdPlugin;
import net.minecraft.server.v1_15_R1.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        Bukkit.getLogger().info(player.getDisplayName() + "is respawning ...");
        Vec3D spawnVector = McdMap.getPlayerLocation(McdGame.mcdPlayerMap.get(player).getSide());
        assert spawnVector != null;
        event.setRespawnLocation(new Location(Bukkit.getWorld(McdPlugin.MAP_WORLD), spawnVector.x, spawnVector.y, spawnVector.z));
    }
}
