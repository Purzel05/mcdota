package de.prentl.firsttestproject;

import de.prentl.firsttestproject.commands.*;
import de.prentl.firsttestproject.entities.*;
import de.prentl.firsttestproject.entities.McdPigZombie;
import de.prentl.firsttestproject.entities.McdSkeleton;
import de.prentl.firsttestproject.listener.ChatListener;
import de.prentl.firsttestproject.listener.JoinListener;
import de.prentl.firsttestproject.listener.WeatherChangeListener;
import de.prentl.firsttestproject.listener.QuitListener;
import de.prentl.firsttestproject.tasks.SendWavesTask;
import de.prentl.firsttestproject.tasks.UpdateGoalsAndTargetsTask;
import net.minecraft.server.v1_15_R1.EntityInsentient;
import net.minecraft.server.v1_15_R1.EntityTypes;
import net.minecraft.server.v1_15_R1.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class McdPlugin extends JavaPlugin {

    public static final String MAP_WORLD = "world";

    public static final List<McdMap.Side> sides = new ArrayList<>();
    public static final List<McdMap.Lane> lanes = new ArrayList<>();

    public static List<EntityInsentient> entitiesInsentient = new ArrayList<>();

    @Override
    public void onLoad() {
        super.onLoad();
        entityRegistration();
    }

    @Override
    public void onEnable() {
        sides.add(McdMap.Side.BLUE);
        sides.add(McdMap.Side.YELLOW);
        lanes.add(McdMap.Lane.LEFT);
        lanes.add(McdMap.Lane.CENTER);
        lanes.add(McdMap.Lane.RIGHT);

        EntityUtils.removeLivingEntities();
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
        pluginManager.registerEvents(new WeatherChangeListener(), this);
    }

    private void commandRegistration() {
        registerCommand("date", new DateCommandExecutor());
        registerCommand("blueplay", new BluePlayCommandExecutor());
        registerCommand("yellowplay", new YellowPlayCommandExecutor());
        registerCommand("lobby", new LobbyCommandExecutor());
        registerCommand("mirr", new MirrorCommandExecutor());
        registerCommand("wave", new SpawnPigZombieWave());
        registerCommand("skels", new SpawnSkeletonsExecutor());
        registerCommand("killall", new RemoveAllExecutor());
        registerCommand("world", new WorldCommandExecutor());
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