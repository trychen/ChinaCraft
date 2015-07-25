package unstudio.chinacraft.item;

import net.minecraft.item.ItemFood;
import unstudio.chinacraft.ChinaCraft;

public class Ladyfinger extends ItemFood{
	public Ladyfinger () {
		super(1, true);
		setUnlocalizedName("ladyfinger");
		setMaxStackSize(64);
		setCreativeTab(ChinaCraft.tabPlant);
	}

}
