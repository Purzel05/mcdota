package de.prentl.firsttestproject.customentities;

import net.minecraft.server.v1_15_R1.EntityZombie;
import net.minecraft.server.v1_15_R1.PathfinderGoalSelector;
import net.minecraft.server.v1_15_R1.World;

import java.lang.reflect.Field;
import java.util.List;

public class CustomZombie extends EntityZombie {

    public CustomZombie(World world) {
        super(world);

        List goalB = (List)getPrivateField("b", PathfinderGoalSelector.class, goalSelector);
        goalB.clear();

        List goalC = (List)getPrivateField("c", PathfinderGoalSelector.class, goalSelector);
        goalC.clear();

        List targetB = (List)getPrivateField("b", PathfinderGoalSelector.class, targetSelector);
        targetB.clear();

        List targetC = (List)getPrivateField("c", PathfinderGoalSelector.class, targetSelector);
        targetC.clear();
    }

    /*public CustomZombie(org.bukkit.World world) //You can also directly use the nms world class but this is easier if you are spawning this entity.
    {
        super(((CraftWorld)world).getHandle());
    }*/

    public static Object getPrivateField(String fieldName, Class clazz, Object object)
    {
        Field field;
        Object o = null;
        try
        {
            field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            o = field.get(object);
        }
        catch(NoSuchFieldException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
        return o;
    }
}
