package unstudio.chinacraft.event;

import java.util.List;

import net.minecraftforge.common.MinecraftForge;
import unstudio.chinacraft.util.annotation.AnnotationClassGetter;

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
        MinecraftForge.EVENT_BUS.register(listener);
    }

    public static List<Class<?>> getListeners(){
        return  AnnotationClassGetter.getClasses("unstudio.chinacraft.event");
    }
}
