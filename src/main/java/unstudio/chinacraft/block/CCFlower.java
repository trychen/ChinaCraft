package unstudio.chinacraft.block;

import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import unstudio.chinacraft.common.ChinaCraft;

import java.util.Random;

import static net.minecraftforge.common.EnumPlantType.Plains;

/**
 * 所有花的父类
 */
public class CCFlower extends BlockBush {


    /**
     * 花的构造器
     * @param name 花的名字
     */
    public CCFlower(String name) {
        super(Material.PLANTS);
        setUnlocalizedName(name);
        setCreativeTab(ChinaCraft.tabFarming);
        setSoundType(SoundType.PLANT);
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return canBlockStay(worldIn, pos, worldIn.getBlockState(pos));
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
        if (worldIn.getBlockState(pos.add(0, -1, 0)).getBlock() == Blocks.DIRT || worldIn.getBlockState(pos.add(0, -1, 0)).getBlock() == Blocks.GRASS)
            return true;
        else
            return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos) {
        if (this == ChinaCraft.azalea) return Plains;
        if (this == ChinaCraft.peony) return Plains;
        if (this == ChinaCraft.chrysanthemum) return Plains;
        return Plains;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and
     * wood.
     */
    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

}
