package unstudio.chinacraft.item;

import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import scala.reflect.internal.Trees.This;
import unstudio.chinacraft.ChinaCraft;

public class TinIngot extends Item{
	
	public TinIngot() {
		setUnlocalizedName("tin_ingot");
		setMaxStackSize(64);
		setCreativeTab(ChinaCraft.tabCore);
	}
}
