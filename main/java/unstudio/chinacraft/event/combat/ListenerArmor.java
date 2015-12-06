package unstudio.chinacraft.event.combat;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.potion.PotionEffect;
import unstudio.chinacraft.common.ChinaCraft;

public class ListenerArmor {
    @SubscribeEvent
    public void wearingNightClothes(TickEvent.PlayerTickEvent event) {
        if (event.player.inventory.armorInventory[0] == null || event.player.inventory.armorInventory[1] == null || event.player.inventory.armorInventory[2] == null || event.player.inventory.armorInventory[3] == null)
            return;
        if (!event.player.inventory.armorInventory[3].getItem().equals(ChinaCraft.nightClothes[0])) return;
        if (!event.player.inventory.armorInventory[2].getItem().equals(ChinaCraft.nightClothes[1])) return;
        if (!event.player.inventory.armorInventory[1].getItem().equals(ChinaCraft.nightClothes[2])) return;
        if (!event.player.inventory.armorInventory[0].getItem().equals(ChinaCraft.nightClothes[3])) return;

        event.player.addPotionEffect(new PotionEffect(1, 10));
        event.player.addPotionEffect(new PotionEffect(8, 10));
        event.player.addPotionEffect(new PotionEffect(5, 10));
    }

    @SubscribeEvent
    public void wearingChinaCrown(TickEvent.PlayerTickEvent event) {
        if (event.player.inventory.armorInventory[0] == null || event.player.inventory.armorInventory[1] == null || event.player.inventory.armorInventory[2] == null || event.player.inventory.armorInventory[3] == null)
            return;
        if (!event.player.inventory.armorInventory[3].getItem().equals(ChinaCraft.chinaCrown)) return;
        event.player.addPotionEffect(new PotionEffect(1, 10));
    }
}
