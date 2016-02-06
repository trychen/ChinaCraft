package unstudio.chinacraft.block.generation.ore;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import unstudio.chinacraft.common.ChinaCraft;
import cpw.mods.fml.common.IWorldGenerator;

public class SilverOre extends Block implements IWorldGenerator {

    public SilverOre() {
        super(Material.rock);
        setBlockName(StatCollector.translateToLocal("silver_ore"));
        setHardness(4.2F);
        setResistance(15.0F);
        setLightLevel(0.0F);
        setStepSound(soundTypeStone);
        setCreativeTab(ChinaCraft.tabCore);
        setHarvestLevel("pickaxe", 1);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
            IChunkProvider chunkProvider) {
        if (world.provider.dimensionId == 0) {
            for (int i = 0; i < 7; i++) {
                int firstBlockXCoord = chunkX * 16 + random.nextInt(16);
                int firstBlockYCoord = random.nextInt(36);
                int firstBlockZCoord = chunkZ * 16 + random.nextInt(16);

                (new WorldGenMinable(this, 5, Blocks.stone)).generate(world, random, firstBlockXCoord, firstBlockYCoord,
                        firstBlockZCoord);
            }
        }
    }
}
