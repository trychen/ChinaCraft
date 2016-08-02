package unstudio.chinacraft.entity.fx;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

/**
 * Created by trychen on 15/11/18.
 */
public class FxHelper {
    public static void spawnEffects(World worldObj, double xCoord, double yCoord, double zCoord) {
        spawnEffects("mobSpellAmbient", worldObj, xCoord, yCoord, zCoord);
    }

    public static void spawnEffects(String kind, World worldObj, double xCoord, double yCoord, double zCoord) {
        EnumParticleTypes types = EnumParticleTypes.getByName(kind);
        worldObj.spawnParticle(types, xCoord + worldObj.rand.nextFloat(), yCoord + 1.1,
                zCoord + worldObj.rand.nextFloat(), 0, 0, 0);
        worldObj.spawnParticle(types, xCoord + worldObj.rand.nextFloat(), yCoord + 1.1,
                zCoord + worldObj.rand.nextFloat(), 0, 0, 0);
        worldObj.spawnParticle(types, xCoord + worldObj.rand.nextFloat(), yCoord + 1.1,
                zCoord + worldObj.rand.nextFloat(), 0, 0, 0);
        worldObj.spawnParticle(types, xCoord + worldObj.rand.nextFloat(), yCoord + 1.1,
                zCoord + worldObj.rand.nextFloat(), 0, 0, 0);
        worldObj.spawnParticle(types, xCoord + worldObj.rand.nextFloat(), yCoord + 1.1,
                zCoord + worldObj.rand.nextFloat(), 0, 0, 0);
        worldObj.spawnParticle(types, xCoord + 0.5, yCoord + 1.1, zCoord + 0.5, 0, 0, 0);
    }
}
