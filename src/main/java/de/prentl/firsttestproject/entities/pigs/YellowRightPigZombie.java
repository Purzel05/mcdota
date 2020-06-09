package de.prentl.firsttestproject.entities.pigs;

import de.prentl.firsttestproject.entities.zombies.McdZombie;
import net.minecraft.server.v1_15_R1.EntityTypes;
import net.minecraft.server.v1_15_R1.Vec3D;
import net.minecraft.server.v1_15_R1.World;

public class YellowRightPigZombie extends McdPigZombie {

    public static final Vec3D spawnLoc = new Vec3D(96.0D, 4.0D, 96.0D);
    private static final Vec3D laneLoc = new Vec3D( 96.0D, 4.0D, 4.0D);
    private static final Vec3D finalLoc = new Vec3D(4.0D, 4.0D, 4.0D);

    public YellowRightPigZombie(World world) {
        super(world);
    }

    public YellowRightPigZombie(EntityTypes<YellowRightPigZombie> entityTypes, World world) { this(world); }

    @Override
    protected Vec3D getLaneLocation() {
        return laneLoc;
    }

    @Override
    protected Vec3D getFinalLocation() {
        return finalLoc;
    }
}
