package de.prentl.firsttestproject.customentities;

import net.minecraft.server.v1_15_R1.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;

import java.util.EnumSet;

public class CustomPathfinderGoal extends PathfinderGoal {
    private double speed;

    private EntityInsentient entity;
    private Location location;
    private Navigation navigation;

    public CustomPathfinderGoal(double speed, EntityInsentient entity, Location location) {
        this.speed = speed;
        this.entity = entity;
        this.location = location;
        this.navigation =  new Navigation(entity, ((CraftWorld)location.getWorld()).getHandle());
    }

    @Override
    public void c() {
        PathEntity pathEntity = this.navigation.a(location.getX(), location.getY(), location.getZ(), 1);
        this.navigation.a(pathEntity, speed);
    }

    @Override
    public boolean a() {
        return true;
    }

    @Override
    public void e() {
        PathEntity pathEntity = this.navigation.a(location.getX(), location.getY(), location.getZ(), 1);
        this.navigation.a(pathEntity, speed);
    }
}
