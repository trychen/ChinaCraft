package unstudio.chinacraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBase extends Block {

    private IIcon icon;

    /**
     * public修饰的Block父类
     */
    public BlockBase(Material p_i45394_1_) {
        super(p_i45394_1_);
    }

    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        icon = p_149651_1_.registerIcon(getTextureName());
    }

}
