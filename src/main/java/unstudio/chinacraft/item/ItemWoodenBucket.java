package unstudio.chinacraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import unstudio.chinacraft.common.ChinaCraft;

public class ItemWoodenBucket extends Item {

    private Block isFull;

    public ItemWoodenBucket(Block p_i45331_1_) {
        this.maxStackSize = 1;
        this.isFull = p_i45331_1_;
        setCreativeTab(ChinaCraft.tabCore);
        if (this.isFull == Blocks.air) {
            setUnlocalizedName("wooden_bucket");
        } else {
            setUnlocalizedName("wooden_bucket_water");
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
        if (player.isSneaking()) {
            return item;
        }
        boolean flag = this.isFull == Blocks.air;
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, flag);

        if (movingobjectposition == null) {
            return item;
        } else {
            FillBucketEvent event = new FillBucketEvent(player, item, world, movingobjectposition);
            if (MinecraftForge.EVENT_BUS.post(event)) {
                return item;
            }

            if (event.getResult() == Result.ALLOW) {
                if (player.capabilities.isCreativeMode) {
                    return item;
                }

                if (--item.stackSize <= 0) {
                    return event.result;
                }

                if (!player.inventory.addItemStackToInventory(event.result)) {
                    player.dropPlayerItemWithRandomChoice(event.result, false);
                }

                return item;
            }
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                BlockPos pos = movingobjectposition.getBlockPos();

                if (!world.canMineBlockBody(player, pos)) {
                    return item;
                }

                if (flag) {
                    if (!player.canPlayerEdit(pos, movingobjectposition.sideHit, item)) {
                        return item;
                    }
                    IBlockState state = world.getBlockState(pos);
                    Block block = state.getBlock();
                    Material material = block.getMaterial();
                    int l = block.getMetaFromState(state);

                    if (material == Material.water && l == 0) {
                        world.setBlockToAir(pos);
                        return this.func_150910_a(item, player, ChinaCraft.woodenBucket_Water);
                    }
                } else {
                    if (this.isFull == Blocks.air) {
                        return new ItemStack(ChinaCraft.woodenBucket);
                    }
                    pos = pos.offset(movingobjectposition.sideHit);
                    if (!player.canPlayerEdit(pos, movingobjectposition.sideHit, item)) {
                        return item;
                    }

                    if (this.tryPlaceContainedLiquid(world, pos) && !player.capabilities.isCreativeMode) {
                        return new ItemStack(ChinaCraft.woodenBucket);
                    }
                }
            }
            return item;
        }
    }

    private ItemStack func_150910_a(ItemStack p_150910_1_, EntityPlayer p_150910_2_, Item p_150910_3_) {
        if (p_150910_2_.capabilities.isCreativeMode) {
            return p_150910_1_;
        } else if (--p_150910_1_.stackSize <= 0) {
            return new ItemStack(p_150910_3_);
        } else {
            if (!p_150910_2_.inventory.addItemStackToInventory(new ItemStack(p_150910_3_))) {
                p_150910_2_.dropPlayerItemWithRandomChoice(new ItemStack(p_150910_3_, 1, 0), false);
            }

            return p_150910_1_;
        }
    }

    /**
     * Attempts to place the liquid contained inside the bucket.
     */
    public boolean tryPlaceContainedLiquid(World worldIn, BlockPos pos) {
        if (this.isFull == Blocks.air) {
            return false;
        } else {
            Material material = worldIn.getBlockState(pos).getBlock().getMaterial();
            boolean flag = !material.isSolid();

            if (!worldIn.isAirBlock(pos) && !flag) {
                return false;
            } else {
                if (!worldIn.provider.isSurfaceWorld() && this.isFull == Blocks.flowing_water) {
                    worldIn.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, "random.fizz",
                            0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

                    for (int l = 0; l < 8; ++l) {
                        worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX() + Math.random(), pos.getY() + Math.random(),
                                pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
                } else {
                    if (!worldIn.isRemote && flag && !material.isLiquid()) {
                        worldIn.destroyBlock(pos, true);
                    }

                    worldIn.setBlockState(pos, this.isFull.getDefaultState(), 3);
                }

                return true;
            }
        }
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!playerIn.isSneaking()) {
            return false;
        }
        IBlockState state = worldIn.getBlockState(pos);
        Block block = state.getBlock();

        if (block == Blocks.snow_layer && block.getMetaFromState(state) < 1) {
            side = EnumFacing.UP;
        } else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush) {
        	pos = pos.offset(side);
        }

        if (!playerIn.canPlayerEdit(pos, side, stack)) {
            return false;
        } else if (stack.stackSize == 0) {
            return false;
        } else {
            if (worldIn.canBlockBePlaced(ChinaCraft.blockWoodenBucket, pos, false,
                    side, (Entity) null, stack)) {
                IBlockState i1 = ChinaCraft.blockWoodenBucket.onBlockPlaced(worldIn, pos,
                        side, hitX, hitY, hitZ, isFull == Blocks.air ? 0 : isFull == Blocks.flowing_water ? 1 : 0, playerIn);

                if (worldIn.setBlockState(pos, i1, 3)) {
                    if (worldIn.getBlockState(pos).getBlock() == ChinaCraft.blockWoodenBucket) {
                        ChinaCraft.blockWoodenBucket.onBlockPlacedBy(worldIn, pos, i1, playerIn, stack);
                    }

                    worldIn.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F,
                            ChinaCraft.blockWoodenBucket.stepSound.getPlaceSound(),
                            (ChinaCraft.blockWoodenBucket.stepSound.getVolume() + 1.0F) / 2.0F,
                            ChinaCraft.blockWoodenBucket.stepSound.getFrequency() * 0.8F);
                    --stack.stackSize;
                }
            }

            return true;
        }
    }
}
