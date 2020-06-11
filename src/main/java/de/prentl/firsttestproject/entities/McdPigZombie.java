package de.prentl.firsttestproject.entities;

import de.prentl.firsttestproject.McdMap;
import net.minecraft.server.v1_15_R1.*;
import org.bukkit.Bukkit;

public class McdPigZombie extends EntityPigZombie implements McdEntity {
    private McdMap.Side side;

    public McdPigZombie(World world) {
        super(EntityTypes.ZOMBIE_PIGMAN, world);
    }

    public McdPigZombie(EntityTypes<McdPigZombie> entityTypes, World world) {
        this(world);
    }

    private Vec3D goalLocation;
    private Vec3D laneLocation;
    private Vec3D finalLocation;

    @Override
    protected void initPathfinder() {
        // initialization happens on first call of updateGoalsAndTargets()
    }

    public void updateGoalsAndTargets() {
        if (goalLocation == null || laneLocation == null) {
            return;
        }

        if (EntityUtils.isNearLocation(this, finalLocation)) {
            this.killEntity();
            return;
        }

        Bukkit.getLogger().info(this + ": updateGoalsAndTargets ...");

        if (this.goalSelector.c().count() == 0) {
            Bukkit.getLogger().info(this + ": initializing goals and targets ...");

            this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 1.2D, false));
            this.goalSelector.a(7, new McdPathfinderGoal(this, goalLocation));

            if (this.getSide().equals(McdMap.Side.BLUE)) {
                this.targetSelector.a(1, new McdPathfinderGoalTarget(this,
                        McdPigZombie.class, 10, true, false, McdPigZombie::targetConditionIsYellow));
            }
            if (this.getSide().equals(McdMap.Side.YELLOW)) {
                this.targetSelector.a(1, new McdPathfinderGoalTarget(this,
                        McdPigZombie.class, 10, true, false, McdPigZombie::targetConditionIsBlue));
            }
        }

        if (goalLocation == laneLocation && EntityUtils.isNearLocation(this, laneLocation)) {
            Bukkit.getLogger().info(this + ": handling isNearLocation lane location ...");
            goalLocation = finalLocation;
            this.goalSelector.c().forEach(PathfinderGoalWrapped::d);
            EntityUtils.clearPathfinderGoalCollections(this);
            this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 1.2D, false));
            this.goalSelector.a(7, new McdPathfinderGoal(this, goalLocation));
        }
    }

    public static boolean targetConditionIsBlue(Object object) {
        if (object instanceof McdEntity) {
            return ((McdEntity)object).getSide().equals(McdMap.Side.BLUE);
        } else {
            return false;
        }
    }

    public static boolean targetConditionIsYellow(Object object) {
        if (object instanceof McdEntity) {
            return ((McdEntity)object).getSide().equals(McdMap.Side.YELLOW);
        } else {
            return false;
        }
    }

    public McdMap.Side getSide() {
        return side;
    }

    public void initialize(McdMap.Side side, McdMap.Lane lane) {
        this.side = side;
        this.laneLocation = McdMap.getLocation(side, lane, McdMap.LaneLocation.LANE);
        this.finalLocation = McdMap.getLocation(side, lane, McdMap.LaneLocation.FINAL);
        this.goalLocation = this.laneLocation;
    }
}
