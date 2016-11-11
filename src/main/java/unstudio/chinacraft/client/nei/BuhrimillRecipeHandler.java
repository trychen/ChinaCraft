package unstudio.chinacraft.client.nei;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import unstudio.chinacraft.client.gui.GuiBuhrimill;
import unstudio.chinacraft.recipes.BuhrimillRecipe;
import unstudio.chinacraft.util.ItemStackHelper;
import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

/**
 * 石磨合成Handler
 */
public class BuhrimillRecipeHandler extends TemplateRecipeHandler {
    @Override
    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(76 - 5, 21 - 11, 22, 12), "buhrimill"));
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
            for (BuhrimillRecipe recipe : recipes) {
                arecipes.add(new SmeltingPair(recipe.getInput1(), recipe.getOutput1(), recipe.getInput2(),
                        recipe.getOutput2(), recipe.getTime()));
            }
        } else
            super.loadCraftingRecipes(outputId, results);
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        List<BuhrimillRecipe> recipes = BuhrimillRecipe.getRecipes();
        for (BuhrimillRecipe recipe : recipes) {
            if (ItemStackHelper.isItemEquivalent(recipe.getOutput1(), result))
                arecipes.add(new SmeltingPair(recipe.getInput1(), recipe.getOutput1(), recipe.getInput2(),
                        recipe.getOutput2(), recipe.getTime()));
        }
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
            if (ItemStackHelper.isItemEquivalent(recipe.getInput1(), ingredient)) {
                arecipes.add(new SmeltingPair(recipe.getInput1(), recipe.getOutput1(), recipe.getInput2(),
                        recipe.getOutput2(), recipe.getTime()));
            }
    }

    @Override
    public String getGuiTexture() {
        return "chinacraft:textures/gui/nei/buhrimill.png";
    }

    @Override
    public void drawExtras(int recipe) {
        // drawProgressBar(X, Y, TX, TY, W, H, Ticks, direction);
        drawProgressBar(76 - 5, 13, 176, 0, 24, 16, 48, 0);
        // 齿轮图标, 暂不绘制
        // drawProgressBar(81 - 7, 37, 176, 14, 16, 20, 48, 3);
        GuiDraw.drawStringC(I18n.format("nei.gui.buhrimill.rotime.info", ((SmeltingPair)this.arecipes.get(recipe)).roTimes), 76 + 5, 28, 0x808080, false);
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
        float roTimes;
        public SmeltingPair(ItemStack in1, ItemStack out1, ItemStack in2, ItemStack out2, int roTimes) {     
            this.input1 = new PositionedStack(ItemStackHelper.getEquivalentItemStacks(in1), 38, 14);
            this.output1 = new PositionedStack(out1, 112 - 5, 14);
            if (in2 != null)this.input2 = new PositionedStack(ItemStackHelper.getEquivalentItemStacks(in2), 38, 39);
            if (out2 != null)this.output2 = new PositionedStack(out2, 112 - 5, 39);
            this.roTimes = (float) (Math.ceil(roTimes/36.0F) * 0.1F);
        }

        public List<PositionedStack> getIngredients() {
            ArrayList<PositionedStack> list= new ArrayList<>();
            // 不使用默认的随机循环显示而是按照次序依次循环显示
            input1.setPermutationToRender((cycleticks / 20) % input1.items.length);
            list.add(input1);
            if (input2 != null) {
                input2.setPermutationToRender((cycleticks / 20) % input2.items.length);
                list.add(input2);
            }
            if (output2 != null)list.add(output2);
            return list;
        }

        public PositionedStack getResult() {
            return output1;
        }
    }
}
