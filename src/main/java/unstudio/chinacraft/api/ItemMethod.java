package unstudio.chinacraft.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemMethod {
    public static ItemStack cutItemStack(ItemStack stack,EntityPlayer player){
        if (!player.capabilities.isCreativeMode){
            stack.stackSize -= 1;
        }
        return stack;
    }
}
