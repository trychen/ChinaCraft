package unstudio.chinacraft.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing.Axis;

/**
 * Created by AAA on 2016/2/13.
 */
public class BlockCCRotatedPillar extends BlockRotatedPillar{

    //private IIcon side,top;
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

    /*@SideOnly(Side.CLIENT)
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
    }*/
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState();

        switch (meta & 12)
        {
            case 0:
                iblockstate = iblockstate.withProperty(AXIS, Axis.Y);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(AXIS, Axis.X);
                break;
            case 8:
                iblockstate = iblockstate.withProperty(AXIS, Axis.Z);
                break;
        }

        return iblockstate;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @SuppressWarnings("incomplete-switch")
    public int getMetaFromState(IBlockState state)
    {
    	int i = 0;
        switch ((Axis)state.getValue(AXIS))
        {
            case X:
                i |= 4;
                break;
            case Z:
                i |= 8;
                break;
        }

        return i;
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, AXIS);
    }
}
