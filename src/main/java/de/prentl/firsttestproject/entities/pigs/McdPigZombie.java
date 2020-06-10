package de.prentl.firsttestproject.entities.pigs;

import de.prentl.firsttestproject.entities.EntityUtils;
import de.prentl.firsttestproject.entities.McdPathfinderGoal;
import de.prentl.firsttestproject.entities.zombies.McdZombie;
import net.minecraft.server.v1_15_R1.*;

public class McdPigZombie extends EntityPigZombie {

    public static final Vec3D yellowSpawnLocation = new Vec3D(96.0D, 4.0D, 96.0D);
    public static final Vec3D yellowFinalLocation = new Vec3D(4.0D, 4.0D, 4.0D);

    public static final Vec3D yellowLeftLaneLocation = new Vec3D( 50.0D, 4.0D, 50.0D);
    public static final Vec3D yellowCenterLaneLocation = new Vec3D( 50.0D, 4.0D, 50.0D);
    public static final Vec3D yellowRightLaneLocation = new Vec3D( 96.0D, 4.0D, 4.0D);


    public McdPigZombie(World world) {
        super(EntityTypes.ZOMBIE_PIGMAN, world);
    }

    public McdPigZombie(EntityTypes<McdPigZombie> entityTypes, World world) {
        this(world);
    }

    private Vec3D goalLocation;
    private Vec3D laneLocation;

    private Vec3D getGoalLocation() {
        return goalLocation == null ? yellowSpawnLocation : goalLocation;
    }

    private Vec3D getLaneLocation() {
        return laneLocation == null ? yellowCenterLaneLocation : laneLocation;
    }

    @Override
    protected void initPathfinder() {
        this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 1.2D, false));
        this.goalSelector.a(7, new McdPathfinderGoal(this, getGoalLocation()));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, McdZombie.class, true));
    }

    public void updateGoalsAndTargets() {
        if (EntityUtils.isNearLocation(this, yellowFinalLocation)) {
            this.killEntity();
            return;
        }

        if (goalLocation == null) {
            goalLocation = getLaneLocation();
            this.goalSelector.c().forEach(PathfinderGoalWrapped::d);
            EntityUtils.clearPathfinderGoalCollections(this);
            this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 1.2D, false));
            this.goalSelector.a(7, new McdPathfinderGoal(this, getGoalLocation()));
        }

        if (goalLocation == getLaneLocation() && EntityUtils.isNearLocation(this, getLaneLocation())) {
            goalLocation = yellowFinalLocation;
            this.goalSelector.c().forEach(PathfinderGoalWrapped::d);
            EntityUtils.clearPathfinderGoalCollections(this);
            this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 1.2D, false));
            this.goalSelector.a(7, new McdPathfinderGoal(this, getGoalLocation()));
        }
    }

    public void setLaneLocation(Vec3D laneLocation) {
        this.laneLocation = laneLocation;
    }
}
