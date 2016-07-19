package unstudio.chinacraft.tileentity;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public class TileFirebrickStructure extends TileEntity {

    private BlockPos tilePos;

    @Override
    public void readFromNBT(NBTTagCompound p_145839_1_) {
        super.readFromNBT(p_145839_1_);
        int x = p_145839_1_.getInteger("tilex"),
        y = p_145839_1_.getInteger("tiley"),
        z = p_145839_1_.getInteger("tilez");
        tilePos = new BlockPos(x, y, z);
    }

    @Override
    public void writeToNBT(NBTTagCompound p_145841_1_) {
        super.writeToNBT(p_145841_1_);
        p_145841_1_.setInteger("tilex", tilePos.getX());
        p_145841_1_.setInteger("tiley", tilePos.getY());
        p_145841_1_.setInteger("tilez", tilePos.getZ());
    }

    public TileEntity getTilePotteryKiln() {
        return worldObj.getTileEntity(tilePos);
    }

    public void setPosition(int x, int y, int z) {
    	tilePos = new BlockPos(x,y,z);
    }
    public void setPosition(BlockPos pos) {
        tilePos = pos;
    }

    public BlockPos getTilePos() {
        return tilePos;
    }
}
