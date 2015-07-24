package unstudio.chinacraft.inventory;

import java.util.Iterator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import unstudio.chinacraft.tileentity.TileBuhrimill;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerBuhrimill extends Container{

	private TileBuhrimill tile;
	private int lastAngle;
	private int lastSchedule;
	
	public ContainerBuhrimill(InventoryPlayer par1InventoryPlayer, TileBuhrimill tileEntity) {
		this.tile = tileEntity;
		this.addSlotToContainer(new Slot(tileEntity, 0, 43, 25));
		this.addSlotToContainer(new Slot(tileEntity, 1, 43, 50));
		this.addSlotToContainer(new SlotFurnace(par1InventoryPlayer.player, tileEntity, 2, 112, 25));
		this.addSlotToContainer(new SlotFurnace(par1InventoryPlayer.player, tileEntity, 3, 112, 50));
        int var3;
        for (var3 = 0; var3 < 3; ++var3)
        {
                    for (int var4 = 0; var4 < 9; ++var4)
                    {
                        this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
                    }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
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
			// 点击到Slot的ID为0-3之间的时候，将物品送回玩家的背包中
			if (par2 >= 0 && par2 <= 3) {
				if (!this.mergeItemStack(var5, 4, 30, false)) {
					return null;
				}
				var4.onSlotChange(var5, var3);
			}
			// 点击到玩家的背包的时候将物品送到玩家的快捷栏中
			else if (par2 > 4 && par2 < 30) {
				if (!this.mergeItemStack(var5, 30, 39, false)) {
					return null;
				}
			}
			// 点击到玩家的快捷栏的时候将物品送到背包中
			else if (par2 >= 30 && par2 < 39) {
				if (!this.mergeItemStack(var5, 4, 30, false)) {
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
		par1iCrafting.sendProgressBarUpdate(this, 0, this.tile.angle);
		par1iCrafting.sendProgressBarUpdate(this, 1, this.tile.schedule);
	}
 
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		if (par1 == 0) {
			this.tile.angle = par2;
		}
		if (par1 == 1) {
			this.tile.schedule= par2;
		}
	}
 
	@Override
	public void detectAndSendChanges() {
		// TODO Auto-generated method stub
		super.detectAndSendChanges();
		Iterator var1 = this.crafters.iterator();
		while (var1.hasNext()) {
			ICrafting var2 = (ICrafting) var1.next();
 
			if (this.lastAngle != this.tile.angle) {
				var2.sendProgressBarUpdate(this, 0, this.tile.angle);
			}
 
			if (this.lastSchedule != this.tile.schedule) {
				var2.sendProgressBarUpdate(this, 1, this.tile.schedule);
			}
		}
		this.lastAngle = this.tile.angle;
		this.lastSchedule = this.tile.schedule;
	}

}
