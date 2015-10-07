package unstudio.chinacraft.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
/**
 * Created by Try on 2015/10/2.
 * Fixed by Mcdarc on 2015/10/7.
 */

public class EntityChinaZombieMob extends EntityZombie
{
    public EntityChinaZombieMob(World var1)
    {
        super(var1);
    }
    
    @Override
    public EntityZombie createChild (EntityAgeable )
    {
    	EntityChinaZombieMob entityzombie = new EntityChinaZombieMob (this.worldObj);
    	String s = this.func_146067_o();
    	
    	if (s != null && s.trim().length() > 0){
    		entityzombie.func_142017_o(s);;
    		entityzombie.setTamed(true);
    	}
    	
    	return entityzombie;
    }

}