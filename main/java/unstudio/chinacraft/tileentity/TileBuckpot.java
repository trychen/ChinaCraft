package unstudio.chinacraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

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
	public void output(EntityPlayer entityPlayer) {
		
	}
}
