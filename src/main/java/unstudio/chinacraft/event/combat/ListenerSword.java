package unstudio.chinacraft.event.combat;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import unstudio.chinacraft.api.EntityMethod;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.common.network.KeyMessage;
import unstudio.chinacraft.entity.fx.FxHelper;

/**
 * Created by trychen on 17/4/2.
 */
public class ListenerSword {
    @SubscribeEvent
    public void mace(LivingHurtEvent event) {
        if (event.source.isFireDamage() || event.source.isProjectile() || !(event.source.getSourceOfDamage() instanceof EntityPlayer) && !event.source.damageType.equals("player") || event.source.getSourceOfDamage() == null)
            return;
        EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();
        if (player.onGround && player.getHeldItem() != null && player.getHeldItem().getItem() == ChinaCraft.mace && player.worldObj.rand.nextInt(3) == 0) {
            player.onCriticalHit(event.entity);
            event.ammount *= 2.5;
            player.getHeldItem().damageItem(2, player);
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void key(InputEvent.MouseInputEvent event){
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if (FMLClientHandler.instance().isGUIOpen(GuiChat.class)) return;
        if (player.getHeldItem() == null || player.getHeldItem().getItem() != ChinaCraft.jointStaff) return;
        if (!player.canAttackWithItem() || player.isUsingItem() || player.isBlocking()) return;

        if (FMLClientHandler.instance().getClient().gameSettings.keyBindAttack.getIsKeyPressed()) {
            ChinaCraft.Network.sendToServer(new KeyMessage(KeyMessage.KEY_ATTACK));//向服务器发送消息

//            DamageSource damageSource = DamageSource.causeMobDamage(player).setMagicDamage();
//            for (EntityLiving entityLiving : EntityMethod.findNear(player, EntityLiving.class, 2, 2)) {
//                if (player.equals(entityLiving)) continue;
//                entityLiving.attackEntityFrom(damageSource, 3);
//            }

            //Spawns a particle.  Args particleName, x, y, z, velX, velY, velZ
            FxHelper.generateSweepAttackParticles(player);
//            player.worldObj.spawnParticle("explode");
        }
    }

    @SubscribeEvent
    public void monksKnife(LivingHurtEvent event) {
        if (event.source.getSourceOfDamage() instanceof EntityLivingBase) {
            EntityLivingBase source = (EntityLivingBase) event.source.getSourceOfDamage();
            if (source.getHeldItem() != null && (source.getHeldItem().getItem() == ChinaCraft.buddhistMonksKnife || source.getHeldItem().getItem() == ChinaCraft.crashBuddhistMonksKnife)) {
                boolean isCrash = source.getHeldItem().getItem() == ChinaCraft.crashBuddhistMonksKnife;
                int damaged = 0;
                int maxDamage = 0;
                int totalReduction = 0;
                int countReduction = 0;
                for (ItemStack itemStack : event.entityLiving.getLastActiveItems()) {
                    if (itemStack != null && itemStack.getItem() instanceof ItemArmor) {
                        int damage = source.worldObj.rand.nextInt(5);
                        itemStack.damageItem(damage, event.entityLiving);
                        damaged += itemStack.getItemDamage();
                        maxDamage += itemStack.getMaxDamage();
                        totalReduction += ((ItemArmor) itemStack.getItem()).getArmorMaterial().getDamageReductionAmount(((ItemArmor) itemStack.getItem()).armorType);
                        countReduction++;
                    }
                }
                if (maxDamage > 0) {
                    float rate = (float) (maxDamage - damaged) / (float) maxDamage;
                    event.ammount *= 1 + rate;
                    if (countReduction > 0) {
                        event.ammount -= totalReduction / (countReduction * 2);
                    }
                    if (isCrash) event.ammount *= 1.4;
                } else {
                    if (isCrash) {
                        event.ammount = 5.0f;
                    } else {
                        event.ammount = 3.0f;
                    }
                }
            } else if (source.getHeldItem() != null && source.getHeldItem().getItem() == ChinaCraft.jointStaff && !event.source.isMagicDamage())
                event.setCanceled(true);
        }
    }
}
