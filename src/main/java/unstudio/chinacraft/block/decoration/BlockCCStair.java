package unstudio.chinacraft.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

/**
 * Created by AAA on 2016/2/9.
 */
public class BlockCCStair extends BlockStairs {

	@Deprecated
    public BlockCCStair(Block block, int damage) {
    	this(block.getStateFromMeta(damage));
    }
	
    public BlockCCStair(IBlockState state){
    	super(state);
        this.useNeighborBrightness = true;
    }

    public Block setHarvestLevelReturnBlock(String toolClass, int level) {
        super.setHarvestLevel(toolClass, level);
        return this;
    }
}
