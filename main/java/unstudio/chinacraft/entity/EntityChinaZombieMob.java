package unstudio.chinacraft.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityChinaZombieMob extends EntityZombie
{
    public EntityChinaZombieMob(World var1)
    {
        super(var1);
        experienceValue = 10;
    }
    
    public EntityZombie createChild (EntityAgeable p_90011_1_)
    {
    	EntityChinaZombieMob entityzombie = new EntityChinaZombieMob (this.worldObj);
    	String s = this.func_146067_o(arrowHitTimer);
    	
    	if (s != null && s.trim().length() > 0){
    		entityzombie.func_142017_o(getAge());
    		entityzombie.canPickUpLoot();
    	}
    	
    	return entityzombie;
    }
    public void onUpdate()
    {
        if (targetTasks != null) isJumping = true;
        super.onUpdate();
    }
}