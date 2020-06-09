package de.prentl.firsttestproject.examples;

public class CodeSnippets {

    public static void codeSnippets() {

        //e.getEntity().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(8.0D);

        //Server server = Bukkit.getServer();
        //CraftServer craftServer = (CraftServer)server;
        //CraftWorld craftWorld = (CraftWorld) world;
        //net.minecraft.server.v1_15_R1.World world1 = craftWorld.getHandle();
        //MinecraftServer server = ((CraftServer)Bukkit.getServer()).getServer();
        //WorldServer world = ((CraftWorld)Bukkit.getServer().getWorlds().get(0)).getHandle();

        //World world = Bukkit.getWorld("world");
        //Location location = new Location(world, 8, 4, 10);
        //Villager villager = (Villager) world.spawnEntity(location, EntityType.VILLAGER);
        //villager.setAI(false);
        //location = new Location(world, 8, 4, 20);
        //villager.teleport(location);
        //return false;

        /*entityCreature.goalSelector.a(1, new PathfinderGoalFloat(entityCreature));
        entityCreature.goalSelector.a(2, new PathfinderGoalLookAtPlayer(entityCreature, EntityHuman.class, 8.0F));
        entityCreature.goalSelector.a(2, new PathfinderGoalRandomLookaround(entityCreature));
        //entityCreature.targetSelector.a(1, new PathfinderGoalHurtByTarget(entityCreature, true, new Class[0]));
        //entityCreature.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget(entityCreature, EntityHuman.class, true));
        entityCreature.goalSelector.a(2, new PathfinderGoalMoveToLocation(entityCreature, location, 1.0));
        //entityCreature.setEquipment(0, CraftItemStack.asNMSCopy(new ItemStack(org.bukkit.Material.DIAMOND_SWORD)));
        PathfinderGoalFloat -> 0 (always 0 in vanilla entities)
        PathfinderGoalLookAtPlayer -> 1
        PathfinderGoalRandomLookaround -> 1
        PathfinderGoalHurtByTarget -> 2
        PathfinderGoalNearestAttackableTarget -> 2
        PathfinderGoalMoveToLocation -> 3*/

        //this.goalSelector.a(4, new EntityZombie.a(this, 1.0D, 3));
        //this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        //this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        //this.goalSelector.a(2, new PathfinderGoalZombieAttack(this, 1.0D, false));
        //this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 1.0D, true, 4, this::ey));
        //this.goalSelector.a(7, new CustomPathfinderGoalCopied(this, 1.0D));
        //this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[0])).a(new Class[]{EntityPigZombie.class}));
        //this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
        //this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityVillagerAbstract.class, false));
        //this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityIronGolem.class, true));
        //this.targetSelector.a(5, new PathfinderGoalNearestAttackableTarget(this, EntityTurtle.class, 10, true, false, EntityTurtle.bw));

        //setNoAI(false);
        //this.setGoalTarget(McDotaMain.villager, EntityTargetEvent.TargetReason.CUSTOM, false);
        //org.bukkit.World world = Bukkit.getWorld("world");
        //Location location = new Location(world, 8, 4, 20);
        //this.goalSelector.a(1, new PathfinderGoalMoveToLocation(1.0D, this, location));
        //this.targetSelector.a(new PathfinderGoalMoveToLocation(1.0D, this, location));
        //this.goalSelector.a(0, new PathfinderGoalFloat(this));
        //this.goalSelector.a(4, new EntityZombie.a(this, 1.0D, 3));
        //this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        //this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        //this.goalSelector.a(2, new PathfinderGoalZombieAttack(this, 1.0D, false));
        //this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 1.0D, true, 4, this::ey));
        //this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D));
        //this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[0])).a(new Class[]{EntityPigZombie.class}));
        //this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
        //this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityVillagerAbstract.class, false));
        //this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityIronGolem.class, true));
        //this.targetSelector.a(5, new PathfinderGoalNearestAttackableTarget(this, EntityTurtle.class, 10, true, false, EntityTurtle.bw));
    }
}
