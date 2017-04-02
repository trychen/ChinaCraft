package unstudio.chinacraft.event.jade;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import unstudio.chinacraft.common.ChinaCraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ListenerJade {
    @SubscribeEvent
    public void useitem(PlayerUseJadeEvent.ItemRightClick event) {
        if (event.itemStack.getItem() == ChinaCraft.jadePurpleItem) {
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

            if (entityPlayer.inventory.mainInventory[8] != null) {
                ItemStack itemStack = entityPlayer.inventory.mainInventory[8];
                if (itemStack.getItem() == ChinaCraft.jadePinkItem) {
                    /**
                     * Pink， 反弹1.3倍致命一击
                     */
                    PlayerUseJadeEvent.Hurt e = new PlayerUseJadeEvent.Hurt(event, entityPlayer, itemStack);
                    if (MinecraftForge.EVENT_BUS.post(e)) return;
                    if (itemStack.getItemDamage() == 0) {
                        event.ammount = 0.0f;
                        event.setCanceled(true);
                    }
                    return;
                } else if (itemStack.getItem() == ChinaCraft.jadeGreen2Item) {
                    /**
                     * Green2， 反弹1.3倍致命一击
                     */
                    PlayerUseJadeEvent.Hurt e = new PlayerUseJadeEvent.Hurt(event, entityPlayer, itemStack);
                    if (MinecraftForge.EVENT_BUS.post(e)) return;

                    if (event.source.getEntity() != null && entityPlayer.getHealth() - event.ammount < 0) {
                        event.source.getEntity().attackEntityFrom(DamageSource.causePlayerDamage(entityPlayer), event.ammount * 1.3f);
                    }
                } else if (itemStack.getItem() == ChinaCraft.jadePurpleItem){
                    /**
                     * Purple， 回复CD
                     */
                    PlayerUseJadeEvent.Hurt e = new PlayerUseJadeEvent.Hurt(event, entityPlayer, itemStack);
                    if (itemStack.getItemDamage() != 0) {
                        itemStack.setItemDamage(itemStack.getItemDamage() - 1);
                    }
                }
            }

            if (entityPlayer.getHeldItem() != null) {
                ItemStack heldItemStack = entityPlayer.getHeldItem();
                if (heldItemStack.getItem().equals(ChinaCraft.bronzeBroadSwordPink)) {
                    /**
                     * Green 青铜剑， 一定几率miss伤害
                     */
                    if (event.entity.worldObj.rand.nextInt(6) == 3) {
                        event.ammount = 0.0f;
                        event.setCanceled(true);
                        entityPlayer.getHeldItem().damageItem(5, event.entityLiving);
                        return;
                    }
                } else if (heldItemStack.getItem().equals(ChinaCraft.bronzeBroadSwordGreen2)) {
                    if (event.entity.worldObj.rand.nextInt(6) == 3) {
                        /**
                         * Green2 青铜剑， 一定几率反弹伤害
                         */
                        event.source.getEntity().attackEntityFrom(DamageSource.causePlayerDamage(entityPlayer), event.ammount * 1.3f);
                        entityPlayer.getHeldItem().damageItem(5, event.entityLiving);
                    }
                }
            }
        }
    }
}
