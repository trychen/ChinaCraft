package unstudio.chinacraft.common;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

import unstudio.chinacraft.event.ListenerRegister;
import unstudio.chinacraft.item.ItemCCBlock;
import unstudio.chinacraft.tileentity.*;
import unstudio.chinacraft.common.config.*;
import unstudio.chinacraft.util.annotation.ItemBlockRegister;
import unstudio.chinacraft.world.gen.WorldGenCCFlower;
import unstudio.chinacraft.world.gen.WorldGenMulberryTree;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        new ConfigLoader(new Configuration(event.getSuggestedConfigurationFile()));

        new FeatureConfig();

        ChinaCraft.bronzeHelmet = (ItemArmor) new ItemArmor(ItemArmor.ArmorMaterial.IRON, ChinaCraft.bronzeArmorTexture,
                0).setUnlocalizedName("bronze_helmet").setMaxStackSize(1).setCreativeTab(ChinaCraft.tabTool);// 青铜头盔
        ChinaCraft.bronzeChestplate = (ItemArmor) new ItemArmor(ItemArmor.ArmorMaterial.IRON,
                ChinaCraft.bronzeArmorTexture, 1).setUnlocalizedName("bronze_body").setMaxStackSize(1)
                        .setCreativeTab(ChinaCraft.tabTool);// 青铜胸甲
        ChinaCraft.bronzeLeggings = (ItemArmor) new ItemArmor(ItemArmor.ArmorMaterial.IRON,
                ChinaCraft.bronzeArmorTexture, 2).setUnlocalizedName("bronze_legs").setMaxStackSize(1)
                        .setCreativeTab(ChinaCraft.tabTool);// 青铜护腿
        ChinaCraft.bronzeBoots = (ItemArmor) new ItemArmor(ItemArmor.ArmorMaterial.IRON, ChinaCraft.bronzeArmorTexture,
                3).setUnlocalizedName("bronze_boots").setMaxStackSize(1).setCreativeTab(ChinaCraft.tabTool);// 青铜靴子
    }

//    public static void onWailaCall(IWailaRegistrar registrar)
//    {
//        if (ChinaCraft.WAILAIsLoad)
//        registrar.registerBodyProvider(new BuhrimillWailaHandler(), BlockBuhrimill.class);
//    }

    public void init(FMLInitializationEvent event) {
        //注册主类为物品集合
        ItemBlockRegister.registerAll();

        //注册通讯
        NetworkRegistry.INSTANCE.registerGuiHandler(ChinaCraft.instance, new GuiHandler());

        //初始化监听器
        ListenerRegister.init();

//        GameRegistry.registerBlock(ChinaCraft.silverBlock, "SilverBlock");
//        GameRegistry.registerBlock(ChinaCraft.copperBlock, "CopperBlock");

//        GameRegistry.registerBlock(ChinaCraft.lanternScaldfish, "LanternScaldfish");
//        GameRegistry.registerBlock(ChinaCraft.lanternScaldfishOpenable, "lanternScaldfishOpenable");
//        GameRegistry.registerItem(ChinaCraft.itemLanternScaldfish, "itemLanternScaldfish");
//        GameRegistry.registerItem(ChinaCraft.itemLanternScaldfishOpenable, "itemLanternScaldfishOpenable");
        GameRegistry.registerWorldGenerator(ChinaCraft.copperOre, 3);
        GameRegistry.registerWorldGenerator(ChinaCraft.tinOre, 3);

//        OreDictionary.registerOre("oreTin", ChinaCraft.tinOre);
//        GameRegistry.registerBlock(ChinaCraft.silverOre, "SilverOre");
//        GameRegistry.registerWorldGenerator(ChinaCraft.silverOre, 3);
//        OreDictionary.registerOre("oreSilver", ChinaCraft.silverOre);
//        GameRegistry.registerBlock(ChinaCraft.jadeOre, "JadeOre");
//        OreDictionary.registerOre("oreJade", ChinaCraft.jadeOre);
        GameRegistry.registerWorldGenerator(ChinaCraft.jadeOre, 3);
//        GameRegistry.registerBlock(ChinaCraft.blockMarble, "BlockMarble");
        GameRegistry.registerWorldGenerator(ChinaCraft.blockMarble, 127);
//        GameRegistry.registerBlock(ChinaCraft.marbleStair, "MarbleStair");
//        GameRegistry.registerBlock(ChinaCraft.marbleSlab, ItemCCSlab.class,"MarbleSlab",ChinaCraft.marbleSlab,ChinaCraft.marbleDoubleSlab,false);
//        GameRegistry.registerBlock(ChinaCraft.marbleDoubleSlab,  ItemCCSlab.class,"MarbleDoubleSlab",ChinaCraft.marbleSlab,ChinaCraft.marbleDoubleSlab,true);
//        GameRegistry.registerBlock(ChinaCraft.smoothMarble, "SmoothMarble");
//        GameRegistry.registerBlock(ChinaCraft.pillarMarble, "PillarMarble");
//        GameRegistry.registerBlock(ChinaCraft.chiseledMarble, "ChiseledMarble");
//        GameRegistry.registerBlock(ChinaCraft.riceGrow, "RiceGrow");
//        GameRegistry.registerBlock(ChinaCraft.woodenWindow1, "WoodenWindow1");// 木窗户
//        GameRegistry.registerBlock(ChinaCraft.woodenWindow2, "WoodenWindow2");
//        GameRegistry.registerBlock(ChinaCraft.woodenWindow3, "WoodenWindow3");
//        GameRegistry.registerBlock(ChinaCraft.woodenWindow4, "WoodenWindow4");
//        GameRegistry.registerBlock(ChinaCraft.woodenWindowdragon, "WoodenWindowDragon"); // 龙腾木窗格
//        GameRegistry.registerBlock(ChinaCraft.woodenWindowfu, "WoodenWindowFu"); // 福字木窗格
//        GameRegistry.registerBlock(ChinaCraft.soyGrow, "SoyGrow");
//        GameRegistry.registerBlock(ChinaCraft.bamboo, "Bamboo"); // 竹子
//        GameRegistry.registerBlock(ChinaCraft.bambooPlank, "BambooPlank"); // 竹木板
//        GameRegistry.registerBlock(ChinaCraft.azalea, "Azalea");
//        GameRegistry.registerBlock(ChinaCraft.peony, "Peony");
//        GameRegistry.registerBlock(ChinaCraft.chrysanthemum, "chrysanthemum");
        GameRegistry.registerWorldGenerator(new WorldGenCCFlower(), 1);
//        GameRegistry.registerItem(ChinaCraft.glutinousRice, "GlutinousRice");
//        GameRegistry.registerBlock(ChinaCraft.blockGlutinousRice, "BlockGlutinousRice");
//        GameRegistry.registerBlock(ChinaCraft.mulberryLog, "MulberryLog");
//        GameRegistry.registerBlock(ChinaCraft.mulberryLeaf, "MulberryLeaf");
//        GameRegistry.registerBlock(ChinaCraft.mulberrySapling, "MulberrySapling");
//        GameRegistry.registerBlock(ChinaCraft.mulberryWood, "MulberryWood");
        GameRegistry.registerWorldGenerator(new WorldGenMulberryTree(true), 1);
//        GameRegistry.registerBlock(ChinaCraft.blockitemBambooBambooShoot, "BlockBambooShoot");
        GameRegistry.registerWorldGenerator(ChinaCraft.blockBambooShoot, 1);
//        GameRegistry.registerBlock(ChinaCraft.blackbrickBlock,"BlackBrickBlock");//青砖块
//        GameRegistry.registerBlock(ChinaCraft.blackbrickSlab,ItemCCSlab.class,"BlackBrickSlab", ChinaCraft.blackbrickSlab, ChinaCraft.blackbrickDoubleSlab,false);
//        GameRegistry.registerBlock(ChinaCraft.blackbrickDoubleSlab,ItemCCSlab.class,"BlackBrickDoubleSlab", ChinaCraft.blackbrickSlab, ChinaCraft.blackbrickDoubleSlab,true);
//        GameRegistry.registerBlock(ChinaCraft.blackbrickStair,"BlackBrickStair");

//        GameRegistry.registerBlock(ChinaCraft.jadeWorkingTable, "JadeWorkingTable"); // 玉石工作台
        GameRegistry.registerTileEntity(TileJadeBench.class, "tileEntityJadeWorkingTable"); // 玉石工作台TileEntity

//!        GameRegistry.registerBlock(ChinaCraft.blockDrum, "Drum");
        GameRegistry.registerTileEntity(TileDrum.class, "tileEntityDrum");

//        GameRegistry.registerBlock(ChinaCraft.buhrimill, ItemCCBlock.class, "Buhrimill"); // 石磨
        GameRegistry.registerTileEntity(TileBuhrimill.class, "tileEntityBuhrimill"); // 石磨TileEntity

//        GameRegistry.registerBlock(ChinaCraft.lantern, "Lantern"); // 灯笼
//        GameRegistry.registerBlock(ChinaCraft.cooking_bench_on, "CookingBenchOn");
//        GameRegistry.registerBlock(ChinaCraft.cooking_bench_off, "CookingBenchOff");
        GameRegistry.registerTileEntity(TileCookingBench.class, "tileEntityCookingBench");

        GameRegistry.registerBlock(ChinaCraft.sericultureFrame, ItemCCBlock.class, "SericultureFrame"); // 养蚕架
        GameRegistry.registerTileEntity(TileSericultureFrame.class, "tileEntitySericultureFrame"); // 养蚕架TileEntity

//        GameRegistry.registerBlock(ChinaCraft.potteryTable, ItemCCBlock.class, "PotteryTable"); // 陶瓷工作台
        GameRegistry.registerTileEntity(TilePotteryTable.class, "tileEntityPotteryTable");

//        GameRegistry.registerBlock(ChinaCraft.blockPotteryBase, "Pottery"); // 陶瓷
//        GameRegistry.registerBlock(ChinaCraft.blockBuckpot, "Buckpot"); // 陶罐

//        GameRegistry.registerItem(ChinaCraft.saltBucket, "SaltBucket");
//        GameRegistry.registerItem(ChinaCraft.douJiangBucket, "DouJiangBucket");
//        GameRegistry.registerItem(ChinaCraft.copperIngot, "CopperIngot");// 铜锭
//        OreDictionary.registerOre("ingotCopper", ChinaCraft.copperIngot);
//        GameRegistry.registerItem(ChinaCraft.bronzeIngot, "BronzeIngot");// 青铜锭
//        OreDictionary.registerOre("ingotBronze", ChinaCraft.bronzeIngot);
//        GameRegistry.registerItem(ChinaCraft.tinPowder, "TinPowder");
//        OreDictionary.registerOre("dustTinsilkworm", ChinaCraft.tinPowder);
//        GameRegistry.registerItem(ChinaCraft.copperPowder, "CopperPowder");
//        OreDictionary.registerOre("dustCopper", ChinaCraft.copperPowder);
//        GameRegistry.registerItem(ChinaCraft.copperTinMixedPowder, "CopperTinMixedPowder");
//        GameRegistry.registerItem(ChinaCraft.silverIngot, "SilverIngot");// 银锭
//        OreDictionary.registerOre("ingotSilver", ChinaCraft.silverOre);
//        GameRegistry.registerItem(ChinaCraft.tinIngot, "TinIngot");// 锡锭
//        OreDictionary.registerOre("ingotTin", ChinaCraft.tinIngot);
//        GameRegistry.registerItem(ChinaCraft.blackbrick, "BlackBrick");//青砖

//        GameRegistry.registerItem(ChinaCraft.bronzeSword, "BronzeSword");// 青铜剑

//        GameRegistry.registerItem(ChinaCraft.bronzeBroadSword, "BronzeBroadSword");// 青铜大刀
//        GameRegistry.registerItem(ChinaCraft.bronzeBroadSwordGreen, "bronzeBroadSwordGreen");// 青铜大刀
//        GameRegistry.registerItem(ChinaCraft.bronzeBroadSwordGreen2, "bronzeBroadSwordGreen2");// 青铜大刀
//        GameRegistry.registerItem(ChinaCraft.bronzeBroadSwordPink, "bronzeBroadSwordPink");// 青铜大刀
//        GameRegistry.registerItem(ChinaCraft.bronzeBroadSwordPurple, "bronzeBroadSwordPurple");// 青铜大刀
//        GameRegistry.registerItem(ChinaCraft.jiuqu_tang, "JiuQuTang");// 九曲镋刀
//        GameRegistry.registerItem(ChinaCraft.mace, "Mace");
//        GameRegistry.registerItem(ChinaCraft.blGiantSword, "YanLungGiantknife");// 炎龙巨刀
//        GameRegistry.registerItem(ChinaCraft.bronzePickaxe, "BronzePickaxe");// 青铜稿
//        GameRegistry.registerItem(ChinaCraft.bronzeAxe, "BronzeAxe");// 青铜斧
//        GameRegistry.registerItem(ChinaCraft.bronzeHoe, "BronzeHoe");// 青铜锄
//        GameRegistry.registerItem(ChinaCraft.bronzeShovel, "BronzeShovel");// 青铜铲
//        GameRegistry.registerItem(ChinaCraft.hammerStone, "StoneHammer");// 石锤
//        GameRegistry.registerItem(ChinaCraft.hammerIron, "IronHammer");// 铁锤
//        GameRegistry.registerItem(ChinaCraft.hammerBronze, "DiamondBronze");// Bronze锤
//        GameRegistry.registerItem(ChinaCraft.hammerDiamond, "DiamondHammer");// 钻石锤
//        GameRegistry.registerItem(ChinaCraft.chinaCrown, "ChinaCrown");

        GameRegistry.registerItem(ChinaCraft.nightClothes[0], "NightClothesHead");
        GameRegistry.registerItem(ChinaCraft.nightClothes[1], "NightClothesBody");
        GameRegistry.registerItem(ChinaCraft.nightClothes[2], "NightClothesLeg");
        GameRegistry.registerItem(ChinaCraft.nightClothes[3], "NightClothesShoe");
//        GameRegistry.registerItem(ChinaCraft.rices, "Rices");// 米
//        GameRegistry.registerItem(ChinaCraft.lcker, "Lckers");// 水稻
//        GameRegistry.registerItem(ChinaCraft.soy, "Soy"); // 大豆
//        GameRegistry.registerItem(ChinaCraft.soyPod, "SoyPod");// 大豆荚
//        GameRegistry.registerItem(ChinaCraft.itemBamboo, "ItemBamboo");// 竹子
//        GameRegistry.registerItem(ChinaCraft.itemMulberryLeaf, "ItemMulberryLeaf");// 桑叶
//        GameRegistry.registerItem(ChinaCraft.woodenBucket, "WoodenBucket");// 木桶
//        GameRegistry.registerBlock(ChinaCraft.blockWoodenBucket, "BlockWoodenBucket");// 木桶(方块)
        // GameRegistry.addRecipe(new ItemStack(ChinaCraft.blockWoodenBucket,
        // 1), new Object[]{"# #", " # ", " ", '#',
        // Item.getItemFromBlock(Blocks.wooden_slab)});
        // GameRegistry.addRecipe(new ItemStack(ChinaCraft.blockWoodenBucket,
        // 1), new Object[]{" ", "# #", " # ", '#',
        // Item.getItemFromBlock(Blocks.wooden_slab)});
//        GameRegistry.registerItem(ChinaCraft.woodenBucket_Water, "WoodenBucket_Water");// 木桶
                                                                                       // (水)
//        GameRegistry.registerItem(ChinaCraft.silkworm, "Silkworm");// 蚕
//        GameRegistry.registerItem(ChinaCraft.silkwormChrysalis, "SilkwormChrysalis");
//        GameRegistry.registerItem(ChinaCraft.redPacket, "RedPacket");// 红包
//        GameRegistry.registerItem(ChinaCraft.firecracker, "Firecracker");
//        GameRegistry.registerItem(ChinaCraft.bomb, "Bomb");
//        GameRegistry.registerItem(ChinaCraft.blackDogBlood, "BlackDogBlood");// 黑狗血
//        GameRegistry.registerItem(ChinaCraft.moonCake, "MoonCake");// 月饼
//        GameRegistry.registerItem(ChinaCraft.artKnife, "ItemArtKnife");// 美工切割刀
//        GameRegistry.registerBlock(ChinaCraft.xinjiangNutCake, "XinjiangNutCake");
//        GameRegistry.registerItem(ChinaCraft.itemXinjiangNutCake, "ItemXinjiangNutCake");
//        GameRegistry.registerBlock(ChinaCraft.appleCake, "BLockAppleCake");//错误命名
//        GameRegistry.registerItem(ChinaCraft.itemAppleCake, "AppleCake");

        // 青铜套
//        GameRegistry.registerItem(ChinaCraft.bronzeHelmet, "BronzeHelmet");// 青铜头盔Mace

        // 耐火砖
//        GameRegistry.registerBlock(ChinaCraft.blockFirebrickStructure, "blockFirebrickStructure");
        GameRegistry.registerTileEntity(TileFirebrickStructure.class, "tileFirebrickStructure");
//        GameRegistry.registerBlock(ChinaCraft.blockPotteryKiln, "blockPotteryKiln");
        GameRegistry.registerTileEntity(TilePotteryKiln.class, "tilePotteryKiln");
//        GameRegistry.registerBlock(ChinaCraft.blockFirebrick, "BlockFirebrick");// 耐火砖方块
//        GameRegistry.registerItem(ChinaCraft.firebrick, "Firebrick");// 耐火砖物品
//        GameRegistry.registerItem(ChinaCraft.claySandMixture, "ClaySandMixture");// 粘土沙子混合物

        // Jade
//        GameRegistry.registerItem(ChinaCraft.jadeGreenItem, "JadeGreen");
//        GameRegistry.registerItem(ChinaCraft.jadeGreen2Item, "JadeGreen2");
//        GameRegistry.registerItem(ChinaCraft.jadePinkItem, "JadePink");
//        GameRegistry.registerItem(ChinaCraft.jadePurpleItem, "JadePurple");
//        GameRegistry.registerItem(ChinaCraft.jadeKnife, "JadeKnife");// 玉石切割刀

        // Drink、Food
        // GameRegistry.registerItem(ChinaCraft.cup, "Cup");
        // GameRegistry.registerItem(ChinaCraft.cup_Clay, "ClayCup");
        // GameRegistry.registerItem(ChinaCraft.cupChocolate, "ChocolateDrink");
        // GameRegistry.registerItem(ChinaCraft.cupChrysanthemum,
        // "ChrysanthemumDrink");
//        GameRegistry.registerItem(ChinaCraft.cocoa, "Cocoa");
//        GameRegistry.registerItem(ChinaCraft.ladyfinger, "Ladyfinger");
//        GameRegistry.registerItem(ChinaCraft.flour, "Flour");
//        GameRegistry.registerItem(ChinaCraft.riceFlour, "RiceFlour");
//        GameRegistry.registerItem(ChinaCraft.barleyRice, "BarleyRice");
//        GameRegistry.registerItem(ChinaCraft.salt, "Salt");

        // spiritual_magic_figures
//        GameRegistry.registerItem(ChinaCraft.spiritualMagicFigures, "SpiritualMagicFigures");
//        GameRegistry.registerItem(ChinaCraft.smfFire, "SpiritualMagicFiguresFire");
//        GameRegistry.registerItem(ChinaCraft.smfNightVision, "SpiritualMagicFiguresNightVision");
//        GameRegistry.registerItem(ChinaCraft.smfPoison, "SpiritualMagicFiguresPoison");
//        GameRegistry.registerItem(ChinaCraft.smfPower, "SpiritualMagicFiguresPower");
//        GameRegistry.registerItem(ChinaCraft.smfHeal, "SpiritualMagicFiguresHeal");
//        GameRegistry.registerItem(ChinaCraft.smfProtect, "SpiritualMagicFiguresProtect");
//        GameRegistry.registerItem(ChinaCraft.smfSuper, "SpiritualMagicFiguresSuper");

//        GameRegistry.registerBlock(ChinaCraft.redCarpet, "red_carpet");
//        GameRegistry.registerBlock(ChinaCraft.silkCarpet, "silk_carpet");

        // disc
//        GameRegistry.registerItem(ChinaCraft.three_stanzas, "three_stanzas_of_plum-blossoms");
//        GameRegistry.registerItem(ChinaCraft.mountain_stream, "mountain_stream");
//        GameRegistry.registerItem(ChinaCraft.the_march_of_the_volunteers, "the_march_of_the_volunteers");
//        GameRegistry.registerItem(ChinaCraft.spring_festival_overture, "spring_festival_overture");

//        GameRegistry.registerItem(ChinaCraft.itemSilk, "silk");
//        GameRegistry.registerItem(ChinaCraft.silkYarn, "silk_yarn");
//
//        GameRegistry.registerItem(ChinaCraft.debug, "Debug");

        Recipes.init();

        FluidContainerRegistry.registerFluidContainer(FluidRegistry.WATER,new ItemStack(ChinaCraft.woodenBucket_Water),new ItemStack(ChinaCraft.woodenBucket));
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
