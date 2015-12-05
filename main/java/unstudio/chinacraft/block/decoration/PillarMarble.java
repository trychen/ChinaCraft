package unstudio.chinacraft.block.decoration;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import unstudio.chinacraft.common.ChinaCraft;

public class PillarMarble extends BlockRotatedPillar {

	IIcon top , side;

	public PillarMarble() {
		super(Material.rock);
		setBlockName(StatCollector.translateToLocal("pillar_marble"));
		setHardness(3.0F);
		setResistance(20.0F);
		setLightLevel(0.0F);
		setStepSound(soundTypeStone);
		setCreativeTab(ChinaCraft.tabCore);
		setHarvestLevel("pickaxe", 1);
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		this.top = reg.registerIcon("chinacraft:pillar_marble_top");
		this.side = reg.registerIcon("chinacraft:pillar_marble");
	}

	@Override
	protected IIcon getSideIcon(int p_150163_1_) {
		return side;
	}
	
    @Override
	@SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int p_150161_1_)
    {
        return top;
    }
}
