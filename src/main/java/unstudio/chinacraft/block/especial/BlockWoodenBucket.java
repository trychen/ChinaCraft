package unstudio.chinacraft.block.especial;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import unstudio.chinacraft.common.ChinaCraft;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockWoodenBucket extends Block {
	public static final PropertyBool FILLED_WATER = PropertyBool.create("filled_water");
    //private IIcon top, bottom, side, inner;

    public BlockWoodenBucket() {
        super(Material.WOOD);
        setHardness(0.5F);
        setResistance(5.0F);
        setSoundType(SoundType.WOOD);
        setUnlocalizedName("wooden_bucket");
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB mask, List<AxisAlignedBB> list, @Nullable Entity collidingEntity) {
        AxisAlignedBB aabb1 = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        float f = 0.125F;
        AxisAlignedBB aabb2 = new AxisAlignedBB(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        AxisAlignedBB aabb3 = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        AxisAlignedBB aabb4 = new AxisAlignedBB(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        AxisAlignedBB aabb5 = new AxisAlignedBB(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        addCollisionBoxToList(pos,mask,list,aabb1);
        addCollisionBoxToList(pos,mask,list,aabb2);
        addCollisionBoxToList(pos,mask,list,aabb3);
        addCollisionBoxToList(pos,mask,list,aabb4);
        addCollisionBoxToList(pos,mask,list,aabb5);
        super.addCollisionBoxToList(state,worldIn,pos,mask,list,collidingEntity);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (state.getValue(FILLED_WATER).booleanValue()) {
            return ChinaCraft.woodenBucket_Water;
        }
        return ChinaCraft.woodenBucket;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(ChinaCraft.woodenBucket);
    }

    @Override
    public void fillWithRain(World worldIn, BlockPos pos) {
        if (worldIn.rand.nextInt(20) == 1) {
        	IBlockState state = worldIn.getBlockState(pos);
            if(!state.getValue(FILLED_WATER))
            	worldIn.setBlockState(pos, state.withProperty(FILLED_WATER, true));
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        ItemStack item = playerIn.inventory.getCurrentItem();
        if (item == null)
            return true;
        if (state.getValue(FILLED_WATER)) {
            if (item.getItem() == Items.BUCKET) {
            	worldIn.setBlockState(pos, state.withProperty(FILLED_WATER, false));
                if (!playerIn.capabilities.isCreativeMode) {
                    if (--item.stackSize <= 0) {
                        playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem,
                                new ItemStack(Items.WATER_BUCKET));
                    } else {
                        playerIn.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET));
                    }
                }
                return true;
            } else if (item.getItem() == ChinaCraft.woodenBucket) {
            	worldIn.setBlockState(pos, state.withProperty(FILLED_WATER, false));
                if (!playerIn.capabilities.isCreativeMode) {
                    if (--item.stackSize <= 0) {
                        playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem,
                                new ItemStack(ChinaCraft.woodenBucket_Water));
                    } else {
                        playerIn.inventory.addItemStackToInventory(new ItemStack(ChinaCraft.woodenBucket_Water));
                    }
                }
                return true;
            } else {
                return true;
            }
        } else {
            if (item.getItem() == Items.WATER_BUCKET) {
            	worldIn.setBlockState(pos, state.withProperty(FILLED_WATER, true));
                if (!playerIn.capabilities.isCreativeMode) {
                    if (--item.stackSize <= 0) {
                        playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem,
                                new ItemStack(Items.BUCKET));
                    } else {
                        playerIn.inventory.addItemStackToInventory(new ItemStack(Items.BUCKET));
                    }
                }
                return true;
            } else if (item.getItem() == ChinaCraft.woodenBucket_Water) {
            	worldIn.setBlockState(pos, state.withProperty(FILLED_WATER, true));
                if (!playerIn.capabilities.isCreativeMode) {
                    if (--item.stackSize <= 0) {
                        playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem,
                                new ItemStack(ChinaCraft.woodenBucket));
                    } else {
                        playerIn.inventory.addItemStackToInventory(new ItemStack(ChinaCraft.woodenBucket));
                    }
                }
                return true;
            } else {
                return true;
            }
        }
    }
}
