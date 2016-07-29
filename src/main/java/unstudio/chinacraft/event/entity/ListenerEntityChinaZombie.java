package unstudio.chinacraft.event.entity;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import unstudio.chinacraft.entity.mob.EntityChinaZombie;

/**
 * Created by trychen on 16/7/23.
 */
public class ListenerEntityChinaZombie {
    @SubscribeEvent
    public void china(LivingFallEvent event){
        if (event.entity instanceof EntityChinaZombie){
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void move(){

    }
}
