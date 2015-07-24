package unstudio.chinacraft.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import unstudio.chinacraft.block.model.ModelBuhrimill;
import unstudio.chinacraft.recipes.BuhrimillRecipe;

public class TileBuhrimill extends TileEntity implements IUpdatePlayerListBox,
		IInventory {

	private ItemStack stack[] = new ItemStack[4];
	public int angle;
	public int schedule;

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
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		// TODO 自动生成的方法存根
		return null;
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
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO 自动生成的方法存根
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
	public void openInventory() {
		// TODO 自动生成的方法存根

	}

	@Override
	public void closeInventory() {
		// TODO 自动生成的方法存根

	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public void update() {
		
	}
	
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items", 10);
        this.stack = new ItemStack[this.getSizeInventory()];
        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.getCompoundTagAt(var3);
            byte var5 = var4.getByte("Slot");
            if (var5 >= 0 && var5 < this.stack.length)
            {
                this.stack[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
        this.angle = par1NBTTagCompound.getShort("angle");
        this.schedule = par1NBTTagCompound.getShort("schedule");
    }
 
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("angle", (short)this.angle);
        par1NBTTagCompound.setShort("schedule", (short)this.schedule);
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.stack.length; ++var3)
        {
            if (this.stack[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.stack[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }
        par1NBTTagCompound.setTag("Items", var2);
    }
    
	@Override
	public void updateEntity() {
		super.updateEntity();
		if(getStackInSlot(0) != null) {
			if(getStackInSlot(1) != null) {
				BuhrimillRecipe r = BuhrimillRecipe.getBuhrimillReciper(getStackInSlot(0), getStackInSlot(1));
				if(r !=null) {
					if((getStackInSlot(0).stackSize-r.getInput1().stackSize)>=0&&(getStackInSlot(1).stackSize-r.getInput2().stackSize)>=0&&(getStackInSlot(2) == null||getStackInSlot(2).equals(r.getOutput1()))&&(getStackInSlot(3)==null||getStackInSlot(3).equals(r.getOutput2()))) {
						if(schedule >= r.getTime()){
							schedule = 0;
							if(getStackInSlot(2) !=null) {
								ItemStack output1 = r.getOutput1();
								output1.stackSize = getStackInSlot(2).stackSize+output1.stackSize;
								setInventorySlotContents(2,output1);
							}else {
								setInventorySlotContents(2, r.getOutput1());
							}
							if(r.getOutput2() != null) {
							if(getStackInSlot(3) !=null) {
								ItemStack output2 = r.getOutput2();
								output2.stackSize = getStackInSlot(3).stackSize+output2.stackSize;
								setInventorySlotContents(3,output2);
							}else {
								setInventorySlotContents(3, r.getOutput2());
							}
							}
							if((getStackInSlot(0).stackSize-r.getInput1().stackSize)==0){
								setInventorySlotContents(0, null);
							}else {
								ItemStack input1 = getStackInSlot(0);
								input1.stackSize = input1.stackSize-r.getInput1().stackSize;
								setInventorySlotContents(0, input1);
							}
							if((getStackInSlot(1).stackSize-r.getInput2().stackSize)==0){
								setInventorySlotContents(1, null);
							}else {
								ItemStack input2 = getStackInSlot(1);
								input2.stackSize = input2.stackSize-r.getInput2().stackSize;
								setInventorySlotContents(1, input2);
							}
							return;
						}
					}
				}else {
					return;
				}
			}else{
				BuhrimillRecipe r = BuhrimillRecipe.getBuhrimillReciper(getStackInSlot(0));
				if(r !=null) {
					if((getStackInSlot(0).stackSize-r.getInput1().stackSize)>=0&&(getStackInSlot(2) == null||getStackInSlot(2).equals(r.getOutput1()))&&(getStackInSlot(3)==null||getStackInSlot(3).equals(r.getOutput2()))) {
						if(schedule >= r.getTime()){
							schedule = 0;
							if(getStackInSlot(2) !=null) {
								ItemStack output1 = r.getOutput1();
								output1.stackSize = getStackInSlot(2).stackSize+output1.stackSize;
								setInventorySlotContents(2,output1);
							}else {
								setInventorySlotContents(2, r.getOutput1());
							}
							if(r.getOutput2() != null) {
							if(getStackInSlot(3) !=null) {
								ItemStack output2 = r.getOutput2();
								output2.stackSize = getStackInSlot(3).stackSize+output2.stackSize;
								setInventorySlotContents(3,output2);
							}else {
								setInventorySlotContents(3, r.getOutput2());
							}
							}
							if((getStackInSlot(0).stackSize-r.getInput1().stackSize)==0){
								setInventorySlotContents(0, null);
							}else {
								ItemStack input1 = getStackInSlot(0);
								input1.stackSize = input1.stackSize-r.getInput1().stackSize;
								setInventorySlotContents(0, input1);
							}
							return;
						}
					}
				}else {
					return;
				}
			}
		}else {
			schedule = 0;
		}
	}
	
	public int getMaxSchedule() {
		if(getStackInSlot(0) != null) {
			if(getStackInSlot(1) != null) {
				BuhrimillRecipe r = BuhrimillRecipe.getBuhrimillReciper(getStackInSlot(0), getStackInSlot(1));
				if(r == null) {
					return 0;
				}
				return r.getTime();
			}else{
				BuhrimillRecipe r = BuhrimillRecipe.getBuhrimillReciper(getStackInSlot(0));
				if(r == null) {
					return 0;
				}
				return r.getTime();
			}
		}
		return 0;
	}
	
	public void addAngle(int i) {
		if(getMaxSchedule() > 0) {
			schedule = schedule+i;
		}
		if(angle+i >= 360) {
			angle = i+angle-360;
		}else {
			angle = angle+i;
		}
	}
}
