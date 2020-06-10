package de.prentl.firsttestproject.tasks;

import de.prentl.firsttestproject.McDotaMain;
import de.prentl.firsttestproject.entities.pigs.McdPigZombie;
import de.prentl.firsttestproject.entities.skeletons.McdSkeleton;
import de.prentl.firsttestproject.entities.zombies.McdZombie;
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

        if (Bukkit.getWorld(McDotaMain.MAP_WORLD).getTime() > 15000) {   // TODO put this in a separate Task!
            Bukkit.getWorld(McDotaMain.MAP_WORLD).setTime(14000);
        }

        for (EntityInsentient entity: insentients) {    // TODO refactor this with an interface for those classes!
            if (entity != null && entity.isAlive()) {
                if (entity instanceof McdZombie) {
                    ((McdZombie)entity).updateGoalsAndTargets();
                } else if (entity instanceof McdPigZombie) {
                    ((McdPigZombie)entity).updateGoalsAndTargets();
                }
            } else {
                insentients.remove(entity);
                break;
            }
        }
    }
}
