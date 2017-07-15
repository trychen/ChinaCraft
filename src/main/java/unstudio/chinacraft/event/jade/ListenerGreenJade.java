package unstudio.chinacraft.event.jade;

import java.util.List;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;

import unstudio.chinacraft.api.EntityMethod;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.entity.fx.FxHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ListenerGreenJade {
    @SubscribeEvent
    public void DoubleJump(LivingEvent.LivingJumpEvent event) {
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer) event.entity;
            if (entityPlayer.getHeldItem() != null) {
                if (entityPlayer.getHeldItem().getItem().equals(ChinaCraft.bronzeBroadSwordGreen)) {
                    entityPlayer.motionY += 0.2;
                    FxHelper.spawnEffects(entityPlayer.worldObj, event.entity.posX - 0.5, event.entity.posY - 2,
                            event.entity.posZ - 0.5);
                    return;
                } else if (entityPlayer.getHeldItem().getItem().equals(ChinaCraft.jadeGreenItem)) {
                    entityPlayer.motionY += 0.2;
                    FxHelper.spawnEffects(entityPlayer.worldObj, event.entity.posX - 0.5,
                            event.entity.posY - 2, event.entity.posZ - 0.5);
                    return;
                }
            }

                if (entityPlayer.inventory.mainInventory[8] != null) {
                    if (entityPlayer.inventory.mainInventory[8].getItem().equals(ChinaCraft.jadeGreenItem)) {
                        entityPlayer.motionY += 0.2;
                        FxHelper.spawnEffects(entityPlayer.worldObj, event.entity.posX - 0.5, event.entity.posY - 2,
                                event.entity.posZ - 0.5);
                        return;
                    }

                }
        }
    }

    @SubscribeEvent
    public void missFall(LivingFallEvent event) {
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer) event.entity;
            if (entityPlayer.getHeldItem() != null) {
                if (entityPlayer.getHeldItem().getItem().equals(ChinaCraft.bronzeBroadSwordGreen)) {
                    if (event.distance > 4.0f) {
                        entityPlayer.worldObj.spawnParticle("largeexplode",
                                event.entity.posX - 0.5 + entityPlayer.worldObj.rand.nextFloat(),
                                event.entity.posY - 2 + 1.1, event.entity.posZ - 0.5, 0, 0, 0);
                        if (event.distance < 6f) {
                            int c = EntityMethod.attackAroundEntity(entityPlayer, entityPlayer.posX, entityPlayer.posY,
                                    entityPlayer.posZ, DamageSource.causePlayerDamage(entityPlayer), 4.0f);
                            entityPlayer.getHeldItem().damageItem(c > 1 ? c / 2 : 1, entityPlayer);
                        } else {
                            if (event.distance < 30f) {
                                int c = EntityMethod.attackAroundEntity(entityPlayer, entityPlayer.posX, entityPlayer.posY,
                                        entityPlayer.posZ, DamageSource.causePlayerDamage(entityPlayer), 9.0f);
                                entityPlayer.getHeldItem().damageItem(c, entityPlayer);
                            } else {
                                int c = EntityMethod.attackAroundEntity(entityPlayer, entityPlayer.posX, entityPlayer.posY,
                                        entityPlayer.posZ, DamageSource.causePlayerDamage(entityPlayer), 17.0f);
                                entityPlayer.getHeldItem().damageItem(c * 2, entityPlayer);
                            }
                        }
                    }
                    if (event.distance > 15) {
                        entityPlayer.getHeldItem().damageItem(3, entityPlayer);
                        event.distance = 6f;
                        entityPlayer.attackEntityFrom(DamageSource.fall, 2.0f);
                        event.setCanceled(true);
                    } else {
                        event.setCanceled(true);
                    }
                } else if (entityPlayer.getHeldItem().getItem().equals(ChinaCraft.blGiantSword) && event.distance > 3.0f) {
                    entityPlayer.worldObj.spawnParticle("largeexplode",
                            event.entity.posX - 0.5 + entityPlayer.worldObj.rand.nextFloat(),
                            event.entity.posY - 2 + 1.1, event.entity.posZ - 0.5, 0, 0, 0);
                    List<EntityMob> nearbyMobsList = EntityMethod.findNearbyMobs(entityPlayer, entityPlayer.posX,
                            entityPlayer.posY, entityPlayer.posZ);
                    for (EntityMob entityMob : nearbyMobsList) {
                        entityMob.setFire(3);
                    }
                    entityPlayer.getHeldItem().damageItem(nearbyMobsList.size() > 1 ? nearbyMobsList.size() / 2 : 1, entityPlayer);
                }
            }
            if (event.distance > 2.0f) {
                if (entityPlayer.inventory.mainInventory[8] != null) {
                    if (entityPlayer.inventory.mainInventory[8].getItem().equals(ChinaCraft.jadeGreenItem)) {
                        if (event.distance < 5.0f) {
                            event.setCanceled(true);
                        } else if (event.distance > 5.0f && event.distance < 12.0f) {
                            entityPlayer.attackEntityFrom(DamageSource.fall, 1.0f);
                            event.setCanceled(true);
                        }
                    }
                }
            }
        }
    }
}
