package unstudio.chinacraft.block.model;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import unstudio.chinacraft.tileentity.TileModelBlock;

public class BlockCCModel extends Block implements ITileEntityProvider{
    private Class<? extends ModelBase> model;
    private String texturename;
    /**
     * @param material
     *            材质
     * @param model
     *            模型
     * @param name
     *            名字
     */
    public BlockCCModel(Material material, Class<? extends ModelBase> model, String name) {
        super(material);
        this.model = model;
        setUnlocalizedName(name);
        setBlockTextureName(name);
        texturename = name;
    }

    /*@Override
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        blockIcon = p_149651_1_.registerIcon("minecraft:redstone_block");
    }*/

    public Class<? extends ModelBase> getModel() {
        return model;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileModelBlock(model, getTextureName());
    }

    protected String getTextureName() {
		return texturename;
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
