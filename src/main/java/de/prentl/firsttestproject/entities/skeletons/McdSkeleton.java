package de.prentl.firsttestproject.entities.skeletons;

import de.prentl.firsttestproject.Side;
import de.prentl.firsttestproject.entities.EntityUtils;
import de.prentl.firsttestproject.entities.McdEntity;
import de.prentl.firsttestproject.entities.McdPathfinderGoal;
import de.prentl.firsttestproject.entities.McdPathfinderGoalTarget;
import de.prentl.firsttestproject.entities.pigs.McdPigZombie;
import net.minecraft.server.v1_15_R1.*;

public class McdSkeleton extends EntitySkeleton implements McdEntity {

    public static final Vec3D blueCenterLocation = new Vec3D(37.0D, 5.0D, 48.0D);

    private Side side;

    private Vec3D location;

    public McdSkeleton(World world) {
        super(EntityTypes.SKELETON, world);
    }

    public McdSkeleton(EntityTypes<McdSkeleton> entityTypes, World world) {
        this(world);
    }

    private Vec3D getLocation() {
        return location == null ? blueCenterLocation : location;
    }

    @Override
    protected void initPathfinder() {
        this.goalSelector.a(7, new McdPathfinderGoal(this, getLocation()));

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

    public void setLocation(Vec3D location) {
        this.location = location;
        this.goalSelector.c().forEach(PathfinderGoalWrapped::d);
        EntityUtils.clearPathfinderGoalCollections(this);
        this.goalSelector.a(7, new McdPathfinderGoal(this, getLocation()));
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }
}
