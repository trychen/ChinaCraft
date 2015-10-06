package unstudio.chinacraft.block;

import java.util.Random;

import unstudio.chinacraft.ChinaCraft;
import unstudio.chinacraft.tileentity.TileBuhrimill;
import unstudio.chinacraft.tileentity.TilePotteryBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPotteryBase extends BlockContainer {

	private String PotteryType;

	public BlockPotteryBase() {
		super(Material.rock);
		setBlockName("pottery");
		setHardness(0.5F);
		setResistance(5.0F);
		setLightLevel(0.0F);
		setStepSound(soundTypeStone);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TilePotteryBase();
	}

	@Override
	public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_,
			int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_,
			ItemStack p_149689_6_) {
		TilePotteryBase tileentity = (TilePotteryBase) p_149689_1_
				.getTileEntity(p_149689_2_, p_149689_3_, p_149689_4_);
		if (p_149689_6_.hasTagCompound()) {
			tileentity.setPotteryType(p_149689_6_.getTagCompound().getString(
					"PotteryType"));
		}
	}

	public void breakBlock(World World, int x, int y, int z, Block Block,
			int var1) {
		TilePotteryBase tileentity = (TilePotteryBase) World.getTileEntity(x,
				y, z);
		Random random = World.rand;
		if (tileentity != null) {
			ItemStack itemstack = new ItemStack(ChinaCraft.blockPotteryBase);
			NBTTagCompound nbtTagCompound = new NBTTagCompound();
			nbtTagCompound
					.setString("PotteryType", tileentity.getPotteryType());
			itemstack.setTagCompound(nbtTagCompound);
			if (itemstack != null) {
				float f = random.nextFloat() * 0.8F + 0.1F;
				float f1 = random.nextFloat() * 0.8F + 0.1F;
				float f2 = random.nextFloat() * 0.8F + 0.1F;
				EntityItem entityitem = new EntityItem(World,
						(double) ((float) x + f), (double) ((float) y + f1),
						(double) ((float) z + f2), new ItemStack(
								itemstack.getItem(), itemstack.stackSize,
								itemstack.getItemDamage()));

				if (itemstack.hasTagCompound()) {
					entityitem.getEntityItem().setTagCompound(
							(NBTTagCompound) itemstack.getTagCompound().copy());
				}

				float f3 = 0.05F;
				entityitem.motionX = (double) ((float) random.nextGaussian() * f3);
				entityitem.motionY = (double) ((float) random.nextGaussian()
						* f3 + 0.2F);
				entityitem.motionZ = (double) ((float) random.nextGaussian() * f3);
				World.spawnEntityInWorld(entityitem);
			}
		}
		World.func_147453_f(x, y, z, Block);
		super.breakBlock(World, x, y, z, Block, var1);
	}

	public String getPotteryType() {
		return PotteryType;
	}

	public void setPotteryType(String potteryType) {
		PotteryType = potteryType;
	}
}
