package unstudio.chinacraft.common;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import unstudio.chinacraft.client.nei.NEIAPI;
import unstudio.chinacraft.client.render.tileentity.TileEntityBuhrimillRenderer;
import unstudio.chinacraft.client.render.tileentity.TileEntityModelBlockRenderer;
import unstudio.chinacraft.client.render.tileentity.TileEntityPotteryTableRenderer;
import unstudio.chinacraft.client.render.tileentity.TileEntitySericultureFrameRenderer;
import unstudio.chinacraft.entity.EntityRenderingRegistry;
import unstudio.chinacraft.item.combat.ModelArmorRegister;
import unstudio.chinacraft.tileentity.*;
import unstudio.chinacraft.util.annotation.ClientSideRegister;
import unstudio.chinacraft.util.annotation.SpecialItemRenderRegister;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
//        TODO GG
//        ChinaCraft.bronzeArmorTexture = RenderingRegistry.addNewArmourRendererPrefix("bronze"); // 青铜套装外部材质注册

        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        if (ChinaCraft.NEIIsLoad) {
            new NEIAPI().loadConfig();
        }

        //清理此地材质注册……
//        ChinaCraft.lanternScaldfishOpenable.setUnlocalizedName("lantern_scaldfish");
        ChinaCraft.lanternScaldfish.setUnlocalizedName("lantern_scaldfish_on");
        ChinaCraft.azalea.setUnlocalizedName("chinacraft:azalea");
        ChinaCraft.peony.setUnlocalizedName("chinacraft:peony");
        ChinaCraft.chrysanthemum.setUnlocalizedName("chinacraft:chrysanthemum");
        ChinaCraft.glutinousRice.setUnlocalizedName("chinacraft:glutinous_rice");
        ChinaCraft.jadeWorkingTable.setUnlocalizedName("chinacraft:jade_table");

        ChinaCraft.tinPowder.setUnlocalizedName("chinacraft:tin_powder");
        ChinaCraft.copperPowder.setUnlocalizedName("chinacraft:copper_powder");
        ChinaCraft.copperIngot.setUnlocalizedName("chinacraft:copper_ingot");
        ChinaCraft.copperTinMixedPowder.setUnlocalizedName("chinacraft:copper_tin_mixed_powder");
        ChinaCraft.bronzeSword.setUnlocalizedName("chinacraft:bronze_sword");
        ChinaCraft.bronzeBroadSword.setUnlocalizedName("chinacraft:bronze_bigsword");
        ChinaCraft.bronzeBroadSwordGreen.setUnlocalizedName("chinacraft:bronze_bigsword_green");
        ChinaCraft.bronzeBroadSwordGreen2.setUnlocalizedName("chinacraft:bronze_bigsword_green2");
        ChinaCraft.bronzeBroadSwordPink.setUnlocalizedName("chinacraft:bronze_bigsword_pink");
        ChinaCraft.bronzeBroadSwordPurple.setUnlocalizedName("chinacraft:bronze_bigsword_purple");
        ChinaCraft.blGiantSword.setUnlocalizedName("chinacraft:blgiantsword");
        ChinaCraft.jiuqu_tang.setUnlocalizedName("chinacraft:jiuqu_tang");
        ChinaCraft.mace.setUnlocalizedName("chinacraft:mace");
        ChinaCraft.tinIngot.setUnlocalizedName("chinacraft:tin_ingot");
        ChinaCraft.silverIngot.setUnlocalizedName("chinacraft:silver_ingot");
        ChinaCraft.rices.setUnlocalizedName("chinacraft:barley_rice");
        ChinaCraft.soy.setUnlocalizedName("chinacraft:soy_item");
        ChinaCraft.lcker.setUnlocalizedName("chinacraft:lcker");
        ChinaCraft.soyPod.setUnlocalizedName("chinacraft:soy_pod");
        ChinaCraft.itemMulberryLeaf.setUnlocalizedName("chinacraft:mulberry_leaf");
        ChinaCraft.woodenBucket.setUnlocalizedName("chinacraft:woodenbucket");
        ChinaCraft.woodenBucket_Water.setUnlocalizedName("chinacraft:woodenbucket_water");
        ChinaCraft.silkwormChrysalis.setUnlocalizedName("chinacraft:silkworm_chrysalis");
        ChinaCraft.redPacket.setUnlocalizedName("chinacraft:redpacket");
        ChinaCraft.firecracker.setUnlocalizedName("chinacraft:firecracker");
        ChinaCraft.bomb.setUnlocalizedName("chinacraft:bomb");
        ChinaCraft.blackDogBlood.setUnlocalizedName("chinacraft:blackdogblood");
        ChinaCraft.moonCake.setUnlocalizedName("chinacraft:mooncake");
        ChinaCraft.itemXinjiangNutCake.setUnlocalizedName("chinacraft:xinjiang_nut_cake");
        ChinaCraft.xinjiangNutCake.setUnlocalizedName("chinacraft:xinjiang_nut_cake");
        ChinaCraft.appleCake.setUnlocalizedName("chinacraft:apple_cake");
        ChinaCraft.itemAppleCake.setUnlocalizedName("chinacraft:apple_cake");
        ChinaCraft.salt.setUnlocalizedName("chinacraft:salt_powder");
        ChinaCraft.douJiangBucket.setUnlocalizedName("chinacraft:doujiang_bucket");
        // 青铜
        ChinaCraft.bronzePickaxe.setUnlocalizedName("chinacraft:bronze_pickaxe");
        ChinaCraft.bronzeAxe.setUnlocalizedName("chinacraft:bronze_axe");
        ChinaCraft.bronzeHoe.setUnlocalizedName("chinacraft:bronze_hoe");
        ChinaCraft.bronzeShovel.setUnlocalizedName("chinacraft:bronze_shovel");

        ChinaCraft.bronzeHelmet.setUnlocalizedName("chinacraft:bronze_helmet");
        ChinaCraft.bronzeChestplate.setUnlocalizedName("chinacraft:bronze_chestplate");
        ChinaCraft.bronzeLeggings.setUnlocalizedName("chinacraft:bronze_leggings");
        ChinaCraft.bronzeBoots.setUnlocalizedName("chinacraft:bronze_boots");
        ChinaCraft.hammerStone.setUnlocalizedName("chinacraft:hammer_stone");
        ChinaCraft.hammerIron.setUnlocalizedName("chinacraft:hammer_iron");
        ChinaCraft.hammerDiamond.setUnlocalizedName("chinacraft:hammer_diamond");
        ChinaCraft.hammerBronze.setUnlocalizedName("chinacraft:hammer_bronze");

        // 玉
        ChinaCraft.jadeGreenItem.setUnlocalizedName("chinacraft:jade_green");
        ChinaCraft.jadeGreen2Item.setUnlocalizedName("chinacraft:jade_green2");
        ChinaCraft.jadePinkItem.setUnlocalizedName("chinacraft:jade_pink");
        ChinaCraft.jadePurpleItem.setUnlocalizedName("chinacraft:jade_purple");
        ChinaCraft.jadeKnife.setUnlocalizedName("chinacraft:jade_knife");
        ChinaCraft.artKnife.setUnlocalizedName("chinacraft:art_knife");

        // 耐火砖
        ChinaCraft.blockFirebrick.setUnlocalizedName("chinacraft:firebrick");
        ChinaCraft.firebrick.setUnlocalizedName("chinacraft:firebrick");
        ChinaCraft.blockPotteryKiln.setUnlocalizedName("chinacraft:firebrick");
        ChinaCraft.claySandMixture.setUnlocalizedName("chinacraft:clay_sand_mixture");

        // 饮品、食物
        ChinaCraft.cup.setUnlocalizedName("chinacraft:cup");
        ChinaCraft.cup_Clay.setUnlocalizedName("chinacraft:cup_clay");
        ChinaCraft.cupChocolate.setUnlocalizedName("chinacraft:cup_chocolate");
        ChinaCraft.cocoa.setUnlocalizedName("chinacraft:cocoa");
        ChinaCraft.ladyfinger.setUnlocalizedName("chinacraft:ladyfinger");
        ChinaCraft.cupWater.setUnlocalizedName("chinacraft:cup_water");
        ChinaCraft.cupChrysanthemum.setUnlocalizedName("chinacraft:cup_chrysanthemum");
        ChinaCraft.flour.setUnlocalizedName("chinacraft:flour");
        ChinaCraft.riceFlour.setUnlocalizedName("chinacraft:riceflour");
        ChinaCraft.barleyRice.setUnlocalizedName("chinacraft:barley_rice");

        // 靈符
        ChinaCraft.spiritualMagicFigures.setUnlocalizedName("chinacraft:spiritual_magic_figures");
        ChinaCraft.smfFire.setUnlocalizedName("chinacraft:spiritual_magic_figures_fire");
        ChinaCraft.smfNightVision.setUnlocalizedName("chinacraft:spiritual_magic_figures_night_vision");
        ChinaCraft.smfPoison.setUnlocalizedName("chinacraft:spiritual_magic_figures_poison");
        ChinaCraft.smfPower.setUnlocalizedName("chinacraft:spiritual_magic_figures_power");
        ChinaCraft.smfHeal.setUnlocalizedName("chinacraft:spiritual_magic_figures_heal");
        ChinaCraft.smfProtect.setUnlocalizedName("chinacraft:spiritual_magic_figures_protect");
        ChinaCraft.smfSuper.setUnlocalizedName("chinacraft:spiritual_magic_figures_super");

        ChinaCraft.debug.setUnlocalizedName("chinacraft:debug");

        super.init(event);

        ModelArmorRegister.init();

        ClientRegistry.bindTileEntitySpecialRenderer(TileBuhrimill.class, new TileEntityBuhrimillRenderer());
//        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ChinaCraft.buhrimill), new ItemBuhrimillRenderer());

        //ClientRegistry.registerTileEntity(TileCCLamp.class, "tileEntityLamp", new TileEntityModelBlockRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCCLamp.class, new TileEntityModelBlockRenderer());
//        MinecraftForgeClient.registerItemRenderer(ChinaCraft.itemLanternScaldfishOpenable,
//                new ModelBlockItemRenderer(new ModelLanternScaldfish(),
//                        new ResourceLocation("chinacraft:textures/models/block/lantern_scaldfish_on.png")));
//        MinecraftForgeClient.registerItemRenderer(ChinaCraft.itemLanternScaldfish,
//                new ModelBlockItemRenderer(new ModelLanternScaldfish(),
//                        new ResourceLocation("chinacraft:textures/models/block/lantern_scaldfish_off.png")));

        ClientRegistry.registerTileEntity(TileModelBlock.class, "tileEntityModelBlock",
                new TileEntityModelBlockRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileModelBlock.class, new TileEntityModelBlockRenderer());

        // MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ChinaCraft.buhrimill),new
        // ItemBuhrimillRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileSericultureFrame.class,
                new TileEntitySericultureFrameRenderer());
//        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ChinaCraft.sericultureFrame), new ItemSericultureFrameRenderer());

//        RenderingRegistry.registerBlockHandler(new BlockWoodenBucketRenderer());
        // MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ChinaCraft.blockPotteryBase),
        // new ItemPotteryBlockRenderer(new TilePotteryBlock(), 0.0D, -0.1D,
        // 0.0D));

        ClientRegistry.bindTileEntitySpecialRenderer(TilePotteryTable.class, new TileEntityPotteryTableRenderer());
//        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ChinaCraft.potteryTable), new ItemPotteryTableRenderer());
        //MinecraftForgeClient.registerItemRenderer(ChinaCraft.itemPotteryTable, new ItemPotteryTableRenderer());

        // RenderingRegistry.registerBlockHandler(new BlockLanternRenderer());
        EntityRenderingRegistry.init();

        //实现IClient
        ClientSideRegister.registerAll();

        //注册物品渲染器
        SpecialItemRenderRegister.registerAll();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
