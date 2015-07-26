package unstudio.chinacraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import unstudio.chinacraft.ChinaCraft;
import unstudio.chinacraft.renderer.BlockLanternRenderer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class Lantern extends Block{
	
	private IIcon side,top;

	public Lantern() {
		super(Material.wood);
		setBlockName("lantern");
		setCreativeTab(ChinaCraft.tabCore);
		setLightLevel(1.0F);
		setBlockBounds(0.0625F*3, 0.0F, 0.0625F*3, 0.0625F*13, 1.0F,0.0625F*13);
	}
	
    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
    	this.top = reg.registerIcon("chinacraft:lantern_top");
    	this.side = reg.registerIcon("chinacraft:lantern");
    }
	
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_150163_1_)
    {
        if(p_150163_1_ <= 1) {
        	return top;
        }else {
        	return side;
        }
    }
}
