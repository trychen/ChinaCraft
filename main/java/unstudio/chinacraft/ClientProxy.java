package unstudio.chinacraft;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy { 
    public void preInit(FMLPreInitializationEvent event) {
        //PreInit方法
    }
 
    public void init(FMLInitializationEvent event) {
    //Init方法 进行注册物品 方块的模型 材质
    }
 
    public void postInit(FMLPostInitializationEvent event) {
        //PostInit方法
    }
}
