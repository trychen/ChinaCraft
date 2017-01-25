package unstudio.chinacraft.block.decoration;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

/**
 * Created by Mouse on 2017/1/25.
 */
public class BlockCCFenceGate extends BlockFenceGate
{

    private Block block;

    public BlockCCFenceGate(Block block){
        this.block = block;
    }

    @Override
    public Material getMaterial() {
        return block.getMaterial();
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_){
        return block.getIcon(p_149691_1_,p_149691_2_);
    }
}
