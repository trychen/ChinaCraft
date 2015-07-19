package unstudio.chinacraft.item;

import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import scala.reflect.internal.Trees.This;
import unstudio.chinacraft.ChinaCraft;

public class BronzeIngot extends Item{
	
	public BronzeIngot() {
		setUnlocalizedName("bronze_ingot");
		setMaxStackSize(64);
		setCreativeTab(ChinaCraft.tabCore);
	}
}
