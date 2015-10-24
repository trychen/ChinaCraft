package unstudio.chinacraft.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;
/**
 * Created by Try on 2015/10/2.
 * Fixed by Mcdarc on 2015/10/7.
 * 说明：本代码依然无法召唤僵尸= -
 */

public class EntityChinaZombieMob extends EntityZombie
{
    public EntityChinaZombieMob(World var1)
    {
        super(var1);
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

}