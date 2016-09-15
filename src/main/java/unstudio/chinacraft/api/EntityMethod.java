package unstudio.chinacraft.api;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;

/**
 * 一些关于实体的方法
 */
public class EntityMethod {

    /**
     * 寻找实体周围的实体
     * @param player
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static List<EntityMob> findNearbyMobs(EntityPlayer player, double x, double y, double z) {
        return findNearbyMobs(player, x, y, z, 3.0d, 2.0d);
    }

    public static List<EntityMob> findNearbyMobs(EntityPlayer player, double x, double y, double z, double d0,
            double d1) {
        return player.worldObj.getEntitiesWithinAABB(EntityMob.class,
                AxisAlignedBB.getBoundingBox(x - d0, y - d1, z - d0, x + d0, y + d1, z + d0));
    }

    public static List<EntityMob> findNearbyPlayers(Entity entity, double x, double y, double z) {
        return findNearbyPlayers(entity, x, y, z, 3.0d, 2.0d);
    }

    public static List<EntityMob> findNearbyPlayers(Entity player, double x, double y, double z, double d0, double d1) {
        return player.worldObj.getEntitiesWithinAABB(EntityPlayer.class,
                AxisAlignedBB.getBoundingBox(x - d0, y - d1, z - d0, x + d0, y + d1, z + d0));
    }

    public static <T> List<T> findNear(EntityPlayer player, Class<? extends T> find, double d0, double d1) {
        double x = player.posX;
        double y = player.posY;
        double z = player.posZ;
        return player.worldObj.getEntitiesWithinAABB(find,
                AxisAlignedBB.getBoundingBox(x - d0, y - d1, z - d0, x + d0, y + d1, z + d0));
    }

    /**
     * 对玩家周围的怪物造成伤害
     */
    public static int attackAroundEntity(EntityPlayer player, double x, double y, double z, DamageSource damageSource,
            float damage) {
        List<EntityMob> nearbyMobsList = findNearbyMobs(player, x, y, z);
        for (EntityMob entityMob : nearbyMobsList) {
            entityMob.attackEntityFrom(damageSource, damage);
        }
        return nearbyMobsList.size();
    }

    /**
     * 击退实体
     * @param target 实体
     * @param x 击退向量
     * @param z 击退向量
     */
    public static void repel(EntityLivingBase target, double x, double z)
    {
        target.isAirBorne = true;
        float normalizedPower = MathHelper.sqrt_double(x * x + z * z);
        float knockPower = 0.8F;
        target.motionX /= 2.0D;
        target.motionY /= 2.0D;
        target.motionZ /= 2.0D;
        target.motionX -= x / (double)normalizedPower * (double)knockPower;
        target.motionY += (double)knockPower;
        target.motionZ -= z / (double)normalizedPower * (double)knockPower;

        if (target.motionY > 0.4000000059604645D)
        {
            target.motionY = 0.4000000059604645D;
        }
    }

}
