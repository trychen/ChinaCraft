package unstudio.chinacraft.block;

import net.minecraft.block.BlockStairs;
import net.minecraft.util.StatCollector;
import unstudio.chinacraft.ChinaCraft;

public class MarbleStair extends BlockStairs{

	public MarbleStair() {
		super(ChinaCraft.smoothMarble, 0);
		setBlockName(StatCollector.translateToLocal("marble_stair"));
		setCreativeTab(ChinaCraft.tabCore);
		setLightOpacity(0);
	}
	
}
