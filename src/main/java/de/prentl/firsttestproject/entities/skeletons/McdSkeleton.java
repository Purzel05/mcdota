package de.prentl.firsttestproject.entities.skeletons;

import de.prentl.firsttestproject.entities.EntityUtils;
import de.prentl.firsttestproject.entities.McdPathfinderGoal;
import de.prentl.firsttestproject.entities.McdPathfinderGoalTarget;
import de.prentl.firsttestproject.entities.pigs.McdPigZombie;
import de.prentl.firsttestproject.entities.pigs.YellowCenterPigZombie;
import de.prentl.firsttestproject.entities.pigs.YellowLeftPigZombie;
import de.prentl.firsttestproject.entities.pigs.YellowRightPigZombie;
import net.minecraft.server.v1_15_R1.*;

public class McdSkeleton extends EntitySkeleton {

    public static final Vec3D blueCenterLocation = new Vec3D(37.0D, 5.0D, 48.0D);

    private Vec3D location;

    public McdSkeleton(World world) {
        super(EntityTypes.SKELETON, world);
    }

    public McdSkeleton(EntityTypes<McdSkeleton> entityTypes, World world) {
        this(world);
    }

    protected Vec3D getLocation() {
        return location == null ? blueCenterLocation : location;
    }

    @Override
    protected void initPathfinder() {
        this.goalSelector.a(7, new McdPathfinderGoal(this, getLocation()));
        this.targetSelector.a(1, new McdPathfinderGoalTarget(this,
                McdPigZombie.class, 10, true, false, McdSkeleton::targetCondition));

    }

    public static boolean targetCondition(Object object) {
        if (object instanceof YellowLeftPigZombie
                || object instanceof YellowCenterPigZombie
                || object instanceof YellowRightPigZombie) {
            return true;
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
}
