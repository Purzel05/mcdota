package de.prentl.firsttestproject.customentities;

import net.minecraft.server.v1_15_R1.*;
import org.bukkit.Location;

public class BlueLeftZombie extends CustomZombie {

    public static final Vec3D spawnLoc = new Vec3D(12.0D, 4.0D, 8.0D);
    public static final Vec3D laneLoc = new Vec3D( 90.0D, 4.0D, 8.0D);
    public static final Vec3D finalLoc = new Vec3D(90.0D, 4.0D, 90.0D);

    public Vec3D nextLoc;

    public BlueLeftZombie(World world) { super(world); }

    public BlueLeftZombie(EntityTypes<BlueLeftZombie> blueLeftZombieEntityTypes, World world) {
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
