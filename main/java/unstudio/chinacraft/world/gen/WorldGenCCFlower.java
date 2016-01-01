package unstudio.chinacraft.world.gen;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import unstudio.chinacraft.common.ChinaCraft;

import java.util.Random;

public class WorldGenCCFlower implements IWorldGenerator {

    private void Gen(Random random, int chunkX, int chunkZ, World world, Block block) {
        int x = chunkX * 16 + random.nextInt(8) - random.nextInt(8);
        int z = chunkZ * 16 + random.nextInt(8) - random.nextInt(8);
        int y = world.getHeightValue(x, z) + random.nextInt(4) - random.nextInt(4);

        if (world.isAirBlock(x, y, z) && block.canBlockStay(world, x, y, z))
            world.setBlock(x, y, z, block, 0, 3);
        System.out.println(x + ";" + y + ";" + z + ";");
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        for (int i = 0; i <= 15; i++) {
            if (random.nextBoolean())
                if (world.provider.dimensionId == 0) {
                    int j = random.nextInt(2);
                    switch (j) {
                        case 0:
                            Gen(random, chunkX, chunkZ, world, ChinaCraft.peony);
                            break;
                        case 1:
                            Gen(random, chunkX, chunkZ, world, ChinaCraft.azalea);
                            break;
                    }
                    break;
                }
        }
    }
}
