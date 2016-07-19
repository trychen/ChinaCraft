package unstudio.chinacraft.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import unstudio.chinacraft.common.ChinaCraft;

public class WorldGenCCFlower implements IWorldGenerator {

    private void Gen(Random random, int chunkX, int chunkZ, World world, Block block) {
        int x = chunkX * 16 + random.nextInt(8) - random.nextInt(8);
        int z = chunkZ * 16 + random.nextInt(8) - random.nextInt(8);
        int y = world.getChunksLowestHorizon(x, z) + random.nextInt(4) - random.nextInt(4);
        BlockPos pos = new BlockPos(x, y, z);
        if (world.isAirBlock(pos) && block.canPlaceBlockAt(world, pos));
            world.setBlockState(pos, block.getDefaultState(), 3);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
            IChunkProvider chunkProvider) {
        for (int i = 0; i <= 15; i++) {
            if (random.nextBoolean())
                if (world.provider.getDimensionId() == 0) {
                    int j = random.nextInt(3);
                    switch (j) {
                    case 0:
                        Gen(random, chunkX, chunkZ, world, ChinaCraft.peony);
                        break;
                    case 1:
                        Gen(random, chunkX, chunkZ, world, ChinaCraft.azalea);
                        break;
                    case 2:
                        Gen(random, chunkX, chunkZ, world, ChinaCraft.chrysanthemum);
                        break;
                    }
                    break;
                }
        }
    }
}
