package unstudio.chinacraft;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
    //PreInit方法 主要进行Config读取 设置等
    }
 
    public void init(FMLInitializationEvent event) {
    //Init方法 主要进行设置方块 物品等
    }
 
    public void postInit(FMLPostInitializationEvent event) {
    //PostInit方法
    }
}
