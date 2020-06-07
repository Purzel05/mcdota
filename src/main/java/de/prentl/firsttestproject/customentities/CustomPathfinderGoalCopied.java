package de.prentl.firsttestproject.customentities;

import net.minecraft.server.v1_15_R1.EntityCreature;
import net.minecraft.server.v1_15_R1.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_15_R1.Vec3D;
import org.bukkit.Location;

import javax.annotation.Nullable;

public class CustomPathfinderGoalCopied extends PathfinderGoalRandomStroll {

    protected final float h;

    private Location location;

    public CustomPathfinderGoalCopied(EntityCreature entityCreature, Location location) {
        super(entityCreature, 1.0D);
        this.h = 0.001F;
    }

    @Nullable
    protected Vec3D g() {
        return new Vec3D(0.0D,0.0D,80.0D);
    }
}
