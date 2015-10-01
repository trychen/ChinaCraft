package unstudio.chinacraft.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import unstudio.chinacraft.ChinaCraft;

public class EntityChineseZombie {
    public static void mainRegister() {
        registerEntity();
    }

    public static void registerEntity() {
        createEntiy(EntityChineseZombieMob.class,"Chinese Zombie",0x0004FF,0xFF00E1);
    }

    public static void createEntiy(Class entityClass, String entityName, int solidColor, int spotColor) {
        int randomId= EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(entityClass,entityName,randomId);
        EntityRegistry.registerModEntity(entityClass, entityName, randomId, ChinaCraft.MODID, 64, 1, true);
        EntityRegistry.addSpawn(entityClass, 2, 0, 1, EnumCreatureType.creature, BiomeGenBase.forest);
        createEgg(randomId,solidColor,spotColor);
    }

    public static void createEgg(int randomId,int solidColor, int spotColor){
        EntityList.entityEggs.put(Integer.valueOf(randomId),new EntityList.EntityEggInfo(randomId,solidColor,spotColor));
    }
}
