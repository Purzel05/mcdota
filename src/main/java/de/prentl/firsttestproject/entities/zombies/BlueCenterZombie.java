package de.prentl.firsttestproject.entities.zombies;

import net.minecraft.server.v1_15_R1.*;

public class BlueCenterZombie extends McdZombie {

    public static final Vec3D spawnLoc = new Vec3D(4.0D, 4.0D, 4.0D);
    private static final Vec3D laneLoc = new Vec3D( 50.0D, 4.0D, 50.0D);
    private static final Vec3D finalLoc = new Vec3D(96.0D, 4.0D, 96.0D);

    public BlueCenterZombie(World world) {
        super(world);
    }

    public BlueCenterZombie(EntityTypes<BlueCenterZombie> blueCenterZombieEntityTypes, World world) {
        this(world);
    }

    @Override
    protected Vec3D getLaneLocation() {
        return laneLoc;
    }

    @Override
    protected Vec3D getFinalLocation() {
        return finalLoc;
    }
}