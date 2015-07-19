package unstudio.chinacraft;

import net.minecraft.init.Items;
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
    	GameRegistry.registerWorldGenerator(ChinaCraft.copperOre,3);
    	GameRegistry.registerBlock(ChinaCraft.bronzeBlock, "BronzeBlock");
    	GameRegistry.registerBlock(ChinaCraft.tinOre, "TinOre");
    	GameRegistry.registerWorldGenerator(ChinaCraft.copperOre,3);
    	GameRegistry.registerBlock(ChinaCraft.marble, "Marble");
    	GameRegistry.registerWorldGenerator(ChinaCraft.marble,127);
    	
<<<<<<< HEAD
    	GameRegistry.registerItem(ChinaCraft.bronzeIngot, "BronzeIngot");
    	GameRegistry.addSmelting(ChinaCraft.copperOre, new ItemStack(ChinaCraft.bronzeIngot), 0.8f);
    	GameRegistry.registerItem(ChinaCraft.bronzeSword, "BronzeSword");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeSword, 3), new Object[] {" # ", " # ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.bronzePickaxe, "BronzePickaxe");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzePickaxe, 3), new Object[] {"###", " X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.bronzeAxe, "BronzeAxe");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeAxe, 3), new Object[] {"## ", "#X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
=======
    	GameRegistry.registerItem(ChinaCraft.copperIngot, "CopperIngot");
    	GameRegistry.addSmelting(ChinaCraft.copperOre, new ItemStack(ChinaCraft.copperIngot), 0.8f);
    	GameRegistry.registerItem(ChinaCraft.copperSword, "CopperSword");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.copperSword, 3), new Object[] {" # ", " # ", " X ", '#', ChinaCraft.copperIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.copperPickaxe, "CopperPickaxe");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.copperPickaxe, 3), new Object[] {"###", " X ", " X ", '#', ChinaCraft.copperIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.copperAxe, "CopperAxe");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.copperAxe, 3), new Object[] {"## ", "#X ", " X ", '#', ChinaCraft.copperIngot, 'X', Items.stick});
    	
    	GameRegistry .registerItem(ChinaCraft.tinIngot, "TinIngot");
    	GameRegistry.addSmelting(ChinaCraft.tinOre, new ItemStack(ChinaCraft.tinIngot), 0.8f);
>>>>>>> 495d9b2d4ae6bc2e5d709fa8c7c317ce51f75fd9
    }

	public void postInit(FMLPostInitializationEvent event) {

	}
}
