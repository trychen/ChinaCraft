package unstudio.chinacraft;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy { 
    public void preInit(FMLPreInitializationEvent event) {
    	super.preInit(event);
    }
 
    public void init(FMLInitializationEvent event) {
    	ChinaCraft.copperOre.setBlockTextureName("chinacraft:copper_ore");
    	ChinaCraft.tinOre.setBlockTextureName("chinacraft:tin_ore");
    	ChinaCraft.marble.setBlockTextureName("chinacraft:marble");
    	
    	ChinaCraft.copperIngot.setTextureName("chinacraft:copper_ingot");
    	super.init(event);
    }
 
    public void postInit(FMLPostInitializationEvent event) {
    	super.postInit(event);
    }
}
