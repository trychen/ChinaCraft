package unstudio.chinacraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.StatCollector;
import unstudio.chinacraft.ChinaCraft;

public class BronzeBlock extends Block {
	public BronzeBlock() {
		super(Material.rock);
		setBlockName(StatCollector.translateToLocal("bronze_block"));
		setHardness(5.0F);
		setResistance(20.0F);
		setStepSound(soundTypeMetal);
		setCreativeTab(ChinaCraft.tabCore);
		setHarvestLevel("pickaxe", 1);
	}
}
