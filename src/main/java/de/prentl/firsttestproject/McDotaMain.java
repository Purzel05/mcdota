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
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class McDotaMain extends JavaPlugin {

    public static final String MAP_WORLD = "world";

    public static List<EntityInsentient> zombies = new ArrayList<>();

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

        /*getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                World world = Bukkit.getWorld(MAP_WORLD);
                world.setTime(13000);
            }
        }, 10L, 1000);*/

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (EntityInsentient entity: zombies) {
                    if (entity.isAlive()) {
                        if (entity instanceof McdZombie) {
                            ((McdZombie)entity).updateGoalsAndTargets();
                        } else {
                            ((McdPigZombie)entity).updateGoalsAndTargets();
                        }

                    } else {
                        zombies.remove(entity);
                        break;
                    }
                }
            }
        }, 1L, 50);
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

    private void setTimeToDusk() {
        System.out.println("setting time to dusk ...");
        World world = Bukkit.getWorld(MAP_WORLD);
        long dusk = getConfig().getLong("worlds." + MAP_WORLD + ".night");
        assert world != null;
        world.setTime(13000);

    }
}
