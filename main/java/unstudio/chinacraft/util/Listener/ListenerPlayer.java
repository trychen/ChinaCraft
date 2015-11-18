package unstudio.chinacraft.util.Listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.WorldEvent;
import unstudio.chinacraft.ChinaCraft;
import unstudio.chinacraft.api.EntityMethod;
import unstudio.chinacraft.entity.EntityBlackDog;

import java.util.List;

/**
 * Name and cast of this class are irrelevant
 */
public class ListenerPlayer {
    /**
     * The key is the @ForgeSubscribe annotation and the cast of the Event you put in as argument.
     * The method name you pick does not matter. Method signature is public void, always.
     */
    @SubscribeEvent
    public void entityAttacked(LivingAttackEvent event) {
        EntityLivingBase attackedEnt = event.entityLiving;
        if (attackedEnt instanceof EntityBlackDog) {
            if (event.source.getEntity() instanceof EntityPlayer) {
                EntityPlayer attackSource = (EntityPlayer) event.source.getEntity();
                if (attackSource.getHeldItem() != null) {
                    if (attackSource.getHeldItem().getItem().equals(Items.bowl)) {
                        EntityBlackDog entityBlackDog = (EntityBlackDog) attackedEnt;
                        entityBlackDog.setHealth(entityBlackDog.getHealth() - 4.0f);
                        entityBlackDog.setAngry(true);
                        entityBlackDog.attackEntityFrom(new DamageSource("withoutBloor"), 5);
                        attackSource.inventory.mainInventory[attackSource.inventory.currentItem] = new ItemStack(Items.bowl, attackSource.getHeldItem().stackSize - 1);
                        attackSource.inventory.addItemStackToInventory(new ItemStack(ChinaCraft.blackDogBlood));
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void LivingJumpEvent(LivingEvent.LivingJumpEvent event) {
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer) event.entity;
            if (((EntityPlayer) event.entity).getHeldItem() != null) {
                if (entityPlayer.getHeldItem().getItem().equals(ChinaCraft.bronzeBroadSwordGreen)) {
                    event.entity.motionY *= 2;
                    spawnEffects(entityPlayer.worldObj, event.entity.posX - 0.5, event.entity.posY - 2, event.entity.posZ - 0.5);
                } else if (entityPlayer.inventory.mainInventory[1] != null) {
                    if (entityPlayer.inventory.mainInventory[1].getItem().equals(ChinaCraft.jadeGreenItem)) {
                        event.entity.motionY *= 2;
                        spawnEffects(entityPlayer.worldObj, event.entity.posX - 0.5, event.entity.posY - 2, event.entity.posZ - 0.5);
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
                                spawnEffects(entityPlayer.worldObj, event.entity.posX - 0.5, event.entity.posY - 2, event.entity.posZ - 0.5);
                                break;
                            }
                        }
                    } else {
                        if (entityPlayer.inventory.mainInventory[time].getItem().equals(ChinaCraft.jadeGreenItem)) {
                            event.entity.motionY *= 1.8;
                            spawnEffects(entityPlayer.worldObj, event.entity.posX - 0.5, event.entity.posY - 2, event.entity.posZ - 0.5);
                            break;
                        }
                    }
                }
            }
        }
    }

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
        if (e.player.inventory.armorInventory[0]!= null&&e.player.inventory.armorInventory[1]!= null&&e.player.inventory.armorInventory[2]!= null&&e.player.inventory.armorInventory[3]!= null){

        }
    }
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
                    spawnEffects(entityPlayer.worldObj, event.entity.posX - 0.5, event.entity.posY, event.entity.posZ - 0.5);
                    event.ammount = 0.0f;
                    event.setCanceled(true);
                } else {
                    for (int time = 0; time < 9; time++) {
                        if (entityPlayer.inventory.mainInventory[time] != null) {
                            if (entityPlayer.inventory.mainInventory[time].getItem().equals(ChinaCraft.jadePinkItem)) {
                                spawnEffects(entityPlayer.worldObj, event.entity.posX - 0.5, event.entity.posY - 2, event.entity.posZ - 0.5);
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

    @SubscribeEvent
    public void PlayerFall(LivingFallEvent event) {
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

    public void spawnEffects(World worldObj, double xCoord, double yCoord, double zCoord) {
        spawnEffects("mobSpellAmbient", worldObj, xCoord, yCoord, zCoord);
    }

    public void spawnEffects(String kind, World worldObj, double xCoord, double yCoord, double zCoord) {
        worldObj.spawnParticle(kind, xCoord + worldObj.rand.nextFloat(), yCoord + 1.1, zCoord + worldObj.rand.nextFloat(), 0, 0, 0);
        worldObj.spawnParticle(kind, xCoord + worldObj.rand.nextFloat(), yCoord + 1.1, zCoord + worldObj.rand.nextFloat(), 0, 0, 0);
        worldObj.spawnParticle(kind, xCoord + worldObj.rand.nextFloat(), yCoord + 1.1, zCoord + worldObj.rand.nextFloat(), 0, 0, 0);
        worldObj.spawnParticle(kind, xCoord + worldObj.rand.nextFloat(), yCoord + 1.1, zCoord + worldObj.rand.nextFloat(), 0, 0, 0);
        worldObj.spawnParticle(kind, xCoord + worldObj.rand.nextFloat(), yCoord + 1.1, zCoord + worldObj.rand.nextFloat(), 0, 0, 0);
        worldObj.spawnParticle(kind, xCoord + 0.5, yCoord + 1.1, zCoord + 0.5, 0, 0, 0);
    }
}
