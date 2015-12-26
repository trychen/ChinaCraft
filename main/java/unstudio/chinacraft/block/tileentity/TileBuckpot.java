package unstudio.chinacraft.block.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileBuckpot extends TilePotteryBase implements ICooker{
	
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
	public void input(ItemStack item, EntityPlayer entityPlayer) {
		
	}

	@Override
	public void output(ItemStack item, EntityPlayer entityPlayer) {
		
	}

	@Override
	public boolean isCooking() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public int remainingTime() {
		// TODO 自动生成的方法存根
		return 0;
	}
}
