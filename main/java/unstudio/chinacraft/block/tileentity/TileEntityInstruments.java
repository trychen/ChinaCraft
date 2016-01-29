package unstudio.chinacraft.block.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by trych on 2016/1/27.
 */
public class TileEntityInstruments extends TileEntity{
    public int musicCount = 0;
    private int maxCount;

    public TileEntityInstruments(int maxCount) {
        this.maxCount = maxCount;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("musicCount",this.musicCount);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.musicCount = nbt.getInteger("musicCount");
        if (this.musicCount < 0)
        {
            this.musicCount = 0;
        }

        if (this.musicCount > maxCount)
        {
            this.musicCount = maxCount;
        }
    }

    public void changeMusicCount()
    {
        this.musicCount++;
    }
}
