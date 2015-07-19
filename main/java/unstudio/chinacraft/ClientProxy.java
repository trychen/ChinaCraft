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
    	ChinaCraft.jadeOre.setBlockTextureName("chinacraft:jade_ore");
    	ChinaCraft.marble.setBlockTextureName("chinacraft:marble");
    	ChinaCraft.smoothMarble.setBlockTextureName("chinacraft:smooth_marble");
    	ChinaCraft.chiseledMarble.setBlockTextureName("chinacraft:chiseled_marble");
    	ChinaCraft.silverOre.setBlockTextureName("chinacraft:silver_ore");
    	
    	ChinaCraft.bronzeIngot.setTextureName("chinacraft:bronze_ingot");
    	ChinaCraft.bronzeSword.setTextureName("chinacraft:bronze_sword");
    	ChinaCraft.tinIngot.setTextureName("chinacraft:tin_ingot");
    	ChinaCraft.silverIngot.setTextureName("chinacraft:silver_ingot");
    	ChinaCraft.rices.setTextureName("chinacraft:rice_item");
    	
    	ChinaCraft.bronzePickaxe.setTextureName("chinacraft:bronze_pickaxe");
    	ChinaCraft.bronzeAxe.setTextureName("chinacraft:bronze_axe");
    	ChinaCraft.bronzeHoe.setTextureName("chinacraft:bronze_hoe");
    	
    	ChinaCraft.bronzeHelmet.setTextureName("chinacraft:bronze_helmet");
    	ChinaCraft.bronzeChestplate.setTextureName("chinacraft:bronze_chestplate");
    	ChinaCraft.bronzeLeggings.setTextureName("chinacraft:bronze_leggings");
    	ChinaCraft.bronzeBoots.setTextureName("chinacraft:bronze_boots");
    	super.init(event);
    }
 
    public void postInit(FMLPostInitializationEvent event) {
    	super.postInit(event);
    }
}
