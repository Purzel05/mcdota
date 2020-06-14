package de.prentl.firsttestproject.entities;

import de.prentl.firsttestproject.McdMap;
import de.prentl.firsttestproject.McdPlugin;
import net.minecraft.server.v1_15_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Objects;

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

        if (this.goalSelector.c().count() == 0) {
            int scanRangeQM = 4;
            this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 1.2D, false));
            this.goalSelector.a(7, new McdPathfinderGoal(this, goalLocation));
            this.targetSelector.a(1, new McdPathfinderGoalHurtByTarget(this, Player.class));
            if (this.getSide().equals(McdMap.Side.BLUE)) {
                this.targetSelector.a(2, new McdPathfinderGoalTarget(this,
                        McdPigZombie.class, scanRangeQM, true, false, McdPigZombie::targetConditionBlue));
                this.targetSelector.a(3, new McdPathfinderGoalTarget(this,
                        McdSkeleton.class, scanRangeQM, true, false, McdPigZombie::targetConditionBlue));
                this.targetSelector.a(4, new McdPathfinderGoalTarget(this,
                        EntityHuman.class, scanRangeQM, true, false, McdPigZombie::targetConditionBlue));
            }
            if (this.getSide().equals(McdMap.Side.YELLOW)) {
                this.targetSelector.a(2, new McdPathfinderGoalTarget(this,
                        McdPigZombie.class, scanRangeQM, true, false, McdPigZombie::targetConditionYellow));
                this.targetSelector.a(3, new McdPathfinderGoalTarget(this,
                        McdSkeleton.class, scanRangeQM, true, false, McdPigZombie::targetConditionYellow));
                this.targetSelector.a(4, new McdPathfinderGoalTarget(this,
                        EntityHuman.class, scanRangeQM, true, false, McdPigZombie::targetConditionYellow));
            }
        }

        if (goalLocation == laneLocation && EntityUtils.isNearLocation(this, laneLocation)) {
            goalLocation = finalLocation;
            this.goalSelector.c().forEach(PathfinderGoalWrapped::d);
            EntityUtils.clearPathfinderGoalCollections(this);
            this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 1.2D, false));
            this.goalSelector.a(7, new McdPathfinderGoal(this, goalLocation));
        }
    }

    public static boolean targetConditionBlue(Object object) {
        return EntityUtils.targetConditionIsYellow(object);
    }

    public static boolean targetConditionYellow(Object object) {
        return EntityUtils.targetConditionIsBlue(object);
    }

    public McdMap.Side getSide() {
        return side;
    }

    public void initialize(McdMap.Side side, McdMap.Lane lane) {
        this.setPersistent();
        this.side = side;
        this.laneLocation = McdMap.getLocation(side, lane, McdMap.LaneLocation.LANE);
        this.finalLocation = McdMap.getLocation(side, lane, McdMap.LaneLocation.FINAL);
        this.goalLocation = this.laneLocation;

        org.bukkit.World world = Bukkit.getWorld(McdPlugin.MAP_WORLD);
        assert world != null;
        for (LivingEntity livingEntity: world.getLivingEntities()) {
            EntityLiving entityLiving = ((CraftLivingEntity)livingEntity).getHandle();
            if (entityLiving == this) {
                if (this.getSide().equals(McdMap.Side.BLUE)) {
                    Objects.requireNonNull(livingEntity.getEquipment()).setHelmet(new org.bukkit.inventory.ItemStack(org.bukkit.Material.IRON_HELMET));
                } else {
                    Objects.requireNonNull(livingEntity.getEquipment()).setHelmet(new org.bukkit.inventory.ItemStack(org.bukkit.Material.GOLDEN_HELMET));
                }
            }
        }
    }
}
