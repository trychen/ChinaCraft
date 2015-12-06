package unstudio.chinacraft.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EntityList;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.entity.animal.EntityBlackDog;
import unstudio.chinacraft.entity.mob.EntityChinaZombie;
import unstudio.chinacraft.entity.projectile.EntityThrownBomb;
import unstudio.chinacraft.entity.projectile.EntityThrownFirecracker;

/**
 * Created by Trychen on 2015/10/30.
 */
public class EntityRegister {
    public static void init() {
        createEntiy(EntityBlackDog.class,"black_dog",0x0004FF,0xFF00E1);
//        EntityRegistry.addSpawn(EntityBlackDog.class, 2, 0, 1, EnumCreatureType.monster, BiomeGenBase.forest);
        createEntiy(EntityChinaZombie.class,"chinazombie",0x0504FF,0x0025FF);
//        createEntiy(EntityKongmingLantern.class,"kongming_lantern",0x0504FF,0x0025FF);


        EntityRegistry.registerModEntity(EntityThrownFirecracker.class, "Entity_Thrown_Firecracker", EntityRegistry.findGlobalUniqueEntityId(), ChinaCraft.instance, 350, 30, true);
        EntityRegistry.registerModEntity(EntityThrownBomb.class, "Entity_Thrown_Bomb", EntityRegistry.findGlobalUniqueEntityId(), ChinaCraft.instance, 350, 30, true);
    }

    public static void createEntiy(Class entityClass, String entityName, int solidColor, int spotColor) {
        int randomId= EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(entityClass, entityName, randomId);
        EntityRegistry.registerModEntity(entityClass, entityName, randomId, ChinaCraft.instance, 64, 1, true);
        createEgg(randomId,solidColor,spotColor);
    }

    public static void createEgg(int randomId,int solidColor, int spotColor){
        EntityList.entityEggs.put(Integer.valueOf(randomId),new EntityList.EntityEggInfo(randomId,solidColor,spotColor));
    }
}
