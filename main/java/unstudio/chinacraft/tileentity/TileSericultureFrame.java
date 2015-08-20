package unstudio.chinacraft.tileentity;

import unstudio.chinacraft.ChinaCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileSericultureFrame extends TileEntity implements ISidedInventory{
	
    private static final int[] slotsTop = new int[] {1};
    private static final int[] slotsBottom = new int[] {2};

	private ItemStack stack[] = new ItemStack[3];
	public float mortality = -1;
	public int schedule = 0;
    
	@Override
	public int getSizeInventory() {
		return stack.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return stack[index];
	}

	@Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.stack[index] != null)
        {
                    ItemStack itemstack;

                    if (this.stack[index].stackSize <= count)
                    {
                        itemstack = this.stack[index];
                        this.stack[index] = null;
                        return itemstack;
                    }
                    else
                    {
                        itemstack = this.stack[index].splitStack(count);

                        if (this.stack[index].stackSize == 0)
                        {
                                    this.stack[index] = null;
                        }

                        return itemstack;
                    }
        }
        else
               {
                    return null;
        }
	}

	@Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_)
    {
        if (this.stack[p_70304_1_] != null)
        {
            ItemStack itemstack = this.stack[p_70304_1_];
            this.stack[p_70304_1_] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.stack[index] = stack;
		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}
	
	@Override
	public void updateEntity() {
		if(getStackInSlot(0)!=null&&getStackInSlot(0).getItem().equals(ChinaCraft.silkworm)&&getStackInSlot(0).stackSize>0) {
			float mortality;
			if(this.mortality < 0) {
				this.mortality = getMortality();
			}
			mortality = this.mortality + (worldObj.isRaining()?worldObj.getTopSolidOrLiquidBlock(xCoord, zCoord) == yCoord?0.2F:0:0);
			if((getStackInSlot(1)==null||getStackInSlot(1).stackSize<=0)&&(getStackInSlot(0).getItemDamage() == 0||getStackInSlot(0).getItemDamage() == 2)) {
				mortality = 0.9F;			
			}else {
				if(getStackInSlot(1)!=null&&worldObj.rand.nextInt(12000)<getStackInSlot(0).stackSize) {
					getStackInSlot(1).stackSize--;
					if(getStackInSlot(1).stackSize<=0) {
						setInventorySlotContents(1, null);
					}
				}
			}
			schedule++;
			if(schedule >= getMaxSchedule(getStackInSlot(0).getItemDamage())) {
				if(getStackInSlot(0).getItemDamage() == 2) {
					setInventorySlotContents(2, new ItemStack(ChinaCraft.silkwormChrysalis,getStackInSlot(0).stackSize));
					getStackInSlot(0).setItemDamage(0);
				}else {
				getStackInSlot(0).setItemDamage(getStackInSlot(0).getItemDamage()+1);
				}
			}
			if(200*mortality>worldObj.rand.nextInt(24000)&&getStackInSlot(0).getItemDamage() != 2) {
				getStackInSlot(0).stackSize--;
				if(getStackInSlot(0).stackSize<=0) {
					setInventorySlotContents(0, null);
				}
			}
		}
	}
	
	public int getMaxSchedule(int i) {
		switch (i) {
		case 0:
			return 24000;
		case 1:
			return 60000;
		case 2:
			return 12000;
		default:
			return 60000;
		}
	}
	
	public float getMortality() {
		float temperature = worldObj.getBiomeGenForCoords(xCoord, zCoord).temperature < 0?0:worldObj.getBiomeGenForCoords(xCoord, zCoord).temperature>1.5F?1.5F:worldObj.getBiomeGenForCoords(xCoord, zCoord).temperature;
		float rainfall = worldObj.getBiomeGenForCoords(xCoord, zCoord).rainfall < 0?0:worldObj.getBiomeGenForCoords(xCoord, zCoord).rainfall >1.5F?1.5F:worldObj.getBiomeGenForCoords(xCoord, zCoord).rainfall;
		int height = yCoord >128?128:yCoord;
		float mortality = 0;
		mortality += 16/30*temperature*temperature-0.8*temperature+0.3;
		mortality += 16/30*rainfall*rainfall-0.8*rainfall+0.3;
		mortality += 0.0000732421875*(rainfall-64)*(rainfall-64);
		return mortality;
	}
	
    public void readFromNBT(NBTTagCompound p_145839_1_)
    {
        super.readFromNBT(p_145839_1_);
        NBTTagList nbttaglist = p_145839_1_.getTagList("Items", 10);
        this.stack = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.stack.length)
            {
                this.stack[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        this.schedule = p_145839_1_.getInteger("schedule");
    }

    public void writeToNBT(NBTTagCompound p_145841_1_)
    {
        super.writeToNBT(p_145841_1_);
        p_145841_1_.setInteger("schedule", this.schedule);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.stack.length; ++i)
        {
            if (this.stack[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.stack[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        p_145841_1_.setTag("Items", nbttaglist);
    }

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return true;
	}

    public int[] getAccessibleSlotsFromSide(int p_94128_1_)
    {
        return p_94128_1_ == 0 ? slotsBottom : slotsTop;
    }

    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_)
    {
        return this.isItemValidForSlot(p_102007_1_, p_102007_2_);
    }

    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_)
    {
        return p_102008_3_ != 0 || p_102008_1_ != 0;
    }

}
