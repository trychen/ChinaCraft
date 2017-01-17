package unstudio.chinacraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import unstudio.chinacraft.recipes.BuhrimillRecipe;

public class TileBuhrimill extends TileEntity implements ISidedInventory {

    public int angle;
    public int schedule;
    private ItemStack stack[] = new ItemStack[4];

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
        if (this.stack[index] != null) {
            ItemStack itemstack;

            if (this.stack[index].stackSize <= count) {
                itemstack = this.stack[index];
                this.stack[index] = null;
                return itemstack;
            } else {
                itemstack = this.stack[index].splitStack(count);

                if (this.stack[index].stackSize == 0) {
                    this.stack[index] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
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
        return "Buhrimill";
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
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items", 10);
        this.stack = new ItemStack[this.getSizeInventory()];
        for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
            NBTTagCompound var4 = var2.getCompoundTagAt(var3);
            byte var5 = var4.getByte("Slot");
            if (var5 >= 0 && var5 < this.stack.length) {
                this.stack[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
        this.angle = par1NBTTagCompound.getShort("angle");
        this.schedule = par1NBTTagCompound.getShort("schedule");
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("angle", (short) this.angle);
        par1NBTTagCompound.setShort("schedule", (short) this.schedule);
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.stack.length; ++var3) {
            if (this.stack[var3] != null) {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte) var3);
                this.stack[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }
        par1NBTTagCompound.setTag("Items", var2);
    }

    @Override
    public void updateEntity() {
        // Only update on the server side
        if (worldObj.isRemote)
            return;
        if (getStackInSlot(0) != null) {
            // 双输入
            if (getStackInSlot(1) != null) {
                BuhrimillRecipe r = BuhrimillRecipe.getBuhrimillReciper(getStackInSlot(0), getStackInSlot(1));
                if (r != null) {
                    // 检查当前输入槽物品的数量，和输出槽的物品类型是否符合该配方
                    if ((getStackInSlot(0).stackSize - r.getInput1().stackSize) >= 0
                            && (getStackInSlot(1).stackSize - r.getInput2().stackSize) >= 0
                            && (getStackInSlot(2) == null || getStackInSlot(2).isItemEqual(r.getOutput1()))
                            && (getStackInSlot(3) == null || r.getOutput2() == null
                            || getStackInSlot(3).isItemEqual(r.getOutput2()))) {
                        // 在更新输入输出槽前先检查输出槽物品数量是否将要超限
                        if ((getStackInSlot(2) != null && getStackInSlot(2).stackSize + r.getOutput1().stackSize > r.getOutput1().getMaxStackSize())
                                || (getStackInSlot(3) != null && r.getOutput2() != null && getStackInSlot(3).stackSize + r.getOutput2().stackSize > r.getOutput2().stackSize))
                            return;
                        // 更新输入输出槽
                        if (schedule >= r.getTime()) {
                            schedule = 0;
                            // 输出槽1
                            if (getStackInSlot(2) != null) {
                                ItemStack output1 = getStackInSlot(2);
                                output1.stackSize += r.getOutput1().stackSize;
                            } else {
                                setInventorySlotContents(2, r.getOutput1().copy());
                            }
                            // 输出槽2
                            if (r.getOutput2() != null) {
                                if (getStackInSlot(3) != null) {
                                    ItemStack output2 = getStackInSlot(3);
                                    output2.stackSize += r.getOutput2().stackSize;
                                } else {
                                    setInventorySlotContents(3, r.getOutput2().copy());
                                }
                            }
                            // 输人槽1
                            if ((getStackInSlot(0).stackSize - r.getInput1().stackSize) == 0) {
                                setInventorySlotContents(0, null);
                            } else {
                                ItemStack input1 = getStackInSlot(0);
                                input1.stackSize -= r.getInput1().stackSize;
                            }
                            // 输入槽2
                            if ((getStackInSlot(1).stackSize - r.getInput2().stackSize) == 0) {
                                setInventorySlotContents(1, null);
                            } else {
                                ItemStack input2 = getStackInSlot(1);
                                input2.stackSize -= r.getInput2().stackSize;
                            }
                        } else
                            return;
                    } else
                        return;
                } else
                    return;
            } else {
                // 单输入
                BuhrimillRecipe r = BuhrimillRecipe.getBuhrimillReciper(getStackInSlot(0));
                if (r != null) {
                    // 检查当前输入槽物品的数量，和输出槽的物品类型是否符合该配方
                    if ((getStackInSlot(0).stackSize - r.getInput1().stackSize) >= 0
                            && (getStackInSlot(2) == null || getStackInSlot(2).isItemEqual(r.getOutput1()))
                            && (getStackInSlot(3) == null || r.getOutput2() == null
                            || getStackInSlot(3).isItemEqual(r.getOutput2()))) {
                        // 在更新输入输出槽前先检查输出槽物品数量是否将要超限
                        if ((getStackInSlot(2) != null && getStackInSlot(2).stackSize + r.getOutput1().stackSize > r.getOutput1().getMaxStackSize())
                                || (getStackInSlot(3) != null && r.getOutput2() != null && getStackInSlot(3).stackSize + r.getOutput2().stackSize > r.getOutput2().stackSize))
                            return;
                        // 更新输入输出槽
                        if (schedule >= r.getTime()) {
                            schedule = 0;
                            // 输出槽1
                            if (getStackInSlot(2) != null) {
                                ItemStack output1 = getStackInSlot(2);
                                output1.stackSize += r.getOutput1().stackSize;
                            } else {
                                setInventorySlotContents(2, r.getOutput1().copy());
                            }
                            // 输出槽2
                            if (r.getOutput2() != null) {
                                if (getStackInSlot(3) != null) {
                                    ItemStack output2 = getStackInSlot(3);
                                    output2.stackSize += r.getOutput2().stackSize;
                                    ;
                                } else {
                                    setInventorySlotContents(3, r.getOutput2().copy());
                                }
                            }
                            // 输入槽1
                            if ((getStackInSlot(0).stackSize - r.getInput1().stackSize) == 0) {
                                setInventorySlotContents(0, null);
                            } else {
                                ItemStack input1 = getStackInSlot(0);
                                input1.stackSize -= r.getInput1().stackSize;
                            }
                        } else
                            return;
                    } else
                        return;
                } else
                    return;
            }
        } else {
            // 无输入
            if (schedule != 0)
                schedule = 0;
            else
                return;
        }
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        markDirty();
    }

    public int getMaxSchedule() {
        if (getStackInSlot(0) != null) {
            if (getStackInSlot(1) != null) {
                BuhrimillRecipe r = BuhrimillRecipe.getBuhrimillReciper(getStackInSlot(0), getStackInSlot(1));
                if (r == null) {
                    return 0;
                }
                return r.getTime();
            } else {
                BuhrimillRecipe r = BuhrimillRecipe.getBuhrimillReciper(getStackInSlot(0));
                if (r == null) {
                    return 0;
                }
                return r.getTime();
            }
        }
        return -1;
    }

    public void addAngle(int i) {
        if (getMaxSchedule() > 0) {
            schedule = schedule + i;
        }
        if (angle + i >= 360) {
            angle = i + angle - 360;
        } else {
            angle = angle + i;
        }
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        markDirty();
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbttagcompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.func_148857_g());
    }

    private int[] slots_out = {2, 3};
    private int[] slots_in = {0, 1};
    private int[] slots_null = {};

    @Override
    public int[] getAccessibleSlotsFromSide(int par1) {
//        return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
        System.out.println(par1);
        return par1 == 0 ? slots_out : (par1 == 1 ? slots_null : slots_in);
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack item, int side) {
        return BuhrimillRecipe.isInput(item);
    }

    @Override
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return p_102008_3_ != 0 || p_102008_1_ != 1;
    }
}
