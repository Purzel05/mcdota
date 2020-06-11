package de.prentl.firsttestproject.entities;

import de.prentl.firsttestproject.McdMap;
import net.minecraft.server.v1_15_R1.*;

public class McdSkeleton extends EntitySkeleton implements McdEntity {

    private McdMap.Side side;

    private Vec3D spawnLocation;

    public McdSkeleton(World world) {
        super(EntityTypes.SKELETON, world);
    }

    public McdSkeleton(EntityTypes<McdSkeleton> entityTypes, World world) {
        this(world);
    }


    @Override
    protected void initPathfinder() {
        // initialization happens on first call of updateGoalsAndTargets()
    }

    public void updateGoalsAndTargets() {
        if (spawnLocation == null) {
            return;
        }

        if (this.goalSelector.c().count() == 0) {
            this.goalSelector.a(7, new McdPathfinderGoal(this, spawnLocation));

            if (this.getSide().equals(McdMap.Side.BLUE)) {
                this.targetSelector.a(1, new McdPathfinderGoalTarget(this,
                        McdPigZombie.class, 10, true, false, McdSkeleton::targetConditionIsYellow));
            }
            if (this.getSide().equals(McdMap.Side.YELLOW)) {
                this.targetSelector.a(1, new McdPathfinderGoalTarget(this,
                        McdPigZombie.class, 10, true, false, McdSkeleton::targetConditionIsBlue));
            }
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

    public void initialize(McdMap.Side side, McdMap.Lane lane, McdMap.TowerLocation location) {
        this.side = side;
        this.spawnLocation = McdMap.getLocation(side, lane, location);
    }

    public McdMap.Side getSide() {
        return side;
    }
}
