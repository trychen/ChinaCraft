package unstudio.chinacraft;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import unstudio.chinacraft.renderer.TileEntityBuhrimillRenderer;
import unstudio.chinacraft.tileentity.TileBuhrimill;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
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
    	ChinaCraft.marbleSlab.setBlockTextureName("chinacraft:smooth_marble");
    	ChinaCraft.marbleDoubleSlab.setBlockTextureName("chinacraft:smooth_marble");
    	ChinaCraft.silverOre.setBlockTextureName("chinacraft:silver_ore");
    	ChinaCraft.blockBamboo.setBlockTextureName("chinacraft:bamboo");
    	ChinaCraft.mulberrySapling.setBlockTextureName("chinacraft:mulberry_sapling");
    	ChinaCraft.mulberryWood.setBlockTextureName("chinacraft:mulberry_wood");
    	ChinaCraft.bambooBlock.setBlockTextureName("chinacraft:bamboo_block");
    	ChinaCraft.jadeWorkingTable.setBlockTextureName("chinacraft:jade_table");
    	
    	ChinaCraft.buhrimill.setBlockTextureName("chinacraft:empty");
    	
    	ChinaCraft.bronzeIngot.setTextureName("chinacraft:bronze_ingot");
    	ChinaCraft.bronzeSword.setTextureName("chinacraft:bronze_sword");
    	ChinaCraft.bronzeBroadSword.setTextureName("chinacraft:bronze_broadsword");
    	ChinaCraft.yanLung_Giantknife.setTextureName("chinacraft:yanlung_giantknife");
    	ChinaCraft.jiuqu_tang.setTextureName("chinacraft:jiuqu_tang");
    	ChinaCraft.tinIngot.setTextureName("chinacraft:tin_ingot");
    	ChinaCraft.silverIngot.setTextureName("chinacraft:silver_ingot");
    	ChinaCraft.rices.setTextureName("chinacraft:rice_item");
    	ChinaCraft.soy.setTextureName("chinacraft:soy_item");
    	ChinaCraft.lcker.setTextureName("chinacraft:lcker");
    	ChinaCraft.soyPod.setTextureName("chinacraft:soy_pod");
    	ChinaCraft.itemBuhrimill.setTextureName("chinacraft:item_buhrimill");
    	ChinaCraft.bamboo.setTextureName("chinacraft:bamboo");
    	ChinaCraft.itemMulberryLeaf.setTextureName("chinacraft:mulberry_leaf");
    	
    	ChinaCraft.bronzePickaxe.setTextureName("chinacraft:bronze_pickaxe");
    	ChinaCraft.bronzeAxe.setTextureName("chinacraft:bronze_axe");
    	ChinaCraft.bronzeHoe.setTextureName("chinacraft:bronze_hoe");
    	ChinaCraft.bronzeShovel.setTextureName("chinacraft:bronze_shovel");
    	ChinaCraft.jadeKnife.setTextureName("chinacraft:jade_knife");
    	
    	ChinaCraft.bronzeHelmet.setTextureName("chinacraft:bronze_helmet");
    	ChinaCraft.bronzeChestplate.setTextureName("chinacraft:bronze_chestplate");
    	ChinaCraft.bronzeLeggings.setTextureName("chinacraft:bronze_leggings");
    	ChinaCraft.bronzeBoots.setTextureName("chinacraft:bronze_boots");
    	
    	//Jade
    	ChinaCraft.jadeGreenItem.setTextureName("chinacraft:jade_green");
    	ChinaCraft.jadeGreen2Item.setTextureName("chinacraft:jade_green2");
    	ChinaCraft.jadePinkItem.setTextureName("chinacraft:jade_pink");
    	ChinaCraft.jadePurpleItem.setTextureName("chinacraft:jade_purple");
    	
    	//Drink、Food
    	ChinaCraft.cup.setTextureName("chinacraft:cup");
    	ChinaCraft.cup_Clay.setTextureName("chinacraft:cup_clay");
    	ChinaCraft.cupChocolate.setTextureName("chinacraft:cup_chocolate");
    	ChinaCraft.cocoa.setTextureName("chinacraft:cocoa");
    	ChinaCraft.ladyfinger.setTextureName("chinacraft:ladyfinger");
    	ChinaCraft.cupWater.setTextureName("chinacraft:cup_water");
    	ChinaCraft.cupChrysanthemum.setTextureName("chinacraft:cup_chrysanthemum");
    
    	
    	ClientRegistry.bindTileEntitySpecialRenderer(TileBuhrimill.class, new TileEntityBuhrimillRenderer());
    	super.init(event);
    }
 
    public void postInit(FMLPostInitializationEvent event) {
    	super.postInit(event);
    }
}
