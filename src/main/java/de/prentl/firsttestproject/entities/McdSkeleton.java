package de.prentl.firsttestproject.entities;

import de.prentl.firsttestproject.McdMap;
import de.prentl.firsttestproject.McdPlugin;
import net.minecraft.server.v1_15_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;

import java.util.Objects;

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
            int scanRangeQM = 4;
            this.goalSelector.c().forEach(PathfinderGoalWrapped::d);
            EntityUtils.clearPathfinderGoalCollections(this);
            this.goalSelector.a(0, new McdPathfinderGoalBowShoot(this, 1.0D, 1, 15.0F ));
            this.goalSelector.a(7, new McdPathfinderGoal(this, spawnLocation));
            if (this.getSide().equals(McdMap.Side.BLUE)) {
                this.targetSelector.a(1, new McdPathfinderGoalTarget(this,
                        McdPigZombie.class, scanRangeQM, true, false, McdSkeleton::targetConditionBlue));
                this.targetSelector.a(4, new McdPathfinderGoalTarget(this,
                        EntityHuman.class, scanRangeQM, true, false, McdSkeleton::targetConditionBlue));
            }
            if (this.getSide().equals(McdMap.Side.YELLOW)) {
                this.targetSelector.a(1, new McdPathfinderGoalTarget(this,
                        McdPigZombie.class, scanRangeQM, true, false, McdSkeleton::targetConditionYellow));
                this.targetSelector.a(4, new McdPathfinderGoalTarget(this,
                        EntityHuman.class, scanRangeQM, true, false, McdSkeleton::targetConditionYellow));
            }
        }
    }

    public static boolean targetConditionBlue(Object object) {
        return EntityUtils.targetConditionIsYellow(object);
    }

    public static boolean targetConditionYellow(Object object) {
        return EntityUtils.targetConditionIsBlue(object);
    }

    public void initialize(McdMap.Side side, McdMap.Lane lane, McdMap.TowerLocation location, Vec3D spawnLocation) {
        this.setPersistent();
        this.side = side;
        this.spawnLocation = spawnLocation;

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
                AttributeInstance movementSpeed = livingEntity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
                assert movementSpeed != null;
                movementSpeed.setBaseValue(0);
                AttributeInstance maxHealth = livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                assert maxHealth != null;
                maxHealth.setBaseValue(200);
            }
        }

        this.setHealth(200.0F);
    }

    public McdMap.Side getSide() {
        return side;
    }

    public Vec3D getSpawnLocation() {
        return spawnLocation;
    }
}
