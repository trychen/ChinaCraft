package unstudio.sinocraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TilePotteryBase extends TileEntity {

    private String potteryType;

    @Override
    public boolean canUpdate() {
        return false;
    }

    @Override
    public void writeToNBT(NBTTagCompound p_145841_1_) {
        super.writeToNBT(p_145841_1_);
        p_145841_1_.setString("PotteryType", potteryType);
    }

    @Override
    public void readFromNBT(NBTTagCompound p_145839_1_) {
        super.readFromNBT(p_145839_1_);
        potteryType = p_145839_1_.getString("PotteryType");
    }

    public String getPotteryType() {
        return potteryType;
    }

    public void setPotteryType(String potteryType) {
        this.potteryType = potteryType;
    }
}
