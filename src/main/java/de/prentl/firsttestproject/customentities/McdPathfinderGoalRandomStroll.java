package de.prentl.firsttestproject.customentities;

import java.util.EnumSet;

import net.minecraft.server.v1_15_R1.EntityCreature;
import net.minecraft.server.v1_15_R1.PathfinderGoal;

public abstract class McdPathfinderGoalRandomStroll extends PathfinderGoal {
    protected final EntityCreature entityCreature;
    protected double b;
    protected double c;
    protected double d;
    protected final double e;
    protected int f;
    protected boolean g;

    public McdPathfinderGoalRandomStroll(EntityCreature var0, double var1) {
        this(var0, var1, 120);
    }

    public McdPathfinderGoalRandomStroll(EntityCreature var0, double var1, int var3) {
        this.entityCreature = var0;
        this.e = var1;
        this.f = var3;
        this.a(EnumSet.of(Type.MOVE));
    }

    public boolean b() {
        return !this.entityCreature.getNavigation().m() && !this.entityCreature.isVehicle();
    }

    public void c() {
        this.entityCreature.getNavigation().a(this.b, this.c, this.d, this.e);
    }

    public void d() {
        this.entityCreature.getNavigation().o();
        super.d();
    }

    public void h() {
        this.g = true;
    }

    public void setTimeBetweenMovement(int var0) {
        this.f = var0;
    }
}