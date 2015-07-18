package unstudio.chinacraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.StatCollector;
import unstudio.chinacraft.ChinaCraft;

public class Marble extends Block{
	public Marble() {
		super(Material.rock);
		setBlockName(StatCollector.translateToLocal("marble"));
		setHardness(6.0F);
		setResistance(20.0F);
		setLightLevel(0.0F);
		setStepSound(soundTypeStone);
		setCreativeTab(ChinaCraft.tabCore);
		setHarvestLevel("pickaxe", 1);
	}

}
