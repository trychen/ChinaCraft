package unstudio.chinacraft;

<<<<<<< HEAD
import unstudio.chinacraft.block.CopperOre;
=======
import net.minecraft.item.ItemStack;
>>>>>>> d82f865d2924dbe4dac6b512081a06507e5b9f0e
import net.minecraft.util.StatCollector;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
<<<<<<< HEAD
	public void preInit(FMLPreInitializationEvent event) {
=======
    public void preInit(FMLPreInitializationEvent event) {
    	
    }
 
    public void init(FMLInitializationEvent event) {
    	GameRegistry.registerBlock(ChinaCraft.copperOre, "CopperOre");
    	GameRegistry.registerItem(ChinaCraft.copperIngot, "CopperIngot");
    	GameRegistry.addSmelting(ChinaCraft.copperOre, new ItemStack(ChinaCraft.copperIngot), 0.8f);
    	GameRegistry.registerBlock(ChinaCraft.marble, "Marble");
    }
 
    public void postInit(FMLPostInitializationEvent event) {
>>>>>>> d82f865d2924dbe4dac6b512081a06507e5b9f0e

	}

	public void init(FMLInitializationEvent event) {
		GameRegistry.registerBlock(ChinaCraft.copperOre, "CopperOre");

		GameRegistry.registerWorldGenerator(ChinaCraft.copperOre,3);
	}

	public void postInit(FMLPostInitializationEvent event) {

	}
}
