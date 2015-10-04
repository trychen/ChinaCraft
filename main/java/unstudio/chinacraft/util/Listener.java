package unstudio.chinacraft.util;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
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
                        entityBlackDogMob.setAngry(true);
                        entityBlackDogMob.attackEntityFrom(new DamageSource("withoutBloor"),5);
                        attackSource.inventory.mainInventory[attackSource.inventory.currentItem] = new ItemStack(Items.bowl,attackSource.getHeldItem().stackSize - 1);
                        attackSource.inventory.addItemStackToInventory(new ItemStack(ChinaCraft.blackDogBlood));
                    }
                }
            }
        }
    }
}