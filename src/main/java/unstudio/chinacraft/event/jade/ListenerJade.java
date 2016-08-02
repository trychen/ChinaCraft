package unstudio.chinacraft.event.jade;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import unstudio.chinacraft.common.ChinaCraft;

/**
 * Created by trychen on 15/11/18.
 */
public class ListenerJade {
    @SubscribeEvent
    public void useitem(PlayerUseJadeEvent.ItemRightClick event) {
        if (event.itemStack.getItem() == ChinaCraft.jadeGreen2Item) {
            System.out.println(event.itemStack.getItemDamage());
            if (event.itemStack.getItemDamage() == 0) {
                event.getEntityPlayer().heal(6);
                event.itemStack.setItemDamage(event.itemStack.getMaxDamage() - 1);
            }
        }
    }

    @SubscribeEvent
    public void AttackEntityEvent(LivingHurtEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer) event.getEntity();
            if (event.getEntity().worldObj.rand.nextInt(6) == 3) {
                if (entityPlayer.getHeldItem(EnumHand.MAIN_HAND) != null
                        && entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem().equals(ChinaCraft.bronzeBroadSwordPink)) {
                    event.setAmount(0.0f);
                    event.setCanceled(true);
                } else {
                    for (int time = 0; time < 9; time++) {
                        if (entityPlayer.inventory.mainInventory[time] != null) {
                            if (entityPlayer.inventory.mainInventory[time].getItem().equals(ChinaCraft.jadePinkItem)) {
                                event.setAmount(0.0f);
                                event.setCanceled(true);
                            }
                        }
                    }
                }
                return;
            }
            if (entityPlayer.getHeldItem(EnumHand.MAIN_HAND) != null
                    && entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem().equals(ChinaCraft.jadeGreen2Item)) {
                if (entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItemDamage() < entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getMaxDamage()) {
                    PlayerUseJadeEvent.MainInventory e = new PlayerUseJadeEvent.MainInventory(entityPlayer,
                            entityPlayer.getHeldItem(EnumHand.MAIN_HAND));
                    if (MinecraftForge.EVENT_BUS.post(e))
                        return;
                    if (entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItemDamage() >= 2)
                        entityPlayer.getHeldItem(EnumHand.MAIN_HAND).setItemDamage(entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItemDamage() - 2);
                    else {
                        entityPlayer.getHeldItem(EnumHand.MAIN_HAND).setItemDamage(entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItemDamage() - 1);
                    }
                }
            } else {
                if (entityPlayer.getHeldItem(EnumHand.MAIN_HAND) != null
                        && entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem().equals(ChinaCraft.bronzeBroadSwordGreen2)) {
                    for (int time = 0; time < 9; time++) {
                        if (entityPlayer.inventory.mainInventory[time] != null) {
                            if (entityPlayer.inventory.mainInventory[time].getItem().equals(ChinaCraft.jadePinkItem)) {
                                entityPlayer.inventory.mainInventory[time]
                                        .setItemDamage(entityPlayer.inventory.mainInventory[time].getItemDamage() - 1);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
