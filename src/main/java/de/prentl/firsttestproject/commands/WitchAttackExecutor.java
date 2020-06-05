package de.prentl.firsttestproject.commands;

import de.prentl.firsttestproject.McDotaMain;
import net.minecraft.server.v1_15_R1.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftWitch;
import org.bukkit.entity.Creature;

public class WitchAttackExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Creature cs = ((Creature)McDotaMain.witch);
        EntityCreature entityCreature = ((CraftWitch)cs).getHandle();

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

        entityCreature.getNavigation().a(40, 4, 40, 3);

        return false;
    }
}
