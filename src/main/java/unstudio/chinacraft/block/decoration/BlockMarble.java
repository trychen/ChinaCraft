package unstudio.chinacraft.block.decoration;

import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import unstudio.chinacraft.block.BlockBase;
import unstudio.chinacraft.common.ChinaCraft;

import java.util.Random;

public class BlockMarble extends BlockBase implements IWorldGenerator {

    public BlockMarble() {
        super(Material.rock);
        setUnlocalizedName("marble");
        setHardness(2.0F);
        setResistance(10.0F);
        setSoundType(SoundType.Stone);
        setCreativeTab(ChinaCraft.tabCore);
        setHarvestLevel("pickaxe", 1);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
                         IChunkProvider chunkProvider) {
        if (world.provider.getDimensionId() == 0 && random.nextInt(9) == 0) {
            int firstBlockXCoord = chunkX * 16 + random.nextInt(16);
            int firstBlockYCoord = random.nextInt(48) + 32;
            int firstBlockZCoord = chunkZ * 16 + random.nextInt(16);

            (new WorldGenMinable(this.getDefaultState(), 32)).generate(world, random, new BlockPos(firstBlockXCoord, firstBlockYCoord,
                    firstBlockZCoord));
        }
    }
}
