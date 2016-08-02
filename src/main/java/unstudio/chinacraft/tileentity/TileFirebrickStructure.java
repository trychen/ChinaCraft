package unstudio.chinacraft.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileFirebrickStructure extends TileEntity {

    private int x;
    private int y;
    private int z;

    @Override
    public void readFromNBT(NBTTagCompound p_145839_1_) {
        super.readFromNBT(p_145839_1_);
        x = p_145839_1_.getInteger("tilex");
        y = p_145839_1_.getInteger("tiley");
        z = p_145839_1_.getInteger("tilez");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound = super.writeToNBT(compound);
        compound.setInteger("tilex", x);
        compound.setInteger("tiley", y);
        compound.setInteger("tilez", z);
        return compound;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return false;
    }

    public TileEntity getTilePotteryKiln() {
        return worldObj.getTileEntity(new BlockPos(x, y, z));
    }

    public void setPosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
