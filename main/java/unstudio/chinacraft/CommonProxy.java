package unstudio.chinacraft;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import unstudio.chinacraft.entity.EntityBlackDog;
import unstudio.chinacraft.entity.EntityChinaZombie;
import unstudio.chinacraft.item.combat.BronzeBoots;
import unstudio.chinacraft.item.combat.BronzeChestplate;
import unstudio.chinacraft.item.combat.BronzeHelmet;
import unstudio.chinacraft.item.combat.BronzeLeggings;
import unstudio.chinacraft.recipes.BuhrimillRecipe;
import unstudio.chinacraft.tileentity.TileBuhrimill;
import unstudio.chinacraft.tileentity.TileCooker;
import unstudio.chinacraft.tileentity.TileFirebrickStructure;
import unstudio.chinacraft.tileentity.TileJadeBench;
import unstudio.chinacraft.tileentity.TilePotteryKiln;
import unstudio.chinacraft.tileentity.TileSericultureFrame;
import unstudio.chinacraft.util.GuiHandler;
import unstudio.chinacraft.world.gen.WorldGenMulberryTree;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
    	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        config.save();
        
		ChinaCraft.bronzeHelmet =  new BronzeHelmet();//青铜头盔
		ChinaCraft.bronzeChestplate =  new BronzeChestplate();//青铜胸甲
		ChinaCraft.bronzeLeggings =  new BronzeLeggings();//青铜护腿
		ChinaCraft.bronzeBoots =  new BronzeBoots();//青铜靴子
    }
 
    public void init(FMLInitializationEvent event) {
    	NetworkRegistry.INSTANCE.registerGuiHandler(ChinaCraft.instance, new GuiHandler());

    	GameRegistry.registerBlock(ChinaCraft.copperOre, "CopperOre");
    	GameRegistry.registerWorldGenerator(ChinaCraft.copperOre, 3);
    	OreDictionary.registerOre("copperOre", ChinaCraft.copperOre);
    	GameRegistry.registerBlock(ChinaCraft.bronzeBlock, "BronzeBlock");
    	GameRegistry.registerBlock(ChinaCraft.tinOre, "TinOre");
    	OreDictionary.registerOre("tinOre", ChinaCraft.tinOre);
    	GameRegistry.registerWorldGenerator(ChinaCraft.tinOre, 3);
    	GameRegistry.registerBlock(ChinaCraft.jadeOre, "JadeOre");
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
    	OreDictionary.registerOre("silverOre", ChinaCraft.silverOre);
    	GameRegistry.registerBlock(ChinaCraft.riceGrow, "RiceGrow");
    	GameRegistry.registerBlock(ChinaCraft.woodenWindow1, "WoodenWindow1");//木窗户
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.woodenWindow1, 4), new Object[]{" A ","A#A"," A ",'A', Item.getItemFromBlock(Blocks.planks),'#',ChinaCraft.artKnife});
    	GameRegistry.registerBlock(ChinaCraft.woodenWindow2, "WoodenWindow2");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.woodenWindow2, 4), new Object[]{"###","#A#","###",'A', ChinaCraft.artKnife,'#',Items.stick});
    	GameRegistry.registerBlock(ChinaCraft.woodenWindow3, "WoodenWindow3");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.woodenWindow3, 4), new Object[]{"B#B","#A#","B#B",'A', ChinaCraft.artKnife,'#',Items.stick,'B',Item.getItemFromBlock(Blocks.log)});
    	GameRegistry.registerBlock(ChinaCraft.woodenWindow4, "WoodenWindow4");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.woodenWindow4, 4), new Object[]{" # ","#A#"," # ",'A', ChinaCraft.artKnife,'#',Items.stick});
    	GameRegistry.registerBlock(ChinaCraft.woodenWindowdragon, "WoodenWindowDragon");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.woodenWindowdragon, 1), new Object[]{"HHH","B#A","HHH",'H', Item.getItemFromBlock(Blocks.log),'#',ChinaCraft.artKnife,'A',ChinaCraft.woodenWindow4,'B',ChinaCraft.woodenWindow2});
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
		GameRegistry.registerTileEntity(TileBuhrimill.class, "tileEntityBuhrimill");
    	
    	GameRegistry.registerBlock(ChinaCraft.lantern, "Lantern");
    	GameRegistry.registerBlock(ChinaCraft.cooker_on, "CookerOn");
    	GameRegistry.registerBlock(ChinaCraft.cooker_off, "CookerOff");
    	GameRegistry.registerTileEntity(TileCooker.class, "tileEntityCooker");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.cooker_off, 1), new Object[]{"###", "# #", "# #", '#', Item.getItemFromBlock(Blocks.cobblestone)});
    	
    	GameRegistry.registerBlock(ChinaCraft.blockWoodenBucket, "BlockWoodenBucket");
		GameRegistry.addRecipe(new ItemStack(ChinaCraft.blockWoodenBucket, 1), new Object[]{"# #", " # ", "   ", '#', Item.getItemFromBlock(Blocks.wooden_slab)});
		GameRegistry.addRecipe(new ItemStack(ChinaCraft.blockWoodenBucket, 1), new Object[]{"   ", "# #", " # ", '#', Item.getItemFromBlock(Blocks.wooden_slab)});

    	GameRegistry.registerBlock(ChinaCraft.sericultureFrame, "SericultureFrame");
    	GameRegistry.registerTileEntity(TileSericultureFrame.class, "tileEntitySericultureFrame");
    	
    	GameRegistry.registerBlock(ChinaCraft.potteryTable, "PotteryTable");
    	
    	GameRegistry.registerItem(ChinaCraft.bronzeIngot, "BronzeIngot");//青铜锭
    	GameRegistry.registerItem(ChinaCraft.copperTinMixedPowder, "CopperTinMixedPowder");
    	GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.copperTinMixedPowder, 4), ChinaCraft.copperOre, ChinaCraft.copperOre, ChinaCraft.copperOre, ChinaCraft.tinOre);
    	GameRegistry.addSmelting(ChinaCraft.copperTinMixedPowder, new ItemStack(ChinaCraft.bronzeIngot), 0.8f);
    	GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.bronzeBlock, 1), ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot);
    	GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.bronzeIngot, 9), Item.getItemFromBlock(ChinaCraft.bronzeBlock));
    	GameRegistry.registerItem(ChinaCraft.silverIngot, "SilverIngot");//银锭
    	GameRegistry.addSmelting(ChinaCraft.silverOre, new ItemStack(ChinaCraft.silverIngot), 1.0f);
    	GameRegistry.registerItem(ChinaCraft.itemBuhrimill, "ItemBuhrimill");
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.itemBuhrimill, 1), new Object[]{" #X", "&#&", "&&&", '#', Item.getItemFromBlock(Blocks.cobblestone), 'X', Items.stick, '&', Item.getItemFromBlock(Blocks.stone)});
    	GameRegistry.registerItem(ChinaCraft.bronzeSword, "BronzeSword");//青铜剑
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeSword, 1), new Object[]{" # ", " # ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.bronzeBroadSword, "BronzeBroadSword");//青铜大刀
    	GameRegistry.registerItem(ChinaCraft.bronzeBroadSwordGreen, "bronzeBroadSwordGreen");//青铜大刀
    	GameRegistry.registerItem(ChinaCraft.bronzeBroadSwordGreen2, "bronzeBroadSwordGreen2");//青铜大刀
    	GameRegistry.registerItem(ChinaCraft.bronzeBroadSwordPink, "bronzeBroadSwordPink");//青铜大刀
    	GameRegistry.registerItem(ChinaCraft.bronzeBroadSwordPurple, "bronzeBroadSwordPurple");//青铜大刀
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.jiuqu_tang, 1), new Object[]{" # ", " # ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.jiuqu_tang, "JiuQuTang");//九曲镋刀
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeBroadSword, 1), new Object[]{" ##", "## ", "X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.yanLung_Giantknife, "YanLungGiantknife");//炎龙巨刀
    	GameRegistry.registerItem(ChinaCraft.bronzePickaxe, "BronzePickaxe");//青铜稿
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzePickaxe, 1), new Object[]{"###", " X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.bronzeAxe, "BronzeAxe");//青铜斧
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeAxe, 1), new Object[]{"## ", "#X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeAxe, 1), new Object[]{" ##", " X#", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.bronzeHoe, "BronzeHoe");//青铜锄
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeHoe, 1), new Object[]{"## ", " X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeHoe, 1), new Object[]{" ##", " X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
    	GameRegistry.registerItem(ChinaCraft.bronzeShovel, "BronzeShovel");//青铜铲
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeShovel, 1), new Object[]{" # ", " X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
		GameRegistry.registerItem(ChinaCraft.hammerStone, "StoneHammer");//石锤
		GameRegistry.addRecipe(new ItemStack(ChinaCraft.hammerStone, 1), new Object[]{"###", "#X#", " X ", '#', Item.getItemFromBlock(Blocks.cobblestone), 'X', Items.stick});
		GameRegistry.registerItem(ChinaCraft.hammerIron, "IronHammer");//铁锤
		GameRegistry.addRecipe(new ItemStack(ChinaCraft.hammerIron, 1), new Object[]{"###", "#X#", " X ", '#', Items.iron_ingot, 'X', Items.stick});
		GameRegistry.registerItem(ChinaCraft.hammerBronze, "DiamondBronze");//Bronze锤
		GameRegistry.addRecipe(new ItemStack(ChinaCraft.hammerBronze, 1), new Object[]{"###", "#X#", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
		GameRegistry.registerItem(ChinaCraft.hammerDiamond, "DiamondHammer");//钻石锤
		GameRegistry.addRecipe(new ItemStack(ChinaCraft.hammerDiamond, 1), new Object[]{"###", "#X#", " X ", '#', Items.diamond, 'X', Items.stick});

    	GameRegistry .registerItem(ChinaCraft.tinIngot, "TinIngot");//锡锭
    	GameRegistry.addSmelting(ChinaCraft.tinOre, new ItemStack(ChinaCraft.tinIngot), 0.8f);
    	GameRegistry .registerItem(ChinaCraft.rices, "Rices");//米
    	GameRegistry .registerItem(ChinaCraft.lcker, "Lckers");//水稻
    	GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.rices, 2), new Object[]{ChinaCraft.lcker});
    	GameRegistry .registerItem(ChinaCraft.soy, "Soy"); //大豆
    	GameRegistry.registerItem(ChinaCraft.soyPod, "SoyPod");//大豆荚
    	GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.soy, 2), new Object[]{ChinaCraft.soyPod});
    	GameRegistry.registerItem(ChinaCraft.bamboo, "Bamboo");//竹子
    	GameRegistry.addSmelting(ChinaCraft.bamboo, new ItemStack(ChinaCraft.mulberrySapling), 1.2f);
    	GameRegistry.registerItem(ChinaCraft.itemMulberryLeaf, "ItemMulberryLeaf");//桑叶
    	GameRegistry.registerItem(ChinaCraft.woodenBucket, "WoodenBucket");//木桶
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.woodenBucket, 1), new Object[]{"   ", "# #", " # ", '#', Item.getItemFromBlock(Blocks.log)});
    	GameRegistry.registerItem(ChinaCraft.woodenBucket_Water, "WoodenBucket_Water");//木桶 (水)
    	GameRegistry.registerItem(ChinaCraft.silkworm, "Silkworm");//蚕
    	GameRegistry.registerItem(ChinaCraft.silkwormChrysalis, "SilkwormChrysalis");
    	GameRegistry.registerItem(ChinaCraft.redPacket, "RedPacket");//红包
    	GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.redPacket, 1), new Object[]{Items.paper, new ItemStack(Items.dye, 1, 1)});
		GameRegistry.registerItem(ChinaCraft.blackDogBlood, "BlackDogBlood");//黑狗血
		GameRegistry.registerItem(ChinaCraft.moonCake, "MoonCake");//月饼
		GameRegistry.registerItem(ChinaCraft.artKnife, "ArtKnife");//美工切割刀
		GameRegistry.addRecipe(new ItemStack(ChinaCraft.artKnife, 1), new Object[]{"   "," Z "," # ",'#',Items.stick,'Z',Items.iron_ingot});
    	//青铜套
    	GameRegistry.registerItem(ChinaCraft.bronzeHelmet, "BronzeHelmet");//青铜头盔
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeHelmet, 1), new Object[]{"###", "# #", "   ", '#', ChinaCraft.bronzeIngot});
    	GameRegistry.registerItem(ChinaCraft.bronzeChestplate, "BronzeChestplate");//青铜护胸
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeChestplate, 1), new Object[]{"# #", "###", "###", '#', ChinaCraft.bronzeIngot});
    	GameRegistry.registerItem(ChinaCraft.bronzeLeggings, "BronzeLeggings");//青铜护腿
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeLeggings, 1), new Object[]{"###", "# #", "# #", '#', ChinaCraft.bronzeIngot});
    	GameRegistry.registerItem(ChinaCraft.bronzeBoots, "BronzeBoots");//青铜鞋
    	GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeBoots, 1), new Object[]{"   ", "# #", "# #", '#', ChinaCraft.bronzeIngot});
    	
    	//耐火砖
		GameRegistry.registerBlock(ChinaCraft.blockFirebrickStructure, "blockFirebrickStructure");
		GameRegistry.registerTileEntity(TileFirebrickStructure.class, "tileFirebrickStructure");
		GameRegistry.registerBlock(ChinaCraft.blockPotteryKiln, "blockPotteryKiln");
		GameRegistry.registerTileEntity(TilePotteryKiln.class, "tilePotteryKiln");
		GameRegistry.registerBlock(ChinaCraft.blockFirebrick, "BlockFirebrick");//耐火砖方块
		GameRegistry.registerItem(ChinaCraft.firebrick, "Firebrick");//耐火砖物品
		GameRegistry.registerItem(ChinaCraft.claySandMixture,"ClaySandMixture");//粘土沙子混合物
		GameRegistry.addRecipe(new ItemStack(ChinaCraft.blockFirebrick), new Object[]{"   ", " ##", " ##", '#', ChinaCraft.firebrick});
		GameRegistry.addSmelting(ChinaCraft.claySandMixture, new ItemStack(ChinaCraft.firebrick), 0);
		GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.claySandMixture, 1), new Object[] {Items.clay_ball,Items.clay_ball,Item.getItemFromBlock(Blocks.sand)});
		
    	//Jade
    	GameRegistry.registerItem(ChinaCraft.jadeGreenItem, "GreenJade");
    	GameRegistry.registerItem(ChinaCraft.jadeGreen2Item, "Green2Jade");
    	GameRegistry.registerItem(ChinaCraft.jadePinkItem, "GreenPink");
    	GameRegistry.registerItem(ChinaCraft.jadePurpleItem, "JadePurple");
    	GameRegistry.registerItem(ChinaCraft.jadeKnife, "JadeKnife");//玉石切割刀
		GameRegistry.addRecipe(new ItemStack(ChinaCraft.jadeKnife, 1), new Object[]{" X ", "X#X", " # ", '#', Items.stick,'X', Items.iron_ingot });

    	//Drink、Food
//    	GameRegistry.registerItem(ChinaCraft.cup, "Cup");
//    	GameRegistry.registerItem(ChinaCraft.cup_Clay, "ClayCup");
//    	GameRegistry.registerItem(ChinaCraft.cupChocolate, "ChocolateDrink");
//		GameRegistry.registerItem(ChinaCraft.cupChrysanthemum, "ChrysanthemumDrink");
		GameRegistry.registerItem(ChinaCraft.cocoa, "Cocoa");
		GameRegistry.registerItem(ChinaCraft.ladyfinger, "Ladyfinger");
    	GameRegistry.registerItem(ChinaCraft.flour, "Flour");
    	GameRegistry.registerItem(ChinaCraft.riceFlour, "RiceFlour");
    	GameRegistry.registerItem(ChinaCraft.barleyRice, "BarleyRice");
    	GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.barleyRice), new ItemStack(Items.wheat));

    	//石磨合成
    	BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(ChinaCraft.barleyRice), null, new ItemStack(ChinaCraft.flour), null, 360);
    	BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(ChinaCraft.rices), null, new ItemStack(ChinaCraft.riceFlour), null, 360);
    	BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(Item.getItemFromBlock(Blocks.cocoa)), null, new ItemStack(ChinaCraft.cocoa), null, 180);
    	BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(Item.getItemFromBlock(ChinaCraft.copperOre)), new ItemStack(ChinaCraft.tinIngot), new ItemStack(ChinaCraft.copperTinMixedPowder), null, 720);
		//玉石工作台合成

		//spiritual_magic_figures
		GameRegistry.registerItem(ChinaCraft.spiritualMagicFigures, "SpiritualMagicFigures");
		GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.spiritualMagicFigures, 3), new Object[]{Items.paper, Items.dye});
		GameRegistry.registerItem(ChinaCraft.smfFire, "SpiritualMagicFiguresFire");
		GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfFire), new Object[]{ChinaCraft.spiritualMagicFigures, Items.magma_cream, Items.redstone, Items.glowstone_dust});
		GameRegistry.registerItem(ChinaCraft.smfNightVision, "SpiritualMagicFiguresNightVision");
		GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfFire), new Object[]{ChinaCraft.spiritualMagicFigures, Items.golden_carrot, Items.redstone, Items.glowstone_dust});
		GameRegistry.registerItem(ChinaCraft.smfPoison, "SpiritualMagicFiguresPoison");
		GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfFire), new Object[]{ChinaCraft.spiritualMagicFigures, Items.spider_eye, Items.redstone, Items.glowstone_dust});
		GameRegistry.registerItem(ChinaCraft.smfPower, "SpiritualMagicFiguresPower");
		GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfFire), new Object[]{ChinaCraft.spiritualMagicFigures, Items.blaze_powder, Items.redstone, Items.glowstone_dust});
		GameRegistry.registerItem(ChinaCraft.smfHeal, "SpiritualMagicFiguresHeal");
		GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfFire), new Object[]{ChinaCraft.spiritualMagicFigures, Items.ghast_tear, Items.golden_apple, Items.redstone});
		GameRegistry.registerItem(ChinaCraft.smfProtect, "SpiritualMagicFiguresProtect");
		GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfProtect), new Object[]{ChinaCraft.spiritualMagicFigures, Items.potionitem});
		GameRegistry.registerItem(ChinaCraft.smfSuper, "SpiritualMagicFiguresSuper");
		GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfProtect), new Object[]{ChinaCraft.blackDogBlood, ChinaCraft.spiritualMagicFigures});

		EntityBlackDog.mainRegister();

		EntityChinaZombie.mainRegister();


		GameRegistry.registerItem(ChinaCraft.debug, "Debug");
    }

	public void postInit(FMLPostInitializationEvent event) {

	}
}
