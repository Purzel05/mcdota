package de.prentl.firsttestproject.entities;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import net.minecraft.server.v1_15_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;

public class McdPathfinderGoalHurtByTarget extends PathfinderGoalTarget {
    private static final PathfinderTargetCondition a = (new PathfinderTargetCondition()).c().e();
    private boolean b;
    private int c;
    private final Class<?>[] targetingClasses;

    private Class<?>[] i;

    public McdPathfinderGoalHurtByTarget(EntityCreature entitycreature, Class<?>... targetingClasses) {
        super(entitycreature, true);
        this.targetingClasses = targetingClasses;
        this.a(EnumSet.of(Type.TARGET));
    }

    public boolean a() {
        int i = this.e.cI();
        EntityLiving entityliving = this.e.getLastDamager();
        if (i != this.c && entityliving != null) {
            Class[] aclass = this.targetingClasses;
            int j = aclass.length;

            for(int k = 0; k < j; ++k) {
                Class<?> oclass = aclass[k];
                if (oclass.isAssignableFrom(entityliving.getClass())) {
                    return false;
                }
            }

            return this.a(entityliving, a);
        } else {
            return false;
        }
    }

    public McdPathfinderGoalHurtByTarget a(Class<?>... aclass) {
        this.b = true;
        this.i = aclass;
        return this;
    }

    public void c() {
        if (this.e instanceof McdPigZombie && this.e.getLastDamager() instanceof McdSkeleton) {
            McdPigZombie mcdPigZombie = (McdPigZombie) this.e;
            McdSkeleton mcdSkeleton = (McdSkeleton) this.e.getLastDamager();
            if (!mcdPigZombie.getSide().equals(mcdSkeleton.getSide())) {
                this.e.setGoalTarget(this.e.getLastDamager(), TargetReason.TARGET_ATTACKED_ENTITY, true);
            } else {
                Bukkit.getLogger().fine("preventing attack on same-side skeleton ...");
            }
        }

        this.g = this.e.getGoalTarget();
        this.c = this.e.cI();
        this.h = 300;
        if (this.b) {
            this.g();
        }

        super.c();
    }

    protected void g() {
        double d0 = this.k();
        List<EntityInsentient> list = this.e.world.b(this.e.getClass(), (new AxisAlignedBB(this.e.locX(), this.e.locY(), this.e.locZ(), this.e.locX() + 1.0D, this.e.locY() + 1.0D, this.e.locZ() + 1.0D)).grow(d0, 10.0D, d0));
        Iterator iterator = list.iterator();

        while(true) {
            EntityInsentient entityinsentient;
            boolean flag;
            do {
                do {
                    do {
                        do {
                            do {
                                if (!iterator.hasNext()) {
                                    return;
                                }

                                entityinsentient = (EntityInsentient)iterator.next();
                            } while(this.e == entityinsentient);
                        } while(entityinsentient.getGoalTarget() != null);
                    } while(this.e instanceof EntityTameableAnimal && ((EntityTameableAnimal)this.e).getOwner() != ((EntityTameableAnimal)entityinsentient).getOwner());
                } while(entityinsentient.r(this.e.getLastDamager()));

                if (this.i == null) {
                    break;
                }

                flag = false;
                Class[] aclass = this.i;
                int i = aclass.length;

                for(int j = 0; j < i; ++j) {
                    Class<?> oclass = aclass[j];
                    if (entityinsentient.getClass() == oclass) {
                        flag = true;
                        break;
                    }
                }
            } while(flag);

            this.a(entityinsentient, this.e.getLastDamager());
        }
    }

    protected void a(EntityInsentient entityinsentient, EntityLiving entityliving) {
        entityinsentient.setGoalTarget(entityliving, TargetReason.TARGET_ATTACKED_NEARBY_ENTITY, true);
    }
}
