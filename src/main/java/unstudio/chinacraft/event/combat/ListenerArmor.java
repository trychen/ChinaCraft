package unstudio.chinacraft.event.combat;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.RenderPlayerEvent;

import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.lwjgl.input.Keyboard;
import unstudio.chinacraft.common.ChinaCraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import unstudio.chinacraft.common.config.FeatureConfig;
import unstudio.chinacraft.common.network.KeyMessage;
import unstudio.chinacraft.common.network.KeyMessageHandler;

import java.util.Hashtable;

public class ListenerArmor {
    @SubscribeEvent
    public void wearingNightClothes(TickEvent.PlayerTickEvent event) {
        if(FeatureConfig.EnableDoubleJump&&event.phase == TickEvent.Phase.END&&event.side.isServer()&&event.player.onGround) {
            NBTTagCompound tCompound = event.player.getEntityData();
            if (tCompound.hasKey("nightClothesHasJumped"))tCompound.removeTag("nightClothesHasJumped");
        }
        int i = 4;
        for (ItemStack itemStack : event.player.inventory.armorInventory) {
            i--;
            if (itemStack == null || itemStack.getItem() != ChinaCraft.nightClothes[i]) {
                return;
            }
        }
        if (event.player.isSneaking()) {
            event.player.addPotionEffect(new PotionEffect(14, 2));
            event.player.addPotionEffect(new PotionEffect(2, 2, 3));
            event.player.addPotionEffect(new PotionEffect(15, 8, 2));
        } else {
            event.player.addPotionEffect(new PotionEffect(1, 2));
            event.player.addPotionEffect(new PotionEffect(5, 2));
            event.player.addPotionEffect(new PotionEffect(8, 2));
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void preRenderPlayer(RenderPlayerEvent.Pre event) {
        if (event.entityPlayer.isSneaking()) {
            int i = 4;
            for (ItemStack itemStack : event.entityPlayer.inventory.armorInventory) {
                i--;
                if (itemStack == null || itemStack.getItem() != ChinaCraft.nightClothes[i]) {
                    return;
                }
            }
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void wearingChinaCrown(LivingHurtEvent event) {
        if (event.entityLiving instanceof EntityPlayer){
            EntityPlayer p = (EntityPlayer) event.entityLiving;
            if (p.inventory.armorInventory[3] != null&&p.inventory.armorInventory[3].getItem().equals(ChinaCraft.chinaCrown)){
                if (p.worldObj.rand.nextInt(2)==1){
                    double percent = (p.worldObj.rand.nextInt(5)+3)/10.0;
                    if (event.source.getSourceOfDamage()!=null&&event.source.getSourceOfDamage() instanceof EntityLiving){
                        event.source.getSourceOfDamage().attackEntityFrom(DamageSource.causePlayerDamage(p), (float) (event.ammount * (1 - percent)));
                        event.ammount = (float) (event.ammount *  percent);
                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void key(InputEvent.KeyInputEvent event){
        if(!FeatureConfig.EnableDoubleJump)return;
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        int i = 4;
        for (ItemStack itemStack : player.inventory.armorInventory) {
            i--;
            if (itemStack == null || itemStack.getItem() != ChinaCraft.nightClothes[i]) {
                return;
            }
        }
        if (!FMLClientHandler.instance().isGUIOpen(GuiChat.class)) {
            if (FMLClientHandler.instance().getClient().gameSettings.keyBindJump.getIsKeyPressed()) {
                if(FeatureConfig.EnableDoubleJump) {
                    if (player.motionY < 0.04 && player.isAirBorne) {
                        ChinaCraft.Network.sendToServer(new KeyMessage(0));
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public void JumpEvent(LivingEvent.LivingJumpEvent event){
        if(!FeatureConfig.EnableDoubleJump)return;
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            int i = 4;
            for (ItemStack itemStack : player.inventory.armorInventory) {
                i--;
                if (itemStack == null || itemStack.getItem() != ChinaCraft.nightClothes[i]) {
                    return;
                }
            }
            NBTTagCompound tCompound = player.getEntityData();
            if (!tCompound.hasKey("nightClothesHasJumped")) {
                tCompound.setInteger("nightClothesHasJumped", 0);
            }
            tCompound.setInteger("nightClothesHasJumped", tCompound.getInteger("nightClothesHasJumped") + 1);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void fall(LivingFallEvent e){
        if (!(e.entityLiving instanceof EntityPlayer)) return;
        EntityPlayer player = (EntityPlayer) e.entityLiving;
        int i = 4;
        for (ItemStack itemStack : player.inventory.armorInventory) {
            i--;
            if (itemStack == null || itemStack.getItem() != ChinaCraft.nightClothes[i]) {
                return;
            }
        }
        if (e.distance < 5){
            e.setCanceled(true);
        }
    }
}
