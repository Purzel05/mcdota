package de.prentl.firsttestproject.entities;

import java.util.EnumSet;

import net.minecraft.server.v1_15_R1.*;

public class McdPathfinderGoalMeleeAttack extends PathfinderGoal {
    protected final EntityCreature entityCreature;
    protected int b;
    private final double speedWhileMovingToTarget; // absolute or relative to normal speed? I guess relative
    private final boolean e;
    private PathEntity f;
    private int g;
    private double targetX;
    private double targetY;
    private double targetZ;
    protected final int c = 20;
    private long k;

    public McdPathfinderGoalMeleeAttack(EntityCreature entityCreature, double speedWhileMovingToTarget, boolean var3) {
        this.entityCreature = entityCreature;
        this.speedWhileMovingToTarget = speedWhileMovingToTarget;
        this.e = var3;
        this.a(EnumSet.of(Type.MOVE, Type.LOOK));
    }

    public boolean a() {
        long time = this.entityCreature.world.getTime();
        if (time - this.k < 20L) {
            return false;
        } else {
            this.k = time;
            EntityLiving goalTargetEntity = this.entityCreature.getGoalTarget();
            if (goalTargetEntity == null) {
                return false;
            } else if (!goalTargetEntity.isAlive()) {
                return false;
            } else {
                this.f = this.entityCreature.getNavigation().a(goalTargetEntity, 0);
                if (this.f != null) {
                    return true;
                } else {
                    return this.fourTimesWidthSquaredPlusWidth(goalTargetEntity) >= this.entityCreature.g(goalTargetEntity.locX(), goalTargetEntity.locY(), goalTargetEntity.locZ());
                }
            }
        }
    }

    public boolean b() {
        EntityLiving goalTargetEntity = this.entityCreature.getGoalTarget();
        if (goalTargetEntity == null) {
            return false;
        } else if (!goalTargetEntity.isAlive()) {
            return false;
        } else if (!this.e) {
            return !this.entityCreature.getNavigation().m();
        } else if (!this.entityCreature.a(new BlockPosition(goalTargetEntity))) {
            return false;
        } else {
            return !(goalTargetEntity instanceof EntityHuman) || !goalTargetEntity.isSpectator() && !((EntityHuman)goalTargetEntity).isCreative();
        }
    }

    public void c() {
        this.entityCreature.getNavigation().a(this.f, this.speedWhileMovingToTarget);
        this.entityCreature.q(true);
        this.g = 0;
    }

    public void d() {
        EntityLiving var0 = this.entityCreature.getGoalTarget();
        if (!IEntitySelector.e.test(var0)) {
            this.entityCreature.setGoalTarget((EntityLiving)null);
        }

        this.entityCreature.q(false);
        this.entityCreature.getNavigation().o();
    }

    public void e() {
        EntityLiving goalTargetEntity = this.entityCreature.getGoalTarget();
        this.entityCreature.getControllerLook().a(goalTargetEntity, 30.0F, 30.0F);

        double sumOfDeltasSquared = this.entityCreature.g(goalTargetEntity.locX(), goalTargetEntity.locY(), goalTargetEntity.locZ());
        /*public double g(double d0, double d1, double d2) {
        double d3 = this.locX() - d0;
        double d4 = this.locY() - d1;
        double d5 = this.locZ() - d2;
        return d3 * d3 + d4 * d4 + d5 * d5;}*/

        --this.g; // wtf is this g? here it is reduced ...
        // I guess g is some optimization so that not every entity is worked on every tick, and more often under certain circumstances
        if ((this.e || this.entityCreature.getEntitySenses().a(goalTargetEntity)) && this.g <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || goalTargetEntity.g(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.entityCreature.getRandom().nextFloat() < 0.05F)) {
            this.targetX = goalTargetEntity.locX();
            this.targetY = goalTargetEntity.locY();
            this.targetZ = goalTargetEntity.locZ();
            this.g = 4 + this.entityCreature.getRandom().nextInt(7);
            if (sumOfDeltasSquared > 1024.0D) {
                this.g += 10;
            } else if (sumOfDeltasSquared > 256.0D) {
                this.g += 5;
            }

            if (!this.entityCreature.getNavigation().a(goalTargetEntity, this.speedWhileMovingToTarget)) {
                this.g += 15;
            }
        }

        this.b = Math.max(this.b - 1, 0);
        this.a(goalTargetEntity, sumOfDeltasSquared);
    }

    protected void a(EntityLiving entityLiving, double sumOfDeltasSquared) {
        double fourTimesWidthSquaredPlusWidth = this.fourTimesWidthSquaredPlusWidth(entityLiving);
        if (sumOfDeltasSquared <= fourTimesWidthSquaredPlusWidth && this.b <= 0) {
            this.b = 20;
            this.entityCreature.a(EnumHand.MAIN_HAND);
            this.entityCreature.B(entityLiving);
        }
    }

    protected double fourTimesWidthSquaredPlusWidth(EntityLiving entityLiving) {
        return (double)(this.entityCreature.getWidth() * 2.0F * this.entityCreature.getWidth() * 2.0F + entityLiving.getWidth());
    }
}
