package unstudio.chinacraft.entity.projectile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityThrownFirecracker extends EntityThrowable{

    public EntityThrownFirecracker(World par1World)
    {
        super(par1World);
    }

    public EntityThrownFirecracker(World par2World, EntityPlayer par3EntityPlayer) {
        super(par2World, par3EntityPlayer);
    }

    protected void entityInit()
    {
    }

    public void onUpdate()
    {
        super.onUpdate();
        this.worldObj.spawnParticle("mobSpell", this.posX, this.posY, this.posZ, 1.0D, 0.0D, 0.0D);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
    }

    protected void onImpact(MovingObjectPosition mop)
    {
        if (this.worldObj.isRemote)
        {
            Explosion explosion = new Explosion(this.worldObj,this, this.posX, this.posY, this.posZ, 0.3945875F);
            explosion.isFlaming = true;
            explosion.isSmoking = true;
            this.worldObj.playSound(this.posX, this.posY, this.posZ, "chinacraft:firecracker", 0.5F, 0.40000000596046447754F / (this.worldObj.rand.nextFloat() * 0.40000000596046447754F + 0.80000001192092895508F),true);
            if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(this.worldObj, explosion)) return;
            explosion.doExplosionA();
            this.worldObj.spawnParticle("explode", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            setDead();
        }
    }
}