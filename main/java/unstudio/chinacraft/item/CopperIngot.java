package unstudio.chinacraft.item;

import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import scala.reflect.internal.Trees.This;
import unstudio.chinacraft.ChinaCraft;

public class CopperIngot extends Item{
	
	public CopperIngot() {
		setUnlocalizedName("copper_ingot");
		setMaxStackSize(64);
		setCreativeTab(ChinaCraft.tabCore);
	}
}
