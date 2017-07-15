package unstudio.chinacraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import unstudio.chinacraft.common.ChinaCraft;

import java.util.List;

/**
 * Created by trychen on 17/5/7.
 */
public class CCBlockWall extends BlockWall{
    public Block block;

    public CCBlockWall(Block b) {
        super(b);
        this.block = b;
        this.setCreativeTab(ChinaCraft.tabCore);
    }

    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return block.getIcon(p_149691_1_, p_149691_2_);
    }

    @Override
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
    }
}
