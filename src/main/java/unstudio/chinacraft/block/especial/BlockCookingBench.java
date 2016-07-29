package unstudio.chinacraft.block.especial;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import unstudio.chinacraft.client.gui.GuiID;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.tileentity.TileCookingBench;

import java.util.Random;

public class BlockCookingBench extends BlockContainer {
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    private static boolean update;
    private boolean fire;
    //private IIcon top_off, top_on, side, bottom, front_off, front_on;

    public BlockCookingBench(boolean fire) {
        super(Material.rock);
        this.fire = fire;
        setUnlocalizedName("cooking_bench");
        setHardness(1.5F);
        setResistance(10.0F);
        setLightLevel(fire ? 1.0F : 0.0F);
        setSoundType(SoundType.Stone);
        setHarvestLevel("pickaxe", 0);
        if (!fire)
            setCreativeTab(ChinaCraft.tabCore);
    }

    public static void updateFurnaceBlockState(boolean active, World worldIn, BlockPos pos) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        update = true;
        if (active) {
        	worldIn.setBlockState(pos, ChinaCraft.cooking_bench_on.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)),3);
        	worldIn.setBlockState(pos, ChinaCraft.cooking_bench_on.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)),3);
        } else {
        	worldIn.setBlockState(pos, ChinaCraft.cooking_bench_off.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)),3);
        	worldIn.setBlockState(pos, ChinaCraft.cooking_bench_off.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)),3);
        }
        update = false;
        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }

    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
                                ItemStack stack) {
    	worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    /*@SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int i, int par2) {
        if (i == 0)
            return bottom;
        else if (i == 1)
            return fire ? top_on : top_off;
        else if (i == par2)
            return fire ? front_on : front_off;
        else
            return side;
    }*/

    /*@SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.bottom = reg.registerIcon("chinacraft:cooking_bench_bottom");
        this.top_off = reg.registerIcon("chinacraft:cooking_bench_top_off");
        this.top_on = reg.registerIcon("chinacraft:cooking_bench_top_on");
        this.side = reg.registerIcon("chinacraft:cooking_bench_side");
        this.front_off = reg.registerIcon("chinacraft:cooking_bench_front_off");
        this.front_on = reg.registerIcon("chinacraft:cooking_bench_front_on");
    }*/

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileCookingBench();
    }

    @Override
    public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (this.fire) {
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
            double d2 = (double)pos.getZ() + 0.5D;
            double d3 = 0.52D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;

            switch (enumfacing)
            {
                case WEST:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                    break;
                case EAST:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                    break;
                case NORTH:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
                    break;
                case SOUTH:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (update)
            return;
        TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileCookingBench)
            {
                InventoryHelper.dropInventoryItems(worldIn, pos, (TileCookingBench)tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(World worldIn, BlockPos pos) {
        return Container.calcRedstoneFromInventory(
                (IInventory) worldIn.getTileEntity(pos));
    }

    @Override
    public Item getItem(World worldIn, BlockPos pos) {
        return Item.getItemFromBlock(ChinaCraft.cooking_bench_off);
    }
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ChinaCraft.cooking_bench_off);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
                                    EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote)
            return true;
        playerIn.openGui(ChinaCraft.instance, GuiID.GUI_CookingBench, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
