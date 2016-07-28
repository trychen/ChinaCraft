package unstudio.sinocraft.common;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import unstudio.sinocraft.recipes.CCShapedIgnoreDamageRecipes;
import unstudio.sinocraft.recipes.BuhrimillRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Use for nothing. Created by trychen on 15/12/5.
 */
public class Recipes {

    /**
     * 注册时实现了该接口的recipes()方法都将执行。注意: 需要手动书写方法(因为接口方法不能定义为static)
     */
    public interface RecipeAble{
        /**
        public static void recipes();
         */
    }

    /**
     * 普通的注册合成
     */
    public static void init() {

        //盐
//        GameRegistry.addSmelting(Items.water_bucket, new ItemStack(SinoCraft.saltBucket), 0.8f);
//        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.Salt,3), SinoCraft.saltBucket);

        
        // 投掷炸弹
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.firecracker, 2), new ItemStack(Items.dye, 1, 1),
                Items.gunpowder, Items.paper);
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.bomb), Items.gunpowder, Items.iron_ingot);
        // 夜行衣
        GameRegistry.addRecipe(new ItemStack(SinoCraft.nightClothes[0]),
                new Object[] { "AAA", "ABA", "   ", 'A', Items.leather, 'B', Items.dye });
        GameRegistry.addRecipe(new ItemStack(SinoCraft.nightClothes[1]),
                new Object[] { "ABA", "AAA", "AAA", 'A', Items.leather, 'B', Items.dye });
        GameRegistry.addRecipe(new ItemStack(SinoCraft.nightClothes[2]),
                new Object[] { "AAA", "ABA", "A A", 'A', Items.leather, 'B', Items.dye });
        GameRegistry.addRecipe(new ItemStack(SinoCraft.nightClothes[3]),
                new Object[] { "   ", "ABA", "A A", 'A', Items.leather, 'B', Items.dye });

        // Copper
        GameRegistry.addSmelting(SinoCraft.copperOre, new ItemStack(SinoCraft.copperIngot, 1), 1.2f);
        GameRegistry.addRecipe(new ItemStack(SinoCraft.copperBlock,1),
                new Object[] { "###", "###", "###", '#', SinoCraft.copperIngot });
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.copperIngot,9), SinoCraft.copperBlock);

        //银类
        GameRegistry.addSmelting(SinoCraft.silverOre, new ItemStack(SinoCraft.silverIngot,1), 1.0f);
        GameRegistry.addRecipe(new ItemStack(SinoCraft.silverBlock,1),
                new Object[] { "###", "###", "###", '#', SinoCraft.silverIngot});
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.silverIngot,9), SinoCraft.silverBlock);

        // 大理石
        GameRegistry.addRecipe(new ItemStack(SinoCraft.chiseledMarble, 4),
                new Object[] { "## ", "## ", "   ", '#', SinoCraft.pillarMarble });
        GameRegistry.addRecipe(new ItemStack(SinoCraft.smoothMarble, 4),
                new Object[] { "## ", "## ", "   ", '#', SinoCraft.chiseledMarble });
        GameRegistry.addRecipe(new ItemStack(SinoCraft.pillarMarble, 4),
                new Object[] { "## ", "## ", "   ", '#', SinoCraft.smoothMarble });
        GameRegistry.addRecipe(new ItemStack(SinoCraft.smoothMarble, 4),
                new Object[] { "## ", "## ", "   ", '#', SinoCraft.blockMarble});
        GameRegistry.addRecipe(new ItemStack(SinoCraft.marbleSlab, 6),
                new Object[] { "   ", "   ", "###", '#', SinoCraft.blockMarble});
        GameRegistry.addRecipe(new ItemStack(SinoCraft.marbleStair, 4),
                new Object[] { "#  ", "## ", "###", '#', SinoCraft.blockMarble});
        GameRegistry.addRecipe(new ItemStack(SinoCraft.marbleStair, 4),
                new Object[] { "  #", " ##", "###", '#', SinoCraft.blockMarble});

        //青砖
        for(int i=1;i<=8;i++) {
            Object object[] = new Object[i+1];
            object[0]=Items.water_bucket;
            for(int j=1;j<object.length;j++)object[j]=Items.brick;
            GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.blackbrick, i),object);
        }
        GameRegistry.addRecipe(new ItemStack(SinoCraft.blackbrickBlock, 1),
                new Object[] { "## ", "## ", "   ", '#', SinoCraft.blackbrick});
        GameRegistry.addRecipe(new ItemStack(SinoCraft.blackbrickSlab, 6),
                new Object[] { "   ", "   ", "###", '#', SinoCraft.blackbrick});
        GameRegistry.addRecipe(new ItemStack(SinoCraft.blackbrickStair, 4),
                new Object[] { "#  ", "## ", "###", '#', SinoCraft.blackbrick});
        GameRegistry.addRecipe(new ItemStack(SinoCraft.blackbrickStair, 4),
                new Object[] { "  #", " ##", "###", '#', SinoCraft.blackbrick});

        // 木窗格
        GameRegistry.addRecipe(CCShapedIgnoreDamageRecipes.createRecipe(new ItemStack(SinoCraft.woodenWindowdragon, 1),
                new Object[] { "HHH", "B#A", "HHH", 'H', Item.getItemFromBlock(Blocks.log), '#', SinoCraft.artKnife,
                        'A', SinoCraft.woodenWindow4, 'B', SinoCraft.woodenWindow2 }));
        GameRegistry.addRecipe(CCShapedIgnoreDamageRecipes.createRecipe(new ItemStack(SinoCraft.woodenWindow4, 4),
                new Object[] { " # ", "#A#", " # ", 'A', SinoCraft.artKnife, '#', Items.stick }));
        GameRegistry.addRecipe(CCShapedIgnoreDamageRecipes.createRecipe(new ItemStack(SinoCraft.woodenWindow3, 4), new Object[] { "B#B", "#A#", "B#B", 'A',
                SinoCraft.artKnife, '#', Items.stick, 'B', Item.getItemFromBlock(Blocks.log) }));
        GameRegistry.addRecipe(CCShapedIgnoreDamageRecipes.createRecipe(new ItemStack(SinoCraft.woodenWindow2, 4),
                new Object[] { "###", "#A#", "###", 'A', SinoCraft.artKnife, '#', Items.stick }));
        GameRegistry.addRecipe(CCShapedIgnoreDamageRecipes.createRecipe(new ItemStack(SinoCraft.woodenWindow1, 4), new Object[] { " A ", "A#A", " A ", 'A',
                Item.getItemFromBlock(Blocks.planks), '#', SinoCraft.artKnife }));

        // 竹子
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.bambooPlank), SinoCraft.itemBamboo, SinoCraft.itemBamboo,
                SinoCraft.itemBamboo, SinoCraft.itemBamboo);

        // mulberry
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.mulberryWood, 4),
                Item.getItemFromBlock(SinoCraft.mulberryLog));

        // 玉石
        GameRegistry.addRecipe(new ItemStack(SinoCraft.jadeWorkingTable), new Object[] { "###", "#X#", "###", '#',
                Item.getItemFromBlock(Blocks.stone), 'X', Item.getItemFromBlock(Blocks.crafting_table) });

        // 养蚕
        GameRegistry.addRecipe(new ItemStack(SinoCraft.sericultureFrame, 1),
                new Object[] { "@#@", "@#@", "@#@", '#', Item.getItemFromBlock(Blocks.wooden_slab), '@', Items.stick });
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.silkYarn, 4), SinoCraft.silkwormChrysalis);
        GameRegistry.addRecipe(new ItemStack(SinoCraft.itemSilk, 1, 15),
                new Object[] { "@@ ", "@@ ", "   ", '@', SinoCraft.silkYarn });

        // CookingBench
        GameRegistry.addRecipe(new ItemStack(SinoCraft.cooking_bench_off, 1),
                new Object[] { "###", "# #", "# #", '#', Item.getItemFromBlock(Blocks.cobblestone) });

        // 混合粉末
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.copperTinMixedPowder, 4), SinoCraft.copperPowder,
                SinoCraft.copperPowder, SinoCraft.copperPowder, SinoCraft.tinPowder);
        GameRegistry.addSmelting(SinoCraft.copperTinMixedPowder, new ItemStack(SinoCraft.bronzeIngot), 0.8f);


        // 石磨
        GameRegistry.addRecipe(new ItemStack(SinoCraft.buhrimill),
                new Object[] { " #S", "XIX", "XXX", 'S', Items.stick, '#', Item.getItemFromBlock(Blocks.stone), 'I',
                        Items.iron_ingot, 'X', Item.getItemFromBlock(Blocks.cobblestone) });
                        // GameRegistry.addRecipe(new
                        // ItemStack(SinoCraft.buhrimill), new Object[]{" #S",
                        // "XIX", "XXX", 'S', Items.stick, '#',
                        // Item.getItemFromBlock(Blocks.stone), 'I',
                        // SinoCraft.bronzeIngot, 'X',
                        // Item.getItemFromBlock(Blocks.cobblestone)});
                        // GameRegistry.addRecipe(new
                        // ItemStack(SinoCraft.itemBuhrimill, 1), new
                        // Object[]{" #X", "&#&", "&&&", '#',
                        // Item.getItemFromBlock(Blocks.cobblestone), 'X',
                        // Items.stick, '&',
                        // Item.getItemFromBlock(Blocks.stone)});

        // 九曲镋刀
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SinoCraft.jiuqu_tang, 1), new Object[] { "#T#", "###",
                " X ", '#', SinoCraft.bronzeIngot, 'X', Items.stick, 'T', SinoCraft.tinIngot }));

        // 青铜
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.bronzeBlock, 1), SinoCraft.bronzeIngot,
                SinoCraft.bronzeIngot, SinoCraft.bronzeIngot, SinoCraft.bronzeIngot, SinoCraft.bronzeIngot,
                SinoCraft.bronzeIngot, SinoCraft.bronzeIngot, SinoCraft.bronzeIngot, SinoCraft.bronzeIngot);
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.bronzeIngot, 9),
                SinoCraft.bronzeBlock);
        GameRegistry.addRecipe(new ItemStack(SinoCraft.bronzeSword, 1),
                new Object[] { " # ", " # ", " X ", '#', SinoCraft.bronzeIngot, 'X', Items.stick });
        GameRegistry.addRecipe(new ItemStack(SinoCraft.mace, 1), new Object[] { " #O", " X#", "X  ", '#', Items.coal,
                'X', Items.stick, 'O', Item.getItemFromBlock(Blocks.obsidian) });
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SinoCraft.bronzeBroadSword, 1),
                new Object[] { " ##", " X#", "X  ", '#', SinoCraft.bronzeIngot, 'X', Items.stick }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SinoCraft.bronzeBroadSword, 1),
                new Object[] { " ##", " X#", "X  ", '#', SinoCraft.bronzeIngot, 'X', Items.stick }));
        GameRegistry.addRecipe(new ItemStack(SinoCraft.blGiantSword, 1),
                new Object[] { "#B#", "EDE", "CAC", '#', Item.getItemFromBlock(Blocks.obsidian), 'A', Items.stick, 'B',
                        Items.lava_bucket.setContainerItem(Items.bucket), 'C', Items.iron_ingot, 'D',
                        Item.getItemFromBlock(Blocks.soul_sand), 'E', SinoCraft.bronzeBlock });
        GameRegistry.addRecipe(new ItemStack(SinoCraft.bronzePickaxe, 1),
                new Object[] { "###", " X ", " X ", '#', SinoCraft.bronzeIngot, 'X', Items.stick });
        GameRegistry.addRecipe(new ItemStack(SinoCraft.bronzeAxe, 1),
                new Object[] { "## ", "#X ", " X ", '#', SinoCraft.bronzeIngot, 'X', Items.stick });
        GameRegistry.addRecipe(new ItemStack(SinoCraft.bronzeAxe, 1),
                new Object[] { " ##", " X#", " X ", '#', SinoCraft.bronzeIngot, 'X', Items.stick });
        GameRegistry.addRecipe(new ItemStack(SinoCraft.bronzeHoe, 1),
                new Object[] { "## ", " X ", " X ", '#', SinoCraft.bronzeIngot, 'X', Items.stick });
        GameRegistry.addRecipe(new ItemStack(SinoCraft.bronzeHoe, 1),
                new Object[] { " ##", " X ", " X ", '#', SinoCraft.bronzeIngot, 'X', Items.stick });
        GameRegistry.addRecipe(new ItemStack(SinoCraft.bronzeShovel, 1),
                new Object[] { " # ", " X ", " X ", '#', SinoCraft.bronzeIngot, 'X', Items.stick });

        // 锤
        GameRegistry.addRecipe(new ItemStack(SinoCraft.hammerDiamond, 1),
                new Object[] { "###", "#X#", " X ", '#', Items.diamond, 'X', Items.stick });
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SinoCraft.hammerBronze, 1),
                new Object[] { "###", "#X#", " X ", '#', SinoCraft.bronzeIngot, 'X', Items.stick }));
        GameRegistry.addRecipe(new ItemStack(SinoCraft.hammerIron, 1),
                new Object[] { "###", "#X#", " X ", '#', Items.iron_ingot, 'X', Items.stick });
        GameRegistry.addRecipe(new ItemStack(SinoCraft.hammerStone, 1),
                new Object[] { "###", "#X#", " X ", '#', Item.getItemFromBlock(Blocks.cobblestone), 'X', Items.stick });

        // 冕冠
        GameRegistry
                .addRecipe(new ShapedOreRecipe(new ItemStack(SinoCraft.chinaCrown, 1),
                        new Object[] { "TET", "#X#", "S#S", '#', Item.getItemFromBlock(Blocks.log), 'X',
                                Items.nether_star.setContainerItem(Items.nether_star),
                                'T', SinoCraft.tinIngot, 'E', Item.getItemFromBlock(Blocks.dragon_egg)
                                        .setContainerItem(Item.getItemFromBlock(Blocks.dragon_egg)),
                                'S', Items.stick }));

        // TinOre
        GameRegistry.addSmelting(SinoCraft.tinOre, new ItemStack(SinoCraft.tinIngot), 0.8f);

        // 水稻
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.rices, 2), new Object[] { SinoCraft.lcker });

        // 大豆荚
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.soy, 2), new Object[] { SinoCraft.soyPod });

        // 竹子
        GameRegistry.addSmelting(SinoCraft.itemBamboo, new ItemStack(SinoCraft.mulberrySapling), 1.2f);

        // 木桶
        GameRegistry.addRecipe(new ItemStack(SinoCraft.woodenBucket, 1),
                new Object[] { "   ", "# #", " # ", '#', Item.getItemFromBlock(Blocks.log) });

        // 红包
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.redPacket, 1),
                new Object[] { Items.paper, new ItemStack(Items.dye, 1, 1) });

        // 美术刀
        GameRegistry.addRecipe(new ItemStack(SinoCraft.artKnife, 1),
                new Object[] { " # ", "#Z#", '#', Items.stick, 'Z', Items.iron_ingot });

        // 蛋糕
        GameRegistry.addRecipe(new ItemStack(SinoCraft.xinjiangNutCake, 1),
                new Object[] { "FWF", "HFH", "E#E", 'W', Items.milk_bucket, 'F',
                        SinoCraft.flour, 'H', Items.carrot, '#', Item.getItemFromBlock(Blocks.pumpkin), 'E',
                        Items.egg });
        GameRegistry.addRecipe(new ItemStack(SinoCraft.itemAppleCake, 1),
                new Object[] { "FWF", "FFF", "E#E", 'W', Items.milk_bucket, 'F',
                        Items.apple, '#', Item.getItemFromBlock(Blocks.pumpkin), 'E', Items.egg });

        // 青铜物品
        GameRegistry.addRecipe(new ItemStack(SinoCraft.bronzeHelmet, 1),
                new Object[] { "###", "# #", "   ", '#', SinoCraft.bronzeIngot });
        GameRegistry.addRecipe(new ItemStack(SinoCraft.bronzeChestplate, 1),
                new Object[] { "# #", "###", "###", '#', SinoCraft.bronzeIngot });
        GameRegistry.addRecipe(new ItemStack(SinoCraft.bronzeLeggings, 1),
                new Object[] { "###", "# #", "# #", '#', SinoCraft.bronzeIngot });
        GameRegistry.addRecipe(new ItemStack(SinoCraft.bronzeBoots, 1),
                new Object[] { "   ", "# #", "# #", '#', SinoCraft.bronzeIngot });

        // 烧炉
        GameRegistry.addRecipe(new ItemStack(SinoCraft.blockFirebrick),
                new Object[] { "   ", " ##", " ##", '#', SinoCraft.firebrick });
        GameRegistry.addSmelting(SinoCraft.claySandMixture, new ItemStack(SinoCraft.firebrick), 0);
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.claySandMixture, 1),
                new Object[] { Items.clay_ball, Items.clay_ball, Item.getItemFromBlock(Blocks.sand) });

        // 玉石
        GameRegistry.addRecipe(new ItemStack(SinoCraft.jadeKnife, 1),
                new Object[] { " X ", "X#X", '#', Items.stick, 'X', Items.iron_ingot });

        // 大麦
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.barleyRice), new ItemStack(Items.wheat));

        // BuhrimillRecipe
        BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(SinoCraft.barleyRice), null,
                new ItemStack(SinoCraft.flour), null, 360);
        BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(SinoCraft.rices), null,
                new ItemStack(SinoCraft.riceFlour), null, 360);
        //BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(SinoCraft.bronzeIngot), null,new ItemStack(SinoCraft.copperTinMixedPowder), null, 720);
        BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(SinoCraft.copperIngot), null,
        		new ItemStack(SinoCraft.copperPowder), null, 720);
        BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(SinoCraft.copperOre), null,
                new ItemStack(SinoCraft.copperPowder), null, 1080);
        BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(SinoCraft.tinOre), null,
        		new ItemStack(SinoCraft.tinPowder), null, 720);
        BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(SinoCraft.tinIngot), null,
                new ItemStack(SinoCraft.tinPowder), null, 720);
        BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(SinoCraft.soy),new ItemStack(Items.water_bucket),
                new ItemStack(SinoCraft.douJiangBucket), null, 720);


        // spiritual_magic_figures
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.spiritualMagicFigures, 3),
                new Object[] { Items.paper, Items.dye });
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.smfFire), new Object[] {
                SinoCraft.spiritualMagicFigures, Items.magma_cream, Items.redstone, Items.glowstone_dust });
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.smfNightVision), new Object[] {
                SinoCraft.spiritualMagicFigures, Items.golden_carrot, Items.redstone, Items.glowstone_dust });
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.smfPoison), new Object[] {
                SinoCraft.spiritualMagicFigures, Items.spider_eye, Items.redstone, Items.glowstone_dust });
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.smfPower), new Object[] {
                SinoCraft.spiritualMagicFigures, Items.blaze_powder, Items.redstone, Items.glowstone_dust });
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.smfHeal), new Object[] {
                SinoCraft.spiritualMagicFigures, Items.ghast_tear, Items.golden_apple, Items.redstone });
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.smfProtect), new Object[] {
                SinoCraft.spiritualMagicFigures, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot });
        GameRegistry.addShapelessRecipe(new ItemStack(SinoCraft.smfSuper),
                new Object[] { SinoCraft.blackDogBlood, SinoCraft.spiritualMagicFigures });

    }
}
