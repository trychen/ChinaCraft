package unstudio.chinacraft.item;

import cpw.mods.fml.common.eventhandler.Event;
import unstudio.chinacraft.ChinaCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
		setUnlocalizedName("wooden_bucket");
	}
    
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
		boolean flag = this.isFull == Blocks.air;
		MovingObjectPosition movingobjectposition = this
				.getMovingObjectPositionFromPlayer(world, player, flag);

		if (movingobjectposition == null) {
			return item;
		} else {
			if (player.isSneaking()) {
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;
				if (movingobjectposition.sideHit == 0) {
					--j;
				}

				if (movingobjectposition.sideHit == 1) {
					++j;
				}

				if (movingobjectposition.sideHit == 2) {
					--k;
				}

				if (movingobjectposition.sideHit == 3) {
					++k;
				}

				if (movingobjectposition.sideHit == 4) {
					--i;
				}

				if (movingobjectposition.sideHit == 5) {
					++i;
				}

				if (!player.canPlayerEdit(i, j, k,
						movingobjectposition.sideHit, item)) {
					return item;
				}

				if (i == (int)player.posX
						&& (j == (int)player.posY || j == ((int)player.posY) + 1)
						&& k == (int)player.posZ) {
					return item;
				}

				if (isFull == Blocks.air) {
					if (world.setBlock(i, j, k, ChinaCraft.blockWoodenBucket,
							0, 2) && !player.capabilities.isCreativeMode) {
						if (--item.stackSize <= 0) {
							return item;
						}
					}
				} else if (isFull == Blocks.water) {
					if (world.setBlock(i, j, k, ChinaCraft.blockWoodenBucket,
							1, 2) && !player.capabilities.isCreativeMode) {
						if (--item.stackSize <= 0) {
							return item;
						}
					}
				}
			} else {
				FillBucketEvent event = new FillBucketEvent(player, item,
						world, movingobjectposition);
				if (MinecraftForge.EVENT_BUS.post(event)) {
					return item;
				}

				if (event.getResult() == Event.Result.ALLOW) {
					if (player.capabilities.isCreativeMode) {
						return item;
					}

					if (--item.stackSize <= 0) {
						return event.result;
					}

					if (!player.inventory.addItemStackToInventory(event.result)) {
						player.dropPlayerItemWithRandomChoice(event.result,
								false);
					}

					return item;
				}
				if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
					int i = movingobjectposition.blockX;
					int j = movingobjectposition.blockY;
					int k = movingobjectposition.blockZ;

					if (!world.canMineBlock(player, i, j, k)) {
						return item;
					}

					if (flag) {
						if (!player.canPlayerEdit(i, j, k,
								movingobjectposition.sideHit, item)) {
							return item;
						}

						Material material = world.getBlock(i, j, k)
								.getMaterial();
						int l = world.getBlockMetadata(i, j, k);

						if (material == Material.water && l == 0) {
							world.setBlockToAir(i, j, k);
							return this.func_150910_a(item, player,
									ChinaCraft.woodenBucket_Water);
						}
					} else {
						if (this.isFull == Blocks.air) {
							return new ItemStack(ChinaCraft.woodenBucket);
						}

						if (movingobjectposition.sideHit == 0) {
							--j;
						}

						if (movingobjectposition.sideHit == 1) {
							++j;
						}

						if (movingobjectposition.sideHit == 2) {
							--k;
						}

						if (movingobjectposition.sideHit == 3) {
							++k;
						}

						if (movingobjectposition.sideHit == 4) {
							--i;
						}

						if (movingobjectposition.sideHit == 5) {
							++i;
						}

						if (!player.canPlayerEdit(i, j, k,
								movingobjectposition.sideHit, item)) {
							return item;
						}

						if (this.tryPlaceContainedLiquid(world, i, j, k)
								&& !player.capabilities.isCreativeMode) {
							return new ItemStack(ChinaCraft.woodenBucket);
						}
					}
				}
			}
		}
		return item;
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
}
