package unstudio.chinacraft.event.jade;

import cpw.mods.fml.common.eventhandler.EventPriority;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import unstudio.chinacraft.common.ChinaCraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import unstudio.chinacraft.entity.fx.FxHelper;

public class ListenerJade {
    @SubscribeEvent
    public void useitem(PlayerUseJadeEvent.ItemRightClick event) {
        if (event.itemStack.getItem() == ChinaCraft.jadePurpleItem) {
            /**
             * Purple Jade， 回5f血
             */
            if (event.itemStack.getItemDamage() == 0) {
                event.entityPlayer.heal(5);
                event.itemStack.setItemDamage(event.itemStack.getMaxDamage() - 1);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void attackEvent(LivingHurtEvent event) {
        if (event.source.getEntity() != null && !event.source.isProjectile() && event.source.damageType.equals("player") && event.source.getEntity() instanceof EntityPlayer){
            EntityPlayer entityPlayer = (EntityPlayer) event.source.getEntity();
            ItemStack heldItemStack = entityPlayer.getHeldItem();
            if (heldItemStack!= null && heldItemStack.getItem().equals(ChinaCraft.bronzeBroadSwordGreen2)) {
                if (event.entity.worldObj.rand.nextInt(6) == 3) {
                    /**
                     * Green2 Sword， 一定几率吸血
                     */
                    entityPlayer.heal(event.ammount);
                    heldItemStack.damageItem(4, entityPlayer);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void hurtEvent(LivingHurtEvent event) {
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer) event.entity;

            if (entityPlayer.inventory.mainInventory[8] != null) {
                ItemStack itemStack = entityPlayer.inventory.mainInventory[8];
                if (itemStack.getItem() == ChinaCraft.jadePinkItem) {
                    /**
                     * Pink Jade， 反弹1.3倍致命一击
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
                     * Green2 Jade， 几率性反弹1.3倍致命一击
                     */
                    PlayerUseJadeEvent.Hurt e = new PlayerUseJadeEvent.Hurt(event, entityPlayer, itemStack);
                    if (MinecraftForge.EVENT_BUS.post(e)) return;
                    if (entityPlayer.worldObj.rand.nextInt(4) == 3 && event.source.getEntity() != null) {
                        event.source.getEntity().attackEntityFrom(DamageSource.causePlayerDamage(entityPlayer), event.ammount * 1.3f);
                        itemStack.damageItem(1, entityPlayer);
                        if(!e.entity.worldObj.isRemote) {
//                            e.entity.worldObj.spawnParticle("magicCrit", e.entity.worldObj, e.entity.posX - 0.5, e.entity.posY + 1, e.entity.posZ - 0.5);
                            FxHelper.spawnEffectsOnHead("magicCrit", e.entity);
                        }
                    }
                } else if (itemStack.getItem() == ChinaCraft.jadePurpleItem) {
                    /**
                     * Purple Jade， 回复CD
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
                     * Pink Sword， 一定几率miss伤害
                     */
                    if (event.entity.worldObj.rand.nextInt(6) == 3) {
                        event.ammount = 0.0f;
                        event.setCanceled(true);
                        entityPlayer.getHeldItem().damageItem(5, event.entityLiving);
                        return;
                    }
                }
            }
        }
    }
}
