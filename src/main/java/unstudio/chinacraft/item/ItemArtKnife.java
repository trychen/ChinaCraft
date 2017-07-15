package unstudio.chinacraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.util.ItemLoreHelper;

import java.util.List;

public class ItemArtKnife extends Item {

    public ItemArtKnife() {
        setUnlocalizedName("art_knife");
        setMaxStackSize(1);
        setMaxDamage(64);
        setHasSubtypes(false);
        setCreativeTab(ChinaCraft.tabTool);
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
        return itemStack;
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        ItemLoreHelper.shiftLoreWithStat(p_77624_3_, getUnlocalizedName());
    }
}
