package unstudio.chinacraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Public修饰的Block主类
 */
public class BlockBase extends Block {

    //private IIcon icon;

    public BlockBase(Material p_i45394_1_) {
        super(p_i45394_1_);
    }

    /*@Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        icon = p_149651_1_.registerIcon(getTextureName());
    }*/

    public Block setHarvestLevelReturnBlock(String toolClass, int level) {
        super.setHarvestLevel(toolClass, level);
        return this;
    }
}
