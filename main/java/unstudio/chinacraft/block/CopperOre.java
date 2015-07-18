package unstudio.chinacraft.block;

import unstudio.chinacraft.ChinaCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.StatCollector;

public class CopperOre extends Block{

	public CopperOre() {
		super(Material.rock);
		setBlockName(StatCollector.translateToLocal("copper_ore"));
		setHardness(3.0F);
		setResistance(15.0F);
		setLightLevel(0.0F);
		setStepSound(soundTypeStone);
		setCreativeTab(ChinaCraft.tabCore);
		setHarvestLevel("pickaxe", 1);
	}

}
