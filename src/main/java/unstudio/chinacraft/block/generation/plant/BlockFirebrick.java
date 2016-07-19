package unstudio.chinacraft.block.generation.plant;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import unstudio.chinacraft.block.especial.BlockPotteryKiln;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.util.BlocksChecker;

public class BlockFirebrick extends Block {

    public BlockFirebrick() {
        super(Material.rock);
        setUnlocalizedName("firebrick");
        setHardness(1.5F);
        setResistance(10.0F);
        setLightLevel(0.0F);
        setStepSound(soundTypeStone);
        setHarvestLevel("pickaxe", 0);
        setCreativeTab(ChinaCraft.tabCore);
    }
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
    		ItemStack stack) {
    	super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (worldIn.isRemote)
            return;
        for (int i = 1; i <= 4; i++) {
            if (worldIn.getBlockState(pos.add(0, -i, 0)).getBlock() == ChinaCraft.blockFirebrick)
                continue;
            else {
            	pos = pos.down(i - 1);
                break;
            }
        }
        for (int i = 1; i <= 4; i++) {
            if (worldIn.getBlockState(pos.add(0, 0, -i)).getBlock() == ChinaCraft.blockFirebrick)
                continue;
            else {
            	pos = pos.add(0, 0, i - 1);
                break;
            }
        }
        for (int i = 1; i <= 4; i++) {
            if (worldIn.getBlockState(pos.add(-i, 0, 0)).getBlock() == ChinaCraft.blockFirebrick)
                continue;
            else {
            	pos = pos.add(i-1, 0, 0);
                break;
            }
        }
        if (BlocksChecker.Pottery_Kiln.check(worldIn, pos)) {
            int l = MathHelper.floor_double(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
            BlockPotteryKiln.generatePotteryKiln(worldIn, pos.add(1, -2, 1), l);
        }
    }
}
