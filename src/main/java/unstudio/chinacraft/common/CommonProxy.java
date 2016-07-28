package unstudio.sinocraft.common;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import unstudio.sinocraft.event.ListenerRegister;
import unstudio.sinocraft.item.ItemCCBlock;
import unstudio.sinocraft.item.ItemCCSlab;
import unstudio.sinocraft.tileentity.*;
import unstudio.sinocraft.common.config.*;
import unstudio.sinocraft.util.annotation.ItemBlockRegister;
import unstudio.sinocraft.world.gen.WorldGenCCFlower;
import unstudio.sinocraft.world.gen.WorldGenMulberryTree;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        new ConfigLoader(new Configuration(event.getSuggestedConfigurationFile()));

        new FeatureConfig();

        SinoCraft.bronzeHelmet = (ItemArmor) new ItemArmor(ItemArmor.ArmorMaterial.IRON, SinoCraft.bronzeArmorTexture,
                0).setUnlocalizedName("bronze_helmet").setMaxStackSize(1).setCreativeTab(SinoCraft.tabTool);// 青铜头盔
        SinoCraft.bronzeChestplate = (ItemArmor) new ItemArmor(ItemArmor.ArmorMaterial.IRON,
                SinoCraft.bronzeArmorTexture, 1).setUnlocalizedName("bronze_body").setMaxStackSize(1)
                        .setCreativeTab(SinoCraft.tabTool);// 青铜胸甲
        SinoCraft.bronzeLeggings = (ItemArmor) new ItemArmor(ItemArmor.ArmorMaterial.IRON,
                SinoCraft.bronzeArmorTexture, 2).setUnlocalizedName("bronze_legs").setMaxStackSize(1)
                        .setCreativeTab(SinoCraft.tabTool);// 青铜护腿
        SinoCraft.bronzeBoots = (ItemArmor) new ItemArmor(ItemArmor.ArmorMaterial.IRON, SinoCraft.bronzeArmorTexture,
                3).setUnlocalizedName("bronze_boots").setMaxStackSize(1).setCreativeTab(SinoCraft.tabTool);// 青铜靴子
    }

//    public static void onWailaCall(IWailaRegistrar registrar)
//    {
//        if (SinoCraft.WAILAIsLoad)
//        registrar.registerBodyProvider(new BuhrimillWailaHandler(), BlockBuhrimill.class);
//    }

    public void init(FMLInitializationEvent event) {
        //注册主类为物品集合
        ItemBlockRegister.registerAll();

        //注册通讯
        NetworkRegistry.INSTANCE.registerGuiHandler(SinoCraft.instance, new GuiHandler());

        //初始化监听器
        ListenerRegister.init();

//        GameRegistry.registerBlock(SinoCraft.silverBlock, "SilverBlock");
//        GameRegistry.registerBlock(SinoCraft.copperBlock, "CopperBlock");

//        GameRegistry.registerBlock(SinoCraft.lanternScaldfish, "LanternScaldfish");
//        GameRegistry.registerBlock(SinoCraft.lanternScaldfishOpenable, "lanternScaldfishOpenable");
//        GameRegistry.registerItem(SinoCraft.itemLanternScaldfish, "itemLanternScaldfish");
//        GameRegistry.registerItem(SinoCraft.itemLanternScaldfishOpenable, "itemLanternScaldfishOpenable");
        GameRegistry.registerWorldGenerator(SinoCraft.copperOre, 3);
        GameRegistry.registerWorldGenerator(SinoCraft.tinOre, 3);

//        OreDictionary.registerOre("oreTin", SinoCraft.tinOre);
//        GameRegistry.registerBlock(SinoCraft.silverOre, "SilverOre");
//        GameRegistry.registerWorldGenerator(SinoCraft.silverOre, 3);
//        OreDictionary.registerOre("oreSilver", SinoCraft.silverOre);
//        GameRegistry.registerBlock(SinoCraft.jadeOre, "JadeOre");
//        OreDictionary.registerOre("oreJade", SinoCraft.jadeOre);
        GameRegistry.registerWorldGenerator(SinoCraft.jadeOre, 3);
//        GameRegistry.registerBlock(SinoCraft.blockMarble, "BlockMarble");
        GameRegistry.registerWorldGenerator(SinoCraft.blockMarble, 127);
//        GameRegistry.registerBlock(SinoCraft.marbleStair, "MarbleStair");
//        GameRegistry.registerBlock(SinoCraft.marbleSlab, ItemCCSlab.class,"MarbleSlab",SinoCraft.marbleSlab,SinoCraft.marbleDoubleSlab,false);
//        GameRegistry.registerBlock(SinoCraft.marbleDoubleSlab,  ItemCCSlab.class,"MarbleDoubleSlab",SinoCraft.marbleSlab,SinoCraft.marbleDoubleSlab,true);
//        GameRegistry.registerBlock(SinoCraft.smoothMarble, "SmoothMarble");
//        GameRegistry.registerBlock(SinoCraft.pillarMarble, "PillarMarble");
//        GameRegistry.registerBlock(SinoCraft.chiseledMarble, "ChiseledMarble");
//        GameRegistry.registerBlock(SinoCraft.riceGrow, "RiceGrow");
//        GameRegistry.registerBlock(SinoCraft.woodenWindow1, "WoodenWindow1");// 木窗户
//        GameRegistry.registerBlock(SinoCraft.woodenWindow2, "WoodenWindow2");
//        GameRegistry.registerBlock(SinoCraft.woodenWindow3, "WoodenWindow3");
//        GameRegistry.registerBlock(SinoCraft.woodenWindow4, "WoodenWindow4");
//        GameRegistry.registerBlock(SinoCraft.woodenWindowdragon, "WoodenWindowDragon"); // 龙腾木窗格
//        GameRegistry.registerBlock(SinoCraft.woodenWindowfu, "WoodenWindowFu"); // 福字木窗格
        GameRegistry.registerBlock(SinoCraft.soyGrow, "SoyGrow");
//        GameRegistry.registerBlock(SinoCraft.bamboo, "Bamboo"); // 竹子
//        GameRegistry.registerBlock(SinoCraft.bambooPlank, "BambooPlank"); // 竹木板
        GameRegistry.registerBlock(SinoCraft.azalea, "Azalea");
        GameRegistry.registerBlock(SinoCraft.peony, "Peony");
        GameRegistry.registerBlock(SinoCraft.chrysanthemum, "chrysanthemum");
        GameRegistry.registerWorldGenerator(new WorldGenCCFlower(), 1);
        GameRegistry.registerItem(SinoCraft.glutinousRice, "GlutinousRice");
        GameRegistry.registerBlock(SinoCraft.blockGlutinousRice, "BlockGlutinousRice");
        GameRegistry.registerBlock(SinoCraft.mulberryLog, "MulberryLog");
        GameRegistry.registerBlock(SinoCraft.mulberryLeaf, "MulberryLeaf");
        GameRegistry.registerBlock(SinoCraft.mulberrySapling, "MulberrySapling");
        GameRegistry.registerBlock(SinoCraft.mulberryWood, "MulberryWood");
        GameRegistry.registerWorldGenerator(new WorldGenMulberryTree(true), 1);
        GameRegistry.registerBlock(SinoCraft.blockBambooShoot, "BlockBambooShoot");
        GameRegistry.registerWorldGenerator(SinoCraft.blockBambooShoot, 1);
        GameRegistry.registerBlock(SinoCraft.blackbrickBlock,"BlackBrickBlock");//青砖块
        GameRegistry.registerBlock(SinoCraft.blackbrickSlab,ItemCCSlab.class,"BlackBrickSlab", SinoCraft.blackbrickSlab, SinoCraft.blackbrickDoubleSlab,false);
        GameRegistry.registerBlock(SinoCraft.blackbrickDoubleSlab,ItemCCSlab.class,"BlackBrickDoubleSlab", SinoCraft.blackbrickSlab, SinoCraft.blackbrickDoubleSlab,true);
        GameRegistry.registerBlock(SinoCraft.blackbrickStair,"BlackBrickStair");

        GameRegistry.registerBlock(SinoCraft.jadeWorkingTable, "JadeWorkingTable"); // 玉石工作台
        GameRegistry.registerTileEntity(TileJadeBench.class, "tileEntityJadeWorkingTable"); // 玉石工作台TileEntity

//!        GameRegistry.registerBlock(SinoCraft.blockDrum, "Drum");
        GameRegistry.registerTileEntity(TileDrum.class, "tileEntityDrum");

        GameRegistry.registerBlock(SinoCraft.buhrimill, ItemCCBlock.class, "Buhrimill"); // 石磨
        GameRegistry.registerTileEntity(TileBuhrimill.class, "tileEntityBuhrimill"); // 石磨TileEntity

//        GameRegistry.registerBlock(SinoCraft.lantern, "Lantern"); // 灯笼
        GameRegistry.registerBlock(SinoCraft.cooking_bench_on, "CookingBenchOn");
        GameRegistry.registerBlock(SinoCraft.cooking_bench_off, "CookingBenchOff");
        GameRegistry.registerTileEntity(TileCookingBench.class, "tileEntityCookingBench");

        GameRegistry.registerBlock(SinoCraft.sericultureFrame, ItemCCBlock.class, "SericultureFrame"); // 养蚕架
        GameRegistry.registerTileEntity(TileSericultureFrame.class, "tileEntitySericultureFrame"); // 养蚕架TileEntity

        GameRegistry.registerBlock(SinoCraft.potteryTable, ItemCCBlock.class, "PotteryTable"); // 陶瓷工作台
        GameRegistry.registerTileEntity(TilePotteryTable.class, "tileEntityPotteryTable");

//        GameRegistry.registerBlock(SinoCraft.blockPotteryBase, "Pottery"); // 陶瓷
//        GameRegistry.registerBlock(SinoCraft.blockBuckpot, "Buckpot"); // 陶罐

//        GameRegistry.registerItem(SinoCraft.saltBucket, "SaltBucket");
        GameRegistry.registerItem(SinoCraft.douJiangBucket, "DouJiangBucket");
//        GameRegistry.registerItem(SinoCraft.copperIngot, "CopperIngot");// 铜锭
//        OreDictionary.registerOre("ingotCopper", SinoCraft.copperIngot);
//        GameRegistry.registerItem(SinoCraft.bronzeIngot, "BronzeIngot");// 青铜锭
//        OreDictionary.registerOre("ingotBronze", SinoCraft.bronzeIngot);
        GameRegistry.registerItem(SinoCraft.tinPowder, "TinPowder");
        OreDictionary.registerOre("dustTin", SinoCraft.tinPowder);
        GameRegistry.registerItem(SinoCraft.copperPowder, "CopperPowder");
        OreDictionary.registerOre("dustCopper", SinoCraft.copperPowder);
        GameRegistry.registerItem(SinoCraft.copperTinMixedPowder, "CopperTinMixedPowder");
//        GameRegistry.registerItem(SinoCraft.silverIngot, "SilverIngot");// 银锭
//        OreDictionary.registerOre("ingotSilver", SinoCraft.silverOre);
//        GameRegistry.registerItem(SinoCraft.tinIngot, "TinIngot");// 锡锭
//        OreDictionary.registerOre("ingotTin", SinoCraft.tinIngot);
        GameRegistry.registerItem(SinoCraft.blackbrick, "BlackBrick");//青砖

        GameRegistry.registerItem(SinoCraft.bronzeSword, "BronzeSword");// 青铜剑

        GameRegistry.registerItem(SinoCraft.bronzeBroadSword, "BronzeBroadSword");// 青铜大刀
        GameRegistry.registerItem(SinoCraft.bronzeBroadSwordGreen, "bronzeBroadSwordGreen");// 青铜大刀
        GameRegistry.registerItem(SinoCraft.bronzeBroadSwordGreen2, "bronzeBroadSwordGreen2");// 青铜大刀
        GameRegistry.registerItem(SinoCraft.bronzeBroadSwordPink, "bronzeBroadSwordPink");// 青铜大刀
        GameRegistry.registerItem(SinoCraft.bronzeBroadSwordPurple, "bronzeBroadSwordPurple");// 青铜大刀
        GameRegistry.registerItem(SinoCraft.jiuqu_tang, "JiuQuTang");// 九曲镋刀
        GameRegistry.registerItem(SinoCraft.mace, "Mace");
        GameRegistry.registerItem(SinoCraft.blGiantSword, "YanLungGiantknife");// 炎龙巨刀
        GameRegistry.registerItem(SinoCraft.bronzePickaxe, "BronzePickaxe");// 青铜稿
        GameRegistry.registerItem(SinoCraft.bronzeAxe, "BronzeAxe");// 青铜斧
        GameRegistry.registerItem(SinoCraft.bronzeHoe, "BronzeHoe");// 青铜锄
        GameRegistry.registerItem(SinoCraft.bronzeShovel, "BronzeShovel");// 青铜铲
        GameRegistry.registerItem(SinoCraft.hammerStone, "StoneHammer");// 石锤
        GameRegistry.registerItem(SinoCraft.hammerIron, "IronHammer");// 铁锤
        GameRegistry.registerItem(SinoCraft.hammerBronze, "DiamondBronze");// Bronze锤
        GameRegistry.registerItem(SinoCraft.hammerDiamond, "DiamondHammer");// 钻石锤
        GameRegistry.registerItem(SinoCraft.chinaCrown, "ChinaCrown");

        GameRegistry.registerItem(SinoCraft.nightClothes[0], "NightClothesHead");
        GameRegistry.registerItem(SinoCraft.nightClothes[1], "NightClothesBody");
        GameRegistry.registerItem(SinoCraft.nightClothes[2], "NightClothesLeg");
        GameRegistry.registerItem(SinoCraft.nightClothes[3], "NightClothesShoe");
        GameRegistry.registerItem(SinoCraft.rices, "Rices");// 米
        GameRegistry.registerItem(SinoCraft.lcker, "Lckers");// 水稻
        GameRegistry.registerItem(SinoCraft.soy, "Soy"); // 大豆
        GameRegistry.registerItem(SinoCraft.soyPod, "SoyPod");// 大豆荚
        GameRegistry.registerItem(SinoCraft.itemBamboo, "ItemBamboo");// 竹子
        GameRegistry.registerItem(SinoCraft.itemMulberryLeaf, "ItemMulberryLeaf");// 桑叶
        GameRegistry.registerItem(SinoCraft.woodenBucket, "WoodenBucket");// 木桶
        GameRegistry.registerBlock(SinoCraft.blockWoodenBucket, "BlockWoodenBucket");// 木桶(方块)
        // GameRegistry.addRecipe(new ItemStack(SinoCraft.blockWoodenBucket,
        // 1), new Object[]{"# #", " # ", " ", '#',
        // Item.getItemFromBlock(Blocks.wooden_slab)});
        // GameRegistry.addRecipe(new ItemStack(SinoCraft.blockWoodenBucket,
        // 1), new Object[]{" ", "# #", " # ", '#',
        // Item.getItemFromBlock(Blocks.wooden_slab)});
        GameRegistry.registerItem(SinoCraft.woodenBucket_Water, "WoodenBucket_Water");// 木桶
                                                                                       // (水)
        GameRegistry.registerItem(SinoCraft.silkworm, "Silkworm");// 蚕
        GameRegistry.registerItem(SinoCraft.silkwormChrysalis, "SilkwormChrysalis");
        GameRegistry.registerItem(SinoCraft.redPacket, "RedPacket");// 红包
        GameRegistry.registerItem(SinoCraft.firecracker, "Firecracker");
        GameRegistry.registerItem(SinoCraft.bomb, "Bomb");
        GameRegistry.registerItem(SinoCraft.blackDogBlood, "BlackDogBlood");// 黑狗血
        GameRegistry.registerItem(SinoCraft.moonCake, "MoonCake");// 月饼
        GameRegistry.registerItem(SinoCraft.artKnife, "ItemArtKnife");// 美工切割刀
        GameRegistry.registerBlock(SinoCraft.xinjiangNutCake, "XinjiangNutCake");
        GameRegistry.registerBlock(SinoCraft.appleCake, "BLockAppleCake");
        GameRegistry.registerItem(SinoCraft.itemAppleCake, "AppleCake");

        // 青铜套
        GameRegistry.registerItem(SinoCraft.bronzeHelmet, "BronzeHelmet");// 青铜头盔
        GameRegistry.registerItem(SinoCraft.bronzeChestplate, "BronzeChestplate");// 青铜护胸
        GameRegistry.registerItem(SinoCraft.bronzeLeggings, "BronzeLeggings");// 青铜护腿
        GameRegistry.registerItem(SinoCraft.bronzeBoots, "BronzeBoots");// 青铜鞋

        // 耐火砖
        GameRegistry.registerBlock(SinoCraft.blockFirebrickStructure, "blockFirebrickStructure");
        GameRegistry.registerTileEntity(TileFirebrickStructure.class, "tileFirebrickStructure");
        GameRegistry.registerBlock(SinoCraft.blockPotteryKiln, "blockPotteryKiln");
        GameRegistry.registerTileEntity(TilePotteryKiln.class, "tilePotteryKiln");
        GameRegistry.registerBlock(SinoCraft.blockFirebrick, "BlockFirebrick");// 耐火砖方块
        GameRegistry.registerItem(SinoCraft.firebrick, "Firebrick");// 耐火砖物品
        GameRegistry.registerItem(SinoCraft.claySandMixture, "ClaySandMixture");// 粘土沙子混合物

        // Jade
        GameRegistry.registerItem(SinoCraft.jadeGreenItem, "JadeGreen");
        GameRegistry.registerItem(SinoCraft.jadeGreen2Item, "JadeGreen2");
        GameRegistry.registerItem(SinoCraft.jadePinkItem, "JadePink");
        GameRegistry.registerItem(SinoCraft.jadePurpleItem, "JadePurple");
        GameRegistry.registerItem(SinoCraft.jadeKnife, "JadeKnife");// 玉石切割刀

        // Drink、Food
        // GameRegistry.registerItem(SinoCraft.cup, "Cup");
        // GameRegistry.registerItem(SinoCraft.cup_Clay, "ClayCup");
        // GameRegistry.registerItem(SinoCraft.cupChocolate, "ChocolateDrink");
        // GameRegistry.registerItem(SinoCraft.cupChrysanthemum,
        // "ChrysanthemumDrink");
        GameRegistry.registerItem(SinoCraft.cocoa, "Cocoa");
        GameRegistry.registerItem(SinoCraft.ladyfinger, "Ladyfinger");
        GameRegistry.registerItem(SinoCraft.flour, "Flour");
        GameRegistry.registerItem(SinoCraft.riceFlour, "RiceFlour");
        GameRegistry.registerItem(SinoCraft.barleyRice, "BarleyRice");
        GameRegistry.registerItem(SinoCraft.Salt, "Salt");

        // spiritual_magic_figures
        GameRegistry.registerItem(SinoCraft.spiritualMagicFigures, "SpiritualMagicFigures");
        GameRegistry.registerItem(SinoCraft.smfFire, "SpiritualMagicFiguresFire");
        GameRegistry.registerItem(SinoCraft.smfNightVision, "SpiritualMagicFiguresNightVision");
        GameRegistry.registerItem(SinoCraft.smfPoison, "SpiritualMagicFiguresPoison");
        GameRegistry.registerItem(SinoCraft.smfPower, "SpiritualMagicFiguresPower");
        GameRegistry.registerItem(SinoCraft.smfHeal, "SpiritualMagicFiguresHeal");
        GameRegistry.registerItem(SinoCraft.smfProtect, "SpiritualMagicFiguresProtect");
        GameRegistry.registerItem(SinoCraft.smfSuper, "SpiritualMagicFiguresSuper");

        GameRegistry.registerBlock(SinoCraft.redCarpet, "red_carpet");
//        GameRegistry.registerBlock(SinoCraft.silkCarpet, "silk_carpet");

        // disc
        GameRegistry.registerItem(SinoCraft.three_stanzas, "three_stanzas_of_plum-blossoms");
        GameRegistry.registerItem(SinoCraft.mountain_stream, "mountain_stream");
        GameRegistry.registerItem(SinoCraft.the_march_of_the_volunteers, "the_march_of_the_volunteers");
        GameRegistry.registerItem(SinoCraft.spring_festival_overture, "spring_festival_overture");

        GameRegistry.registerItem(SinoCraft.itemSilk, "silk");
        GameRegistry.registerItem(SinoCraft.silkYarn, "silk_yarn");

        GameRegistry.registerItem(SinoCraft.debug, "Debug");

        Recipes.init();

        FluidContainerRegistry.registerFluidContainer(FluidRegistry.WATER,new ItemStack(SinoCraft.woodenBucket_Water),new ItemStack(SinoCraft.woodenBucket));
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
