package unstudio.chinacraft.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.util.StatCollector;
import unstudio.chinacraft.common.ChinaCraft;

/**
 * Created by AAA on 2016/2/9.
 */
public class BlockCCStair extends BlockStairs {

    public BlockCCStair(Block block, int damage) {
        super(block,damage);
        this.useNeighborBrightness = true;
        //setLightOpacity(0);
    }
}
