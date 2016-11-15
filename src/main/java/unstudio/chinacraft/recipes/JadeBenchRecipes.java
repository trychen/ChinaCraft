package unstudio.chinacraft.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import scala.reflect.internal.Trees.New;
import unstudio.chinacraft.util.ItemStackHelper;

public class JadeBenchRecipes {
    
    private static List<JadeBenchOreRecipe> listOreRecipe = new ArrayList<JadeBenchOreRecipe>();
    private static List<JadeBenchModifyRecipe> listModifyRecipe = new ArrayList<JadeBenchModifyRecipe>();
    private static int totalWeightedChanceForOre = 0;
    
    // Ore recipes 
    public static void registerOreRecipe(ItemStack output, int weightedChance) {
        listOreRecipe.add(new JadeBenchOreRecipe(output, weightedChance));
        totalWeightedChanceForOre += weightedChance;
    }
    
    public static void removeOreRecipe(ItemStack outputJade) {
        for (JadeBenchOreRecipe oreRecipe: listOreRecipe) 
            if (oreRecipe.outputJade.isItemEqual(outputJade)) {
                listOreRecipe.remove(oreRecipe);
                totalWeightedChanceForOre -= oreRecipe.weightedChance;
                return;
            }
    }
    
    public static void removeAllOreRecipe() {
        listOreRecipe.clear();
    }
    
    public static List<JadeBenchOreRecipe> getOreRecipes() {
        return listOreRecipe;
    }
    
    public static int getTotalWeightedChanceForOre() {
        return totalWeightedChanceForOre;
    }
    
    public static void recalculateTotalWeightedChance() {
        totalWeightedChanceForOre = 0;
        for (JadeBenchOreRecipe oreRecipe: listOreRecipe)
            totalWeightedChanceForOre += oreRecipe.weightedChance;
    }
    
    // Jade recipes
    public static void registerModifyRecipe(ItemStack inputWeapon, ItemStack inputJade, ItemStack outputWeapon, ItemStack outputEpixWeapon, float epixModifyChance) {
        listModifyRecipe.add(new JadeBenchModifyRecipe(inputWeapon, inputJade, outputWeapon, outputEpixWeapon, epixModifyChance));
    }
    
    public static void removeModifyRecipe(ItemStack inputWeapon, ItemStack inputJade, ItemStack outputWeapon, ItemStack outputEpixWeapon) {
        for (JadeBenchModifyRecipe modifyRecipe: listModifyRecipe)
            if (ItemStackHelper.isItemEqualWithoutDamage(modifyRecipe.inputWeapon, inputWeapon) && inputJade.isItemEqual(modifyRecipe.inputJade) && ItemStackHelper.isItemEqualWithoutDamage(modifyRecipe.outputWeapon, outputWeapon) && ItemStackHelper.isItemEqualWithoutDamage(modifyRecipe.outputEpixWeapon, outputEpixWeapon)) {
                listModifyRecipe.remove(modifyRecipe);
                return;
            }
    }
    
    public static void removeAllModifyRecipe() {
        listModifyRecipe.clear();
    }
    
    public static JadeBenchModifyRecipe getModifyRecipe(ItemStack inputWeapon, ItemStack inputJade) {
        for (JadeBenchModifyRecipe modifyRecipe: listModifyRecipe)
            if (ItemStackHelper.isItemEqualWithoutDamage(modifyRecipe.inputWeapon, inputWeapon) && inputJade.isItemEqual(modifyRecipe.inputJade))
                return modifyRecipe;
        return null;
    }
    
    public static List<JadeBenchModifyRecipe> getModifyRecipes() {
        return listModifyRecipe;
    }
    
    // Ore recipe inner class
    public static class JadeBenchOreRecipe {
        public ItemStack outputJade;
        public int weightedChance;
        
        public JadeBenchOreRecipe(ItemStack outputJade, int weightedChance) {
            this.outputJade = outputJade;
            this.weightedChance = weightedChance;
        }    
    }
    
    // Modify recipe inner class
    public static class JadeBenchModifyRecipe {
        public ItemStack inputWeapon;
        public ItemStack inputJade;
        public ItemStack outputWeapon;
        public ItemStack outputEpixWeapon;
        public float epixModifyChance;
        
        public JadeBenchModifyRecipe(ItemStack inputWeapon, ItemStack inputJade, ItemStack outputWeapon, ItemStack outputEpixWeapon, float epixModifyChance) {
            this.inputWeapon = inputWeapon;
            this.inputJade = inputJade;
            this.outputWeapon = outputWeapon;
            this.outputEpixWeapon = outputEpixWeapon;
            this.epixModifyChance = epixModifyChance;
        }
    }
    
    
    
    
}
