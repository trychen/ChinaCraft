package unstudio.chinacraft.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import unstudio.chinacraft.ChinaCraft;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class WoodenWindow extends BlockPane{

	public WoodenWindow(String arg1,String arg2) {
		super(arg1, arg2, Material.wood, true);
		setBlockName(StatCollector.translateToLocal("wooden_window"));
		setHardness(0.3F);
		setStepSound(soundTypeWood);
		setCreativeTab(ChinaCraft.tabCore);
	}

    public int damageDropped(int p_149692_1_)
    {
        return p_149692_1_;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        super.registerBlockIcons(p_149651_1_);
    }
}
