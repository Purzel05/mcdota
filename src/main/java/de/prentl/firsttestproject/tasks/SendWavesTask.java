package de.prentl.firsttestproject.tasks;

import de.prentl.firsttestproject.McDotaMain;
import de.prentl.firsttestproject.Side;
import de.prentl.firsttestproject.entities.CustomEntityType;
import de.prentl.firsttestproject.entities.pigs.McdPigZombie;
import de.prentl.firsttestproject.entities.skeletons.McdSkeleton;
import net.minecraft.server.v1_15_R1.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class SendWavesTask implements Runnable {

    public static final int WAVES_SIZE = 4;
    public static boolean skeletonsSpawned = false;

    @Override
    public void run() {
        World world = Bukkit.getWorld(McDotaMain.MAP_WORLD);
        spawnSkeletons(world);
        spawnBluePigZombies(world);
        spawnYellowPigZombies(world);
    }

    private void spawnSkeletons(World world) {
        if (!skeletonsSpawned) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Location location = new Location(world, McdSkeleton.blueCenterLocation.x + i,
                            McdSkeleton.blueCenterLocation.y, McdSkeleton.blueCenterLocation.z + j);
                    McdSkeleton skeleton = CustomEntityType.skeletonType.spawn(location);
                    if (skeleton != null) {
                        skeleton.setSide(Side.BLUE);
                        skeleton.setLocation(new Vec3D(location.getX(), location.getY(), location.getZ()));
                        McDotaMain.insentients.add(skeleton);
                    } else {
                        Bukkit.getLogger().warning("spawned skeleton is null (chunk not loaded?)");
                    }
                }
            }
            assert world != null;
            world.getLivingEntities().stream().filter(
                    livingEntity -> Objects.equals(livingEntity.getType().getEntityClass(), org.bukkit.entity.Skeleton.class))
                    .forEach(livingEntity -> {
                        Objects.requireNonNull(livingEntity.getEquipment()).setHelmet(new ItemStack(Material.IRON_HELMET));
                    });

            skeletonsSpawned = true;
        }
    }

    private void spawnBluePigZombies(World world) {
        Location location;
        McdPigZombie pigZombie;

        for (int i = 0; i < WAVES_SIZE; i++) {
            location = new Location(world, McdPigZombie.blueSpawnLocation.x + i,
                    McdPigZombie.blueSpawnLocation.y, McdPigZombie.blueSpawnLocation.z);
            pigZombie = CustomEntityType.pigZombieType.spawn(location);
            if (pigZombie != null) {
                pigZombie.setSide(Side.BLUE);
                pigZombie.setLaneLocation(McdPigZombie.blueLeftLaneLocation);
                McDotaMain.insentients.add(pigZombie);
            } else {
                Bukkit.getLogger().warning("spawned pig zombie is null (chunk not loaded?)");
            }
        }

        for (int i = 0; i < WAVES_SIZE; i++) {
            location = new Location(world, McdPigZombie.blueSpawnLocation.x + i,
                    McdPigZombie.blueSpawnLocation.y, McdPigZombie.blueSpawnLocation.z + i);
            pigZombie = CustomEntityType.pigZombieType.spawn(location);
            if (pigZombie != null) {
                pigZombie.setSide(Side.BLUE);
                pigZombie.setLaneLocation(McdPigZombie.blueCenterLaneLocation);
                McDotaMain.insentients.add(pigZombie);
            } else {
                Bukkit.getLogger().warning("spawned pig zombie is null (chunk not loaded?)");
            }
        }

        for (int i = 0; i < WAVES_SIZE; i++) {
            location = new Location(world, McdPigZombie.blueSpawnLocation.x,
                    McdPigZombie.blueSpawnLocation.y, McdPigZombie.blueSpawnLocation.z + i);
            pigZombie = CustomEntityType.pigZombieType.spawn(location);
            if (pigZombie != null) {
                pigZombie.setSide(Side.BLUE);
                pigZombie.setLaneLocation(McdPigZombie.blueRightLaneLocation);
                McDotaMain.insentients.add(pigZombie);
            } else {
                Bukkit.getLogger().warning("spawned pig zombie is null (chunk not loaded?)");
            }
        }

        assert world != null;
        world.getLivingEntities().stream().filter(
                livingEntity -> Objects.equals(livingEntity.getType().getEntityClass(), org.bukkit.entity.Zombie.class))
                .forEach(livingEntity -> {
                    Objects.requireNonNull(livingEntity.getEquipment()).setHelmet(new ItemStack(Material.GOLDEN_HELMET));
                    Objects.requireNonNull(livingEntity.getEquipment()).setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
                });

    }

    private void spawnYellowPigZombies(World world) {
        Location location;
        McdPigZombie pigZombie;

        for (int i = 0; i < WAVES_SIZE; i++) {
            location = new Location(world, McdPigZombie.yellowSpawnLocation.x + i,
                    McdPigZombie.yellowSpawnLocation.y, McdPigZombie.yellowSpawnLocation.z);
            pigZombie = CustomEntityType.pigZombieType.spawn(location);
            if (pigZombie != null) {
                pigZombie.setSide(Side.YELLOW);
                pigZombie.setLaneLocation(McdPigZombie.yellowLeftLaneLocation);
                McDotaMain.insentients.add(pigZombie);
            } else {
                Bukkit.getLogger().warning("spawned pig zombie is null (chunk not loaded?)");
            }
        }

        for (int i = 0; i < WAVES_SIZE; i++) {
            location = new Location(world, McdPigZombie.yellowSpawnLocation.x + i,
                    McdPigZombie.yellowSpawnLocation.y, McdPigZombie.yellowSpawnLocation.z);
            pigZombie = CustomEntityType.pigZombieType.spawn(location);
            if (pigZombie != null) {
                pigZombie.setSide(Side.YELLOW);
                pigZombie.setLaneLocation(McdPigZombie.yellowCenterLaneLocation);
                McDotaMain.insentients.add(pigZombie);
            } else {
                Bukkit.getLogger().warning("spawned pig zombie is null (chunk not loaded?)");
            }
        }

        for (int i = 0; i < WAVES_SIZE; i++) {
            location = new Location(world, McdPigZombie.yellowSpawnLocation.x + i,
                    McdPigZombie.yellowSpawnLocation.y, McdPigZombie.yellowSpawnLocation.z);
            pigZombie = CustomEntityType.pigZombieType.spawn(location);
            if (pigZombie != null) {
                pigZombie.setSide(Side.YELLOW);
                pigZombie.setLaneLocation(McdPigZombie.yellowRightLaneLocation);
                McDotaMain.insentients.add(pigZombie);
            } else {
                Bukkit.getLogger().warning("spawned pig zombie is null (chunk not loaded?)");
            }
        }

        assert world != null;
        world.getLivingEntities().stream().filter(
                livingEntity -> Objects.equals(livingEntity.getType().getEntityClass(), org.bukkit.entity.PigZombie.class))
                .forEach(livingEntity -> {
                    Objects.requireNonNull(livingEntity.getEquipment()).setHelmet(new ItemStack(Material.IRON_HELMET));
                    Objects.requireNonNull(livingEntity.getEquipment()).setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                });
    }




}
