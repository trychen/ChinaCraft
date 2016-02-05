package unstudio.chinacraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ICooker {
	public boolean input(ItemStack item,EntityPlayer entityPlayer);
	public boolean output(ItemStack item,EntityPlayer entityPlayer);
	public boolean isCooking();
	public int remainingTime();
}
