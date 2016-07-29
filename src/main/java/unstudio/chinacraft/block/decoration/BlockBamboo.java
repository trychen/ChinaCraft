package unstudio.chinacraft.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import unstudio.chinacraft.common.ChinaCraft;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * 竹子的主类，可种植，与甘蔗差不多
 */
public class BlockBamboo extends Block implements IPlantable,IBlockColor {
    
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);
    //private IIcon icon;

    public BlockBamboo() {
        super(Material.PLANTS);
        setTickRandomly(true);
        setHardness(3.0F);
        setCreativeTab(ChinaCraft.tabCore);
        setUnlocalizedName("bamboo");
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(this);
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
        return block.canSustainPlant(worldIn.getBlockState(pos),worldIn, pos.down(), EnumFacing.UP, this);
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
        this.checkForDrop((World)world, pos, world.getBlockState(neighbor));
    }

    protected final boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state)
    {
        if (this.canBlockStay(worldIn, pos)) {
            return true;
        } else {
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
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
        float f = 0.375F;
        AxisAlignedBB aabb = new AxisAlignedBB(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
        return aabb;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ChinaCraft.itemBamboo;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube? This determines whether
     * or not to render the shared face of two adjacent blocks and also whether
     * the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getItem(World worldIn, BlockPos pos,IBlockState state) {
        return new ItemStack(ChinaCraft.itemBamboo);
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied
     * against the blocks color. Note only called when first determining what to
     * render.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
    	return worldIn.getBiomeGenForCoords(pos).getGrassColorAtPos(pos);
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Plains;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return getDefaultState();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        ItemStack item = playerIn.inventory.getCurrentItem();
        if (item == null)
            return true;
        if (item.getItem() == Items.DYE) {
            if (item.getItemDamage() == 15) {
                if (!playerIn.capabilities.isCreativeMode && --item.stackSize <= 0) {
                    playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, null);
                }
                Random r = worldIn.rand;
                pos = pos.add(r.nextInt(3) - 1, 0, r.nextInt(3) - 1);
                if (worldIn.isAirBlock(pos) && (worldIn.getBlockState(pos.down()).getBlock() == Blocks.GRASS
                        || worldIn.getBlockState(pos.down()).getBlock() == Blocks.DIRT)) {
                    worldIn.setBlockState(pos, ChinaCraft.blockBambooShoot.getDefaultState(), 2);
                }
            }
        }
        return true;
    }
    @Override
    public IBlockState getStateFromMeta(int meta) {
    	return getDefaultState().withProperty(AGE, meta);
    }
    @Override
    public int getMetaFromState(IBlockState state) {
		return state.getValue(AGE);
    }
    @Override
    protected BlockStateContainer createBlockState() {
    	return new BlockStateContainer(this, AGE);
    }
}
