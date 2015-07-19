package unstudio.chinacraft.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import unstudio.chinacraft.ChinaCraft;

public class PillarMarble extends Block{
	
	IIcon top = null, down = null, st1 = null, st2 = null, st3 = null, st4 = null;
	
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
	public IIcon getIcon(int i, int par2){
	if (i == 0)
	return top;
	else if (i == 1)
	return down;
	else if (i == 2)
	return st1;
	else if (i == 3)
	return st2;
	else if (i == 4)
	return st4;
	else if (i == 5)
	return st3;
	else
	return top;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister reg){
	this.top = reg.registerIcon("chinacraft:pillar_marble_top");
	this.down = reg.registerIcon("chinacraft:pillar_marble_top");
	this.st1 = reg.registerIcon("chinacraft:pillar_marble");
	this.st2 = reg.registerIcon("chinacraft:pillar_marble");
	this.st3 = reg.registerIcon("chinacraft:pillar_marble");
	this.st4 = reg.registerIcon("chinacraft:pillar_marble");
	}
}
