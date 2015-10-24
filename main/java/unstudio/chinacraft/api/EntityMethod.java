package unstudio.chinacraft.api;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;

import java.util.List;

/**
 * Created by Trychen on 2015/10/24.
 */
public class EntityMethod {

    public static List<EntityMob> findNearbyMobs(EntityPlayer player, double x, double y, double z)
    {
        return findNearbyMobs(player,x,y,z,3.0d,2.0d);
    }

    public static List<EntityMob> findNearbyMobs(EntityPlayer player, double x, double y, double z,double d0,double d1)
    {
        return player.worldObj.getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getBoundingBox(x - d0, y - d1, z - d0, x + d0, y + d1, z + d0));
    }

    public static List<EntityMob> findNearbyPlayers(Entity entity, double x, double y, double z)
    {
        return findNearbyPlayers(entity, x, y, z, 3.0d, 2.0d);
    }

    public static List<EntityMob> findNearbyPlayers(Entity player, double x, double y, double z,double d0,double d1)
    {
        return player.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(x - d0, y - d1, z - d0, x + d0, y + d1, z + d0));
    }

    public static void attackAroundEntity(EntityPlayer player,double x, double y, double z,DamageSource damageSource,float damage){
        List<EntityMob> nearbyMobsList = findNearbyMobs(player,x,y,z);
        for(EntityMob entityMob:nearbyMobsList){
            entityMob.attackEntityFrom(damageSource,damage);
        }
    }
}

