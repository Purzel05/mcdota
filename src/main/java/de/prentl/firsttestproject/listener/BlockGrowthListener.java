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
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class BlockGrowthListener implements Listener {

    @EventHandler
    public void onGrowth(BlockGrowEvent event) {
        Bukkit.getLogger().info("cancelling block growth event ...");
        event.setCancelled(true);
    }
}
