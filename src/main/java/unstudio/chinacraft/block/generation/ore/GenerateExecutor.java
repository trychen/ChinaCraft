package unstudio.chinacraft.block.generation.ore;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

/**
 * Created by trych on 2016/2/12.
 */
public interface GenerateExecutor {
    void generate(Block block, Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider);
}
