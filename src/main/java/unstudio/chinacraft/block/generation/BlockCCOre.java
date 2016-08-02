package unstudio.chinacraft.block.generation;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import unstudio.chinacraft.block.BlockBase;

import java.util.Random;

/**
 * 矿物父类含自动生成
 */
public class BlockCCOre extends BlockBase implements IWorldGenerator {

    private final int size;
    private final int frequency;
    private final int highest;
    private final int lowest;
    private final int dimensionID;

    /**
     * @param material    材质
     * @param size        数量
     * @param frequency   频率
     * @param highest     最高生成层
     * @param lowest      最低生成层
     * @param dimensionID 世界组
     */
    public BlockCCOre(Material material, int size, int frequency, int highest, int lowest, int dimensionID) {
        super(material);
        this.size = size;
        this.frequency = frequency;
        this.highest = highest;
        this.lowest = lowest;
        this.dimensionID = dimensionID;
        setSoundType(SoundType.STONE);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (dimensionID == world.provider.getDimension()) {
            for (int i = 0; i < frequency; i++) {
                int firstBlockXCoord = chunkX * 16 + random.nextInt(16);
                int firstBlockYCoord = random.nextInt(highest - lowest) + lowest;
                int firstBlockZCoord = chunkZ * 16 + random.nextInt(16);
                (new WorldGenMinable(getDefaultState(), size)).generate(world, random, new BlockPos(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord));
            }
        }
    }
}

