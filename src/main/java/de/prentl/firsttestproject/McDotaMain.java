package de.prentl.firsttestproject;

import de.prentl.firsttestproject.commands.*;
import de.prentl.firsttestproject.customentities.CustomEntityType;
import de.prentl.firsttestproject.customentities.CustomVillager;
import de.prentl.firsttestproject.customentities.CustomZombie;
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

    public static final Double[] spawnLocBlueLeft = new Double[] {12.0D, 4.0D, 8.0D};
    public static final Double[] spawnLocBlueCenter = new Double[] {12.0D, 4.0D, 12.0D};
    public static final Double[] spawnLocBlueRight = new Double[] {8.0D, 4.0D, 12.0D};

    public static CustomEntityType<CustomZombie> zombieType;
    public static CustomEntityType<CustomVillager> villagerType;

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
                setTimeToDusk();
            }
        }, 60L, 1000);
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
        registerCommand("date", new DateCommand());
        registerCommand("blueplay", new BlueplayCommand());
        registerCommand("yellowplay", new YellowPlayCommand());
        registerCommand("lobby", new LobbyCommand());
        registerCommand("mirror", new MirrorCommandExecutor());
        registerCommand("zombie", new SpawnZombieExecutor());
    }

    private void registerCommand(String commandString, CommandExecutor commandExecutor) {
        PluginCommand command = getCommand(commandString);
        command.setExecutor(commandExecutor);
    }

    private void entityRegistration() {
        McDotaMain.zombieType = new CustomEntityType <CustomZombie>
                ("customzombie", CustomZombie.class, EntityTypes.ZOMBIE, CustomZombie::new);
        McDotaMain.zombieType.register();


        McDotaMain.villagerType = new CustomEntityType <CustomVillager>
                ("customvillager", CustomVillager.class, EntityTypes.VILLAGER, CustomVillager::new);
        McDotaMain.villagerType.register();
    }

    private void setTimeToDusk() {
        System.out.println("setting time to dusk ...");
        World world = Bukkit.getWorld(MAP_WORLD);
        long dusk = getConfig().getLong("worlds." + MAP_WORLD + ".dusk");
        assert world != null;
        world.setTime(dusk);

    }
}
