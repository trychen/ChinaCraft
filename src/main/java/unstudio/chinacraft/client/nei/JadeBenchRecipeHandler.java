package unstudio.chinacraft.client.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler.CachedRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import unstudio.chinacraft.client.gui.GuiJadeBench;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.item.combat.Hammer;
import unstudio.chinacraft.recipes.JadeBenchRecipes;
import unstudio.chinacraft.recipes.JadeBenchRecipes.JadeBenchModifyRecipe;
import unstudio.chinacraft.recipes.JadeBenchRecipes.JadeBenchOreRecipe;
import unstudio.chinacraft.util.ItemStackHelper;

/**
 * 玉石加工台合成Handler
 */
public class JadeBenchRecipeHandler extends TemplateRecipeHandler {
    @Override
    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(38, 20, 22, 20), "jadebench"));
    }

    @Override
    public Class<? extends GuiContainer> getGuiClass() {
        return GuiJadeBench.class;
    }

    @Override
    public String getRecipeName() {
        return NEIClientUtils.translate("gui.jadebench.title");
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("jadebench")) {
            // Ore recipes
            for (JadeBenchOreRecipe oreRecipe: JadeBenchRecipes.getOreRecipes())
                arecipes.add(new CachedOreRecipe(oreRecipe.outputJade, (100.0F * oreRecipe.weightedChance) / JadeBenchRecipes.getTotalWeightedChanceForOre()));
            // Modify recipes
            for (JadeBenchModifyRecipe modifyRecipe: JadeBenchRecipes.getModifyRecipes()) 
                arecipes.add(new CachedModifyRecipe(modifyRecipe.inputWeapon, modifyRecipe.inputJade, modifyRecipe.outputWeapon, modifyRecipe.outputEpixWeapon, modifyRecipe.epixModifyChance));    
        } else
            super.loadCraftingRecipes(outputId, results);
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        // Ore recipes
        for (JadeBenchOreRecipe oreRecipe: JadeBenchRecipes.getOreRecipes())
            if (oreRecipe.outputJade.isItemEqual(result))
                arecipes.add(new CachedOreRecipe(oreRecipe.outputJade, (100.0F * oreRecipe.weightedChance) / JadeBenchRecipes.getTotalWeightedChanceForOre()));     
        // Modify recipes
        for (JadeBenchModifyRecipe modifyRecipe: JadeBenchRecipes.getModifyRecipes())
            if (ItemStackHelper.isItemEqualWithoutDamage(modifyRecipe.outputWeapon, result) || ItemStackHelper.isItemEqualWithoutDamage(modifyRecipe.outputEpixWeapon, result))
                arecipes.add(new CachedModifyRecipe(modifyRecipe.inputWeapon, modifyRecipe.inputJade, modifyRecipe.outputWeapon, modifyRecipe.outputEpixWeapon, modifyRecipe.epixModifyChance));
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        // Ore recipes
        if (ingredient.getItem() instanceof Hammer || ItemStackHelper.isItemEquivalent(new ItemStack(ChinaCraft.jadeOre), ingredient))
            for (JadeBenchOreRecipe oreRecipe: JadeBenchRecipes.getOreRecipes()) 
                arecipes.add(new CachedOreRecipe(oreRecipe.outputJade, (100.0F * oreRecipe.weightedChance) / JadeBenchRecipes.getTotalWeightedChanceForOre()));         
        // Modify recipes
        if (ingredient.getItem() instanceof Hammer)
            for (JadeBenchModifyRecipe modifyRecipe: JadeBenchRecipes.getModifyRecipes()) 
                arecipes.add(new CachedModifyRecipe(modifyRecipe.inputWeapon, modifyRecipe.inputJade, modifyRecipe.outputWeapon, modifyRecipe.outputEpixWeapon, modifyRecipe.epixModifyChance));
        else 
            for (JadeBenchModifyRecipe modifyRecipe: JadeBenchRecipes.getModifyRecipes()) 
                if (modifyRecipe.inputJade.isItemEqual(ingredient) || ItemStackHelper.isItemEqualWithoutDamage(modifyRecipe.inputWeapon, ingredient))
                    arecipes.add(new CachedModifyRecipe(modifyRecipe.inputWeapon, modifyRecipe.inputJade, modifyRecipe.outputWeapon, modifyRecipe.outputEpixWeapon, modifyRecipe.epixModifyChance));  
    }

    @Override
    public String getGuiTexture() {
        return "chinacraft:textures/gui/jadetable.png";
    }
    
    @Override
    public void drawExtras(int recipe) {
        CachedRecipe cachedRecipe = arecipes.get(recipe);
        if (cachedRecipe instanceof CachedOreRecipe) {
            CachedOreRecipe oreRecipe = (CachedOreRecipe) arecipes.get(recipe);
            GuiDraw.drawStringC(String.format("%.2f%%", oreRecipe.chance), 75, 40, 0xFFFFFF, false);
        }
        else if (cachedRecipe instanceof CachedModifyRecipe) {
            CachedModifyRecipe modifyRecipe = (CachedModifyRecipe) arecipes.get(recipe);
            GuiDraw.drawString(I18n.format("nei.jadebench.output.info"), 110, 10, 0xFFFFFF, false);
            GuiDraw.drawStringR(String.format("%.2f%%", 100 * (1 - modifyRecipe.chance)), 147, 25, 0xFFFFFF, false);
            GuiDraw.drawStringR(String.format("%.2f%%", 100 * modifyRecipe.chance), 147, 42, 0xFFFFFF, false);
        }
      }

    @Override
    public String getOverlayIdentifier() {
        return "jadebench";
    }

    public class CachedOreRecipe extends CachedRecipe {
        public PositionedStack hammer;
        public PositionedStack ore;
        public PositionedStack outputJade;
        public float chance;
        
        public CachedOreRecipe(ItemStack outputJade, float chance) {
            this.outputJade = new PositionedStack(outputJade, 66, 23);
            this.chance = chance;
            List<ItemStack> listHammer = new ArrayList<ItemStack>();
            listHammer.add(new ItemStack(ChinaCraft.hammerStone));
            listHammer.add(new ItemStack(ChinaCraft.hammerIron));
            listHammer.add(new ItemStack(ChinaCraft.hammerBronze));
            listHammer.add(new ItemStack(ChinaCraft.hammerDiamond));
            this.hammer = new PositionedStack(listHammer, 20, 6);
            this.ore = new PositionedStack(ItemStackHelper.getEquivalentItemStacks(new ItemStack(ChinaCraft.jadeOre)), 21, 40);
        }
        
        @Override
        public List<PositionedStack> getIngredients() {
            List<PositionedStack> listIngredients = new ArrayList<PositionedStack>();
            hammer.setPermutationToRender((cycleticks / 20) % hammer.items.length);
            ore.setPermutationToRender((cycleticks / 20) % ore.items.length);
            listIngredients.add(hammer);
            listIngredients.add(ore);
            listIngredients.add(outputJade);
            return listIngredients;
        }
        
        @Override
        public PositionedStack getResult() {
            return null;
        }      
    }
    
    public class CachedModifyRecipe extends CachedRecipe {
        public PositionedStack hammer;
        public PositionedStack inputWeapon;
        public PositionedStack inputJade;
        public PositionedStack outputWeapon;
        public PositionedStack outputEpixWeapon;
        public float chance;
        
        public CachedModifyRecipe(ItemStack inputWeapon, ItemStack inputJade, ItemStack outputWeapon, ItemStack outputEpixWeapon, float chance) {
            this.inputJade = new PositionedStack(inputJade, 66, 23);
            this.chance = chance;
            List<ItemStack> listHammer = new ArrayList<ItemStack>();
            listHammer.add(new ItemStack(ChinaCraft.hammerStone));
            listHammer.add(new ItemStack(ChinaCraft.hammerIron));
            listHammer.add(new ItemStack(ChinaCraft.hammerBronze));
            listHammer.add(new ItemStack(ChinaCraft.hammerDiamond));
            this.hammer = new PositionedStack(listHammer, 20, 6);
            this.inputWeapon = new PositionedStack(inputWeapon, 21, 40);
            this.outputWeapon = new PositionedStack(outputWeapon, 107, 21);
            this.outputEpixWeapon = new PositionedStack(outputEpixWeapon, 107, 38);
        }
        
        @Override
        public List<PositionedStack> getIngredients() {
            List<PositionedStack> listIngredients = new ArrayList<PositionedStack>();
            hammer.setPermutationToRender((cycleticks / 20) % hammer.items.length);
            listIngredients.add(hammer);
            listIngredients.add(inputWeapon);
            listIngredients.add(inputJade);
            listIngredients.add(outputWeapon);
            listIngredients.add(outputEpixWeapon);
            return listIngredients;
        }
        
        @Override
        public PositionedStack getResult() {
            return null;
        }      
    }

}
