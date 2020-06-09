package de.prentl.firsttestproject.entities.pigs;

import de.prentl.firsttestproject.entities.EntityUtils;
import de.prentl.firsttestproject.entities.McdPathfinderGoal;
import de.prentl.firsttestproject.entities.zombies.McdZombie;
import net.minecraft.server.v1_15_R1.*;

public abstract class McdPigZombie extends EntityPigZombie {

    public McdPigZombie(World world) {
        super(EntityTypes.ZOMBIE_PIGMAN, world);
    }

    public McdPigZombie(EntityTypes<McdPigZombie> entityTypes, World world) {
        this(world);
    }

    private Vec3D nextLoc;

    protected abstract Vec3D getLaneLocation();
    protected abstract Vec3D getFinalLocation();

    @Override
    protected void initPathfinder() {
        updateGoalsAndTargets();
    }

    public void updateGoalsAndTargets() {

        if (EntityUtils.isNearLocation(this, getFinalLocation())) {
            this.killEntity();
            return;
        }

        if (this.goalSelector.c().count() == 0) {
            if (nextLoc == null) { nextLoc = getLaneLocation(); }
            this.goalSelector.a(7, new McdPathfinderGoal(this, nextLoc));
            this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 1.2D, false));
        }

        if (this.targetSelector.c().count() == 0) {
            this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, McdZombie.class, true));
        }

        if (nextLoc == getLaneLocation() && EntityUtils.isNearLocation(this, getLaneLocation())) {
            System.out.println("nextLoc is now finalLoc");
            nextLoc = getFinalLocation();
            this.goalSelector.c().forEach(PathfinderGoalWrapped::d);
            EntityUtils.clearPathfinderGoalCollections(this);
            this.goalSelector.a(7, new McdPathfinderGoal(this, nextLoc));
        }
    }
}
