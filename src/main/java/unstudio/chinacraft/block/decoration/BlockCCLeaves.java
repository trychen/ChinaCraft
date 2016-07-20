package unstudio.chinacraft.block.decoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import unstudio.chinacraft.common.ChinaCraft;

/**
 * Created by AAA on 2016/2/13.
 */
public class BlockCCLeaves extends BlockLeaves {
	public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.<BlockPlanks.EnumType>create("variant", BlockPlanks.EnumType.class, new Predicate<EnumType>(){

		@Override
		public boolean apply(EnumType input) {
			// TODO Auto-generated method stub
			return input.getMetadata() < 4;
		}});
    //private IIcon icon;
    private Block sapling;

    public BlockCCLeaves(Block sapling){
        this.sapling=sapling;
    }

    public Block setSapling(Block sapling){
        this.sapling = sapling;
        return this;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    /*@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.icon = reg.registerIcon(getTextureName());
    }

    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return icon;
    }*/

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if (ChinaCraft.rand.nextInt(3) != 0) {
            ret.add(new ItemStack(this, 1, getMetaFromState(world.getBlockState(pos)) & 3));
        } else {
            ret.add(new ItemStack(ChinaCraft.itemMulberryLeaf, ChinaCraft.rand.nextInt(3) + 1));
        }
        if (ChinaCraft.rand.nextInt(16) == 0)
            ret.add(new ItemStack(ChinaCraft.silkworm, 1, 0));
        return ret;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(sapling);
    }

    @Override
    public int getRenderColor(IBlockState state) {
        return state.getValue(VARIANT).getMetadata() == 1 ? ColorizerFoliage.getFoliageColorPine()
                : (state.getValue(VARIANT).getMetadata() == 2 ? ColorizerFoliage.getFoliageColorBirch() : ColorizerFoliage.getFoliageColorBasic());
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return true;
    }

	@Override
	public EnumType getWoodType(int meta) {
		return (meta & 3) == 1 ? EnumType.SPRUCE : ((meta & 3) == 2 ? EnumType.BIRCH : EnumType.OAK);
	}
	@Override
	public IBlockState getStateFromMeta(int meta) {
		// TODO Auto-generated method stub
		return getDefaultState().withProperty(VARIANT, getWoodType(meta)).withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
	}
	@Override
	public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata();

        if (!((Boolean)state.getValue(DECAYABLE)).booleanValue())
        {
            i |= 4;
        }

        if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue())
        {
            i |= 8;
        }

        return i;
	}
	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, VARIANT);
	}
}
