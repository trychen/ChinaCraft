package unstudio.chinacraft.entity.AI;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAIJump extends EntityAIBase{

	EntityLiving entity;
	World world;
	int cooldown;
	double jump;
	int frequency;
	public EntityAIJump(EntityLiving entity,double jump,double frequency)
	{
		this.entity=entity;
		this.world=entity.worldObj;
		this.cooldown=10;
		this.jump=jump;
		this.frequency=MathHelper.floor_double(1.0D/frequency);
	}
	
	@Override
	public boolean shouldExecute() {
		int i = MathHelper.floor_double(this.entity.posX);
        int j = MathHelper.floor_double(this.entity.posY);
        int k = MathHelper.floor_double(this.entity.posZ);
        if(world.getBlock(i, j+2, k)!=Blocks.air || world.getBlock(i, j+3, k)!=Blocks.air 
        		|| world.getBlock(i, j+4, k)!=Blocks.air || world.getBlock(i, j+5, k)!=Blocks.air)
        	return false;
        if(cooldown>0)
        {
        	cooldown--;
        	return false;
        }
        if(entity.getRNG().nextInt(frequency)!=0)
        	return false;
        return true;
	}
    public void startExecuting()
    {
        this.world.setEntityState(this.entity, (byte)10);
        this.entity.getNavigator().clearPathEntity();
    }
    public boolean continueExecuting()
    {
        return false;
    }
    public void updateTask()
    {
    	cooldown=10;
        entity.setVelocity(0, jump, 0);
    }
}
