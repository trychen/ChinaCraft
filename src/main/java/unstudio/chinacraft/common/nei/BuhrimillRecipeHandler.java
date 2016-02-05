package unstudio.chinacraft.common.nei;

import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import unstudio.chinacraft.client.gui.GuiBuhrimill;
import unstudio.chinacraft.recipes.BuhrimillRecipe;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Recipe Handler of Buhrimill.
 */
public class BuhrimillRecipeHandler extends TemplateRecipeHandler {

    /**
     * Pair of Smelting.
     */
	public class SmeltingPair extends CachedRecipe
    {
        public SmeltingPair(ItemStack in1, ItemStack out1 ,ItemStack in2, ItemStack out2) {
            in1.stackSize = 1;
            this.input1 = new PositionedStack(in1, 38, 14);
            this.output1 = new PositionedStack(out1, 112-5, 14);
            if (in2 != null) {
                this.input2 = new PositionedStack(in2, 38, 28);
            }
            if (out2 != null) {
                this.output2 = new PositionedStack(out2, 112-5, 28);
            }
        }

        public List<PositionedStack> getIngredients() {
            return getCycledIngredients(cycleticks / 48, Arrays.asList(input1));
        }

        public PositionedStack getResult() {
            return output1;
        }


        PositionedStack input1;
        PositionedStack input2;
        PositionedStack output1;
        PositionedStack output2;
    }

    public static class FuelPair
    {
        public FuelPair(ItemStack ingred, int burnTime) {
            this.stack = new PositionedStack(ingred, 0,0, false);
            this.burnTime = burnTime;
        }

        public PositionedStack stack;
        public int burnTime;
    }

    public static ArrayList<FuelPair> afuels;
    public static HashSet<Block> efuels;

    @Override
    public void loadTransferRects() {
    	transferRects.add(new RecipeTransferRect(new Rectangle(76 - 5, 21 - 11, 22, 12), "milling"));
    }

    @Override
    public Class<? extends GuiContainer> getGuiClass() {
        return GuiBuhrimill.class;
    }

    @Override
    public String getRecipeName() {
        return NEIClientUtils.translate("gui.buhrimill.title");
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("buhrimill") && getClass() == BuhrimillRecipeHandler.class) {//don't want subclasses getting a hold of this
            List<BuhrimillRecipe> recipes = BuhrimillRecipe.getRecipes();
            for (BuhrimillRecipe recipe : recipes)
                arecipes.add(new SmeltingPair(recipe.getInput1(), recipe.getOutput2(),recipe.getInput2(),recipe.getOutput2()));
        } else
            super.loadCraftingRecipes(outputId, results);
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        List<BuhrimillRecipe> recipes = BuhrimillRecipe.getRecipes();
        for (BuhrimillRecipe recipe : recipes)
            if (NEIServerUtils.areStacksSameType(recipe.getOutput1(), result))
                arecipes.add(new SmeltingPair(recipe.getInput1(), recipe.getOutput1(),recipe.getInput2(),recipe.getOutput2()));
    }

    @Override
    public void loadUsageRecipes(String inputId, Object... ingredients) {
        if (inputId.equals("fuel") && getClass() == BuhrimillRecipeHandler.class)//don't want subclasses getting a hold of this
            loadCraftingRecipes("buhrimill");
        else
            super.loadUsageRecipes(inputId, ingredients);
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        List<BuhrimillRecipe> recipes = BuhrimillRecipe.getRecipes();
        for (BuhrimillRecipe recipe : recipes)
            if (NEIServerUtils.areStacksSameTypeCrafting(recipe.getInput1(), ingredient)) {
                SmeltingPair arecipe = new SmeltingPair(recipe.getInput1(), recipe.getOutput1(),recipe.getInput2(),recipe.getOutput2());
                arecipe.setIngredientPermutation(Arrays.asList(arecipe.input1), ingredient);
                arecipes.add(arecipe);
            }
    }

    @Override
    public String getGuiTexture() {
        return "chinacraft:textures/gui/nei/buhrimill.png";
    }

    @Override
    public void drawExtras(int recipe) {
      //drawProgressBar(X, Y, TX, TY, W, H, Ticks, direction);
    	drawProgressBar(76 - 5, 21 - 11, 176, 14, 22, 12, 48, 0);
        drawProgressBar(81 - 5, 37 - 11, 176, 0, 14, 14, 48, 7);
    }

//    private static Set<Item> excludedFuels() {
//        Set<Item> efuels = new HashSet<Item>();
//        efuels.add(Item.getItemFromBlock(Blocks.brown_mushroom));
//        efuels.add(Item.getItemFromBlock(Blocks.red_mushroom));
//        efuels.add(Item.getItemFromBlock(Blocks.standing_sign));
//        efuels.add(Item.getItemFromBlock(Blocks.wall_sign));
//        efuels.add(Item.getItemFromBlock(Blocks.wooden_door));
//        efuels.add(Item.getItemFromBlock(Blocks.trapped_chest));
//        return efuels;
//    }


    @Override
    public String getOverlayIdentifier() {
        return "buhrimill";
    }
}
