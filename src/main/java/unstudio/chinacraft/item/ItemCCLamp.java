package unstudio.chinacraft.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import unstudio.chinacraft.common.ChinaCraft;

/**
 * Created by Mouse on 2017/1/24.
 */
public class ItemCCLamp extends ItemBlockWithMetadata{

    public ItemCCLamp(Block block){
        super(block,block);
    }

    @Override
    public String getUnlocalizedName(ItemStack p_77667_1_) {
        return this.getUnlocalizedName()+"_"+p_77667_1_.getItemDamage();
    }
}
