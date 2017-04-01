package unstudio.chinacraft.event.combat;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.RenderPlayerEvent;

import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import unstudio.chinacraft.api.ChinaCraftApi;
import unstudio.chinacraft.api.EntityMethod;
import unstudio.chinacraft.common.ChinaCraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import unstudio.chinacraft.common.config.FeatureConfig;
import unstudio.chinacraft.common.network.KeyMessage;

public class ListenerArmor {
    /**
     * 夜行衣穿着检查器
     */
    @SubscribeEvent
    public void wearingNightClothes(TickEvent.PlayerTickEvent event) {
        NBTTagCompound tCompound = event.player.getEntityData();
        if(FeatureConfig.enableDoubleJump &&event.phase == TickEvent.Phase.END&&event.side.isServer()&&event.player.onGround) {
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
    }


    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void preRenderPlayer(RenderPlayerEvent.Pre event) {
        if (event.entityPlayer.isSneaking()) {
            if (event.entityPlayer.worldObj.getWorldTime() < 13500 || event.entityPlayer.worldObj.getWorldTime() > 22300) return;
            if (!ChinaCraftApi.isWearingWholeNightClothes(event.entityPlayer)) return;
            event.setCanceled(true);
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void key(InputEvent.KeyInputEvent event){
        if(!FeatureConfig.enableDoubleJump)return;
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if (player.getItemInUse() != null) return; //不能拿着物品连跳
        if (!ChinaCraftApi.isWearingWholeNightClothes(player)) return;

        if (!FMLClientHandler.instance().isGUIOpen(GuiChat.class)) {
            if (FMLClientHandler.instance().getClient().gameSettings.keyBindJump.getIsKeyPressed()) { //是否按下了跳跃键
                if(FeatureConfig.enableDoubleJump) {
                    if (player.motionY < 0.04 && player.isAirBorne) {
                        ChinaCraft.Network.sendToServer(new KeyMessage(0));//向服务器发送消息
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void JumpEvent(LivingEvent.LivingJumpEvent event){
        if(!FeatureConfig.enableDoubleJump)return;
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            if (player.getFoodStats().getFoodLevel()<14) return;//饱食度要大于14
            if (player.inventory.getCurrentItem() != null) return; //不能拿着物品连跳
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

    @SubscribeEvent
    public void attack(LivingHurtEvent event){
        if (event.source.getEntity() == null) return;
        if (!(event.entity instanceof EntityPlayer)) return;
        EntityPlayer entityPlayer = (EntityPlayer) event.entity;
        if (!ChinaCraftApi.isWearingWholeNightClothes(entityPlayer)){
            hurtNightClothes(event);
        } else if (entityPlayer.inventory.armorInventory[1] != null && entityPlayer.inventory.armorInventory[1].getItem().equals(ChinaCraft.cassock)){
            hurtCassock(event, entityPlayer);
        } else if (entityPlayer.inventory.armorInventory[3] != null&& entityPlayer.inventory.armorInventory[3].getItem().equals(ChinaCraft.chinaCrown)){
            hurtChinaCrown(event, entityPlayer);
        }

    }

    private void hurtChinaCrown(LivingHurtEvent event, EntityPlayer entityPlayer) {
        if (entityPlayer.worldObj.rand.nextInt(2)==1){
            double percent = (entityPlayer.worldObj.rand.nextInt(5)+3)/10.0;
            if (event.source.getSourceOfDamage()!=null&&event.source.getSourceOfDamage() instanceof EntityLiving){
                event.source.getSourceOfDamage().attackEntityFrom(DamageSource.causePlayerDamage(entityPlayer), (float) (event.ammount * (1 - percent)));
                event.ammount = (float) (event.ammount *  percent);
            }
        }
    }

    private void hurtCassock(LivingHurtEvent event, EntityPlayer entityPlayer){
        if (entityPlayer.getHealth() - event.ammount <= 0){
            entityPlayer.setHealth(entityPlayer.getMaxHealth() / 2);
            entityPlayer.inventory.armorInventory[1] = null;
        }
    }

    private void hurtNightClothes(LivingHurtEvent event){
        EntityLivingBase source = (EntityLivingBase) event.source.getEntity();
        EntityLivingBase destination = event.entityLiving;

        double diffX = destination.posX - source.posX;
        double diffZ;
        for (diffZ = destination.posZ - source.posZ; diffX * diffX + diffZ * diffZ < 1.0E-4D; diffZ = (Math.random() - Math.random()) * 0.01D)
        {
            diffX = (Math.random() - Math.random()) * 0.01D;
        }
        EntityMethod.repel(event.entityLiving, diffX, diffZ);
    }
}
