package de.prentl.firsttestproject.customentities;

import net.minecraft.server.v1_15_R1.EntityTypes;
import net.minecraft.server.v1_15_R1.Vec3D;
import net.minecraft.server.v1_15_R1.World;

public class BlueCenterZombie extends CustomZombie {
    public static final Double[] spawnLoc = new Double[] {12.0D, 4.0D, 12.0D};
    public static final Double[] laneLoc = new Double[] {40.0D, 4.0D, 40.0D};

    public BlueCenterZombie(World world) {
        super(world);
    }

    public BlueCenterZombie(EntityTypes<BlueCenterZombie> blueCenterZombieEntityTypes, World world) {
        this(world);
    }

    @Override
    protected void initPathfinder() {
        super.initPathfinder();
        Vec3D targetVector = new Vec3D(laneLoc[0], laneLoc[1], laneLoc[2]);
        System.out.println("target vector is: " + targetVector.x + "/" + targetVector.y + "/" + targetVector.z);
        this.goalSelector.a(7, new CustomPathfinderGoalCopied(this, targetVector));
    }
}
