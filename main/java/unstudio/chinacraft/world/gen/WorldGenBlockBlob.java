package unstudio.chinacraft.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

@Deprecated
public class WorldGenBlockBlob extends WorldGenerator{

	@Deprecated
    private Block genBlock;
	@Deprecated
    private int field_150544_b;

    @Deprecated
    public WorldGenBlockBlob(Block genBlock, int p_i45450_2_)
    {
        super(false);
        this.genBlock = genBlock;
        this.field_150544_b = p_i45450_2_;
    }

    @Deprecated
    public boolean generate(World world, Random random, int x, int y, int z)
    {
            if (y <= 3)
            {
                return false;
            }

            int k2 = this.field_150544_b;

            for (int l = 0; k2 >= 0 && l < 3; ++l)
            {
                int i1 = k2 + random.nextInt(2);
                int j1 = k2 + random.nextInt(2);
                int k1 = k2 + random.nextInt(2);
                float f = (float)(i1 + j1 + k1) * 0.333F + 0.5F;

                for (int l1 = x - i1; l1 <= x + i1; ++l1)
                {
                    for (int i2 = z - k1; i2 <= z + k1; ++i2)
                    {
                        for (int j2 = y - j1; j2 <= y + j1; ++j2)
                        {
                            float f1 = (float)(l1 - x);
                            float f2 = (float)(i2 - z);
                            float f3 = (float)(j2 - y);

                            if (f1 * f1 + f2 * f2 + f3 * f3 <= f * f&&world.getBlock(l1, j2, i2) == Blocks.stone)
                            {
                                world.setBlock(l1, j2, i2, this.genBlock, 0, 4);
                            }
                        }
                    }
                }

                x += -(k2 + 1) + random.nextInt(2 + k2 * 2);
                z += -(k2 + 1) + random.nextInt(2 + k2 * 2);
                y += 0 - random.nextInt(2);
            }
            return true;
    }

}
