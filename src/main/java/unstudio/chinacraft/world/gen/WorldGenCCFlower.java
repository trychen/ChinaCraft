package unstudio.chinacraft.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;

import net.minecraft.world.gen.feature.WorldGenerator;
import unstudio.chinacraft.common.ChinaCraft;

public class WorldGenCCFlower extends WorldGenerator{

    private void gen(Random random, int blockX, int blockZ, World world, Block block) {
        int x = blockX + random.nextInt(16);
        int z = blockZ + random.nextInt(16);
        int y = world.getHeightValue(x, z) +1;

        if (world.isAirBlock(x, y, z) && block.canBlockStay(world, x, y, z))
            world.setBlock(x, y, z, block, 0, 3);
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        if(random.nextInt(9)==0) return true;
        if (world.provider.dimensionId != 0) return true;

        for (int i = 0; i <= 15; i++) {
            int j = random.nextInt(3);
            switch (j) {
                case 0:
                    gen(random, x, z, world, ChinaCraft.peony);
                    break;
                case 1:
                    gen(random, x, z, world, ChinaCraft.azalea);
                    break;
                case 2:
                    gen(random, x, z, world, ChinaCraft.chrysanthemum);
                    break;

            }
        }
        return true;
    }
}
