package unstudio.chinacraft.block;

import unstudio.chinacraft.ChinaCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockFirebrick extends Block{

	public BlockFirebrick() {
		super(Material.rock);
		setBlockName("firebrick");
		setHardness(1.5F);
		setResistance(10.0F);
		setLightLevel(0.0F);
		setStepSound(soundTypeStone);
		setHarvestLevel("pickaxe", 0);
		setCreativeTab(ChinaCraft.tabCore);
	}
	
	

}
