package unstudio.chinacraft.block.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ICooker {
	public void input(ItemStack item,EntityPlayer entityPlayer);
	public void output(ItemStack item,EntityPlayer entityPlayer);
	public boolean cooking();
}
