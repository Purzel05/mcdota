package de.prentl.firsttestproject.tasks;

import de.prentl.firsttestproject.McdGame;
import de.prentl.firsttestproject.entities.*;

public class SendWavesTask implements Runnable {

    @Override
    public void run() {
        if (McdGame.isStarted) {
            EntityUtils.spawnSkeletons();
            EntityUtils.spawnPigZombies();
            EntityUtils.equipPigZombiesAndSkeletons();
        }
    }
}
