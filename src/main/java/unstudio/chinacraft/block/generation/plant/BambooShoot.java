package unstudio.chinacraft.block.generation.plant;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import unstudio.chinacraft.common.ChinaCraft;

import java.util.Random;

public class BambooShoot extends Block implements IPlantable, IWorldGenerator
{
    public BambooShoot()
    {
        super(Material.plants);
        float f = 0.375F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
        this.setTickRandomly(true);
        setHardness(0.0F);
        setCreativeTab(ChinaCraft.tabPlant);
        setBlockName(StatCollector.translateToLocal("bamboo_shoot"));
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
	public void updateTick(World worldIn, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random random)
    {
        if (this.func_150170_e(worldIn, p_149674_2_, p_149674_3_, p_149674_4_))
        {
            if (worldIn.isAirBlock(p_149674_2_, p_149674_3_ + 1, p_149674_4_))
            {
                    int i1 = worldIn.getBlockMetadata(p_149674_2_, p_149674_3_, p_149674_4_);
                    if (i1 == 15)
                    {
                        worldIn.setBlock(p_149674_2_, p_149674_3_, p_149674_4_, ChinaCraft .blockBamboo);
                        worldIn.setBlockMetadataWithNotify(p_149674_2_, p_149674_3_, p_149674_4_, 0, 4);
                    }
                    else
                    {
                        worldIn.setBlockMetadataWithNotify(p_149674_2_, p_149674_3_, p_149674_4_, i1 + 1, 4);
                    }
                }
            }
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    @Override
	public boolean canPlaceBlockAt(World worldIn, int x, int y, int z)
    {
    	Block block = worldIn.getBlock(x, y - 1, z);
        return block.canSustainPlant(worldIn, x, y - 1, z, ForgeDirection.UP, this);
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    @Override
	public boolean canBlockStay(World world, int x, int y, int z)
    {
        return this.canPlaceBlockAt(world, x, y, z);
    }
    
    @Override
	public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block block)
    {
        this.func_150170_e(worldIn, x, y, z);
    }

    protected final boolean func_150170_e(World p_150170_1_, int p_150170_2_, int p_150170_3_, int p_150170_4_)
    {
        if (!this.canBlockStay(p_150170_1_, p_150170_2_, p_150170_3_, p_150170_4_))
        {
            this.dropBlockAsItem(p_150170_1_, p_150170_2_, p_150170_3_, p_150170_4_, p_150170_1_.getBlockMetadata(p_150170_2_, p_150170_3_, p_150170_4_), 0);
            p_150170_1_.setBlockToAir(p_150170_2_, p_150170_3_, p_150170_4_);
            return false;
        }else {
        	return true;
		}
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    @Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }

    @Override
	public Item getItemDropped(int p_149650_1_, Random random, int p_149650_3_)
    {
        return Item.getItemFromBlock(ChinaCraft.bambooShoot);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @Override
	public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    @Override
	public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
	public int getRenderType()
    {
        return 1;
    }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @Override
	@SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return Item.getItemFromBlock(ChinaCraft.bambooShoot);
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    @Override
	@SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
        return p_149720_1_.getBiomeGenForCoords(p_149720_2_, p_149720_4_).getBiomeGrassColor(p_149720_2_, p_149720_3_, p_149720_4_);
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
        return EnumPlantType.Plains;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z)
    {
        return this;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }


	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    	int l6 = chunkX*16 + random.nextInt(16);
    	int i11 = random.nextInt(128);
    	int l14 = chunkZ*16 + random.nextInt(16);
    	int id = world.getBiomeGenForCoords(l6, l14).biomeID;
    	if((id == 3||id == 4||id == 18||id == 20||id == 34||id == 27||id == 28||id == 29)&&random.nextInt(16)==0) {
    		new WorldGenFlowers(ChinaCraft.bambooShoot).generate(world, random, l6, i11, l14);
    	}
	}
}
