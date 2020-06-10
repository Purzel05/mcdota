package de.prentl.firsttestproject.entities;

import java.util.EnumSet;
import java.util.function.Predicate;
import javax.annotation.Nullable;

import net.minecraft.server.v1_15_R1.*;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;

public class McdPathfinderGoalTarget<T extends EntityLiving> extends PathfinderGoalTarget {
    protected final Class<T> oclass;
    protected final int b;
    protected EntityLiving entityLiving;
    protected PathfinderTargetCondition pathfinderTargetCondition;

    public McdPathfinderGoalTarget(EntityInsentient entityinsentient, Class<T> oclass, int i, boolean flag, boolean flag1, @Nullable Predicate<EntityLiving> predicate) {
        super(entityinsentient, flag, flag1);
        this.oclass = oclass;
        this.b = i;
        this.a(EnumSet.of(Type.TARGET));
        this.pathfinderTargetCondition = (new PathfinderTargetCondition()).a(this.k()).a(predicate);
    }

    public boolean a() {
        if (this.b > 0 && this.e.getRandom().nextInt(this.b) != 0) {
            return false;
        } else {
            this.g();
            return this.entityLiving != null;
        }
    }

    protected AxisAlignedBB a(double d0) {
        return this.e.getBoundingBox().grow(d0, 4.0D, d0);
    }

    protected void g() {
        if (this.oclass != EntityHuman.class && this.oclass != EntityPlayer.class) {
            this.entityLiving = this.e.world.b(this.oclass, this.pathfinderTargetCondition, this.e, this.e.locX(), this.e.getHeadY(), this.e.locZ(), this.a(this.k()));
        } else {
            this.entityLiving = this.e.world.a(this.pathfinderTargetCondition, this.e, this.e.locX(), this.e.getHeadY(), this.e.locZ());
        }
    }

    public void c() {
        this.e.setGoalTarget(this.entityLiving, this.entityLiving instanceof EntityPlayer ? TargetReason.CLOSEST_PLAYER : TargetReason.CLOSEST_ENTITY, true);
        super.c();
    }
}