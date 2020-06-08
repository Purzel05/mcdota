package de.prentl.firsttestproject;

import de.prentl.firsttestproject.commands.*;
import de.prentl.firsttestproject.customentities.*;
import de.prentl.firsttestproject.listener.ChatListener;
import de.prentl.firsttestproject.listener.JoinListener;
import de.prentl.firsttestproject.listener.QuitListener;
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

    public static List<CustomZombie> zombies = new ArrayList<>();

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

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                World world = Bukkit.getWorld(MAP_WORLD);
                world.setTime(13000);
            }
        }, 10L, 1000);

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (CustomZombie zombie: zombies) {
                    System.out.println("number of zombies: " + zombies.size());
                    zombie.repeatingTask();
                }
            }
        }, 10L, 1000);
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
                ("blueleftzombie", BlueLeftZombie.class, EntityTypes.ZOMBIE, BlueLeftZombie::new);
        CustomEntityType.blueLeftZombieType.register();

        CustomEntityType.blueCenterZombieType = new CustomEntityType <BlueCenterZombie>
                ("bluecenterzombie", BlueCenterZombie.class, EntityTypes.ZOMBIE, BlueCenterZombie::new);
        CustomEntityType.blueCenterZombieType.register();

        CustomEntityType.blueRightZombieType = new CustomEntityType <BlueRightZombie>
                ("bluerightzombie", BlueRightZombie.class, EntityTypes.ZOMBIE, BlueRightZombie::new);
        CustomEntityType.blueRightZombieType.register();
    }

    private void setTimeToDusk() {
        System.out.println("setting time to dusk ...");
        World world = Bukkit.getWorld(MAP_WORLD);
        long dusk = getConfig().getLong("worlds." + MAP_WORLD + ".night");
        assert world != null;
        world.setTime(13000);

    }
}
