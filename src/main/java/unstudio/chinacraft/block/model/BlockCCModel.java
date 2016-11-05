package unstudio.chinacraft.block.model;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.tileentity.TileModelBlock;

import java.util.List;

public class BlockCCModel extends BlockContainer {
    private Class<? extends ModelBase> model;
    private IIcon icon;
    protected String name;

    /**
     * @param material 材质
     * @param model 模型
     * @param name 名字
     */
    public BlockCCModel(Material material, Class<? extends ModelBase> model, String name, IIcon icon) {
        super(material);
        this.model = model;
        this.icon = icon;
        this.name = name;
        this.setBlockName(name);
        this.setCreativeTab(ChinaCraft.tabCore);
        this.blockIcon = icon;
    }

    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return icon;
    }

    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_) {
    }

    public Class<? extends ModelBase> getModel() {
        return model;
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

    @Override
    public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_,
                                        int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_,
                                        List p_149743_6_, Entity p_149743_7_) {
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_,
                p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        this.setBlockBoundsForItemRender();
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     *
     * @param p_149915_1_
     * @param p_149915_2_
     */
    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileModelBlock(model, name);
    }

    public BlockCCModel setTexture(String name){
        this.name = name;
        return this;
    }
}
