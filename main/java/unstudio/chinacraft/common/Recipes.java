package unstudio.chinacraft.common;


import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import unstudio.chinacraft.recipes.BuhrimillRecipe;

/**
 * Use for nothing.
 * Created by trychen on 15/12/5.
 */
public class Recipes {
    public static void init(){
        //

        //木桶
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.woodenBucket, 1), new Object[]{"   ", "# #", " # ", '#', Item.getItemFromBlock(Blocks.log)});

        //红包
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.redPacket, 1), new Object[]{Items.paper, new ItemStack(Items.dye, 1, 1)});

        //美术刀
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.artKnife, 1), new Object[]{"   ", " Z ", " # ", '#', Items.stick, 'Z', Items.iron_ingot});

        //蛋糕
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.xinjiangNutCake, 1), new Object[]{"FWF", "HFH", "E#E", 'W', Items.water_bucket.setContainerItem(Items.bucket), 'F', ChinaCraft.flour, 'H', Items.carrot, '#', Item.getItemFromBlock(Blocks.pumpkin), 'E', Items.egg});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.itemAppleCake, 1), new Object[]{"FWF", "FFF", "E#E", 'W', Items.water_bucket.setContainerItem(Items.bucket), 'F', Items.apple, '#', Item.getItemFromBlock(Blocks.pumpkin), 'E', Items.egg});

        //青铜物品
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeHelmet, 1), new Object[]{"###", "# #", "   ", '#', ChinaCraft.bronzeIngot});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeChestplate, 1), new Object[]{"# #", "###", "###", '#', ChinaCraft.bronzeIngot});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeLeggings, 1), new Object[]{"###", "# #", "# #", '#', ChinaCraft.bronzeIngot});
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.bronzeBoots, 1), new Object[]{"   ", "# #", "# #", '#', ChinaCraft.bronzeIngot});

        //烧炉
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.blockFirebrick), new Object[]{"   ", " ##", " ##", '#', ChinaCraft.firebrick});
        GameRegistry.addSmelting(ChinaCraft.claySandMixture, new ItemStack(ChinaCraft.firebrick), 0);
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.claySandMixture, 1), new Object[] {Items.clay_ball,Items.clay_ball, Item.getItemFromBlock(Blocks.sand)});

        //玉石
        GameRegistry.addRecipe(new ItemStack(ChinaCraft.jadeKnife, 1), new Object[]{" X ", "X#X", " # ", '#', Items.stick,'X', Items.iron_ingot });

        //大麦
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.barleyRice), new ItemStack(Items.wheat));

        //BuhrimillRecipe
        BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(ChinaCraft.barleyRice), null, new ItemStack(ChinaCraft.flour), null, 360);
        BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(ChinaCraft.rices), null, new ItemStack(ChinaCraft.riceFlour), null, 360);
        BuhrimillRecipe.registerBuhrimillReciper(new ItemStack(ChinaCraft.bronzeIngot),null, new ItemStack(ChinaCraft.copperTinMixedPowder), null, 720);


        //spiritual_magic_figures
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.spiritualMagicFigures, 3), new Object[]{Items.paper, Items.dye});
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfFire), new Object[]{ChinaCraft.spiritualMagicFigures, Items.magma_cream, Items.redstone, Items.glowstone_dust});
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfNightVision), new Object[]{ChinaCraft.spiritualMagicFigures, Items.golden_carrot, Items.redstone, Items.glowstone_dust});
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfPoison), new Object[]{ChinaCraft.spiritualMagicFigures, Items.spider_eye, Items.redstone, Items.glowstone_dust});
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfPower), new Object[]{ChinaCraft.spiritualMagicFigures, Items.blaze_powder, Items.redstone, Items.glowstone_dust});
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfHeal), new Object[]{ChinaCraft.spiritualMagicFigures, Items.ghast_tear, Items.golden_apple, Items.redstone});
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfProtect), new Object[]{ChinaCraft.spiritualMagicFigures, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot});
        GameRegistry.addShapelessRecipe(new ItemStack(ChinaCraft.smfSuper), new Object[]{ChinaCraft.blackDogBlood, ChinaCraft.spiritualMagicFigures});

    }
}
