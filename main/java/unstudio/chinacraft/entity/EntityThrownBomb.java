package unstudio.chinacraft.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by trychen on 15/11/22.
 */
public class EntityThrownBomb extends EntityThrowable{

    public EntityThrownBomb(World par1World)
    {
        super(par1World);
    }

    public EntityThrownBomb(World par2World, EntityPlayer par3EntityPlayer) {
        super(par2World, par3EntityPlayer);
    }

    protected void entityInit()
    {
    }

    public void onUpdate()
    {
        super.onUpdate();
        this.worldObj.spawnParticle("spell", this.posX, this.posY, this.posZ, 1.0D, 0.0D, 0.0D);
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
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    bomb();
                }
            },2 * 1000);
        }
    }
    public void bomb(){
        this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 4.5F, true);
    }
}
