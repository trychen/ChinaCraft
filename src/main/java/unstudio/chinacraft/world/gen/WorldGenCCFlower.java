package unstudio.chinacraft.world.gen;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import unstudio.chinacraft.common.ChinaCraft;

import java.util.Random;

public class WorldGenCCFlower implements IWorldGenerator {

    private void Gen(Random random, int chunkX, int chunkZ, World world, Block block) {
        int x = chunkX * 16 + random.nextInt(8) - random.nextInt(8);
        int z = chunkZ * 16 + random.nextInt(8) - random.nextInt(8);
        int y = world.getHeight(new BlockPos(x, 0, z)).getY() + random.nextInt(4) - random.nextInt(4);

        if (world.isAirBlock(new BlockPos(x, y, z)) && block.canPlaceBlockAt(world, new BlockPos(x, y, z)))
            world.setBlockState(new BlockPos(x, y, z), block.getDefaultState());
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
                         IChunkProvider chunkProvider) {
        for (int i = 0; i <= 15; i++) {
            if (random.nextBoolean())
                if (world.provider.getDimension() == 0) {
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
