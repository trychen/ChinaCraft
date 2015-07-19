package unstudio.chinacraft.item;

import net.minecraft.item.ItemReed;
import unstudio.chinacraft.ChinaCraft;

public class ItemBuhrimill extends ItemReed{
	
	public ItemBuhrimill() {
		super(ChinaCraft.buhrimill);
		setUnlocalizedName("buhrimill");
		setMaxStackSize(64);
		setCreativeTab(ChinaCraft.tabCore); 
     }

}
