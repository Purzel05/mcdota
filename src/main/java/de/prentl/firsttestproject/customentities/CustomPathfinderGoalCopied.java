package de.prentl.firsttestproject.customentities;

import net.minecraft.server.v1_15_R1.EntityCreature;
import net.minecraft.server.v1_15_R1.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_15_R1.Vec3D;
import org.bukkit.Location;

import javax.annotation.Nullable;

public class CustomPathfinderGoalCopied extends PathfinderGoalRandomStroll {

    protected final float h;
    EntityCreature entityCreature;
    private final Vec3D targetVector;

    public CustomPathfinderGoalCopied(EntityCreature entityCreature, Vec3D targetVector) {
        super(entityCreature, 1.0D);
        this.h = 0.001F;
        this.targetVector = targetVector;
        this.entityCreature = entityCreature;
    }

    @Nullable
    protected Vec3D g() {
        Vec3D entityVector = new Vec3D(entityCreature.locX(), entityCreature.locY(), entityCreature.locZ());
        Vec3D deltaVector = new Vec3D(targetVector.x - entityVector.x, targetVector.y - entityVector.y,
                targetVector.z - entityVector.z);

        System.out.println("delta vector is: " + deltaVector.x + "/" + deltaVector.y + "/" + deltaVector.z);

        return deltaVector;
    }
}
