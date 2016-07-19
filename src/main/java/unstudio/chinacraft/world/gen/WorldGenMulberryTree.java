package unstudio.chinacraft.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.IWorldGenerator;
import unstudio.chinacraft.common.ChinaCraft;

public class WorldGenMulberryTree extends WorldGenAbstractTree implements IWorldGenerator {

    private final int minTreeHeight;
    private final int maxTreeHeight;
    private final Block treeBlock;
    private final Block treeLeaf;
    private final Block treeSapling;

    public WorldGenMulberryTree(boolean p_i45448_1_) {
        this(p_i45448_1_, 4, 7, ChinaCraft.mulberryLog, ChinaCraft.mulberryLeaf, ChinaCraft.mulberrySapling);
    }

    public WorldGenMulberryTree(boolean p_i45448_1_, int minTreeHeight, int maxTreeHeight, Block treeBlock,
            Block treeLeaf, Block treeSapling) {
        super(p_i45448_1_);
        this.minTreeHeight = minTreeHeight;
        this.maxTreeHeight = maxTreeHeight;
        this.treeBlock = treeBlock;
        this.treeLeaf = treeLeaf;
        this.treeSapling = treeSapling;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int l = rand.nextInt(this.maxTreeHeight - this.minTreeHeight) + this.minTreeHeight;
        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + l + 1 <= 256) {
            byte b0;
            int k1;
            Block block;

            for (int i1 = position.getY(); i1 <= position.getY() + 1 + l; ++i1) {
                b0 = 1;

                if (i1 == position.getY()) {
                    b0 = 0;
                }

                if (i1 >= position.getY() + 1 + l - 2) {
                    b0 = 2;
                }

                for (int j1 = position.getX() - b0; j1 <= position.getX() + b0 && flag; ++j1) {
                    for (k1 = position.getZ() - b0; k1 <= position.getZ() + b0 && flag; ++k1) {
                        if (i1 >= 0 && i1 < 256) {
                            BlockPos pos = new BlockPos(j1,i1,k1);

                            if (!this.isReplaceable(worldIn, pos)) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                BlockPos pos = position.add(0, -1, 0);
            	IBlockState state2 = worldIn.getBlockState(pos);
            	Block block2 = state2.getBlock();

                boolean isSoil = block2.canSustainPlant(worldIn, pos, EnumFacing.UP, (IPlantable) treeSapling);
                if (isSoil && position.getY() < 256 - l - 1) {
                    block2.onPlantGrow(worldIn, pos, position);
                    b0 = 3;
                    byte b1 = 0;
                    int l1;
                    int i2;
                    int j2;
                    int i3;

                    for (k1 = position.getY() - b0 + l; k1 <= position.getY() + l; ++k1) {
                        i3 = k1 - (position.getY() + l);
                        l1 = b1 + 1 - i3 / 2;

                        for (i2 = position.getX() - l1; i2 <= position.getX() + l1; ++i2) {
                            j2 = i2 - position.getX();

                            for (int k2 = position.getZ() - l1; k2 <= position.getZ() + l1; ++k2) {
                                int l2 = k2 - position.getZ();

                                if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || rand.nextInt(2) != 0 && i3 != 0) {
                                    BlockPos pos1 = new BlockPos(i2, k1, k2);
                                	Block block1 = worldIn.getBlockState(pos1).getBlock();

                                    if (block1.isAir(worldIn, pos1)
                                            || block1.isLeaves(worldIn, pos1)) {
                                        this.setBlockAndNotifyAdequately(worldIn, pos1, treeLeaf.getDefaultState());
                                    }
                                }
                            }
                        }
                    }

                    for (k1 = 0; k1 < l; ++k1) {
                        BlockPos pos1 = position.add(0, k1, 0);
                    	block = worldIn.getBlockState(pos1).getBlock();

                        if (block.isAir(worldIn, pos1)
                                || block.isLeaves(worldIn, pos1)) {
                            this.setBlockAndNotifyAdequately(worldIn, pos1, treeBlock.getDefaultState());
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
            IChunkProvider chunkProvider) {
        int x = chunkX * 16 + random.nextInt(16);
        int z = chunkZ * 16 + random.nextInt(16);
        int biomeID = world.getBiomeGenForCoords(new BlockPos(x, 0, z)).biomeID;
        if (biomeID == 4) {
        	BlockPos pos = new BlockPos(x, world.getChunksLowestHorizon(x, z), z);
            Block topBlock = world.getBlockState(pos).getBlock();
            if (topBlock == Blocks.grass) {
                this.generate(world, random, world.getTopSolidOrLiquidBlock(pos));
            }
        }
    }
}
