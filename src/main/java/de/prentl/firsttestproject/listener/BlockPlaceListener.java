package de.prentl.firsttestproject.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;


public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onJoin(BlockPlaceEvent event) {
        event.setCancelled(true);
    }
}
