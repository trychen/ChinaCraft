package unstudio.chinacraft.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import unstudio.chinacraft.common.ChinaCraft;

/**
 * Created by trychen on 16/7/31.
 */
public class ItemCCStaff extends Item {
    public ItemCCStaff() {
        setMaxStackSize(1);
        setCreativeTab(ChinaCraft.tabTool);
        setFull3D();
    }

    @Override
    public int getMaxItemUseDuration(ItemStack p_77626_1_) {
        return 72000;
    }
}
