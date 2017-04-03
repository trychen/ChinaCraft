package unstudio.chinacraft.event.combat;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import unstudio.chinacraft.common.ChinaCraft;

/**
 * Created by trychen on 17/4/2.
 */
public class ListenerSword {
    @SubscribeEvent
    public void mace(LivingHurtEvent event){
        if (event.source.isFireDamage() || event.source.isProjectile() || !(event.source.getSourceOfDamage() instanceof EntityPlayer) && !event.source.damageType.equals("player") || event.source.getSourceOfDamage() == null) return;
        EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();
        if (player.onGround && player.getHeldItem() != null && player.getHeldItem().getItem() == ChinaCraft.mace && player.worldObj.rand.nextBoolean()){
            player.onCriticalHit(event.entity);
            event.ammount *= 1.8;
            player.getHeldItem().damageItem(2, player);
        }
    }
}
