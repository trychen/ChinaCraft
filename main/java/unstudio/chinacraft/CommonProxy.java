package unstudio.chinacraft;

import unstudio.chinacraft.block.Buhrimill;
import unstudio.chinacraft.renderer.TileEntityBuhrimillRenderer;
import unstudio.chinacraft.tileentity.TileBuhrimill;
import unstudio.chinacraft.item.Lcker;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Random;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
    	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        config.save();
    }
 
    public void init(FMLInitializationEvent event) {
    	Random random = new Random();
    	GameRegistry.registerBlock(ChinaCraft.copperOre, "CopperOre");
    	GameRegistry.registerWorldGenerator(ChinaCraft.copperOre,3);
    	OreDictionary.registerOre("copperOre", ChinaCraft.copperOre);
    	GameRegistry.registerBlock(ChinaCraft.bronzeBlock, "BronzeBlock");
    	GameRegistry.registerBlock(ChinaCraft.tinOre, "TinOre");
    	OreDictionary.registerOre("tinOre", ChinaCraft.tinOre);
    	GameRegistry.registerWorldGenerator(ChinaCraft.tinOre,3);
    	GameRegistry.registerBlock(ChinaCraft.jadeOre, "JadeOre");
    	GameRegistry.registerWorldGenerator(ChinaCraft.jadeOre,3);
    	GameRegistry.registerBlock(ChinaCraft.marble, "Marble");
    	GameRegistry.registerWorldGenerator(ChinaCraft.marble,127);
    	GameRegistry.registerBlock(ChinaCraft.marbleStair, "MarbleStair");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.marbleStair, 4), new Object[] {"#  ", "## ", "###", '#', ChinaCraft.marble});
    	GameRegistry.registerBlock(ChinaCraft.marbleSlab, "MarbleSlab");
    	GameRegistry.registerBlock(ChinaCraft.marbleDoubleSlab, "MarbleDoubleSlab");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.marbleSlab, 6), new Object[] {"   ", "   ", "###", '#', ChinaCraft.marble});
    	GameRegistry.registerBlock(ChinaCraft.smoothMarble, "SmoothMarble");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.smoothMarble, 4), new Object[] {"## ", "## ", "   ", '#', ChinaCraft.marble});
    	GameRegistry.registerBlock(ChinaCraft.pillarMarble, "PillarMarble");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.pillarMarble, 4), new Object[] {"## ", "## ", "   ", '#', ChinaCraft.smoothMarble});
    	GameRegistry.registerBlock(ChinaCraft.chiseledMarble, "ChiseledMarble");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.chiseledMarble, 4), new Object[] {"## ", "## ", "   ", '#', ChinaCraft.pillarMarble});
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.smoothMarble, 4), new Object[] {"## ", "## ", "   ", '#', ChinaCraft.chiseledMarble});
    	GameRegistry.registerBlock(ChinaCraft.silverOre, "SilverOre");
    	GameRegistry.registerWorldGenerator(ChinaCraft.silverOre,3);
    	OreDictionary.registerOre("silverOre", ChinaCraft.silverOre);
    	GameRegistry.registerBlock(ChinaCraft.riceGrow, "RiceGrow");
    	GameRegistry.registerBlock(ChinaCraft.soyGrow, "SoyGrow");
    	GameRegistry.registerBlock(ChinaCraft.woodenWindow, "WoodenWindow");
    	
    	GameRegistry.registerBlock(ChinaCraft.buhrimill, "Buhrimill");
    	ClientRegistry.bindTileEntitySpecialRenderer(TileBuhrimill.class, new TileEntityBuhrimillRenderer());
    	GameRegistry.registerTileEntity(TileBuhrimill.class, "tileEntityBuhrimill");
    	
    	GameRegistry.registerItem(ChinaCraft.bronzeIngot, "BronzeIngot");//铜锭
    	GameRegistry.addSmelting(ChinaCraft.copperOre, new ItemStack(ChinaCraft.bronzeIngot), 0.8f);
    	GameRegistry.registerItem(ChinaCraft.silverIngot, "SilverIngot");//银锭
    	GameRegistry.addSmelting(ChinaCraft.silverOre, new ItemStack(ChinaCraft.silverIngot), 1.0f);
    	GameRegistry.registerItem(ChinaCraft.itemBuhrimill, "ItemBuhrimill");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.itemBuhrimill, 1), new Object[] {" #X", "&#&", "&&&", '#',Item.getItemFromBlock( Blocks.cobblestone), 'X', Items.stick,'&',Item.getItemFromBlock(Blocks.stone)});
    	GameRegistry.registerItem(ChinaCraft.bronzeSword, "BronzeSword");//青铜剑
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeSword, 1), new Object[] {" # ", " # ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.bronzeBroadSword, "BronzeBroadSword");//青铜大刀
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.jiuqu_tang, 1), new Object[] {" # ", " # ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.jiuqu_tang, "JiuQuTang");//九曲镋刀
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeBroadSword, 1), new Object[] {" ##", "## ", "X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.yanLung_Giantknife, "YanLungGiantknife");//炎龙巨刀
    	GameRegistry.registerItem(ChinaCraft.bronzePickaxe, "BronzePickaxe");//青铜稿
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzePickaxe, 1), new Object[] {"###", " X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.bronzeAxe, "BronzeAxe");//青铜斧
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeAxe, 1), new Object[] {"## ", "#X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeAxe, 1), new Object[] {" ##", " X#", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.bronzeHoe, "BronzeHoe");//青铜锄
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeHoe, 1), new Object[] {"## ", " X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeHoe, 1), new Object[] {" ##", " X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.bronzeShovel, "BronzeShovel");//青铜铲
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeShovel, 1), new Object[] {" # ", " X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});    	
    	
    	GameRegistry .registerItem(ChinaCraft.tinIngot, "TinIngot");
    	GameRegistry.addSmelting(ChinaCraft.tinOre, new ItemStack(ChinaCraft.tinIngot), 0.8f);
    	GameRegistry .registerItem(ChinaCraft.rices, "Rices");
    	GameRegistry .registerItem(ChinaCraft.lcker, "Lckers");
    	GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.rices,6), new Object[] {ChinaCraft.lcker});
    	GameRegistry .registerItem(ChinaCraft.soy, "Soy");
    	GameRegistry.registerItem(ChinaCraft.soyPod, "SoyPod");
    	GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.soy,random.nextInt(2)+2), new Object[] {ChinaCraft.soyPod});
    	
    	GameRegistry.registerItem(ChinaCraft.bronzeHelmet, "BronzeHelmet");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeHelmet,1), new Object[] {"###", "# #", "   ", '#', ChinaCraft.bronzeIngot});
    	GameRegistry.registerItem(ChinaCraft.bronzeChestplate, "BronzeChestplate");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeChestplate,1), new Object[] {"# #", "###", "###", '#', ChinaCraft.bronzeIngot});
    	GameRegistry.registerItem(ChinaCraft.bronzeLeggings, "BronzeLeggings");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeLeggings,1), new Object[] {"###", "# #", "# #", '#', ChinaCraft.bronzeIngot});
    	GameRegistry.registerItem(ChinaCraft.bronzeBoots, "BronzeBoots");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeBoots,1), new Object[] {"   ", "# #", "# #", '#', ChinaCraft.bronzeIngot});
    }

	public void postInit(FMLPostInitializationEvent event) {

	}
}
