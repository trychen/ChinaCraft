package unstudio.chinacraft.block;

import unstudio.chinacraft.tileentity.TilePotteryBlock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPotteryBase extends BlockContainer{

	public BlockPotteryBase() {
		super(Material.rock);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TilePotteryBlock();
	}

}
