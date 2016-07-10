package unstudio.chinacraft.event;

import net.minecraftforge.common.MinecraftForge;

import unstudio.chinacraft.event.combat.ListenerArmor;
import unstudio.chinacraft.event.common.ListenerVersionChecker;
import unstudio.chinacraft.event.entity.ListenerEntityBlackDog;
import unstudio.chinacraft.event.jade.ListenerGreenJade;
import unstudio.chinacraft.event.jade.ListenerJade;
import cpw.mods.fml.common.FMLCommonHandler;
import unstudio.chinacraft.util.annotation.AnnotationClassGetter;

import java.util.List;

public class ListenerRegister {
    public static void init(){
        for (Class<?> clazz : getListeners()) {
            if (clazz == ListenerRegister.class) continue;
            try {
                registerListener(clazz.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void registerListener(Object listener) {
        FMLCommonHandler.instance().bus().register(listener);
        MinecraftForge.EVENT_BUS.register(listener);
    }

    public static List<Class<?>> getListeners(){
        return  AnnotationClassGetter.getClasses("unstudio.chinacraft.event");
    }
}
