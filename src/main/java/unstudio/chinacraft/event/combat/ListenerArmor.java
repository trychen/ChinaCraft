package unstudio.chinacraft.event.combat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;

import unstudio.chinacraft.common.ChinaCraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ListenerArmor {
    @SubscribeEvent
    public void wearingNightClothes(TickEvent.PlayerTickEvent event) {
        int i = 4;
        for (ItemStack itemStack : event.player.inventory.armorInventory) {
            i--;
            if (itemStack == null || itemStack.getItem() != ChinaCraft.nightClothes[i]) {
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
    public void wearingChinaCrown(TickEvent.PlayerTickEvent event) {
        if (event.player.inventory.armorInventory[3] == null)
            return;
        if (!event.player.inventory.armorInventory[3].getItem().equals(ChinaCraft.chinaCrown))
            return;
        event.player.addPotionEffect(new PotionEffect(5, 10));
    }
}
