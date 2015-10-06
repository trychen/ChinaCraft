package unstudio.chinacraft;

import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import unstudio.chinacraft.entity.EntityBlackDogMob;
import unstudio.chinacraft.entity.EntityChinaZombie;
import unstudio.chinacraft.entity.EntityChinaZombieMob;
import unstudio.chinacraft.entity.model.ModelChinaZombie;
import unstudio.chinacraft.renderer.ItemPotteryBlockRenderer;
import unstudio.chinacraft.renderer.TileEntityBuhrimillRenderer;
import unstudio.chinacraft.renderer.BlockWoodenBucketRenderer;
import unstudio.chinacraft.renderer.TileEntityPotteryBlockRenderer;
import unstudio.chinacraft.tileentity.TileBuhrimill;
import unstudio.chinacraft.tileentity.TilePotteryBase;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy { 
    public void preInit(FMLPreInitializationEvent event) {
		ChinaCraft.bronzeArmorTexture = RenderingRegistry.addNewArmourRendererPrefix("bronze"); //青铜套装外部材质注册
    	super.preInit(event);
    }
 
    public void init(FMLInitializationEvent event) {
		ClientRegistry.bindTileEntitySpecialRenderer(TileBuhrimill.class, new TileEntityBuhrimillRenderer());
		RenderingRegistry.registerBlockHandler(new BlockWoodenBucketRenderer());
//		ClientRegistry.bindTileEntitySpecialRenderer(TilePotteryBlock.class, new TileEntityPotteryBlockRenderer());
//		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ChinaCraft.blockPotteryBase), new ItemPotteryBlockRenderer(new TilePotteryBlock(), 0.0D, -0.1D, 0.0D));

		RenderingRegistry.registerEntityRenderingHandler(EntityChinaZombieMob.class, new RenderLiving(new ModelChinaZombie(), 0) {
			protected ResourceLocation getEntityTexture(Entity par1Entity) {
				return new ResourceLocation("chinacraft", "textures/entity/chinazombie/chinazombie.png");
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityBlackDogMob.class, new RenderLiving(new ModelWolf(), 0) {
			@Override
			protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
				return new ResourceLocation("chinacraft", "textures/entity/blackwolf/blackwolf.png");
			}
		});
		ChinaCraft.copperOre.setBlockTextureName("chinacraft:copper_ore");
    	ChinaCraft.bronzeBlock.setBlockTextureName("chinacraft:bronze_block");
    	ChinaCraft.tinOre.setBlockTextureName("chinacraft:tin_ore");
    	ChinaCraft.jadeOre.setBlockTextureName("chinacraft:jade_ore");
    	ChinaCraft.marble.setBlockTextureName("chinacraft:marble");
    	ChinaCraft.smoothMarble.setBlockTextureName("chinacraft:smooth_marble");
    	ChinaCraft.chiseledMarble.setBlockTextureName("chinacraft:chiseled_marble");
    	ChinaCraft.marbleSlab.setBlockTextureName("chinacraft:smooth_marble");
    	ChinaCraft.marbleDoubleSlab.setBlockTextureName("chinacraft:smooth_marble");
    	ChinaCraft.silverOre.setBlockTextureName("chinacraft:silver_ore");
    	ChinaCraft.blockBamboo.setBlockTextureName("chinacraft:bamboo");
    	ChinaCraft.mulberrySapling.setBlockTextureName("chinacraft:mulberry_sapling");
    	ChinaCraft.mulberryWood.setBlockTextureName("chinacraft:mulberry_wood");
    	ChinaCraft.bambooBlock.setBlockTextureName("chinacraft:bamboo_block");
    	ChinaCraft.jadeWorkingTable.setBlockTextureName("chinacraft:jade_table");
    	ChinaCraft.bambooShoot.setBlockTextureName("chinacraft:bamboo_shoot");
    	
    	ChinaCraft.bronzeIngot.setTextureName("chinacraft:bronze_ingot");
    	ChinaCraft.copperTinMixedPowder.setTextureName("chinacraft:copper_tin_mixed_powder");
    	ChinaCraft.bronzeSword.setTextureName("chinacraft:bronze_sword");
    	ChinaCraft.bronzeBroadSword.setTextureName("chinacraft:bronze_bigsword");
    	ChinaCraft.yanLung_Giantknife.setTextureName("chinacraft:yanlung_giantknife");
    	ChinaCraft.jiuqu_tang.setTextureName("chinacraft:jiuqu_tang");
    	ChinaCraft.tinIngot.setTextureName("chinacraft:tin_ingot");
    	ChinaCraft.silverIngot.setTextureName("chinacraft:silver_ingot");
    	ChinaCraft.rices.setTextureName("chinacraft:barley_rice");
    	ChinaCraft.soy.setTextureName("chinacraft:soy_item");
    	ChinaCraft.lcker.setTextureName("chinacraft:lcker");
    	ChinaCraft.soyPod.setTextureName("chinacraft:soy_pod");
    	ChinaCraft.itemBuhrimill.setTextureName("chinacraft:item_buhrimill");
    	ChinaCraft.bamboo.setTextureName("chinacraft:bamboo");
    	ChinaCraft.itemMulberryLeaf.setTextureName("chinacraft:mulberry_leaf");
    	ChinaCraft.woodenBucket.setTextureName("chinacraft:woodenbucket");
    	ChinaCraft.woodenBucket_Water.setTextureName("chinacraft:woodenbucket_water");
    	ChinaCraft.silkwormChrysalis.setTextureName("chinacraft:silkworm_chrysalis");
    	ChinaCraft.redPacket.setTextureName("chinacraft:redpacket");
		ChinaCraft.blackDogBlood.setTextureName("chinacraft:blackdogblood");
		ChinaCraft.moonCake.setTextureName("chinacraft:mooncake");

		//青铜
    	ChinaCraft.bronzePickaxe.setTextureName("chinacraft:bronze_pickaxe");
    	ChinaCraft.bronzeAxe.setTextureName("chinacraft:bronze_axe");
    	ChinaCraft.bronzeHoe.setTextureName("chinacraft:bronze_hoe");
    	ChinaCraft.bronzeShovel.setTextureName("chinacraft:bronze_shovel");
    	ChinaCraft.jadeKnife.setTextureName("chinacraft:jade_knife");
    	
    	ChinaCraft.bronzeHelmet.setTextureName("chinacraft:bronze_helmet");
    	ChinaCraft.bronzeChestplate.setTextureName("chinacraft:bronze_chestplate");
    	ChinaCraft.bronzeLeggings.setTextureName("chinacraft:bronze_leggings");
    	ChinaCraft.bronzeBoots.setTextureName("chinacraft:bronze_boots");
    	ChinaCraft.hammerStone.setTextureName("chinacraft:hammer_stone");
    	ChinaCraft.hammerIron.setTextureName("chinacraft:hammer_iron");
    	ChinaCraft.hammerDiamond.setTextureName("chinacraft:hammer_diamond");
    	ChinaCraft.hammerBronze.setTextureName("chinacraft:hammer_bronze");

    	//Jade
    	ChinaCraft.jadeGreenItem.setTextureName("chinacraft:jade_green");
    	ChinaCraft.jadeGreen2Item.setTextureName("chinacraft:jade_green2");
    	ChinaCraft.jadePinkItem.setTextureName("chinacraft:jade_pink");
    	ChinaCraft.jadePurpleItem.setTextureName("chinacraft:jade_purple");

		//耐火砖
		ChinaCraft.blockFirebrick.setBlockTextureName("chinacraft:firebrick");
		ChinaCraft.firebrick.setTextureName("chinacraft:firebrick");
		ChinaCraft.blockPotteryKiln.setBlockTextureName("chinacraft:firebrick");
		ChinaCraft.claySandMixture.setTextureName("chinacraft:clay_sand_mixture");

		//Drink、Food
		ChinaCraft.cup.setTextureName("chinacraft:cup");
    	ChinaCraft.cup_Clay.setTextureName("chinacraft:cup_clay");
    	ChinaCraft.cupChocolate.setTextureName("chinacraft:cup_chocolate");
    	ChinaCraft.cocoa.setTextureName("chinacraft:cocoa");
    	ChinaCraft.ladyfinger.setTextureName("chinacraft:ladyfinger");
    	ChinaCraft.cupWater.setTextureName("chinacraft:cup_water");
    	ChinaCraft.cupChrysanthemum.setTextureName("chinacraft:cup_chrysanthemum");
    	ChinaCraft.flour.setTextureName("chinacraft:flour");
    	ChinaCraft.riceFlour.setTextureName("chinacraft:riceflour");
    	ChinaCraft.barleyRice.setTextureName("chinacraft:barley_rice");

		//spiritual_magic_figures
		ChinaCraft.spiritualMagicFigures.setTextureName("chinacraft:spiritual_magic_figures");
		ChinaCraft.smfFire.setTextureName("chinacraft:spiritual_magic_figures_fire");
		ChinaCraft.smfNightVision.setTextureName("chinacraft:spiritual_magic_figures_night_vision");
		ChinaCraft.smfPoison.setTextureName("chinacraft:spiritual_magic_figures_poison");
		ChinaCraft.smfPower.setTextureName("chinacraft:spiritual_magic_figures_power");
		ChinaCraft.smfHeal.setTextureName("chinacraft:spiritual_magic_figures_heal");
		ChinaCraft.smfProtect.setTextureName("chinacraft:spiritual_magic_figures_protect");
		ChinaCraft.smfSuper.setTextureName("chinacraft:spiritual_magic_figures_super");

		ChinaCraft.debug.setTextureName("chinacraft:debug");
		super.init(event);
    }
 
    public void postInit(FMLPostInitializationEvent event) {
    	super.postInit(event);
    }
}
