package unstudio.chinacraft.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockWood;
import net.minecraft.block.material.Material;
import unstudio.chinacraft.ChinaCraft;

public class BambooBlock extends Block{
	
	public BambooBlock() {
		super (Material.wood);
		setBlockName("bamboo_block");
		setHardness(7.0f);
		setCreativeTab(ChinaCraft.tabCore);
	}

}
