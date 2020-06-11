package de.prentl.firsttestproject.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener implements Listener {
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        Bukkit.getLogger().info("preventing weather change ...");
        event.setCancelled(true);
        event.getWorld().setStorm(false);
    }
}

