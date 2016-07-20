package unstudio.chinacraft.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.item.ItemCCSlab;

import java.util.Random;

/**
 * Created by AAA on 2016/2/9.
 */
public class BlockCCSlab extends BlockSlab{

    private Block block;
    private boolean isDouble;
    //private IIcon icon;

    public BlockCCSlab(boolean p_i45410_1_, Material p_i45410_2_) {
        super(p_i45410_2_);
        isDouble = p_i45410_1_;
        fullBlock = p_i45410_1_;
        this.useNeighborBrightness = !p_i45410_1_;
    }

    @Override
    public String getUnlocalizedName(int meta) {
        return getUnlocalizedName();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return block==null?Item.getItemFromBlock(this):Item.getItemFromBlock(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos) {
        return block==null?Item.getItemFromBlock(this):Item.getItemFromBlock(block);
    }

    public Block getBlockSlab() {
        return block;
    }

    public BlockCCSlab setBlockSlab(Block block) {
        this.block = block;
        return this;
    }

    /*@Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {

        return icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        icon = p_149651_1_.registerIcon(getTextureName());
    }*/

    public BlockCCSlab setHarvestLevelReturnBlock(String toolClass, int level) {
        super.setHarvestLevel(toolClass, level);
        return this;
    }

	@Override
	public boolean isDouble() {
		return isDouble;
	}

	@Override
	public IProperty<?> getVariantProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getVariant(ItemStack stack) {
		// TODO Auto-generated method stub
		return null;
	}
}
