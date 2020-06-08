package de.prentl.firsttestproject.customentities;

import net.minecraft.server.v1_15_R1.EntityCreature;
import net.minecraft.server.v1_15_R1.Vec3D;

public class McdZombiePathfinderGoal extends McdPathfinderGoalRandomStroll {

    protected final float h;
    private final Vec3D targetVector;

    public McdZombiePathfinderGoal(EntityCreature entityCreature, Vec3D targetVector) {
        super(entityCreature, 1.0D);
        this.h = 0.001F;
        this.targetVector = targetVector;
    }

    public boolean a() {
        this.b = targetVector.x;
        this.c = targetVector.y;
        this.d = targetVector.z;
        this.g = false;
        return true;
    }
}
