package unstudio.chinacraft.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

/**
 * Created by Mouse on 2017/1/25.
 */
public class BlockCCFence extends BlockFence {

    public BlockCCFence(String p_i45406_1_, Material p_i45406_2_) {
        super(p_i45406_1_, p_i45406_2_);
    }

    private Block gate;

    public BlockCCFence setGate(Block gate){
        this.gate = gate;
        return this;
    }

    public Block getGate(){
        return gate;
    }

    public boolean canConnectFenceTo(IBlockAccess p_149826_1_, int p_149826_2_, int p_149826_3_, int p_149826_4_)
    {
        Block block = p_149826_1_.getBlock(p_149826_2_, p_149826_3_, p_149826_4_);
        return block == gate||super.canConnectFenceTo(p_149826_1_,p_149826_2_,p_149826_3_,p_149826_4_);
    }
}
