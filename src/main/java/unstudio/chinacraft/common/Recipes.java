package unstudio.chinacraft.common;

import net.minecraft.block.BlockColored;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import net.minecraftforge.oredict.ShapelessOreRecipe;
import unstudio.chinacraft.recipes.CCShapedIgnoreDamageRecipe;
import unstudio.chinacraft.recipes.JadeBenchRecipes;
import unstudio.chinacraft.recipes.BuhrimillRecipe;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Use for nothing. Created by trychen on 15/12/5.
 */
public class Recipes {

    /**
     * 注册时实现了该接口的recipes()方法都将执行。注意: 需要手动书写方法(因为接口方法不能定义为static)
     */
    public interface RecipeAble {
        /**
         public static void recipes();
         */
    }

    /**
     * 普通的注册合成
     */
    public static void init() {

        //盐
//        GameRegistry.addSmelting(Items.water_bucket, new ItemStack(ChinaCraft.saltBucket), 0.8f);
//        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.salt,3), ChinaCraft.saltBucket);


        // 投掷炸弹
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.firecracker, 2), new ItemStack(Items.dye, 1, 1),
                Items.gunpowder, Items.paper);
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ChinaCraft.bomb), Items.gunpowder, Items.iron_ingot));
        // 夜行衣
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.nightClothes[0]),
                "AAA",
                "A A",
                "   ",
                'A', new ItemStack(ChinaCraft.itemSilk, 1, 15));
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.nightClothes[1]),
                "A A",
                "AAA",
                "AAA",
                'A', new ItemStack(ChinaCraft.itemSilk, 1, 15));
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.nightClothes[2]),
                "AAA",
                "A A",
                "A A",
                'A', new ItemStack(ChinaCraft.itemSilk, 1, 15));
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.nightClothes[3]),
                "   ",
                "A A",
                "A A",
                'A', new ItemStack(ChinaCraft.itemSilk, 1, 15));

        // 袈裟
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.cassock),
                "Y Y",
                "YGR",
                "YRR",
                'Y', new ItemStack(ChinaCraft.itemSilk, 1, 4),
                'G', Items.golden_apple,
                'R', new ItemStack(ChinaCraft.itemSilk, 1, 14));

        // Copper
        GameRegistry.addSmelting(ChinaCraft.copperOre, new ItemStack(ChinaCraft.copperIngot, 1), 1.2f);
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.copperBlock, 1),
                new Object[]{"###", "###", "###", '#', ChinaCraft.copperIngot});
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.copperIngot, 9), ChinaCraft.copperBlock);

        //银类
        GameRegistry.addSmelting(ChinaCraft.silverOre, new ItemStack(ChinaCraft.silverIngot, 1), 1.0f);
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.silverBlock, 1),
                new Object[]{"###", "###", "###", '#', ChinaCraft.silverIngot});
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.silverIngot, 9), ChinaCraft.silverBlock);

        // 大理石
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.chiseledMarble, 4),
                new Object[]{"## ", "## ", "   ", '#', ChinaCraft.pillarMarble});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.smoothMarble, 4),
                new Object[]{"## ", "## ", "   ", '#', ChinaCraft.chiseledMarble});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.pillarMarble, 4),
                new Object[]{"## ", "## ", "   ", '#', ChinaCraft.smoothMarble});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.smoothMarble, 4),
                new Object[]{"## ", "## ", "   ", '#', ChinaCraft.blockMarble});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.marbleSlab, 6),
                new Object[]{"   ", "   ", "###", '#', ChinaCraft.blockMarble});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.marbleStair, 4),
                new Object[]{"#  ", "## ", "###", '#', ChinaCraft.blockMarble});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.marbleStair, 4),
                new Object[]{"  #", " ##", "###", '#', ChinaCraft.blockMarble});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.marbleWall),
                "###",
                "###",
                '#', ChinaCraft.smoothMarble
        );

        //青砖
        for (int i = 1; i <= 8; i++) {
            Object object[] = new Object[i + 1];
            object[0] = Items.water_bucket;
            for (int j = 1; j < object.length; j++) object[j] = Items.brick;
            GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.blackbrick, i), object);
        }
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.blackbrickBlock, 1),
                new Object[]{"## ", "## ", "   ", '#', ChinaCraft.blackbrick});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.blackbrickSlab, 6),
                new Object[]{"   ", "   ", "###", '#', ChinaCraft.blackbrick});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.blackbrickStair, 4),
                new Object[]{"#  ", "## ", "###", '#', ChinaCraft.blackbrick});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.blackbrickStair, 4),
                new Object[]{"  #", " ##", "###", '#', ChinaCraft.blackbrick});
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.blackbrickMossyBlock), ChinaCraft.blackbrickBlock, Item.getItemFromBlock(Blocks.mossy_cobblestone));
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.blackbrickBlackerBlock), ChinaCraft.blackbrickBlock, Items.clay_ball);
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.blackbrickWall),
                "###",
                "###",
                '#', ChinaCraft.blackbrickBlock
        );

        // 木窗格
        GameRegistry.addRecipe(CCShapedIgnoreDamageRecipe.createRecipe(new ItemStack(ChinaCraft.woodenWindowdragon, 1),
                new Object[]{"HHH", "B#A", "HHH", 'H', Item.getItemFromBlock(Blocks.log), '#', ChinaCraft.artKnife,
                        'A', ChinaCraft.woodenWindow4, 'B', ChinaCraft.woodenWindow2}));
        GameRegistry.addRecipe(CCShapedIgnoreDamageRecipe.createRecipe(new ItemStack(ChinaCraft.woodenWindowfu, 1),
                new Object[]{"HHH", "B#B", "HHH", 'H', Item.getItemFromBlock(Blocks.log), '#', ChinaCraft.artKnife, 'B', ChinaCraft.woodenWindow2}));
        GameRegistry.addRecipe(CCShapedIgnoreDamageRecipe.createRecipe(new ItemStack(ChinaCraft.woodenWindow4, 4),
                new Object[]{" # ", "#A#", " # ", 'A', ChinaCraft.artKnife, '#', Items.stick}));
        GameRegistry.addRecipe(CCShapedIgnoreDamageRecipe.createRecipe(new ItemStack(ChinaCraft.woodenWindow3, 4), new Object[]{"B#B", "#A#", "B#B", 'A',
                ChinaCraft.artKnife, '#', Items.stick, 'B', Item.getItemFromBlock(Blocks.log)}));
        GameRegistry.addRecipe(CCShapedIgnoreDamageRecipe.createRecipe(new ItemStack(ChinaCraft.woodenWindow2, 4),
                new Object[]{"###", "#A#", "###", 'A', ChinaCraft.artKnife, '#', Items.stick}));
        GameRegistry.addRecipe(CCShapedIgnoreDamageRecipe.createRecipe(new ItemStack(ChinaCraft.woodenWindow1, 4), new Object[]{" A ", "A#A", " A ", 'A',
                Item.getItemFromBlock(Blocks.planks), '#', ChinaCraft.artKnife}));

        GameRegistry.addRecipe(CCShapedIgnoreDamageRecipe.createRecipe(new ItemStack(ChinaCraft.peeledBambooWindow1, 1),
                "HHH",
                " # ",
                "HHH",
                'H', ChinaCraft.peeledBambooPlank,
                '#', ChinaCraft.artKnife));
        GameRegistry.addRecipe(CCShapedIgnoreDamageRecipe.createRecipe(new ItemStack(ChinaCraft.peeledBambooWindow2, 1),
                "HHH",
                "H#H",
                "HHH",
                'H', ChinaCraft.peeledBambooPlank,
                '#', ChinaCraft.artKnife));
        GameRegistry.addRecipe(CCShapedIgnoreDamageRecipe.createRecipe(new ItemStack(ChinaCraft.peeledBambooWindow3, 1),
                " H ",
                "H#H",
                " H ",
                'H', ChinaCraft.peeledBambooPlank,
                '#', ChinaCraft.artKnife));

        GameRegistry.addRecipe(new ItemStack(ChinaCraft.lanternScaldfish, 1, 2), "WYW", "WTW", "WYW", 'W', new ItemStack(Blocks.wool, 1, 14), 'Y', new ItemStack(Blocks.wool, 1, 4), 'T', Blocks.torch);
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.itemDing), " J ", "MOM", "MCM", 'C', Blocks.coal_block, 'J', ChinaCraft.jadeKnife, 'O', Blocks.obsidian, 'M', Blocks.mossy_cobblestone);

        // 竹子
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.bambooPlank), ChinaCraft.itemBamboo, ChinaCraft.itemBamboo, ChinaCraft.itemBamboo, ChinaCraft.itemBamboo);
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.bambooSlab, 6), "   ", "   ", "###", '#', ChinaCraft.bambooPlank);
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.bambooStair, 4),
                new Object[]{"#  ", "## ", "###", '#', ChinaCraft.bambooPlank});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.bambooStair, 4),
                new Object[]{"  #", " ##", "###", '#', ChinaCraft.bambooPlank});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.bambooFence, 2),
                new Object[]{"###", "###", "   ", '#', ChinaCraft.itemBamboo});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.bambooFenceGate, 1),
                new Object[]{"#$#", "#$#", "   ", '#', ChinaCraft.itemBamboo, '$', ChinaCraft.bambooPlank});
        GameRegistry.addRecipe(CCShapedIgnoreDamageRecipe.createRecipe(new ItemStack(ChinaCraft.peeledBambooPlank, 8),
                "###",
                "#K#",
                "###",
                '#', ChinaCraft.bambooPlank,
                'K', ChinaCraft.artKnife));

        // mulberry
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.mulberryWood, 4),
                Item.getItemFromBlock(ChinaCraft.mulberryLog));

        // 玉石
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.jadeWorkingTable), new Object[]{"###", "#X#", "###", '#',
                Item.getItemFromBlock(Blocks.stone), 'X', Item.getItemFromBlock(Blocks.crafting_table)});

        // 养蚕
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.sericultureFrame, 1),
                new Object[]{"@#@", "@#@", "@#@", '#', Item.getItemFromBlock(Blocks.wooden_slab), '@', Items.stick});
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.silkYarn, 4), ChinaCraft.silkwormChrysalis);
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.itemSilk, 1, 0),
                new Object[]{"@@ ", "@@ ", "   ", '@', ChinaCraft.silkYarn});
        for (int i = 0; i < 16; ++i)
            GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.itemSilk, 1, BlockColored.func_150031_c(i)), new Object[]{new ItemStack(Items.dye, 1, i), new ItemStack(ChinaCraft.itemSilk, 1, 0)});

        // CookingBench
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.cooking_bench_off, 1),
                new Object[]{"###", "# #", "# #", '#', Item.getItemFromBlock(Blocks.cobblestone)});

        // 混合粉末
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ChinaCraft.copperTinMixedPowder, 4), ChinaCraft.copperPowder,
                ChinaCraft.copperPowder, ChinaCraft.copperPowder, ChinaCraft.tinPowder));
        GameRegistry.addSmelting(ChinaCraft.copperTinMixedPowder, new ItemStack(ChinaCraft.bronzeIngot), 0.8f);


        // 石磨
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ChinaCraft.buhrimill),
                new Object[]{" #S", "XIX", "XXX", 'S', Items.stick, '#', Item.getItemFromBlock(Blocks.stone), 'I',
                        Items.iron_ingot, 'X', Item.getItemFromBlock(Blocks.cobblestone)}));
        // GameRegistry.addRecipe(new
        // ItemStack(ChinaCraft.buhrimill), new Object[]{" #S",
        // "XIX", "XXX", 'S', Items.stick, '#',
        // Item.getItemFromBlock(Blocks.stone), 'I',
        // ChinaCraft.bronzeIngot, 'X',
        // Item.getItemFromBlock(Blocks.cobblestone)});
        // GameRegistry.addRecipe(new
        // ItemStack(ChinaCraft.itemBuhrimill, 1), new
        // Object[]{" #X", "&#&", "&&&", '#',
        // Item.getItemFromBlock(Blocks.cobblestone), 'X',
        // Items.stick, '&',
        // Item.getItemFromBlock(Blocks.stone)});

        // 九曲镋刀
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ChinaCraft.jiuquTang, 1), new Object[]{"#T#", "###",
                " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick, 'T', ChinaCraft.tinIngot}));

        // 青铜
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.bronzeBlock, 1), ChinaCraft.bronzeIngot,
                ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot,
                ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot, ChinaCraft.bronzeIngot);
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.bronzeIngot, 9),
                ChinaCraft.bronzeBlock);
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeSword, 1),
                new Object[]{" # ", " # ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.mace, 1),
                " DO",
                "XBD",
                "XX ",
                'D', Items.diamond,
                'X', Items.stick,
                'O', Item.getItemFromBlock(Blocks.obsidian),
                'B', ChinaCraft.bronzeIngot);
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ChinaCraft.bronzeBroadSword, 1),
                new Object[]{" ##", " X#", "X  ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ChinaCraft.bronzeBroadSword, 1),
                new Object[]{" ##", " X#", "X  ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ChinaCraft.blGiantSword, 1),
                new Object[]{"#B#", "EDE", "CAC", '#', Item.getItemFromBlock(Blocks.obsidian), 'A', Items.stick, 'B',
                        Items.lava_bucket.setContainerItem(Items.bucket), 'C', Items.iron_ingot, 'D',
                        Item.getItemFromBlock(Blocks.soul_sand), 'E', ChinaCraft.bronzeBlock}));
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzePickaxe, 1),
                new Object[]{"###", " X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeAxe, 1),
                new Object[]{"## ", "#X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeAxe, 1),
                new Object[]{" ##", " X#", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeHoe, 1),
                new Object[]{"## ", " X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeHoe, 1),
                new Object[]{" ##", " X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeShovel, 1),
                new Object[]{" # ", " X ", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick});

        // 锤
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ChinaCraft.hammerDiamond, 1),
                new Object[]{"###", "#X#", " X ", '#', Items.diamond, 'X', Items.stick}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ChinaCraft.hammerBronze, 1),
                new Object[]{"###", "#X#", " X ", '#', ChinaCraft.bronzeIngot, 'X', Items.stick}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ChinaCraft.hammerIron, 1),
                new Object[]{"###", "#X#", " X ", '#', Items.iron_ingot, 'X', Items.stick}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ChinaCraft.hammerStone, 1),
                new Object[]{"###", "#X#", " X ", '#', Item.getItemFromBlock(Blocks.cobblestone), 'X', Items.stick}));

        // 冕冠
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ChinaCraft.chinaCrown, 1),
                "TET",
                "#X#",
                "S#S",
                '#', Items.gold_ingot,
                'X', Items.nether_star.setContainerItem(Items.nether_star),
                'T', new ItemStack(ChinaCraft.itemSilk, 1, 12),
                'E', Item.getItemFromBlock(Blocks.dragon_egg).setContainerItem(Item.getItemFromBlock(Blocks.dragon_egg)),
                'S', new ItemStack(ChinaCraft.itemSilk, 1, 14)));

        // TinOre
        GameRegistry.addSmelting(ChinaCraft.tinOre, new ItemStack(ChinaCraft.tinIngot), 0.8f);

        // 水稻
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.rices, 2), new Object[]{ChinaCraft.lcker});

        // 大豆荚
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.soy, 2), new Object[]{ChinaCraft.soyPod});

        // 木桶
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.woodenBucket, 1),
                new Object[]{"   ", "# #", " # ", '#', Item.getItemFromBlock(Blocks.log)});

        // 红包
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.redPacket, 1),
                new Object[]{Items.paper, new ItemStack(Items.dye, 1, 1)});

        // 美术刀
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.artKnife, 1),
                new Object[]{" # ", "#Z#", '#', Items.stick, 'Z', Items.iron_ingot});

        // 蛋糕
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.xinjiangNutCake, 1),
                new Object[]{"FWF", "HFH", "E#E", 'W', Items.milk_bucket, 'F',
                        ChinaCraft.flour, 'H', Items.carrot, '#', Item.getItemFromBlock(Blocks.pumpkin), 'E',
                        Items.egg});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.itemAppleCake, 1),
                new Object[]{"FWF", "FFF", "E#E", 'W', Items.milk_bucket, 'F',
                        Items.apple, '#', Item.getItemFromBlock(Blocks.pumpkin), 'E', Items.egg});

        // 青铜物品
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeHelmet, 1),
                new Object[]{"###", "# #", "   ", '#', ChinaCraft.bronzeIngot});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeChestplate, 1),
                new Object[]{"# #", "###", "###", '#', ChinaCraft.bronzeIngot});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeLeggings, 1),
                new Object[]{"###", "# #", "# #", '#', ChinaCraft.bronzeIngot});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeBoots, 1),
                new Object[]{"   ", "# #", "# #", '#', ChinaCraft.bronzeIngot});

        // 烧炉
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.blockFirebrick),
                new Object[]{"   ", " ##", " ##", '#', ChinaCraft.firebrick});
        GameRegistry.addSmelting(ChinaCraft.claySandMixture, new ItemStack(ChinaCraft.firebrick), 0);
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.claySandMixture, 1),
                new Object[]{Items.clay_ball, Items.clay_ball, Item.getItemFromBlock(Blocks.sand)});

        // 玉石
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ChinaCraft.jadeKnife, 1),
                new Object[]{" X ", "X#X", '#', Items.stick, 'X', Items.iron_ingot}));

        //飞刀
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ChinaCraft.flyingCutter, 2),
                new Object[]{"  I", "S  ", "F  ", 'S', Items.stick, 'I', Items.iron_ingot, 'F', Items.feather}));

        //蝉杖
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ChinaCraft.buddhistCane, 1), "IAI", "BSB", " S ", 'I', Items.gold_ingot, 'B', ChinaCraft.tinIngot, 'A', Items.ghast_tear, 'S', Items.stick));

        // 大麦
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.barleyRice), new ItemStack(Items.wheat));

        // BuhrimillRecipe
        BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(ChinaCraft.barleyRice), null,
                new ItemStack(ChinaCraft.flour), null, 360);
        BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(ChinaCraft.rices), null,
                new ItemStack(ChinaCraft.riceFlour), null, 360);
        //BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(ChinaCraft.bronzeIngot), null,new ItemStack(ChinaCraft.copperTinMixedPowder), null, 720);
        BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(ChinaCraft.copperIngot), null,
                new ItemStack(ChinaCraft.copperPowder), null, 720);
        BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(ChinaCraft.copperOre), null,
                new ItemStack(ChinaCraft.copperPowder), null, 1080);
        BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(ChinaCraft.tinOre), null,
                new ItemStack(ChinaCraft.tinPowder), null, 720);
        BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(ChinaCraft.tinIngot), null,
                new ItemStack(ChinaCraft.tinPowder), null, 720);
        BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(ChinaCraft.soy), new ItemStack(Items.water_bucket),
                new ItemStack(ChinaCraft.douJiangBucket), null, 720);

        // JadeBenchRecipes
        JadeBenchRecipes.registerOreRecipe(new ItemStack(ChinaCraft.jadeGreenItem), 25);
        JadeBenchRecipes.registerOreRecipe(new ItemStack(ChinaCraft.jadeGreen2Item), 25);
        JadeBenchRecipes.registerOreRecipe(new ItemStack(ChinaCraft.jadePinkItem), 25);
        JadeBenchRecipes.registerOreRecipe(new ItemStack(ChinaCraft.jadePurpleItem), 25);
        List<ItemStack> listBronzeBoradSword = new ArrayList<ItemStack>();
        ItemStack bronzeBroadSword = new ItemStack(ChinaCraft.bronzeBroadSword);
        ItemStack bronzeBroadSwordGreen = new ItemStack(ChinaCraft.bronzeBroadSwordGreen);
        ItemStack bronzeBroadSwordGreen2 = new ItemStack(ChinaCraft.bronzeBroadSwordGreen2);
        ItemStack bronzeBroadSwordPink = new ItemStack(ChinaCraft.bronzeBroadSwordPink);
        ItemStack bronzeBroadSwordPurple = new ItemStack(ChinaCraft.bronzeBroadSwordPurple);
        listBronzeBoradSword.add(bronzeBroadSword);
        listBronzeBoradSword.add(bronzeBroadSwordGreen);
        listBronzeBoradSword.add(bronzeBroadSwordGreen2);
        listBronzeBoradSword.add(bronzeBroadSwordPink);
        listBronzeBoradSword.add(bronzeBroadSwordPurple);
        for (ItemStack sword : listBronzeBoradSword) {
            JadeBenchRecipes.registerModifyRecipe(sword, new ItemStack(ChinaCraft.jadeGreenItem), bronzeBroadSwordGreen, new ItemStack(ChinaCraft.blGiantSword), 0.05F);
            JadeBenchRecipes.registerModifyRecipe(sword, new ItemStack(ChinaCraft.jadeGreen2Item), bronzeBroadSwordGreen2, new ItemStack(ChinaCraft.blGiantSword), 0.05F);
            JadeBenchRecipes.registerModifyRecipe(sword, new ItemStack(ChinaCraft.jadePinkItem), bronzeBroadSwordPink, new ItemStack(ChinaCraft.blGiantSword), 0.05F);
            JadeBenchRecipes.registerModifyRecipe(sword, new ItemStack(ChinaCraft.jadePurpleItem), bronzeBroadSwordPurple, new ItemStack(ChinaCraft.blGiantSword), 0.05F);
        }

        // spiritual_magic_figures
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.spiritualMagicFigures, 3),
                new Object[]{Items.paper, Items.dye});
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfFire), new Object[]{
                ChinaCraft.spiritualMagicFigures, Items.magma_cream, Items.redstone, Items.glowstone_dust});
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfNightVision), new Object[]{
                ChinaCraft.spiritualMagicFigures, Items.golden_carrot, Items.redstone, Items.glowstone_dust});
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfPoison), new Object[]{
                ChinaCraft.spiritualMagicFigures, Items.spider_eye, Items.redstone, Items.glowstone_dust});
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfPower), new Object[]{
                ChinaCraft.spiritualMagicFigures, Items.blaze_powder, Items.redstone, Items.glowstone_dust});
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfHeal), new Object[]{
                ChinaCraft.spiritualMagicFigures, Items.ghast_tear, Items.golden_apple, Items.redstone});
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfProtect), new Object[]{
                ChinaCraft.spiritualMagicFigures, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot});
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfSuper),
                new Object[]{ChinaCraft.blackDogBlood, ChinaCraft.spiritualMagicFigures});
    }
}
