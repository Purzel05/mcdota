package de.prentl.firsttestproject.customentities;

import net.minecraft.server.v1_15_R1.EntityTypes;
import net.minecraft.server.v1_15_R1.Vec3D;
import net.minecraft.server.v1_15_R1.World;

public class BlueCenterZombie extends CustomZombie {

    public static final Vec3D spawnLoc = new Vec3D(12.0D, 4.0D, 12.0D);
    public static final Vec3D laneLoc = new Vec3D( 45.0D, 4.0D, 45.0D);
    public static final Vec3D finalLoc = new Vec3D(90.0D, 4.0D, 90.0D);

    public Vec3D nextLoc = laneLoc;

    public BlueCenterZombie(World world) {
        super(world);
    }

    public BlueCenterZombie(EntityTypes<BlueCenterZombie> blueCenterZombieEntityTypes, World world) {
        this(world);
    }

    @Override
    protected void initPathfinder() {
        if (nextLoc == null) {
            nextLoc = laneLoc;
        } else if (Math.abs(this.locX() - laneLoc.x) < 2 && Math.abs(this.locY() - laneLoc.y) < 2 && Math.abs(this.locZ() - laneLoc.z) < 2) {
            nextLoc = finalLoc;
        }
        this.goalSelector.a(7, new McdZombiePathfinderGoal(this, nextLoc));
    }
}
