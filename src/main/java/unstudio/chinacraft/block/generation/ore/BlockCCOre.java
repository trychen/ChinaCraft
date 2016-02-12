package unstudio.chinacraft.block.generation.ore;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import unstudio.chinacraft.common.ChinaCraft;

import java.util.Random;

/**
 * Created by trych on 2016/2/12.
 */
public class BlockCCOre extends Block implements IWorldGenerator {
    private GenerateExecutor generateExecutor;
    public BlockCCOre(String name ,Material material, int harvestLevel, float hardness, GenerateExecutor generateExecutor) {
        super(material);
        setCreativeTab(ChinaCraft.tabCore);
        setBlockName(name);
        setResistance(15.0F);
        setStepSound(soundTypeStone);
        setHardness(hardness);
        setHarvestLevel("pickaxe", harvestLevel);
        this.generateExecutor = generateExecutor;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (generateExecutor != null) generateExecutor.generate(this,random,chunkX,chunkZ,world,chunkGenerator,chunkProvider);
    }
}
