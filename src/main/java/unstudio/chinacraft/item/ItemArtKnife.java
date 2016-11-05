package unstudio.chinacraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.util.ItemLoreHelper;

import java.util.ArrayList;
import java.util.List;

public class ItemArtKnife extends CCItemThrowable {

    public ItemArtKnife() {
        super(0.5f,false,false);
        setUnlocalizedName("art_knife");
        setMaxStackSize(1);
        setMaxDamage(6);
        setHasSubtypes(false);
        setCreativeTab(ChinaCraft.tabTool);
        setHitSound("dig.wood");
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
}
