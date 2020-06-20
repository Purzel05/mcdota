package de.prentl.firsttestproject.listener;

import de.prentl.firsttestproject.McdGame;
import de.prentl.firsttestproject.McdPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {

    @EventHandler
    public void onGrowth(BlockGrowEvent event) {
        Bukkit.getLogger().info("cancelling block growth event ...");
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (!McdPlugin.blocksMode) {
            Bukkit.getLogger().info("cancelling block place event ...");
            event.setCancelled(true);
        } else {
            if (McdPlugin.treeMode && event.getBlockPlaced().getType().equals(Material.SPRUCE_WOOD)) {
                Location locOrig = event.getBlockPlaced().getLocation();
                Location loc;

                // Stamm
                loc = new Location(locOrig.getWorld(), locOrig.getX(), locOrig.getY() + 1, locOrig.getZ());
                loc.getBlock().setType(Material.SPRUCE_WOOD);
                loc = new Location(locOrig.getWorld(), locOrig.getX(), locOrig.getY() + 2, locOrig.getZ());
                loc.getBlock().setType(Material.SPRUCE_WOOD);
                loc = new Location(locOrig.getWorld(), locOrig.getX(), locOrig.getY() + 3, locOrig.getZ());
                loc.getBlock().setType(Material.SPRUCE_WOOD);
                loc = new Location(locOrig.getWorld(), locOrig.getX(), locOrig.getY() + 4, locOrig.getZ());
                loc.getBlock().setType(Material.SPRUCE_WOOD);
                loc = new Location(locOrig.getWorld(), locOrig.getX(), locOrig.getY() + 5, locOrig.getZ());
                loc.getBlock().setType(Material.SPRUCE_LEAVES);

                // Blätter Höhe 2
                loc = new Location(locOrig.getWorld(), locOrig.getX() + 1, locOrig.getY() + 2, locOrig.getZ());
                loc.getBlock().setType(Material.SPRUCE_LEAVES);
                loc = new Location(locOrig.getWorld(), locOrig.getX() + 1, locOrig.getY() + 2, locOrig.getZ() + 1);
                loc.getBlock().setType(Material.SPRUCE_LEAVES);
                loc = new Location(locOrig.getWorld(), locOrig.getX(), locOrig.getY() + 2, locOrig.getZ() + 1);
                loc.getBlock().setType(Material.SPRUCE_LEAVES);
                loc = new Location(locOrig.getWorld(), locOrig.getX() - 1, locOrig.getY() + 2, locOrig.getZ());
                loc.getBlock().setType(Material.SPRUCE_LEAVES);
                loc = new Location(locOrig.getWorld(), locOrig.getX() - 1, locOrig.getY() + 2, locOrig.getZ() - 1);
                loc.getBlock().setType(Material.SPRUCE_LEAVES);
                loc = new Location(locOrig.getWorld(), locOrig.getX(), locOrig.getY() + 2, locOrig.getZ() - 1);
                loc.getBlock().setType(Material.SPRUCE_LEAVES);
                loc = new Location(locOrig.getWorld(), locOrig.getX() - 1, locOrig.getY() + 2, locOrig.getZ() + 1);
                loc.getBlock().setType(Material.SPRUCE_LEAVES);
                loc = new Location(locOrig.getWorld(), locOrig.getX() + 1, locOrig.getY() + 2, locOrig.getZ() - 1);
                loc.getBlock().setType(Material.SPRUCE_LEAVES);

                // Blätter Höhe 3
                loc = new Location(locOrig.getWorld(), locOrig.getX(), locOrig.getY() + 3, locOrig.getZ() - 1);
                loc.getBlock().setType(Material.SPRUCE_LEAVES);
                loc = new Location(locOrig.getWorld(), locOrig.getX(), locOrig.getY() + 3, locOrig.getZ() + 1);
                loc.getBlock().setType(Material.SPRUCE_LEAVES);
                loc = new Location(locOrig.getWorld(), locOrig.getX() - 1, locOrig.getY() + 3, locOrig.getZ());
                loc.getBlock().setType(Material.SPRUCE_LEAVES);
                loc = new Location(locOrig.getWorld(), locOrig.getX() + 1, locOrig.getY() + 3, locOrig.getZ());
                loc.getBlock().setType(Material.SPRUCE_LEAVES);

                // Blätter Höhe 4
                loc = new Location(locOrig.getWorld(), locOrig.getX() + 1, locOrig.getY() + 4, locOrig.getZ());
                loc.getBlock().setType(Material.SPRUCE_LEAVES);
                loc = new Location(locOrig.getWorld(), locOrig.getX() + 1, locOrig.getY() + 4, locOrig.getZ() + 1);
                loc.getBlock().setType(Material.SPRUCE_LEAVES);
                loc = new Location(locOrig.getWorld(), locOrig.getX(), locOrig.getY() + 4, locOrig.getZ() + 1);
                loc.getBlock().setType(Material.SPRUCE_LEAVES);
                loc = new Location(locOrig.getWorld(), locOrig.getX() - 1, locOrig.getY() + 4, locOrig.getZ());
                loc.getBlock().setType(Material.SPRUCE_LEAVES);
                loc = new Location(locOrig.getWorld(), locOrig.getX() - 1, locOrig.getY() + 4, locOrig.getZ() - 1);
                loc.getBlock().setType(Material.SPRUCE_LEAVES);
                loc = new Location(locOrig.getWorld(), locOrig.getX(), locOrig.getY() + 4, locOrig.getZ() - 1);
                loc.getBlock().setType(Material.SPRUCE_LEAVES);
                loc = new Location(locOrig.getWorld(), locOrig.getX() - 1, locOrig.getY() + 4, locOrig.getZ() + 1);
                loc.getBlock().setType(Material.SPRUCE_LEAVES);
                loc = new Location(locOrig.getWorld(), locOrig.getX() + 1, locOrig.getY() + 4, locOrig.getZ() - 1);
                loc.getBlock().setType(Material.SPRUCE_LEAVES);
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (!McdPlugin.blocksMode) {
            Bukkit.getLogger().info("cancelling block break event ...");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(BlockDamageEvent event) {
        if (!McdPlugin.blocksMode) {
            Bukkit.getLogger().info("cancelling block damage event ...");
            event.setCancelled(true);
        }
    }
}
