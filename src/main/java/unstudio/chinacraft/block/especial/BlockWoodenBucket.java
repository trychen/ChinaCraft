package unstudio.chinacraft.block.especial;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import unstudio.chinacraft.common.ChinaCraft;
public class BlockWoodenBucket extends Block {
	public static final PropertyBool FILLED_WATER = PropertyBool.create("filled_water");
    //private IIcon top, bottom, side, inner;

    public BlockWoodenBucket() {
        super(Material.wood);
        setHardness(0.5F);
        setResistance(5.0F);
        setStepSound(soundTypeWood);
        setUnlocalizedName("wooden_bucket");
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask,
    		List<AxisAlignedBB> list, Entity collidingEntity) {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        float f = 0.125F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        this.setBlockBoundsForItemRender();
    }

    @Override
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public boolean isOpaqueCube() {
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
    public Item getItem(World worldIn, BlockPos pos) {
        return ChinaCraft.woodenBucket;
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
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
    		EnumFacing side, float hitX, float hitY, float hitZ) {
        ItemStack item = playerIn.inventory.getCurrentItem();
        if (item == null)
            return true;
        if (state.getValue(FILLED_WATER)) {
            if (item.getItem() == Items.bucket) {
            	worldIn.setBlockState(pos, state.withProperty(FILLED_WATER, false));
                if (!playerIn.capabilities.isCreativeMode) {
                    if (--item.stackSize <= 0) {
                        playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem,
                                new ItemStack(Items.water_bucket));
                    } else {
                        playerIn.inventory.addItemStackToInventory(new ItemStack(Items.water_bucket));
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
            if (item.getItem() == Items.water_bucket) {
            	worldIn.setBlockState(pos, state.withProperty(FILLED_WATER, true));
                if (!playerIn.capabilities.isCreativeMode) {
                    if (--item.stackSize <= 0) {
                        playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem,
                                new ItemStack(Items.bucket));
                    } else {
                        playerIn.inventory.addItemStackToInventory(new ItemStack(Items.bucket));
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
