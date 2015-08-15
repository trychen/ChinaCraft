package unstudio.chinacraft.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import unstudio.chinacraft.ChinaCraft;
import unstudio.chinacraft.renderer.BlockLanternRenderer;
import unstudio.chinacraft.renderer.BlockWoodenBucketRenderer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class Lantern extends Block{
	
	private IIcon side,top;

	public Lantern() {
		super(Material.wood);
		setBlockName("lantern");
		setCreativeTab(ChinaCraft.tabCore);
		setLightLevel(1.0F);
		setStepSound(soundTypeWood);
	}
	
    public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_)
    {
        this.setBlockBounds(0.0625F*3, 0.0F, 0.0625F*3, 0.0625F*13, 1.0F,0.0625F*13);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        this.setBlockBoundsForItemRender();
    }
    
    public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.0625F*3, 0.0F, 0.0625F*3, 0.0625F*13, 1.0F,0.0625F*13);
    }
	
    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public int getRenderType()
    {
        return BlockLanternRenderer.renderID;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
    	this.top = reg.registerIcon("chinacraft:lantern_top");
    	this.side = reg.registerIcon("chinacraft:lantern");
    }
	
    @SideOnly(Side.CLIENT)
    public IIcon getIconTop()
    {
        	return top;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIconSide()
    {
        	return side;
    }
}
