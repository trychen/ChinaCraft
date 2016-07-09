package unstudio.chinacraft.common;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCake;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import unstudio.chinacraft.block.BlockBase;
import unstudio.chinacraft.block.CCFlower;
import unstudio.chinacraft.block.CCGrowablePlant;
import unstudio.chinacraft.block.decoration.*;
import unstudio.chinacraft.block.especial.*;
import unstudio.chinacraft.block.generation.BlockCCOre;
import unstudio.chinacraft.block.generation.plant.BlockBambooShoot;
import unstudio.chinacraft.block.generation.plant.BlockCCCake;
import unstudio.chinacraft.block.generation.plant.BlockFirebrick;
import unstudio.chinacraft.block.model.BlockCCLantern;
import unstudio.chinacraft.block.model.BlockCCModel;
import unstudio.chinacraft.client.model.ModelLanternScaldfish;
import unstudio.chinacraft.common.network.RedPacketMessage;
import unstudio.chinacraft.common.network.RedPacketMessageHandler;
import unstudio.chinacraft.entity.EntityRegister;
import unstudio.chinacraft.item.*;
import unstudio.chinacraft.item.combat.*;
import unstudio.chinacraft.item.jade.Jade;
import unstudio.chinacraft.item.jade.JadeKnife;
import unstudio.chinacraft.item.jade.JadePinkSystem;
import unstudio.chinacraft.util.VersionChecker;
import unstudio.chinacraft.util.annotation.register.CCOreRegister;
import unstudio.chinacraft.util.annotation.register.CCRegister;
import unstudio.chinacraft.util.annotation.register.CCSlabRegister;
import unstudio.chinacraft.util.annotation.ItemBlockCollection;
import unstudio.chinacraft.util.config.ConfigLoader;
import unstudio.chinacraft.util.config.FeatureConfig;
import unstudio.chinacraft.world.gen.WorldGenMulberryTree;
import unstudio.forgebukkitbridge.VaultPlugin;

import javax.swing.*;
import java.util.Random;

@Mod(modid = ChinaCraft.MODID, name = ChinaCraft.NAME, version = ChinaCraft.VERSION)
public class ChinaCraft implements ItemBlockCollection {
    public static final String MODID = "chinacraft";
    public static final String NAME = "ChinaCraft";
    public static final String VERSION = "193";
    public static final int OutPutVERSION = 193;
    public static boolean NEIIsLoad = false;
    public static boolean WAILAIsLoad = false;
    public static boolean VersionCheckerIsLoad = false;
    public static VaultPlugin vault = null;
    public static SimpleNetworkWrapper Network;
    @SidedProxy(clientSide = "unstudio.chinacraft.common.ClientProxy", serverSide = "unstudio.chinacraft.common.CommonProxy")
    public static CommonProxy proxy;
    @Instance("chinacraft")
    public static ChinaCraft instance;
    // 特殊变量
    public static JadePinkSystem jadePinkSystem = new JadePinkSystem();
    public static VersionChecker versionChecker = new VersionChecker();
    public static boolean haveWarnedVersionOutOfDate = false;
    public static Random rand = new Random();
    // Material
    public static final Item.ToolMaterial BRONZE = EnumHelper.addToolMaterial("BRONZE", 2, 230, 6.0F, 2.0F, 1);
    public static final Item.ToolMaterial HAMMERSTONE = EnumHelper.addToolMaterial("HAMMERSIONE", 1, 240, 4.0F, 2.0F, 5); // 石锤
    public static final Item.ToolMaterial HAMMERIRON = EnumHelper.addToolMaterial("HAMMERIRON", 2, 475, 6.0F, 3.0F, 14); // 铁锤
    public static final Item.ToolMaterial HAMMERDIANMOND = EnumHelper.addToolMaterial("HAMMERDIAMOND", 3, 2096, 8.0F, 4.0F,
            10); // 钻石锤
    public static final Item.ToolMaterial YANGLONG = EnumHelper.addToolMaterial("yanlong", 3, 2568, 8.0F, 6.0F, 10); // BLGiantSword
    public static final Item.ToolMaterial BROAD_BRONZE = EnumHelper.addToolMaterial("BROAD_BRONZE", 2, 230, 6.0F, 2.5F, 1);

    public static final CreativeTabs tabCore = new CreativeTabs(StatCollector.translateToLocal("core")) {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return jadeGreenItem;
        }
    };
    public static final CreativeTabs tabFarming = new CreativeTabs(StatCollector.translateToLocal("farming")) {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return rices;
        }
    };

    public static final CreativeTabs tabTool = new CreativeTabs(StatCollector.translateToLocal("tool")) {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return bronzePickaxe;
        }
    };
    // 方块
    @CCRegister("SilverBlock")
    public static final Block silverBlock = new BlockBase(Material.rock).setHarvestLevelReturnBlock("pickaxe", 1).setBlockName("silver_block").setHardness(3.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setCreativeTab(ChinaCraft.tabCore).setBlockTextureName("chinacraft:silver_block"); // 银块
    @CCRegister("CopperBlock")
    public static final Block copperBlock = new BlockBase(Material.rock).setHarvestLevelReturnBlock("pickaxe", 1).setBlockName("copper_block").setHardness(3.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setCreativeTab(ChinaCraft.tabCore).setBlockTextureName("chinacraft:copper_block"); // 铜块
    @CCRegister("BronzeBlock")
    public static final Block bronzeBlock = new BlockCCMetal("bronze_block", 1, 5.0f).setBlockTextureName("chinacraft:bronze_block"); // 青铜块

    @CCOreRegister(name = "CopperOre",ore = "oreCopper")
    public static final BlockCCOre copperOre = (BlockCCOre) new BlockCCOre(Material.rock, 8, 20, 64, 0, 0).setHarvestLevelReturnBlock("pickaxe", 1).setBlockName("copper_ore").setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setCreativeTab(ChinaCraft.tabCore).setBlockTextureName("chinacraft:copper_ore"); // 铜矿
    @CCOreRegister(name = "TinOre",ore = "oreTin")
    public static final BlockCCOre tinOre = (BlockCCOre) new BlockCCOre(Material.rock, 8, 10, 64, 0, 0).setHarvestLevelReturnBlock("pickaxe", 1).setBlockName("tin_ore").setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setCreativeTab(ChinaCraft.tabCore).setBlockTextureName("chinacraft:tin_ore");// 锡矿
    @CCOreRegister(name = "JadeOre",ore = "oreJade")
    public static final BlockCCOre jadeOre = (BlockCCOre) new BlockCCOre(Material.rock, 4, 4, 64, 32, 0).setHarvestLevelReturnBlock("pickaxe", 2).setBlockName("jade_ore").setHardness(3.0F).setResistance(10.0F).setLightLevel(0.125F).setStepSound(Block.soundTypeStone).setCreativeTab(ChinaCraft.tabCore).setBlockTextureName("chinacraft:jade_ore"); // 玉原石
    @CCOreRegister(name = "SilverOre",ore = "oreSilver")
    public static final BlockCCOre silverOre = (BlockCCOre) new BlockCCOre(Material.rock, 8, 4, 32, 0, 0).setHarvestLevelReturnBlock("pickaxe", 2).setBlockName("silver_ore").setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setCreativeTab(ChinaCraft.tabCore).setBlockTextureName("chinacraft:silver_ore"); // 银矿

    @CCRegister("BlockMarble")
    public static final BlockMarble blockMarble = (BlockMarble) new BlockMarble().setBlockTextureName("chinacraft:marble"); // 大理石
    @CCRegister("SmoothMarble")
    public static final Block smoothMarble = new BlockBase(Material.rock).setHarvestLevelReturnBlock("pickaxe", 1).setBlockName("smooth_marble").setHardness(3.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setCreativeTab(ChinaCraft.tabCore).setBlockTextureName("chinacraft:smooth_marble"); // 平滑大理石块
    @CCRegister("PillarMarble")
    public static final Block pillarMarble = new BlockCCRotatedPillar(Material.rock,"chinacraft:pillar_marble_top","chinacraft:pillar_marble").setHarvestLevelReturnBlock("pickaxe", 1).setBlockName("pillar_marble").setHardness(3.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setCreativeTab(ChinaCraft.tabCore);// 条纹大理石块
    @CCRegister("ChiseledMarble")
    public static final Block chiseledMarble = new BlockBase(Material.rock).setHarvestLevelReturnBlock("pickaxe", 1).setBlockName("chiseled_marble").setHardness(3.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setCreativeTab(ChinaCraft.tabCore).setBlockTextureName("chinacraft:chiseled_marble"); // 錾制大理石块
    @CCRegister("MarbleStair")
    public static final Block marbleStair = new BlockCCStair(smoothMarble, 0).setHarvestLevelReturnBlock("pickaxe", 1).setBlockName("marble_stair").setCreativeTab(ChinaCraft.tabCore); // 大理石楼梯
    @CCSlabRegister(name = "MarbleSlab", first = "marbleSlab", second = "marbleDoubleSlab")
    public static final Block marbleSlab = new BlockCCSlab(false, Material.rock).setHarvestLevelReturnBlock("pickaxe", 1).setCreativeTab(ChinaCraft.tabCore).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setBlockName("marble_slab").setBlockTextureName("chinacraft:smooth_marble"); // 大理石半砖
    @CCSlabRegister(name = "MarbleDoubleSlab", first = "marbleSlab", second = "marbleDoubleSlab")
    public static final Block marbleDoubleSlab = new BlockCCSlab(true, Material.rock).setBlockSlab(ChinaCraft.marbleSlab).setHarvestLevelReturnBlock("pickaxe", 1).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setBlockName("marble_slab").setBlockTextureName("chinacraft:smooth_marble"); // 大理石半砖

    @CCRegister("WoodenWindow1")
    public static final BlockWoodenWindow woodenWindow1 = new BlockWoodenWindow("chinacraft:wooden_window_1", "chinacraft:wooden_window_top"); // 木窗框1
    @CCRegister("WoodenWindow2")
    public static final BlockWoodenWindow woodenWindow2 = new BlockWoodenWindow("chinacraft:wooden_window_2", "chinacraft:wooden_window_top"); // 木窗框2
    @CCRegister("WoodenWindow3")
    public static final BlockWoodenWindow woodenWindow3 = new BlockWoodenWindow("chinacraft:wooden_window_3", "chinacraft:wooden_window_top"); // 木窗框3
    @CCRegister("WoodenWindow4")
    public static final BlockWoodenWindow woodenWindow4 = new BlockWoodenWindow("chinacraft:wooden_window_4", "chinacraft:wooden_window_top"); // 木窗框3
    @CCRegister("WoodenWindowDragon")
    public static final BlockWoodenWindow woodenWindowdragon = new BlockWoodenWindow("chinacraft:wooden_window_dragon", "chinacraft:wooden_window_top"); // 木窗框Logo
    @CCRegister("WoodenWindowFu")
    public static final BlockWoodenWindow woodenWindowfu = new BlockWoodenWindow("chinacraft:wooden_window_fu", "chinacraft:wooden_window_top"); // 木窗框:福

    @CCRegister("Bamboo")
    public static final Block bamboo = new BlockBamboo().setBlockTextureName("chinacraft:bamboo"); // 竹子方块
    public static final BlockBambooShoot blockBambooShoot = (BlockBambooShoot) new BlockBambooShoot().setBlockTextureName("chinacraft:bamboo_shoot"); // 竹笋
    @CCRegister("BambooPlank")
    public static final Block bambooPlank = new BlockBase(Material.wood).setBlockTextureName("chinacraft:bamboo_plank").setBlockName("bamboo_plank")
            .setCreativeTab(ChinaCraft.tabCore).setStepSound(Block.soundTypeWood); // 竹木板
    public static final Block mulberryLog = new BlockCCLog("chinacraft:mulberry_log_top", "chinacraft:mulberry_log").setCreativeTab(ChinaCraft.tabCore).setBlockName("mulberry_log"); // 桑树原木
    public static final Block mulberrySapling = new BlockCCSapling(WorldGenMulberryTree.class).setBlockTextureName("chinacraft:mulberry_sapling").setBlockName("mulberry_sapling").setCreativeTab(ChinaCraft.tabFarming);//桑树树苗
    public static final Block mulberryLeaf = new BlockCCLeaves(ChinaCraft.mulberrySapling).setCreativeTab(ChinaCraft.tabFarming).setBlockName("mulberry_leaf").setBlockTextureName("chinacraft:mulberry_leaf"); // 桑树树叶
    public static final Block mulberryWood = new BlockBase(Material.wood).setBlockName("mulberry_wood").setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setCreativeTab(ChinaCraft.tabCore).setBlockTextureName("chinacraft:mulberry_wood"); // 桑树木板
    public static final CCFlower azalea = new CCFlower("azalea");
    public static final CCFlower peony = new CCFlower("peony");
    public static final CCFlower chrysanthemum = new CCFlower("chrysanthemum");
    public static final JadeWorkingTable jadeWorkingTable = new JadeWorkingTable(); // 玉石工作台
//    public static final BlockInstruments blockDrum = new BlockInstruments("drum", Material.wood, true, "note.drum", 20);
//    public static final BlockCCLamp lanternScaldfishOpenable = new BlockCCLamp(Material.cake, ModelLanternScaldfish.class, "lantern_scaldfish");
    public static final BlockCCModel lanternScaldfish = (BlockCCModel) new BlockCCModel(Material.cake, ModelLanternScaldfish.class,"lantern_scaldfish_openable").setLightLevel(8.0f);
    public static final Item itemLanternScaldfish = new ItemReed(ChinaCraft.lanternScaldfish)
            .setUnlocalizedName("lantern_scaldfish").setCreativeTab(ChinaCraft.tabCore);
//!    public static final Item itemLanternScaldfishOpenable = new ItemReed(ChinaCraft.lanternScaldfishOpenable)
//!            .setUnlocalizedName("lantern_scaldfish_openable").setCreativeTab(ChinaCraft.tabCore);
    // TraditionalCarpet
    public static final TraditionalCarpet redCarpet = new TraditionalCarpet("red_carpet", "chinacraft:red_carpet");
//    public static final TraditionalCarpet silkCarpet = new TraditionalCarpet("slik_carpet", "chinacraft:slik_carpet");

    public static final BlockPotteryTable potteryTable = new BlockPotteryTable(); // 陶瓷工作台
    //    public static final BlockPotteryBase blockPotteryBase = new BlockPotteryBase(); // 陶瓷
    public static final BlockBuhrimill buhrimill = new BlockBuhrimill(); // 石磨
    public static final BlockCCLantern lantern = new BlockCCLantern(); // 灯笼
    public static final BlockWoodenBucket blockWoodenBucket = new BlockWoodenBucket(); // 木桶
    public static final BlockCookingBench cooking_bench_off = new BlockCookingBench(false); // 灶台
    public static final BlockCookingBench cooking_bench_on = new BlockCookingBench(true); // 灶台
    public static final BlockSericultureFrame sericultureFrame = new BlockSericultureFrame(); // 养蚕架
    public static final Block blackbrickBlock = new BlockBase(Material.rock).setHarvestLevelReturnBlock("pickaxe", 0).setHardness(2.0F).setResistance(10.0F).setBlockName("blackbrick_block").setStepSound(Block.soundTypeStone).setBlockTextureName("chinacraft:blackbrick_block").setCreativeTab(ChinaCraft.tabCore);//青砖块
    public static final Block blackbrickSlab = new BlockCCSlab(false,Material.rock).setHarvestLevelReturnBlock("pickaxe", 0).setHardness(2.0F).setResistance(10.0F).setBlockName("blackbrick_slab").setStepSound(Block.soundTypeStone).setBlockTextureName("chinacraft:blackbrick_block").setCreativeTab(ChinaCraft.tabCore);//青砖半砖
    public static final Block blackbrickDoubleSlab =  new BlockCCSlab(true,Material.rock).setHarvestLevelReturnBlock("pickaxe", 0).setBlockSlab(ChinaCraft.blackbrickSlab).setHardness(2.0F).setResistance(10.0F).setBlockName("blackbrick_slab").setStepSound(Block.soundTypeStone).setBlockTextureName("chinacraft:blackbrick_block");//青砖半砖
    public static final Block blackbrickStair = new BlockCCStair(ChinaCraft.blackbrickBlock,0).setHarvestLevelReturnBlock("pickaxe", 0).setBlockName("blackbrick_stair").setCreativeTab(ChinaCraft.tabCore);

    // 物品
    public static final Item copperIngot = new Item().setUnlocalizedName("copper_ingot").setCreativeTab(ChinaCraft.tabCore); // 铜锭
    public static final Item bronzeIngot = new ItemBase().setUnlocalizedName("bronze_ingot")
            .setCreativeTab(ChinaCraft.tabCore).setTextureName("chinacraft:bronze_ingot"); // 青铜锭
    public static final Item tinIngot = new Item().setUnlocalizedName("tin_ingot").setCreativeTab(ChinaCraft.tabCore); // 锡锭
    public static final Item silverIngot = new Item().setUnlocalizedName("silver_ingot").setCreativeTab(ChinaCraft.tabCore); // 银锭
    public static final Item copperTinMixedPowder = new Item().setUnlocalizedName("copper_tin_mixed_powder")
            .setCreativeTab(ChinaCraft.tabCore); // 铜锡混合矿粉
    public static final Item tinPowder = new Item().setUnlocalizedName("tin_powder").setCreativeTab(ChinaCraft.tabCore); // 锡粉
    public static final Item copperPowder = new Item().setUnlocalizedName("copper_powder").setCreativeTab(ChinaCraft.tabCore); // 铜粉
    public static final Item blackbrick = new ItemBase().setUnlocalizedName("blackbrick").setCreativeTab(ChinaCraft.tabCore).setTextureName("chinacraft:blackbrick");//青砖
    public static final CCCropPlantItem rices = (CCCropPlantItem) new CCCropPlantItem(ChinaCraft.riceGrow)
            .setUnlocalizedName("rices"); // 米
    public static final Item lcker = new Item().setUnlocalizedName("lcker").setCreativeTab(ChinaCraft.tabFarming); // 米穗
    // public static final RiceGrow riceGrow = new RiceGrow(); //水稻作物
    public static final CCGrowablePlant riceGrow = new CCGrowablePlant("rice", 5, ChinaCraft.rices, ChinaCraft.lcker); // 水稻作物
    public static final CCCropPlantItem soy = (CCCropPlantItem) new CCCropPlantItem(ChinaCraft.soyGrow)
            .setUnlocalizedName("soy"); // 大豆
    public static final CCCropPlantItem glutinousRice = (CCCropPlantItem) new CCCropPlantItem(ChinaCraft.blockGlutinousRice)
            .setUnlocalizedName("glutinous_rice"); // 糯米
    public static final CCGrowablePlant blockGlutinousRice = new CCGrowablePlant("glutinous_rice", 7,
            ChinaCraft.glutinousRice, ChinaCraft.glutinousRice);// 糯米作物
    public static final Item itemBamboo = new ItemBase().setTextureName("chinacraft:bamboo").setCreativeTab(ChinaCraft.tabFarming).setUnlocalizedName("bamboo"); // 竹子
    public static final Item soyPod = new Item().setUnlocalizedName("soy_pod").setCreativeTab(ChinaCraft.tabFarming); // 大豆荚
    // public static final SoyGrow soyGrow = new SoyGrow(); //大豆作物
    public static final CCGrowablePlant soyGrow = new CCGrowablePlant("soy", 5, ChinaCraft.soyPod, ChinaCraft.soyPod); // 大豆作物
    public static final Item itemMulberryLeaf = new Item().setUnlocalizedName("mulberry_leaf")
            .setCreativeTab(ChinaCraft.tabFarming); // 桑叶
    public static final ItemWoodenBucket woodenBucket = new ItemWoodenBucket(Blocks.air); // 木桶
    public static final ItemWoodenBucket woodenBucket_Water = new ItemWoodenBucket(Blocks.flowing_water); // 木水桶
//    public static final Item saltBucket = new Item().setUnlocalizedName("bucket_salt").setTextureName("minecraft:bucket").setCreativeTab(ChinaCraft.tabFarming); // 豆浆桶
    public static final Item Salt = new Item().setUnlocalizedName("salt_powder").setTextureName("chinacraft:salt_powder").setCreativeTab(ChinaCraft.tabFarming); // 豆浆桶
    public static final Item douJiangBucket = new DouJiangBucket().setUnlocalizedName("doujiang_bucket").setTextureName("chinacraft:doujiang_bucket").setCreativeTab(ChinaCraft.tabFarming); // 豆浆桶
    public static final ItemSilkworm silkworm = new ItemSilkworm(); // 蚕
    public static final Item silkwormChrysalis = new Item().setCreativeTab(ChinaCraft.tabFarming)
            .setUnlocalizedName("silkworm_chrysalis"); // 蚕茧
    public static final ItemRedPacket redPacket = new ItemRedPacket(); // 红包
    public static final ItemFirecracker firecracker = new ItemFirecracker();
    public static final ItemBomb bomb = new ItemBomb();
    public static final ItemBlackDogBlood blackDogBlood = new ItemBlackDogBlood();
    public static final Item moonCake = new Item().setUnlocalizedName("moon_cake").setCreativeTab(ChinaCraft.tabFarming);
    // 防具武器
    public static final BronzeSword bronzeSword = new BronzeSword(); // 青铜剑
    public static final BronzeBroadSword bronzeBroadSword = new BronzeBroadSword(BROAD_BRONZE, null); // 青铜大刀
    public static final BronzeBroadSword bronzeBroadSwordGreen = new BronzeBroadSword(BROAD_BRONZE, "green"); // 青铜大刀Green
    public static final BronzeBroadSword bronzeBroadSwordGreen2 = new BronzeBroadSword(BROAD_BRONZE, "green2"); // 青铜大刀Green2
    public static final BronzeBroadSword bronzeBroadSwordPink = new BronzeBroadSword(BROAD_BRONZE, "pink"); // 青铜大刀Pink
    public static final BronzeBroadSword bronzeBroadSwordPurple = new BronzeBroadSword(BROAD_BRONZE, "purple"); // 青铜大刀purple
    public static final BLGiantSword blGiantSword = new BLGiantSword(ChinaCraft.YANGLONG); // 炎龙巨刀
    public static final ModelArmor chinaCrown = new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "china_crown", "chinacrown",0, 0,
            1);
    public static final ModelArmor[] nightClothes = new ModelArmor[]{
            new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_head", "nightclothes", 1, 0, 1),
            new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_body", "nightclothes", 1, 1, 1),
            new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_leg", "nightclothes", 1, 2, 1),
            new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_shoe", "nightclothes", 1, 3, 1)};
    // public static final ModelArmor nightClothesHead = new
    // ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_head",
    // "nightclothes", 1, 0, 1);
    // public static final ModelArmor nightClothesBody = new
    // ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_body",
    // "nightclothes", 1, 1, 1);
    // public static final ModelArmor nightClothesLeg = new
    // ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_leg",
    // "nightclothes", 1, 2, 1);
    // public static final ModelArmor nightClothesShoe = new
    // ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_shoe",
    // "nightclothes", 1, 3, 1);
    public static final Mace mace = new Mace();
    public static final ItemSuperBow superBow = new ItemSuperBow();
    public static final Item superArrow = new Item().setCreativeTab(ChinaCraft.tabTool).setUnlocalizedName("super_arrow");
    // 工具
    public static final BronzePickaxe bronzePickaxe = new BronzePickaxe();// 青铜稿
    public static final BronzeAxe bronzeAxe = new BronzeAxe();// 青铜斧
    public static final BronzeHoe bronzeHoe = new BronzeHoe();// 青铜锄
    public static final BronzeShovel bronzeShovel = new BronzeShovel();// 青铜铲
    public static final JiuQu_tang jiuqu_tang = new JiuQu_tang();// 九曲镋
    public static final JadeKnife jadeKnife = new JadeKnife();// 玉石切割刀
    public static final ItemArtKnife artKnife = new ItemArtKnife();// 美工切割刀
    public static final Hammer hammerStone = new Hammer(ChinaCraft.HAMMERSTONE, "stone");// 石锤
    public static final Hammer hammerIron = new Hammer(ChinaCraft.HAMMERIRON, "iron");// 铁锤
    public static final Hammer hammerDiamond = new Hammer(ChinaCraft.HAMMERDIANMOND, "diamond");// 钻石锤
    public static final Hammer hammerBronze = new Hammer(ChinaCraft.HAMMERIRON, "bronze");// 钻石锤
    public static int bronzeArmorTexture = -1; // 青铜套装外部材质注册
    public static ItemArmor bronzeHelmet;// 青铜头盔
    public static ItemArmor bronzeChestplate;// 青铜胸甲
    public static ItemArmor bronzeLeggings;// 青铜护腿
    public static ItemArmor bronzeBoots;// 青铜靴子
    // 玉石
    public static final Jade jadeGreenItem = new Jade("jade_green");
    public static final Jade jadeGreen2Item = new Jade("jade_green2");
    public static final Jade jadePinkItem = new Jade("jade_pink");
    public static final Jade jadePurpleItem = new Jade("jade_purple");
    // Drink、Food
    public static final Item cup = new Item().setUnlocalizedName("cup").setCreativeTab(ChinaCraft.tabFarming); // 杯
    public static final Item cup_Clay = new Item().setUnlocalizedName("cpu_clay").setCreativeTab(ChinaCraft.tabFarming);
    public static final CupChocolate cupChocolate = new CupChocolate();
    public static final Item cocoa = new Item().setUnlocalizedName("cocoa").setCreativeTab(ChinaCraft.tabFarming);
    public static final ItemFood ladyfinger = (ItemFood) new ItemFood(2, false).setUnlocalizedName("ladyfinger")
            .setCreativeTab(ChinaCraft.tabFarming);
    public static final CupWater cupWater = new CupWater();
    public static final CupChrysanthemum cupChrysanthemum = new CupChrysanthemum();
    public static final Item flour = new Item().setUnlocalizedName("flour").setCreativeTab(ChinaCraft.tabFarming); // 面粉
    public static final Item riceFlour = new Item().setUnlocalizedName("rice_flour").setCreativeTab(ChinaCraft.tabFarming); // 米粉
    public static final Item barleyRice = new Item().setUnlocalizedName("barley_rice").setCreativeTab(ChinaCraft.tabFarming); // 麦仁,大麦米
    public static final BlockCake xinjiangNutCake = (BlockCake) new BlockCCCake("xinjiang_nut_cake")
            .setCreativeTab(ChinaCraft.tabFarming);
    public static final BlockCake appleCake = new BlockCCCake("apple_cake");
    public static final ItemReed itemAppleCake = (ItemReed) new ItemReed(ChinaCraft.appleCake)
            .setUnlocalizedName("apple_cake").setMaxStackSize(1).setCreativeTab(ChinaCraft.tabFarming);
    // 耐火砖
    public static final BlockFirebrick blockFirebrick = new BlockFirebrick(); // 耐火砖块
    public static final BlockFirebrickStructure blockFirebrickStructure = new BlockFirebrickStructure(); // 耐火砖块(多方块结构)
    public static final BlockPotteryKiln blockPotteryKiln = new BlockPotteryKiln(); // 窑炉核心方块
    public static final Item firebrick = new Item().setUnlocalizedName("firebrick").setCreativeTab(ChinaCraft.tabCore); // 耐火砖
    public static final Item claySandMixture = new Item().setUnlocalizedName("clay_sand_mixture")
            .setCreativeTab(ChinaCraft.tabCore); // 粘土沙子混合物
    // spiritual_magic_figures灵符
    public static final Item spiritualMagicFigures = new Item().setCreativeTab(ChinaCraft.tabCore)
            .setUnlocalizedName("spiritual_magic_figures").setMaxStackSize(16); // 基本灵符
    public static final ItemSMFFire smfFire = new ItemSMFFire(); // 火
    public static final ItemSMFPotion smfNightVision = new ItemSMFPotion("spiritual_magic_figures_night_vision",
            new int[][]{{16, 10000}}); // 夜视
    public static final ItemSMFPotion smfPoison = new ItemSMFPotion("spiritual_magic_figures_poison",
            new int[][]{{19, 450, 4}}); // 中毒
    public static final ItemSMFPotion smfPower = new ItemSMFPotion("spiritual_magic_figures_power",
            new int[][]{{5, 7000}}); // 力量
    public static final ItemSMFPotion smfProtect = new ItemSMFPotion("spiritual_magic_figures_protect",
            new int[][]{{12, 3500}, {11, 2500, 3}}); // 保护
    public static final ItemSMFPotion smfHeal = new ItemSMFPotion("spiritual_magic_figures_heal",
            new int[][]{{6, 1}, {10, 500}}); // 生命回复
    public static final ItemSMFSuper smfSuper = new ItemSMFSuper(); // 捉妖符
    // disc
    public static final CCMusicDisc three_stanzas = new CCMusicDisc("three_stanzas_of_plum-blossoms");
    public static final CCMusicDisc mountain_stream = new CCMusicDisc("mountain_stream");
    public static final CCMusicDisc the_march_of_the_volunteers = new CCMusicDisc("the_march_of_the_volunteers");
    public static final CCMusicDisc spring_festival_overture = new CCMusicDisc("spring_festival_overture");
    public static final Item itemSilk = new ItemSilk().setUnlocalizedName("silk"); // 丝绸
    public static final Item silkYarn = new ItemBase().setUnlocalizedName("silk_yarn").setTextureName("chinacraft:silk_yarn")
            .setCreativeTab(ChinaCraft.tabFarming);
    public static final ItemDebug debug = new ItemDebug(); // 调试物品

    public static void main(String[] args) {
        // 当直接打开Mod时提示信息
        JOptionPane.showMessageDialog(null, "This is a Minecraft Forge Mod , you can't run it!",
                "Chinacraft : mccraft.cn", JOptionPane.OK_OPTION);
        System.exit(0);
    }

    public static Configuration getMainConfig() {
        return ConfigLoader.getMainConfig();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        Network = NetworkRegistry.INSTANCE.newSimpleChannel("ChinaCraftChannel");
        Network.registerMessage(new RedPacketMessageHandler(), RedPacketMessage.class, 0, Side.SERVER);

        NEIIsLoad = Loader.isModLoaded("NotEnoughItems");
        WAILAIsLoad = Loader.isModLoaded("Waila");

        VersionCheckerIsLoad = Loader.isModLoaded("VersionChecker");
        // Network.registerMessage(BaseMessage.Handler.class, BaseMessage.class,
        // 1, Side.CLIENT);
        if (FeatureConfig.EnableUpdate) new Thread(versionChecker).start();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        EntityRegister.init();
        jadePinkSystem.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @EventHandler
    public void onServerStarted(FMLServerStartedEvent event) {
        //vault = ServerManager.getVaultPlugin();
    }

    @Override
    public boolean canInvoker(Object input) {
        return true;
    }
}
