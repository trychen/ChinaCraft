package unstudio.chinacraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.StatCollector;
import unstudio.chinacraft.ChinaCraft;

public class CopperBlock extends Block{
	public CopperBlock() {
		super(Material.rock);
		setBlockName(StatCollector.translateToLocal("copper_block"));
		setHardness(5.0F);
		setResistance(20.0F);
		setLightLevel(0.0F);
		setStepSound(soundTypeMetal);
		setCreativeTab(ChinaCraft.tabCore);
		setHarvestLevel("pickaxe", 1);
	}
}
