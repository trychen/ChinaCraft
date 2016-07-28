package unstudio.sinocraft.event.combat;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.RenderPlayerEvent;

import net.minecraftforge.event.entity.living.LivingHurtEvent;
import unstudio.sinocraft.common.SinoCraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ListenerArmor {
    @SubscribeEvent
    public void wearingNightClothes(TickEvent.PlayerTickEvent event) {
        int i = 4;
        for (ItemStack itemStack : event.player.inventory.armorInventory) {
            i--;
            if (itemStack == null || itemStack.getItem() != SinoCraft.nightClothes[i]) {
                return;
            }
        }

        event.player.addPotionEffect(new PotionEffect(1, 2));
        event.player.addPotionEffect(new PotionEffect(5, 2));
        event.player.addPotionEffect(new PotionEffect(8, 2));
        if (event.player.isSneaking()) {
            event.player.addPotionEffect(new PotionEffect(14, 2));
        }
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void preRenderPlayer(RenderPlayerEvent.Pre event) {
        if (event.entityPlayer.isSneaking()) {
            int i = 4;
            for (ItemStack itemStack : event.entityPlayer.inventory.armorInventory) {
                i--;
                if (itemStack == null || itemStack.getItem() != SinoCraft.nightClothes[i]) {
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
            if (p.inventory.armorInventory[3] != null&&p.inventory.armorInventory[3].getItem().equals(SinoCraft.chinaCrown)){
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
}
