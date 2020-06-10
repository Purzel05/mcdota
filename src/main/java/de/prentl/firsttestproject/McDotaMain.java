package de.prentl.firsttestproject;

import de.prentl.firsttestproject.commands.*;
import de.prentl.firsttestproject.entities.*;
import de.prentl.firsttestproject.entities.pigs.YellowCenterPigZombie;
import de.prentl.firsttestproject.entities.pigs.YellowLeftPigZombie;
import de.prentl.firsttestproject.entities.pigs.YellowRightPigZombie;
import de.prentl.firsttestproject.entities.skeletons.McdSkeleton;
import de.prentl.firsttestproject.entities.zombies.*;
import de.prentl.firsttestproject.listener.ChatListener;
import de.prentl.firsttestproject.listener.JoinListener;
import de.prentl.firsttestproject.listener.WeatherChangeListener;
import de.prentl.firsttestproject.listener.QuitListener;
import de.prentl.firsttestproject.tasks.SendWavesTask;
import de.prentl.firsttestproject.tasks.UpdateGoalsAndTargetsTask;
import net.minecraft.server.v1_15_R1.EntityInsentient;
import net.minecraft.server.v1_15_R1.EntityTypes;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class McDotaMain extends JavaPlugin {

    public static final String MAP_WORLD = "world";
    public static List<EntityInsentient> insentients = new ArrayList<>();

    @Override
    public void onLoad() {
        super.onLoad();
        entityRegistration();
    }

    @Override
    public void onEnable() {
        removeLivingEntities();
        listenerRegistration();
        commandRegistration();
        repeatingTasksRegistration();
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().fine("Plugin wird deaktiviert.");
    }

    private void removeLivingEntities() {
        World world = Bukkit.getWorld(McDotaMain.MAP_WORLD);
        assert world != null;
        world.getLivingEntities().forEach(Entity::remove);
    }

    private void listenerRegistration() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new QuitListener(), this);
        pluginManager.registerEvents(new ChatListener(), this);
        pluginManager.registerEvents(new WeatherChangeListener(), this);
    }

    private void commandRegistration() {
        registerCommand("date", new DateCommandExecutor());
        registerCommand("blueplay", new BluePlayCommandExecutor());
        registerCommand("yellowplay", new YellowPlayCommandExecutor());
        registerCommand("lobby", new LobbyCommandExecutor());
        registerCommand("mirr", new MirrorCommandExecutor());
        registerCommand("zombie", new SpawnZombieExecutor());
        registerCommand("world", new WorldCommandExecutor());
    }

    private void registerCommand(String commandString, CommandExecutor commandExecutor) {
        PluginCommand command = getCommand(commandString);
        command.setExecutor(commandExecutor);
    }

    private void entityRegistration() {
        CustomEntityType.blueLeftZombieType = new CustomEntityType <BlueLeftZombie>
                ("blue_left_zombie", BlueLeftZombie.class, EntityTypes.ZOMBIE, BlueLeftZombie::new);
        CustomEntityType.blueLeftZombieType.register();

        CustomEntityType.blueCenterZombieType = new CustomEntityType <BlueCenterZombie>
                ("blue_center_zombie", BlueCenterZombie.class, EntityTypes.ZOMBIE, BlueCenterZombie::new);
        CustomEntityType.blueCenterZombieType.register();

        CustomEntityType.blueRightZombieType = new CustomEntityType <BlueRightZombie>
                ("blue_right_zombie", BlueRightZombie.class, EntityTypes.ZOMBIE, BlueRightZombie::new);
        CustomEntityType.blueRightZombieType.register();

        CustomEntityType.yellowLeftPigZombieType = new CustomEntityType <YellowLeftPigZombie>
                ("yellow_left_pig_zombie", YellowLeftPigZombie.class, EntityTypes.ZOMBIE_PIGMAN, YellowLeftPigZombie::new);
        CustomEntityType.yellowLeftPigZombieType.register();

        CustomEntityType.yellowCenterPigZombieType = new CustomEntityType <YellowCenterPigZombie>
                ("yellow_center_pig_zombie", YellowCenterPigZombie.class, EntityTypes.ZOMBIE_PIGMAN, YellowCenterPigZombie::new);
        CustomEntityType.yellowCenterPigZombieType.register();

        CustomEntityType.yellowRightPigZombieType = new CustomEntityType <YellowRightPigZombie>
                ("yellow_right_pig_zombie", YellowRightPigZombie.class, EntityTypes.ZOMBIE_PIGMAN, YellowRightPigZombie::new);
        CustomEntityType.yellowRightPigZombieType.register();

        CustomEntityType.skeletonType = new CustomEntityType <McdSkeleton>
                ("mcd_skeleton", McdSkeleton.class, EntityTypes.SKELETON, McdSkeleton::new);
        CustomEntityType.skeletonType.register();
    }

    private void repeatingTasksRegistration() {
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new UpdateGoalsAndTargetsTask(insentients), 2L, 20);
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new SendWavesTask(), 1L, 1000);
    }
}
