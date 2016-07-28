package unstudio.sinocraft.block.especial;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import unstudio.sinocraft.tileentity.TileCooker;

public class BlockCooker extends BlockContainer {

    public BlockCooker() {
        super(Material.rock);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileCooker();
    }

}
