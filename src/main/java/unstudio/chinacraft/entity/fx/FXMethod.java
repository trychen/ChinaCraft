package unstudio.chinacraft.entity.fx;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

/**
 * Created by trychen on 15/11/18.
 */
public class FXMethod {
    public static void spawnEffects(World worldObj, double xCoord, double yCoord, double zCoord) {
        spawnEffects(EnumParticleTypes.SPELL_MOB_AMBIENT, worldObj, xCoord, yCoord, zCoord);
    }

    public static void spawnEffects(EnumParticleTypes kind, World worldObj, double xCoord, double yCoord, double zCoord) {
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
