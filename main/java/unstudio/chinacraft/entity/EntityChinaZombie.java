package unstudio.chinacraft.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import unstudio.chinacraft.ChinaCraft;

/**
 * Created by Trychen on 2015/10/3.
 */
public class EntityChinaZombie {
    public static void mainRegister() {
        registerEntity();
    }

    public static void registerEntity() {
        createEntiy(EntityChinaZombie.class,"chinazombie",0x0504FF,0x0025FF);
    }

    public static void createEntiy(Class entityClass, String entityName, int solidColor, int spotColor) {
        int randomId= EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(entityClass,entityName,randomId);
        EntityRegistry.registerModEntity(entityClass, entityName, randomId, ChinaCraft.MODID, 256, 1, true);
        EntityRegistry.addSpawn(entityClass, 2, 0, 1, EnumCreatureType.creature, BiomeGenBase.forest);
        createEgg(randomId,solidColor,spotColor);
    }

    public static void createEgg(int randomId,int solidColor, int spotColor){
       // EntityList.entityEggs.put(Integer.valueOf(randomId),new EntityList.EntityEggInfo(randomId,solidColor,spotColor));
    }
}
