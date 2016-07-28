package unstudio.chinacraft.client.nei;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;

import unstudio.chinacraft.client.gui.GuiBuhrimill;
import unstudio.chinacraft.recipes.BuhrimillRecipe;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

/**
 * 石磨合成Handler
 */
public class BuhrimillRecipeHandler extends TemplateRecipeHandler {
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
        if (outputId.equals("buhrimill") && getClass() == BuhrimillRecipeHandler.class) {
            List<BuhrimillRecipe> recipes = BuhrimillRecipe.getRecipes();
            for (BuhrimillRecipe recipe : recipes)
                arecipes.add(new SmeltingPair(recipe.getInput1(), recipe.getOutput2(), recipe.getInput2(),
                        recipe.getOutput2(),recipe.getTime()/360));
        } else
            super.loadCraftingRecipes(outputId, results);
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        List<BuhrimillRecipe> recipes = BuhrimillRecipe.getRecipes();
        for (BuhrimillRecipe recipe : recipes)
            if (NEIServerUtils.areStacksSameType(recipe.getOutput1(), result))
                arecipes.add(new SmeltingPair(recipe.getInput1(), recipe.getOutput1(), recipe.getInput2(),
                        recipe.getOutput2(),recipe.getTime()/360));
    }

    @Override
    public void loadUsageRecipes(String inputId, Object... ingredients) {
        if (inputId.equals("fuel") && getClass() == BuhrimillRecipeHandler.class)
            loadCraftingRecipes("buhrimill");
        else
            super.loadUsageRecipes(inputId, ingredients);
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        List<BuhrimillRecipe> recipes = BuhrimillRecipe.getRecipes();
        for (BuhrimillRecipe recipe : recipes)
            if (NEIServerUtils.areStacksSameTypeCrafting(recipe.getInput1(), ingredient)) {
                SmeltingPair arecipe = new SmeltingPair(recipe.getInput1(), recipe.getOutput1(), recipe.getInput2(),
                        recipe.getOutput2(),recipe.getTime()/360);
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
        // drawProgressBar(X, Y, TX, TY, W, H, Ticks, direction);
        drawProgressBar(76 - 5, 21 - 11, 176, 14, 22, 12, 48, 0);
        drawProgressBar(81 - 5, 37 - 11, 176, 0, 14, 14, 48, 7);
    }

    @Override
    public String getOverlayIdentifier() {
        return "buhrimill";
    }

    public class SmeltingPair extends CachedRecipe {
        PositionedStack input1;
        PositionedStack input2;
        PositionedStack output1;
        PositionedStack output2;
        int roTimes;
        public SmeltingPair(ItemStack in1, ItemStack out1, ItemStack in2, ItemStack out2 , int roTimes) {
            in1.stackSize = 1;
            this.input1 = new PositionedStack(in1, 38, 14);
            this.output1 = new PositionedStack(out1, 112 - 5, 14);
            if (in2 != null) {
                this.input2 = new PositionedStack(in2, 38, 28);
            }
            if (out2 != null) {
                this.output2 = new PositionedStack(out2, 112 - 5, 28);
            }
            this.roTimes = roTimes;
        }

        public List<PositionedStack> getIngredients() {
            return getCycledIngredients(cycleticks / 48, Arrays.asList(input1));
        }

        public PositionedStack getResult() {
            return output1;
        }
    }
}
