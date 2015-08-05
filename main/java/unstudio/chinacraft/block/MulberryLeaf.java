package unstudio.chinacraft.block;

import java.util.Random;

import com.jcraft.jorbis.Block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import unstudio.chinacraft.ChinaCraft;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MulberryLeaf extends BlockLeaves{

	IIcon icon;
	private int blockID;
	private boolean graphicsLevel;
	
	public MulberryLeaf() {
		setCreativeTab(ChinaCraft.tabCore);
		setBlockName(StatCollector.translateToLocal("mulberry_leaf"));
	}
	
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
    	this.icon = reg.registerIcon("chinacraft:mulberry_leaf");
    }
    
	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return true;
	}
	
	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		return icon;
	}
	
    protected void func_150124_c(World p_150124_1_, int p_150124_2_, int p_150124_3_, int p_150124_4_, int p_150124_5_, int p_150124_6_)
    {
            this.dropBlockAsItem(p_150124_1_, p_150124_2_, p_150124_3_, p_150124_4_, new ItemStack(ChinaCraft.itemMulberryLeaf , p_150124_1_.rand.nextInt(3)+1));
            if(p_150124_1_.rand.nextInt(16) == 0) {
            	this.dropBlockAsItem(p_150124_1_, p_150124_2_, p_150124_3_, p_150124_4_, new ItemStack(ChinaCraft.silkworm, 1, 0));
            }
    }


	@Override
	public String[] func_150125_e() {
		return new String[] {"mulbrry"};
	}

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(ChinaCraft.mulberrySapling);
    }
    
    @Override
    public int getRenderColor(int par1)
    {
        return (par1 & 3) == 1 ? ColorizerFoliage.getFoliageColorPine() : ((par1 & 3) == 2 ? ColorizerFoliage.getFoliageColorBirch() : ColorizerFoliage.getFoliageColorBasic());
    }
    @Override
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
    {
        return true;
    }
}
