package unstudio.chinacraft.common;

import cpw.mods.fml.common.Loader;
import net.minecraft.block.BlockCake;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import unstudio.chinacraft.block.*;
import unstudio.chinacraft.block.decoration.*;
import unstudio.chinacraft.block.especial.*;
import unstudio.chinacraft.block.generation.ore.CopperOre;
import unstudio.chinacraft.block.generation.ore.SilverOre;
import unstudio.chinacraft.block.generation.ore.TinOre;
import unstudio.chinacraft.block.generation.plant.*;
import unstudio.chinacraft.block.model.CCLamp;
import unstudio.chinacraft.block.model.CCModelBlock;
import unstudio.chinacraft.block.model.Lantern;
import unstudio.chinacraft.client.model.ModelLanternScaldfish;
import unstudio.chinacraft.common.network.RedPacketMessage;
import unstudio.chinacraft.common.network.RedPacketMessageHandler;
import unstudio.chinacraft.entity.EntityRegister;
import unstudio.chinacraft.event.ListenerRegister;
import unstudio.chinacraft.item.*;
import unstudio.chinacraft.item.combat.*;
import unstudio.chinacraft.item.jade.Jade;
import unstudio.chinacraft.item.jade.JadeKnife;
import unstudio.chinacraft.item.jade.JadeOre;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemReed;
import net.minecraft.util.StatCollector;
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
import unstudio.chinacraft.item.jade.JadePinkSystem;
import unstudio.chinacraft.util.VersionChecker;
import unstudio.chinacraft.util.config.ConfigLoader;
import unstudio.forgebukkitbridge.ServerManager;
import unstudio.forgebukkitbridge.VaultPlugin;

import javax.swing.*;

import java.util.Random;

@Mod(modid = ChinaCraft.MODID, name = ChinaCraft.NAME, version = ChinaCraft.VERSION)
public class ChinaCraft {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "This is a Minecraft Forge Mod , you can't run it!", "Chinacraft : mccraft.cn", JOptionPane.OK_OPTION);
        System.exit(0);
    }

    public static final String MODID = "chinacraft";
    public static final String NAME = "ChinaCraft";
    public static final String VERSION = "172";
    public static final int OutPutVERSION = 172;
    public static boolean NEIIsLoad = false;
    public static VaultPlugin vault = null;

    public static SimpleNetworkWrapper Network;

    @SidedProxy(clientSide = "unstudio.chinacraft.common.ClientProxy",
            serverSide = "unstudio.chinacraft.common.CommonProxy")
    public static CommonProxy proxy;

    @Instance("chinacraft")
    public static ChinaCraft instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        Network = NetworkRegistry.INSTANCE.newSimpleChannel("ChinaCraftChannel");
        Network.registerMessage(new RedPacketMessageHandler(), RedPacketMessage.class, 0, Side.SERVER);
        NEIIsLoad = Loader.isModLoaded("NotEnoughItems");
//        Network.registerMessage(BaseMessage.Handler.class, BaseMessage.class, 1, Side.CLIENT);
        new Thread(versionChecker).start();
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
    public void onServerStarted(FMLServerStartedEvent event){
    	vault=ServerManager.getVaultPlugin();
    }

    public static Configuration getMainConfig() {
        return ConfigLoader.getMainConfig();
    }

    public static CreativeTabs tabCore = new CreativeTabs(StatCollector.translateToLocal("core")) {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return Item.getItemFromBlock(copperOre);
        }
    };

    public static CreativeTabs tabTool = new CreativeTabs(StatCollector.translateToLocal("tool")) {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return bronzePickaxe;
        }
    };

    public static CreativeTabs tabPlant = new CreativeTabs(StatCollector.translateToLocal("plant")) {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return rices;
        }
    };
    //特殊变量
    public static JadePinkSystem jadePinkSystem = new JadePinkSystem();
    public static VersionChecker versionChecker = new VersionChecker();
    public static boolean haveWarnedVersionOutOfDate = false;
    public static Random rand = new Random();

    //Material
    public static Item.ToolMaterial BRONZE = EnumHelper.addToolMaterial("BRONZE", 2, 230, 6.0F, 2.0F, 1);
    public static Item.ToolMaterial HAMMERSTONE = EnumHelper.addToolMaterial("HAMMERSIONE", 1, 240, 4.0F, 2.0F, 5); //石锤
    public static Item.ToolMaterial HAMMERIRON = EnumHelper.addToolMaterial("HAMMERIRON", 2, 475, 6.0F, 3.0F, 14); //铁锤
    public static Item.ToolMaterial HAMMERDIANMOND = EnumHelper.addToolMaterial("HAMMERDIAMOND", 3, 2096, 8.0F, 4.0F, 10); //钻石锤
    public static Item.ToolMaterial YANGLONG = EnumHelper.addToolMaterial("yanlong", 3, 2568, 8.0F, 6.0F, 10); //BLGiantSword
    public static Item.ToolMaterial BROAD_BRONZE = EnumHelper.addToolMaterial("BROAD_BRONZE", 2, 230, 6.0F, 2.5F, 1);

    //方块
    public static CopperOre copperOre = new CopperOre();  //铜矿
    public static BlockBronze bronzeBlock = new BlockBronze();  //青铜块
    public static TinOre tinOre = new TinOre(); //锡矿
    public static JadeOre jadeOre = new JadeOre(); //玉原石
    public static Marble marble = new Marble();  //大理石
    public static SmoothMarble smoothMarble = new SmoothMarble(); //平滑大理石块
    public static PillarMarble pillarMarble = new PillarMarble(); //条纹大理石块
    public static ChiseledMarble chiseledMarble = new ChiseledMarble(); //錾制大理石块
    public static MarbleStair marbleStair = new MarbleStair(); //大理石楼梯
    public static MarbleSlab marbleDoubleSlab = new MarbleSlab(true); //大理石半砖
    public static MarbleSlab marbleSlab = (MarbleSlab) new MarbleSlab(false).setCreativeTab(ChinaCraft.tabCore); //大理石半砖
    public static SilverOre silverOre = new SilverOre();  //银
    public static WoodenWindow woodenWindow1 = new WoodenWindow("chinacraft:wooden_window_1", "chinacraft:wooden_window_top"); //木窗框1
    public static WoodenWindow woodenWindow2 = new WoodenWindow("chinacraft:wooden_window_2", "chinacraft:wooden_window_top"); //木窗框2
    public static WoodenWindow woodenWindow3 = new WoodenWindow("chinacraft:wooden_window_3", "chinacraft:wooden_window_top"); //木窗框3
    public static WoodenWindow woodenWindow4 = new WoodenWindow("chinacraft:wooden_window_4", "chinacraft:wooden_window_top"); //木窗框3
    public static WoodenWindow woodenWindowdragon = new WoodenWindow("chinacraft:wooden_window_dragon", "chinacraft:wooden_window_top"); //木窗框Logo
//    public static RiceGrow riceGrow = new RiceGrow(); //水稻作物
    public static CCGrowablePlant riceGrow = new CCGrowablePlant("rice",5,ChinaCraft.rices,ChinaCraft.lcker); //水稻作物
    //    public static SoyGrow soyGrow = new SoyGrow(); //大豆作物
    public static CCGrowablePlant soyGrow = new CCGrowablePlant("soy",5,ChinaCraft.soyPod,ChinaCraft.soyPod); //大豆作物
    public static CCGrowablePlant blockGlutinousRice = new CCGrowablePlant("glutinous_rice",7,ChinaCraft.glutinousRice,ChinaCraft.glutinousRice);//糯米作物
    public static BlockBamboo blockBamboo = new BlockBamboo();  //竹子方块
    public static BambooShoot bambooShoot = new BambooShoot(); //竹笋
    public static MulberryLog mulberryLog = new MulberryLog(); //桑树原木
    public static MulberryLeaf mulberryLeaf = new MulberryLeaf(); //桑树树叶
    public static MulberrySapling mulberrySapling = new MulberrySapling(); //桑树树苗
    public static MulberryWood mulberryWood = new MulberryWood(); //桑树木板
    public static CCFlower azalea = new CCFlower("azalea");
    public static CCFlower peony = new CCFlower("peony");
    public static Block bambooSlab = new BlockBase(Material.wood).setBlockName("bamboo_slab").setCreativeTab(ChinaCraft.tabCore).setStepSound(Block.soundTypeWood); //竹木板
    public static JadeWorkingTable jadeWorkingTable = new JadeWorkingTable(); //玉石工作台
    public static CCInstruments blockDrum = new CCInstruments("drum",Material.wood,true,"note.drum",20);
    public static CCLamp lanternScaldfishOpenable = new CCLamp(Material.cake,new ModelLanternScaldfish(),"lantern_scaldfish");
    public static CCModelBlock lanternScaldfish = new CCModelBlock(Material.cake,new ModelLanternScaldfish(),"lantern_scaldfish");

    //TraditionalCarpet
    public static TraditionalCarpet redCarpet = new TraditionalCarpet("red_carpet", "chinacraft:red_carpet");
    public static TraditionalCarpet silkCarpet = new TraditionalCarpet("slik_carpet", "chinacraft:slik_carpet");
    public static TraditionalCarpet slik_right = new TraditionalCarpet("slik_right", "chinacraft:slik_right");
    public static TraditionalCarpet slik_left = new TraditionalCarpet("slik_left", "chinacraft:slik_left");
    public static TraditionalCarpet silk_right_down = new TraditionalCarpet("silk_right_down", "chinacraft:silk_right_down");
    public static TraditionalCarpet silk_right_up = new TraditionalCarpet("silk_right_up", "chinacraft:silk_right_up");
    public static TraditionalCarpet silk_left_up = new TraditionalCarpet("silk_left_up", "chinacraft:silk_left_up");
    public static TraditionalCarpet silk_left_down = new TraditionalCarpet("silk_left_down", "chinacraft:silk_left_down");

    public static BlockPotteryTable potteryTable = new BlockPotteryTable(); //陶瓷工作台
    public static Item itemPotteryTable = new ItemReed(ChinaCraft.potteryTable).setUnlocalizedName("potterytable").setCreativeTab(ChinaCraft.tabCore); //陶瓷工作台
    public static BlockPotteryBase blockPotteryBase = new BlockPotteryBase(); //陶瓷
    public static BlockBuckpot blockBuckpot = new BlockBuckpot(); //陶锅

    public static BlockBuhrimill buhrimill = new BlockBuhrimill(); //石磨

    public static Lantern lantern = new Lantern(); //灯笼
    public static BlockWoodenBucket blockWoodenBucket = new BlockWoodenBucket(); //木桶

    public static BlockCookingBench cooking_bench_off = new BlockCookingBench(false); //灶台
    public static BlockCookingBench cooking_bench_on = new BlockCookingBench(true); //灶台

    public static BlockSericultureFrame sericultureFrame = new BlockSericultureFrame(); //养蚕架

    //物品
    public static Item copperIngot = new Item().setUnlocalizedName("copper_ingot").setCreativeTab(ChinaCraft.tabCore);
    public static Item bronzeIngot = new Item().setUnlocalizedName("bronze_ingot").setCreativeTab(ChinaCraft.tabCore);  //青铜锭
    public static Item tinIngot = new Item().setUnlocalizedName("tin_ingot").setCreativeTab(ChinaCraft.tabCore);  //铜锭
    public static Item silverIngot = new Item().setUnlocalizedName("silver_ingot").setCreativeTab(ChinaCraft.tabCore); //银锭
    public static Item copperTinMixedPowder = new Item().setUnlocalizedName("copper_tin_mixed_powder").setCreativeTab(ChinaCraft.tabCore); //铜锡混合矿粉
    public static CCCropPlantItem rices = (CCCropPlantItem) new CCCropPlantItem(ChinaCraft.riceGrow).setUnlocalizedName("rices"); //米
    public static Item lcker = new Item().setUnlocalizedName("lcker").setCreativeTab(ChinaCraft.tabPlant); //米穗
    public static CCCropPlantItem soy = (CCCropPlantItem) new CCCropPlantItem(ChinaCraft.soyGrow).setUnlocalizedName("soy"); //大豆
    public static CCCropPlantItem glutinousRice = (CCCropPlantItem) new CCCropPlantItem(ChinaCraft.blockGlutinousRice).setUnlocalizedName("glutinous_rice"); // 糯米
    public static Item bamboo = new Item().setCreativeTab(ChinaCraft.tabPlant).setUnlocalizedName("bamboo"); //竹子
    public static Item soyPod = new Item().setUnlocalizedName("soy_pod").setCreativeTab(ChinaCraft.tabPlant); //大豆荚
    public static Item itemBuhrimill = new ItemReed(ChinaCraft.buhrimill).setUnlocalizedName("buhrimill").setCreativeTab(ChinaCraft.tabCore); //石磨
    public static Item itemSericultureFrame = new ItemReed(ChinaCraft.sericultureFrame).setUnlocalizedName("sericulture_frame").setCreativeTab(ChinaCraft.tabCore); //养蚕架
    public static Item itemMulberryLeaf = new Item().setUnlocalizedName("mulberry_leaf").setCreativeTab(ChinaCraft.tabPlant); //桑叶
    public static ItemWoodenBucket woodenBucket = new ItemWoodenBucket(Blocks.air); //木桶
    public static ItemWoodenBucket woodenBucket_Water = new ItemWoodenBucket(Blocks.flowing_water); //木水桶
    public static Silkworm silkworm = new Silkworm(); //蚕
    public static Item silkwormChrysalis = new Item().setCreativeTab(ChinaCraft.tabPlant).setUnlocalizedName("silkworm_chrysalis"); //蚕茧
    public static ItemRedPacket redPacket = new ItemRedPacket(); //红包
    public static ItemFirecracker firecracker = new ItemFirecracker();
    public static ItemBomb bomb = new ItemBomb();
    public static ItemBlackDogBlood blackDogBlood = new ItemBlackDogBlood();
    public static Item moonCake = new Item().setUnlocalizedName("moon_cake").setCreativeTab(ChinaCraft.tabPlant);

    //防具武器
    public static BronzeSword bronzeSword = new BronzeSword();  //青铜剑
    public static BronzeBroadSword bronzeBroadSword = new BronzeBroadSword(BROAD_BRONZE, null);  //青铜大刀
    public static BronzeBroadSword bronzeBroadSwordGreen = new BronzeBroadSword(BROAD_BRONZE, "green");  //青铜大刀Green
    public static BronzeBroadSword bronzeBroadSwordGreen2 = new BronzeBroadSword(BROAD_BRONZE, "green2");  //青铜大刀Green2
    public static BronzeBroadSword bronzeBroadSwordPink = new BronzeBroadSword(BROAD_BRONZE, "pink");  //青铜大刀Pink
    public static BronzeBroadSword bronzeBroadSwordPurple = new BronzeBroadSword(BROAD_BRONZE, "purple");  //青铜大刀purple
    public static BLGiantSword blGiantSword = new BLGiantSword(ChinaCraft.YANGLONG);  //炎龙巨刀

    public static ModelArmor chinaCrown = new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "china_crown", "chinacrown", 0, 1);
    public static ModelArmor[] nightClothes = new ModelArmor[]{new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_head", "nightclothes", 1, 0, 1),new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_body", "nightclothes", 1, 1, 1),new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_leg", "nightclothes", 1, 2, 1),new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_shoe", "nightclothes", 1, 3, 1)};
//    public static ModelArmor nightClothesHead = new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_head", "nightclothes", 1, 0, 1);
//    public static ModelArmor nightClothesBody = new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_body", "nightclothes", 1, 1, 1);
//    public static ModelArmor nightClothesLeg = new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_leg", "nightclothes", 1, 2, 1);
//    public static ModelArmor nightClothesShoe = new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_shoe", "nightclothes", 1, 3, 1);
    public static Mace mace = new Mace();

    public static ItemSuperBow superBow = new ItemSuperBow();
    public static Item superArrow = new Item().setCreativeTab(ChinaCraft.tabTool).setUnlocalizedName("super_arrow");

    //工具
    public static BronzePickaxe bronzePickaxe = new BronzePickaxe();//青铜稿
    public static BronzeAxe bronzeAxe = new BronzeAxe();//青铜斧
    public static BronzeHoe bronzeHoe = new BronzeHoe();//青铜锄
    public static BronzeShovel bronzeShovel = new BronzeShovel();//青铜铲
    public static JiuQu_tang jiuqu_tang = new JiuQu_tang();//九曲镋
    public static JadeKnife jadeKnife = new JadeKnife();//玉石切割刀
    public static ItemArtKnife artKnife = new ItemArtKnife();//美工切割刀

    public static Hammer hammerStone = new Hammer(ChinaCraft.HAMMERSTONE, "stone");//石锤
    public static Hammer hammerIron = new Hammer(ChinaCraft.HAMMERIRON, "iron");//铁锤
    public static Hammer hammerDiamond = new Hammer(ChinaCraft.HAMMERDIANMOND, "diamond");//钻石锤
    public static Hammer hammerBronze = new Hammer(ChinaCraft.HAMMERIRON, "bronze");//钻石锤

    public static int bronzeArmorTexture = -1; //青铜套装外部材质注册
    public static ItemArmor bronzeHelmet;//青铜头盔
    public static ItemArmor bronzeChestplate;//青铜胸甲
    public static ItemArmor bronzeLeggings;//青铜护腿
    public static ItemArmor bronzeBoots;//青铜靴子

    //玉石
    public static Jade jadeGreenItem = new Jade("jade_green");
    public static Jade jadeGreen2Item = new Jade("jade_green2");
    public static Jade jadePinkItem = new Jade("jade_pink");
    public static Jade jadePurpleItem = new Jade("jade_purple");

    //Drink、Food
    public static Item cup = new Item().setUnlocalizedName("cup").setCreativeTab(ChinaCraft.tabPlant); //杯
    public static Item cup_Clay = new Item().setUnlocalizedName("cpu_clay").setCreativeTab(ChinaCraft.tabPlant);
    public static CupChocolate cupChocolate = new CupChocolate();
    public static Item cocoa = new Item().setUnlocalizedName("cocoa").setCreativeTab(ChinaCraft.tabPlant);
    public static ItemFood ladyfinger = (ItemFood) new ItemFood(2, false).setUnlocalizedName("ladyfinger").setCreativeTab(ChinaCraft.tabPlant);
    public static CupWater cupWater = new CupWater();
    public static CupChrysanthemum cupChrysanthemum = new CupChrysanthemum();
    public static Item flour = new Item().setUnlocalizedName("flour").setCreativeTab(ChinaCraft.tabPlant); //面粉
    public static Item riceFlour = new Item().setUnlocalizedName("rice_flour").setCreativeTab(ChinaCraft.tabPlant); //米粉
    public static Item barleyRice = new Item().setUnlocalizedName("barley_rice").setCreativeTab(ChinaCraft.tabPlant); //麦仁,大麦米
    public static BlockCake xinjiangNutCake = (BlockCake) new BlockCCCake("xinjiang_nut_cake").setCreativeTab(ChinaCraft.tabPlant);
    public static BlockCake appleCake = new BlockCCCake("apple_cake");
    public static ItemReed itemAppleCake = (ItemReed) new ItemReed(ChinaCraft.appleCake).setUnlocalizedName("apple_cake").setMaxStackSize(1).setCreativeTab(ChinaCraft.tabPlant);

    //耐火砖
    public static BlockFirebrick blockFirebrick = new BlockFirebrick(); //耐火砖块
    public static BlockFirebrickStructure blockFirebrickStructure = new BlockFirebrickStructure(); //耐火砖块(多方块结构)
    public static BlockPotteryKiln blockPotteryKiln = new BlockPotteryKiln(); //窑炉核心方块
    public static Item firebrick = new Item().setUnlocalizedName("firebrick").setCreativeTab(ChinaCraft.tabCore); //耐火砖
    public static Item claySandMixture = new Item().setUnlocalizedName("clay_sand_mixture").setCreativeTab(ChinaCraft.tabCore); //粘土沙子混合物


    //spiritual_magic_figures灵符
    public static Item spiritualMagicFigures = new Item().setCreativeTab(ChinaCraft.tabCore).setUnlocalizedName("spiritual_magic_figures").setMaxStackSize(16); //基本灵符
    public static ItemSMFFire smfFire = new ItemSMFFire(); //火
    public static ItemSMFPotion smfNightVision = new ItemSMFPotion("spiritual_magic_figures_night_vision", new int[][]{{16, 10000}}); //夜视
    public static ItemSMFPotion smfPoison = new ItemSMFPotion("spiritual_magic_figures_poison", new int[][]{{19, 450, 4}}); //中毒
    public static ItemSMFPotion smfPower = new ItemSMFPotion("spiritual_magic_figures_power", new int[][]{{5, 7000}}); //力量
    public static ItemSMFPotion smfProtect = new ItemSMFPotion("spiritual_magic_figures_protect", new int[][]{{12, 3500}, {11, 2500, 3}}); //保护
    public static ItemSMFPotion smfHeal = new ItemSMFPotion("spiritual_magic_figures_heal", new int[][]{{6, 1}, {10, 500}}); //生命回复
    public static ItemSMFSuper smfSuper = new ItemSMFSuper(); //捉妖符

    //disc
    public static CCMusicDisc three_stanzas = new CCMusicDisc("three_stanzas_of_plum-blossoms");
    public static CCMusicDisc mountain_stream = new CCMusicDisc("mountain_stream");
    public static CCMusicDisc the_march_of_the_volunteers = new CCMusicDisc("the_march_of_the_volunteers");

    public static ItemDebug debug = new ItemDebug(); //调试物品

}
