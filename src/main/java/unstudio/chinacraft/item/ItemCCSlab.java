package unstudio.chinacraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import unstudio.chinacraft.block.decoration.BlockCCSlab;

/**
 * Created by AAA on 2016/2/9.
 */
public class ItemCCSlab extends ItemSlab{
    public ItemCCSlab(Block block, BlockCCSlab slab, BlockCCSlab doubleSlab, Boolean stacked) {
        super(block,slab,doubleSlab,stacked);
    }
}
