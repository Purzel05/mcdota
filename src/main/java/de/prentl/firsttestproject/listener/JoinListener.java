package de.prentl.firsttestproject.listener;

import de.prentl.firsttestproject.McdGame;
import de.prentl.firsttestproject.McdMap;
import de.prentl.firsttestproject.McdPlayer;
import de.prentl.firsttestproject.McdPlugin;
import net.minecraft.server.v1_15_R1.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        //player.sendMessage(ChatColor.GREEN + "Willkommen auf MCDota!");
        //event.setJoinMessage(ChatColor.YELLOW + player.getName() + ChatColor.BLUE + " hat den Server betreten");

        McdMap.Side side;

        if (McdGame.bluePlayers.size() <= McdGame.yellowPlayers.size()) {
            side = McdMap.Side.BLUE;
            event.setJoinMessage(ChatColor.YELLOW + player.getName() + ChatColor.BLUE + " ist dem Team BLAU beigetreten.");
            McdGame.bluePlayers.add(player);
        } else {
            side = McdMap.Side.YELLOW;
            event.setJoinMessage(ChatColor.YELLOW + player.getName() + ChatColor.BLUE + " ist dem Team GELB beigetreten.");
            McdGame.yellowPlayers.add(player);
        }
        McdPlayer mcdPlayer = new McdPlayer(side);

        McdGame.mcdPlayerMap.put(player, mcdPlayer);

        Vec3D spawnVector = McdMap.getPlayerLocation(side);
        assert spawnVector != null;
        player.teleport(new Location(Bukkit.getWorld(McdPlugin.MAP_WORLD), spawnVector.x, spawnVector.y, spawnVector.z));
    }
}
