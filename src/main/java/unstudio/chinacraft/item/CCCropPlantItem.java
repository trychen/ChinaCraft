package unstudio.chinacraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import unstudio.chinacraft.common.ChinaCraft;

public class CCCropPlantItem extends Item implements IPlantable {

	private Block bb;

	public CCCropPlantItem(Block b) {
		this.setCreativeTab(ChinaCraft.tabFarming);
		this.bb = b;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ) {
		if (side != EnumFacing.UP) {
			return false;
		} else if (playerIn.canPlayerEdit(pos, side, stack) && playerIn.canPlayerEdit(pos.add(0, 1, 0), side, stack)) {
			if (worldIn.getBlockState(pos).getBlock().canSustainPlant(worldIn, pos, EnumFacing.UP, this)
					&& worldIn.isAirBlock(pos.add(0, 1, 0))) {
				worldIn.setBlockState(pos, bb.getDefaultState());
				--stack.stackSize;
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Crop;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return bb.getDefaultState();
	}

}
