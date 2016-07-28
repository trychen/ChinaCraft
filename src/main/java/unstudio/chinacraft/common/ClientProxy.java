package unstudio.chinacraft.common;

import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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
        super.init(event);
        //清理此地材质注册……
//        ChinaCraft.lanternScaldfishOpenable.setBlockTextureName("lantern_scaldfish");
        /*ChinaCraft.lanternScaldfish.setBlockTextureName("lantern_scaldfish_on");
        ChinaCraft.azalea.setBlockTextureName("chinacraft:azalea");
        ChinaCraft.peony.setBlockTextureName("chinacraft:peony");
        ChinaCraft.chrysanthemum.setBlockTextureName("chinacraft:chrysanthemum");
        ChinaCraft.glutinousRice.setTextureName("chinacraft:glutinous_rice");
        ChinaCraft.jadeWorkingTable.setBlockTextureName("chinacraft:jade_table");
        */
        registerItemBlockRenderer(ChinaCraft.silverBlock, "silver_block");
        registerItemBlockRenderer(ChinaCraft.copperBlock, "copper_block");
        registerItemBlockRenderer(ChinaCraft.bronzeBlock, "bronze_block");
        registerItemBlockRenderer(ChinaCraft.copperOre, "copper_ore");
        registerItemBlockRenderer(ChinaCraft.tinOre, "tin_ore");
        registerItemBlockRenderer(ChinaCraft.jadeOre, "jade_ore");
        registerItemBlockRenderer(ChinaCraft.silverOre, "silver_ore");
        registerItemBlockRenderer(ChinaCraft.blockMarble, "marble");
        registerItemBlockRenderer(ChinaCraft.smoothMarble, "smooth_marble");
        registerItemBlockRenderer(ChinaCraft.pillarMarble, "piller_marble");
        registerItemBlockRenderer(ChinaCraft.chiseledMarble, "chiseled_marble");
        registerItemBlockRenderer(ChinaCraft.marbleStair, "marble_stair");
        //registerItemBlockRenderer(ChinaCraft.marbleSlab, "marble_slab");
        //registerItemBlockRenderer(ChinaCraft.marbleDoubleSlab, "marble_double_slab");
        registerItemBlockRenderer(ChinaCraft.woodenWindow1, "wooden_window_1");
        registerItemBlockRenderer(ChinaCraft.woodenWindow2, "wooden_window_2");
        registerItemBlockRenderer(ChinaCraft.woodenWindow3, "wooden_window_3");
        registerItemBlockRenderer(ChinaCraft.woodenWindow4, "wooden_window_4");
        registerItemBlockRenderer(ChinaCraft.woodenWindowdragon, "wooden_window_dragon");
        registerItemBlockRenderer(ChinaCraft.woodenWindowfu, "wooden_window_fu");
        registerItemBlockRenderer(ChinaCraft.bamboo, "bamboo");
        registerItemBlockRenderer(ChinaCraft.blockBambooShoot, "bamboo_shoot");
        registerItemBlockRenderer(ChinaCraft.bambooPlank, "bamboo_plank");
        registerItemBlockRenderer(ChinaCraft.mulberryLog, "mulberry_log");
        registerItemBlockRenderer(ChinaCraft.mulberrySapling, "mulberry_sapling");
        registerItemBlockRenderer(ChinaCraft.mulberryLeaf, "mulberry_leaf");
        registerItemBlockRenderer(ChinaCraft.mulberryWood, "mulberry_wood");
        registerItemBlockRenderer(ChinaCraft.blackbrickBlock, "blackbrick_block");
        registerItemBlockRenderer(ChinaCraft.blackbrickSlab, "blackbrick_slab");
        //registerItemBlockRenderer(ChinaCraft.blackbrickDoubleSlab, "blackbrick_double_slab");
        registerItemBlockRenderer(ChinaCraft.blackbrickStair, "blackbrick_stair");
        registerItemRenderer(ChinaCraft.bronzeIngot, "bronze_ingot");
        registerItemRenderer(ChinaCraft.blackbrick, "blackbrick");
        registerItemRenderer(ChinaCraft.itemBamboo, "bamboo_item");
        registerItemRenderer(ChinaCraft.Salt, "salt_powder");
        registerItemRenderer(ChinaCraft.douJiangBucket, "doujiang_bucket");
        registerItemRenderer(ChinaCraft.silkYarn, "silk_yarn");
        
        registerItemRenderer(ChinaCraft.tinIngot, "tin_ingot");
        registerItemRenderer(ChinaCraft.copperPowder, "copper_powder");
        registerItemRenderer(ChinaCraft.copperIngot, "copper_ingot");
        registerItemRenderer(ChinaCraft.copperTinMixedPowder, "copper_tin_mixed_powder");
        registerItemRenderer(ChinaCraft.bronzeSword, "bronze_sword");
        registerItemRenderer(ChinaCraft.bronzeBroadSword, "bronze_bigsword");
        registerItemRenderer(ChinaCraft.bronzeBroadSwordGreen, "bronze_bigsword_green");
        registerItemRenderer(ChinaCraft.bronzeBroadSwordGreen2, "bronze_bigsword_green2");
        registerItemRenderer(ChinaCraft.bronzeBroadSwordPink, "bronze_bigsword_pink");
        registerItemRenderer(ChinaCraft.bronzeBroadSwordPurple, "bronze_bigsword_purple");
        registerItemRenderer(ChinaCraft.blGiantSword, "blGiantsword");
        registerItemRenderer(ChinaCraft.jiuqu_tang, "jiuqu_tang");
        registerItemRenderer(ChinaCraft.mace, "mace");
        registerItemRenderer(ChinaCraft.silverIngot, "silver_ingot");
        registerItemRenderer(ChinaCraft.rices, "barley_rice");
        registerItemRenderer(ChinaCraft.soy, "soy_item");
        registerItemRenderer(ChinaCraft.lcker, "lcker");
        registerItemRenderer(ChinaCraft.soyPod, "soy_pod");
        registerItemRenderer(ChinaCraft.itemMulberryLeaf, "mulberry_leaf");
        registerItemRenderer(ChinaCraft.woodenBucket, "woodenbucket");
        registerItemRenderer(ChinaCraft.woodenBucket_Water, "woodenbucket_water");
        registerItemRenderer(ChinaCraft.silkwormChrysalis, "silkworm_chrysalis");
        registerItemRenderer(ChinaCraft.redPacket, "redpacket");
        registerItemRenderer(ChinaCraft.firecracker, "firecracker");
        registerItemRenderer(ChinaCraft.bomb, "bomb");
        registerItemRenderer(ChinaCraft.blackDogBlood, "blackdogblood");
        registerItemRenderer(ChinaCraft.moonCake, "mooncake");
        registerItemBlockRenderer(ChinaCraft.xinjiangNutCake, "xinjiang_nut_cake");
        registerItemBlockRenderer(ChinaCraft.appleCake, "block_apple_cake");
        registerItemRenderer(ChinaCraft.itemAppleCake, "apple_cake");
        // 青铜
        registerItemRenderer(ChinaCraft.bronzePickaxe, "bronze_pickaxe");
        registerItemRenderer(ChinaCraft.bronzeAxe, "bronze_axe");
        registerItemRenderer(ChinaCraft.bronzeHoe, "bronze_hoe");
        registerItemRenderer(ChinaCraft.bronzeShovel, "bronze_shovel");

        registerItemRenderer(ChinaCraft.bronzeHelmet, "bronze_helmet");
        registerItemRenderer(ChinaCraft.bronzeChestplate, "bronze_chestplate");
        registerItemRenderer(ChinaCraft.bronzeLeggings, "bronze_leggings");
        registerItemRenderer(ChinaCraft.bronzeBoots, "bronze_boots");
        registerItemRenderer(ChinaCraft.hammerStone, "hammer_stone");
        registerItemRenderer(ChinaCraft.hammerIron, "hammer_iron");
        registerItemRenderer(ChinaCraft.hammerDiamond, "hammer_diamond");
        registerItemRenderer(ChinaCraft.hammerBronze, "hammer_bronze");

        // 玉
        registerItemRenderer(ChinaCraft.jadeGreenItem, "jade_green");
        registerItemRenderer(ChinaCraft.jadeGreen2Item, "jade_green2");
        registerItemRenderer(ChinaCraft.jadePinkItem, "jade_pink");
        registerItemRenderer(ChinaCraft.jadePurpleItem, "jade_purple");
        registerItemRenderer(ChinaCraft.jadeKnife, "jade_knife");
        registerItemRenderer(ChinaCraft.artKnife, "art_knife");

        // 耐火砖
        registerItemBlockRenderer(ChinaCraft.blockFirebrick, "block_firebrick");
        registerItemRenderer(ChinaCraft.firebrick, "firebrick");
        registerItemBlockRenderer(ChinaCraft.blockPotteryKiln, "block_firebrick");
        registerItemRenderer(ChinaCraft.claySandMixture, "clay_sand_mixture");

        // 饮品、食物
        registerItemRenderer(ChinaCraft.cup, "cup");
        registerItemRenderer(ChinaCraft.cup_Clay, "cup_clay");
        registerItemRenderer(ChinaCraft.cupChocolate, "cup_chocolate");
        registerItemRenderer(ChinaCraft.cocoa, "cocoa");
        registerItemRenderer(ChinaCraft.ladyfinger, "ladyfinger");
        registerItemRenderer(ChinaCraft.cupWater, "cup_water");
        registerItemRenderer(ChinaCraft.cupChrysanthemum, "cup_chrysanthemum");
        registerItemRenderer(ChinaCraft.flour, "flour");
        registerItemRenderer(ChinaCraft.riceFlour, "riceflour");
        registerItemRenderer(ChinaCraft.barleyRice, "barley_rice");

        // 靈符
        registerItemRenderer(ChinaCraft.spiritualMagicFigures, "spiritual_magic_figures");
        registerItemRenderer(ChinaCraft.smfFire, "spiritual_magic_figures_fire");
        registerItemRenderer(ChinaCraft.smfNightVision, "spiritual_magic_figures_night_vision");
        registerItemRenderer(ChinaCraft.smfPoison, "spiritual_magic_figures_poison");
        registerItemRenderer(ChinaCraft.smfPower, "spiritual_magic_figures_power");
        registerItemRenderer(ChinaCraft.smfHeal, "spiritual_magic_figures_heal");
        registerItemRenderer(ChinaCraft.smfProtect, "spiritual_magic_figures_protect");
        registerItemRenderer(ChinaCraft.smfSuper, "spiritual_magic_figures_super");

        registerItemRenderer(ChinaCraft.debug, "debug");

        registerItemBlockRenderer(ChinaCraft.blockWoodenBucket, "wooden_bucket_empty");
        registerItemBlockRenderer(ChinaCraft.blockWoodenBucket, 1, "wooden_bucket_full");
        registerItemRenderer(ChinaCraft.tinPowder, "tin_powder");
        
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
        /*MinecraftForgeClient.registerItemRenderer(ChinaCraft.itemLanternScaldfish,
                new ModelBlockItemRenderer(new ModelLanternScaldfish(),
                        new ResourceLocation("chinacraft:textures/models/block/lantern_scaldfish_off.png")));*/

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
        /*RenderingRegistry.registerBlockHandler(new BlockWoodenBucketRenderer());
         MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ChinaCraft.blockPotteryBase),
         new ItemPotteryBlockRenderer(new TilePotteryBlock(), 0.0D, -0.1D,
         0.0D));*/

        ClientRegistry.bindTileEntitySpecialRenderer(TilePotteryTable.class, new TileEntityPotteryTableRenderer());
        //TODO: create relative json
        //MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ChinaCraft.potteryTable), new ItemPotteryTableRenderer());
        //MinecraftForgeClient.registerItemRenderer(ChinaCraft.itemPotteryTable, new ItemPotteryTableRenderer());

        //RenderingRegistry.registerBlockHandler(new BlockLanternRenderer());
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
