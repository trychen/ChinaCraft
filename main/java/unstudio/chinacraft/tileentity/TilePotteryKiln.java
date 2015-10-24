package unstudio.chinacraft.tileentity;

import unstudio.chinacraft.block.BlockFirebrickStructure;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TilePotteryKiln extends TileEntity implements ISidedInventory {

	private ItemStack stack[] = new ItemStack[2];
    public int furnaceBurnTime;  
    public int currentItemBurnTime;
    
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
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
    {
        return p_94041_1_ == 2 ? false : (p_94041_1_ == 1 ? isItemFuel(p_94041_2_) : true);
    }
	
	   public static int getItemBurnTime(ItemStack p_145952_0_)
	    {
	        if (p_145952_0_ == null)
	        {
	            return 0;
	        }
	        else
	        {
	            Item item = p_145952_0_.getItem();

	            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
	            {
	                Block block = Block.getBlockFromItem(item);

	                if (block == Blocks.wooden_slab)
	                {
	                    return 150;
	                }

	                if (block.getMaterial() == Material.wood)
	                {
	                    return 300;
	                }

	                if (block == Blocks.coal_block)
	                {
	                    return 16000;
	                }
	            }

	            if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return 200;
	            if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200;
	            if (item instanceof ItemHoe && ((ItemHoe)item).getToolMaterialName().equals("WOOD")) return 200;
	            if (item == Items.stick) return 100;
	            if (item == Items.coal) return 1600;
	            if (item == Items.lava_bucket) return 20000;
	            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
	            if (item == Items.blaze_rod) return 2400;
	            return GameRegistry.getFuelValue(p_145952_0_);
	        }
	    }

	    public static boolean isItemFuel(ItemStack p_145954_0_)
	    {
	        return getItemBurnTime(p_145954_0_) > 0;
	    }
	    
	    public boolean isBurning()
	    {
	        return this.furnaceBurnTime > 0;
	    }
	    
	    @Override
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

	        this.furnaceBurnTime = p_145839_1_.getShort("BurnTime");
	        this.currentItemBurnTime = getItemBurnTime(this.stack[0]);
	    }

	    @Override
		public void writeToNBT(NBTTagCompound p_145841_1_)
	    {
	        super.writeToNBT(p_145841_1_);
	        p_145841_1_.setShort("BurnTime", (short)this.furnaceBurnTime);
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
		public void updateEntity()
	    {
	        boolean flag = this.furnaceBurnTime > 0;
	        boolean flag1 = false;

	        if (this.furnaceBurnTime > 0)
	        {
	            --this.furnaceBurnTime;
	        }

	        if (!this.worldObj.isRemote)
	        {
	            if (this.furnaceBurnTime != 0 || this.stack[0] != null)
	            {
	                if (this.furnaceBurnTime == 0)
	                {
	                    this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.stack[0]);

	                    if (this.furnaceBurnTime > 0)
	                    {
	                        flag1 = true;

	                        if (this.stack[0] != null)
	                        {
	                            --this.stack[0].stackSize;

	                            if (this.stack[0].stackSize <= 0)
	                            {
	                                this.stack[0] = stack[0].getItem().getContainerItem(stack[0]);
	                            }
	                        }
	                    }
	                }
	                if (this.isBurning())
	                {
	                        flag1 = true;
	                }
	            }

	            if (flag != this.furnaceBurnTime > 0)
	            {
	                flag1 = true;
	            }
	        }

	        if (flag1)
	        {
	            this.markDirty();
	        	if(this.blockMetadata == 0) {
	        		 BlockFirebrickStructure.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord-1);
	        	}else if(blockMetadata == 1) {
	        		 BlockFirebrickStructure.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord+1, this.yCoord, this.zCoord);
	        	}else if(blockMetadata == 2) {
	        		 BlockFirebrickStructure.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord+1);
	        	}else if(blockMetadata == 3) {
	        		 BlockFirebrickStructure.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord-1, this.yCoord, this.zCoord);
	        	}
	        }
	    }

	    @Override
		public int[] getAccessibleSlotsFromSide(int p_94128_1_)
	    {
	        return null;
	    }

	    @Override
		public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_)
	    {
	        return false;
	    }

	    @Override
		public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_)
	    {
	        return false;
	    }

		public int getBurnTimeRemainingScaled(int i) {
	        if (this.currentItemBurnTime == 0)
	        {
	            this.currentItemBurnTime = 200;
	        }

	        return this.furnaceBurnTime * i/ this.currentItemBurnTime;
		}

}
