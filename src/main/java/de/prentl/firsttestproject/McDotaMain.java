package de.prentl.firsttestproject;

import de.prentl.firsttestproject.commands.*;
import de.prentl.firsttestproject.listener.ChatListener;
import de.prentl.firsttestproject.listener.JoinListener;
import de.prentl.firsttestproject.listener.QuitListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Witch;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class McDotaMain extends JavaPlugin {

    public static Villager villager1 = null;
    public static Witch witch = null;

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
        PluginCommand dateCommand = getCommand("date");
        DateCommand newDateCommand = new DateCommand();
        dateCommand.setExecutor(newDateCommand);

        PluginCommand blueplayCommand = getCommand("blueplay");
        BlueplayCommand newBlueplayCommand = new BlueplayCommand();
        blueplayCommand.setExecutor(newBlueplayCommand);

        PluginCommand yellowplayCommand = getCommand("yellowplay");
        YellowPlayCommand newYellowplayCommand = new YellowPlayCommand();
        yellowplayCommand.setExecutor(newYellowplayCommand);

        PluginCommand lobbyCommand = getCommand("lobby");
        LobbyCommand newLobbyCommand = new LobbyCommand();
        lobbyCommand.setExecutor(newLobbyCommand);

        registerCommand("mirror", new MirrorCommandExecutor());
        registerCommand("villagers", new SpawnVillagersExecutor());
        registerCommand("witch", new SpawnWitchExecutor());
        registerCommand("move", new MoveWitchExecutor());
        registerCommand("ai", new WitchAiExecutor());
        registerCommand("attack", new WitchAttackExecutor());
        registerCommand("teleport", new TeleportWitchExecutor());

    }

    private void registerCommand(String commandString, CommandExecutor commandExecutor) {
        PluginCommand command = getCommand(commandString);
        command.setExecutor(commandExecutor);
    }
}
