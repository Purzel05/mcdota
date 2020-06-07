package de.prentl.firsttestproject.customentities;

import com.google.common.collect.BiMap;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.types.Type;
import net.minecraft.server.v1_15_R1.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.event.entity.CreatureSpawnEvent;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Map;

public class CustomEntityType<T extends EntityLiving> {

    public static CustomEntityType<BlueLeftZombie> blueLeftZombieType;
    public static CustomEntityType<BlueCenterZombie> blueCenterZombieType;
    public static CustomEntityType<BlueRightZombie> blueRightZombieType;

    private static Field REGISTRY_MAT_MAP;

    static {
        try {
            REGISTRY_MAT_MAP = RegistryMaterials.class.getDeclaredField("c");
        } catch (ReflectiveOperationException err) {
            err.printStackTrace();
            REGISTRY_MAT_MAP = null;
            // technically should only occur if server version changes or jar is modified in "weird" ways
        }
    }

    private final MinecraftKey key;
    private final Class<T> clazz;
    private final EntityTypes.b<T> maker;
    private EntityTypes<? super T> parentType;
    private EntityTypes<T> entityType;
    private boolean registered;

    public CustomEntityType(String name, Class<T> customEntityClass, EntityTypes<? super T> parentType, EntityTypes.b<T> maker) {
        this.key = MinecraftKey.a(name); // returns null if 256+ chars, non-alphanumeric, or contains uppercase chars
        this.clazz = customEntityClass;
        this.parentType = parentType;
        this.maker = maker;
    }

    public T spawn(Location loc) {
        return entityType.spawnCreature(((CraftWorld)loc.getWorld()).getHandle(),
                null, null, null,
                new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()),
                EnumMobSpawn.EVENT, true, false, CreatureSpawnEvent.SpawnReason.CUSTOM);
    }

    public void register() throws IllegalStateException {
        if (registered || IRegistry.ENTITY_TYPE.getOptional(key).isPresent()) {
            throw new IllegalStateException(String.format
                    ("Unable to register entity with key '%s' as it is already registered.", key));
        }
        // Add custom entity to data fixers map with parent entity's data fixer
        Map<Object, Type<?>> dataTypes = (Map<Object, Type<?>>)DataConverterRegistry.a()
                .getSchema(DataFixUtils.makeKey(SharedConstants.getGameVersion().getWorldVersion()))
                .findChoiceType(DataConverterTypes.ENTITY_TREE).types(); // DataConverterTypes.ENTITY in < 1.15.2
        dataTypes.put(key.toString(), dataTypes.get(parentType.h().toString().replace("entity/", "")));

        // Add our custom entity to the entity registry map
        EntityTypes.a<T> a = EntityTypes.a.a(maker, EnumCreatureType.CREATURE);
        entityType = a.a(key.getKey());
        IRegistry.a(IRegistry.ENTITY_TYPE, key.getKey(), entityType);
        registered = true;
    }
}