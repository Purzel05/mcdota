package de.prentl.firsttestproject.entities;

import java.util.EnumSet;

import net.minecraft.server.v1_15_R1.EntityCreature;
import net.minecraft.server.v1_15_R1.PathfinderGoal;
import net.minecraft.server.v1_15_R1.Vec3D;

public class McdPathfinderGoal extends PathfinderGoal {
    protected final EntityCreature entityCreature;
    protected double targetX;
    protected double targetY;
    protected double targetZ;
    protected final double e;
    protected int f;
    protected boolean g;
    private final Vec3D targetVector;

    public McdPathfinderGoal(EntityCreature entityCreature, Vec3D targetVector) {
        this.entityCreature = entityCreature;
        this.e = 1.0D;
        this.f = 120;
        this.a(EnumSet.of(Type.MOVE));
        this.targetVector = targetVector;
    }

    @Override
    public boolean a() {         // very often called
        this.targetX = targetVector.x;
        this.targetY = targetVector.y;
        this.targetZ = targetVector.z;
        this.g = false;
        return true;
    }

    @Override
    public boolean b() {         // very often called
        return !this.entityCreature.getNavigation().m() && !this.entityCreature.isVehicle();
    }

    @Override
    public void c() {         // very often called
        //super.c();
        //System.out.println("McdZombiePathfinderGoal::c");
        this.entityCreature.getNavigation().a(this.targetX, this.targetY, this.targetZ, this.e);
    }

    @Override
    public void d() {         // very often called
        //super.d();
        //System.out.println("McdZombiePathfinderGoal::d");
        this.entityCreature.getNavigation().o();
    }

    @Override
    public boolean E_() {
        //System.out.println("McdZombiePathfinderGoal::E_");
        return super.E_();
    }

    @Override
    public void e() {         // very often called
        //System.out.println("McdZombiePathfinderGoal::e");
        super.e();
    }

    @Override
    public void a(EnumSet<Type> var0) {
        //System.out.println("McdZombiePathfinderGoal::aEnumSet");
        super.a(var0);
    }

    @Override
    public EnumSet<Type> i() {         // very often called
        //System.out.println("McdZombiePathfinderGoal::i");
        return super.i();
    }
}