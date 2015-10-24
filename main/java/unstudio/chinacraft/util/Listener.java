package unstudio.chinacraft.util;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import unstudio.chinacraft.ChinaCraft;
import unstudio.chinacraft.entity.EntityBlackDogMob;

/**
 * Name and cast of this class are irrelevant
 */
public class Listener
{
    /**
     * The key is the @ForgeSubscribe annotation and the cast of the Event you put in as argument.
     * The method name you pick does not matter. Method signature is public void, always.
     */
    @SubscribeEvent
    public void entityAttacked(LivingAttackEvent event)
    {
        EntityLivingBase attackedEnt = event.entityLiving;
        if (attackedEnt instanceof EntityBlackDogMob){
            if(event.source.getEntity() instanceof EntityPlayer){
                EntityPlayer attackSource = (EntityPlayer)event.source.getEntity();
                if (attackSource.getHeldItem() != null){
                    if (attackSource.getHeldItem().getItem().equals(Items.bowl)){
                        EntityBlackDogMob entityBlackDogMob = (EntityBlackDogMob) attackedEnt;
                        entityBlackDogMob.setHealth(entityBlackDogMob.getHealth() - 4.0f);
                        entityBlackDogMob.setAngry(true);
                        entityBlackDogMob.attackEntityFrom(new DamageSource("withoutBloor"),5);
                        attackSource.inventory.mainInventory[attackSource.inventory.currentItem] = new ItemStack(Items.bowl,attackSource.getHeldItem().stackSize - 1);
                        attackSource.inventory.addItemStackToInventory(new ItemStack(ChinaCraft.blackDogBlood));
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void LivingJumpEvent(LivingEvent.LivingJumpEvent event){
        if (event.entity instanceof EntityPlayer &&((EntityPlayer) event.entity).getHeldItem() != null) {
            EntityPlayer entityPlayer = (EntityPlayer) event.entity ;
            if (entityPlayer.getHeldItem().getItem().equals(ChinaCraft.bronzeBroadSwordGreen)){
                event.entity.motionY *= 2;
                spawnEffects(entityPlayer.worldObj,event.entity.posX-0.5, event.entity.posY - 2, event.entity.posZ-0.5);
            }else if (entityPlayer.inventory.mainInventory[1]!=null){
                if (entityPlayer.inventory.mainInventory[1].getItem().equals(ChinaCraft.jadeGreenItem)){
                    event.entity.motionY *= 2;
                    spawnEffects(entityPlayer.worldObj,event.entity.posX-0.5, event.entity.posY - 2, event.entity.posZ-0.5);
                }
            }
        }
    }
    @SubscribeEvent
    public void jump(TickEvent.PlayerTickEvent e){
        if (e.player.getHeldItem() != null) {
            if (e.player.getHeldItem().getItem().equals(ChinaCraft.bronzeBroadSwordGreen)) {
                if(e.player.isAirBorne||e.player.isSneaking()){
                    e.player.motionY *= -0.17;
                }
            }
        }
    }
    public void spawnEffects(World worldObj, double xCoord, double yCoord, double zCoord) {
        worldObj.spawnParticle("mobSpellAmbient", xCoord + worldObj.rand.nextFloat(), yCoord + 1.1, zCoord + worldObj.rand.nextFloat(), 0, 0, 0);
        worldObj.spawnParticle("mobSpellAmbient", xCoord + worldObj.rand.nextFloat(), yCoord + 1.1, zCoord + worldObj.rand.nextFloat(), 0, 0, 0);
        worldObj.spawnParticle("mobSpellAmbient", xCoord + worldObj.rand.nextFloat(), yCoord + 1.1, zCoord + worldObj.rand.nextFloat(), 0, 0, 0);
        worldObj.spawnParticle("mobSpellAmbient", xCoord + worldObj.rand.nextFloat(), yCoord + 1.1, zCoord + worldObj.rand.nextFloat(), 0, 0, 0);
        worldObj.spawnParticle("mobSpellAmbient", xCoord + 0.5, yCoord + 1.1, zCoord + 0.5, 0, 0, 0);
    }
}
