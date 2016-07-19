package unstudio.chinacraft.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockRule {

	@Deprecated
    private Block block;
	@Deprecated
    private int data;
    private IBlockState state;
    
    @Deprecated
    public BlockRule(Block block) {
        this(block.getDefaultState());
    }
    
    @Deprecated
    public BlockRule(Block block, int data) {
    	this(block.getStateFromMeta(data));
    }
    
    public BlockRule(IBlockState state) {
    	this.setRule(state);
    }
    @Deprecated
    public Block getBlock() {
        return this.block;
    }
    @Deprecated
    public int getData() {
        return this.data;
    }
    
    public IBlockState getState() {
		return state;
	}

	@Deprecated
    public void setRule(Block block, int data) {
        setRule(block.getStateFromMeta(data));
    }

    public void setRule(IBlockState state) {
    	this.state = state;
    }
    public boolean check(World world, BlockPos pos) {
        return this.check(world.getBlockState(pos));
    }
    @Deprecated
    public boolean check(World world, int X, int Y, int Z) {
    	return check(world, new BlockPos(X, Y, Z));
    }
    
    @Deprecated
    public boolean check(Block block, int data) {
    	return check(block.getStateFromMeta(data));
        /*if (block == null)
            return false;
        if (!block.equals(this.block))
            return false;
        if (this.data != -1 && data != this.data)
            return false;
        return true;*/
    }
    
    public boolean check(IBlockState state) {
        if (state == null)
            return false;
        if (!state.equals(this.state))
            return false;
        return true;
    }

    public BlockRule copy() {
        return new BlockRule(state);
    }
}
