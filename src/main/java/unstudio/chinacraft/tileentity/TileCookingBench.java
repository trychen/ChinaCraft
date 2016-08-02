package unstudio.chinacraft.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IInteractionObject;
import net.minecraftforge.fml.common.registry.GameRegistry;
import unstudio.chinacraft.block.especial.BlockCookingBench;
import unstudio.chinacraft.inventory.ContainerCookingBench;

import javax.annotation.Nullable;

public class TileCookingBench extends TileEntity implements ISidedInventory, IInteractionObject {

    private static final int[] slotsTop = new int[] { 0 };
    private static final int[] slotsBottom = new int[] { 1 };
    public int furnaceBurnTime;
    public int currentItemBurnTime;
    private ItemStack stack[] = new ItemStack[2];

    public static int getItemBurnTime(ItemStack stack) {
        if (stack == null) {
            return 0;
        } else {
            Item item = stack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.WOODEN_SLAB) {
                    return 150;
                }

                if (block.getDefaultState().getMaterial() == Material.WOOD) {
                    return 300;
                }

                if (block == Blocks.COAL_BLOCK) {
                    return 16000;
                }
            }

            if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD"))
                return 200;
            if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD"))
                return 200;
            if (item instanceof ItemHoe && ((ItemHoe) item).getMaterialName().equals("WOOD"))
                return 200;
            if (item == Items.STICK)
                return 100;
            if (item == Items.COAL)
                return 1600;
            if (item == Items.LAVA_BUCKET)
                return 20000;
            if (item == Item.getItemFromBlock(Blocks.SAPLING))
                return 100;
            if (item == Items.BLAZE_ROD)
                return 2400;
            return GameRegistry.getFuelValue(stack);
        }
    }

    public static boolean isItemFuel(ItemStack stack) {
        return getItemBurnTime(stack) > 0;
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

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return p_94041_1_ == 2 ? false : (p_94041_1_ == 1 ? isItemFuel(p_94041_2_) : true);
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

    public boolean isBurning() {
        return this.furnaceBurnTime > 0;
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

        this.furnaceBurnTime = p_145839_1_.getShort("BurnTime");
        this.currentItemBurnTime = getItemBurnTime(this.stack[0]);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound = super.writeToNBT(compound);
        compound.setShort("BurnTime", (short) this.furnaceBurnTime);
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

    public void update() {
        boolean flag = this.furnaceBurnTime > 0;
        boolean flag1 = false;

        if (this.furnaceBurnTime > 0) {
            --this.furnaceBurnTime;
        }

        if (!this.worldObj.isRemote) {
            if (this.furnaceBurnTime != 0 || this.stack[0] != null) {
                if (this.furnaceBurnTime == 0) {
                    this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.stack[0]);

                    if (this.furnaceBurnTime > 0) {
                        flag1 = true;

                        if (this.stack[0] != null) {
                            --this.stack[0].stackSize;

                            if (this.stack[0].stackSize <= 0) {
                                this.stack[0] = stack[0].getItem().getContainerItem(stack[0]);
                            }
                        }
                    }
                }
                if (this.isBurning()) {
                    flag1 = true;
                }
            }

            if (flag != this.furnaceBurnTime > 0) {
                flag1 = true;
                BlockCookingBench.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.pos);
            }
        }

        if (flag1) {
            this.markDirty();
        }
    }

    public int getBurnTimeRemainingScaled(int i) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = 200;
        }

        return this.furnaceBurnTime * i / this.currentItemBurnTime;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return side == EnumFacing.DOWN ? slotsBottom : slotsTop;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction) {
        return this.isItemValidForSlot(index, stack);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return direction != EnumFacing.DOWN || index != 1 || stack.getItem() == Items.BUCKET;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerCookingBench(playerInventory, this);
    }

    @Override
    public String getGuiID() {
        return "chinacraft:cooking_bench";
    }
}
