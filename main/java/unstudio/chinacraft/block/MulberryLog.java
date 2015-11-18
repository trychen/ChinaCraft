package unstudio.chinacraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import unstudio.chinacraft.ChinaCraft;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class MulberryLog extends BlockLog{
	
	IIcon top,side;

	public MulberryLog() {
		setCreativeTab(ChinaCraft.tabCore);
		setBlockName(StatCollector.translateToLocal("mulberry_log"));
	}
	
    @Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
    	this.top = reg.registerIcon("chinacraft:mulberry_log_top");
    	this.side = reg.registerIcon("chinacraft:mulberry_log");
    }
	
    @Override
	@SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int p_150163_1_)
    {
        return side;
    }

    @Override
	@SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int p_150161_1_)
    {
        return top;
    }
}
