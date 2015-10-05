package unstudio.chinacraft.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

import java.util.Timer;
import java.util.TimerTask;

public class SMFSuper extends SpiritualMagicFigures{
    public SMFSuper(){
        setUnlocalizedName("SMFSuper");
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (!attacker.worldObj.isRemote) {
//            if (target instanceof EntityCreeper) {
//                final EntityCreature creeper = (EntityCreature) target;
//                creeper.tasks.addTask(0, new EntityAIAttackOnCollide(creeper, EntityMob.class, 1.0D, false));
//                creeper.targetTasks.addTask(0, new EntityAINearestAttackableTarget(creeper, EntityMob.class, 0, true));
//            } else if (target instanceof EntityZombie){
//                final EntityZombie zombie = (EntityZombie) target;
//                zombie.tasks.addTask(0, new EntityAIAttackOnCollide(zombie, EntityMob.class, 1.0D, false));
//                zombie.targetTasks.addTask(0, new EntityAINearestAttackableTarget(zombie, EntityMob.class, 0, true));
//                new Timer().schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        zombie.setHealth(0);
//                    }
//                }, 15 * 1000);
//            } else if (target instanceof EntityPlayer){
//                target.addPotionEffect(new PotionEffect(16, 700));
//            } else if (target instanceof EntitySkeleton){
//                final EntitySkeleton skeleton = (EntitySkeleton) target;
//                skeleton.tasks.addTask(0, new EntityAIAttackOnCollide(skeleton, EntityMob.class, 1.0D, false));
//                skeleton.targetTasks.addTask(0, new EntityAINearestAttackableTarget(skeleton, EntityMob.class, 0, true));
//                new Timer().schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        skeleton.setHealth(0);
//                    }
//                }, 15 * 1000);
//            } else
            if (target instanceof EntityMob){
                final EntityMob mob = (EntityMob) target;
                if (!(target instanceof EntitySkeleton)) {
                    mob.tasks.addTask(0, new EntityAIAttackOnCollide(mob, EntityMob.class, 1.0D, false));
                }
                mob.targetTasks.addTask(0, new EntityAINearestAttackableTarget(mob, EntityMob.class, 0, true));
                if (!(target instanceof EntityEnderman || target instanceof EntitySpider || target instanceof EntityCaveSpider)){
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            mob.setHealth(0);
                        }
                    }, 15 * 1000);
                }
            } else if(target instanceof EntityPlayer){
                target.addPotionEffect(new PotionEffect(16, 700));
            }

        }


        if (attacker instanceof EntityPlayer && target instanceof EntityMob) {
            if (!((EntityPlayer) attacker).capabilities.isCreativeMode) {
                if (!(target instanceof EntityEnderman || target instanceof EntitySpider || target instanceof EntityCaveSpider)){
                    --stack.stackSize;
                }
            }
        }
        return false;
    }
}
