package unstudio.chinacraft.event.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.entity.animal.EntityBlackDog;

public class ListenerEntityBlackDog {
    @SubscribeEvent
    public void entityAttacked(LivingAttackEvent event) {
        EntityLivingBase attackedEnt = event.getEntityLiving();
        if (attackedEnt instanceof EntityBlackDog) {
            if (event.getSource().getEntity() instanceof EntityPlayer) {
                EntityPlayer attackSource = (EntityPlayer) event.getSource().getEntity();
                if (attackSource.getHeldItem(EnumHand.MAIN_HAND) != null) {
                    if (attackSource.getHeldItem(EnumHand.MAIN_HAND).getItem().equals(Items.BOWL)) {
                        EntityBlackDog entityBlackDog = (EntityBlackDog) attackedEnt;
                        entityBlackDog.setHealth(entityBlackDog.getHealth() - 4.0f);
                        entityBlackDog.setAngry(true);
                        entityBlackDog.attackEntityFrom(new DamageSource("withoutBloor"), 5);
                        attackSource.inventory.mainInventory[attackSource.inventory.currentItem] = new ItemStack(
                                Items.BOWL, attackSource.getHeldItem(EnumHand.MAIN_HAND).stackSize - 1);
                        attackSource.inventory.addItemStackToInventory(new ItemStack(ChinaCraft.blackDogBlood));
                    }
                }
            }
        }
    }
}
