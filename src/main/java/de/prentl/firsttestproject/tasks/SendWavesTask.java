package de.prentl.firsttestproject.tasks;

import de.prentl.firsttestproject.McdGame;
import de.prentl.firsttestproject.McdPlugin;
import de.prentl.firsttestproject.entities.*;
import org.bukkit.Bukkit;

import java.util.Objects;

public class SendWavesTask implements Runnable {

    @Override
    public void run() {
        if (McdGame.isStarted) {
            Objects.requireNonNull(Bukkit.getWorld(McdPlugin.MAP_WORLD)).getPlayers().forEach(p -> { p.sendMessage("wave is rolling!");});
            EntityUtils.spawnSkeletons();
            EntityUtils.spawnPigZombies();
            //EntityUtils.equipPigZombiesAndSkeletons();
        }
    }
}
