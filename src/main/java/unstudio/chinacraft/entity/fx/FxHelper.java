package unstudio.chinacraft.entity.fx;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

/**
 * Created by trychen on 15/11/18.
 */
public class FxHelper {
    public static void spawnEffects(World worldObj, double xCoord, double yCoord, double zCoord) {
        spawnEffects("mobSpellAmbient", worldObj, xCoord, yCoord, zCoord);
    }

    public static void spawnEffects(String type, Entity entity) {
        for (int i = 0; i < 5; i++) {
            double motionX = entity.worldObj.rand.nextGaussian() * 0.02D;
            double motionY = entity.worldObj.rand.nextGaussian() * 0.02D;
            double motionZ = entity.worldObj.rand.nextGaussian() * 0.02D;
            entity.worldObj.spawnParticle(type, entity.posX, entity.posY + 1, entity.posZ, motionX,motionY,motionZ);
        }
    }

    public static void spawnEffects(String kind, World worldObj, double xCoord, double yCoord, double zCoord) {
        worldObj.spawnParticle(kind, xCoord + worldObj.rand.nextFloat(), yCoord + 1.1,
                zCoord + worldObj.rand.nextFloat(), 0, 0, 0);
        worldObj.spawnParticle(kind, xCoord + worldObj.rand.nextFloat(), yCoord + 1.1,
                zCoord + worldObj.rand.nextFloat(), 0, 0, 0);
        worldObj.spawnParticle(kind, xCoord + worldObj.rand.nextFloat(), yCoord + 1.1,
                zCoord + worldObj.rand.nextFloat(), 0, 0, 0);
        worldObj.spawnParticle(kind, xCoord + worldObj.rand.nextFloat(), yCoord + 1.1,
                zCoord + worldObj.rand.nextFloat(), 0, 0, 0);
        worldObj.spawnParticle(kind, xCoord + worldObj.rand.nextFloat(), yCoord + 1.1,
                zCoord + worldObj.rand.nextFloat(), 0, 0, 0);
        worldObj.spawnParticle(kind, xCoord + 0.5, yCoord + 1.1, zCoord + 0.5, 0, 0, 0);
    }
}
