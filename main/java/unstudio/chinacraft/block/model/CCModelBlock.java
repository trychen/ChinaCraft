package unstudio.chinacraft.block.model;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.tileentity.TileModelBlock;

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

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }
}
