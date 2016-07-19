package unstudio.chinacraft.entity.AI;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
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
        BlockPos pos = new BlockPos(i, j, k);
        
        for (BlockPos poses : BlockPos.getAllInBox(pos.add(0, 2, 0), pos.add(0, 5, 0))) {
			if(world.getBlockState(poses).getBlock() != Blocks.air) return false;
		}
        
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
