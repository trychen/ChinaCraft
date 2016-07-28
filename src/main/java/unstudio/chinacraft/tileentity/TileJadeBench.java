package unstudio.sinocraft.tileentity;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import unstudio.sinocraft.common.SinoCraft;
import unstudio.sinocraft.item.combat.BronzeBroadSword;
import unstudio.sinocraft.item.combat.Hammer;
import unstudio.sinocraft.item.jade.Jade;

public class TileJadeBench extends TileEntity implements IInventory {

    private ItemStack stack[] = new ItemStack[3];

    @Override
    public int getSizeInventory() {
        return stack.length;
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return stack[p_70301_1_];
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
        if (p_70304_1_ >= 0 && p_70304_1_ < stack.length) {
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
    public String getInventoryName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub

    }

    @Override
    public void closeInventory() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void updateEntity() {
        if (getStackInSlot(0) != null) {
            if (getStackInSlot(1) != null) {
                if (getStackInSlot(2) == null) {
                    if (getStackInSlot(0).getItem() instanceof Hammer) {
                        if (getStackInSlot(1).getItem().equals(Item.getItemFromBlock(SinoCraft.jadeOre))) {
                            ItemStack newhammer = new ItemStack(getStackInSlot(0).getItem(), 1,
                                    (getStackInSlot(0).getItemDamage() - 5));
                            setInventorySlotContents(0, newhammer);
                            if (getStackInSlot(1).stackSize == 1) {
                                setInventorySlotContents(1, null);
                            } else {
                                ItemStack newJadeOre = new ItemStack(getStackInSlot(1).getItem(),
                                        getStackInSlot(1).stackSize - 1);
                                setInventorySlotContents(1, newJadeOre);
                            }
                            switch (new Random().nextInt(3)) {
                            case 0:
                                setInventorySlotContents(2, new ItemStack(SinoCraft.jadeGreenItem));
                                break;
                            case 1:
                                setInventorySlotContents(2, new ItemStack(SinoCraft.jadeGreen2Item));
                                break;
                            case 2:
                                setInventorySlotContents(2, new ItemStack(SinoCraft.jadePurpleItem));
                                break;
                            case 3:
                                setInventorySlotContents(2, new ItemStack(SinoCraft.jadePinkItem));
                                break;
                            default:
                                setInventorySlotContents(2, new ItemStack(SinoCraft.jadeGreenItem));
                                break;
                            }
                        }
                    }
                } else {
                    if (getStackInSlot(2).getItem() instanceof Jade && getStackInSlot(0).getItem() instanceof Hammer
                            && getStackInSlot(1).getItem() instanceof ItemSword) {
                        if (getStackInSlot(1).getItem() instanceof BronzeBroadSword) {
                            ItemStack newhammer = new ItemStack(getStackInSlot(0).getItem(), 1,
                                    (getStackInSlot(0).getItemDamage() - 5));
                            ItemStack sword = getStackInSlot(1).copy();
                            Item jade = getStackInSlot(2).getItem();
                            setInventorySlotContents(0, newhammer);
                            if (getStackInSlot(2).stackSize == 1) {
                                setInventorySlotContents(1, null);
                            } else {
                                setInventorySlotContents(1,
                                        new ItemStack(getStackInSlot(2).getItem(), getStackInSlot(2).stackSize - 1));
                            }
                            if (new Random().nextInt(19) == 3) {
                                ItemStack newSword = new ItemStack(SinoCraft.blGiantSword, 1);
                                setInventorySlotContents(2, newSword);
                            } else {
                                if (jade.equals(SinoCraft.jadeGreenItem)) {
                                    setInventorySlotContents(2,
                                            new ItemStack(SinoCraft.bronzeBroadSwordGreen, 1, sword.getItemDamage()));
                                } else if (jade.equals(SinoCraft.jadeGreen2Item)) {
                                    setInventorySlotContents(2,
                                            new ItemStack(SinoCraft.bronzeBroadSwordGreen2, 1, sword.getItemDamage()));
                                } else if (jade.equals(SinoCraft.jadePinkItem)) {
                                    setInventorySlotContents(2,
                                            new ItemStack(SinoCraft.bronzeBroadSwordPink, 1, sword.getItemDamage()));
                                } else if (jade.equals(SinoCraft.jadeGreen2Item)) {
                                    setInventorySlotContents(2,
                                            new ItemStack(SinoCraft.bronzeBroadSwordPurple, 1, sword.getItemDamage()));
                                }
                            }
                        }
                    }
                }
            }
        }

        // if (getStackInSlot(2) != null){
        // Item item2 = getStackInSlot(2).getItem();
        // if (item2 == SinoCraft.jadeGreenItem||item2 ==
        // SinoCraft.jadeGreen2Item||item2 == SinoCraft.jadePinkItem||item2 ==
        // SinoCraft.jadePurpleItem){
        //
        // }
        // }

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
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
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
}
