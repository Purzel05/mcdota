package de.prentl.firsttestproject.tasks;

import de.prentl.firsttestproject.McdPlugin;
import de.prentl.firsttestproject.entities.McdEntity;
import net.minecraft.server.v1_15_R1.EntityInsentient;
import org.bukkit.Bukkit;

import java.util.List;

public class UpdateGoalsAndTargetsTask implements Runnable {

    public List<EntityInsentient> insentients;

    public UpdateGoalsAndTargetsTask(List<EntityInsentient> insentients) {
        this.insentients = insentients;
    }

    @Override
    public void run() {

        if (Bukkit.getWorld(McdPlugin.MAP_WORLD).getTime() > 15000) {   // TODO put this in a separate Task!
            Bukkit.getWorld(McdPlugin.MAP_WORLD).setTime(14000);
        }

        for (EntityInsentient entity: insentients) {
            if (entity != null && entity.isAlive()) {
                ((McdEntity)entity).updateGoalsAndTargets();
            } else {
                insentients.remove(entity);
                break;
            }
        }
    }
}
