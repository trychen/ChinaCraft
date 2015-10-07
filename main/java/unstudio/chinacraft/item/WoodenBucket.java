package unstudio.chinacraft.item;

import cpw.mods.fml.common.eventhandler.Event;
import unstudio.chinacraft.ChinaCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemReed;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class WoodenBucket extends Item{

    private Block isFull;
    
	public WoodenBucket(Block p_i45331_1_) {
        this.maxStackSize = 1;
        this.isFull = p_i45331_1_;
		setCreativeTab(ChinaCraft.tabCore);
		if(this.isFull == Blocks.air){setUnlocalizedName("wooden_bucket");}else{setUnlocalizedName("wooden_bucket_water");}
	}
    
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
		if(player.isSneaking()) {
			return item;
		}
	       boolean flag = this.isFull == Blocks.air;
	        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, flag);

	        if (movingobjectposition == null)
	        {
	            return item;
	        }
	        else
	        {
	            FillBucketEvent event = new FillBucketEvent(player, item, world, movingobjectposition);
	            if (MinecraftForge.EVENT_BUS.post(event))
	            {
	                return item;
	            }

	            if (event.getResult() == Event.Result.ALLOW)
	            {
	                if (player.capabilities.isCreativeMode)
	                {
	                    return item;
	                }

	                if (--item.stackSize <= 0)
	                {
	                    return event.result;
	                }

	                if (!player.inventory.addItemStackToInventory(event.result))
	                {
	                    player.dropPlayerItemWithRandomChoice(event.result, false);
	                }

	                return item;
	            }
	            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
	            {
	                int i = movingobjectposition.blockX;
	                int j = movingobjectposition.blockY;
	                int k = movingobjectposition.blockZ;

	                if (!world.canMineBlock(player, i, j, k))
	                {
	                    return item;
	                }

	                if (flag)
	                {
	                    if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, item))
	                    {
	                        return item;
	                    }

	                    Material material = world.getBlock(i, j, k).getMaterial();
	                    int l = world.getBlockMetadata(i, j, k);

	                    if (material == Material.water && l == 0)
	                    {
	                        world.setBlockToAir(i, j, k);
	                        return this.func_150910_a(item, player, ChinaCraft.woodenBucket_Water);
	                    }
	                }
	                else
	                {
	                    if (this.isFull == Blocks.air)
	                    {
	                        return new ItemStack(ChinaCraft.woodenBucket);
	                    }

	                    if (movingobjectposition.sideHit == 0)
	                    {
	                        --j;
	                    }

	                    if (movingobjectposition.sideHit == 1)
	                    {
	                        ++j;
	                    }

	                    if (movingobjectposition.sideHit == 2)
	                    {
	                        --k;
	                    }

	                    if (movingobjectposition.sideHit == 3)
	                    {
	                        ++k;
	                    }

	                    if (movingobjectposition.sideHit == 4)
	                    {
	                        --i;
	                    }

	                    if (movingobjectposition.sideHit == 5)
	                    {
	                        ++i;
	                    }

	                    if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, item))
	                    {
	                        return item;
	                    }

	                    if (this.tryPlaceContainedLiquid(world, i, j, k) && !player.capabilities.isCreativeMode)
	                    {
	                        return new ItemStack(ChinaCraft.woodenBucket);
	                    }
	                }
	            }
	            return item;
	        }
	}

    private ItemStack func_150910_a(ItemStack p_150910_1_, EntityPlayer p_150910_2_, Item p_150910_3_)
    {
        if (p_150910_2_.capabilities.isCreativeMode)
        {
            return p_150910_1_;
        }
        else if (--p_150910_1_.stackSize <= 0)
        {
            return new ItemStack(p_150910_3_);
        }
        else
        {
            if (!p_150910_2_.inventory.addItemStackToInventory(new ItemStack(p_150910_3_)))
            {
                p_150910_2_.dropPlayerItemWithRandomChoice(new ItemStack(p_150910_3_, 1, 0), false);
            }

            return p_150910_1_;
        }
    }

    /**
     * Attempts to place the liquid contained inside the bucket.
     */
    public boolean tryPlaceContainedLiquid(World p_77875_1_, int p_77875_2_, int p_77875_3_, int p_77875_4_)
    {
        if (this.isFull == Blocks.air)
        {
            return false;
        }
        else
        {
            Material material = p_77875_1_.getBlock(p_77875_2_, p_77875_3_, p_77875_4_).getMaterial();
            boolean flag = !material.isSolid();

            if (!p_77875_1_.isAirBlock(p_77875_2_, p_77875_3_, p_77875_4_) && !flag)
            {
                return false;
            }
            else
            {
                if (p_77875_1_.provider.isHellWorld && this.isFull == Blocks.flowing_water)
                {
                    p_77875_1_.playSoundEffect((double)((float)p_77875_2_ + 0.5F), (double)((float)p_77875_3_ + 0.5F), (double)((float)p_77875_4_ + 0.5F), "random.fizz", 0.5F, 2.6F + (p_77875_1_.rand.nextFloat() - p_77875_1_.rand.nextFloat()) * 0.8F);

                    for (int l = 0; l < 8; ++l)
                    {
                        p_77875_1_.spawnParticle("largesmoke", (double)p_77875_2_ + Math.random(), (double)p_77875_3_ + Math.random(), (double)p_77875_4_ + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
                }
                else
                {
                    if (!p_77875_1_.isRemote && flag && !material.isLiquid())
                    {
                        p_77875_1_.func_147480_a(p_77875_2_, p_77875_3_, p_77875_4_, true);
                    }

                    p_77875_1_.setBlock(p_77875_2_, p_77875_3_, p_77875_4_, this.isFull, 0, 3);
                }

                return true;
            }
        }
    }
    
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
    	if(!p_77648_2_.isSneaking()) {
    		return false;
    	}
    	
        Block block = p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_);

        if (block == Blocks.snow_layer && (p_77648_3_.getBlockMetadata(p_77648_4_, p_77648_5_, p_77648_6_) & 7) < 1)
        {
            p_77648_7_ = 1;
        }
        else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush)
        {
            if (p_77648_7_ == 0)
            {
                --p_77648_5_;
            }

            if (p_77648_7_ == 1)
            {
                ++p_77648_5_;
            }

            if (p_77648_7_ == 2)
            {
                --p_77648_6_;
            }

            if (p_77648_7_ == 3)
            {
                ++p_77648_6_;
            }

            if (p_77648_7_ == 4)
            {
                --p_77648_4_;
            }

            if (p_77648_7_ == 5)
            {
                ++p_77648_4_;
            }
        }

        if (!p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_))
        {
            return false;
        }
        else if (p_77648_1_.stackSize == 0)
        {
            return false;
        }
        else
        {
            if (p_77648_3_.canPlaceEntityOnSide(ChinaCraft.blockWoodenBucket, p_77648_4_, p_77648_5_, p_77648_6_, false, p_77648_7_, (Entity)null, p_77648_1_))
            {
                int i1 = ChinaCraft.blockWoodenBucket.onBlockPlaced(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_, 0);

                if (p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, ChinaCraft.blockWoodenBucket, isFull == Blocks.air?0:isFull == Blocks.flowing_water?1:0, 3))
                {
                    if (p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) == ChinaCraft.blockWoodenBucket)
                    {
                    	ChinaCraft.blockWoodenBucket.onBlockPlacedBy(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_2_, p_77648_1_);
                    	ChinaCraft.blockWoodenBucket.onPostBlockPlaced(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, i1);
                    }

                    p_77648_3_.playSoundEffect((double)((float)p_77648_4_ + 0.5F), (double)((float)p_77648_5_ + 0.5F), (double)((float)p_77648_6_ + 0.5F), ChinaCraft.blockWoodenBucket.stepSound.func_150496_b(), (ChinaCraft.blockWoodenBucket.stepSound.getVolume() + 1.0F) / 2.0F, ChinaCraft.blockWoodenBucket.stepSound.getPitch() * 0.8F);
                    --p_77648_1_.stackSize;
                }
            }

            return true;
        }
    }
}
