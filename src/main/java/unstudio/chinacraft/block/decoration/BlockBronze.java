package unstudio.chinacraft.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.StatCollector;
import unstudio.chinacraft.common.ChinaCraft;

public class BlockBronze extends Block {
	public BlockBronze() {
		super(Material.rock);
		setBlockName(StatCollector.translateToLocal("bronze_block"));
		setHardness(5.0F);
		setResistance(20.0F);
		setStepSound(soundTypeMetal);
		setCreativeTab(ChinaCraft.tabCore);
		setHarvestLevel("pickaxe", 1);
	}
}
