package unstudio.chinacraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.item.combat.Hammer;
import unstudio.chinacraft.recipes.JadeBenchRecipes;
import unstudio.chinacraft.recipes.JadeBenchRecipes.JadeBenchModifyRecipe;
import unstudio.chinacraft.recipes.JadeBenchRecipes.JadeBenchOreRecipe;

public class TileJadeBench extends TileEntity implements IInventory {

    private ItemStack[] stack = new ItemStack[3];

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
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }

    @Override
    public void updateEntity() {
        // Only update on the server side
        if (!worldObj.isRemote)
            if (getStackInSlot(0) != null && getStackInSlot(0).getItem() instanceof Hammer) {
                if (getStackInSlot(1) != null) {
                    if (getStackInSlot(2) == null) {
                        // Ore recipes
                        if (getStackInSlot(1).getItem().equals(Item.getItemFromBlock(ChinaCraft.jadeOre))) {
                            int thresholdWeight = worldObj.rand.nextInt(JadeBenchRecipes.getTotalWeightedChanceForOre());
                            for (JadeBenchOreRecipe oreRecipe: JadeBenchRecipes.getOreRecipes()) {
                                thresholdWeight -= oreRecipe.weightedChance;
                                if (thresholdWeight <= 0) {
                                    setInventorySlotContents(2, oreRecipe.outputJade);
                                    damageTool(0, 5);
                                    splitStack(1, 1);
                                    break;
                                }
                            } 
                        }       
                    } 
                    else {
                        // Modify recipes
                        JadeBenchModifyRecipe modifyRecipe = JadeBenchRecipes.getModifyRecipe(getStackInSlot(1), getStackInSlot(2));
                        if (modifyRecipe != null) {
                            boolean damageFlag = false;
                            ItemStack outputWeapon;
                            // Decide the outputStack
                            if (worldObj.rand.nextFloat() < modifyRecipe.epixModifyChance) {
                                outputWeapon = modifyRecipe.outputEpixWeapon.copy();
                                damageFlag = false;
                            }
                            else {
                                outputWeapon = modifyRecipe.outputWeapon.copy();
                                damageFlag = true;
                            }
                            // Transfer the enchantment
                            NBTTagList enchList = getStackInSlot(1).getEnchantmentTagList();
                            if (enchList != null && enchList.tagCount()>0) {
                                if (!outputWeapon.hasTagCompound())
                                    outputWeapon.setTagCompound(new NBTTagCompound());
                                if (outputWeapon.getTagCompound().getTag("ench") == null)
                                    outputWeapon.getTagCompound().setTag("ench", new NBTTagList());
                                for (int i = 0; i < enchList.tagCount(); i++)
                                    outputWeapon.getEnchantmentTagList().appendTag(enchList.getCompoundTagAt(i));
                            } 
                            // Update the inventory
                            setInventorySlotContents(2, outputWeapon);
                            if (damageFlag) damageTool(2, getStackInSlot(1).getItemDamage());
                            damageTool(0, 5);
                            splitStack(1, 1);
                        }
                    }
                }
            }
    }

    public void splitStack(int slotIndex, int amount) {
        int newStackSize = this.stack[slotIndex].stackSize - amount;
        if (newStackSize > 0) 
            this.stack[slotIndex].stackSize = newStackSize;
        else
            this.stack[slotIndex] = null;
    }
    
    public void damageTool(int slotIndex, int damage) {
        if (this.stack[slotIndex].isItemStackDamageable()) {
            int newDamage = this.stack[slotIndex].getItemDamage() + damage;
            if (newDamage > this.stack[slotIndex].getMaxDamage())
                this.stack[slotIndex] = null;
            else
                this.stack[slotIndex].setItemDamage(newDamage);
        }
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
