package de.prentl.firsttestproject;

import de.prentl.firsttestproject.commands.BlueplayCommand;
import de.prentl.firsttestproject.commands.DateCommand;
import de.prentl.firsttestproject.commands.YellowPlayCommand;
import de.prentl.firsttestproject.listener.JoinListener;
import de.prentl.firsttestproject.listener.QuitListener;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

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
    }
    //Hallo
}
