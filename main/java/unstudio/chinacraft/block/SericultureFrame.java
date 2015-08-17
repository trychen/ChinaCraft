package unstudio.chinacraft.block;

import unstudio.chinacraft.tileentity.TileSericultureFrame;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class SericultureFrame extends BlockContainer{

	public SericultureFrame() {
		super(Material.wood);
		setBlockName("sericulture_frame");
		setHardness(1.5F);
		setResistance(10.0F);
		setStepSound(soundTypeWood);
	}
	
	

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileSericultureFrame();
	}

}
