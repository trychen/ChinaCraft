package unstudio.chinacraft.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * 关于物品的一些方法
 */
public class ItemMethod {
    /**
     * 减物品的损耗值
     */
    public static ItemStack cutItemStack(ItemStack stack, EntityPlayer player) {
        if (!player.capabilities.isCreativeMode) {
            stack.stackSize -= 1;
        }
        return stack;
    }
}
