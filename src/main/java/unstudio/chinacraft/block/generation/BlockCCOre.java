package unstudio.chinacraft.block.generation;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import unstudio.chinacraft.block.BlockBase;
import unstudio.chinacraft.common.ChinaCraft;

import java.util.Random;

/**
 * Created by AAA on 2016/2/10.
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
    }

    public Block setHarvestLevelReturnBlock(String toolClass, int level) {
        super.setHarvestLevel(toolClass, level);
        return this;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (dimensionID == world.provider.dimensionId) {
            for (int i = 0; i < frequency; i++) {
                int firstBlockXCoord = chunkX * 16 + random.nextInt(16);
                int firstBlockYCoord = random.nextInt(highest - lowest) + lowest;
                int firstBlockZCoord = chunkZ * 16 + random.nextInt(16);
                (new WorldGenMinable(this, size, Blocks.stone)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
            }
        }
    }
}

