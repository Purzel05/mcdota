package de.prentl.firsttestproject;

import de.prentl.firsttestproject.commands.*;
import de.prentl.firsttestproject.entities.*;
import de.prentl.firsttestproject.entities.pigs.McdPigZombie;
import de.prentl.firsttestproject.entities.pigs.YellowCenterPigZombie;
import de.prentl.firsttestproject.entities.pigs.YellowLeftPigZombie;
import de.prentl.firsttestproject.entities.pigs.YellowRightPigZombie;
import de.prentl.firsttestproject.entities.zombies.*;
import de.prentl.firsttestproject.listener.ChatListener;
import de.prentl.firsttestproject.listener.JoinListener;
import de.prentl.firsttestproject.listener.QuitListener;
import net.minecraft.server.v1_15_R1.EntityInsentient;
import net.minecraft.server.v1_15_R1.EntityTypes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class McDotaMain extends JavaPlugin {

    public static final String MAP_WORLD = "world";
    public static final int WAVES_SIZE = 4;

    public static List<EntityInsentient> waveEntities = new ArrayList<>();

    @Override
    public void onLoad() {
        super.onLoad();
        entityRegistration();
    }

    @Override
    public void onEnable() {
        Bukkit.getLogger().fine("Plugin wird aktiviert.");
        listenerRegistration();
        commandRegistration();
        repeatingTasksRegistration();
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().fine("Plugin wird deaktiviert.");
    }

    private void listenerRegistration() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new QuitListener(), this);
        pluginManager.registerEvents(new ChatListener(), this);
    }

    private void commandRegistration() {
        registerCommand("date", new DateCommandExecutor());
        registerCommand("blueplay", new BluePlayCommandExecutor());
        registerCommand("yellowplay", new YellowPlayCommandExecutor());
        registerCommand("lobby", new LobbyCommandExecutor());
        registerCommand("mirror", new MirrorCommandExecutor());
        registerCommand("zombie", new SpawnZombieExecutor());
        registerCommand("world", new WorldCommandExecutor());
    }

    private void registerCommand(String commandString, CommandExecutor commandExecutor) {
        PluginCommand command = getCommand(commandString);
        command.setExecutor(commandExecutor);
    }

    private void entityRegistration() {
        CustomEntityType.blueLeftZombieType = new CustomEntityType <BlueLeftZombie>
                ("blueleft", BlueLeftZombie.class, EntityTypes.ZOMBIE, BlueLeftZombie::new);
        CustomEntityType.blueLeftZombieType.register();

        CustomEntityType.blueCenterZombieType = new CustomEntityType <BlueCenterZombie>
                ("bluecenter", BlueCenterZombie.class, EntityTypes.ZOMBIE, BlueCenterZombie::new);
        CustomEntityType.blueCenterZombieType.register();

        CustomEntityType.blueRightZombieType = new CustomEntityType <BlueRightZombie>
                ("blueright", BlueRightZombie.class, EntityTypes.ZOMBIE, BlueRightZombie::new);
        CustomEntityType.blueRightZombieType.register();

        CustomEntityType.yellowLeftPigZombieType = new CustomEntityType <YellowLeftPigZombie>
                ("yellowleft", YellowLeftPigZombie.class, EntityTypes.ZOMBIE_PIGMAN, YellowLeftPigZombie::new);
        CustomEntityType.yellowLeftPigZombieType.register();

        CustomEntityType.yellowCenterPigZombieType = new CustomEntityType <YellowCenterPigZombie>
                ("yellowcenter", YellowCenterPigZombie.class, EntityTypes.ZOMBIE_PIGMAN, YellowCenterPigZombie::new);
        CustomEntityType.yellowCenterPigZombieType.register();

        CustomEntityType.yellowRightPigZombieType = new CustomEntityType <YellowRightPigZombie>
                ("yellowright", YellowRightPigZombie.class, EntityTypes.ZOMBIE_PIGMAN, YellowRightPigZombie::new);
        CustomEntityType.yellowRightPigZombieType.register();
    }

    private void repeatingTasksRegistration() {
        registerUpdateGoalsAndTargetsTask();
        registerSendWavesTask();
    }

    private void registerUpdateGoalsAndTargetsTask() {
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (EntityInsentient entity: waveEntities) {
                    if (entity.isAlive()) {
                        if (entity instanceof McdZombie) {
                            ((McdZombie)entity).updateGoalsAndTargets();
                        } else {
                            ((McdPigZombie)entity).updateGoalsAndTargets();
                        }

                    } else {
                        waveEntities.remove(entity);
                        break;
                    }
                }
            }
        }, 1L, 50);
    }

    private void registerSendWavesTask() {
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                System.out.println("spawning wave ...");
                World world = Bukkit.getWorld(McDotaMain.MAP_WORLD);
                McdZombie zombie;

                for (int i = 0; i < WAVES_SIZE; i++) {
                    zombie = CustomEntityType.blueLeftZombieType.spawn(new Location(world, BlueLeftZombie.spawnLoc.x + i,
                            BlueLeftZombie.spawnLoc.y, BlueLeftZombie.spawnLoc.z));
                    McDotaMain.waveEntities.add(zombie);
                }

                for (int i = 0; i < WAVES_SIZE; i++) {
                    zombie = CustomEntityType.blueCenterZombieType.spawn(new Location(world, BlueCenterZombie.spawnLoc.x + i,
                            BlueCenterZombie.spawnLoc.y, BlueCenterZombie.spawnLoc.z + i));
                    McDotaMain.waveEntities.add(zombie);
                }

                for (int i = 0; i < WAVES_SIZE; i++) {
                    zombie = CustomEntityType.blueRightZombieType.spawn(new Location(world, BlueRightZombie.spawnLoc.x,
                            BlueRightZombie.spawnLoc.y, BlueRightZombie.spawnLoc.z + i));
                    McDotaMain.waveEntities.add(zombie);
                }

                McdPigZombie pigZombie;

                for (int i = 0; i < WAVES_SIZE; i++) {
                    pigZombie = CustomEntityType.yellowLeftPigZombieType.spawn(new Location(world, YellowLeftPigZombie.spawnLoc.x + i,
                            YellowLeftPigZombie.spawnLoc.y, YellowLeftPigZombie.spawnLoc.z));
                    McDotaMain.waveEntities.add(pigZombie);
                }

                for (int i = 0; i < WAVES_SIZE; i++) {
                    pigZombie = CustomEntityType.yellowCenterPigZombieType.spawn(new Location(world, YellowCenterPigZombie.spawnLoc.x + i,
                            YellowCenterPigZombie.spawnLoc.y, YellowCenterPigZombie.spawnLoc.z));
                    McDotaMain.waveEntities.add(pigZombie);
                }

                for (int i = 0; i < WAVES_SIZE; i++) {
                    pigZombie = CustomEntityType.yellowRightPigZombieType.spawn(new Location(world, YellowRightPigZombie.spawnLoc.x + i,
                            YellowRightPigZombie.spawnLoc.y, YellowRightPigZombie.spawnLoc.z));
                    McDotaMain.waveEntities.add(pigZombie);
                }

                assert world != null;
                world.getLivingEntities().stream().filter(
                        livingEntity -> Objects.equals(livingEntity.getType().getEntityClass(), org.bukkit.entity.Zombie.class))
                        .forEach(livingEntity -> {
                            Objects.requireNonNull(livingEntity.getEquipment()).setHelmet(new ItemStack(Material.IRON_HELMET));
                        });
            }
        }, 1L, 5000);
    }
}
