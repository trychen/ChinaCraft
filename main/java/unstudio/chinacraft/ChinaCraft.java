package unstudio.chinacraft;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import unstudio.chinacraft.block.*;
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
import unstudio.chinacraft.util.Listener;

@Mod(modid = ChinaCraft.MODID, name=ChinaCraft.NAME, version = ChinaCraft.VERSION)
public class ChinaCraft {
	    public static final String MODID = "chinacraft";
	    public static final String NAME = "ChinaCraft";
	    public static final String VERSION = "0.1.0.155";
	    
	    public static boolean NEIIsLoad = false;
	    
        public static SimpleNetworkWrapper Network;
	 	private Listener listener = new Listener();
	 	
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
	    }
	    
	    @EventHandler
	    public void init(FMLInitializationEvent event) {
	        proxy.init(event);
			FMLCommonHandler.instance().bus().register(listener);
	        MinecraftForge.EVENT_BUS.register(listener);
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
	    
		public static Item.ToolMaterial BRONZE = EnumHelper.addToolMaterial("BRONZE", 2, 230, 6.0F, 2.0F, 1);
		public static Item.ToolMaterial HAMMERSTONE = EnumHelper.addToolMaterial("HAMMERSIONE", 1, 240, 4.0F, 2.0F, 5); //石锤
		public static Item.ToolMaterial HAMMERIRON = EnumHelper.addToolMaterial("HAMMERIRON", 2, 475, 6.0F, 3.0F, 14); //铁锤
		public static Item.ToolMaterial HAMMERDIANMOND = EnumHelper.addToolMaterial("HAMMERDIAMOND", 3, 2096, 8.0F, 4.0F, 10); //钻石锤
		public static Item.ToolMaterial yanlong = EnumHelper.addToolMaterial("yanlong", 3, 2568, 8.0F, 6.0F, 10); //YanLung_Giantknife
		public static Item.ToolMaterial _BRONZE = EnumHelper.addToolMaterial("BRONZEBIG", 2, 230, 6.0F, 2.5F, 1);

	    //方块
	    public static CopperOre copperOre = new CopperOre();  //铜矿
	    public static BronzeBlock bronzeBlock = new BronzeBlock();  //青铜块
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
	    public static RiceGrow riceGrow = new RiceGrow(); //水稻作物
	    public static WoodenWindow woodenWindow1 = new WoodenWindow("chinacraft:wooden_window_1","chinacraft:wooden_window_top"); //木窗框1
	    public static WoodenWindow woodenWindow2 = new WoodenWindow("chinacraft:wooden_window_2","chinacraft:wooden_window_top"); //木窗框2
	    public static WoodenWindow woodenWindow3 = new WoodenWindow("chinacraft:wooden_window_3","chinacraft:wooden_window_top"); //木窗框3
	    public static WoodenWindow woodenWindow4 = new WoodenWindow("chinacraft:wooden_window_4","chinacraft:wooden_window_top"); //木窗框3
	    public static WoodenWindow woodenWindowdragon = new WoodenWindow("chinacraft:wooden_window_dragon","chinacraft:wooden_window_top"); //木窗框Logo
	    public static SoyGrow soyGrow = new SoyGrow(); //大豆作物
	    public static BlockBamboo blockBamboo = new BlockBamboo();  //竹子方块
	    public static BambooShoot bambooShoot = new BambooShoot(); //竹笋  
	    public static MulberryLog mulberryLog = new MulberryLog(); //桑树原木
	    public static MulberryLeaf mulberryLeaf = new MulberryLeaf(); //桑树树叶
	    public static MulberrySapling mulberrySapling = new MulberrySapling(); //桑树树苗
	    public static MulberryWood mulberryWood = new MulberryWood(); //桑树木板  
	    public static Block bambooBlock = new BlockBase(Material.wood).setBlockName("bamboo_block").setCreativeTab(ChinaCraft.tabCore).setStepSound(Block.soundTypeWood); //竹木板
	    public static JadeWorkingTable jadeWorkingTable = new JadeWorkingTable(); //玉石工作台
	    
	    public static PotteryTable potteryTable = new PotteryTable(); //陶瓷工作台
	    public static BlockPotteryBase blockPotteryBase = new BlockPotteryBase(); //陶瓷
	    public static BlockBuckpot blockBuckpot = new BlockBuckpot(); //陶锅
	    
	    public static Buhrimill buhrimill = new Buhrimill(); //石磨
	    
	    public static Lantern lantern = new Lantern(); //灯笼  
	    public static BlockWoodenBucket blockWoodenBucket = new BlockWoodenBucket(); //木桶
	    
	    public static Cooker cooker_off = new Cooker(false); //灶台
	    public static Cooker cooker_on	= new Cooker(true); //灶台
	    
	    public static SericultureFrame sericultureFrame = new SericultureFrame(); //养蚕架
	    
	    //物品
	    public static Item bronzeIngot = new Item().setUnlocalizedName("bronze_ingot").setCreativeTab(ChinaCraft.tabCore);  //青铜锭
	    public static Item tinIngot = new Item().setUnlocalizedName("tin_ingot").setCreativeTab(ChinaCraft.tabCore);  //铜锭
	    public static Item silverIngot = new Item().setUnlocalizedName("silver_ingot").setCreativeTab(ChinaCraft.tabCore); //银锭
	    public static Item copperTinMixedPowder = new Item().setUnlocalizedName("copper_tin_mixed_powder").setCreativeTab(ChinaCraft.tabCore); //铜锡混合矿粉
	    public static CropPlant rices = (CropPlant) new CropPlant(ChinaCraft.riceGrow).setUnlocalizedName("rices"); //米
	    public static Item lcker = new Item().setUnlocalizedName("lcker").setCreativeTab(ChinaCraft.tabPlant); //米穗  
	    public static CropPlant soy = (CropPlant) new 	CropPlant(ChinaCraft.soyGrow).setUnlocalizedName("soy"); //大豆
	    public static Item bamboo = new Item().setCreativeTab(ChinaCraft.tabPlant).setUnlocalizedName("bamboo"); //竹子 
	    public static Item soyPod = new Item().setUnlocalizedName("soy_pod").setCreativeTab(ChinaCraft.tabPlant); //大豆荚
	    public static ItemReed itemBuhrimill = (ItemReed) new ItemReed(ChinaCraft.buhrimill).setUnlocalizedName("buhrimill").setMaxStackSize(1).setCreativeTab(ChinaCraft.tabCore); //石磨  
	    public static Item itemMulberryLeaf = new Item().setUnlocalizedName("mulberry_leaf").setCreativeTab(ChinaCraft.tabPlant); //桑叶
	    public static WoodenBucket woodenBucket = new WoodenBucket(Blocks.air); //木桶  
	    public static WoodenBucket woodenBucket_Water = new WoodenBucket(Blocks.flowing_water); //木水桶
	    public static Silkworm silkworm = new Silkworm(); //蚕
	    public static Item silkwormChrysalis = new Item().setCreativeTab(ChinaCraft.tabPlant).setUnlocalizedName("silkworm_chrysalis"); //蚕茧
	    public static RedPacket redPacket = new RedPacket(); //红包
		public static BlackDogBlood blackDogBlood = new BlackDogBlood();
		public static Item moonCake = new Item().setUnlocalizedName("moon_cake").setCreativeTab(ChinaCraft.tabCore);

	    //防具武器
	    public static BronzeSword bronzeSword = new BronzeSword();  //青铜剑
	    public static BronzeBroadSword bronzeBroadSword = new BronzeBroadSword();  //青铜大刀
	    public static BronzeBroadSword bronzeBroadSwordGreen = new BronzeBroadSword();  //青铜大刀Green
	    public static BronzeBroadSword bronzeBroadSwordGreen2 = new BronzeBroadSword();  //青铜大刀Green2
	    public static BronzeBroadSword bronzeBroadSwordPink = new BronzeBroadSword();  //青铜大刀Pink
	    public static BronzeBroadSword bronzeBroadSwordPurple = new BronzeBroadSword();  //青铜大刀purple
	    public static YanLung_Giantknife yanLung_Giantknife = new YanLung_Giantknife();  //炎龙巨刀
	    
	    //工具
	    public static BronzePickaxe bronzePickaxe = new BronzePickaxe();//青铜稿
	    public static BronzeAxe bronzeAxe =  new BronzeAxe();//青铜斧
	    public static BronzeHoe bronzeHoe =  new BronzeHoe();//青铜锄
	    public static BronzeShovel bronzeShovel =  new BronzeShovel();//青铜铲
	    public static JiuQu_tang jiuqu_tang = new JiuQu_tang();//九曲镋
	    public static JadeKnife jadeKnife = new JadeKnife();//玉石切割刀
	    public static ArtKnife artKnife = new ArtKnife();//美工切割刀
	    
		public static Hammer hammerStone = new Hammer(ChinaCraft.HAMMERSTONE,"stone");//石锤
		public static Hammer hammerIron = new Hammer(ChinaCraft.HAMMERIRON,"iron");//铁锤
		public static Hammer hammerDiamond = new Hammer(ChinaCraft.HAMMERDIANMOND,"diamond");//钻石锤
		public static Hammer hammerBronze = new Hammer(ChinaCraft.HAMMERIRON,"bronze");//钻石锤

		public static int bronzeArmorTexture = -1; //青铜套装外部材质注册
	    public static BronzeHelmet bronzeHelmet;//青铜头盔
	    public static BronzeChestplate bronzeChestplate;//青铜胸甲
	    public static BronzeLeggings bronzeLeggings;//青铜护腿
	    public static BronzeBoots bronzeBoots;//青铜靴子

	    //玉石
	    public static Jade jadeGreenItem =  new Jade("jade_green");
	    public static Jade jadeGreen2Item =  new Jade("jade_green2");
	    public static Jade jadePinkItem = new Jade("jade_pink");
	    public static Jade jadePurpleItem =new Jade("jade_purple");

	    //Drink、Food
	    public static Item cup = new Item().setUnlocalizedName("cup").setCreativeTab(ChinaCraft.tabPlant); //杯
	    public static Item cup_Clay = new Item().setUnlocalizedName("cpu_clay").setCreativeTab(ChinaCraft.tabPlant);
	    public static CupChocolate cupChocolate = new CupChocolate();
	    public static Item cocoa =new Item().setUnlocalizedName("cocoa").setCreativeTab(ChinaCraft.tabPlant);
	    public static ItemFood ladyfinger =(ItemFood) new ItemFood(2, false).setUnlocalizedName("ladyfinger").setCreativeTab(ChinaCraft.tabPlant);
	    public static CupWater cupWater =new CupWater();
	    public static CupChrysanthemum cupChrysanthemum =new CupChrysanthemum();
	    public static Item flour =new Item().setUnlocalizedName("flour").setCreativeTab(ChinaCraft.tabPlant); //面粉
	    public static Item riceFlour = new Item().setUnlocalizedName("rice_flour").setCreativeTab(ChinaCraft.tabPlant); //米粉
	    public static Item barleyRice =new Item().setUnlocalizedName("barley_rice").setCreativeTab(ChinaCraft.tabPlant); //麦仁,大麦米

		//耐火砖
		public static BlockFirebrick blockFirebrick = new BlockFirebrick(); //耐火砖块
		public static BlockFirebrickStructure	blockFirebrickStructure = new BlockFirebrickStructure(); //耐火砖块(多方块结构)
	    public static BlockPotteryKiln blockPotteryKiln = new BlockPotteryKiln(); //窑炉核心方块
		public static Item firebrick = new Item().setUnlocalizedName("firebrick").setCreativeTab(ChinaCraft.tabCore); //耐火砖
		public static Item claySandMixture = new Item().setUnlocalizedName("clay_sand_mixture").setCreativeTab(ChinaCraft.tabCore); //粘土沙子混合物


		//spiritual_magic_figures灵符
		public static SpiritualMagicFigures spiritualMagicFigures = new SpiritualMagicFigures(); //基本灵符
		public static SMFFire smfFire = new SMFFire(); //火
		public static SMFPotion smfNightVision = new SMFPotion("spiritual_magic_figures_night_vision",new int[][]{{16,10000}}); //夜视
		public static SMFPotion smfPoison = new SMFPotion("spiritual_magic_figures_poison",new int[][]{{19,450,4}}); //中毒
		public static SMFPotion smfPower = new SMFPotion("spiritual_magic_figures_power",new int[][]{{5,7000}}); //力量
		public static SMFPotion smfProtect = new SMFPotion("spiritual_magic_figures_protect",new int[][]{{12,3500},{11,2500,3}}); //保护
		public static SMFPotion smfHeal = new SMFPotion("spiritual_magic_figures_heal",new int[][]{{6,1},{10,500}}); //生命回复
		public static SMFSuper smfSuper = new SMFSuper(); //捉妖符

		public static Debug debug = new Debug(); //调试物品
}
