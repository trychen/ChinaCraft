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
    	ChinaCraft.bronzeBlock.setBlockTextureName("chinacraft:bronze_block");
    	ChinaCraft.tinOre.setBlockTextureName("chinacraft:tin_ore");
    	ChinaCraft.marble.setBlockTextureName("chinacraft:marble");
    	
<<<<<<< HEAD
    	ChinaCraft.bronzeIngot.setTextureName("chinacraft:bronze_ingot");
    	ChinaCraft.bronzeSword.setTextureName("chinacraft:bronze_sword");
=======
    	ChinaCraft.copperIngot.setTextureName("chinacraft:copper_ingot");
    	ChinaCraft.tinIngot.setTextureName("chinacraft:tin_ingot");
    	ChinaCraft.copperSword.setTextureName("chinacraft:copper_sword");
>>>>>>> 495d9b2d4ae6bc2e5d709fa8c7c317ce51f75fd9
    	
    	ChinaCraft.bronzePickaxe.setTextureName("chinacraft:bronze_pickaxe");
    	ChinaCraft.bronzeAxe.setTextureName("chinacraft:bronze_axe");
    	super.init(event);
    }
 
    public void postInit(FMLPostInitializationEvent event) {
    	super.postInit(event);
    }
}
