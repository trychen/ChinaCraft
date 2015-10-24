package unstudio.chinacraft.world.gen;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import unstudio.chinacraft.ChinaCraft;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenMulberryTree extends WorldGenAbstractTree implements IWorldGenerator{
	
	private final int minTreeHeight;
	private final int maxTreeHeight;
	private final Block treeBlock;
	private final Block treeLeaf;
	private final Block treeSapling;

	public WorldGenMulberryTree(boolean p_i45448_1_) {
		this(p_i45448_1_, 4, 7, ChinaCraft.mulberryLog, ChinaCraft.mulberryLeaf, ChinaCraft.mulberrySapling);
	}
	
	public WorldGenMulberryTree(boolean p_i45448_1_,int minTreeHeight,int maxTreeHeight,Block treeBlock,Block treeLeaf,Block treeSapling) {
		super(p_i45448_1_);
		this.minTreeHeight = minTreeHeight;
		this.maxTreeHeight = maxTreeHeight;
		this.treeBlock = treeBlock;
		this.treeLeaf = treeLeaf;
		this.treeSapling = treeSapling;
	}

	@Override
    public boolean generate(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_)
    {
        int l = p_76484_2_.nextInt(this.maxTreeHeight - this.minTreeHeight) + this.minTreeHeight;
        boolean flag = true;

        if (p_76484_4_ >= 1 && p_76484_4_ + l + 1 <= 256)
        {
            byte b0;
            int k1;
            Block block;

            for (int i1 = p_76484_4_; i1 <= p_76484_4_ + 1 + l; ++i1)
            {
                b0 = 1;

                if (i1 == p_76484_4_)
                {
                    b0 = 0;
                }

                if (i1 >= p_76484_4_ + 1 + l - 2)
                {
                    b0 = 2;
                }

                for (int j1 = p_76484_3_ - b0; j1 <= p_76484_3_ + b0 && flag; ++j1)
                {
                    for (k1 = p_76484_5_ - b0; k1 <= p_76484_5_ + b0 && flag; ++k1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            block = p_76484_1_.getBlock(j1, i1, k1);

                            if (!this.isReplaceable(p_76484_1_, j1, i1, k1))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                Block block2 = p_76484_1_.getBlock(p_76484_3_, p_76484_4_ - 1, p_76484_5_);

                boolean isSoil = block2.canSustainPlant(p_76484_1_, p_76484_3_, p_76484_4_ - 1, p_76484_5_, ForgeDirection.UP, (IPlantable) treeSapling);
                if (isSoil && p_76484_4_ < 256 - l - 1)
                {
                    block2.onPlantGrow(p_76484_1_, p_76484_3_, p_76484_4_ - 1, p_76484_5_, p_76484_3_, p_76484_4_, p_76484_5_);
                    b0 = 3;
                    byte b1 = 0;
                    int l1;
                    int i2;
                    int j2;
                    int i3;

                    for (k1 = p_76484_4_ - b0 + l; k1 <= p_76484_4_ + l; ++k1)
                    {
                        i3 = k1 - (p_76484_4_ + l);
                        l1 = b1 + 1 - i3 / 2;

                        for (i2 = p_76484_3_ - l1; i2 <= p_76484_3_ + l1; ++i2)
                        {
                            j2 = i2 - p_76484_3_;

                            for (int k2 = p_76484_5_ - l1; k2 <= p_76484_5_ + l1; ++k2)
                            {
                                int l2 = k2 - p_76484_5_;

                                if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || p_76484_2_.nextInt(2) != 0 && i3 != 0)
                                {
                                    Block block1 = p_76484_1_.getBlock(i2, k1, k2);

                                    if (block1.isAir(p_76484_1_, i2, k1, k2) || block1.isLeaves(p_76484_1_, i2, k1, k2))
                                    {
                                        this.setBlockAndNotifyAdequately(p_76484_1_, i2, k1, k2, treeLeaf, 0);
                                    }
                                }
                            }
                        }
                    }

                    for (k1 = 0; k1 < l; ++k1)
                    {
                        block = p_76484_1_.getBlock(p_76484_3_, p_76484_4_ + k1, p_76484_5_);

                        if (block.isAir(p_76484_1_, p_76484_3_, p_76484_4_ + k1, p_76484_5_) || block.isLeaves(p_76484_1_, p_76484_3_, p_76484_4_ + k1, p_76484_5_))
                        {
                            this.setBlockAndNotifyAdequately(p_76484_1_, p_76484_3_, p_76484_4_ + k1, p_76484_5_, treeBlock, 0);
                        }
                    }
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int x = chunkX * 16 + random.nextInt(16);
		int z = chunkZ * 16 + random.nextInt(16);
		int biomeID = world.getBiomeGenForCoords(x,z).biomeID;
		if (biomeID == 4) {
			Block topBlock = world.getTopBlock(x, z);
			if(topBlock == Blocks.grass) {
			this.generate(world, random, x, world.getTopSolidOrLiquidBlock(x, z), z);
			}
		}
	}

}
