package unstudio.chinacraft.block.decoration;

import com.sun.org.apache.xml.internal.security.utils.I18n;
import net.minecraft.block.BlockPane;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import unstudio.chinacraft.common.ChinaCraft;

public class BlockWoodenWindow extends BlockPane {

    public BlockWoodenWindow(String arg1, String arg2) {
        super(Material.WOOD, true);
        setUnlocalizedName(I18n.translate("wooden_window"));
        setHardness(0.3F);
        setSoundType(SoundType.WOOD);
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
