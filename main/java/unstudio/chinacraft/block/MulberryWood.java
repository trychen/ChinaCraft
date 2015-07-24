package unstudio.chinacraft.block;

import unstudio.chinacraft.ChinaCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.StatCollector;

public class MulberryWood extends Block{

	public MulberryWood() {
		super(Material.wood);
		setBlockName(StatCollector.translateToLocal("mulberry_wood"));
		setCreativeTab(ChinaCraft.tabCore);
	}

}
