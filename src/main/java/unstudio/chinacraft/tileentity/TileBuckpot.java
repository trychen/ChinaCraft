package unstudio.chinacraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileBuckpot extends TilePotteryBase implements ICooker {

    private ItemStack itemStacks[];
    private int cooktime;

    public TileBuckpot() {
        setPotteryType("Buckpot");
    }

    @Override
    public void readFromNBT(NBTTagCompound p_145839_1_) {
        super.readFromNBT(p_145839_1_);
    }

    @Override
    public void writeToNBT(NBTTagCompound p_145841_1_) {
        super.writeToNBT(p_145841_1_);
    }

    @Override
    public void updateEntity() {

    }

    @Override
    public boolean input(ItemStack item, EntityPlayer entityPlayer) {
        return false;
    }

    @Override
    public boolean output(ItemStack item, EntityPlayer entityPlayer) {
        return false;
    }

    @Override
    public boolean isCooking() {
        return false;
    }

    @Override
    public int remainingTime() {
        return 0;
    }
}
