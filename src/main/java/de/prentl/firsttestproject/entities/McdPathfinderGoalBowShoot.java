package de.prentl.firsttestproject.entities;

import java.util.EnumSet;

import net.minecraft.server.v1_15_R1.*;
import net.minecraft.server.v1_15_R1.PathfinderGoal.Type;
import org.bukkit.Bukkit;

public class McdPathfinderGoalBowShoot<T extends EntityMonster & IRangedEntity> extends PathfinderGoal {
    private final T a;
    private final double b;
    private int c;
    private final float d;
    private int e = -1;
    private int f;
    private boolean g;
    private boolean h;
    private int i = -1;

    public McdPathfinderGoalBowShoot(T var0, double var1, int var3, float var4) {
        this.a = var0;
        this.b = var1;
        this.c = var3;
        this.d = var4 * var4;
        this.a(EnumSet.of(Type.MOVE, Type.LOOK));
    }

    public void a(int var0) {
        this.c = var0;
    }

    public boolean a() {
        return this.a.getGoalTarget() == null ? false : this.g();
    }

    protected boolean g() {
        return this.a.a(Items.BOW);
    }

    public boolean b() {
        return (this.a() || !this.a.getNavigation().m()) && this.g();
    }

    public void c() {
        super.c();
        this.a.q(true);
    }

    public void d() {
        super.d();
        this.a.q(false);
        this.f = 0;
        this.e = -1;
        this.a.dH();
    }

    public void e() {
        EntityLiving theSkeletonsCurrentTarget = this.a.getGoalTarget();
        if (theSkeletonsCurrentTarget != null) {

            // var1 was 177 in my debug session
            double var1 = this.a.g(theSkeletonsCurrentTarget.locX(), theSkeletonsCurrentTarget.locY(), theSkeletonsCurrentTarget.locZ());

            boolean myGuessWhetherSkelCanSeePigZobie = this.a.getEntitySenses().a(theSkeletonsCurrentTarget);
            boolean var4 = this.f > 0;
            if (myGuessWhetherSkelCanSeePigZobie != var4) {
                this.f = 0;
            }

            if (myGuessWhetherSkelCanSeePigZobie) {
                ++this.f;
            } else {
                --this.f;
            }

            if (var1 <= (double)this.d && this.f >= 20) {
                this.a.getNavigation().o();
                ++this.i;
            } else {
                this.a.getNavigation().a(theSkeletonsCurrentTarget, this.b);
                this.i = -1;
            }

            if (this.i >= 20) {
                if ((double)this.a.getRandom().nextFloat() < 0.3D) {
                    this.g = !this.g;
                }

                if ((double)this.a.getRandom().nextFloat() < 0.3D) {
                    this.h = !this.h;
                }

                this.i = 0;
            }

            if (this.i > -1) {
                if (var1 > (double)(this.d * 0.75F)) {
                    this.h = false;
                } else if (var1 < (double)(this.d * 0.25F)) {
                    this.h = true;
                }

                this.a.getControllerMove().a(this.h ? -0.5F : 0.5F, this.g ? 0.5F : -0.5F);
                this.a.a(theSkeletonsCurrentTarget, 30.0F, 30.0F);
            } else {
                this.a.getControllerLook().a(theSkeletonsCurrentTarget, 30.0F, 30.0F);
            }

            if (this.a.isHandRaised()) {
                if (!myGuessWhetherSkelCanSeePigZobie && this.f < -60) {
                    this.a.dH();   // dH seems to equip the weapon
                } else if (myGuessWhetherSkelCanSeePigZobie) {
                    int var5 = this.a.dF(); // whats this?
                    if (var5 >= 20) {
                        this.a.dH();

                        // is the second param the damage?
                        float damageQM = ItemBow.a(var5);
                        Bukkit.getLogger().fine("skel shoots with damage? " + damageQM);

                        ((IRangedEntity)this.a).a(theSkeletonsCurrentTarget, damageQM);

                        this.e = this.c;
                    }
                }
            } else if (--this.e <= 0 && this.f >= -60) {
                this.a.c(ProjectileHelper.a(this.a, Items.BOW));
            }

        }
    }
}
