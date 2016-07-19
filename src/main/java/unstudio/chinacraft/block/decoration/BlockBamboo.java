package unstudio.chinacraft.block.decoration;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import unstudio.chinacraft.common.ChinaCraft;

/**
 * 竹子的主类，可种植，与甘蔗差不多
 */
public class BlockBamboo extends Block implements IPlantable {

    //private IIcon icon;

    public BlockBamboo() {
        super(Material.plants);
        float f = 0.375F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
        this.setTickRandomly(true);
        setHardness(3.0F);
        setCreativeTab(ChinaCraft.tabCore);
        setUnlocalizedName("bamboo");
    }

    /*@Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {return icon;}

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        icon = p_149651_1_.registerIcon(getTextureName());
    }*/

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (worldIn.getBlockState(pos.down()) == ChinaCraft.bamboo
                || this.checkForDrop(worldIn, pos, state)) {
            if (worldIn.isAirBlock(pos.up())) {
                int l;

                for (l = 1; worldIn.getBlockState(pos.down(l)).getBlock() == this; ++l) {
                    ;
                }

                if (l < 10) {
                    int i1 = p_149674_1_.getBlockMetadata(p_149674_2_, p_149674_3_, p_149674_4_);

                    if (i1 == 15) {
                        p_149674_1_.setBlock(p_149674_2_, p_149674_3_ + 1, p_149674_4_, this);
                        p_149674_1_.setBlockMetadataWithNotify(p_149674_2_, p_149674_3_, p_149674_4_, 0, 4);
                    } else {
                        p_149674_1_.setBlockMetadataWithNotify(p_149674_2_, p_149674_3_, p_149674_4_, i1 + 1, 4);
                    }
                }
            }
        }
    }

    /**
     * Checks to see if its valid to put this block at the specified
     * coordinates. Args: world, x, y, z
     */
    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        int i = 1;
        Block block;
        while (true) {
            block = worldIn.getBlockState(pos.down(i)).getBlock();
            if (block != ChinaCraft.bamboo) {
                break;
            }
            i++;
        }
        return block.canSustainPlant(worldIn, pos.down(), EnumFacing.UP, this);
    }

    /**
     * Can this block stay at this position. Similar to canPlaceBlockAt except
     * gets checked often with plants.
     */
    
    public boolean canBlockStay(World worldIn, BlockPos pos) {
    	return this.canPlaceBlockAt(worldIn, pos);
    }

    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
        this.checkForDrop(worldIn, pos, state);
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

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this
     * box can change after the pool has been cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_,
            int p_149668_4_) {
        return null;
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return ChinaCraft.itemBamboo;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube? This determines whether
     * or not to render the shared face of two adjacent blocks and also whether
     * the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False
     * (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType() {
        return 1;
    }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return ChinaCraft.itemBamboo;
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied
     * against the blocks color. Note only called when first determining what to
     * render.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_) {
        return p_149720_1_.getBiomeGenForCoords(p_149720_2_, p_149720_4_).getBiomeGrassColor(p_149720_2_, p_149720_3_,
                p_149720_4_);
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Plains;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        return this;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_,
            float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        ItemStack item = player.inventory.getCurrentItem();
        if (item == null)
            return true;
        if (item.getItem() == Items.dye) {
            if (item.getItemDamage() == 15) {
                if (!player.capabilities.isCreativeMode && --item.stackSize <= 0) {
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                }
                Random r = world.rand;
                int x1 = x + r.nextInt(3) - 1;
                int z1 = z + r.nextInt(3) - 1;
                if (world.isAirBlock(x1, y, z1) && (world.getBlock(x1, y - 1, z1) == Blocks.grass
                        || world.getBlock(x1, y - 1, z1) == Blocks.dirt)) {
                    world.setBlock(x1, y, z1, ChinaCraft.blockBambooShoot, 0, 2);
                }
            }
        }
        return true;
    }
}
