package de.prentl.firsttestproject.entities;

import com.google.common.collect.Sets;
import net.minecraft.server.v1_15_R1.*;

import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public abstract class EntityUtils {

    public static void clearPathfinderGoalCollections(EntityInsentient entityInsentient) {
        Class clazz = PathfinderGoalSelector.class;
        try {
            Map<PathfinderGoal.Type, PathfinderGoalWrapped> c = new EnumMap(PathfinderGoal.Type.class);
            Field field = clazz.getDeclaredField("c");
            field.setAccessible(true);
            field.set(entityInsentient.goalSelector, c);

            Set<PathfinderGoalWrapped> d = Sets.newLinkedHashSet();
            field = clazz.getDeclaredField("d");
            field.setAccessible(true);
            field.set(entityInsentient.goalSelector, d);
        } catch (NoSuchFieldException | IllegalAccessException noSuchFieldException) {
            noSuchFieldException.printStackTrace();
        }
    }

    public static boolean isNearLocation(EntityInsentient entityInsentient, Vec3D vec3D) {
        return Math.abs(entityInsentient.locX() - vec3D.x) < 2
                && Math.abs(entityInsentient.locY() - vec3D.y) < 2
                && Math.abs(entityInsentient.locZ() - vec3D.z) < 2;
    }
}
