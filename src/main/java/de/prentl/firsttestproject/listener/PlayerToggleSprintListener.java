package de.prentl.firsttestproject.listener;

import de.prentl.firsttestproject.McdGame;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSprintEvent;

public class PlayerToggleSprintListener implements Listener {

    @EventHandler
    public void onSprint(PlayerToggleSprintEvent event) {
        if (McdGame.isStarted) {
            Player player = event.getPlayer();
            player.sendMessage(ChatColor.RED + "Eine mächtige Magie bestraft in diesem Spiel das Sprinten!");
            ((CraftPlayer) player).getHandle().setOnFire(8);
        }
    }
}
