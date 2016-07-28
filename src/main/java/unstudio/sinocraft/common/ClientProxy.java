package unstudio.sinocraft.common;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;

import unstudio.sinocraft.client.model.ModelLanternScaldfish;
import unstudio.sinocraft.client.render.block.BlockWoodenBucketRenderer;
import unstudio.sinocraft.client.render.item.*;
import unstudio.sinocraft.client.render.tileentity.TileEntityBuhrimillRenderer;
import unstudio.sinocraft.client.render.tileentity.TileEntityModelBlockRenderer;
import unstudio.sinocraft.client.render.tileentity.TileEntityPotteryTableRenderer;
import unstudio.sinocraft.client.render.tileentity.TileEntitySericultureFrameRenderer;
import unstudio.sinocraft.client.nei.NEIAPI;
import unstudio.sinocraft.entity.EntityRenderingRegistry;
import unstudio.sinocraft.item.combat.ModelArmorRegister;
import unstudio.sinocraft.tileentity.*;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import unstudio.sinocraft.util.annotation.ClientSideRegister;

public class ClientProxy extends CommonProxy {
    public static HugeItemRenderer hugeItemRenderer;
    public static SpearItemRender spearItemRender;
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        SinoCraft.bronzeArmorTexture = RenderingRegistry.addNewArmourRendererPrefix("bronze"); // 青铜套装外部材质注册

        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        if (SinoCraft.NEIIsLoad) {
            new NEIAPI().loadConfig();
        }

        //清理此地材质注册……
//        SinoCraft.lanternScaldfishOpenable.setBlockTextureName("lantern_scaldfish");
        SinoCraft.lanternScaldfish.setBlockTextureName("lantern_scaldfish_on");
        SinoCraft.azalea.setBlockTextureName("sinocraft:azalea");
        SinoCraft.peony.setBlockTextureName("sinocraft:peony");
        SinoCraft.chrysanthemum.setBlockTextureName("sinocraft:chrysanthemum");
        SinoCraft.glutinousRice.setTextureName("sinocraft:glutinous_rice");
        SinoCraft.jadeWorkingTable.setBlockTextureName("sinocraft:jade_table");

        SinoCraft.tinPowder.setTextureName("sinocraft:tin_powder");
        SinoCraft.copperPowder.setTextureName("sinocraft:copper_powder");
        SinoCraft.copperIngot.setTextureName("sinocraft:copper_ingot");
        SinoCraft.copperTinMixedPowder.setTextureName("sinocraft:copper_tin_mixed_powder");
        SinoCraft.bronzeSword.setTextureName("sinocraft:bronze_sword");
        SinoCraft.bronzeBroadSword.setTextureName("sinocraft:bronze_bigsword");
        SinoCraft.bronzeBroadSwordGreen.setTextureName("sinocraft:bronze_bigsword_green");
        SinoCraft.bronzeBroadSwordGreen2.setTextureName("sinocraft:bronze_bigsword_green2");
        SinoCraft.bronzeBroadSwordPink.setTextureName("sinocraft:bronze_bigsword_pink");
        SinoCraft.bronzeBroadSwordPurple.setTextureName("sinocraft:bronze_bigsword_purple");
        SinoCraft.blGiantSword.setTextureName("sinocraft:blgiantsword");
        SinoCraft.jiuqu_tang.setTextureName("sinocraft:jiuqu_tang");
        SinoCraft.mace.setTextureName("sinocraft:mace");
        SinoCraft.tinIngot.setTextureName("sinocraft:tin_ingot");
        SinoCraft.silverIngot.setTextureName("sinocraft:silver_ingot");
        SinoCraft.rices.setTextureName("sinocraft:barley_rice");
        SinoCraft.soy.setTextureName("sinocraft:soy_item");
        SinoCraft.lcker.setTextureName("sinocraft:lcker");
        SinoCraft.soyPod.setTextureName("sinocraft:soy_pod");
        SinoCraft.itemMulberryLeaf.setTextureName("sinocraft:mulberry_leaf");
        SinoCraft.woodenBucket.setTextureName("sinocraft:woodenbucket");
        SinoCraft.woodenBucket_Water.setTextureName("sinocraft:woodenbucket_water");
        SinoCraft.silkwormChrysalis.setTextureName("sinocraft:silkworm_chrysalis");
        SinoCraft.redPacket.setTextureName("sinocraft:redpacket");
        SinoCraft.firecracker.setTextureName("sinocraft:firecracker");
        SinoCraft.bomb.setTextureName("sinocraft:bomb");
        SinoCraft.blackDogBlood.setTextureName("sinocraft:blackdogblood");
        SinoCraft.moonCake.setTextureName("sinocraft:mooncake");
        SinoCraft.xinjiangNutCake.setBlockTextureName("sinocraft:xinjiang_nut_cake");
        SinoCraft.appleCake.setBlockTextureName("sinocraft:apple_cake");
        SinoCraft.itemAppleCake.setTextureName("sinocraft:apple_cake");
        // 青铜
        SinoCraft.bronzePickaxe.setTextureName("sinocraft:bronze_pickaxe");
        SinoCraft.bronzeAxe.setTextureName("sinocraft:bronze_axe");
        SinoCraft.bronzeHoe.setTextureName("sinocraft:bronze_hoe");
        SinoCraft.bronzeShovel.setTextureName("sinocraft:bronze_shovel");

        SinoCraft.bronzeHelmet.setTextureName("sinocraft:bronze_helmet");
        SinoCraft.bronzeChestplate.setTextureName("sinocraft:bronze_chestplate");
        SinoCraft.bronzeLeggings.setTextureName("sinocraft:bronze_leggings");
        SinoCraft.bronzeBoots.setTextureName("sinocraft:bronze_boots");
        SinoCraft.hammerStone.setTextureName("sinocraft:hammer_stone");
        SinoCraft.hammerIron.setTextureName("sinocraft:hammer_iron");
        SinoCraft.hammerDiamond.setTextureName("sinocraft:hammer_diamond");
        SinoCraft.hammerBronze.setTextureName("sinocraft:hammer_bronze");

        // 玉
        SinoCraft.jadeGreenItem.setTextureName("sinocraft:jade_green");
        SinoCraft.jadeGreen2Item.setTextureName("sinocraft:jade_green2");
        SinoCraft.jadePinkItem.setTextureName("sinocraft:jade_pink");
        SinoCraft.jadePurpleItem.setTextureName("sinocraft:jade_purple");
        SinoCraft.jadeKnife.setTextureName("sinocraft:jade_knife");
        SinoCraft.artKnife.setTextureName("sinocraft:art_knife");

        // 耐火砖
        SinoCraft.blockFirebrick.setBlockTextureName("sinocraft:firebrick");
        SinoCraft.firebrick.setTextureName("sinocraft:firebrick");
        SinoCraft.blockPotteryKiln.setBlockTextureName("sinocraft:firebrick");
        SinoCraft.claySandMixture.setTextureName("sinocraft:clay_sand_mixture");

        // 饮品、食物
        SinoCraft.cup.setTextureName("sinocraft:cup");
        SinoCraft.cup_Clay.setTextureName("sinocraft:cup_clay");
        SinoCraft.cupChocolate.setTextureName("sinocraft:cup_chocolate");
        SinoCraft.cocoa.setTextureName("sinocraft:cocoa");
        SinoCraft.ladyfinger.setTextureName("sinocraft:ladyfinger");
        SinoCraft.cupWater.setTextureName("sinocraft:cup_water");
        SinoCraft.cupChrysanthemum.setTextureName("sinocraft:cup_chrysanthemum");
        SinoCraft.flour.setTextureName("sinocraft:flour");
        SinoCraft.riceFlour.setTextureName("sinocraft:riceflour");
        SinoCraft.barleyRice.setTextureName("sinocraft:barley_rice");

        // 靈符
        SinoCraft.spiritualMagicFigures.setTextureName("sinocraft:spiritual_magic_figures");
        SinoCraft.smfFire.setTextureName("sinocraft:spiritual_magic_figures_fire");
        SinoCraft.smfNightVision.setTextureName("sinocraft:spiritual_magic_figures_night_vision");
        SinoCraft.smfPoison.setTextureName("sinocraft:spiritual_magic_figures_poison");
        SinoCraft.smfPower.setTextureName("sinocraft:spiritual_magic_figures_power");
        SinoCraft.smfHeal.setTextureName("sinocraft:spiritual_magic_figures_heal");
        SinoCraft.smfProtect.setTextureName("sinocraft:spiritual_magic_figures_protect");
        SinoCraft.smfSuper.setTextureName("sinocraft:spiritual_magic_figures_super");

        SinoCraft.debug.setTextureName("sinocraft:debug");

        super.init(event);

        ModelArmorRegister.init();

        ClientRegistry.bindTileEntitySpecialRenderer(TileBuhrimill.class, new TileEntityBuhrimillRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(SinoCraft.buhrimill), new ItemBuhrimillRenderer());

        //ClientRegistry.registerTileEntity(TileCCLamp.class, "tileEntityLamp", new TileEntityModelBlockRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCCLamp.class, new TileEntityModelBlockRenderer());
//        MinecraftForgeClient.registerItemRenderer(SinoCraft.itemLanternScaldfishOpenable,
//                new ModelBlockItemRenderer(new ModelLanternScaldfish(),
//                        new ResourceLocation("sinocraft:textures/models/block/lantern_scaldfish_on.png")));
        MinecraftForgeClient.registerItemRenderer(SinoCraft.itemLanternScaldfish,
                new ModelBlockItemRenderer(new ModelLanternScaldfish(),
                        new ResourceLocation("sinocraft:textures/models/block/lantern_scaldfish_off.png")));

        ClientRegistry.registerTileEntity(TileModelBlock.class, "tileEntityModelBlock",
                new TileEntityModelBlockRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileModelBlock.class, new TileEntityModelBlockRenderer());

        // MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(SinoCraft.buhrimill),new
        // ItemBuhrimillRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileSericultureFrame.class,
                new TileEntitySericultureFrameRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(SinoCraft.sericultureFrame), new ItemSericultureFrameRenderer());

        RenderingRegistry.registerBlockHandler(new BlockWoodenBucketRenderer());
        // MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(SinoCraft.blockPotteryBase),
        // new ItemPotteryBlockRenderer(new TilePotteryBlock(), 0.0D, -0.1D,
        // 0.0D));

        ClientRegistry.bindTileEntitySpecialRenderer(TilePotteryTable.class, new TileEntityPotteryTableRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(SinoCraft.potteryTable), new ItemPotteryTableRenderer());
        //MinecraftForgeClient.registerItemRenderer(SinoCraft.itemPotteryTable, new ItemPotteryTableRenderer());

        // RenderingRegistry.registerBlockHandler(new BlockLanternRenderer());
        EntityRenderingRegistry.init();

        //初始化渲染器
        hugeItemRenderer = new HugeItemRenderer();
        spearItemRender = new SpearItemRender();

        //实现IClient
        ClientSideRegister.registerAll();

        //使用渲染器
        MinecraftForgeClient.registerItemRenderer(SinoCraft.mace, hugeItemRenderer);
        MinecraftForgeClient.registerItemRenderer(SinoCraft.jiuqu_tang, spearItemRender);

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
