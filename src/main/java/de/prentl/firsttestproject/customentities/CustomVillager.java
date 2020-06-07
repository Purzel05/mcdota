package de.prentl.firsttestproject.customentities;

import net.minecraft.server.v1_15_R1.*;

public class CustomVillager extends EntityVillager {

    public CustomVillager(World world) {
        super(EntityTypes.VILLAGER, world);
    }

    public CustomVillager(EntityTypes<CustomVillager> customVillagerEntityTypes, World world) {
        this(world);
    }

    @Override
    protected void initPathfinder() {
        //super.initPathfinder();

        setNoAI(true);
    }
}
