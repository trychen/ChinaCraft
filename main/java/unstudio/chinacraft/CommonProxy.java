package unstudio.chinacraft;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
    	
    }
 
    public void init(FMLInitializationEvent event) {
    	GameRegistry.registerBlock(ChinaCraft.copperOre, "CopperOre");
    	GameRegistry.registerItem(ChinaCraft.copperIngot, "CopperIngot");
    	GameRegistry.addSmelting(ChinaCraft.copperOre, new ItemStack(ChinaCraft.copperIngot), 0.8f);
    	GameRegistry.registerBlock(ChinaCraft.marble, "Marble");
    }
 
    public void postInit(FMLPostInitializationEvent event) {

    }
}
