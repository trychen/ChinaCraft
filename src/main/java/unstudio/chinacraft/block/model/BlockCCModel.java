package unstudio.chinacraft.block.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.tileentity.TileModelBlock;

import java.util.List;

public class BlockCCModel extends BlockContainer {
    private Class model;
    protected String name;

    /**
     * @param material 材质
     * @param model 模型
     * @param name 名字
     */
    public BlockCCModel(Material material, Class model, String name) {
        super(material);
        this.model = model;
        this.name = name;
        this.setBlockName(name);
        this.setCreativeTab(ChinaCraft.tabCore);
    }

    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_) {
    }

    @SideOnly(Side.CLIENT)
    private ModelBase modelBase;

    @SideOnly(Side.CLIENT)
    public ModelBase getModel() {
        if (modelBase == null){
            try {
                modelBase = (ModelBase) model.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return modelBase;
    }

    @SideOnly(Side.CLIENT)
    private ResourceLocation resourceLocation;

    @SideOnly(Side.CLIENT)
    public ResourceLocation getTexture(){
        if (resourceLocation == null){
            resourceLocation = new ResourceLocation("chinacraft:textures/models/block/" + this.name + ".png");
        }
        return resourceLocation;
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

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileModelBlock();
    }

    public BlockCCModel setTexture(String name){
        this.name = name;
        return this;
    }
}
