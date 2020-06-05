package de.prentl.firsttestproject;

import de.prentl.firsttestproject.commands.*;
import de.prentl.firsttestproject.customentities.CustomEntityType;
import de.prentl.firsttestproject.customentities.CustomZombie;
import de.prentl.firsttestproject.listener.ChatListener;
import de.prentl.firsttestproject.listener.JoinListener;
import de.prentl.firsttestproject.listener.QuitListener;
import net.minecraft.server.v1_15_R1.EntityTypes;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class McDotaMain extends JavaPlugin {

    public static CustomEntityType<CustomZombie> zombie = null;

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
        McDotaMain.zombie = new CustomEntityType <CustomZombie>
                ("customzombie", CustomZombie.class, EntityTypes.ZOMBIE, CustomZombie::new);
        McDotaMain.zombie.register();
    }
}
