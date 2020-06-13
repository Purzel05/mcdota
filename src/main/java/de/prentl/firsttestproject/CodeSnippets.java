package de.prentl.firsttestproject;

import net.minecraft.server.v1_15_R1.EntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.craftbukkit.v1_15_R1.CraftServer;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_15_R1.attribute.CraftAttributeMap;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftLivingEntity;

public class CodeSnippets {

    public static void relationshipBetweenBukkitAndCraftAndNMS() {
        // Bukkit classes
        Server bukkitServer = Bukkit.getServer();
        World bukkitWorld = bukkitServer.getWorld(McdPlugin.MAP_WORLD);
        bukkitWorld.getTime();

        // CraftBukkit classes - these just need to be casted from the Bukkit classes
        CraftServer craftServer = (CraftServer) bukkitServer;
        CraftWorld craftWorld = (CraftWorld) bukkitWorld;

        // NMS classes - these you get from the CraftBukktit classes via get...()?
        net.minecraft.server.v1_15_R1.World nmsWorld = craftWorld.getHandle();
        net.minecraft.server.v1_15_R1.MinecraftServer nmsServer = craftServer.getServer();
        net.minecraft.server.v1_15_R1.WorldServer nmsWorldServer = craftWorld.getHandle();
    }

    public static void codeSnippets() {
        //CraftLivingEntity craftLivingEntity = (CraftLivingEntity)livingEntity;
        //EntityLiving entityLiving1 = craftLivingEntity.getHandle();
        //CraftAttributeMap craftAttributeMap = entityLiving1.craftAttributes;
        //AttributeInstance attributeInstance = craftAttributeMap.getAttribute(Attribute.GENERIC_ATTACK_SPEED);

        //e.getEntity().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(8.0D);

        //World world = Bukkit.getWorld("world");
        //Location location = new Location(world, 8, 4, 10);
        //Villager villager = (Villager) world.spawnEntity(location, EntityType.VILLAGER);
        //location = new Location(world, 8, 4, 20);
        //villager.teleport(location);

        //entityCreature.setEquipment(0, CraftItemStack.asNMSCopy(new ItemStack(org.bukkit.Material.DIAMOND_SWORD)));

        /*PathfinderGoalFloat -> 0 (always 0 in vanilla entities)
        PathfinderGoalLookAtPlayer -> 1
        PathfinderGoalRandomLookaround -> 1
        PathfinderGoalHurtByTarget -> 2
        PathfinderGoalNearestAttackableTarget -> 2
        PathfinderGoalMoveToLocation -> 3*/
    }
}
