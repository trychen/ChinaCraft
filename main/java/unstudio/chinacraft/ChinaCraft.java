package unstudio.chinacraft;

import cpw.mods.fml.common.Loader;
import net.minecraft.block.BlockCake;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import unstudio.chinacraft.block.*;
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
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import unstudio.chinacraft.network.BaseMessage;
import unstudio.chinacraft.util.VersionChecker;
import javax.swing.*;

@Mod(modid = ChinaCraft.MODID, name = ChinaCraft.NAME, version = ChinaCraft.VERSION)
public class ChinaCraft {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null,"This is a Minecraft Forge Mod , you can't run it!","Chinacraft : mccraft.cn",JOptionPane.OK_OPTION);
        System.exit(0);
    }
    public static final String MODID = "chinacraft";
    public static final String NAME = "ChinaCraft";
    public static final String VERSION = "170";
    public static final int OutPutVERSION = 170;
    public static boolean NEIIsLoad = false;

    public static SimpleNetworkWrapper Network;

    @SidedProxy(clientSide = "unstudio.chinacraft.ClientProxy",
            serverSide = "unstudio.chinacraft.CommonProxy")
    public static CommonProxy proxy;

    @Instance("chinacraft")
    public static ChinaCraft instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        NEIIsLoad = Loader.isModLoaded("NotEnoughItems");
        Network = NetworkRegistry.INSTANCE.newSimpleChannel("ChinaCraftChannel");
        Network.registerMessage(BaseMessage.Handler.class, BaseMessage.class, 0, Side.SERVER);
        Network.registerMessage(BaseMessage.Handler.class, BaseMessage.class, 1, Side.CLIENT);
        new Thread(versionChecker).start();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        ListenerRegister.init();
        EntityRegister.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
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
    //鐗规畩鍙橀噺
    public static int jadehasHealTicker = 0;
    public static VersionChecker versionChecker= new VersionChecker();
    public static boolean haveWarnedVersionOutOfDate = false;

    //Material
    public static Item.ToolMaterial BRONZE = EnumHelper.addToolMaterial("BRONZE", 2, 230, 6.0F, 2.0F, 1);
    public static Item.ToolMaterial HAMMERSTONE = EnumHelper.addToolMaterial("HAMMERSIONE", 1, 240, 4.0F, 2.0F, 5); //鐭抽敜
    public static Item.ToolMaterial HAMMERIRON = EnumHelper.addToolMaterial("HAMMERIRON", 2, 475, 6.0F, 3.0F, 14); //閾侀敜
    public static Item.ToolMaterial HAMMERDIANMOND = EnumHelper.addToolMaterial("HAMMERDIAMOND", 3, 2096, 8.0F, 4.0F, 10); //閽荤煶閿�
    public static Item.ToolMaterial yanlong = EnumHelper.addToolMaterial("yanlong", 3, 2568, 8.0F, 6.0F, 10); //YanLung_Giantknife
    public static Item.ToolMaterial _BRONZE = EnumHelper.addToolMaterial("BRONZEBIG", 2, 230, 6.0F, 2.5F, 1);

    //鏂瑰潡
    public static CopperOre copperOre = new CopperOre();  //閾滅熆
    public static BronzeBlock bronzeBlock = new BronzeBlock();  //闈掗摐鍧�
    public static TinOre tinOre = new TinOre(); //閿＄熆
    public static JadeOre jadeOre = new JadeOre(); //鐜夊師鐭�
    public static Marble marble = new Marble();  //澶х悊鐭�
    public static SmoothMarble smoothMarble = new SmoothMarble(); //骞虫粦澶х悊鐭冲潡
    public static PillarMarble pillarMarble = new PillarMarble(); //鏉＄汗澶х悊鐭冲潡
    public static ChiseledMarble chiseledMarble = new ChiseledMarble(); //閷惧埗澶х悊鐭冲潡
    public static MarbleStair marbleStair = new MarbleStair(); //澶х悊鐭虫ゼ姊�
    public static MarbleSlab marbleDoubleSlab = new MarbleSlab(true); //澶х悊鐭冲崐鐮�
    public static MarbleSlab marbleSlab = (MarbleSlab) new MarbleSlab(false).setCreativeTab(ChinaCraft.tabCore); //澶х悊鐭冲崐鐮�
    public static SilverOre silverOre = new SilverOre();  //閾�
    public static RiceGrow riceGrow = new RiceGrow(); //姘寸ɑ浣滅墿
    public static WoodenWindow woodenWindow1 = new WoodenWindow("chinacraft:wooden_window_1", "chinacraft:wooden_window_top"); //鏈ㄧ獥妗�1
    public static WoodenWindow woodenWindow2 = new WoodenWindow("chinacraft:wooden_window_2", "chinacraft:wooden_window_top"); //鏈ㄧ獥妗�2
    public static WoodenWindow woodenWindow3 = new WoodenWindow("chinacraft:wooden_window_3", "chinacraft:wooden_window_top"); //鏈ㄧ獥妗�3
    public static WoodenWindow woodenWindow4 = new WoodenWindow("chinacraft:wooden_window_4", "chinacraft:wooden_window_top"); //鏈ㄧ獥妗�3
    public static WoodenWindow woodenWindowdragon = new WoodenWindow("chinacraft:wooden_window_dragon", "chinacraft:wooden_window_top"); //鏈ㄧ獥妗哃ogo
    public static SoyGrow soyGrow = new SoyGrow(); //澶ц眴浣滅墿
    public static BlockBamboo blockBamboo = new BlockBamboo();  //绔瑰瓙鏂瑰潡
    public static BambooShoot bambooShoot = new BambooShoot(); //绔圭瑡
    public static MulberryLog mulberryLog = new MulberryLog(); //妗戞爲鍘熸湪
    public static MulberryLeaf mulberryLeaf = new MulberryLeaf(); //妗戞爲鏍戝彾
    public static MulberrySapling mulberrySapling = new MulberrySapling(); //妗戞爲鏍戣嫍
    public static MulberryWood mulberryWood = new MulberryWood(); //妗戞爲鏈ㄦ澘
    public static Block bambooBlock = new BlockBase(Material.wood).setBlockName("bamboo_block").setCreativeTab(ChinaCraft.tabCore).setStepSound(Block.soundTypeWood); //绔规湪鏉�
    public static JadeWorkingTable jadeWorkingTable = new JadeWorkingTable(); //鐜夌煶宸ヤ綔鍙�
    //TraditionalCarpet
    public static TraditionalCarpet redCarpet = new TraditionalCarpet("red_carpet", "chinacraft:red_carpet");
    public static TraditionalCarpet silkCarpet = new TraditionalCarpet("slik_carpet", "chinacraft:slik_carpet");
    public static TraditionalCarpet slik_right = new TraditionalCarpet("slik_right", "chinacraft:slik_right");
    public static TraditionalCarpet slik_left = new TraditionalCarpet("slik_left", "chinacraft:slik_left");
    public static TraditionalCarpet silk_right_down = new TraditionalCarpet("silk_right_down", "chinacraft:silk_right_down");
    public static TraditionalCarpet silk_right_up = new TraditionalCarpet("silk_right_up", "chinacraft:silk_right_up");
    public static TraditionalCarpet silk_left_up = new TraditionalCarpet("silk_left_up", "chinacraft:silk_left_up");
    public static TraditionalCarpet silk_left_down = new TraditionalCarpet("silk_left_down", "chinacraft:silk_left_down");

    public static PotteryTable potteryTable = new PotteryTable(); //闄剁摲宸ヤ綔鍙�
    public static BlockPotteryBase blockPotteryBase = new BlockPotteryBase(); //闄剁摲
    public static BlockBuckpot blockBuckpot = new BlockBuckpot(); //闄堕攨

    public static Buhrimill buhrimill = new Buhrimill(); //鐭崇（

    public static Lantern lantern = new Lantern(); //鐏
    public static BlockWoodenBucket blockWoodenBucket = new BlockWoodenBucket(); //鏈ㄦ《

    public static Cooker cooker_off = new Cooker(false); //鐏跺彴
    public static Cooker cooker_on = new Cooker(true); //鐏跺彴

    public static SericultureFrame sericultureFrame = new SericultureFrame(); //鍏昏殨鏋�

    //鐗╁搧
    public static Item tinPowder = new Item().setUnlocalizedName("tin_powder").setCreativeTab(ChinaCraft.tabCore);
    public static Item copperPowder = new Item().setUnlocalizedName("copper_powder").setCreativeTab(ChinaCraft.tabCore); 
    public static Item copperIngot = new Item().setUnlocalizedName("copper_ingot").setCreativeTab(ChinaCraft.tabCore);
    public static Item bronzeIngot = new Item().setUnlocalizedName("bronze_ingot").setCreativeTab(ChinaCraft.tabCore);  //闈掗摐閿�
    public static Item tinIngot = new Item().setUnlocalizedName("tin_ingot").setCreativeTab(ChinaCraft.tabCore);  //閾滈敪
    public static Item silverIngot = new Item().setUnlocalizedName("silver_ingot").setCreativeTab(ChinaCraft.tabCore); //閾堕敪
    public static Item copperTinMixedPowder = new Item().setUnlocalizedName("copper_tin_mixed_powder").setCreativeTab(ChinaCraft.tabCore); //閾滈敗娣峰悎鐭跨矇
    public static CropPlant rices = (CropPlant) new CropPlant(ChinaCraft.riceGrow).setUnlocalizedName("rices"); //绫�
    public static Item lcker = new Item().setUnlocalizedName("lcker").setCreativeTab(ChinaCraft.tabPlant); //绫崇
    public static CropPlant soy = (CropPlant) new CropPlant(ChinaCraft.soyGrow).setUnlocalizedName("soy"); //澶ц眴
    public static Item bamboo = new Item().setCreativeTab(ChinaCraft.tabPlant).setUnlocalizedName("bamboo"); //绔瑰瓙
    public static Item soyPod = new Item().setUnlocalizedName("soy_pod").setCreativeTab(ChinaCraft.tabPlant); //澶ц眴鑽�
    public static ItemReed itemBuhrimill = (ItemReed) new ItemReed(ChinaCraft.buhrimill).setUnlocalizedName("buhrimill").setMaxStackSize(1).setCreativeTab(ChinaCraft.tabCore); //鐭崇（
    public static Item itemMulberryLeaf = new Item().setUnlocalizedName("mulberry_leaf").setCreativeTab(ChinaCraft.tabPlant); //妗戝彾
    public static WoodenBucket woodenBucket = new WoodenBucket(Blocks.air); //鏈ㄦ《
    public static WoodenBucket woodenBucket_Water = new WoodenBucket(Blocks.flowing_water); //鏈ㄦ按妗�
    public static Silkworm silkworm = new Silkworm(); //铓�
    public static Item silkwormChrysalis = new Item().setCreativeTab(ChinaCraft.tabPlant).setUnlocalizedName("silkworm_chrysalis"); //铓曡導
    public static ItemRedPacket redPacket = new ItemRedPacket(); //绾㈠寘
    public static ItemFirecracker firecracker = new ItemFirecracker();
    public static ItemBomb bomb = new ItemBomb();
    public static ItemBlackDogBlood blackDogBlood = new ItemBlackDogBlood();
    public static Item moonCake = new Item().setUnlocalizedName("moon_cake").setCreativeTab(ChinaCraft.tabPlant);

    //闃插叿姝﹀櫒
    public static BronzeSword bronzeSword = new BronzeSword();  //闈掗摐鍓�
    public static BronzeBroadSword bronzeBroadSword = new BronzeBroadSword("bronze_bigsword");  //闈掗摐澶у垁
    public static BronzeBroadSword bronzeBroadSwordGreen = new BronzeBroadSword("bronze_bigsword_green");  //闈掗摐澶у垁Green
    public static BronzeBroadSword bronzeBroadSwordGreen2 = new BronzeBroadSword("bronze_bigsword_green2");  //闈掗摐澶у垁Green2
    public static BronzeBroadSword bronzeBroadSwordPink = new BronzeBroadSword("bronze_bigsword_pink");  //闈掗摐澶у垁Pink
    public static BronzeBroadSword bronzeBroadSwordPurple = new BronzeBroadSword("bronze_bigsword_purple");  //闈掗摐澶у垁purple
    public static YanLung_Giantknife yanLung_Giantknife = new YanLung_Giantknife();  //鐐庨緳宸ㄥ垁

    public static ModelArmor chinaCrown = new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "china_crown", "chinacrown",0,1);
    public static ModelArmor nightClothesHead = new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_head", "nightclothes",1,0,1);
    public static ModelArmor nightClothesBody = new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_body", "nightclothes",1,1,1);
    public static ModelArmor nightClothesLeg = new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_leg", "nightclothes",1,2,1);
    public static ModelArmor nightClothesShoe = new ModelArmor(ItemArmor.ArmorMaterial.CLOTH, "night_clothes_shoe", "nightclothes",1,3,1);
    public static Mace mace = new Mace();

    //宸ュ叿
    public static BronzePickaxe bronzePickaxe = new BronzePickaxe();//闈掗摐绋�
    public static BronzeAxe bronzeAxe = new BronzeAxe();//闈掗摐鏂�
    public static BronzeHoe bronzeHoe = new BronzeHoe();//闈掗摐閿�
    public static BronzeShovel bronzeShovel = new BronzeShovel();//闈掗摐閾�
    public static JiuQu_tang jiuqu_tang = new JiuQu_tang();//涔濇洸闀�
    public static JadeKnife jadeKnife = new JadeKnife();//鐜夌煶鍒囧壊鍒�
    public static ItemArtKnife artKnife = new ItemArtKnife();//缇庡伐鍒囧壊鍒�

    public static Hammer hammerStone = new Hammer(ChinaCraft.HAMMERSTONE, "stone");//鐭抽敜
    public static Hammer hammerIron = new Hammer(ChinaCraft.HAMMERIRON, "iron");//閾侀敜
    public static Hammer hammerDiamond = new Hammer(ChinaCraft.HAMMERDIANMOND, "diamond");//閽荤煶閿�
    public static Hammer hammerBronze = new Hammer(ChinaCraft.HAMMERIRON, "bronze");//閽荤煶閿�

    public static int bronzeArmorTexture = -1; //闈掗摐濂楄澶栭儴鏉愯川娉ㄥ唽
    public static ItemArmor bronzeHelmet;//闈掗摐澶寸洈
    public static ItemArmor bronzeChestplate;//闈掗摐鑳哥敳
    public static ItemArmor bronzeLeggings;//闈掗摐鎶よ吙
    public static ItemArmor bronzeBoots;//闈掗摐闈村瓙

    //鐜夌煶
    public static Jade jadeGreenItem = new Jade("jade_green");
    public static Jade jadeGreen2Item = new Jade("jade_green2");
    public static Jade jadePinkItem = new Jade("jade_pink");
    public static Jade jadePurpleItem = new Jade("jade_purple");

    //Drink銆丗ood
    public static Item cup = new Item().setUnlocalizedName("cup").setCreativeTab(ChinaCraft.tabPlant); //鏉�
    public static Item cup_Clay = new Item().setUnlocalizedName("cpu_clay").setCreativeTab(ChinaCraft.tabPlant);
    public static CupChocolate cupChocolate = new CupChocolate();
    public static Item cocoa = new Item().setUnlocalizedName("cocoa").setCreativeTab(ChinaCraft.tabPlant);
    public static ItemFood ladyfinger = (ItemFood) new ItemFood(2, false).setUnlocalizedName("ladyfinger").setCreativeTab(ChinaCraft.tabPlant);
    public static CupWater cupWater = new CupWater();
    public static CupChrysanthemum cupChrysanthemum = new CupChrysanthemum();
    public static Item flour = new Item().setUnlocalizedName("flour").setCreativeTab(ChinaCraft.tabPlant); //闈㈢矇
    public static Item riceFlour = new Item().setUnlocalizedName("rice_flour").setCreativeTab(ChinaCraft.tabPlant); //绫崇矇
    public static Item barleyRice = new Item().setUnlocalizedName("barley_rice").setCreativeTab(ChinaCraft.tabPlant); //楹︿粊,澶ч害绫�
    public static BlockCake xinjiangNutCake = (BlockCake) new BlockCCCake("xinjiang_nut_cake").setCreativeTab(ChinaCraft.tabPlant);
    public static BlockCake appleCake = new BlockCCCake("apple_cake");
    public static ItemReed itemAppleCake = (ItemReed) new ItemReed(ChinaCraft.appleCake).setUnlocalizedName("apple_cake").setMaxStackSize(1).setCreativeTab(ChinaCraft.tabPlant);

    //鑰愮伀鐮�
    public static BlockFirebrick blockFirebrick = new BlockFirebrick(); //鑰愮伀鐮栧潡
    public static BlockFirebrickStructure blockFirebrickStructure = new BlockFirebrickStructure(); //鑰愮伀鐮栧潡(澶氭柟鍧楃粨鏋�)
    public static BlockPotteryKiln blockPotteryKiln = new BlockPotteryKiln(); //绐戠倝鏍稿績鏂瑰潡
    public static Item firebrick = new Item().setUnlocalizedName("firebrick").setCreativeTab(ChinaCraft.tabCore); //鑰愮伀鐮�
    public static Item claySandMixture = new Item().setUnlocalizedName("clay_sand_mixture").setCreativeTab(ChinaCraft.tabCore); //绮樺湡娌欏瓙娣峰悎鐗�


    //spiritual_magic_figures鐏电
    public static ItemSpiritualMagicFigures spiritualMagicFigures = new ItemSpiritualMagicFigures(); //鍩烘湰鐏电
    public static ItemSMFFire smfFire = new ItemSMFFire(); //鐏�
    public static ItemSMFPotion smfNightVision = new ItemSMFPotion("spiritual_magic_figures_night_vision", new int[][]{{16, 10000}}); //澶滆
    public static ItemSMFPotion smfPoison = new ItemSMFPotion("spiritual_magic_figures_poison", new int[][]{{19, 450, 4}}); //涓瘨
    public static ItemSMFPotion smfPower = new ItemSMFPotion("spiritual_magic_figures_power", new int[][]{{5, 7000}}); //鍔涢噺
    public static ItemSMFPotion smfProtect = new ItemSMFPotion("spiritual_magic_figures_protect", new int[][]{{12, 3500}, {11, 2500, 3}}); //淇濇姢
    public static ItemSMFPotion smfHeal = new ItemSMFPotion("spiritual_magic_figures_heal", new int[][]{{6, 1}, {10, 500}}); //鐢熷懡鍥炲
    public static ItemSMFSuper smfSuper = new ItemSMFSuper(); //鎹夊绗�

    public static ItemDebug debug = new ItemDebug(); //璋冭瘯鐗╁搧

}
