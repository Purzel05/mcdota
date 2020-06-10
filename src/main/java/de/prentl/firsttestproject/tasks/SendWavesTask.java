package de.prentl.firsttestproject.tasks;

import de.prentl.firsttestproject.McDotaMain;
import de.prentl.firsttestproject.entities.CustomEntityType;
import de.prentl.firsttestproject.entities.pigs.McdPigZombie;
import de.prentl.firsttestproject.entities.skeletons.McdSkeleton;
import de.prentl.firsttestproject.entities.zombies.BlueCenterZombie;
import de.prentl.firsttestproject.entities.zombies.BlueLeftZombie;
import de.prentl.firsttestproject.entities.zombies.BlueRightZombie;
import de.prentl.firsttestproject.entities.zombies.McdZombie;
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
        McdZombie zombie;

        if (!skeletonsSpawned) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Location location = new Location(world, McdSkeleton.blueCenterLocation.x + i,
                            McdSkeleton.blueCenterLocation.y, McdSkeleton.blueCenterLocation.z + j);
                    McdSkeleton skeleton = CustomEntityType.skeletonType.spawn(location);
                    skeleton.setLocation(new Vec3D(location.getX(), location.getY(), location.getZ()));
                    McDotaMain.insentients.add(skeleton);
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

        for (int i = 0; i < WAVES_SIZE; i++) {
            zombie = CustomEntityType.blueLeftZombieType.spawn(new Location(world, BlueLeftZombie.spawnLoc.x + i,
                    BlueLeftZombie.spawnLoc.y, BlueLeftZombie.spawnLoc.z));
            McDotaMain.insentients.add(zombie);
        }

        for (int i = 0; i < WAVES_SIZE; i++) {
            zombie = CustomEntityType.blueCenterZombieType.spawn(new Location(world, BlueCenterZombie.spawnLoc.x + i,
                    BlueCenterZombie.spawnLoc.y, BlueCenterZombie.spawnLoc.z + i));
            McDotaMain.insentients.add(zombie);
        }

        for (int i = 0; i < WAVES_SIZE; i++) {
            zombie = CustomEntityType.blueRightZombieType.spawn(new Location(world, BlueRightZombie.spawnLoc.x,
                    BlueRightZombie.spawnLoc.y, BlueRightZombie.spawnLoc.z + i));
            McDotaMain.insentients.add(zombie);
        }

        McdPigZombie pigZombie;

        for (int i = 0; i < WAVES_SIZE; i++) {
            pigZombie = CustomEntityType.pigZombieType.spawn(new Location(world, McdPigZombie.yellowSpawnLocation.x + i,
                    McdPigZombie.yellowSpawnLocation.y, McdPigZombie.yellowSpawnLocation.z));
            pigZombie.setLaneLocation(McdPigZombie.yellowLeftLaneLocation);
            McDotaMain.insentients.add(pigZombie);
        }

        for (int i = 0; i < WAVES_SIZE; i++) {
            pigZombie = CustomEntityType.pigZombieType.spawn(new Location(world, McdPigZombie.yellowSpawnLocation.x + i,
                    McdPigZombie.yellowSpawnLocation.y, McdPigZombie.yellowSpawnLocation.z));
            pigZombie.setLaneLocation(McdPigZombie.yellowCenterLaneLocation);
            McDotaMain.insentients.add(pigZombie);
        }

        for (int i = 0; i < WAVES_SIZE; i++) {
            pigZombie = CustomEntityType.pigZombieType.spawn(new Location(world, McdPigZombie.yellowSpawnLocation.x + i,
                    McdPigZombie.yellowSpawnLocation.y, McdPigZombie.yellowSpawnLocation.z));
            pigZombie.setLaneLocation(McdPigZombie.yellowRightLaneLocation);
            McDotaMain.insentients.add(pigZombie);
        }

        assert world != null;
        world.getLivingEntities().stream().filter(
                livingEntity -> Objects.equals(livingEntity.getType().getEntityClass(), org.bukkit.entity.Zombie.class))
                .forEach(livingEntity -> {
                    Objects.requireNonNull(livingEntity.getEquipment()).setHelmet(new ItemStack(Material.GOLDEN_HELMET));
                    Objects.requireNonNull(livingEntity.getEquipment()).setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
                });

        world.getLivingEntities().stream().filter(
                livingEntity -> Objects.equals(livingEntity.getType().getEntityClass(), org.bukkit.entity.PigZombie.class))
                .forEach(livingEntity -> {
                    Objects.requireNonNull(livingEntity.getEquipment()).setHelmet(new ItemStack(Material.IRON_HELMET));
                    Objects.requireNonNull(livingEntity.getEquipment()).setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                });
    }
}
