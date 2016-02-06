package unstudio.chinacraft.block.decoration;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;

import unstudio.chinacraft.common.ChinaCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MulberryLeaf extends BlockLeaves {

    IIcon icon;

    public MulberryLeaf() {
        setCreativeTab(ChinaCraft.tabPlant);
        setBlockName(StatCollector.translateToLocal("mulberry_leaf"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.icon = reg.registerIcon("chinacraft:mulberry_leaf");
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return true;
    }

    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return icon;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if (ChinaCraft.rand.nextInt(3) != 0) {
            ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 3));
        } else {
            ret.add(new ItemStack(ChinaCraft.itemMulberryLeaf, ChinaCraft.rand.nextInt(3) + 1));
        }
        if (ChinaCraft.rand.nextInt(16) == 0)
            ret.add(new ItemStack(ChinaCraft.silkworm, 1, 0));
        return ret;
    }

    @Override
    public String[] func_150125_e() {
        return new String[] { "mulbrry" };
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(ChinaCraft.mulberrySapling);
    }

    @Override
    public int getRenderColor(int par1) {
        return (par1 & 3) == 1 ? ColorizerFoliage.getFoliageColorPine()
                : ((par1 & 3) == 2 ? ColorizerFoliage.getFoliageColorBirch() : ColorizerFoliage.getFoliageColorBasic());
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_,
            int p_149646_5_) {
        return true;
    }
}
