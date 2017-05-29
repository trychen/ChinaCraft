package unstudio.chinacraft.entity.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

/**
 * Created by trychen on 15/11/18.
 */
@SideOnly(Side.CLIENT)
public class FxHelper {
    public static void spawnEffects(World worldObj, double xCoord, double yCoord, double zCoord) {
        spawnEffects("mobSpellAmbient", worldObj, xCoord, yCoord, zCoord);
    }

    public static void spawnEffectsOnHead(String type, Entity entity) {
        for (int i = 0; i < 5; i++) {
            double motionX = entity.worldObj.rand.nextGaussian() * 0.02D;
            double motionY = entity.worldObj.rand.nextGaussian() * 0.02D;
            double motionZ = entity.worldObj.rand.nextGaussian() * 0.02D;
            entity.worldObj.spawnParticle(type, entity.posX, entity.posY + 1, entity.posZ, motionX,motionY,motionZ);
        }
    }
    public static void spawnEffectsOnFoot(String type, Entity entity) {
        for (int i = 0; i < 5; i++) {
            double motionX = entity.worldObj.rand.nextGaussian() * 0.02D;
            double motionY = entity.worldObj.rand.nextGaussian() * 0.02D;
            double motionZ = entity.worldObj.rand.nextGaussian() * 0.02D;
            entity.worldObj.spawnParticle(type, entity.posX, entity.posY - 1, entity.posZ, motionX,motionY,motionZ);
        }
    }
    public static void spawnOneEffectsOnFoot(String type, Entity entity) {
            double motionX = entity.worldObj.rand.nextGaussian() * 0.02D;
            double motionY = entity.worldObj.rand.nextGaussian() * 0.02D;
            double motionZ = entity.worldObj.rand.nextGaussian() * 0.02D;
            entity.worldObj.spawnParticle(type, entity.posX, entity.posY - 2, entity.posZ, 0,0,0);
//            entity.worldObj.spawnParticle(type, entity.posX, entity.posY - 2, entity.posZ, motionX,motionY,motionZ);
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

    public static void generateSweepAttackParticles(Entity entity) {
        double d0 = (double)(-MathHelper.sin(entity.rotationYaw * 0.017453292F));
        double d1 = (double)MathHelper.cos(entity.rotationYaw * 0.017453292F);


        EntityFX head = new EntitySweepAttackFX(Minecraft.getMinecraft().getTextureManager(),
                entity.worldObj,
                entity.posX + d0,
                entity.posY - (double)entity.height * 0.3f,
                entity.posZ + d1,
                d0,
                0,
                d1);

        Minecraft.getMinecraft().effectRenderer.addEffect(head);
    }
}
