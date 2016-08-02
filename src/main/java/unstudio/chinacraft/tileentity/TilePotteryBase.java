package unstudio.chinacraft.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TilePotteryBase extends TileEntity {

    private String potteryType;

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return false;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound = super.writeToNBT(compound);
        compound.setString("PotteryType", potteryType);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        potteryType = compound.getString("PotteryType");
    }

    public String getPotteryType() {
        return potteryType;
    }

    public void setPotteryType(String potteryType) {
        this.potteryType = potteryType;
    }
}
