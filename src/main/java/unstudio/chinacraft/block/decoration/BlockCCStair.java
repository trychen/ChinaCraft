package unstudio.chinacraft.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

/**
 * Created by AAA on 2016/2/9.
 */
public class BlockCCStair extends BlockStairs {

    public BlockCCStair(Block block, int damage) {
        super(block,damage);
        this.useNeighborBrightness = true;
    }

    public Block setHarvestLevelReturnBlock(String toolClass, int level) {
        super.setHarvestLevel(toolClass, level);
        return this;
    }
}
