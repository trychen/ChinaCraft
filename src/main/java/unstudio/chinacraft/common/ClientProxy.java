package unstudio.chinacraft.common;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import scala.collection.generic.BitOperations.Int;
import unstudio.chinacraft.client.model.ModelLanternScaldfish;
import unstudio.chinacraft.client.nei.NEIAPI;
import unstudio.chinacraft.client.render.block.BlockWoodenBucketRenderer;
import unstudio.chinacraft.client.render.item.ItemBuhrimillRenderer;
import unstudio.chinacraft.client.render.item.ItemPotteryTableRenderer;
import unstudio.chinacraft.client.render.item.ItemSericultureFrameRenderer;
import unstudio.chinacraft.client.render.item.ModelBlockItemRenderer;
import unstudio.chinacraft.client.render.tileentity.TileEntityBuhrimillRenderer;
import unstudio.chinacraft.client.render.tileentity.TileEntityModelBlockRenderer;
import unstudio.chinacraft.client.render.tileentity.TileEntityPotteryTableRenderer;
import unstudio.chinacraft.client.render.tileentity.TileEntitySericultureFrameRenderer;
import unstudio.chinacraft.entity.EntityRenderingRegistry;
import unstudio.chinacraft.item.combat.ModelArmorRegister;
import unstudio.chinacraft.tileentity.TileBuhrimill;
import unstudio.chinacraft.tileentity.TileCCLamp;
import unstudio.chinacraft.tileentity.TileModelBlock;
import unstudio.chinacraft.tileentity.TilePotteryTable;
import unstudio.chinacraft.tileentity.TileSericultureFrame;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        //ChinaCraft.bronzeArmorTexture = RenderingRegistry.addNewArmourRendererPrefix("bronze"); // 青铜套装外部材质注册

        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        if (ChinaCraft.NEIIsLoad) {
            new NEIAPI().loadConfig();
        }


        //清理此地材质注册……
//        ChinaCraft.lanternScaldfishOpenable.setBlockTextureName("lantern_scaldfish");
        ChinaCraft.lanternScaldfish.setBlockTextureName("lantern_scaldfish_on");
        ChinaCraft.azalea.setBlockTextureName("chinacraft:azalea");
        ChinaCraft.peony.setBlockTextureName("chinacraft:peony");
        ChinaCraft.chrysanthemum.setBlockTextureName("chinacraft:chrysanthemum");
        ChinaCraft.glutinousRice.setTextureName("chinacraft:glutinous_rice");
        ChinaCraft.jadeWorkingTable.setBlockTextureName("chinacraft:jade_table");

        ChinaCraft.tinPowder.setTextureName("chinacraft:tin_powder");
        ChinaCraft.copperPowder.setTextureName("chinacraft:copper_powder");
        ChinaCraft.copperIngot.setTextureName("chinacraft:copper_ingot");
        ChinaCraft.copperTinMixedPowder.setTextureName("chinacraft:copper_tin_mixed_powder");
        ChinaCraft.bronzeSword.setTextureName("chinacraft:bronze_sword");
        ChinaCraft.bronzeBroadSword.setTextureName("chinacraft:bronze_bigsword");
        ChinaCraft.bronzeBroadSwordGreen.setTextureName("chinacraft:bronze_bigsword_green");
        ChinaCraft.bronzeBroadSwordGreen2.setTextureName("chinacraft:bronze_bigsword_green2");
        ChinaCraft.bronzeBroadSwordPink.setTextureName("chinacraft:bronze_bigsword_pink");
        ChinaCraft.bronzeBroadSwordPurple.setTextureName("chinacraft:bronze_bigsword_purple");
        ChinaCraft.blGiantSword.setTextureName("chinacraft:blgiantsword");
        ChinaCraft.jiuqu_tang.setTextureName("chinacraft:jiuqu_tang");
        ChinaCraft.mace.setTextureName("chinacraft:mace");
        ChinaCraft.tinIngot.setTextureName("chinacraft:tin_ingot");
        ChinaCraft.silverIngot.setTextureName("chinacraft:silver_ingot");
        ChinaCraft.rices.setTextureName("chinacraft:barley_rice");
        ChinaCraft.soy.setTextureName("chinacraft:soy_item");
        ChinaCraft.lcker.setTextureName("chinacraft:lcker");
        ChinaCraft.soyPod.setTextureName("chinacraft:soy_pod");
        ChinaCraft.itemMulberryLeaf.setTextureName("chinacraft:mulberry_leaf");
        ChinaCraft.woodenBucket.setTextureName("chinacraft:woodenbucket");
        ChinaCraft.woodenBucket_Water.setTextureName("chinacraft:woodenbucket_water");
        ChinaCraft.silkwormChrysalis.setTextureName("chinacraft:silkworm_chrysalis");
        ChinaCraft.redPacket.setTextureName("chinacraft:redpacket");
        ChinaCraft.firecracker.setTextureName("chinacraft:firecracker");
        ChinaCraft.bomb.setTextureName("chinacraft:bomb");
        ChinaCraft.blackDogBlood.setTextureName("chinacraft:blackdogblood");
        ChinaCraft.moonCake.setTextureName("chinacraft:mooncake");
        ChinaCraft.xinjiangNutCake.setBlockTextureName("chinacraft:xinjiang_nut_cake");
        ChinaCraft.appleCake.setBlockTextureName("chinacraft:apple_cake");
        ChinaCraft.itemAppleCake.setTextureName("chinacraft:apple_cake");
        // 青铜
        ChinaCraft.bronzePickaxe.setTextureName("chinacraft:bronze_pickaxe");
        ChinaCraft.bronzeAxe.setTextureName("chinacraft:bronze_axe");
        ChinaCraft.bronzeHoe.setTextureName("chinacraft:bronze_hoe");
        ChinaCraft.bronzeShovel.setTextureName("chinacraft:bronze_shovel");

        ChinaCraft.bronzeHelmet.setTextureName("chinacraft:bronze_helmet");
        ChinaCraft.bronzeChestplate.setTextureName("chinacraft:bronze_chestplate");
        ChinaCraft.bronzeLeggings.setTextureName("chinacraft:bronze_leggings");
        ChinaCraft.bronzeBoots.setTextureName("chinacraft:bronze_boots");
        ChinaCraft.hammerStone.setTextureName("chinacraft:hammer_stone");
        ChinaCraft.hammerIron.setTextureName("chinacraft:hammer_iron");
        ChinaCraft.hammerDiamond.setTextureName("chinacraft:hammer_diamond");
        ChinaCraft.hammerBronze.setTextureName("chinacraft:hammer_bronze");

        // 玉
        ChinaCraft.jadeGreenItem.setTextureName("chinacraft:jade_green");
        ChinaCraft.jadeGreen2Item.setTextureName("chinacraft:jade_green2");
        ChinaCraft.jadePinkItem.setTextureName("chinacraft:jade_pink");
        ChinaCraft.jadePurpleItem.setTextureName("chinacraft:jade_purple");
        ChinaCraft.jadeKnife.setTextureName("chinacraft:jade_knife");
        ChinaCraft.artKnife.setTextureName("chinacraft:art_knife");

        // 耐火砖
        ChinaCraft.blockFirebrick.setBlockTextureName("chinacraft:firebrick");
        ChinaCraft.firebrick.setTextureName("chinacraft:firebrick");
        ChinaCraft.blockPotteryKiln.setBlockTextureName("chinacraft:firebrick");
        ChinaCraft.claySandMixture.setTextureName("chinacraft:clay_sand_mixture");

        // 饮品、食物
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

        // 靈符
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

        ModelArmorRegister.init();

        ClientRegistry.bindTileEntitySpecialRenderer(TileBuhrimill.class, new TileEntityBuhrimillRenderer());
        //TODO: create relative json
        //MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ChinaCraft.buhrimill), new ItemBuhrimillRenderer());

        //ClientRegistry.registerTileEntity(TileCCLamp.class, "tileEntityLamp", new TileEntityModelBlockRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCCLamp.class, new TileEntityModelBlockRenderer());
//        MinecraftForgeClient.registerItemRenderer(ChinaCraft.itemLanternScaldfishOpenable,
//                new ModelBlockItemRenderer(new ModelLanternScaldfish(),
//                        new ResourceLocation("chinacraft:textures/models/block/lantern_scaldfish_on.png")));
        //TODO: create relative json
        //MinecraftForgeClient.registerItemRenderer(ChinaCraft.itemLanternScaldfish,
        //        new ModelBlockItemRenderer(new ModelLanternScaldfish(),
        //                new ResourceLocation("chinacraft:textures/models/block/lantern_scaldfish_off.png")));

        ClientRegistry.registerTileEntity(TileModelBlock.class, "tileEntityModelBlock",
                new TileEntityModelBlockRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileModelBlock.class, new TileEntityModelBlockRenderer());

        // MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ChinaCraft.buhrimill),new
        // ItemBuhrimillRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileSericultureFrame.class,
                new TileEntitySericultureFrameRenderer());
        //TODO: create relative json
        //MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ChinaCraft.sericultureFrame), new ItemSericultureFrameRenderer());

        //TODO: create relative json
        //RenderingRegistry.registerBlockHandler(new BlockWoodenBucketRenderer());
        // MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ChinaCraft.blockPotteryBase),
        // new ItemPotteryBlockRenderer(new TilePotteryBlock(), 0.0D, -0.1D,
        // 0.0D));

        ClientRegistry.bindTileEntitySpecialRenderer(TilePotteryTable.class, new TileEntityPotteryTableRenderer());
        //TODO: create relative json
        //MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ChinaCraft.potteryTable), new ItemPotteryTableRenderer());
        //MinecraftForgeClient.registerItemRenderer(ChinaCraft.itemPotteryTable, new ItemPotteryTableRenderer());

        // RenderingRegistry.registerBlockHandler(new BlockLanternRenderer());
        EntityRenderingRegistry.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
    
    private void registerItemBlockRenderer(Block block, String name){
    	registerItemBlockRenderer(block, 0, name);
    }
    private void registerItemBlockRenderer(Block block, int meta, String name){
    	registerItemRenderer(Item.getItemFromBlock(block), meta, name);
    }
    private void registerItemRenderer(Item item, String name){
    	registerItemRenderer(item, 0, name);
    }
    private void registerItemRenderer(Item item, int meta, String name){
    	ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(ChinaCraft.MODID + ":" + name, "inventory"));
    }
}
