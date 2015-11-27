package unstudio.chinacraft.event;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;
import unstudio.chinacraft.event.common.ListenerVersionChecker;
import unstudio.chinacraft.event.entity.ListenerEntityBlackDog;
import unstudio.chinacraft.event.jade.ListenerGreenJade;

public class ListenerRegister {
    public static void init(){
        registerListener(new ListenerVersionChecker()); //版本检查事件注册
        registerListener(new ListenerGreenJade());

        registerListener(new ListenerEntityBlackDog());
    }
    public static void registerListener(Object listener){
        FMLCommonHandler.instance().bus().register(listener);
        MinecraftForge.EVENT_BUS.register(listener);
    }
}
