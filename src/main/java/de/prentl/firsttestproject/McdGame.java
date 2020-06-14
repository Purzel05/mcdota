package de.prentl.firsttestproject;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class McdGame {
    public static boolean isStarted = false;
    public static List<Player> bluePlayers = new ArrayList<>();
    public static List<Player> yellowPlayers = new ArrayList<>();
    public static Map<Player, McdPlayer> mcdPlayerMap = new HashMap<>();
}
