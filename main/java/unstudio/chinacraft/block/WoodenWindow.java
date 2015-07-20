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
	
    private static final IIcon[] field_150106_a = new IIcon[16];
    private static final IIcon[] field_150105_b = new IIcon[16];

	public WoodenWindow() {
		super("wooden_window", "wooden_window_top", Material.wood, true);
		setBlockName(StatCollector.translateToLocal("wooden_window"));
		setHardness(0.3F);
		setStepSound(soundTypeGlass);
		setCreativeTab(ChinaCraft.tabCore);
	}
	
    @SideOnly(Side.CLIENT)
    public IIcon func_149735_b(int p_149735_1_, int p_149735_2_)
    {
        return field_150106_a[p_149735_2_ % field_150106_a.length];
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_150104_b(int p_150104_1_)
    {
        return field_150105_b[~p_150104_1_ & 15];
    }
    
    public static int func_150032_b(int p_150032_0_)
    {
        return func_150031_c(p_150032_0_);
    }

    public static int func_150031_c(int p_150031_0_)
    {
        return ~p_150031_0_ & 15;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return this.func_149735_b(p_149691_1_, ~p_149691_2_ & 15);
    }

    public int damageDropped(int p_149692_1_)
    {
        return p_149692_1_;
    }

    @SideOnly(Side.CLIENT)
    public static int func_150103_c(int p_150103_0_)
    {
        return p_150103_0_ & 15;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
        for (int i = 0; i < field_150106_a.length; ++i)
        {
            p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        super.registerBlockIcons(p_149651_1_);

        for (int i = 0; i < field_150106_a.length; ++i)
        {
            field_150106_a[i] = p_149651_1_.registerIcon(this.getTextureName() + "_" + ItemDye.field_150921_b[func_150103_c(i)]);
            field_150105_b[i] = p_149651_1_.registerIcon(this.getTextureName() + "_top_" + ItemDye.field_150921_b[func_150103_c(i)]);
        }
    }
}
