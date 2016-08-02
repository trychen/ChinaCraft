package unstudio.chinacraft.entity.projectile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.ExplosionEvent;

public class EntityThrownFirecracker extends EntityThrowable {

    public EntityThrownFirecracker(World par1World) {
        super(par1World);
    }

    public EntityThrownFirecracker(World par2World, EntityPlayer par3EntityPlayer) {
        super(par2World, par3EntityPlayer);
    }

    @Override
    protected void entityInit() {}

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.worldObj.spawnParticle(EnumParticleTypes.SPELL_MOB, this.posX, this.posY, this.posZ, 1.0D, 0.0D, 0.0D);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (this.worldObj.isRemote) {
            Explosion explosion = new Explosion(this.worldObj, this, this.posX, this.posY, this.posZ, 0.3945875F, true, true);
            this.worldObj.playSound(this.posX, this.posY, this.posZ, new SoundEvent(new ResourceLocation("chinacraft:firecracker")), SoundCategory.MUSIC, 0.5F,
                    0.40000000596046447754F
                            / (this.worldObj.rand.nextFloat() * 0.40000000596046447754F + 0.80000001192092895508F),
                    true);
            MinecraftForge.EVENT_BUS.post(new ExplosionEvent.Start(this.worldObj, explosion));
            explosion.doExplosionA();
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            setDead();
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {}

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {}

}
