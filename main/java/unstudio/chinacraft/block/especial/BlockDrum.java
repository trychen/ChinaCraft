package unstudio.chinacraft.block.especial;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import unstudio.chinacraft.common.ChinaCraft;

public class BlockDrum extends Block{
    public BlockDrum() {
        super(Material.wood);
        setBlockName("drum");
        this.setCreativeTab(ChinaCraft.tabCore);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        System.out.println(1);
        int meta = world.getBlockMetadata(x, y, z);
        if (!world.isRemote)
        {
            world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "note.snare", 3.0F, 10f);
        }
        return true;
    }

}
