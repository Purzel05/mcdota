package de.prentl.firsttestproject.entities.pigs;

import net.minecraft.server.v1_15_R1.EntityTypes;
import net.minecraft.server.v1_15_R1.Vec3D;
import net.minecraft.server.v1_15_R1.World;

public class YellowCenterPigZombie extends McdPigZombie {

    public static final Vec3D spawnLoc = new Vec3D(96.0D, 4.0D, 96.0D);
    private static final Vec3D laneLoc = new Vec3D( 50.0D, 4.0D, 50.0D);
    private static final Vec3D finalLoc = new Vec3D(4.0D, 4.0D, 4.0D);

    public YellowCenterPigZombie(World world) {
        super(world);
    }

    public YellowCenterPigZombie(EntityTypes<YellowCenterPigZombie> entityTypes, World world) { this(world); }

    @Override
    protected Vec3D getLaneLocation() {
        return laneLoc;
    }

    @Override
    protected Vec3D getFinalLocation() {
        return finalLoc;
    }
}
