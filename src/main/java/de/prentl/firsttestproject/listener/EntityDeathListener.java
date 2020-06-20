package de.prentl.firsttestproject.listener;

import de.prentl.firsttestproject.McdGame;
import de.prentl.firsttestproject.McdPlayer;
import de.prentl.firsttestproject.entities.McdPigZombie;
import net.minecraft.server.v1_15_R1.EntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EntityDeathListener implements Listener {
    private static final int maxAmount = 10;

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        LivingEntity bukkitEntity = event.getEntity();
        EntityLiving mcEntity = ((CraftLivingEntity)bukkitEntity).getHandle();

        Bukkit.getLogger().fine("preventing drops from entities ...");
        event.getDrops().clear();

        if (mcEntity instanceof McdPigZombie) {
            McdPigZombie mcdKilledPigZombie = (McdPigZombie) mcEntity;
            Player bukkitKiller = event.getEntity().getKiller();   // KILLER doesn't work, see EntityDamageListener
            if (bukkitKiller != null) {
                McdPlayer mcdKiller = McdGame.mcdPlayerMap.get(bukkitKiller);
                if (!mcdKiller.getSide().equals(mcdKilledPigZombie.getSide())) {
                    Inventory inventory = bukkitKiller.getInventory();
                    if (increase(inventory, Material.GOLD_NUGGET, 1)) {
                        increase(inventory, Material.GOLD_INGOT, 1);
                    }
                }
            }
        }
    }

    private boolean increase(Inventory inventory, Material material, int amount) {
        if (inventory.contains(material)) {
            int slot = inventory.first(material);
            ItemStack stack = inventory.getItem(slot);
            int stackAmount = stack.getAmount();
            if (stackAmount <= (maxAmount - amount)) {
                stack.setAmount(stackAmount + amount);
                return false;
            } else {
                stack.setAmount(amount - (maxAmount - stackAmount));
                return true;
            }
        } else {
            inventory.addItem(new ItemStack(material, amount));
            return false;
        }
    }
}
