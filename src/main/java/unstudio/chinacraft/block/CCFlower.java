package unstudio.chinacraft.block;

import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import unstudio.chinacraft.common.ChinaCraft;

import java.util.Random;

import static net.minecraftforge.common.EnumPlantType.Plains;

/**
 * 所有花的父类
 * Created by trychen on 2015/12/26.
 */
public class CCFlower extends BlockBush {

    /**
     * @param name 花的名字
     */
    public CCFlower(String name) {
        super(Material.plants);
        setBlockName(name);
        setCreativeTab(ChinaCraft.tabPlant);
        setStepSound(soundTypeGrass);
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return canBlockStay(world, x, y, z);
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        if (world.getBlock(x, y - 1, z) == Blocks.dirt ||
                world.getBlock(x, y - 1, z) == Blocks.grass)
            return true;
        else
            return false;
    }

    @Override
    public int getRenderType() {
        return 1;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        if (this == ChinaCraft.azalea) return Plains;
        return Plains;
    }
    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    @Override
	public int damageDropped(int p_149692_1_) {
        return p_149692_1_;
    }

}
