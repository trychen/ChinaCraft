package unstudio.chinacraft.common;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCake;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import unstudio.chinacraft.block.BlockBase;
import unstudio.chinacraft.block.CCFlower;
import unstudio.chinacraft.block.CCGrowablePlant;
import unstudio.chinacraft.block.decoration.*;
import unstudio.chinacraft.block.especial.*;
import unstudio.chinacraft.block.generation.BlockCCOre;
import unstudio.chinacraft.block.generation.plant.BlockBambooShoot;
import unstudio.chinacraft.block.generation.plant.BlockCCCake;
import unstudio.chinacraft.block.generation.plant.BlockFirebrick;
import unstudio.chinacraft.block.model.BlockCCLamp;
import unstudio.chinacraft.client.model.block.ModelLanternScaldfish;
import unstudio.chinacraft.common.network.KeyMessage;
import unstudio.chinacraft.common.network.KeyMessageHandler;
import unstudio.chinacraft.common.network.RedPacketMessage;
import unstudio.chinacraft.common.network.RedPacketMessageHandler;
import unstudio.chinacraft.entity.EntityRegister;
import unstudio.chinacraft.item.*;
import unstudio.chinacraft.item.combat.*;
import unstudio.chinacraft.item.jade.Jade;
import unstudio.chinacraft.item.jade.JadePinkSystem;
import unstudio.chinacraft.util.remote.Network;
import unstudio.chinacraft.util.remote.MinecraftModVersionChecker;
import unstudio.chinacraft.util.remote.VersionChecker;
import unstudio.chinacraft.util.annotation.register.Register;
import unstudio.chinacraft.util.annotation.register.SlabRegister;
import unstudio.chinacraft.util.annotation.register.ICollection;
import unstudio.chinacraft.common.config.FeatureConfig;
import unstudio.chinacraft.common.config.ConfigLoader;
import unstudio.chinacraft.world.gen.WorldGenMulberryTree;

import java.util.Random;

@Mod(modid = ChinaCraft.MODID, name = ChinaCraft.NAME, version = ChinaCraft.VERSION)
public class ChinaCraft implements ICollection {
    public static final String MODID = "chinacraft";
    public static final String NAME = "ChinaCraft";
    public static final String VERSION = "0.3.200";
    public static final int PROJECT_ID = 1;

    public static SimpleNetworkWrapper Network;
    public static Logger log = LogManager.getLogger(NAME);

    //其他Mod加载情况
    public static boolean NEIIsLoad = false;
    public static boolean WAILAIsLoad = false;
    public static boolean VersionCheckerIsLoad = false;
    public unstudio.chinacraft.util.remote.Network network = new Network();

    @SidedProxy(clientSide = "unstudio.chinacraft.common.ClientProxy", serverSide = "unstudio.chinacraft.common.CommonProxy")
    public static CommonProxy proxy;
    @Instance("chinacraft")
    public static ChinaCraft instance;

    // 特殊变量
    public static JadePinkSystem jadePinkSystem = new JadePinkSystem();
    public static VersionChecker versionChecker;
    public static boolean haveWarnedVersionOutOfDate = false;
    public final static Random rand = new Random();
    // Material
    public static final Item.ToolMaterial BRONZE = EnumHelper.addToolMaterial("BRONZE", 2, 200, 5.2F, 1.8F, 14);

    public static final Item.ToolMaterial HAMMERSTONE = EnumHelper.addToolMaterial("HAMMERSIONE", 1, 240, 4.0F, 2.0F, 5); // 石锤
    public static final Item.ToolMaterial HAMMERIRON = EnumHelper.addToolMaterial("HAMMERIRON", 2, 475, 6.0F, 3.0F, 14); // 铁锤
    public static final Item.ToolMaterial HAMMERDIANMOND = EnumHelper.addToolMaterial("HAMMERDIAMOND", 3, 2096, 8.0F, 4.0F,
            10); // 钻石锤
    public static final Item.ToolMaterial YANGLONG = EnumHelper.addToolMaterial("yanlong", 3, 2568, 8.0F, 6.0F, 10); // BLGiantSword
    public static final Item.ToolMaterial BROAD_BRONZE = EnumHelper.addToolMaterial("BROAD_BRONZE", 2, 230, 6.0F, 2.5F, 1);

    public static final CreativeTabs tabCore = new CreativeTabs("core") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return jadeGreenItem;
        }
    };
    public static final CreativeTabs tabFarming = new CreativeTabs("farming") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return rices;
        }
    };

    public static final CreativeTabs tabTool = new CreativeTabs("tool") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return bronzePickaxe;
        }
    };
    // 方块
    @Register(value = "SilverBlock", ore = "blockSilver")
    public static final Block silverBlock = new BlockBase(Material.rock).setHarvestLevelReturnBlock("pickaxe", 1).setBlockName("silver_block").setHardness(3.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setCreativeTab(ChinaCraft.tabCore).setBlockTextureName("chinacraft:silver_block"); // 银块
    @Register(value = "CopperBlock", ore = "blockCopper")
    public static final Block copperBlock = new BlockBase(Material.rock).setHarvestLevelReturnBlock("pickaxe", 1).setBlockName("copper_block").setHardness(3.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setCreativeTab(ChinaCraft.tabCore).setBlockTextureName("chinacraft:copper_block"); // 铜块
    @Register(value = "BronzeBlock", ore = "blockBronze")
    public static final Block bronzeBlock = new BlockCCMetal("bronze_block", 1, 5.0f).setBlockTextureName("chinacraft:bronze_block"); // 青铜块

    @Register(value = "CopperOre",ore = "oreCopper")
    public static final BlockCCOre copperOre = (BlockCCOre) new BlockCCOre(Material.rock, 8, 20, 64, 0, 0).setHarvestLevelReturnBlock("pickaxe", 1).setBlockName("copper_ore").setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setCreativeTab(ChinaCraft.tabCore).setBlockTextureName("chinacraft:copper_ore"); // 铜矿
    @Register(value = "TinOre",ore = "oreTin")
    public static final BlockCCOre tinOre = (BlockCCOre) new BlockCCOre(Material.rock, 8, 10, 64, 0, 0).setHarvestLevelReturnBlock("pickaxe", 1).setBlockName("tin_ore").setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setCreativeTab(ChinaCraft.tabCore).setBlockTextureName("chinacraft:tin_ore");// 锡矿
    @Register(value = "JadeOre",ore = "oreJade")
    public static final BlockCCOre jadeOre = (BlockCCOre) new BlockCCOre(Material.rock, 4, 4, 64, 32, 0).setHarvestLevelReturnBlock("pickaxe", 2).setBlockName("jade_ore").setHardness(3.0F).setResistance(10.0F).setLightLevel(0.125F).setStepSound(Block.soundTypeStone).setCreativeTab(ChinaCraft.tabCore).setBlockTextureName("chinacraft:jade_ore"); // 玉原石
    @Register(value = "SilverOre",ore = "oreSilver")
    public static final BlockCCOre silverOre = (BlockCCOre) new BlockCCOre(Material.rock, 8, 4, 32, 0, 0).setHarvestLevelReturnBlock("pickaxe", 2).setBlockName("silver_ore").setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setCreativeTab(ChinaCraft.tabCore).setBlockTextureName("chinacraft:silver_ore"); // 银矿

    @Register(value = "CopperIngot",ore = "ingotCopper")
    public static final Item copperIngot = new Item().setUnlocalizedName("copper_ingot").setCreativeTab(ChinaCraft.tabCore); // 铜锭
    @Register(value = "BronzeIngot",ore = "ingotBronze")
    public static final Item bronzeIngot = new ItemBase().setUnlocalizedName("bronze_ingot").setCreativeTab(ChinaCraft.tabCore).setTextureName("chinacraft:bronze_ingot"); // 青铜锭
    @Register(value = "TinIngot",ore = "ingotTin")
    public static final Item tinIngot = new Item().setUnlocalizedName("tin_ingot").setCreativeTab(ChinaCraft.tabCore); // 锡锭
    @Register(value = "SilverIngot",ore = "ingotSilver")
    public static final Item silverIngot = new Item().setUnlocalizedName("silver_ingot").setCreativeTab(ChinaCraft.tabCore); // 银锭
    @Register(value = "CopperTinMixedPowder", ore = "dustCopperMixedTin")
    public static final Item copperTinMixedPowder = new Item().setUnlocalizedName("copper_tin_mixed_powder").setCreativeTab(ChinaCraft.tabCore); // 铜锡混合矿粉
    @Register(value = "TinPowder",ore = "dustTin")
    public static final Item tinPowder = new Item().setUnlocalizedName("tin_powder").setCreativeTab(ChinaCraft.tabCore); // 锡粉
    @Register(value = "CopperPowder",ore = "dustCopper")
    public static final Item copperPowder = new Item().setUnlocalizedName("copper_powder").setCreativeTab(ChinaCraft.tabCore); // 铜粉
    @Register("BlackBrick")
    public static final Item blackbrick = new ItemBase().setUnlocalizedName("blackbrick").setCreativeTab(ChinaCraft.tabCore).setTextureName("chinacraft:blackbrick");//青砖


    @Register("BlockMarble")
    public static final BlockMarble blockMarble = (BlockMarble) new BlockMarble().setBlockTextureName("chinacraft:marble"); // 大理石
    @Register("SmoothMarble")
    public static final Block smoothMarble = new BlockBase(Material.rock).setHarvestLevelReturnBlock("pickaxe", 1).setBlockName("smooth_marble").setHardness(3.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setCreativeTab(ChinaCraft.tabCore).setBlockTextureName("chinacraft:smooth_marble"); // 平滑大理石块
    @Register("PillarMarble")
    public static final Block pillarMarble = new BlockCCRotatedPillar(Material.rock,"chinacraft:pillar_marble_top","chinacraft:pillar_marble").setHarvestLevelReturnBlock("pickaxe", 1).setBlockName("pillar_marble").setHardness(3.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setCreativeTab(ChinaCraft.tabCore);// 条纹大理石块
    @Register("ChiseledMarble")
    public static final Block chiseledMarble = new BlockBase(Material.rock).setHarvestLevelReturnBlock("pickaxe", 1).setBlockName("chiseled_marble").setHardness(3.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setCreativeTab(ChinaCraft.tabCore).setBlockTextureName("chinacraft:chiseled_marble"); // 錾制大理石块
    @Register("MarbleStair")
    public static final Block marbleStair = new BlockCCStair(smoothMarble, 0).setHarvestLevelReturnBlock("pickaxe", 1).setBlockName("marble_stair").setCreativeTab(ChinaCraft.tabCore); // 大理石楼梯
    @SlabRegister(name = "MarbleSlab", first = "marbleSlab", second = "marbleDoubleSlab")
    public static final Block marbleSlab = new BlockCCSlab(false, Material.rock).setHarvestLevelReturnBlock("pickaxe", 1).setCreativeTab(ChinaCraft.tabCore).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setBlockName("marble_slab").setBlockTextureName("chinacraft:smooth_marble"); // 大理石半砖
    @SlabRegister(name = "MarbleDoubleSlab", first = "marbleSlab", second = "marbleDoubleSlab")
    public static final Block marbleDoubleSlab = new BlockCCSlab(true, Material.rock).setBlockSlab(ChinaCraft.marbleSlab).setHarvestLevelReturnBlock("pickaxe", 1).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setBlockName("marble_slab").setBlockTextureName("chinacraft:smooth_marble"); // 大理石半砖

//    @Register("TraditionalPainting")
    public static final CCItemPainting traditionalPainting = new CCItemPainting("traditional_painting");
    
    @Register("Bamboo")
    public static final Block bamboo = new BlockBamboo().setBlockTextureName("chinacraft:bamboo"); // 竹子方块
    @Register("BlockBambooShoot")
    public static final BlockBambooShoot blockBambooShoot = (BlockBambooShoot) new BlockBambooShoot().setBlockTextureName("chinacraft:bamboo_shoot"); // 竹笋
    @Register("BambooPlank")
    public static final Block bambooPlank = new BlockBase(Material.wood).setBlockTextureName("chinacraft:bamboo_plank").setBlockName("bamboo_plank")
            .setCreativeTab(ChinaCraft.tabCore).setStepSound(Block.soundTypeWood); // 竹板
    @SlabRegister(name = "BambooSlab",first = "bambooSlab",second = "bambooDoubleSlab")
    public static final Block bambooSlab = new BlockCCSlab(false,Material.wood).setHardness(2.0F).setResistance(5.0F).setBlockName("bamboo_slab").setStepSound(Block.soundTypeWood).setBlockTextureName("chinacraft:bamboo_plank").setCreativeTab(ChinaCraft.tabCore);//竹制半砖
    @SlabRegister(name = "BambooDoubleSlab",first = "bambooSlab",second = "bambooDoubleSlab")
    public static final Block bambooDoubleSlab =  new BlockCCSlab(true,Material.wood).setBlockSlab(ChinaCraft.bambooSlab).setHardness(2.0F).setResistance(5.0F).setBlockName("bamboo_slab").setStepSound(Block.soundTypeWood).setBlockTextureName("chinacraft:bamboo_plank");//竹制半砖
    @Register("BambooStair")
    public static final Block bambooStair = new BlockCCStair(ChinaCraft.bambooPlank,0).setHardness(2.0F).setResistance(5.0F).setBlockName("bamboo_stair").setCreativeTab(ChinaCraft.tabCore);//竹制楼梯
    @Register("BambooFenceGate")
    public static final Block bambooFenceGate = new BlockCCFenceGate(bambooPlank).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("bamboo_fence_gate").setCreativeTab(ChinaCraft.tabCore);//竹制栅栏门
    @Register("BambooFence")
    public static final Block bambooFence = new BlockCCFence("chinacraft:bamboo_plank",Material.wood).setGate(bambooFenceGate).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("bamboo_fence").setCreativeTab(ChinaCraft.tabCore);//竹制栅栏

    @Register("BlackBrickBlock")
    public static final Block blackbrickBlock = new BlockBase(Material.rock).setHarvestLevelReturnBlock("pickaxe", 0).setHardness(2.0F).setResistance(10.0F).setBlockName("blackbrick_block").setStepSound(Block.soundTypeStone).setBlockTextureName("chinacraft:blackbrick_block").setCreativeTab(ChinaCraft.tabCore);//青砖块
    @SlabRegister(name = "BlackBrickSlab",first = "blackbrickSlab",second = "blackbrickDoubleSlab")
    public static final Block blackbrickSlab = new BlockCCSlab(false,Material.rock).setHarvestLevelReturnBlock("pickaxe", 0).setHardness(2.0F).setResistance(10.0F).setBlockName("blackbrick_slab").setStepSound(Block.soundTypeStone).setBlockTextureName("chinacraft:blackbrick_block").setCreativeTab(ChinaCraft.tabCore);//青砖半砖
    @SlabRegister(name = "BlackBrickDoubleSlab",first = "blackbrickSlab",second = "blackbrickDoubleSlab")
    public static final Block blackbrickDoubleSlab =  new BlockCCSlab(true,Material.rock).setHarvestLevelReturnBlock("pickaxe", 0).setBlockSlab(ChinaCraft.blackbrickSlab).setHardness(2.0F).setResistance(10.0F).setBlockName("blackbrick_slab").setStepSound(Block.soundTypeStone).setBlockTextureName("chinacraft:blackbrick_block");//青砖半砖
    @Register("BlackBrickStair")
    public static final Block blackbrickStair = new BlockCCStair(ChinaCraft.blackbrickBlock,0).setHarvestLevelReturnBlock("pickaxe", 0).setHardness(2.0F).setResistance(10.0F).setBlockName("blackbrick_stair").setCreativeTab(ChinaCraft.tabCore);

    @Register(value = "MulberryLog",ore = "logWood")
    public static final Block mulberryLog = new BlockCCLog("chinacraft:mulberry_log_top", "chinacraft:mulberry_log").setCreativeTab(ChinaCraft.tabCore).setBlockName("mulberry_log"); // 桑树原木
    @Register(value = "MulberryLeaf",ore = "treeLeaves")
    public static final Block mulberryLeaf = new BlockCCLeaves(ChinaCraft.mulberrySapling).setCreativeTab(ChinaCraft.tabFarming).setBlockName("mulberry_leaf").setBlockTextureName("chinacraft:mulberry_leaf"); // 桑树树叶
    @Register(value = "MulberrySapling",ore = "treeSapling")
    public static final Block mulberrySapling = new BlockCCSapling(WorldGenMulberryTree.class).setBlockTextureName("chinacraft:mulberry_sapling").setBlockName("mulberry_sapling").setCreativeTab(ChinaCraft.tabFarming);//桑树树苗
    @Register(value = "MulberryWood",ore = "plankWood")
    public static final Block mulberryWood = new BlockBase(Material.wood).setBlockName("mulberry_wood").setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setCreativeTab(ChinaCraft.tabCore).setBlockTextureName("chinacraft:mulberry_wood"); // 桑树木板


    @Register("WoodenWindow1")
    public static final BlockWoodenWindow woodenWindow1 = new BlockWoodenWindow("chinacraft:wooden_window_1", "chinacraft:wooden_window_top"); // 木窗框1
    @Register("WoodenWindow2")
    public static final BlockWoodenWindow woodenWindow2 = new BlockWoodenWindow("chinacraft:wooden_window_2", "chinacraft:wooden_window_top"); // 木窗框2
    @Register("WoodenWindow3")
    public static final BlockWoodenWindow woodenWindow3 = new BlockWoodenWindow("chinacraft:wooden_window_3", "chinacraft:wooden_window_top"); // 木窗框3
    @Register("WoodenWindow4")
    public static final BlockWoodenWindow woodenWindow4 = new BlockWoodenWindow("chinacraft:wooden_window_4", "chinacraft:wooden_window_top"); // 木窗框3
    @Register("WoodenWindowDragon")
    public static final BlockWoodenWindow woodenWindowdragon = new BlockWoodenWindow("chinacraft:wooden_window_dragon", "chinacraft:wooden_window_top"); // 木窗框Logo
    @Register("WoodenWindowFu")
    public static final BlockWoodenWindow woodenWindowfu = new BlockWoodenWindow("chinacraft:wooden_window_fu", "chinacraft:wooden_window_top"); // 木窗框:福

    @Register("Azalea")
    public static final CCFlower azalea = new CCFlower("azalea");
    @Register("Peony")
    public static final CCFlower peony = new CCFlower("peony");
    @Register("chrysanthemum")// TODO:字母未大写
    public static final CCFlower chrysanthemum = new CCFlower("chrysanthemum");
    @Register("JadeWorkingTable")
    public static final JadeWorkingTable jadeWorkingTable = new JadeWorkingTable(); // 玉石工作台

//    public static final BlockInstruments blockDrum = new BlockInstruments("drum", Material.wood, true, "note.drum", 20);

//!    public static final Item itemLanternScaldfishOpenable = new ItemReed(ChinaCraft.lanternScaldfishOpenable)
//!            .setUnlocalizedName("lantern_scaldfish_openable").setCreativeTab(ChinaCraft.tabCore);

    // TraditionalCarpet
    @Register("red_carpet")
    public static final TraditionalCarpet redCarpet = new TraditionalCarpet("red_carpet", "chinacraft:red_carpet");

    @Register("PotteryTable")
    public static final BlockPotteryTable potteryTable = new BlockPotteryTable(); // 陶瓷工作台
    //    public static final BlockPotteryBase blockPotteryBase = new BlockPotteryBase(); // 陶瓷
    @Register("Buhrimill")
    public static final BlockBuhrimill buhrimill = new BlockBuhrimill(); // 石磨
    @Register(value="LanternScaldfish", itemClass = ItemCCLamp.class)
    public static final BlockCCLamp lanternScaldfish = (BlockCCLamp) new BlockCCLamp(Material.wood, ModelLanternScaldfish.class,"lantern_scaldfish",true).setTexture("lantern_scaldfish_on");
    @Register(value="LanternScaldfishOff", itemClass = ItemCCLamp.class)
    public static final BlockCCLamp lanternScaldfishOff = (BlockCCLamp) new BlockCCLamp(Material.wood, ModelLanternScaldfish.class, "lantern_scaldfish_openable",false).setTexture("lantern_scaldfish_off").setCreativeTab(null);
//    @Register("Fu")
    public static final CCItemWord wordFu = new CCItemWord();
    @Register("BlockDing")
    public static final BlockCCDing ding = new BlockCCDing();
    @Register("Ding")
    public static final ItemCCDing itemDing = new ItemCCDing();
    @Register("BlockWoodenBucket")
    public static final BlockWoodenBucket blockWoodenBucket = new BlockWoodenBucket(); // 木桶
    @Register("CookingBenchOff")
    public static final BlockCookingBench cooking_bench_off = new BlockCookingBench(false); // 灶台
    @Register("CookingBenchOn")
    public static final BlockCookingBench cooking_bench_on = new BlockCookingBench(true); // 灶台

    @Register("SericultureFrame")
    public static final BlockSericultureFrame sericultureFrame = new BlockSericultureFrame(); // 养蚕架

    @Register("RiceGrow")
    public static final CCGrowablePlant riceGrow = new CCGrowablePlant("rice", 5, ChinaCraft.rices, ChinaCraft.lcker); // 水稻作物
    @Register("Rices")
    public static final CCCropPlantItem rices = (CCCropPlantItem) new CCCropPlantItem(ChinaCraft.riceGrow)
            .setUnlocalizedName("rices"); // 米
    @Register("Lckers")
    public static final Item lcker = new Item().setUnlocalizedName("lcker").setCreativeTab(ChinaCraft.tabFarming); // 米穗
    @Register("SoyPod")
    public static final Item soyPod = new Item().setUnlocalizedName("soy_pod").setCreativeTab(ChinaCraft.tabFarming); // 大豆荚
    @Register("SoyGrow")
    public static final CCGrowablePlant soyGrow = new CCGrowablePlant("soy", 5, ChinaCraft.soyPod, ChinaCraft.soyPod); // 大豆作物
    @Register("Soy")
    public static final CCCropPlantItem soy = (CCCropPlantItem) new CCCropPlantItem(ChinaCraft.soyGrow).setUnlocalizedName("soy"); // 大豆
    @Register("BlockGlutinousRice")
    public static final CCGrowablePlant blockGlutinousRice = new CCGrowablePlant("glutinous_rice", 7, ChinaCraft.glutinousRice, ChinaCraft.glutinousRice);// 糯米作物
    @Register("GlutinousRice")
    public static final CCCropPlantItem glutinousRice = (CCCropPlantItem) new CCCropPlantItem(ChinaCraft.blockGlutinousRice).setUnlocalizedName("glutinous_rice"); // 糯米
    @Register("ItemBamboo")
    public static final Item itemBamboo = new ItemBase().setTextureName("chinacraft:bamboo").setCreativeTab(ChinaCraft.tabFarming).setUnlocalizedName("bamboo"); // 竹子
    @Register("ItemMulberryLeaf")
    public static final Item itemMulberryLeaf = new Item().setUnlocalizedName("mulberry_leaf").setCreativeTab(ChinaCraft.tabFarming); // 桑叶
    @Register("BlackDogBlood")
    public static final ItemBlackDogBlood blackDogBlood = new ItemBlackDogBlood();
    @Register("MoonCake")
    public static final Item moonCake = new Item().setUnlocalizedName("moon_cake").setCreativeTab(ChinaCraft.tabFarming);

    @Register("WoodenBucket")
    public static final ItemWoodenBucket woodenBucket = new ItemWoodenBucket(Blocks.air); // 木桶
    @Register("WoodenBucket_Water")
    public static final ItemWoodenBucket woodenBucket_Water = new ItemWoodenBucket(Blocks.flowing_water); // 木水桶
    @Register("DouJiangBucket")
    public static final Item douJiangBucket = new DouJiangBucket().setUnlocalizedName("doujiang_bucket").setCreativeTab(ChinaCraft.tabFarming); // 豆浆桶
    //    public static final Item saltBucket = new Item().setUnlocalizedName("bucket_salt").setTextureName("minecraft:bucket").setCreativeTab(ChinaCraft.tabFarming); // 豆浆桶
    @Register("Salt")
    public static final Item salt = new Item().setUnlocalizedName("salt_powder").setCreativeTab(ChinaCraft.tabFarming); // 豆浆桶
    @Register("RedPacket")
    public static final ItemRedPacket redPacket = new ItemRedPacket(); // 红包
    @Register("Firecracker")
    public static final ItemFirecracker firecracker = new ItemFirecracker();
    @Register("Bomb")
    public static final ItemBomb bomb = new ItemBomb();
    // 防具武器
    @Register("BronzeSword")
    public static final Item bronzeSword = new ItemSword(ChinaCraft.BRONZE).setUnlocalizedName("bronze_sword").setCreativeTab(ChinaCraft.tabTool); // 青铜剑
    @Register("BronzeBroadSword")
    public static final BronzeBroadSword bronzeBroadSword = new BronzeBroadSword(BROAD_BRONZE, null); // 青铜大刀
    @Register("bronzeBroadSwordGreen")
    public static final BronzeBroadSword bronzeBroadSwordGreen = new BronzeBroadSword(BROAD_BRONZE, "green"); // 青铜大刀Green
    @Register("bronzeBroadSwordGreen2")
    public static final BronzeBroadSword bronzeBroadSwordGreen2 = new BronzeBroadSword(BROAD_BRONZE, "green2"); // 青铜大刀Green2
    @Register("bronzeBroadSwordPink")
    public static final BronzeBroadSword bronzeBroadSwordPink = new BronzeBroadSword(BROAD_BRONZE, "pink"); // 青铜大刀Pink
    @Register("bronzeBroadSwordPurple")
    public static final BronzeBroadSword bronzeBroadSwordPurple = new BronzeBroadSword(BROAD_BRONZE, "purple"); // 青铜大刀purple
    @Register("YanLungGiantknife")
    public static final BLGiantSword blGiantSword = new BLGiantSword(ChinaCraft.YANGLONG); // 炎龙巨刀
    @Register("JiuQuTang")
    public static final CCItemJiuQuTang jiuquTang = new CCItemJiuQuTang();// 九曲镋
    @Register("Mace")
    public static final CCItemMace mace = new CCItemMace();
//    @Register("SuperBow")
    public static final ItemSuperBow superBow = new ItemSuperBow();
    @Register("StoneHammer")
    public static final Hammer hammerStone = new Hammer(ChinaCraft.HAMMERSTONE, "stone");// 石锤
    @Register("DiamondHammer")
    public static final Hammer hammerBronze = new Hammer(ChinaCraft.HAMMERIRON, "bronze");
    @Register("IronHammer")
    public static final Hammer hammerIron = new Hammer(ChinaCraft.HAMMERIRON, "iron");// 铁锤
    @Register("DiamondBronze")
    public static final Hammer hammerDiamond = new Hammer(ChinaCraft.HAMMERDIANMOND, "diamond");// 钻石锤
//    @Register("BronzeShield")
    public static final CCShield bronzeShield = (CCShield) new CCShield(10, 10, true).setUnlocalizedName("bronze_shield").setTextureName("chinacraft:shield_bronze");
    @Register("ChinaCrown")
    public static final ModelArmor chinaCrown = new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "china_crown", "chinacrown",0, 0, 1);
    public static final ModelArmor[] nightClothes = new ModelArmor[]{
            new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_head", "nightclothes", 1, 0, 1),
            new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_body", "nightclothes", 1, 1, 1),
            new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_leg", "nightclothes", 1, 2, 1),
            new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_shoe", "nightclothes", 1, 3, 1)};
    @Register("Cassock")
    public static final ModelArmorCassock cassock = new ModelArmorCassock();

    @Register("BronzeHelmet")
    public static ItemArmor bronzeHelmet;// 青铜头盔
    @Register("BronzeChestplate")
    public static ItemArmor bronzeChestplate;// 青铜胸甲
    @Register("BronzeLeggings")
    public static ItemArmor bronzeLeggings;// 青铜护腿
    @Register("BronzeBoots")
    public static ItemArmor bronzeBoots;// 青铜靴子
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

    // 工具
    @Register("BronzePickaxe")
    public static final Item bronzePickaxe = new CCItemPickaxe(ChinaCraft.BRONZE).setUnlocalizedName("bronze_pickaxe").setCreativeTab(ChinaCraft.tabTool);// 青铜稿
    @Register("BronzeAxe")
    public static final Item bronzeAxe = new CCItemAxe(ChinaCraft.BRONZE).setUnlocalizedName("bronze_axe").setMaxStackSize(1).setMaxDamage(251).setCreativeTab(ChinaCraft.tabTool);// 青铜斧
    @Register("BronzeHoe")
    public static final Item bronzeHoe = new ItemHoe(ChinaCraft.BRONZE).setUnlocalizedName("bronze_hoe").setMaxStackSize(1).setMaxDamage(251).setCreativeTab(ChinaCraft.tabTool);// 青铜锄
    @Register("BronzeShovel")
    public static final Item bronzeShovel = new ItemSpade(ChinaCraft.BRONZE).setUnlocalizedName("bronze_spade").setCreativeTab(ChinaCraft.tabTool);// 青铜铲
    @Register("JadeKnife")
    public static final CCItemThrowable jadeKnife = (CCItemThrowable) new CCItemThrowable(1.0f,false,false).setUnlocalizedName("jade_knife").setMaxStackSize(1).setCreativeTab(ChinaCraft.tabTool);// 玉石切割刀
    @Register("ItemArtKnife")
    public static final ItemArtKnife artKnife = new ItemArtKnife();// 美工切割刀
    @Register("FlyingCutter")
    public static final CCItemThrowable flyingCutter = (CCItemThrowable) new CCItemThrowable(4.0f,false,true).setSpeed(18).setUnlocalizedName("flying_cutter");
    @Register("BuddhistCane")
    public static final ItemBuddhistCane buddhistCane = new ItemBuddhistCane();

    public static int bronzeArmorTexture = -1; // 青铜套装外部材质注册
    // 玉石
    @Register("JadeGreen")
    public static final Jade jadeGreenItem = new Jade("jade_green");
    @Register("JadeGreen2")
    public static final Item jadeGreen2Item = new Jade("jade_green2").setMaxDamage(56);
    @Register("JadePink")
    public static final Jade jadePinkItem = new Jade("jade_pink");
    @Register("JadePurple")
    public static final Jade jadePurpleItem = new Jade("jade_purple");
    // Drink、Food
    public static final Item cup = new Item().setUnlocalizedName("cup").setCreativeTab(ChinaCraft.tabFarming); // 杯
    public static final Item cup_Clay = new Item().setUnlocalizedName("cpu_clay").setCreativeTab(ChinaCraft.tabFarming);
    public static final CupChocolate cupChocolate = new CupChocolate();
    public static final CupWater cupWater = new CupWater();
    public static final CupChrysanthemum cupChrysanthemum = new CupChrysanthemum();
    @Register("Cocoa")
    public static final Item cocoa = new Item().setUnlocalizedName("cocoa").setCreativeTab(ChinaCraft.tabFarming);
    @Register("Ladyfinger")
    public static final ItemFood ladyfinger = (ItemFood) new ItemFood(2, false).setUnlocalizedName("ladyfinger").setCreativeTab(ChinaCraft.tabFarming);
    @Register("Flour")
    public static final Item flour = new Item().setUnlocalizedName("flour").setCreativeTab(ChinaCraft.tabFarming); // 面粉
    @Register("RiceFlour")
    public static final Item riceFlour = new Item().setUnlocalizedName("rice_flour").setCreativeTab(ChinaCraft.tabFarming); // 米粉
    @Register("BarleyRice")
    public static final Item barleyRice = new Item().setUnlocalizedName("barley_rice").setCreativeTab(ChinaCraft.tabFarming); // 麦仁,大麦米
    @Register("XinjiangNutCake")//TODO:错误命名方式,BlockXinjiangNutCake
    public static final BlockCake xinjiangNutCake = (BlockCake) new BlockCCCake("xinjiang_nut_cake");
    @Register("ItemXinjiangNutCake")//TODO:错误命名方式,XinjiangNutCake
    public static final ItemReed itemXinjiangNutCake = (ItemReed) new ItemReed(ChinaCraft.xinjiangNutCake).setUnlocalizedName("xinjiang_nut_cake").setMaxStackSize(1).setCreativeTab(ChinaCraft.tabFarming);
    @Register("BLockAppleCake")//TODO:错误命名方式,BlockAppleCake
    public static final BlockCake appleCake = new BlockCCCake("apple_cake");
    @Register("AppleCake")
    public static final ItemReed itemAppleCake = (ItemReed) new ItemReed(ChinaCraft.appleCake).setUnlocalizedName("apple_cake").setMaxStackSize(1).setCreativeTab(ChinaCraft.tabFarming);

    // 耐火砖
    @Register("BlockFirebrick")
    public static final BlockFirebrick blockFirebrick = new BlockFirebrick(); // 耐火砖块
    @Register("blockFirebrickStructure")//TODO:错误命名方式
    public static final BlockFirebrickStructure blockFirebrickStructure = new BlockFirebrickStructure(); // 耐火砖块(多方块结构)
    @Register("blockPotteryKiln")//TODO:错误命名方式
    public static final BlockPotteryKiln blockPotteryKiln = new BlockPotteryKiln(); // 窑炉核心方块
    @Register("Firebrick")
    public static final Item firebrick = new Item().setUnlocalizedName("firebrick").setCreativeTab(ChinaCraft.tabCore); // 耐火砖
    @Register("ClaySandMixture")
    public static final Item claySandMixture = new Item().setUnlocalizedName("clay_sand_mixture")
            .setCreativeTab(ChinaCraft.tabCore); // 粘土沙子混合物

    // spiritual_magic_figures灵符
    @Register("SpiritualMagicFigures")
    public static final Item spiritualMagicFigures = new Item().setCreativeTab(ChinaCraft.tabCore).setUnlocalizedName("spiritual_magic_figures").setMaxStackSize(16); // 基本灵符
    @Register("SpiritualMagicFiguresFire")
    public static final ItemSMFFire smfFire = new ItemSMFFire(); // 火
    @Register("SpiritualMagicFiguresNightVision")
    public static final ItemSMFPotion smfNightVision = new ItemSMFPotion("spiritual_magic_figures_night_vision", new int[][]{{16, 10000}}); // 夜视
    @Register("SpiritualMagicFiguresPoison")
    public static final ItemSMFPotion smfPoison = new ItemSMFPotion("spiritual_magic_figures_poison", new int[][]{{19, 450, 4}}); // 中毒
    @Register("SpiritualMagicFiguresPower")
    public static final ItemSMFPotion smfPower = new ItemSMFPotion("spiritual_magic_figures_power", new int[][]{{5, 7000}}); // 力量
    @Register("SpiritualMagicFiguresHeal")
    public static final ItemSMFPotion smfProtect = new ItemSMFPotion("spiritual_magic_figures_protect", new int[][]{{12, 3500}, {11, 2500, 3}}); // 保护
    @Register("SpiritualMagicFiguresProtect")
    public static final ItemSMFPotion smfHeal = new ItemSMFPotion("spiritual_magic_figures_heal", new int[][]{{6, 1}, {10, 500}}); // 生命回复
    @Register("SpiritualMagicFiguresSuper")
    public static final ItemSMFSuper smfSuper = new ItemSMFSuper(); // 捉妖符

    // disc
    @Register("three_stanzas_of_plum-blossoms")
    public static final CCMusicDisc three_stanzas = new CCMusicDisc("three_stanzas_of_plum-blossoms");
    @Register("mountain_stream")
    public static final CCMusicDisc mountain_stream = new CCMusicDisc("mountain_stream");
    @Register("the_march_of_the_volunteers")
    public static final CCMusicDisc the_march_of_the_volunteers = new CCMusicDisc("the_march_of_the_volunteers");
    @Register("spring_festival_overture")
    public static final CCMusicDisc spring_festival_overture = new CCMusicDisc("spring_festival_overture");

    @Register("Silkworm")
    public static final ItemSilkworm silkworm = new ItemSilkworm(); // 蚕
    @Register("SilkwormChrysalis")
    public static final Item silkwormChrysalis = new Item().setCreativeTab(ChinaCraft.tabFarming).setUnlocalizedName("silkworm_chrysalis"); // 蚕茧
    @Register("silk_yarn")
    public static final Item silkYarn = new ItemBase().setUnlocalizedName("silk_yarn").setTextureName("chinacraft:silk_yarn").setCreativeTab(ChinaCraft.tabFarming);
    @Register("Silkworm_dead")
    public static final Item itemSilkwormDead = new Item().setUnlocalizedName("silkworm_dead").setTextureName("chinacraft:silkworm_dead").setCreativeTab(ChinaCraft.tabFarming); // 死亡的蚕
    @Register("Moth")
    public static final Item itemMoth = new ItemMoth().setUnlocalizedName("moth"); // 蛾子
    @Register("silk")
    public static final Item itemSilk = new ItemSilk().setUnlocalizedName("silk"); // 丝绸
    @Register("Debug")
    public static final ItemDebug debug = new ItemDebug(); // 调试物品

    static {
        HAMMERSTONE.setRepairItem(new ItemStack(Blocks.stone));
        HAMMERIRON.setRepairItem(new ItemStack(Items.iron_ingot));
        HAMMERDIANMOND.setRepairItem(new ItemStack(Items.diamond));
        BRONZE.setRepairItem(new ItemStack(ChinaCraft.bronzeIngot));
    }

    public static Configuration getMainConfig() {
        return ConfigLoader.getMainConfig();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        NEIIsLoad = Loader.isModLoaded("NotEnoughItems");
        WAILAIsLoad = Loader.isModLoaded("Waila");
        VersionCheckerIsLoad = Loader.isModLoaded("VersionChecker");
        
        proxy.preInit(event);

        Network = NetworkRegistry.INSTANCE.newSimpleChannel("ChinaCraftChannel");
        Network.registerMessage(new RedPacketMessageHandler(), RedPacketMessage.class, 0, Side.SERVER);
        Network.registerMessage(new KeyMessageHandler(), KeyMessage.class, 1, Side.SERVER);

        versionChecker = new MinecraftModVersionChecker(ChinaCraft.class,"ChinaCraft 华夏文明",PROJECT_ID,log,FeatureConfig.EnableSanpShot);
        if (FeatureConfig.EnableUpdate) versionChecker.start();
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

    @Override
    public boolean canInvoker(Object input) {
        return true;
    }
}
