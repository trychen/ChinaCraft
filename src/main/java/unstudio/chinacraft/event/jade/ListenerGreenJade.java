package unstudio.chinacraft.event.jade;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import unstudio.chinacraft.api.EntityMethod;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.entity.fx.FxHelper;

import java.util.List;

public class ListenerGreenJade {
    @SubscribeEvent
    public void PlayerTick(TickEvent.PlayerTickEvent e) {
        if (e.player.getHeldItem(EnumHand.MAIN_HAND) != null) {
            if (e.player.getHeldItem(EnumHand.MAIN_HAND).getItem().equals(ChinaCraft.bronzeBroadSwordGreen)) {
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
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer) event.getEntity();
            if (((EntityPlayer) event.getEntity()).getHeldItem(EnumHand.MAIN_HAND) != null) {
                if (entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem().equals(ChinaCraft.bronzeBroadSwordGreen)) {
                    event.getEntity().motionY += 0.2;
                    FxHelper.spawnEffects(entityPlayer.worldObj, event.getEntity().posX - 0.5, event.getEntity().posY - 2,
                            event.getEntity().posZ - 0.5);
                    return;
                }
            }
            for (int time = 0; time < 9; time++) {
                if (entityPlayer.inventory.mainInventory[time] != null) {
                    if (entityPlayer.getHeldItem(EnumHand.MAIN_HAND) != null) {
                        if (entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem().equals(ChinaCraft.bronzeBroadSwordGreen)) {
                            break;
                        } else {
                            if (entityPlayer.inventory.mainInventory[time].getItem().equals(ChinaCraft.jadeGreenItem)) {
                                event.getEntity().motionY += 1.8;
                                FxHelper.spawnEffects(entityPlayer.worldObj, event.getEntity().posX - 0.5,
                                        event.getEntity().posY - 2, event.getEntity().posZ - 0.5);
                                break;
                            }
                        }
                    } else {
                        if (entityPlayer.inventory.mainInventory[time].getItem().equals(ChinaCraft.jadeGreenItem)) {
                            event.getEntity().motionY *= 1.8;
                            FxHelper.spawnEffects(entityPlayer.worldObj, event.getEntity().posX - 0.5, event.getEntity().posY - 2,
                                    event.getEntity().posZ - 0.5);
                            break;
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void missFall(LivingFallEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer) event.getEntity();
            if (entityPlayer.getHeldItem(EnumHand.MAIN_HAND) != null) {
                if (entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem().equals(ChinaCraft.bronzeBroadSwordGreen)) {
                    if (event.getDistance() > 4.0f) {
                        entityPlayer.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE,
                                event.getEntity().posX - 0.5 + entityPlayer.worldObj.rand.nextFloat(),
                                event.getEntity().posY - 2 + 1.1, event.getEntity().posZ - 0.5, 0, 0, 0);
                        if (event.getDistance() < 6f) {
                            int c = EntityMethod.attackAroundEntity(entityPlayer, entityPlayer.posX, entityPlayer.posY,
                                    entityPlayer.posZ, DamageSource.causePlayerDamage(entityPlayer), 4.0f);
                            entityPlayer.getHeldItem(EnumHand.MAIN_HAND).damageItem(c>1?c/2:1,entityPlayer);
                        } else {
                            if (event.getDistance() < 30f) {
                                int c = EntityMethod.attackAroundEntity(entityPlayer, entityPlayer.posX, entityPlayer.posY,
                                        entityPlayer.posZ, DamageSource.causePlayerDamage(entityPlayer), 9.0f);
                                entityPlayer.getHeldItem(EnumHand.MAIN_HAND).damageItem(c,entityPlayer);
                            } else {
                                int c = EntityMethod.attackAroundEntity(entityPlayer, entityPlayer.posX, entityPlayer.posY,
                                        entityPlayer.posZ, DamageSource.causePlayerDamage(entityPlayer), 17.0f);
                                entityPlayer.getHeldItem(EnumHand.MAIN_HAND).damageItem(c*2,entityPlayer);
                            }
                        }
                    }
                    if (event.getDistance() > 15) {
                        entityPlayer.getHeldItem(EnumHand.MAIN_HAND).damageItem(3, entityPlayer);
                        event.setDistance(6f);
                        entityPlayer.attackEntityFrom(DamageSource.fall, 2.0f);
                        event.setCanceled(true);
                    } else {
                        event.setCanceled(true);
                    }
                } else if (entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem().equals(ChinaCraft.blGiantSword) && event.getDistance() > 3.0f) {

                    entityPlayer.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE,
                            event.getEntity().posX - 0.5 + entityPlayer.worldObj.rand.nextFloat(),
                            event.getEntity().posY - 2 + 1.1, event.getEntity().posZ - 0.5, 0, 0, 0);
                    List<EntityMob> nearbyMobsList = EntityMethod.findNearbyMobs(entityPlayer, entityPlayer.posX,
                            entityPlayer.posY, entityPlayer.posZ);
                    for (EntityMob entityMob : nearbyMobsList) {
                        entityMob.setFire(3);
                    }
                    entityPlayer.getHeldItem(EnumHand.MAIN_HAND).damageItem(nearbyMobsList.size() > 1?nearbyMobsList.size()/2:1, entityPlayer);
                }
            }
            if (event.getDistance() > 2.0f) {
                for (int time = 0; time < 9; time++) {
                    if (entityPlayer.inventory.mainInventory[time] != null) {
                        if (entityPlayer.inventory.mainInventory[time].getItem().equals(ChinaCraft.jadeGreenItem)) {
                            if (event.getDistance() < 5.0f) {
                                event.setCanceled(true);
                            } else if (event.getDistance() > 5.0f && event.getDistance() < 12.0f) {
                                entityPlayer.attackEntityFrom(DamageSource.fall, 1.0f);
                                event.setCanceled(true);
                            }
                        }
                    }
                }
            }
        }
    }
}
