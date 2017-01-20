package unstudio.chinacraft.entity.projectile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityThrownBomb extends EntityThrowable {

    public EntityThrownBomb(World par1World) {
        super(par1World);
    }

    public EntityThrownBomb(World par2World, EntityPlayer par3EntityPlayer) {
        super(par2World, par3EntityPlayer);
    }

    public EntityThrownBomb(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_) {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }

    @Override
    protected void entityInit() {}

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.worldObj.spawnParticle("spell", this.posX, this.posY, this.posZ, 1.0D, 0.0D, 0.0D);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {}

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {}

    @Override
    protected void onImpact(MovingObjectPosition mop) {
        if (!this.worldObj.isRemote) {

            this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 1.5F, true);
            setDead();
        }
    }
}
