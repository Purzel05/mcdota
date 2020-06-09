package de.prentl.firsttestproject.entities.zombies;

import net.minecraft.server.v1_15_R1.*;

public class BlueRightZombie extends McdZombie {

    public static final Vec3D spawnLoc = new Vec3D(10.0D, 4.0D, 10.0D);
    private static final Vec3D laneLoc = new Vec3D( 10.0D, 4.0D, 80.0D);
    private static final Vec3D finalLoc = new Vec3D(80.0D, 4.0D, 80.0D);

    public BlueRightZombie(World world) {
        super(world);
    }

    public BlueRightZombie(EntityTypes<BlueRightZombie> blueRightZombieEntityTypes, World world) { this(world); }

    @Override
    protected Vec3D getLaneLocation() {
        return laneLoc;
    }

    @Override
    protected Vec3D getFinalLocation() {
        return finalLoc;
    }
}
