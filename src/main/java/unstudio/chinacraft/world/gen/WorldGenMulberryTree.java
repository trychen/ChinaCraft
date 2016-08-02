package unstudio.chinacraft.world.gen;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.IWorldGenerator;
import unstudio.chinacraft.common.ChinaCraft;

import java.util.Random;

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
    public boolean generate(World world, Random random, BlockPos pos) {
        int l = random.nextInt(this.maxTreeHeight - this.minTreeHeight) + this.minTreeHeight;
        boolean flag = true;

        if (pos.getY() >= 1 && pos.getY() + l + 1 <= 256) {
            byte b0;
            int k1;
            Block block;

            for (int i1 = pos.getY(); i1 <= pos.getY() + 1 + l; ++i1) {
                b0 = 1;

                if (i1 == pos.getY()) {
                    b0 = 0;
                }

                if (i1 >= pos.getY() + 1 + l - 2) {
                    b0 = 2;
                }

                for (int j1 = pos.getX() - b0; j1 <= pos.getX() + b0 && flag; ++j1) {
                    for (k1 = pos.getZ() - b0; k1 <= pos.getZ() + b0 && flag; ++k1) {
                        if (i1 >= 0 && i1 < 256) {
                            block = world.getBlockState(new BlockPos(j1, i1, k1)).getBlock();

                            if (!this.isReplaceable(world, new BlockPos(j1, i1, k1))) {
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
                Block block2 = world.getBlockState(pos.down()).getBlock();

                boolean isSoil = block2.canSustainPlant(block2.getDefaultState(), world, pos.down(),
                        EnumFacing.UP, (IPlantable) treeSapling);
                if (isSoil && pos.getY() < 256 - l - 1) {
                    block2.onPlantGrow(block2.getDefaultState(), world, pos.down(), pos);
                    b0 = 3;
                    byte b1 = 0;
                    int l1;
                    int i2;
                    int j2;
                    int i3;

                    for (k1 = pos.getY() - b0 + l; k1 <= pos.getY() + l; ++k1) {
                        i3 = k1 - (pos.getY() + l);
                        l1 = b1 + 1 - i3 / 2;

                        for (i2 = pos.getX() - l1; i2 <= pos.getX() + l1; ++i2) {
                            j2 = i2 - pos.getX();

                            for (int k2 = pos.getZ() - l1; k2 <= pos.getZ() + l1; ++k2) {
                                int l2 = k2 - pos.getZ();

                                if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || random.nextInt(2) != 0 && i3 != 0) {
                                    Block block1 = world.getBlockState(new BlockPos(i2, k1, k2)).getBlock();

                                    if (block1.isAir(world.getBlockState(new BlockPos(i2, k1, k2)), world, new BlockPos(i2, k1, k2))
                                            || block1.isLeaves(world.getBlockState(new BlockPos(i2, k1, k2)), world, new BlockPos(i2, k1, k2))) {
                                        this.setBlockAndNotifyAdequately(world, new BlockPos(i2, k1, k2), treeLeaf.getDefaultState());
                                    }
                                }
                            }
                        }
                    }

                    for (k1 = 0; k1 < l; ++k1) {
                        block = world.getBlockState(pos.add(0, k1, 0)).getBlock();

                        if (block.isAir(block.getDefaultState(), world, pos.add(0, k1, 0))
                                || block.isLeaves(block.getDefaultState(), world, pos.add(0, k1, 0))) {
                            this.setBlockAndNotifyAdequately(world, pos.add(0, k1, 0),
                                    treeBlock.getDefaultState());
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
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int x = chunkX * 16 + random.nextInt(16);
        int z = chunkZ * 16 + random.nextInt(16);
        Biome biome = world.getBiomeForCoordsBody(new BlockPos(x, 0, z));
        if (biome.getBiomeClass() == BiomeForest.class) {
            Block topBlock = world.getBlockState(world.getHeight(new BlockPos(x, 0, z))).getBlock();
            if (topBlock == Blocks.GRASS) {
                this.generate(world, random, world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)));
            }
        }
    }
}
