package unstudio.chinacraft.event.entity;

import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import unstudio.chinacraft.entity.mob.EntityChinaZombie;

/**
 * Created by trychen on 16/7/23.
 */
public class ListenerEntityChinaZombie {
    @SubscribeEvent
    public void china(LivingFallEvent event){
        if (event.getEntity() instanceof EntityChinaZombie){
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void move(){

    }
}
