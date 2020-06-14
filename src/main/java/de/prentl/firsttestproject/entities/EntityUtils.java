package de.prentl.firsttestproject.entities;

import com.google.common.collect.Sets;
import de.prentl.firsttestproject.McdGame;
import de.prentl.firsttestproject.McdMap;
import de.prentl.firsttestproject.McdPlugin;
import net.minecraft.server.v1_15_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.*;

public abstract class EntityUtils {
    public static final int WAVES_SIZE = 4;
    private static boolean skeletonsSpawned = false;

    public static void removeLivingEntities() {
        Bukkit.getLogger().info("removing all entities");
        World world = Bukkit.getWorld(McdPlugin.MAP_WORLD);
        assert world != null;
        world.getLivingEntities().forEach(Entity::remove);
    }

    public static void spawnSkeletons() {
        World world = Bukkit.getWorld(McdPlugin.MAP_WORLD);
        if (!skeletonsSpawned) {
            for (McdMap.Side side: McdPlugin.sides) {
                for (McdMap.Lane lane: McdPlugin.lanes) {
                    for (McdMap.TowerLocation location: McdPlugin.towerLocations) {
                        List<Vec3D> vec3DList = McdMap.getLocation(side, lane, location);
                        if (vec3DList != null) {
                            for (Vec3D spawnVec : vec3DList) {
                                assert spawnVec != null;
                                Location spawnLocation = new Location(world, spawnVec.x, spawnVec.y, spawnVec.z);
                                McdSkeleton skeleton = CustomEntityType.skeletonType.spawn(spawnLocation);
                                if (skeleton != null) {
                                    skeleton.initialize(side, lane, location, spawnVec);
                                    McdPlugin.entitiesInsentient.add(skeleton);
                                } else {
                                    Bukkit.getLogger().warning("spawned skeleton is null (chunk not loaded?)");
                                }
                            }
                        }
                    }
                }
            }
            skeletonsSpawned = true;
        }

        assert world != null;
        world.getLivingEntities().forEach(livingEntity -> {
            EntityLiving entityLiving = ((CraftLivingEntity)livingEntity).getHandle();
            if (entityLiving instanceof McdSkeleton) {
                McdSkeleton skeleton = (McdSkeleton)entityLiving;
                Vec3D spawnLocation = skeleton.getSpawnLocation();
                livingEntity.teleport(new Location(world, spawnLocation.x, spawnLocation.y, spawnLocation.z));
            }
        });
    }

    public static void spawnPigZombies() {
        World world = Bukkit.getWorld(McdPlugin.MAP_WORLD);
        for (McdMap.Side side: McdPlugin.sides) {
            for (McdMap.Lane lane: McdPlugin.lanes) {
                for (int i = 0; i < WAVES_SIZE; i++) {
                    Vec3D spawnVec = McdMap.getLocation(side, lane, McdMap.LaneLocation.SPAWN);
                    assert spawnVec != null;
                    Location spawnLocation = new Location(world, spawnVec.x + i, spawnVec.y, spawnVec.z);

                    McdPigZombie pigZombie = null;
                    boolean doIt = true;
                    while (doIt) {
                        pigZombie = CustomEntityType.pigZombieType.spawn(spawnLocation);
                        if (pigZombie.isBaby()) {
                            for (LivingEntity livingEntity : world.getLivingEntities()) {
                                EntityLiving entityLiving = ((CraftLivingEntity) livingEntity).getHandle();
                                if (entityLiving == pigZombie) {
                                    livingEntity.remove();
                                }
                            }
                            Bukkit.getLogger().info("Baby zombie removed ...");
                        } else {
                            doIt = false;
                        }
                    }

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

    public static void clearPathfinderGoalCollections(EntityInsentient entityInsentient) {
        Class<PathfinderGoalSelector> clazz = PathfinderGoalSelector.class;
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

    public static boolean targetConditionIsBlue(Object object) {
        if (object instanceof McdEntity) {
            return ((McdEntity) object).getSide().equals(McdMap.Side.BLUE);
        } else if (object instanceof EntityHuman) {
            EntityHuman entityHuman = (EntityHuman) object;
            for (Player player: McdGame.bluePlayers) {
                CraftPlayer craftPlayer = (CraftPlayer) player;
                EntityPlayer craftPlayerHandle = craftPlayer.getHandle();
                if (entityHuman == craftPlayerHandle) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean targetConditionIsYellow(Object object) {
        if (object instanceof McdEntity) {
            return ((McdEntity) object).getSide().equals(McdMap.Side.YELLOW);
        } else if (object instanceof EntityHuman) {
            EntityHuman entityHuman = (EntityHuman) object;
            for (Player player: McdGame.yellowPlayers) {
                CraftPlayer craftPlayer = (CraftPlayer) player;
                EntityPlayer craftPlayerHandle = craftPlayer.getHandle();
                if (entityHuman == craftPlayerHandle) {
                    return true;
                }
            }
        }
        return false;
    }
}
