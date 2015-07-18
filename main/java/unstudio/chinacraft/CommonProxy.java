package unstudio.chinacraft;

import unstudio.chinacraft.block.CopperOre;
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

		GameRegistry.registerWorldGenerator(ChinaCraft.copperOre,3);
	}

	public void postInit(FMLPostInitializationEvent event) {

	}
}
