package unstudio.chinacraft;

import net.minecraftforge.common.util.EnumHelper;
import unstudio.chinacraft.block.BambooBlock;
import unstudio.chinacraft.block.BambooShoot;
import unstudio.chinacraft.block.BlockBamboo;
import unstudio.chinacraft.block.BlockWoodenBucket;
import unstudio.chinacraft.block.BronzeBlock;
import unstudio.chinacraft.block.Buhrimill;
import unstudio.chinacraft.block.ChiseledMarble;
import unstudio.chinacraft.block.CopperOre;
import unstudio.chinacraft.block.Lantern;
import unstudio.chinacraft.block.Marble;
import unstudio.chinacraft.block.MarbleSlab;
import unstudio.chinacraft.block.MarbleStair;
import unstudio.chinacraft.block.MulberryLeaf;
import unstudio.chinacraft.block.MulberryLog;
import unstudio.chinacraft.block.MulberrySapling;
import unstudio.chinacraft.block.MulberryWood;
import unstudio.chinacraft.block.PillarMarble;
import unstudio.chinacraft.block.RiceGrow;
import unstudio.chinacraft.block.SilverOre;
import unstudio.chinacraft.block.SmoothMarble;
import unstudio.chinacraft.block.SoyGrow;
import unstudio.chinacraft.block.TinOre;
import unstudio.chinacraft.block.WoodenWindow;
import unstudio.chinacraft.combat.*;
import unstudio.chinacraft.item.*;
import unstudio.chinacraft.item.Bamboo;
import unstudio.chinacraft.item.BarleyRice;
import unstudio.chinacraft.item.BronzeIngot;
import unstudio.chinacraft.item.Cocoa;
import unstudio.chinacraft.item.Cup;
import unstudio.chinacraft.item.CupChocolate;
import unstudio.chinacraft.item.CupChrysanthemum;
import unstudio.chinacraft.item.CupWater;
import unstudio.chinacraft.item.Cup_Clay;
import unstudio.chinacraft.item.Flour;
import unstudio.chinacraft.item.ItemBuhrimill;
import unstudio.chinacraft.item.ItemMulberryLeaf;
import unstudio.chinacraft.item.Ladyfinger;
import unstudio.chinacraft.item.Lcker;
import unstudio.chinacraft.item.Rices;
import unstudio.chinacraft.item.SilverIngot;
import unstudio.chinacraft.item.Soy;
import unstudio.chinacraft.item.SoyPod;
import unstudio.chinacraft.item.TinIngot;
import unstudio.chinacraft.item.WoodenBucket;
import unstudio.chinacraft.jade.JadeGreen2Item;
import unstudio.chinacraft.jade.JadeGreenItem;
import unstudio.chinacraft.jade.JadeKnife;
import unstudio.chinacraft.jade.JadeOre;
import unstudio.chinacraft.jade.JadePinkItem;
import unstudio.chinacraft.jade.JadePurpleItem;
import unstudio.chinacraft.jade.JadeWorkingTable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = ChinaCraft.MODID, version = ChinaCraft.VERSION)
public class ChinaCraft {
	    public static final String MODID = "chinacraft";
	    public static final String VERSION = "0.0.1";
	 
	    @SidedProxy(clientSide = "unstudio.chinacraft.ClientProxy",
	            serverSide = "unstudio.chinacraft.CommonProxy")
	    public static CommonProxy proxy;
	 
	    @Instance("chinacraft")
	    public static ChinaCraft instance;
	 
	    @EventHandler
	    public void preInit(FMLPreInitializationEvent event) {
	        proxy.preInit(event);
	    }
	 
	    @EventHandler
	    public void init(FMLInitializationEvent event) {
	        proxy.init(event);
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
		public static Item.ToolMaterial HAMMERSTONE = EnumHelper.addToolMaterial("HAMMERSIONE", 1, 240, 4.0F, 2.0F, 5);
		public static Item.ToolMaterial HAMMERIRON = EnumHelper.addToolMaterial("HAMMERIRON", 2, 475, 6.0F, 3.0F, 14);
		public static Item.ToolMaterial HAMMERDIANMOND = EnumHelper.addToolMaterial("HAMMERDIAMOND", 3, 2096, 8.0F, 4.0F, 10);
		public static Item.ToolMaterial yanlong = EnumHelper.addToolMaterial("yanlong", 3, 2568, 8.0F, 6.0F, 10);
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
	    public static SoyGrow soyGrow = new SoyGrow(); //大豆作物
	    public static BlockBamboo blockBamboo = new BlockBamboo();  //竹子方块
	    public static BambooShoot bambooShoot = new BambooShoot(); //竹笋  
	    public static MulberryLog mulberryLog = new MulberryLog(); //桑树原木
	    public static MulberryLeaf mulberryLeaf = new MulberryLeaf(); //桑树树叶
	    public static MulberrySapling mulberrySapling = new MulberrySapling(); //桑树树苗
	    public static MulberryWood mulberryWood = new MulberryWood(); //桑树木板  
	    public static BambooBlock bambooBlock = new BambooBlock(); //桑树树苗
	    public static JadeWorkingTable jadeWorkingTable = new JadeWorkingTable(); //玉石工作台
	    
	    public static Buhrimill buhrimill = new Buhrimill(); //石磨
	    
	    public static Lantern lantern = new Lantern(); //灯笼  
	    public static BlockWoodenBucket blockWoodenBucket = new BlockWoodenBucket(); //木桶
	    
	    //物品
	    public static BronzeIngot bronzeIngot = new BronzeIngot();  //青铜锭
	    public static TinIngot tinIngot = new TinIngot();  //铜锭
	    public static SilverIngot silverIngot = new SilverIngot();  //银锭
	    public static Rices rices = new Rices(); //米  
	    public static Lcker lcker = new Lcker(); //米穗  
	    public static Soy soy = new Soy(); //米穗  
	    public static Bamboo bamboo = new Bamboo(); //竹子 
	    public static SoyPod soyPod = new SoyPod(); //米穗  
	    public static ItemBuhrimill itemBuhrimill = new ItemBuhrimill(); //石磨  
	    public static ItemMulberryLeaf itemMulberryLeaf = new ItemMulberryLeaf(); //桑叶  
	    public static WoodenBucket woodenBucket = new WoodenBucket(Blocks.air); //木桶  
	    public static WoodenBucket woodenBucket_Water = new WoodenBucket(Blocks.water); //木水桶
	    
	    //防具武器
	    public static BronzeSword bronzeSword = new BronzeSword();  //青铜剑
	    public static BronzeBroadSword bronzeBroadSword = new BronzeBroadSword();  //青铜大刀
	    public static YanLung_Giantknife yanLung_Giantknife = new YanLung_Giantknife();  //青铜大刀
	    
	    //工具
	    public static BronzePickaxe bronzePickaxe = new BronzePickaxe();//青铜稿
	    public static BronzeAxe bronzeAxe =  new BronzeAxe();//青铜斧
	    public static BronzeHoe bronzeHoe =  new BronzeHoe();//青铜锄
	    public static BronzeShovel bronzeShovel =  new BronzeShovel();//青铜铲
	    public static JiuQu_tang jiuqu_tang = new JiuQu_tang();//九曲镋
	    public static JadeKnife jadeKnife = new JadeKnife();//玉石切割刀
		public static HammerStone hammerStone = new HammerStone();//石锤
		public static HammerIron hammerIron = new HammerIron();//铁锤
		public static HammerDiamond hammerDiamond = new HammerDiamond();//铁锤

	    public static int bronzeArmorTexture = RenderingRegistry.addNewArmourRendererPrefix("bronze"); //青铜套装外部材质注册
	    public static BronzeHelmet bronzeHelmet =  new BronzeHelmet();//青铜头盔
	    public static BronzeChestplate bronzeChestplate =  new BronzeChestplate();//青铜胸甲
	    public static BronzeLeggings bronzeLeggings =  new BronzeLeggings();//青铜护腿
	    public static BronzeBoots bronzeBoots =  new BronzeBoots();//青铜靴子
	    
	    //玉石
	    public static JadeGreenItem jadeGreenItem =  new JadeGreenItem();
	    public static JadeGreen2Item jadeGreen2Item =  new JadeGreen2Item();
	    public static JadePinkItem jadePinkItem = new JadePinkItem();
	    public static JadePurpleItem jadePurpleItem =new JadePurpleItem();

	    //Drink、Food
	    public static Cup cup = new Cup();
	    public static Cup_Clay cup_Clay = new Cup_Clay();
	    public static CupChocolate cupChocolate = new CupChocolate();
	    public static Cocoa cocoa =new Cocoa();
	    public static Ladyfinger ladyfinger =new Ladyfinger();
	    public static CupWater cupWater =new CupWater();
	    public static CupChrysanthemum cupChrysanthemum =new CupChrysanthemum();
	    public static Flour flour =new Flour();
	    public static BarleyRice barleyRice =new BarleyRice();

		//spiritual_magic_figures灵符
		public static SpiritualMagicFigures spiritualMagicFigures = new SpiritualMagicFigures();
}
