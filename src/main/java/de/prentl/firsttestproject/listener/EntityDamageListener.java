package de.prentl.firsttestproject.listener;

import de.prentl.firsttestproject.McdPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
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
}
