package unstudio.chinacraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import unstudio.chinacraft.common.ChinaCraft;

import javax.annotation.Nullable;
import java.util.Random;

public class TileSericultureFrame extends TileEntity implements ISidedInventory {

    private static final int[] slotsTop = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
    private static final int[] slotsSide = new int[] { 9 };
    private static final int[] slotsBottom = new int[] { 10 };

    private ItemStack stack[] = new ItemStack[11];
    private double mortality = -1;

    public double getMortality() {
        return mortality;
    }

    public void setMortality(double mortality) {
        this.mortality = mortality;
    }

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

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    // TODO @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        if (this.stack[p_70304_1_] != null) {
            ItemStack itemstack = this.stack[p_70304_1_];
            this.stack[p_70304_1_] = null;
            return itemstack;
        } else {
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
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }
    
    public void update() {
        for (int i = 0; i < 9; i++) {
            ItemStack item = getStackInSlot(i);
            if (item == null)
                continue;
            if (item.getItem() != ChinaCraft.silkworm)
                continue;
            // TODO: NullPointerException, getStackInSlot(10) == null
            if(getStackInSlot(10)!=null&&getStackInSlot(10).stackSize==64)
                continue;
            if (item.hasTagCompound()) {
                NBTTagCompound nbt = item.getTagCompound();
                int x = nbt.getInteger("Schedule");
                x++;
                if (x >= getMaxSchedule(item.getItemDamage())) {
                    nbt.setInteger("Schedule", 0);
                    if (item.getItemDamage() >= 2) {
                        if (getStackInSlot(10) == null) {
                            setInventorySlotContents(10, new ItemStack(ChinaCraft.silkwormChrysalis));
                            setInventorySlotContents(i, new ItemStack(ChinaCraft.silkworm));
                        } else {
                            getStackInSlot(10).stackSize++;
                            setInventorySlotContents(i, new ItemStack(ChinaCraft.silkworm));
                        }
                    } else {
                        item.setItemDamage(item.getItemDamage() + 1);
                    }
                } else {
                    nbt.setInteger("Schedule", x);
                    if (item.getItemDamage() == 1) {
                        if (getStackInSlot(9) == null || getStackInSlot(9).getItem() != ChinaCraft.itemMulberryLeaf)
                            continue;
                        Random r = new Random();
                        int y = r.nextInt(4000);
                        if (y < 1) {
                            getStackInSlot(9).stackSize--;
                            if(getStackInSlot(9).stackSize==0){
                                setInventorySlotContents(9,null);
                            }
                        }
                    }
                }
                item.setTagCompound(nbt);
            } else {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setInteger("Schedule", 0);
                item.setTagCompound(nbt);
            }
        }
        // if(mortality == -1) {
        // mortality = getMortality();
        // }
        // if(getStackInSlot(0)!=null&&getStackInSlot(0).getItem().equals(ChinaCraft.silkworm)&&getStackInSlot(0).stackSize>0)
        // {
        // float m;
        // m = mortality +
        // (worldObj.isRaining()?worldObj.getTopSolidOrLiquidBlock(xCoord,
        // zCoord) == yCoord?0.2F:0:0);
        // if((getStackInSlot(1)==null||getStackInSlot(1).stackSize<=0||getStackInSlot(1).getItem()!=ChinaCraft.itemMulberryLeaf)&&getStackInSlot(0).getItemDamage()
        // == 1) {
        // m = 10.0F;
        // }
        // if(getStackInSlot(1)!=null&&worldObj.rand.nextInt(12000)<getStackInSlot(0).stackSize)
        // {
        // getStackInSlot(1).stackSize--;
        // if(getStackInSlot(1).stackSize<=0) {
        // setInventorySlotContents(1, null);
        // }
        // }
        // schedule++;
        // if(schedule >= getMaxSchedule(getStackInSlot(0).getItemDamage())) {
        // if(getStackInSlot(0).getItemDamage() == 2) {
        // setInventorySlotContents(2, new
        // ItemStack(ChinaCraft.silkwormChrysalis,getStackInSlot(0).stackSize));
        // getStackInSlot(0).setItemDamage(0);
        // getStackInSlot(0).stackSize=(getStackInSlot(0).stackSize*2)>=64?64:getStackInSlot(0).stackSize*2;
        // }else {
        // getStackInSlot(0).setItemDamage(getStackInSlot(0).getItemDamage()+1);
        // }
        // }
        // if(9.325*m>worldObj.rand.nextInt(24000)) {
        // if(getStackInSlot(0).getItemDamage() == 2){
        // if(getStackInSlot(2) == null){
        // setInventorySlotContents(2, new
        // ItemStack(ChinaCraft.silkwormChrysalis));
        // }else if(getStackInSlot(2).getItem() == ChinaCraft.itemMulberryLeaf){
        // getStackInSlot(2).stackSize++;
        // }
        // }
        // getStackInSlot(0).stackSize--;
        // if(getStackInSlot(0).stackSize<=0) {
        // setInventorySlotContents(0, null);
        // }
        // }
        // }else{
        // schedule = 0;
        // }
    }

    public int getMaxSchedule(int i) {
        switch (i) {
        case 0:
            return 16000;
        case 1:
            return 45000;
        case 2:
            return 9000;
        default:
            return -1;
        }
    }

    @Deprecated
    public double getDefaultMortality() {
        double temperature = worldObj.getBiomeForCoordsBody(pos).getTemperature() < 0 ? 0
                : worldObj.getBiomeForCoordsBody(pos).getTemperature() > 1.5F ? 1.5F
                        : worldObj.getBiomeForCoordsBody(pos).getTemperature();
        double rainfall = worldObj.getBiomeForCoordsBody(pos).getRainfall() < 0 ? 0
                : worldObj.getBiomeForCoordsBody(pos).getRainfall() > 1.5F ? 1.5F
                        : worldObj.getBiomeForCoordsBody(pos).getRainfall();
        int height = pos.getY() > 128 ? 128 : pos.getY();
        double m = 0;
        m += -0.422 * Math.pow(temperature, 4) + 1.109 * Math.pow(temperature, 3) - 0.301 * Math.pow(temperature, 2)
                - 0.620 * temperature + 0.3 < 0 ? 0
                        : -0.422 * Math.pow(temperature, 4) + 1.109 * Math.pow(temperature, 3)
                                - 0.301 * Math.pow(temperature, 2) - 0.620 * temperature + 0.3;
        m += -0.422 * Math.pow(rainfall, 4) + 1.109 * Math.pow(rainfall, 3) - 0.301 * Math.pow(rainfall, 2)
                - 0.620 * rainfall + 0.3 < 0 ? 0
                        : -0.422 * Math.pow(rainfall, 4) + 1.109 * Math.pow(rainfall, 3) - 0.301 * Math.pow(rainfall, 2)
                                - 0.620 * rainfall + 0.3;
        m += 0.000048828125F * (height - 64F) * (height - 64F);
        return m;
    }

    @Override
    public void readFromNBT(NBTTagCompound p_145839_1_) {
        super.readFromNBT(p_145839_1_);
        NBTTagList nbttaglist = p_145839_1_.getTagList("Items", 10);
        this.stack = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.stack.length) {
                this.stack[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        // this.schedule = p_145839_1_.getInteger("schedule");
        this.mortality = p_145839_1_.getDouble("mortality");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound = super.writeToNBT(compound);
        // compound.setInteger("schedule", this.schedule);
        compound.setDouble("mortality", this.mortality);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.stack.length; ++i) {
            if (this.stack[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.stack[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        compound.setTag("Items", nbttaglist);
        return compound;
    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return side == EnumFacing.DOWN ? slotsBottom : side == EnumFacing.UP ? slotsTop : slotsSide;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction) {
        return this.isItemValidForSlot(index, stack);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return direction != EnumFacing.DOWN || index != 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }
}
