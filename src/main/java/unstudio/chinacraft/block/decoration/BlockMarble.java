package unstudio.chinacraft.block.decoration;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import unstudio.chinacraft.block.BlockBase;
import unstudio.chinacraft.common.ChinaCraft;
import cpw.mods.fml.common.IWorldGenerator;

public class BlockMarble extends BlockBase implements IWorldGenerator{

    public BlockMarble() {
        super(Material.rock);
        setBlockName("marble");
        setHardness(2.0F);
        setResistance(10.0F);
        setStepSound(soundTypeStone);
        setCreativeTab(ChinaCraft.tabCore);
        setHarvestLevel("pickaxe", 1);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
            IChunkProvider chunkProvider) {
        if (world.provider.dimensionId == 0 && random.nextInt(9) == 0) {
            int firstBlockXCoord = chunkX * 16 + random.nextInt(16);
            int firstBlockYCoord = random.nextInt(48) + 32;
            int firstBlockZCoord = chunkZ * 16 + random.nextInt(16);

            (new WorldGenMinable(this, 32 , Blocks.stone)).generate(world, random, firstBlockXCoord, firstBlockYCoord,
                    firstBlockZCoord);
        }
    }
}
