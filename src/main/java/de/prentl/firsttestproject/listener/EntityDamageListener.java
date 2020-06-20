package de.prentl.firsttestproject.listener;

import de.prentl.firsttestproject.McdPlugin;
import de.prentl.firsttestproject.entities.McdSkeleton;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftTippedArrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class EntityDamageListener implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        final Vector vec = new Vector();
        event.getEntity().setVelocity(vec);
        new BukkitRunnable() {
            public void run() {
                event.getEntity().setVelocity(vec);
            }
        }.runTaskLater(McdPlugin.mcdPlugin, 1L);
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        Entity damager = event.getDamager();
        if (damager instanceof CraftTippedArrow) {
            CraftTippedArrow arrow = (CraftTippedArrow) damager;
            ProjectileSource projectileSource = arrow.getShooter();
            Bukkit.getLogger().info(projectileSource.toString() + " damaged " + entity.toString());
        } else {
            Bukkit.getLogger().info(damager.toString() + " damaged " + entity.toString());
        }
    }
}


