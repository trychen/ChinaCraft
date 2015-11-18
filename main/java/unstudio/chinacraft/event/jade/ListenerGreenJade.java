package unstudio.chinacraft.event.jade;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import unstudio.chinacraft.ChinaCraft;
import unstudio.chinacraft.EntityFX.FXMethod;
import unstudio.chinacraft.api.EntityMethod;

import java.util.List;

public class ListenerGreenJade {
    @SubscribeEvent
    public void PlayerTick(TickEvent.PlayerTickEvent e) {
        if (e.player.getHeldItem() != null) {
            if (e.player.getHeldItem().getItem().equals(ChinaCraft.bronzeBroadSwordGreen)) {
                if (e.player.isAirBorne && e.player.isSneaking()) {
                    e.player.motionY = -0.1;
                    e.player.motionX *= 1.02D;
                    e.player.motionZ *= 1.02D;
                }
            }
        }
    }

    @SubscribeEvent
    public void DoubleJump(LivingEvent.LivingJumpEvent event) {
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer) event.entity;
            if (((EntityPlayer) event.entity).getHeldItem() != null) {
                if (entityPlayer.getHeldItem().getItem().equals(ChinaCraft.bronzeBroadSwordGreen)) {
                    event.entity.motionY *= 2;
                    FXMethod.spawnEffects(entityPlayer.worldObj, event.entity.posX - 0.5, event.entity.posY - 2, event.entity.posZ - 0.5);
                } else if (entityPlayer.inventory.mainInventory[1] != null) {
                    if (entityPlayer.inventory.mainInventory[1].getItem().equals(ChinaCraft.jadeGreenItem)) {
                        event.entity.motionY *= 2;
                        FXMethod.spawnEffects(entityPlayer.worldObj, event.entity.posX - 0.5, event.entity.posY - 2, event.entity.posZ - 0.5);
                    }
                }
            }
            for (int time = 0; time < 9; time++) {
                if (entityPlayer.inventory.mainInventory[time] != null) {
                    if (entityPlayer.getHeldItem() != null) {
                        if (entityPlayer.getHeldItem().getItem().equals(ChinaCraft.bronzeBroadSwordGreen)) {
                            break;
                        } else {
                            if (entityPlayer.inventory.mainInventory[time].getItem().equals(ChinaCraft.jadeGreenItem)) {
                                event.entity.motionY *= 1.8;
                                FXMethod.spawnEffects(entityPlayer.worldObj, event.entity.posX - 0.5, event.entity.posY - 2, event.entity.posZ - 0.5);
                                break;
                            }
                        }
                    } else {
                        if (entityPlayer.inventory.mainInventory[time].getItem().equals(ChinaCraft.jadeGreenItem)) {
                            event.entity.motionY *= 1.8;
                            FXMethod.spawnEffects(entityPlayer.worldObj, event.entity.posX - 0.5, event.entity.posY - 2, event.entity.posZ - 0.5);
                            break;
                        }
                    }
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
                    if (event.distance > 15) {
                        entityPlayer.attackEntityFrom(DamageSource.fall, 2.0f);
                        event.setCanceled(true);
                    } else {
                        event.setCanceled(true);
                    }
                    if (event.distance > 4.0f) {
                        entityPlayer.worldObj.spawnParticle("largeexplode", event.entity.posX - 0.5 + entityPlayer.worldObj.rand.nextFloat(), event.entity.posY - 2 + 1.1, event.entity.posZ - 0.5, 0, 0, 0);
                        if (event.distance < 6f) {
                            EntityMethod.attackAroundEntity(entityPlayer, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, DamageSource.causePlayerDamage(entityPlayer), 4.0f);
                        } else {
                            if (event.distance < 30f) {
                                EntityMethod.attackAroundEntity(entityPlayer, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, DamageSource.causePlayerDamage(entityPlayer), 9.0f);
                            } else {
                                EntityMethod.attackAroundEntity(entityPlayer, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, DamageSource.causePlayerDamage(entityPlayer), 17.0f);
                            }
                        }
                    }
                    return;
                } else if (entityPlayer.getHeldItem().getItem().equals(ChinaCraft.yanLung_Giantknife) && event.distance > 3.0f) {
                    entityPlayer.worldObj.spawnParticle("largeexplode", event.entity.posX - 0.5 + entityPlayer.worldObj.rand.nextFloat(), event.entity.posY - 2 + 1.1, event.entity.posZ - 0.5, 0, 0, 0);
                    List<EntityMob> nearbyMobsList = EntityMethod.findNearbyMobs(entityPlayer, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ);
                    for (EntityMob entityMob : nearbyMobsList) {
                        entityMob.setFire(3);
                    }
                }
            }
            if (event.distance > 2.0f) {
                for (int time = 0; time < 9; time++) {
                    if (entityPlayer.inventory.mainInventory[time] != null) {
                        if (entityPlayer.inventory.mainInventory[time].getItem().equals(ChinaCraft.jadeGreenItem)) {
                            if (event.distance > 4.0f) {
                                event.setCanceled(true);
                                entityPlayer.attackEntityFrom(DamageSource.fall, 1.0f);
                            } else if (event.distance < 4.0f) {
                                event.setCanceled(true);
                            }
                        }
                    }
                }
            }
        }
    }
}
