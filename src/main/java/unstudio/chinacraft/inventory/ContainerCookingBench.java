package unstudio.chinacraft.inventory;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

import unstudio.chinacraft.tileentity.TileCookingBench;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerCookingBench extends Container {

    private TileCookingBench tile;
    private int lastFurnaceBurnTimet;
    private int lastCurrentItemBurnTime;

    public ContainerCookingBench(InventoryPlayer par1InventoryPlayer, TileCookingBench tileEntity) {
        this.tile = tileEntity;
        this.addSlotToContainer(new Slot(tileEntity, 0, 80, 31));
        this.addSlotToContainer(new SlotFurnace(par1InventoryPlayer.player, tileEntity, 1, 80, 53));
        int var3;
        for (var3 = 0; var3 < 3; ++var3) {
            for (int var4 = 0; var4 < 9; ++var4) {
                this.addSlotToContainer(
                        new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3) {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot) this.inventorySlots.get(par2);
        if (var4 != null && var4.getHasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();
            // 点击到Slot的ID为0-1之间的时候，将物品送回玩家的背包中
            if (par2 >= 0 && par2 <= 1) {
                if (!this.mergeItemStack(var5, 2, 29, false)) {
                    return null;
                }
                var4.onSlotChange(var5, var3);
            }
            // 点击到玩家的背包的时候将物品送到玩家的快捷栏中
            else if (par2 > 2 && par2 < 29) {
                if (!this.mergeItemStack(var5, 29, 38, false)) {
                    return null;
                }
            }
            // 点击到玩家的快捷栏的时候将物品送到背包中
            else if (par2 >= 29 && par2 < 38) {
                if (!this.mergeItemStack(var5, 2, 29, false)) {
                    return null;
                }
            }
            if (var5.stackSize == 0) {
                var4.putStack((ItemStack) null);
            } else {
                var4.onSlotChanged();
            }
            if (var5.stackSize == var3.stackSize) {
                return null;
            }
            var4.onPickupFromSlot(par1EntityPlayer, var5);
        }
        return var3;
    }

    @Override
    public void addCraftingToCrafters(ICrafting par1iCrafting) {
        // TODO Auto-generated method stub
        super.addCraftingToCrafters(par1iCrafting);
        par1iCrafting.sendProgressBarUpdate(this, 0, this.tile.furnaceBurnTime);
        par1iCrafting.sendProgressBarUpdate(this, 1, this.tile.currentItemBurnTime);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.tile.furnaceBurnTime = par2;
        }
        if (par1 == 1) {
            this.tile.currentItemBurnTime = par2;
        }
    }

    @Override
    public void detectAndSendChanges() {
        // TODO Auto-generated method stub
        super.detectAndSendChanges();
        Iterator var1 = this.crafters.iterator();
        while (var1.hasNext()) {
            ICrafting var2 = (ICrafting) var1.next();

            if (this.lastFurnaceBurnTimet != this.tile.furnaceBurnTime) {
                var2.sendProgressBarUpdate(this, 0, this.tile.furnaceBurnTime);
            }

            if (this.lastCurrentItemBurnTime != this.tile.currentItemBurnTime) {
                var2.sendProgressBarUpdate(this, 1, this.tile.currentItemBurnTime);
            }
        }
        this.lastFurnaceBurnTimet = this.tile.furnaceBurnTime;
        this.lastCurrentItemBurnTime = this.tile.currentItemBurnTime;
    }
}
