package de.prentl.firsttestproject.customentities;

import net.minecraft.server.v1_15_R1.*;

import java.util.List;

public class CustomZombie extends EntityZombie {

    public CustomZombie(World world) {
        super(EntityTypes.ZOMBIE, world);
    }

    public CustomZombie(EntityTypes<CustomZombie> customZombieEntityTypes, World world) {
        this(world);
    }

    public void repeatingTask() {
        this.goalSelector.c().forEach(g -> { System.out.println(g.toString()); });
    }
}
