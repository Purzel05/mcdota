package de.prentl.firsttestproject.customentities;

import de.prentl.firsttestproject.McDotaMain;
import net.minecraft.server.v1_15_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.entity.EntityTargetEvent;

public class CustomZombie extends EntityZombie {

    private String side;
    private String lane;

    public CustomZombie(World world) {
        super(EntityTypes.ZOMBIE, world);
    }

    public CustomZombie(EntityTypes<CustomZombie> customZombieEntityTypes, World world) {
        this(world);
    }

    @Override
    protected void initPathfinder() {
        super.initPathfinder();

        Location location = null;   // TODO !!!!!!!!!

        this.goalSelector.a(7, new CustomPathfinderGoalCopied(this, location));
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }
}
