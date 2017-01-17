package unstudio.chinacraft.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import unstudio.chinacraft.util.ItemStackHelper;

public class BuhrimillRecipe {

    private static List<BuhrimillRecipe> recipes = new ArrayList<BuhrimillRecipe>();
    private final ItemStack input1;
    private final ItemStack input2;
    private final ItemStack output1;
    private final ItemStack output2;
    private final int time;

    /**
     * 构造石磨配方
     *
     * @param input1
     *            主原料
     * @param input2
     *            副原料
     * @param output1
     *            主产品
     * @param output2
     *            副产品
     * @param time
     *            时间,石磨上部旋转的度数
     */
    public BuhrimillRecipe(ItemStack input1, ItemStack input2, ItemStack output1, ItemStack output2, int time) {
        this.input1 = input1;
        this.input2 = input2;
        this.output1 = output1;
        this.output2 = output2;
        this.time = time;
    }

    public static void registerBuhrimillReciper(ItemStack input1, ItemStack input2, ItemStack output1,
            ItemStack output2, int time) {
        if (input1 == null || output1 == null)
            return;
        recipes.add(new BuhrimillRecipe(input1, input2, output1, output2, time));
    }

    public static BuhrimillRecipe getBuhrimillReciper(ItemStack input1) {
        for (BuhrimillRecipe r : recipes) {
            if (ItemStackHelper.isItemEquivalent(r.getInput1(), input1) && r.getInput2() == null) {
                return r;
            }
        }
        return null;
    }

    public static BuhrimillRecipe getBuhrimillReciper(ItemStack input1, ItemStack input2) {
        for (BuhrimillRecipe r : recipes) {
            if (ItemStackHelper.isItemEquivalent(r.getInput1(), input1) && r.getInput2() != null && ItemStackHelper.isItemEquivalent(r.getInput2(), input2)) {
                return r;
            }
        }
        return null;
    }

    public static int getBuhrimillReciperTime(ItemStack input1, ItemStack input2) {
        for (BuhrimillRecipe r : recipes) {
            if (ItemStackHelper.isItemEquivalent(r.getInput1(), input1) && r.getInput2() != null) {
                if (r.getInput2() == null && input2 == null) {
                    return r.getTime();
                } else {
                    if (input2 != null && r.getInput2() != null && ItemStackHelper.isItemEquivalent(r.getInput2(), input2)) {
                        return r.getTime();
                    }
                }
            }
        }
        return 0;
    }

    public static List<BuhrimillRecipe> getRecipes() {
        return recipes;
    }

    public ItemStack getInput1() {
        return input1;
    }

    public ItemStack getInput2() {
        return input2;
    }

    public ItemStack getOutput1() {
        return output1;
    }

    public ItemStack getOutput2() {
        return output2;
    }

    public int getTime() {
        return time;
    }

    public static boolean isInput(ItemStack item){
        return getBuhrimillReciperTime(item,null) > 0;
    }
}