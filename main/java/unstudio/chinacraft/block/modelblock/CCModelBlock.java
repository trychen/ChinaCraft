package unstudio.chinacraft.block.modelblock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import unstudio.chinacraft.block.tileentity.TileModelBlock;
import unstudio.chinacraft.common.ChinaCraft;

public class CCModelBlock extends Block{
    private ModelBase model;


    /**
     * @param material 材质
     * @param model 模型
     * @param name 名字
     */
    public CCModelBlock(Material material, ModelBase model,String name) {
        super(material);
        this.model = model;
        setBlockName(name);
        setBlockTextureName(name);
        setCreativeTab(ChinaCraft.tabCore);
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileModelBlock(model,getTextureName());
    }

    //You don't want the normal render type, or it wont render properly.
    @Override
    public int getRenderType() {
        return -1;
    }

    //It's not an opaque cube, so you need this.
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    //It's not a normal block, so you need this too.
    public boolean renderAsNormalBlock() {
        return false;
    }
}
