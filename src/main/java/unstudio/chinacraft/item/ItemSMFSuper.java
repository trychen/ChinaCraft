package unstudio.chinacraft.item;

import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import unstudio.chinacraft.api.ItemMethod;
import unstudio.chinacraft.common.ChinaCraft;

public class ItemSMFSuper extends Item {
    public ItemSMFSuper() {
        setUnlocalizedName("SMFSuper");
        setCreativeTab(ChinaCraft.tabCore);
        setMaxStackSize(8);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (!attacker.worldObj.isRemote) {
            if (attacker instanceof EntityPlayer && target instanceof EntityMob) {
                if (target instanceof EntityZombie || target instanceof EntityZombie || target instanceof EntitySkeleton
                        || target instanceof EntityWitch) {
                    final EntityMob mob = (EntityMob) target;
                    mob.tasks.addTask(0, new EntityAIAttackOnCollide(mob, EntityMob.class, 1.0D, false));
                    mob.targetTasks.addTask(0, new EntityAINearestAttackableTarget(mob, EntityMob.class, 0, true));
                }
                ItemMethod.cutItemStack(stack, (EntityPlayer) attacker);
            }
        }
        // if (!attacker.worldObj.isRemote) {
        // if (target instanceof EntityMob){
        // final EntityMob mob = (EntityMob) target;
        // if (!(target instanceof EntitySkeleton)) {
        // mob.tasks.addTask(0, new EntityAIAttackOnCollide(mob,
        // EntityMob.class, 1.0D, false));
        // }
        // mob.targetTasks.addTask(0, new EntityAINearestAttackableTarget(mob,
        // EntityMob.class, 0, true));
        // if (!(target instanceof EntityEnderman || target instanceof
        // EntitySpider || target instanceof EntityCaveSpider)){
        // new Timer().schedule(new TimerTask() {
        // @Override
        // public void run() {
        // mob.setHealth(0);
        // }
        // }, 15 * 1000);
        // }
        // } else if(target instanceof EntityPlayer){
        // target.addPotionEffect(new PotionEffect(16, 700));
        // }
        //
        // }
        return false;
    }
}
