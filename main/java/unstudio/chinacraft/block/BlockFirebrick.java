package unstudio.chinacraft.block;

import unstudio.chinacraft.ChinaCraft;
import unstudio.chinacraft.util.BlocksChecker;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockFirebrick extends Block{

	public BlockFirebrick() {
		super(Material.rock);
		setBlockName("firebrick");
		setHardness(1.5F);
		setResistance(10.0F);
		setLightLevel(0.0F);
		setStepSound(soundTypeStone);
		setHarvestLevel("pickaxe", 0);
		setCreativeTab(ChinaCraft.tabCore);
	}
	
	@Override
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_,int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
		super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_,p_149749_5_, p_149749_6_);
	}
	
	@Override
	public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_,int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_,ItemStack p_149689_6_) {
		super.onBlockPlacedBy(p_149689_1_, p_149689_2_, p_149689_3_, p_149689_4_,p_149689_5_, p_149689_6_);
		if(p_149689_1_.isRemote) return;
		for(int i=1;i<=4;i++) {
			if(p_149689_1_.getBlock(p_149689_2_, p_149689_3_+i, p_149689_4_)==ChinaCraft.blockFirebrick) continue;
			else {
				p_149689_3_+=(i-1);
				break;
			}
		}
		for(int i=1;i<=4;i++) {
			if(p_149689_1_.getBlock(p_149689_2_, p_149689_3_, p_149689_4_-i)==ChinaCraft.blockFirebrick) continue;
			else {
				p_149689_4_-=(i-1);
				break;
			}
		}
		for(int i=1;i<=4;i++) {
			if(p_149689_1_.getBlock(p_149689_2_-i, p_149689_3_, p_149689_4_)==ChinaCraft.blockFirebrick) continue;
			else {
				p_149689_2_-=(i-1);
				break;
			}
		}
		if(BlocksChecker.Pottery_Kiln.check(p_149689_1_, p_149689_2_, p_149689_3_, p_149689_4_)) {
	        int l = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
	        BlockPotteryKiln.generatePotteryKiln(p_149689_1_, p_149689_2_+1, p_149689_3_-2, p_149689_4_+1, l);
		}
	}
}
