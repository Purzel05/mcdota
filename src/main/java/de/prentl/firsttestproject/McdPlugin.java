package de.prentl.firsttestproject;

import de.prentl.firsttestproject.commands.*;
import de.prentl.firsttestproject.entities.*;
import de.prentl.firsttestproject.entities.McdPigZombie;
import de.prentl.firsttestproject.entities.McdSkeleton;
import de.prentl.firsttestproject.listener.*;
import de.prentl.firsttestproject.tasks.SendWavesTask;
import de.prentl.firsttestproject.tasks.UpdateGoalsAndTargetsTask;
import net.minecraft.server.v1_15_R1.EntityInsentient;
import net.minecraft.server.v1_15_R1.EntityTypes;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

public final class McdPlugin extends JavaPlugin {

    public static McdPlugin mcdPlugin;

    public static final String MAP_WORLD = "world";

    public static final List<McdMap.Side> sides = new ArrayList<>();
    public static final List<McdMap.Lane> lanes = new ArrayList<>();
    public static final List<McdMap.TowerLocation> towerLocations = new ArrayList<>();

    public static List<EntityInsentient> entitiesInsentient = new ArrayList<>();

    @Override
    public void onLoad() {
        super.onLoad();
        entityRegistration();
        mcdPlugin = this;
    }

    @Override
    public void onEnable() {
        Bukkit.getLogger().setLevel(Level.INFO);

        sides.add(McdMap.Side.BLUE);
        sides.add(McdMap.Side.YELLOW);
        lanes.add(McdMap.Lane.LEFT);
        lanes.add(McdMap.Lane.CENTER);
        lanes.add(McdMap.Lane.RIGHT);
        towerLocations.add(McdMap.TowerLocation.BASE);
        towerLocations.add(McdMap.TowerLocation.MID);
        towerLocations.add(McdMap.TowerLocation.RIVER);

        setChunksToForceLoaded();
        listenerRegistration();
        commandRegistration();
        repeatingTasksRegistration();
        EntityUtils.removeLivingEntities();
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().fine("Plugin wird deaktiviert.");
    }

    private void setChunksToForceLoaded() {
        for (int i = -2; i < 10; i++) {
            for (int j = -2; j < 10; j++) {
                Objects.requireNonNull(Bukkit.getWorld(MAP_WORLD)).setChunkForceLoaded(i, j, true);
            }
        }
    }

    private void listenerRegistration() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new QuitListener(), this);
        pluginManager.registerEvents(new ChatListener(), this);
        pluginManager.registerEvents(new WeatherChangeListener(), this);
        pluginManager.registerEvents(new PlayerDeathListener(), this);
        pluginManager.registerEvents(new PlayerRespawnListener(), this);
        pluginManager.registerEvents(new EntityDeathListener(), this);
        pluginManager.registerEvents(new EntityDamageListener(), this);
        pluginManager.registerEvents(new PlayerToggleSprintListener(), this);
    }

    private void commandRegistration() {
        registerCommand("date", new DateCommandExecutor());
        registerCommand("blueplay", new BluePlayCommandExecutor());
        registerCommand("yellowplay", new YellowPlayCommandExecutor());
        registerCommand("lobby", new LobbyCommandExecutor());
        registerCommand("mirr", new MirrorCommandExecutor());
        registerCommand("wave", new SpawnPigZombieWaveExecutor());
        registerCommand("rme", new RemoveAllExecutorExecutor());
        registerCommand("world", new WorldCommandExecutor());
        registerCommand("sga", new StartGameExecutor());
        registerCommand("logl", new SetLogLevelExecutor());

    }

    private void registerCommand(String commandString, CommandExecutor commandExecutor) {
        PluginCommand command = getCommand(commandString);
        command.setExecutor(commandExecutor);
    }

    private void entityRegistration() {
        CustomEntityType.pigZombieType = new CustomEntityType <McdPigZombie>
                ("mcd_pig_zombie", McdPigZombie.class, EntityTypes.ZOMBIE_PIGMAN, McdPigZombie::new);
        CustomEntityType.pigZombieType.register();

        CustomEntityType.skeletonType = new CustomEntityType <McdSkeleton>
                ("mcd_skeleton", McdSkeleton.class, EntityTypes.SKELETON, McdSkeleton::new);
        CustomEntityType.skeletonType.register();
    }

    private void repeatingTasksRegistration() {
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new UpdateGoalsAndTargetsTask(entitiesInsentient), 2L, 20);
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new SendWavesTask(), 1L, 1000);
    }
}
