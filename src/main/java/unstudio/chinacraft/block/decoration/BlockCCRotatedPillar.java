package unstudio.sinocraft.block.decoration;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/**
 * Created by AAA on 2016/2/13.
 */
public class BlockCCRotatedPillar extends BlockRotatedPillar{

    private IIcon side,top;
    private String topTexture, sideTexture;

    public BlockCCRotatedPillar(Material p_i45425_1_,String topTexture, String sideTexture) {
        super(p_i45425_1_);
        this.topTexture = topTexture;
        this.sideTexture = sideTexture;
    }

    public Block setHarvestLevelReturnBlock(String toolClass, int level) {
        super.setHarvestLevel(toolClass, level);
        return this;
    }

    public Block setTopTextureName(String arg1) {
        this.topTexture = arg1;
        return this;
    }

    public Block setSideTextureName(String arg1) {
        this.sideTexture = arg1;
        return this;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.top = reg.registerIcon(topTexture);
        this.side = reg.registerIcon(sideTexture);
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int p_150163_1_) {
        return side;
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int p_150161_1_) {
        return top;
    }
}
