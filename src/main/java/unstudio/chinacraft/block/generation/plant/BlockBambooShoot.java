package unstudio.chinacraft.block.generation.plant;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import unstudio.chinacraft.block.BlockBase;
import unstudio.chinacraft.common.ChinaCraft;

import java.util.Random;

public class BlockBambooShoot extends BlockBase implements IPlantable, IWorldGenerator {
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);
    public BlockBambooShoot() {
        super(Material.PLANTS);
        this.setTickRandomly(true);
        setHardness(0.0F);
        setCreativeTab(ChinaCraft.tabFarming);
        setUnlocalizedName("bamboo_shoot");
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        float f = 0.375F;
        return new AxisAlignedBB(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (worldIn.getBlockState(pos.down()).getBlock() == ChinaCraft.bamboo || this.checkForDrop(worldIn, pos, state))
        {
            if (worldIn.isAirBlock(pos.up()))
            {
                int i;

                for (i = 1; worldIn.getBlockState(pos.down(i)).getBlock() == this; ++i)
                {
                    ;
                }

                if (i < 3)
                {
                    int j = ((Integer)state.getValue(AGE)).intValue();

                    if (j == 15)
                    {
                        worldIn.setBlockState(pos.up(), this.getDefaultState());
                        worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(0)), 4);
                    }
                    else
                    {
                        worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(j + 1)), 4);
                    }
                }
            }
        }
    	super.updateTick(worldIn, pos, state, rand);
    }

    /**
     * Checks to see if its valid to put this block at the specified
     * coordinates. Args: world, pos
     */
    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        Block block = worldIn.getBlockState(pos.add(0, -1, 0)).getBlock();
        return block.canSustainPlant(block.getBlockState().getBaseState(), worldIn, pos.add(0, -1, 0), EnumFacing.UP, this);
    }

    /**
     * Can this block stay at this position. Similar to canPlaceBlockAt except
     * gets checked often with plants.
     */
    
    public boolean canBlockStay(World worldIn, BlockPos pos) {
    	return this.canPlaceBlockAt(worldIn, pos);
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
        // TODO GG in checkForDrop(world, pos, world.getBlockState(pos));
    }

    protected final boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state)
    {
        if (this.canBlockStay(worldIn, pos))
        {
            return true;
        }
        else
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
            return false;
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    	// TODO Auto-generated method stub
    	return Item.getItemFromBlock(ChinaCraft.blockBambooShoot);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube? This determines whether
     * or not to render the shared face of two adjacent blocks and also whether
     * the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    // TODO @Override
    public int getRenderType() {
        return 3;
    }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    	return new ItemStack(Item.getItemFromBlock(ChinaCraft.blockBambooShoot));
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied
     * against the blocks color. Note only called when first determining what to
     * render.
     * TODO Dropped
     */
//    @Override
//    @SideOnly(Side.CLIENT)
//    public Biome colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
//      TODO Auto-generated method stub
//    	return worldIn.getBiomeGenForCoords(pos).getGrassColorAtPos(pos);
//    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Plains;
    }
    
    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return this.getDefaultState();
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int l6 = chunkX * 16 + random.nextInt(16);
        int i11 = random.nextInt(128);
        int l14 = chunkZ * 16 + random.nextInt(16);
        BlockPos pos = new BlockPos(l6, i11, l14);
        String biomeName = world.getBiomeForCoordsBody(pos).getBiomeName();
        if ((biomeName.equals("extreme_hills") || biomeName.equals("forest") || biomeName.equals("forest_hills") || biomeName.equals("smaller_extreme_hills") || biomeName.equals("extreme_hills_with_trees") || biomeName.equals("birch_forest") || biomeName.equals("birch_forest_hills") || biomeName.equals("roofed_forest"))
                && random.nextInt(16) == 0) {
            for (int i = 0; i < 64; ++i)
            {
                BlockPos blockpos = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));

                if (world.isAirBlock(blockpos) && (!world.provider.getHasNoSky() || blockpos.getY() < 255) && ChinaCraft.blockBambooShoot.canBlockStay(world, blockpos))
                {
                    world.setBlockState(blockpos, ChinaCraft.blockBambooShoot.getDefaultState(), 2);
                }
            }
        }
    }
}
