package unstudio.chinacraft.event.jade;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
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
                event.entityPlayer.heal(6);
                event.itemStack.setItemDamage(event.itemStack.getMaxDamage() - 1);
            }
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
            if (entityPlayer.getHeldItem() != null && entityPlayer.getHeldItem().getItem().equals(ChinaCraft.jadeGreen2Item)) {
                if (entityPlayer.getHeldItem().getItemDamage() < entityPlayer.getHeldItem().getMaxDamage()) {
                    PlayerUseJadeEvent.MainInventory e = new PlayerUseJadeEvent.MainInventory(entityPlayer, entityPlayer.getHeldItem());
                    if (MinecraftForge.EVENT_BUS.post(e)) return;
                    if (entityPlayer.getHeldItem().getItemDamage() >= 2)
                        entityPlayer.getHeldItem().setItemDamage(entityPlayer.getHeldItem().getItemDamage() - 2);
                    else {
                        entityPlayer.getHeldItem().setItemDamage(entityPlayer.getHeldItem().getItemDamage() - 1);
                    }
                }
            } else {
                if (entityPlayer.getHeldItem().getItem() != null && entityPlayer.getHeldItem().getItem().equals(ChinaCraft.bronzeBroadSwordGreen2)) {
                    for (int time = 0; time < 9; time++) {
                        if (entityPlayer.inventory.mainInventory[time] != null) {
                            if (entityPlayer.inventory.mainInventory[time].getItem().equals(ChinaCraft.jadePinkItem)) {
                                entityPlayer.inventory.mainInventory[time].setItemDamage(entityPlayer.inventory.mainInventory[time].getItemDamage()-1);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
