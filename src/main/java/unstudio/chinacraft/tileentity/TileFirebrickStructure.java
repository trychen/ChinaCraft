package unstudio.chinacraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

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
    public void writeToNBT(NBTTagCompound p_145841_1_) {
        super.writeToNBT(p_145841_1_);
        p_145841_1_.setInteger("tilex", x);
        p_145841_1_.setInteger("tiley", y);
        p_145841_1_.setInteger("tilez", z);
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    public TileEntity getTilePotteryKiln() {
        return worldObj.getTileEntity(x, y, z);
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
