package de.prentl.firsttestproject.entities;

import com.google.common.collect.Sets;
import de.prentl.firsttestproject.McdMap;
import de.prentl.firsttestproject.McdPlugin;
import net.minecraft.server.v1_15_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public abstract class EntityUtils {
    public static final int WAVES_SIZE = 4;
    public static boolean skeletonsSpawned = false;

    public static void  removeLivingEntities() {
        World world = Bukkit.getWorld(McdPlugin.MAP_WORLD);
        assert world != null;
        world.getLivingEntities().forEach(Entity::remove);
    }

    public static void spawnSkeletons() {
        World world = Bukkit.getWorld(McdPlugin.MAP_WORLD);
        if (!skeletonsSpawned) {
            McdMap.Side side = McdMap.Side.BLUE;
            McdMap.Lane lane = McdMap.Lane.CENTER;
            McdMap.TowerLocation location = McdMap.TowerLocation.RIVER;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Vec3D spawnVec = McdMap.getLocation(side, lane, location);
                    assert spawnVec != null;
                    Location spawnLocation = new Location(world, spawnVec.x + i, spawnVec.y, spawnVec.z);
                    McdSkeleton skeleton = CustomEntityType.skeletonType.spawn(spawnLocation);
                    if (skeleton != null) {
                        skeleton.initialize(side, lane, location);
                        McdPlugin.entitiesInsentient.add(skeleton);
                    } else {
                        Bukkit.getLogger().warning("spawned skeleton is null (chunk not loaded?)");
                    }
                }
            }
            skeletonsSpawned = true;
        }
    }

    public static void spawnPigZombies() {
        World world = Bukkit.getWorld(McdPlugin.MAP_WORLD);
        for (McdMap.Side side: McdPlugin.sides) {
            for (McdMap.Lane lane: McdPlugin.lanes) {
                for (int i = 0; i < WAVES_SIZE; i++) {
                    Vec3D spawnVec = McdMap.getLocation(side, lane, McdMap.LaneLocation.SPAWN);
                    Location spawnLocation = new Location(world, spawnVec.x + i, spawnVec.y, spawnVec.z);
                    McdPigZombie pigZombie = CustomEntityType.pigZombieType.spawn(spawnLocation);
                    if (pigZombie != null) {
                        pigZombie.initialize(side, lane);
                        McdPlugin.entitiesInsentient.add(pigZombie);
                    } else {
                        Bukkit.getLogger().warning("spawned pig zombie is null (chunk not loaded?)");
                    }
                }
            }
        }
    }

    public static void equipPigZombiesAndSkeletons() {
        World world = Bukkit.getWorld(McdPlugin.MAP_WORLD);
        assert world != null;
        world.getLivingEntities().forEach(livingEntity -> {
            EntityLiving entityLiving = ((CraftLivingEntity)livingEntity).getHandle();
            if (entityLiving instanceof McdEntity) {
                McdEntity mcdEntity = (McdEntity) entityLiving;
                if (mcdEntity.getSide().equals(McdMap.Side.BLUE)) {
                    Objects.requireNonNull(livingEntity.getEquipment()).setHelmet(new org.bukkit.inventory.ItemStack(org.bukkit.Material.IRON_HELMET));
                    Objects.requireNonNull(livingEntity.getEquipment()).setChestplate(new org.bukkit.inventory.ItemStack(org.bukkit.Material.IRON_CHESTPLATE));
                }
                if (mcdEntity.getSide().equals(McdMap.Side.YELLOW)) {
                    Objects.requireNonNull(livingEntity.getEquipment()).setHelmet(new org.bukkit.inventory.ItemStack(org.bukkit.Material.GOLDEN_HELMET));
                    Objects.requireNonNull(livingEntity.getEquipment()).setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
                }
            }
        });
    }

    public static void clearPathfinderGoalCollections(EntityInsentient entityInsentient) {
        Class clazz = PathfinderGoalSelector.class;
        try {
            Map<PathfinderGoal.Type, PathfinderGoalWrapped> c = new EnumMap(PathfinderGoal.Type.class);
            Field field = clazz.getDeclaredField("c");
            field.setAccessible(true);
            field.set(entityInsentient.goalSelector, c);

            Set<PathfinderGoalWrapped> d = Sets.newLinkedHashSet();
            field = clazz.getDeclaredField("d");
            field.setAccessible(true);
            field.set(entityInsentient.goalSelector, d);
        } catch (NoSuchFieldException | IllegalAccessException noSuchFieldException) {
            noSuchFieldException.printStackTrace();
        }
    }

    public static boolean isNearLocation(EntityInsentient entityInsentient, Vec3D vec3D) {
        return Math.abs(entityInsentient.locX() - vec3D.x) < 2
                && Math.abs(entityInsentient.locY() - vec3D.y) < 2
                && Math.abs(entityInsentient.locZ() - vec3D.z) < 2;
    }
}
