package unstudio.chinacraft.common;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import unstudio.chinacraft.recipes.BuhrimillRecipe;
import unstudio.chinacraft.block.tileentity.*;
import unstudio.chinacraft.util.GuiHandler;
import unstudio.chinacraft.util.config.ConfigLoader;
import unstudio.chinacraft.world.gen.WorldGenMulberryTree;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
		new ConfigLoader(new Configuration(event.getSuggestedConfigurationFile()));
        
		ChinaCraft.bronzeHelmet = (ItemArmor) new ItemArmor(ItemArmor.ArmorMaterial.IRON,ChinaCraft.bronzeArmorTexture,0).setUnlocalizedName("bronze_helmet").setMaxStackSize(1).setCreativeTab(ChinaCraft.tabTool);//闂堟帡鎽愭径瀵告磮
		ChinaCraft.bronzeChestplate =   (ItemArmor) new ItemArmor(ItemArmor.ArmorMaterial.IRON,ChinaCraft.bronzeArmorTexture,1).setUnlocalizedName("bronze_body").setMaxStackSize(1).setCreativeTab(ChinaCraft.tabTool);//闂堟帡鎽愰懗鍝ユ暢
		ChinaCraft.bronzeLeggings = (ItemArmor) new ItemArmor(ItemArmor.ArmorMaterial.IRON,ChinaCraft.bronzeArmorTexture,2).setUnlocalizedName("bronze_legs").setMaxStackSize(1).setCreativeTab(ChinaCraft.tabTool);//闂堟帡鎽愰幎銈堝悪
		ChinaCraft.bronzeBoots =   (ItemArmor) new ItemArmor(ItemArmor.ArmorMaterial.IRON,ChinaCraft.bronzeArmorTexture,3).setUnlocalizedName("bronze_helmet").setMaxStackSize(1).setCreativeTab(ChinaCraft.tabTool);//闂堟帡鎽愰棃鏉戠摍
    }
 
    public void init(FMLInitializationEvent event) {
    	NetworkRegistry.INSTANCE.registerGuiHandler(ChinaCraft.instance, new GuiHandler());

    	GameRegistry.registerBlock(ChinaCraft.copperOre, "CopperOre");
    	GameRegistry.registerWorldGenerator(ChinaCraft.copperOre, 3);
    	OreDictionary.registerOre("oreCopper", ChinaCraft.copperOre);
		GameRegistry.addSmelting(ChinaCraft.copperOre,new ItemStack(ChinaCraft.bronzeIngot,1),1.2f);
    	GameRegistry.registerBlock(ChinaCraft.bronzeBlock, "BronzeBlock");
    	GameRegistry.registerBlock(ChinaCraft.tinOre, "TinOre");
    	OreDictionary.registerOre("tinOre", ChinaCraft.tinOre);
    	GameRegistry.registerWorldGenerator(ChinaCraft.tinOre, 3);
    	GameRegistry.registerBlock(ChinaCraft.jadeOre, "JadeOre");
		OreDictionary.registerOre("oreJade",ChinaCraft.jadeOre);
    	GameRegistry.registerWorldGenerator(ChinaCraft.jadeOre, 3);
    	GameRegistry.registerBlock(ChinaCraft.marble, "Marble");
    	GameRegistry.registerWorldGenerator(ChinaCraft.marble, 127);
    	GameRegistry.registerBlock(ChinaCraft.marbleStair, "MarbleStair");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.marbleStair, 4), new Object[]{"#  ", "## ", "###", '#', ChinaCraft.marble});
    	GameRegistry.registerBlock(ChinaCraft.marbleSlab, "MarbleSlab");
    	GameRegistry.registerBlock(ChinaCraft.marbleDoubleSlab, "MarbleDoubleSlab");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.marbleSlab, 6), new Object[]{"   ", "   ", "###", '#', ChinaCraft.marble});
    	GameRegistry.registerBlock(ChinaCraft.smoothMarble, "SmoothMarble");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.smoothMarble, 4), new Object[]{"## ", "## ", "   ", '#', ChinaCraft.marble});
    	GameRegistry.registerBlock(ChinaCraft.pillarMarble, "PillarMarble");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.pillarMarble, 4), new Object[]{"## ", "## ", "   ", '#', ChinaCraft.smoothMarble});
    	GameRegistry.registerBlock(ChinaCraft.chiseledMarble, "ChiseledMarble");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.chiseledMarble, 4), new Object[]{"## ", "## ", "   ", '#', ChinaCraft.pillarMarble});
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.smoothMarble, 4), new Object[]{"## ", "## ", "   ", '#', ChinaCraft.chiseledMarble});
    	GameRegistry.registerBlock(ChinaCraft.silverOre, "SilverOre");
    	GameRegistry.registerWorldGenerator(ChinaCraft.silverOre, 3);
    	OreDictionary.registerOre("oreSilver", ChinaCraft.silverOre);
    	GameRegistry.registerBlock(ChinaCraft.riceGrow, "RiceGrow");
    	GameRegistry.registerBlock(ChinaCraft.woodenWindow1, "WoodenWindow1");//閺堛劎鐛ラ幋锟�
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.woodenWindow1, 4), new Object[]{" A ", "A#A", " A ", 'A', Item.getItemFromBlock(Blocks.planks), '#', ChinaCraft.artKnife});
    	GameRegistry.registerBlock(ChinaCraft.woodenWindow2, "WoodenWindow2");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.woodenWindow2, 4), new Object[]{"###", "#A#", "###", 'A', ChinaCraft.artKnife, '#', Items.stick});
    	GameRegistry.registerBlock(ChinaCraft.woodenWindow3, "WoodenWindow3");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.woodenWindow3, 4), new Object[]{"B#B", "#A#", "B#B", 'A', ChinaCraft.artKnife, '#', Items.stick, 'B', Item.getItemFromBlock(Blocks.log)});
    	GameRegistry.registerBlock(ChinaCraft.woodenWindow4, "WoodenWindow4");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.woodenWindow4, 4), new Object[]{" # ", "#A#", " # ", 'A', ChinaCraft.artKnife, '#', Items.stick});
    	GameRegistry.registerBlock(ChinaCraft.woodenWindowdragon, "WoodenWindowDragon");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.woodenWindowdragon, 1), new Object[]{"HHH", "B#A", "HHH", 'H', Item.getItemFromBlock(Blocks.log), '#', ChinaCraft.artKnife, 'A', ChinaCraft.woodenWindow4, 'B', ChinaCraft.woodenWindow2});
    	GameRegistry.registerBlock(ChinaCraft.soyGrow, "SoyGrow");
    	GameRegistry.registerBlock(ChinaCraft.blockBamboo, "BlockBamboo");
    	GameRegistry.registerBlock(ChinaCraft.bambooBlock, "BambooBlock");
		GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.bambooBlock), ChinaCraft.bamboo, ChinaCraft.bamboo, ChinaCraft.bamboo, ChinaCraft.bamboo);
		GameRegistry.registerBlock(ChinaCraft.mulberryLog, "MulberryLog");
    	GameRegistry.registerBlock(ChinaCraft.mulberryLeaf, "MulberryLeaf");
    	GameRegistry.registerBlock(ChinaCraft.mulberrySapling, "MulberrySapling");
    	GameRegistry.registerBlock(ChinaCraft.mulberryWood, "MulberryWood");
    	GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.mulberryWood, 4), Item.getItemFromBlock(ChinaCraft.mulberryLog));
    	GameRegistry.registerWorldGenerator(new WorldGenMulberryTree(true), 1);
    	GameRegistry.registerBlock(ChinaCraft.bambooShoot, "BambooShoot");
    	GameRegistry.registerWorldGenerator(ChinaCraft.bambooShoot, 1);
    	
    	GameRegistry.registerBlock(ChinaCraft.jadeWorkingTable, "JadeWorkingTable");
		GameRegistry.addRecipe(new ItemStack(ChinaCraft.jadeWorkingTable), new Object[]{"###", "#X#", "###", '#', Item.getItemFromBlock(Blocks.stone), 'X', Item.getItemFromBlock(Blocks.crafting_table)});
    	GameRegistry.registerTileEntity(TileJadeBench.class, "tileEntityJadeWorkingTable");

		GameRegistry.registerBlock(ChinaCraft.buhrimill, "Buhrimill");
		GameRegistry.addRecipe(new ItemStack(ChinaCraft.buhrimill), new Object[]{" #S", "XIX", "XXX", 'S', Items.stick, '#', Item.getItemFromBlock(Blocks.stone), 'I', Items.iron_ingot, 'X', Item.getItemFromBlock(Blocks.cobblestone)});
		GameRegistry.addRecipe(new ItemStack(ChinaCraft.buhrimill), new Object[]{" #S", "XIX", "XXX", 'S', Items.stick, '#', Item.getItemFromBlock(Blocks.stone), 'I', ChinaCraft.bronzeIngot, 'X', Item.getItemFromBlock(Blocks.cobblestone)});
		GameRegistry.registerTileEntity(TileBuhrimill.class, "tileEntityBuhrimill");
    	
//    	GameRegistry.registerBlock(ChinaCraft.lantern, "Lantern");
    	GameRegistry.registerBlock(ChinaCraft.cooker_on, "CookerOn");
    	GameRegistry.registerBlock(ChinaCraft.cooker_off, "CookerOff");
    	GameRegistry.registerTileEntity(TileCooker.class, "tileEntityCooker");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.cooker_off, 1), new Object[]{"###", "# #", "# #", '#', Item.getItemFromBlock(Blocks.cobblestone)});

//    	GameRegistry.registerBlock(ChinaCraft.sericultureFrame, "SericultureFrame");
//    	GameRegistry.registerTileEntity(TileSericultureFrame.class, "tileEntitySericultureFrame");
//    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.sericultureFrame, 1), new Object[]{"@#@", "@#@", "@#@", '#', Item.getItemFromBlock(Blocks.wooden_slab), '@', Items.stick});
    	
//    	GameRegistry.registerBlock(ChinaCraft.potteryTable, "PotteryTable");
    	
    	GameRegistry.registerItem(ChinaCraft.tinPowder, "TinPowder");
    	OreDictionary.registerOre("dustTin",ChinaCraft.tinPowder);
    	GameRegistry.addSmelting(ChinaCraft.tinPowder, new ItemStack(ChinaCraft.tinIngot), 0.8f);
    	GameRegistry.registerItem(ChinaCraft.copperPowder, "CopperPowder");
    	OreDictionary.registerOre("dustCopper",ChinaCraft.copperPowder);
    	GameRegistry.addSmelting(ChinaCraft.copperPowder, new ItemStack(ChinaCraft.copperIngot), 0.8f);
    	GameRegistry.registerItem(ChinaCraft.copperIngot, "CopperIngot");
    	OreDictionary.registerOre("ingotCopper",ChinaCraft.copperIngot);
    	GameRegistry.registerItem(ChinaCraft.bronzeIngot, "BronzeIngot");//闂堟帡鎽愰柨锟�
		OreDictionary.registerOre("ingotBronze",ChinaCraft.bronzeIngot);
    	GameRegistry.registerItem(ChinaCraft.copperTinMixedPowder, "CopperTinMixedPowder");
    	GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.copperTinMixedPowder, 4), ChinaCraft.copperPowder, ChinaCraft.tinPowder, ChinaCraft.copperPowder, ChinaCraft.copperPowder);
    	GameRegistry.addSmelting(ChinaCraft.copperTinMixedPowder, new ItemStack(ChinaCraft.bronzeIngot), 0.8f);
    	GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.bronzeBlock, 1), ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot);
    	GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.bronzeIngot, 9), Item.getItemFromBlock(ChinaCraft.bronzeBlock));
    	GameRegistry.registerItem(ChinaCraft.silverIngot, "SilverIngot");//闁惧爼鏁�
		OreDictionary.registerOre("ingotSilver",ChinaCraft.silverOre);
    	GameRegistry.addSmelting(ChinaCraft.silverOre, new ItemStack(ChinaCraft.silverIngot), 1.0f);
    	GameRegistry.registerItem(ChinaCraft.itemBuhrimill, "ItemBuhrimill");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.itemBuhrimill, 1), new Object[]{" #X", "&#&", "&&&", '#', Item.getItemFromBlock(Blocks.cobblestone), 'X', Items.stick, '&', Item.getItemFromBlock(Blocks.stone)});
    	GameRegistry.registerItem(ChinaCraft.bronzeSword, "BronzeSword");//闂堟帡鎽愰崜锟�
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeSword, 1), new Object[]{" # ", " # ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.bronzeBroadSword, "BronzeBroadSword");//闂堟帡鎽愭径褍鍨�
    	GameRegistry.registerItem(ChinaCraft.bronzeBroadSwordGreen, "bronzeBroadSwordGreen");//闂堟帡鎽愭径褍鍨�
    	GameRegistry.registerItem(ChinaCraft.bronzeBroadSwordGreen2, "bronzeBroadSwordGreen2");//闂堟帡鎽愭径褍鍨�
    	GameRegistry.registerItem(ChinaCraft.bronzeBroadSwordPink, "bronzeBroadSwordPink");//闂堟帡鎽愭径褍鍨�
    	GameRegistry.registerItem(ChinaCraft.bronzeBroadSwordPurple, "bronzeBroadSwordPurple");//闂堟帡鎽愭径褍鍨�
		GameRegistry.registerItem(ChinaCraft.jiuqu_tang, "JiuQuTang");//娑旀繃娲搁梹瀣瀬
		GameRegistry.addRecipe(new ItemStack(ChinaCraft.jiuqu_tang, 1), new Object[]{"#T#", "###", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick,'T',ChinaCraft.tinIngot});
		GameRegistry.registerItem(ChinaCraft.mace, "Mace");
		GameRegistry.addRecipe(new ItemStack(ChinaCraft.mace, 1), new Object[]{" #O", " X#", "X  ", '#', Items.coal, 'X', Items.stick,'O',Item.getItemFromBlock(Blocks.obsidian)});
		GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeBroadSword, 1), new Object[]{" ##", " X#", "X  ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.yanLung_Giantknife, "YanLungGiantknife");//閻愬酣绶冲銊ュ瀬
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.yanLung_Giantknife, 1), new Object[]{"#B#", "EDE", "CAC", '#', Item.getItemFromBlock(Blocks.obsidian), 'A', Items.stick, 'B', Items.lava_bucket.setContainerItem(Items.bucket), 'C', Items.iron_ingot, 'D', Item.getItemFromBlock(Blocks.soul_sand), 'E', ChinaCraft.bronzeBlock});
    	GameRegistry.registerItem(ChinaCraft.bronzePickaxe, "BronzePickaxe");//闂堟帡鎽愮粙锟�
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzePickaxe, 1), new Object[]{"###", " X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.bronzeAxe, "BronzeAxe");//闂堟帡鎽愰弬锟�
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeAxe, 1), new Object[]{"## ", "#X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeAxe, 1), new Object[]{" ##", " X#", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.bronzeHoe, "BronzeHoe");//闂堟帡鎽愰柨锟�
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeHoe, 1), new Object[]{"## ", " X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeHoe, 1), new Object[]{" ##", " X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.bronzeShovel, "BronzeShovel");//闂堟帡鎽愰柧锟�
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeShovel, 1), new Object[]{" # ", " X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
		GameRegistry.registerItem(ChinaCraft.hammerStone, "StoneHammer");//閻娊鏁�
		GameRegistry.addRecipe(new ItemStack(ChinaCraft.hammerStone, 1), new Object[]{"###", "#X#", " X ", '#', Item.getItemFromBlock(Blocks.cobblestone), 'X', Items.stick});
		GameRegistry.registerItem(ChinaCraft.hammerIron, "IronHammer");//闁句線鏁�
		GameRegistry.addRecipe(new ItemStack(ChinaCraft.hammerIron, 1), new Object[]{"###", "#X#", " X ", '#', Items.iron_ingot, 'X', Items.stick});
		GameRegistry.registerItem(ChinaCraft.hammerBronze, "DiamondBronze");//Bronze闁匡拷
		GameRegistry.addRecipe(new ItemStack(ChinaCraft.hammerBronze, 1), new Object[]{"###", "#X#", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
		GameRegistry.registerItem(ChinaCraft.hammerDiamond, "DiamondHammer");//闁借崵鐓堕柨锟�
		GameRegistry.addRecipe(new ItemStack(ChinaCraft.hammerDiamond, 1), new Object[]{"###", "#X#", " X ", '#', Items.diamond, 'X', Items.stick});
		GameRegistry.registerItem(ChinaCraft.chinaCrown, "ChinaCrown");
		GameRegistry.addRecipe(new ItemStack(ChinaCraft.chinaCrown, 1), new Object[]{"TET", "#X#", "S#S", '#', Item.getItemFromBlock(Blocks.log), 'X', Items.nether_star.setContainerItem(Items.nether_star), 'T', ChinaCraft.tinIngot, 'E', Item.getItemFromBlock(Blocks.dragon_egg).setContainerItem(Item.getItemFromBlock(Blocks.dragon_egg)), 'S', Items.stick});
		GameRegistry.registerItem(ChinaCraft.nightClothesHead, "NightClothesHead");
		GameRegistry.registerItem(ChinaCraft.nightClothesBody, "NightClothesBody");
		GameRegistry.registerItem(ChinaCraft.nightClothesLeg, "NightClothesLeg");
		GameRegistry.registerItem(ChinaCraft.nightClothesShoe, "NightClothesShoe");
		GameRegistry .registerItem(ChinaCraft.tinIngot, "TinIngot");//闁匡繝鏁�
		OreDictionary.registerOre("ingotTin",ChinaCraft.tinIngot);
    	GameRegistry.addSmelting(ChinaCraft.tinOre, new ItemStack(ChinaCraft.tinIngot), 0.8f);
		OreDictionary.registerOre("oreTin",ChinaCraft.tinOre);
    	GameRegistry .registerItem(ChinaCraft.rices, "Rices");//缁拷
    	GameRegistry .registerItem(ChinaCraft.lcker, "Lckers");//濮樺蓱
    	GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.rices, 2), new Object[]{ChinaCraft.lcker});
    	GameRegistry .registerItem(ChinaCraft.soy, "Soy"); //婢堆嗙湸
    	GameRegistry.registerItem(ChinaCraft.soyPod, "SoyPod");//婢堆嗙湸閼斤拷
    	GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.soy, 2), new Object[]{ChinaCraft.soyPod});
    	GameRegistry.registerItem(ChinaCraft.bamboo, "Bamboo");//缁旂懓鐡�
    	GameRegistry.addSmelting(ChinaCraft.bamboo, new ItemStack(ChinaCraft.mulberrySapling), 1.2f);
    	GameRegistry.registerItem(ChinaCraft.itemMulberryLeaf, "ItemMulberryLeaf");//濡楁垵褰�
    	GameRegistry.registerItem(ChinaCraft.woodenBucket, "WoodenBucket");GameRegistry.registerBlock(ChinaCraft.blockWoodenBucket, "BlockWoodenBucket");//閺堛劍銆�(閺傜懓娼�)
		//GameRegistry.addRecipe(new ItemStack(ChinaCraft.blockWoodenBucket, 1), new Object[]{"# #", " # ", "   ", '#', Item.getItemFromBlock(Blocks.wooden_slab)});
		//GameRegistry.addRecipe(new ItemStack(ChinaCraft.blockWoodenBucket, 1), new Object[]{"   ", "# #", " # ", '#', Item.getItemFromBlock(Blocks.wooden_slab)});
    	GameRegistry.registerItem(ChinaCraft.woodenBucket_Water, "WoodenBucket_Water");//閺堛劍銆� (濮橈拷)
    	GameRegistry.registerItem(ChinaCraft.silkworm, "Silkworm");//閾擄拷
    	GameRegistry.registerItem(ChinaCraft.silkwormChrysalis, "SilkwormChrysalis");
    	GameRegistry.registerItem(ChinaCraft.redPacket, "RedPacket");//缁俱垹瀵�
    	GameRegistry.registerItem(ChinaCraft.firecracker,"Firecracker");
		GameRegistry.registerItem(ChinaCraft.bomb,"Bomb");
		GameRegistry.registerItem(ChinaCraft.blackDogBlood, "BlackDogBlood");//姒涙垹瀚嶇悰锟�
		GameRegistry.registerItem(ChinaCraft.moonCake, "MoonCake");//閺堝牓銈�
		GameRegistry.registerItem(ChinaCraft.artKnife, "ItemArtKnife");//缂囧骸浼愰崚鍥у閸掞拷
		GameRegistry.registerBlock(ChinaCraft.xinjiangNutCake, "XinjiangNutCake");
		GameRegistry.registerBlock(ChinaCraft.appleCake, "BLockAppleCake");
		GameRegistry.registerItem(ChinaCraft.itemAppleCake, "AppleCake");

		//青铜物品
    	GameRegistry.registerItem(ChinaCraft.bronzeHelmet, "BronzeHelmet");//闂堟帡鎽愭径瀵告磮
    	GameRegistry.registerItem(ChinaCraft.bronzeChestplate, "BronzeChestplate");//闂堟帡鎽愰幎銈堝厪
    	GameRegistry.registerItem(ChinaCraft.bronzeLeggings, "BronzeLeggings");//闂堟帡鎽愰幎銈堝悪
    	GameRegistry.registerItem(ChinaCraft.bronzeBoots, "BronzeBoots");//闂堟帡鎽愰棄锟�

    	//閼版劗浼�閻拷
		GameRegistry.registerBlock(ChinaCraft.blockFirebrickStructure, "blockFirebrickStructure");
		GameRegistry.registerTileEntity(TileFirebrickStructure.class, "tileFirebrickStructure");
		GameRegistry.registerBlock(ChinaCraft.blockPotteryKiln, "blockPotteryKiln");
		GameRegistry.registerTileEntity(TilePotteryKiln.class, "tilePotteryKiln");
		GameRegistry.registerBlock(ChinaCraft.blockFirebrick, "BlockFirebrick");//閼版劗浼�閻牗鏌熼崸锟�
		GameRegistry.registerItem(ChinaCraft.firebrick, "Firebrick");//閼版劗浼�閻牜澧块崫锟�
		GameRegistry.registerItem(ChinaCraft.claySandMixture,"ClaySandMixture");//缁ê婀″▽娆忕摍濞ｅ嘲鎮庨悧锟�

    	//Jade
    	GameRegistry.registerItem(ChinaCraft.jadeGreenItem, "GreenJade");
    	GameRegistry.registerItem(ChinaCraft.jadeGreen2Item, "Green2Jade");
    	GameRegistry.registerItem(ChinaCraft.jadePinkItem, "GreenPink");
    	GameRegistry.registerItem(ChinaCraft.jadePurpleItem, "JadePurple");
    	GameRegistry.registerItem(ChinaCraft.jadeKnife, "JadeKnife");//閻滃鐓堕崚鍥у閸掞拷

    	//Drink閵嗕笚ood
//    	GameRegistry.registerItem(ChinaCraft.cup, "Cup");
//    	GameRegistry.registerItem(ChinaCraft.cup_Clay, "ClayCup");
//    	GameRegistry.registerItem(ChinaCraft.cupChocolate, "ChocolateDrink");
//		GameRegistry.registerItem(ChinaCraft.cupChrysanthemum, "ChrysanthemumDrink");
		GameRegistry.registerItem(ChinaCraft.cocoa, "Cocoa");
		GameRegistry.registerItem(ChinaCraft.ladyfinger, "Ladyfinger");
    	GameRegistry.registerItem(ChinaCraft.flour, "Flour");
    	GameRegistry.registerItem(ChinaCraft.riceFlour, "RiceFlour");
    	GameRegistry.registerItem(ChinaCraft.barleyRice, "BarleyRice");



		//spiritual_magic_figures
		GameRegistry.registerItem(ChinaCraft.spiritualMagicFigures, "SpiritualMagicFigures");
		GameRegistry.registerItem(ChinaCraft.smfFire, "SpiritualMagicFiguresFire");
		GameRegistry.registerItem(ChinaCraft.smfNightVision, "SpiritualMagicFiguresNightVision");
		GameRegistry.registerItem(ChinaCraft.smfPoison, "SpiritualMagicFiguresPoison");
		GameRegistry.registerItem(ChinaCraft.smfPower, "SpiritualMagicFiguresPower");
		GameRegistry.registerItem(ChinaCraft.smfHeal, "SpiritualMagicFiguresHeal");
		GameRegistry.registerItem(ChinaCraft.smfProtect, "SpiritualMagicFiguresProtect");

		GameRegistry.registerBlock(ChinaCraft.redCarpet, "red_carpet");
		GameRegistry.registerBlock(ChinaCraft.silkCarpet, "silk_carpet");
		GameRegistry.registerBlock(ChinaCraft.slik_right, "slik_right");
		GameRegistry.registerBlock(ChinaCraft.slik_left, "slik_left");
		GameRegistry.registerBlock(ChinaCraft.silk_right_down, "silk_right_down");
		GameRegistry.registerBlock(ChinaCraft.silk_right_up, "silk_right_up");
		GameRegistry.registerBlock(ChinaCraft.silk_left_up, "silk_left_up");
		GameRegistry.registerBlock(ChinaCraft.silk_left_down, "silk_left_down");

		GameRegistry.registerItem(ChinaCraft.debug, "Debug");

		Recipes.init();
	}

	public void postInit(FMLPostInitializationEvent event) {

	}
}
