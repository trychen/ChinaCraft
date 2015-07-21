package unstudio.chinacraft.item;

import unstudio.chinacraft.ChinaCraft;
import net.minecraft.block.Block;
import net.minecraft.item.ItemReed;

public class Bamboo extends ItemReed{

	public Bamboo() {
		super(ChinaCraft.blockBamboo);
		setCreativeTab(ChinaCraft.tabPlant);
		setUnlocalizedName("bamboo");
	}

}
