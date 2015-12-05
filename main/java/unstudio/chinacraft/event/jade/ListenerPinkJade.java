package unstudio.chinacraft.event.jade;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import unstudio.chinacraft.common.ChinaCraft;

/**
 * Created by trychen on 15/11/18.
 */
public class ListenerPinkJade {

    @SubscribeEvent
    public void tick(TickEvent.PlayerTickEvent e){
        if (ChinaCraft.jadehasHealTicker >0){
            ChinaCraft.jadehasHealTicker--;
        }
    }
    @SubscribeEvent
    public void EntityJoinWorldEvent(EntityJoinWorldEvent e){
        if (e.entity instanceof EntityPlayer){
            ChinaCraft.jadehasHealTicker = 0;
        }
    }

    @SubscribeEvent
    public void AttackEntityEvent(LivingHurtEvent event) {
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer) event.entity;
            if (event.entity.worldObj.rand.nextInt(6) == 3) {
                if (entityPlayer.getHeldItem() != null && entityPlayer.getHeldItem().getItem().equals(ChinaCraft.bronzeBroadSwordPink)) {
                    event.ammount = 0.0f;
                    event.setCanceled(true);
                } else {
                    for (int time = 0; time < 9; time++) {
                        if (entityPlayer.inventory.mainInventory[time] != null) {
                            if (entityPlayer.inventory.mainInventory[time].getItem().equals(ChinaCraft.jadePinkItem)) {
                                event.ammount = 0.0f;
                                event.setCanceled(true);
                            }
                        }
                    }
                }
                return;
            }
            if (entityPlayer.getHeldItem() != null && entityPlayer.getHeldItem().getItem().equals(ChinaCraft.jadeGreen2Item)){
                if (entityPlayer.getHeldItem().getMaxDamage() != 0){
                    entityPlayer.getHeldItem().setItemDamage(entityPlayer.getHeldItem().getItemDamage() - 1);
                }
            }
        }
    }
}
