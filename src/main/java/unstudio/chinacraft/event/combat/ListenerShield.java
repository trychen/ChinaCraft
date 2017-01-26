package unstudio.chinacraft.event.combat;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import unstudio.chinacraft.item.CCShield;

/**
 * Created by trychen on 16/10/7.
 */
public class ListenerShield {
//    @SubscribeEvent(priority = EventPriority.LOW)
//    public void attackEvent(LivingHurtEvent e) {
//        if (!(e.entityLiving instanceof EntityPlayer)) {
//            return;
//        }
//        EntityPlayer player = (EntityPlayer) e.entityLiving;
//        if (player.getItemInUse() == null) {
//            return;
//        }
//        float damage = e.ammount;
//        ItemStack activeItemStack = player.getItemInUse();
//
//        if (damage > 0.0F && activeItemStack != null && activeItemStack.getItem() instanceof CCShield) {
//            int i = 1 + MathHelper.floor_float(damage);
//            activeItemStack.damageItem(i, player);
//
//            e.ammount = ((CCShield)activeItemStack.getItem()).defense(activeItemStack,player,e.source,e.ammount);
//
//            if (activeItemStack.stackSize <= 0) {
//                net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, activeItemStack);
//
//                player.setItemInUse(null,0);
//                if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
//                    player.playSound("entity.item.break", 0.8F, 0.8F + player.worldObj.rand.nextFloat() * 0.4F);
//                }
//            }
//        }
//    }
//
//    @SubscribeEvent(priority = EventPriority.LOW)
//    public void attackEvent(LivingAttackEvent e) {
//        if (!(e.entityLiving instanceof EntityPlayer)) {
//            return;
//        }
//        EntityPlayer player = (EntityPlayer) e.entityLiving;
//        if (player.getItemInUse() == null) {
//            return;
//        }
//        float damage = e.ammount;
//        ItemStack activeItemStack = player.getItemInUse();
//
//        if (damage > 0.0F && activeItemStack != null && activeItemStack.getItem() instanceof CCShield) {
//            int i = 1 + MathHelper.floor_float(damage);
//            activeItemStack.damageItem(i, player);
//
//            damage = ((CCShield)activeItemStack.getItem()).defense(activeItemStack,player,e.source,e.ammount);
//
//            if (e.source.isProjectile() && damage == 0) {
//                e.setCanceled(true);
//                if (activeItemStack.stackSize <= 0) {
//                    net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, activeItemStack);
//                    player.setItemInUse(null,0);
//                    if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
//                        player.playSound("entity.item.break", 0.8F, 0.8F + player.worldObj.rand.nextFloat() * 0.4F);
//                    }
//                }
//            }
//        }
//    }
}
