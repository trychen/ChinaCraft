package unstudio.chinacraft.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.StatCollector;
import unstudio.chinacraft.common.ChinaCraft;

/**
 * Created by AAA on 2016/2/13.
 */
public class BlockCCLog extends BlockLog {
    //private IIcon top, side;
    private String topTexture, sideTexture;

    public BlockCCLog(String topTexture, String sideTexture) {
        this.topTexture = topTexture;
        this.sideTexture = sideTexture;
    }

    public Block setTopTextureName(String arg1) {
        this.topTexture = arg1;
        return this;
    }

    public Block setSideTextureName(String arg1) {
        this.sideTexture = arg1;
        return this;
    }
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState();

        switch (meta & 12)
        {
            case 0:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
                break;
            case 8:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
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

        switch ((BlockLog.EnumAxis)state.getValue(LOG_AXIS))
        {
            case X:
                i |= 4;
                break;
            case Z:
                i |= 8;
                break;
            case NONE:
                i |= 12;
        }

        return i;
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, LOG_AXIS);
    }
    /*@Override
    @SideOnly(Side.CLIENT)
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
}
