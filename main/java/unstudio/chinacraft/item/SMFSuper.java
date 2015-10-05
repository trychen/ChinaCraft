package unstudio.chinacraft.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class SMFSuper extends SpiritualMagicFigures{
    public SMFSuper(){
        setUnlocalizedName("SMFSuper");
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (target instanceof EntityZombie){
            EntityZombie entityZombie = (EntityZombie) target;
            entityZombie.tasks.removeTask(new EntityAIAttackOnCollide(entityZombie, EntityPlayer.class, 1.0D, false));
            entityZombie.tasks.removeTask(new EntityAIWander(entityZombie, 1.0D));
            entityZombie.targetTasks.removeTask( new EntityAINearestAttackableTarget(entityZombie, EntityPlayer.class, 0, true));
        }
        return false;
    }
}
