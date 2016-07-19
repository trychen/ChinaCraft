package unstudio.chinacraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import unstudio.chinacraft.common.ChinaCraft;

public class CupDrink extends ItemSoup {

    public CupDrink(int p_i45330_1_) {
        super(p_i45330_1_);
        this.setMaxStackSize(1);
        setCreativeTab(ChinaCraft.tabFarming);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        super.onItemUseFinish(stack, worldIn, playerIn);
        return new ItemStack(ChinaCraft.cup);
    }
}
