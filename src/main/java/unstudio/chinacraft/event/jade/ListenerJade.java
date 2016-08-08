package unstudio.chinacraft.event.jade;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import unstudio.chinacraft.common.ChinaCraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by trychen on 15/11/18.
 */
public class ListenerJade {
    @SubscribeEvent
    public void useitem(PlayerUseJadeEvent.ItemRightClick event) {
        if (event.itemStack.getItem() == ChinaCraft.jadeGreen2Item) {
            if (event.itemStack.getItemDamage() == 0) {
                event.entityPlayer.heal(5);
                event.itemStack.setItemDamage(event.itemStack.getMaxDamage() - 1);
            }
        }
    }

    @SubscribeEvent
    public void AttackEntityEvent(LivingHurtEvent event) {
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer) event.entity;
            if (event.entity.worldObj.rand.nextInt(6) == 3) {
                if (entityPlayer.getHeldItem() != null
                        && entityPlayer.getHeldItem().getItem().equals(ChinaCraft.bronzeBroadSwordPink)) {
                    event.ammount = 0.0f;
                    event.setCanceled(true);
                    return;
                } else {
                    for (int time = 0; time < 9; time++) {
                        if (entityPlayer.inventory.mainInventory[time] != null) {
                            if (entityPlayer.inventory.mainInventory[time].getItem().equals(ChinaCraft.jadePinkItem)) {
                                event.ammount = 0.0f;
                                event.setCanceled(true);
                                return;
                            }
                        }
                    }
                }
            }
            if (!event.source.isMagicDamage()&&!event.source.isUnblockable()) {
                for (int time = 0; time < 9; time++) {
                    if (entityPlayer.inventory.mainInventory[time] != null) {
                        if (entityPlayer.inventory.mainInventory[time].getItem().equals(ChinaCraft.jadeGreen2Item)) {
                            ItemStack itemStack = entityPlayer.inventory.mainInventory[time];
                            if (itemStack.getItemDamage() != 0) {
                                PlayerUseJadeEvent.MainInventory e = new PlayerUseJadeEvent.MainInventory(entityPlayer,
                                        itemStack);
                                if (MinecraftForge.EVENT_BUS.post(e))
                                    return;
                                if (itemStack.getItemDamage() >= 2)
                                    itemStack.setItemDamage(itemStack.getItemDamage() - 2);
                                else {
                                    itemStack.setItemDamage(itemStack.getItemDamage() - 1);
                                }
                            } else if ((entityPlayer.getHealth() - event.ammount) < 0){ //如果是致命一击就自动回血
                                entityPlayer.heal(5);
                                itemStack.setItemDamage(itemStack.getMaxDamage() - 1);
                            }
                            break;
                        }
                    }
                }
            } else {
                if (entityPlayer.getHeldItem() != null
                        && entityPlayer.getHeldItem().getItem().equals(ChinaCraft.bronzeBroadSwordGreen2)) {
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
