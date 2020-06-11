package de.prentl.firsttestproject.entities.pigs;

import de.prentl.firsttestproject.Side;
import de.prentl.firsttestproject.entities.EntityUtils;
import de.prentl.firsttestproject.entities.McdEntity;
import de.prentl.firsttestproject.entities.McdPathfinderGoal;
import de.prentl.firsttestproject.entities.McdPathfinderGoalTarget;
import net.minecraft.server.v1_15_R1.*;
import org.bukkit.Bukkit;

public class McdPigZombie extends EntityPigZombie implements McdEntity {

    public static final Vec3D blueSpawnLocation = new Vec3D(4.0D, 4.0D, 4.0D);
    public static final Vec3D blueFinalLocation = new Vec3D(96.0D, 4.0D, 96.0D);

    public static final Vec3D yellowSpawnLocation = new Vec3D(96.0D, 4.0D, 96.0D);
    public static final Vec3D yellowFinalLocation = new Vec3D(4.0D, 4.0D, 4.0D);

    public static final Vec3D yellowLeftLaneLocation = new Vec3D( 4.0D, 4.0D, 96.0D);
    public static final Vec3D yellowCenterLaneLocation = new Vec3D( 50.0D, 4.0D, 50.0D);
    public static final Vec3D yellowRightLaneLocation = new Vec3D( 96.0D, 4.0D, 4.0D);

    public static final Vec3D blueLeftLaneLocation = new Vec3D( 96.0D, 4.0D, 4.0D);
    public static final Vec3D blueCenterLaneLocation = new Vec3D( 50.0D, 4.0D, 50.0D);
    public static final Vec3D blueRightLaneLocation = new Vec3D( 4.0D, 4.0D, 96.0D);

    private Side side;

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

        if (this.getSide().equals(Side.BLUE)) {
            this.targetSelector.a(1, new McdPathfinderGoalTarget(this,
                    McdPigZombie.class, 10, true, false, McdPigZombie::targetConditionIsYellow));
        }
        if (this.getSide().equals(Side.YELLOW)) {
            this.targetSelector.a(1, new McdPathfinderGoalTarget(this,
                    McdPigZombie.class, 10, true, false, McdPigZombie::targetConditionIsBlue));
        }
    }

    public static boolean targetConditionIsBlue(Object object) {
        if (object instanceof McdPigZombie) {
            return ((McdPigZombie)object).getSide().equals(Side.BLUE);
        } else {
            return false;
        }
    }

    public static boolean targetConditionIsYellow(Object object) {
        if (object instanceof McdPigZombie) {
            return ((McdPigZombie)object).getSide().equals(Side.YELLOW);
        } else {
            return false;
        }
    }

    public void updateGoalsAndTargets() {
        if (EntityUtils.isNearLocation(this, yellowFinalLocation)) {
            this.killEntity();
            return;
        }

        if (goalLocation == null) {
            Vec3D ll = getLaneLocation();
            Bukkit.getLogger().info("setting goal location to lane location: " + ll);
            goalLocation = ll;
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

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public void setLaneLocation(Vec3D laneLocation) {
        this.laneLocation = laneLocation;
    }
}
