package unstudio.chinacraft.event.combat;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

/**
 * Created by trychen on 17/4/2.
 */
public class ListenerSword {
    @SubscribeEvent
    public void mace(LivingHurtEvent event){
        System.out.println(event.source.damageType);
        System.out.println(event.source.isDamageAbsolute());
        if (event.source.getSourceOfDamage() == null) return;
        Entity entity = event.source.getSourceOfDamage();

    }
}
