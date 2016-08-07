package unstudio.chinacraft.event.item;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import unstudio.chinacraft.common.ChinaCraft;

/**
 * Created by trychen on 16/8/7.
 */
public class ListenerBuddhistCane {
    @SubscribeEvent
    public void attack(AttackEntityEvent event){
        if (event.entityPlayer.inventory.getCurrentItem() != null && event.entityPlayer.inventory.getCurrentItem().getItem().equals(ChinaCraft.buddhistCane)){
            if (event.target instanceof EntityPlayer || event.target instanceof EntityAnimal){
                ((EntityLivingBase) event.target).addPotionEffect(new PotionEffect(6,20,2));
                event.target.worldObj.spawnParticle("heart",event.target.posX,event.target.posY + event.target.getEyeHeight(),event.target.posZ,1,1,1);
                if (!event.entityPlayer.worldObj.isRemote) event.entityPlayer.attackEntityFrom(new DamageSource("continuedLife"),0.4f);
                event.entityPlayer.inventory.getCurrentItem().damageItem(2,event.entityPlayer);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void tick(TickEvent.PlayerTickEvent event){
        if (event.player.capabilities.isCreativeMode) return;
        if (!event.player.worldObj.isRemote) return;
        if (event.player.ticksExisted % 6 != 0) return;
        if (event.player.getItemInUse() == null) return;
        if (!event.player.getItemInUse().getItem().equals(ChinaCraft.buddhistCane)) return;
        event.player.getItemInUse().damageItem(1,event.player);
    }

}
