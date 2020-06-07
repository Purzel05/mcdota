package de.prentl.firsttestproject.customentities;

import de.prentl.firsttestproject.McDotaMain;
import net.minecraft.server.v1_15_R1.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CustomZombie extends EntityZombie {

    public CustomZombie(World world) {
        super(EntityTypes.ZOMBIE, world);
    }

    public CustomZombie(EntityTypes<CustomZombie> customZombieEntityTypes, World world) {
        this(world);
    }
}
