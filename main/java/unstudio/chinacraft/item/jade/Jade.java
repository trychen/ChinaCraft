package unstudio.chinacraft.item.jade;

import java.util.Random;

import net.minecraft.item.Item;
import unstudio.chinacraft.ChinaCraft;

public class Jade extends Item{
	private int Level = 1;
	private Random random = new Random();
	public Jade (String s) {
		setUnlocalizedName(s);
		setMaxStackSize(16);
		setCreativeTab(ChinaCraft.tabCore);
	}
	public Jade (String s,int level) {
		setUnlocalizedName(s);
		setMaxStackSize(16);
		setCreativeTab(ChinaCraft.tabCore);
	}

	public int getEnchantability()
	{
		return 3;
	}
}
