package unstudio.chinacraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TilePotteryBase extends TileEntity {

    private String potteryType;

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setString("PotteryType", potteryType);
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
