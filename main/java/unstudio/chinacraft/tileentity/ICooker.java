package unstudio.chinacraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ICooker {

	public void input(ItemStack item,EntityPlayer entityPlayer);
	public void output(EntityPlayer entityPlayer);
}
