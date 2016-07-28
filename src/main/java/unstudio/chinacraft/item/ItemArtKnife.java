package unstudio.sinocraft.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import unstudio.sinocraft.common.SinoCraft;

public class ItemArtKnife extends Item {

    public ItemArtKnife() {
        setUnlocalizedName("art_knife");
        setMaxStackSize(1);
        setMaxDamage(6);
        setHasSubtypes(false);
        setCreativeTab(SinoCraft.tabTool);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        itemStack.setItemDamage(itemStack.getItemDamage()+1);
        if(itemStack.getItemDamage()>=getMaxDamage())
            itemStack.stackSize=0;
        //不懂如何添加破坏的声音
        return itemStack;
    }


}
