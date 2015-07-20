package unstudio.chinacraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.util.StatCollector;
import unstudio.chinacraft.ChinaCraft;

public class MarbleStair extends BlockStairs{

	public MarbleStair() {
		super(ChinaCraft.smoothMarble, 3);
		setBlockName(StatCollector.translateToLocal("marble_stair"));
		setHardness(3.0F);
		setResistance(15.0F);
		setLightLevel(0.0F);
		setStepSound(soundTypeStone);
		setCreativeTab(ChinaCraft.tabCore);
		setHarvestLevel("pickaxe", 1);
	}
	
}
