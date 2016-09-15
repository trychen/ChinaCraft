package unstudio.chinacraft.event.combat;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.RenderPlayerEvent;

import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import org.lwjgl.input.Keyboard;
import unstudio.chinacraft.api.ChinaCraftApi;
import unstudio.chinacraft.api.EntityMethod;
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
        NBTTagCompound tCompound = event.player.getEntityData();
        if(FeatureConfig.EnableDoubleJump&&event.phase == TickEvent.Phase.END&&event.side.isServer()&&event.player.onGround) {
            if (tCompound.hasKey("chinacraft.nightClothesHasJumped"))tCompound.removeTag("chinacraft.nightClothesHasJumped");
        }

        int i = 4;
        for (ItemStack itemStack : event.player.inventory.armorInventory) {
            i--;
            if (itemStack == null || itemStack.getItem() != ChinaCraft.nightClothes[i]) {
                if (tCompound.hasKey("chinacraft.wearingWholeNightClothes")) tCompound.removeTag("chinacraft.wearingWholeNightClothes");
                return;
            }
        }
        tCompound.setByte("chinacraft.wearingWholeNightClothes",(byte)0);

        if (event.player.isSneaking()&&(event.player.isAirBorne||!event.player.onGround)) {
            event.player.addPotionEffect(new PotionEffect(14, 2));
            event.player.addPotionEffect(new PotionEffect(2, 2, 3));
            event.player.addPotionEffect(new PotionEffect(15, 8));
        } else {
            event.player.addPotionEffect(new PotionEffect(1, 2));
            event.player.addPotionEffect(new PotionEffect(5, 2));
            event.player.addPotionEffect(new PotionEffect(8, 2));
        }
    }

    @SubscribeEvent
    public void attack(LivingHurtEvent event){
        if (event.source.getEntity() == null) return;
        if (event.entity instanceof EntityPlayer){
            if (!ChinaCraftApi.isWearingWholeNightClothes((EntityPlayer) event.entityLiving)) return;
            EntityLivingBase source = (EntityLivingBase) event.source.getEntity();
            EntityLivingBase destination = event.entityLiving;

            double diffX = destination.posX - source.posX;
            double diffZ;
            for (diffZ = destination.posZ - source.posZ; diffX * diffX + diffZ * diffZ < 1.0E-4D; diffZ = (Math.random() - Math.random()) * 0.01D)
            {
                diffX = (Math.random() - Math.random()) * 0.01D;
            }
            EntityMethod.repel(event.entityLiving, diffX, diffZ);
            System.out.println("end");
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void preRenderPlayer(RenderPlayerEvent.Pre event) {
        if (event.entityPlayer.isSneaking()) {
            if (!ChinaCraftApi.isWearingWholeNightClothes(event.entityPlayer)) return;
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
        if (!ChinaCraftApi.isWearingWholeNightClothes(player)) return;

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
    public void attack(AttackEntityEvent e){
        EntityPlayer player = e.entityPlayer;
        if (!player.isSneaking()) return;
        if (player.isAirBorne||!player.onGround) return;
        if (!ChinaCraftApi.isWearingWholeNightClothes(player)) return;
        e.setCanceled(true);
    }


    @SubscribeEvent
    public void JumpEvent(LivingEvent.LivingJumpEvent event){
        if(!FeatureConfig.EnableDoubleJump)return;
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            if (player.getFoodStats().getFoodLevel()<14) return;

            if (!ChinaCraftApi.isWearingWholeNightClothes(player)) return;

            NBTTagCompound tCompound = player.getEntityData();
            if (!tCompound.hasKey("chinacraft.nightClothesHasJumped")) {
                tCompound.setInteger("chinacraft.nightClothesHasJumped", 0);
            }
            tCompound.setInteger("chinacraft.nightClothesHasJumped", tCompound.getInteger("chinacraft.nightClothesHasJumped") + 1);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void fall(LivingFallEvent e){
        if (!(e.entityLiving instanceof EntityPlayer)) return;
        EntityPlayer player = (EntityPlayer) e.entityLiving;
        if (!ChinaCraftApi.isWearingWholeNightClothes(player)) return;
        if (e.distance < 5){
            e.setCanceled(true);
        }
    }
}
