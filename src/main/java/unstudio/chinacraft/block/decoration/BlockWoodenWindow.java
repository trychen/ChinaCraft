package unstudio.chinacraft.block.decoration;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.StatCollector;
import unstudio.chinacraft.common.ChinaCraft;

public class BlockWoodenWindow extends BlockPane {

    public BlockWoodenWindow(String arg1, String arg2) {
        super(Material.wood, true);
        setUnlocalizedName(StatCollector.translateToLocal("wooden_window"));
        setHardness(0.3F);
        setStepSound(soundTypeWood);
        setCreativeTab(ChinaCraft.tabCore);
    }

    @Override
    public int damageDropped(IBlockState state) {
    	return getMetaFromState(state);
    }

    /*@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        super.registerBlockIcons(p_149651_1_);
    }*/
}
