package unstudio.chinacraft.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemStackHelper {
    
    public static boolean isItemEqualWithoutDamage(ItemStack target, ItemStack input) {
        return target.getItem() == input.getItem();
    }
    
    public static boolean isItemEquivalent(ItemStack target, ItemStack input) {
        int[] targetIDs = OreDictionary.getOreIDs(target);
        int[] inputIDs = OreDictionary.getOreIDs(input);
        if (targetIDs != null && inputIDs != null)
            for (int targetID: targetIDs)
                for (int inputID: inputIDs)
                    if (targetID == inputID)
                        return true;
        return target.isItemEqual(input);
    }
    
    public static List<ItemStack> getEquivalentItemStacks(ItemStack stack) {
        List<ItemStack> stackList = new ArrayList<ItemStack>();
        int[] oreIDs = OreDictionary.getOreIDs(stack);
        if (oreIDs != null) {
            for (int oreID: oreIDs)
                if (oreID != -1) {
                    List<ItemStack> originStackList = OreDictionary.getOres(oreID);
                    if (originStackList != null && !originStackList.isEmpty())
                        for (ItemStack originStack: originStackList) {
                            ItemStack newStack = originStack.copy();
                            newStack.stackSize = stack.stackSize;
                            stackList.add(newStack);
                        }   
                } 
        }
        if (stackList.isEmpty()) stackList.add(stack);
        return stackList;
    }
    
}
